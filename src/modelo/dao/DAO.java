
package modelo.dao;

import java.util.List;

public interface DAO <T,K>{
    
    void insertar(T a) throws DAOException;
    
    void modificar(T a, K id) throws DAOException;
    
    void eliminar(T a) throws DAOException;
    
    void eliminarTodos(K id) throws DAOException;
    
    List<T> obtenerTodos() throws DAOException;
    
    T obtener(K id) throws DAOException;
                   
    List<T> obtenerTodos(K id) throws DAOException;
    
    K insertarId(T a) throws DAOException;
}
