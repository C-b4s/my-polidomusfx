package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.EstadoDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class EstadoDAO extends DataHelperSQLiteDAO<EstadoDTO> {
    public EstadoDAO() throws AppException {
        super(EstadoDTO.class, "Estado", "IdEstado");
    }
    
}
