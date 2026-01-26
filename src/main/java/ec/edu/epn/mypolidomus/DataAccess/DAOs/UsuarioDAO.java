package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioDAO extends DataHelperSQLiteDAO<UsuarioDTO> {
    public UsuarioDAO() throws AppException {
        super(UsuarioDTO.class, "Usuario", "IdUsuario");
    }
}
