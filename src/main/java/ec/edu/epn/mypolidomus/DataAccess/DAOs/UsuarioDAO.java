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
