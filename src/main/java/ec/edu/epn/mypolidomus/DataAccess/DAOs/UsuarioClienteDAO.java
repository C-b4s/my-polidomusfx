package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioClienteDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioClienteDAO extends DataHelperSQLiteDAO<UsuarioClienteDTO> {
    public UsuarioClienteDAO() throws AppException {
        super(UsuarioClienteDTO.class, "UsuarioCliente", "IdUsuarioCliente");
    }

    public boolean validarClavePropietario(String claveIngresada) throws AppException {
        String sql = "SELECT Contrasena FROM " + tableName +
                     " WHERE IdUsuarioTipo = ? AND Estado = 'A'";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, 2); // 2 = Tipo "Cliente/Propietario"
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String claveBD = rs.getString("Contrasena");
                    // Compara directamente, si quieres hashing aquí se hace igual
                    return claveBD.equals(claveIngresada);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al validar clave", e, getClass(), "validarClavePropietario");
        }
    }
    
}
