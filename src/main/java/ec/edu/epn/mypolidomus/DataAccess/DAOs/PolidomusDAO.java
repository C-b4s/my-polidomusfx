package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.PolidomusDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class PolidomusDAO extends DataHelperSQLiteDAO<PolidomusDTO> {
    public PolidomusDAO() throws AppException {
        super(PolidomusDTO.class, "Polidomus", "IdPolidomus");
    }
}