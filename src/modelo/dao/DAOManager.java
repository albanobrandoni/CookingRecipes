
package modelo.dao;

public interface DAOManager {
    
    IngredienteRecetaDao getIngredienteRecetaDao();
    
    IngredientesDao getIngredientesDao();
    
    PasosDao getPasosDao();
            
    UsuariosDao getUsuariosDao();
            
    RecetasDao getRecetasDao();
           
    TipoDao getTipoDao();
}
