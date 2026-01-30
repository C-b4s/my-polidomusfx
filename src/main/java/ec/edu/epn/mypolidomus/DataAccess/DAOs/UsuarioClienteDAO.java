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

   
    
}
