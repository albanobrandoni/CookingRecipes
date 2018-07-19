
package modelo.dao.sqlitemysql;

import modelo.dao.DAOException;
import modelo.dao.IngredientesDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Ingredientes;

public class SQLiteMySQLIngredientesDAO implements IngredientesDao{

                       
    private Ingredientes convertir(ResultSet rs) throws SQLException{        
        String nombre = rs.getString("name");
        Long idUsers = rs.getLong("users_idusers");
        Ingredientes ingrediente = new Ingredientes(nombre, idUsers);
        ingrediente.setId(rs.getLong("idingredients"));
        return ingrediente;
    }
    
    private Connection conn;
    
    SQLiteMySQLIngredientesDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Ingredientes a) throws DAOException {
        String INSERT = "INSERT INTO ingredients ( name, users_idusers) VALUES (?, ?)";
        Long id = controlador.Principal.getUsuario().getId();        
        PreparedStatement stat = null;
        try{
        stat = conn.prepareStatement(INSERT);
        stat.setString(1, a.getNombre());
        stat.setLong(2, id);
        if(stat.executeUpdate()>0){
            JOptionPane.showMessageDialog(null, "El ingrediente "+a.getNombre()+" se añadió exitosamente!!");
        }                            
        }catch(SQLException ex){JOptionPane.showMessageDialog( null, "puede que el alimento ya se encuentre en la lista");}
        finally{                 
            if (stat != null){
                try {stat.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
            }            
         }        
    }

    

    @Override
    public void eliminar(Ingredientes a) throws DAOException {
        String DELETE = "DELETE FROM ingredients WHERE name = ? AND users_idusers = ?";
        PreparedStatement stat = null;                               
        int respuesta = 0;
        int i = 0;
        try {           
            respuesta = JOptionPane.showConfirmDialog(null, "Desea eliminar el ingrediente:\n "+a.getNombre(), "Eliminando ingrediente", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                 String nombre = a.getNombre();
                 Long idUsuario = a.getIdUsers();
                 stat = conn.prepareStatement(DELETE);
                 stat.setString(1, nombre);
                 stat.setLong(2, idUsuario);
                 i =  stat.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "El ingrediente "+a.getNombre()+" se borro con exito!");
                } 
            }             
        } catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
         finally{
                if (stat != null){
                try {stat.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
                }                        
        }
    }

    @Override
    public List<Ingredientes> obtenerTodos() throws DAOException {
        return null;        
    }
    

    @Override
    public Ingredientes obtener(Long id) throws DAOException {
        
        String GETONE = "SELECT * FROM ingredients WHERE idingredients = ?";
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            Ingredientes ingrediente = null;
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs =  (ResultSet) stat.executeQuery();
            if (rs.next()){
                ingrediente = convertir(rs);
            } else {
                throw new DAOException("Puede que el usuario no exista");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql: " , ex);
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
            return ingrediente;        
    }

    

    @Override
    public List<Ingredientes> obtenerTodos(Long id) throws DAOException {
        String GETALL = "SELECT * FROM ingredients WHERE users_idusers ="+id+" ORDER BY name ASC";        
        PreparedStatement stat = null;
        ResultSet rs = null;
        List <Ingredientes> ingredientes = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
            while (rs.next()){
                ingredientes.add(convertir(rs));
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
            return ingredientes;
    }

    @Override
    public Long insertarId(Ingredientes a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Ingredientes a, Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTodos(Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
