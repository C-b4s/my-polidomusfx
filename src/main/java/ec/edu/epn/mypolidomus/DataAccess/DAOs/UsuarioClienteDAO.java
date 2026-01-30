package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioClienteDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;
import ec.edu.epn.mypolidomus.BusinessLogic.Security.PasswordHasher;

public class UsuarioClienteDAO extends DataHelperSQLiteDAO<UsuarioClienteDTO> {
    public UsuarioClienteDAO() throws AppException {
        super(UsuarioClienteDTO.class, "UsuarioCliente", "IdUsuarioCliente");
    }

    public boolean validarCredenciales(String contraseniaIngresada) throws AppException {
        String sql = "SELECT Contrasena FROM " + tableName + " WHERE Estado = 'A' LIMIT 1";

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String contrasenaHashBD = rs.getString("Contrasena");
                    // Compara usando el hash con PasswordHasher.verify()
                    return PasswordHasher.verify(contraseniaIngresada, contrasenaHashBD);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al validar credenciales", e, getClass(), "validarCredenciales");
        }
    }
}
