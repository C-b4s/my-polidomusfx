package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.BusinessLogic.Security.PasswordHasher;

public class UsuarioDAO extends DataHelperSQLiteDAO<UsuarioDTO> {
    public UsuarioDAO() throws AppException {
        super(UsuarioDTO.class, "Usuario", "IdUsuario");
    }

    public boolean validarCredenciales(String contraseniaIngresada) throws AppException {
        String sql = "SELECT Contrasena FROM " + tableName + " WHERE Estado = 'A' AND IdUsuarioTipo = 1 LIMIT 1";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String contrasenaHashBD = rs.getString("Contrasena");
                    return PasswordHasher.verify(contraseniaIngresada, contrasenaHashBD);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al validar credenciales", e, getClass(), "validarCredenciales");
        }
    }

    /** Busca un usuario activo por correo. */
    public UsuarioDTO readByCorreo(String correo) throws AppException {
        if (correo == null || correo.isBlank()) return null;
        String sql = "SELECT * FROM " + tableName + " WHERE Correo = ? AND Estado = 'A' LIMIT 1";
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setString(1, correo.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        } catch (SQLException e) {
            throw new AppException("Error al buscar usuario por correo", e, getClass(), "readByCorreo");
        }
    }

    /** Valida correo y contraseña; devuelve el UsuarioDTO si son correctos, null si no. */
    public UsuarioDTO validarCredencialesPorCorreo(String correo, String contrasenia) throws AppException {
        UsuarioDTO u = readByCorreo(correo);
        if (u == null) return null;
        if (contrasenia == null || contrasenia.isEmpty()) return null;
        return PasswordHasher.verify(contrasenia, u.getContrasena()) ? u : null;
    }
}
