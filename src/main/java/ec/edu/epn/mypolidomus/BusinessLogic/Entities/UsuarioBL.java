package ec.edu.epn.mypolidomus.BusinessLogic.Entities;

import java.util.List;

import ec.edu.epn.mypolidomus.DataAccess.DAOs.UsuarioDAO;
import ec.edu.epn.mypolidomus.DataAccess.DTOs.UsuarioDTO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class UsuarioBL {
    public UsuarioDAO uDao;
    
    public UsuarioBL() throws AppException {
         uDao = new UsuarioDAO();
    }

    public List<UsuarioDTO> getAll() throws AppException {
         return uDao.readAll();
    }

    public UsuarioDTO getBy(Integer id) throws AppException {
        return uDao.readBy(id);
    }

    public boolean add(UsuarioDTO oT) throws AppException {
        return uDao.create(oT);
    }

    public boolean upd(UsuarioDTO oT) throws AppException {
        return uDao.update(oT);
    }

    public boolean del(Integer id) throws AppException {
        return uDao.delete(id);
    }

    public boolean validar(String nombre) throws AppException{
        return uDao.validarClavePropietario(nombre);
    }
}
