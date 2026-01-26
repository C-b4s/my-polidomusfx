package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioTipoDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioTipoDAO extends DataHelperSQLiteDAO<UsuarioTipoDTO> {
    public UsuarioTipoDAO() throws AppException {
        super(UsuarioTipoDTO.class, "UsuarioTipo", "IdUsuarioTipo");
    }
    
}
