package ec.edu.epn.mypolidomus.DataAccess.Helpers;

import ec.edu.epn.mypolidomus.DataAccess.Interfaces.IDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppConfig;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

import javafx.beans.property.Property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataHelperSQLiteDAO<T> implements IDAO<T> {

    protected final Class<T> DTOClass;
    protected final String tableName;
    protected final String tablePK;

    private static final String DBPath = AppConfig.getDATABASE();
    private static Connection conn = null;

    /* ==========================================================
       CONEXIÓN
       ========================================================== */

    protected static synchronized Connection openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DBPath);
        }
        return conn;
    }

    protected static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    protected String getDateTimeNow() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /* ==========================================================
       CONSTRUCTOR
       ========================================================== */

    public DataHelperSQLiteDAO(Class<T> dtoClass,
                              String tableName,
                              String tablePK) throws AppException {
        try {
            openConnection();
        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "DataHelperSQLiteDAO");
        }
        this.DTOClass = dtoClass;
        this.tableName = tableName;
        this.tablePK = tablePK;
    }

    /* ==========================================================
       MÉTODOS AUXILIARES PARA JAVAFX PROPERTIES
       ========================================================== */

    private Object extractValue(Field field, Object entity)
            throws IllegalAccessException {
        Object value = field.get(entity);
        if (value instanceof Property<?> prop) {
            return prop.getValue();
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private void assignValue(Field field, Object instance, Object value)
            throws IllegalAccessException {
        Object fieldValue = field.get(instance);

        if (fieldValue instanceof Property<?> prop) {
            ((Property<Object>) prop).setValue(value);
        } else {
            field.set(instance, value);
        }
    }

    /* ==========================================================
       CREATE
       ========================================================== */

    @Override
    public boolean create(T entity) throws AppException {
        Field[] fields = DTOClass.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : fields) {
            String name = field.getName();
            if (!name.equalsIgnoreCase(tablePK)
                && !name.equalsIgnoreCase("Estado")
                && !name.equalsIgnoreCase("FechaCreacion")
                && !name.equalsIgnoreCase("FechaModifica")) {

                columns.append(name).append(",");
                values.append("?,");
            }
        }

        columns.setLength(columns.length() - 1);
        values.setLength(values.length() - 1);

        String sql = "INSERT INTO " + tableName +
                     " (" + columns + ") VALUES (" + values + ")";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            int index = 1;
            for (Field field : fields) {
                String name = field.getName();
                if (!name.equalsIgnoreCase(tablePK)
                    && !name.equalsIgnoreCase("Estado")
                    && !name.equalsIgnoreCase("FechaCreacion")
                    && !name.equalsIgnoreCase("FechaModifica")) {

                    field.setAccessible(true);
                    stmt.setObject(index++, extractValue(field, entity));
                }
            }
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new AppException(null, e, getClass(), "create");
        }
    }

    /* ==========================================================
       UPDATE
       ========================================================== */

    @Override
    public boolean update(T entity) throws AppException {
        try {
            Field[] fields = DTOClass.getDeclaredFields();
            StringBuilder updates = new StringBuilder();
            Object pkValue = null;

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase(tablePK)) {
                    pkValue = extractValue(field, entity);
                } else {
                    updates.append(field.getName()).append(" = ?, ");
                }
            }

            updates.append("FechaModifica = ?");

            String sql = "UPDATE " + tableName +
                         " SET " + updates +
                         " WHERE " + tablePK + " = ?";

            try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
                int index = 1;
                for (Field field : fields) {
                    if (!field.getName().equalsIgnoreCase(tablePK)) {
                        stmt.setObject(index++, extractValue(field, entity));
                    }
                }

                stmt.setString(index++, getDateTimeNow());
                stmt.setObject(index, pkValue);

                return stmt.executeUpdate() > 0;
            }

        } catch (Exception e) {
            throw new AppException(null, e, getClass(), "update");
        }
    }

    /* ==========================================================
       DELETE (LÓGICO)
       ========================================================== */

    @Override
    public boolean delete(Integer id) throws AppException {
        String sql = "UPDATE " + tableName +
                     " SET Estado = ?, FechaModifica = ?" +
                     " WHERE " + tablePK + " = ?";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setString(1, "X");
            stmt.setString(2, getDateTimeNow());
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "delete");
        }
    }

    /* ==========================================================
       READ BY ID
       ========================================================== */

    @Override
    public T readBy(Integer id) throws AppException {
        String sql = "SELECT * FROM " + tableName +
                     " WHERE " + tablePK + " = ? AND Estado = 'A'";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "readBy");
        }
    }

    /* ==========================================================
       READ ALL
       ========================================================== */

    @Override
    public List<T> readAll() throws AppException {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE Estado = 'A'";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "readAll");
        }
        return list;
    }

    /* ==========================================================
       COUNT
       ========================================================== */

    @Override
    public Integer getMaxReg() throws AppException {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE Estado = 'A'";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "getMaxReg");
        }
    }

    /* ==========================================================
       MAPEO RESULTSET → DTO (JavaFX)
       ========================================================== */

    protected T mapResultSetToEntity(ResultSet rs) throws AppException {
        try {
            T instance = DTOClass.getDeclaredConstructor().newInstance();
            ResultSetMetaData meta = rs.getMetaData();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String column = meta.getColumnLabel(i);
                Object value = rs.getObject(i);

                Field field = DTOClass.getDeclaredField(column);
                field.setAccessible(true);
                assignValue(field, instance, value);
            }
            return instance;

        } catch (SQLException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchFieldException e) {

            throw new AppException(null, e, getClass(), "mapResultSetToEntity");
        }
    }
}