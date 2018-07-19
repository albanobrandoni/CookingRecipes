
package modelo.dao.sqlitemysql;


import modelo.dao.DAOException;
import modelo.dao.PasosDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Pasos;

public class SQLiteMySQLPasosDAO implements PasosDao{
           
    private Pasos convertir(ResultSet rs) throws SQLException{
       
        String descripcion = rs.getString("description");        
        int orden = rs.getInt("step_order");
        Long idDetalle = rs.getLong("recipes_idrecipes");
        Pasos paso = new Pasos( descripcion, orden, idDetalle);
        
        return paso;
    }
    
    
    private Connection conn;

    SQLiteMySQLPasosDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Pasos a) throws DAOException {
        
        String INSERT = "INSERT INTO steps (description, step_order, recipes_idrecipes) VALUES ( ?, ?, ?)";
        
        PreparedStatement stat = null;
        try{
        stat = conn.prepareStatement(INSERT);        
        stat.setString(1, a.getDescripcion());
        stat.setInt(2, a.getOrden());
        stat.setLong(3,a.getIdRecetas());
                
        if(stat.executeUpdate()==0){throw new DAOException("puede que no se haya guardado");}                    
        }catch(SQLException ex){throw new DAOException("Error en sql",ex);}
        finally{        
        if (stat != null){
           try {stat.close();}
           catch (SQLException ex) {throw new DAOException("error en SQL", ex); }                                                              
           }      
         }   
    }

   

    @Override
    public void eliminar(Pasos a) throws DAOException {
        
        String DELETE = "DELETE FROM steps WHERE idsteps = ?";
        
        PreparedStatement stat = null;                               
            int respuesta = 0;
            int i = 0;
        try {           
            respuesta = JOptionPane.showConfirmDialog(null, "Desea eliminar el paso numero: "+a.getOrden(), "Eliminando paso", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                 Long id = a.getId();
                 stat = conn.prepareStatement(DELETE);
                 stat.setLong(1, id);
                 i =  stat.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "El paso se borro con exito!");
                } 
            } else {
                    JOptionPane.showMessageDialog(null, "No pudo eliminarse el paso");
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
    public List<Pasos> obtenerTodos() throws DAOException {
        Long id = null;
        final String GETALL = "SELECT * FROM steps WHERE details_recipe_id ="+id+"ORDER BY step_order ASC";
        
        PreparedStatement stat = null;
            ResultSet rs = null;
            List <Pasos> pasos = new ArrayList<>();
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
            while (rs.next()){
                pasos.add(convertir(rs));
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
            return pasos;
        
    }

    @Override
    public Pasos obtener(Long orden) throws DAOException {
        
        String GETONE = "SELECT * FROM steps WHERE step_order = ?";
        
         PreparedStatement stat = null;
            ResultSet rs = null;
            Pasos paso = null;
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setLong(1, orden);
            rs =  (ResultSet) stat.executeQuery();
            if (rs.next()){
                paso = convertir(rs);
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
            return paso;    
    }

    @Override
    public void modificar(Pasos a, Long id) throws DAOException {
        String UPDATE = "UPDATE steps SET title = ?, description = ?, step_order = ? WHERE idsteps=";
        
        PreparedStatement stat = null;
        int respuesta,i;
        try{
        stat = (PreparedStatement) conn.prepareStatement(UPDATE);        
        stat.setString(1, a.getDescripcion());
        stat.setInt(2, a.getOrden());
        
        respuesta = JOptionPane.showConfirmDialog(null, "¿Realmente desea efectuar los cambios?","Modificando usuario", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                try{
                 i =  stat.executeUpdate();
                 if (i > 0){
                    JOptionPane.showMessageDialog(null, "El paso se actualizó con exito!");
                 }
                }catch(SQLException e){e.printStackTrace();}                                   
            } 
        
        }catch(SQLException ex){throw new DAOException("Error en sql",ex);
        }finally{
                 if (stat != null){
                    try {stat.close();}
                    catch (SQLException ex) {throw new DAOException("error en SQL", ex);}
                 }
                
        }
    }

    @Override
    public List<Pasos> obtenerTodos(Long idReceta) throws DAOException {
   
        String GETALL = "SELECT * FROM steps WHERE recipes_idrecipes = "+idReceta+" ORDER BY step_order ASC";
        
        PreparedStatement stat = null;
            ResultSet rs = null;
            List <Pasos> pasos = new ArrayList<>();
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
            while (rs.next()){
                pasos.add(convertir(rs));
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
            return pasos;
        
    }

    @Override
    public Long insertarId(Pasos a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTodos(Long id) throws DAOException {
        try {
            String DELETE = "DELETE FROM steps WHERE recipes_idrecipes ="+id;
            
            PreparedStatement stat = null;
            stat = conn.prepareStatement(DELETE);
            stat.executeUpdate();
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error con la base de datos: "+ex);
        }
    }

    
    
}
