package ec.edu.epn.mypolidomus.BusinessLogic.Entities;

import java.util.List;

import ec.edu.epn.mypolidomus.DataAccess.DAOs.UsuarioClienteDAO;
import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioClienteDTO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioClienteBL {

    public UsuarioClienteDAO uDao;
    
    public UsuarioClienteBL() throws AppException {
         uDao = new UsuarioClienteDAO();
    }

    public List<UsuarioClienteDTO> getAll() throws AppException {
         return uDao.readAll();
    }

    public UsuarioClienteDTO getBy(Integer id) throws AppException {
        return uDao.readBy(id);
    }

    public boolean add(UsuarioClienteDTO oT) throws AppException {
        return uDao.create(oT);
    }

    public boolean upd(UsuarioClienteDTO oT) throws AppException {
        return uDao.update(oT);
    }

    public boolean del(Integer id) throws AppException {
        return uDao.delete(id);
    }
}   
