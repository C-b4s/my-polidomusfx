package ec.edu.epn.mypolidomus.DataAccess.DAOs;

import ec.edu.epn.mypolidomus.DataAccess.DTOs.ArduinosDTO;
import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class ArduinosDAO extends DataHelperSQLiteDAO<ArduinosDTO> {
    public ArduinosDAO() throws AppException {
        super(ArduinosDTO.class, "Arduinos", "IdArduinos");
    }
    
}
