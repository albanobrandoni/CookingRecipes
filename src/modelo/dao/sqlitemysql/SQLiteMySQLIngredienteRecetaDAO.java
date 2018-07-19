
package modelo.dao.sqlitemysql;


import modelo.dao.DAOException;
import modelo.dao.IngredienteRecetaDao;
import java.util.List;
import modelo.IngredienteReceta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class SQLiteMySQLIngredienteRecetaDAO implements IngredienteRecetaDao{

    private Connection conn;
    
    private IngredienteReceta convertir(ResultSet rs) throws SQLException{
       
        Long idIngrediente = rs.getLong("ingredients_idingredients");        
        Long idReceta = rs.getLong("recipes_idrecipes");
        String cantidad = rs.getString("quantity");
        IngredienteReceta ir = new IngredienteReceta(idIngrediente,idReceta, cantidad);
        
        return ir;
    }
    

    public SQLiteMySQLIngredienteRecetaDAO(Connection conn) {
        this.conn = conn;
    }
    
    
    
    
    @Override
    public void insertar(IngredienteReceta a) throws DAOException {
        String INSERT = "INSERT INTO ingredients_has_recipes( ingredients_idingredients, recipes_idrecipes, quantity) VALUES (?, ?, ?)";        
        int resultado;
        PreparedStatement stat = null;
        try{
        stat = conn.prepareStatement(INSERT);
        stat.setLong(1, a.getIdIngrediente());
        stat.setLong(2, a.getIdReceta());
        stat.setString(3, a.getCantidad());
        resultado = stat.executeUpdate();
        if(resultado == 0){
            JOptionPane.showMessageDialog(null, "Algo falló");
        }                            
        }catch(SQLException ex){JOptionPane.showMessageDialog( null, "Los alimentos repetidos no se guardaran en la receta!"); }
        finally{                 
            if (stat != null){
                try {stat.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
            }            
         }        
    }

   
    @Override
    public void eliminar(IngredienteReceta a) {
               
    }

    @Override
    public List<IngredienteReceta> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IngredienteReceta obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public List<IngredienteReceta> obtenerTodos(Long idReceta) throws DAOException {
            
            final String GETALL = "SELECT * FROM ingredients_has_recipes WHERE recipes_idrecipes ="+idReceta;
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            List <IngredienteReceta> ingredienteReceta = new ArrayList<>();
            try {
            stat = conn.prepareStatement(GETALL);            
            rs =   stat.executeQuery();
                                    
            while (rs.next()){               
                ingredienteReceta.add(convertir(rs));                
            } 
            
        } catch (SQLException ex) {throw new DAOException("Error en sql: " , ex);
        } finally{
            if(rs != null){
                try {rs.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: " , ex);}                                   
            } 
            if (stat != null){
                try {stat.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
            }           
        }                        
            return ingredienteReceta;
    }

    @Override
    public Long insertarId(IngredienteReceta a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(IngredienteReceta a , Long idReceta) throws DAOException {
        try {
            String UPDATE = "UPDATE ingredients_has_recipes SET quantity = ? "
                    + "WHERE ingredients_idingredients ="+a.getIdIngrediente()+" AND recipes_idrecipes ="+idReceta;
            
            PreparedStatement stat = null;
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1,a.getCantidad());
            
            stat.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteMySQLIngredienteRecetaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarTodos(Long id) throws DAOException {
         try {
            String DELETE = "DELETE FROM ingredients_has_recipes WHERE recipes_idrecipes ="+id;
            
            PreparedStatement stat = null;
            stat = conn.prepareStatement(DELETE);
            stat.executeUpdate();
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error con la base de datos: "+ex);
        }
    }

    
    
}
