package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioTecnicoDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioTecnicoDAO extends DataHelperSQLiteDAO<UsuarioTecnicoDTO> {
    public UsuarioTecnicoDAO() throws AppException {
        super(UsuarioTecnicoDTO.class, "UsuarioTecnico", "IdUsuarioTecnico");
    }
    
}
