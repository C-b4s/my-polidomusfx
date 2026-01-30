package ec.edu.epn.mypolidomus.BusinessLogic;

import java.util.List;

import ec.edu.epn.mypolidomus.DataAccess.Helpers.DataHelperSQLiteDAO;
import ec.edu.epn.mypolidomus.DataAccess.Interfaces.IDAO;
import ec.edu.epn.mypolidomus.Infrastructure.AppException;

public class FactoryBL <T>{
private final IDAO<T> oDAO;
    public FactoryBL(Class<? extends DataHelperSQLiteDAO<T>> classDAO) {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            AppException er = new AppException("Error al instanciar classDAO<T>", e, getClass(), "FactoryBL(...)");
            throw new RuntimeException(er);
        }
    }
 
    public List<T> getAll() throws AppException {
         return oDAO.readAll();
    }

    public T getBy(Integer id) throws AppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws AppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws AppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws AppException {
        return oDAO.delete(id);
    }
}
