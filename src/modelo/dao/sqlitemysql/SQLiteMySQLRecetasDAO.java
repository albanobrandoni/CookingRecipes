
package modelo.dao.sqlitemysql;


import controlador.Principal;
import java.io.IOException;
import java.io.InputStream;
import modelo.dao.DAOException;
import modelo.dao.RecetasDao;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Recetas;

public class SQLiteMySQLRecetasDAO implements RecetasDao{
   
    private Connection conn;
    

    SQLiteMySQLRecetasDAO(Connection conn) {
        this.conn = conn;
    }
    
    private Recetas convertir(ResultSet rs) throws SQLException, IOException{
        InputStream is;
        int size;
        String autor = rs.getString("author");        
        String titulo = rs.getString("title");
        String consejo = rs.getString("comments");        
        String imagen = rs.getString("image");                    
        int comensales = rs.getInt("serves");
        String tiempo = rs.getString("time");
        String tiempoPreparacion = rs.getString("time_preparation");
        String tipo = rs.getString("type");
        Recetas receta = new Recetas(autor, titulo, consejo, imagen, comensales, tiempo, tiempoPreparacion, tipo);
        
        receta.setId(rs.getLong("idrecipes"));
        receta.setFecha(rs.getString("date"));        
        receta.setIdUsuarios(rs.getLong("users_idusers"));        
        
        return receta;
    }
    
    @Override
    public void insertar(Recetas a ) throws DAOException {
        String INSERT = "INSERT INTO recipes ( date, users_idusers, author, comments, image, serves, time, title, time_preparation, type) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";        
                
        Long idUsuario = Principal.getUsuario().getId();
        int ultimoId, resultado;
         
                
        Date d = new Date();
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");        
        String fecha = formato.format(d);
        
        String autor = a.getAutor();
        String consejo = a.getConsejo();
        String imagen =  a.getImagen();
        int comensales = a.getComensales();
        String tiempo = a.getTiempo();
        String titulo = a.getTitulo();
        String tiempoPreparacion = a.getTiempoPreparacion();
        String tipo = a.getTipo();
        
        PreparedStatement stat = null;
                                    
            try {
                                             
                stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
                stat.setString(1, fecha);
                stat.setLong(2, idUsuario);
                stat.setString(3, autor);
                stat.setString(4, consejo);
                stat.setString(5, imagen);                
                stat.setInt(6, comensales);
                stat.setString(7, tiempo);
                stat.setString(8, titulo);
                stat.setString(9,tiempoPreparacion);
                stat.setString(10, tipo);
                resultado = stat.executeUpdate();
                ResultSet rs = stat.getGeneratedKeys();
                if(rs.next()){
                    ultimoId =  rs.getInt(1);                    
                   
                }
                
                if(resultado == 0){JOptionPane.showMessageDialog(null, "Puede que la receta no se haya guardado!!");}
            } catch (SQLException ex) {                
                {JOptionPane.showMessageDialog(null, "Puede que la receta no se haya guardado!!");}
            }
        }   

        

   
    @Override
    public void eliminar(Recetas a) throws DAOException {
        
        String DELETE = "DELETE FROM recipes WHERE idrecipes = ?";
        
        PreparedStatement stat = null;                               
            int respuesta = 0;
            int i = 0;
        try {           
            respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la receta "+a.getTitulo()+"? ", "Eliminando receta", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                 Long id = a.getId();
                 stat = conn.prepareStatement(DELETE);
                 stat.setLong(1, id);
                 i =  stat.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "La receta "+a.getTitulo()+" se borro con exito!");
                } 
            } else {
                    JOptionPane.showMessageDialog(null, "No pudo eliminarse la receta");
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
    public List<Recetas> obtenerTodos() throws DAOException {
        
        
        final String GETALL = "SELECT * FROM recipes;";
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            List <Recetas> recetas = new ArrayList<>();
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
                                    
            while (rs.next()){               
                recetas.add(convertir(rs));                
            } 
            
        } catch (SQLException ex) {throw new DAOException("Error en sql: " , ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteMySQLRecetasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            return recetas;        
    }

    @Override
    public Recetas obtener(Long id) throws DAOException {
            
            String GETONE = "SELECT * FROM recipes WHERE idrecipes = ?";
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            Recetas receta = null;
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs =  (ResultSet) stat.executeQuery();
            if (rs.next()){
                receta = convertir(rs);
            } else {
                throw new DAOException("Puede que el usuario no exista");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql: " , ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteMySQLRecetasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            return receta;  
    }

    @Override
    public void modificar(Recetas a, Long idReceta) throws DAOException {
        try {
            String UPDATE = "UPDATE recipes SET date = ?, title = ?, author = ?, type = ?,"
                    + "serves = ?, time_preparation = ?, time = ?, comments = ?, image = ?"
                    + "  WHERE idrecipes="+idReceta;                                   
            int respuesta ;
            
            Date d = new Date();
            DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fecha = formato.format(d);
            
            String autor = a.getAutor();
            String consejo = a.getConsejo();
            String imagen = a.getImagen();
            int comensales = a.getComensales();
            String tiempo = a.getTiempo();
            String titulo = a.getTitulo();
            String tiempoPreparacion = a.getTiempoPreparacion();
            String tipo = a.getTipo();
            
            PreparedStatement stat = null;
            
            
                        
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, fecha);
            stat.setString(2, titulo);
            stat.setString(3, autor);
            stat.setString(4, tipo);
            stat.setInt(5, comensales);
            stat.setString(6, tiempoPreparacion);
            stat.setString(7, tiempo );
            stat.setString(8, consejo);
            stat.setString(9, imagen);
                        
            respuesta = JOptionPane.showConfirmDialog(null, "¿Realmente desea efectuar los cambios?","Modificando receta", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){                
                int i =  stat.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "La receta se actualizó con exito!");
                }                
            }
            } catch (SQLException ex) {
            Logger.getLogger(SQLiteMySQLRecetasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

                                    

    @Override
    public List<Recetas> obtenerTodos(Long id) throws DAOException {
                
            final String GETALL = "SELECT * FROM recipes WHERE users_idusers ="+id;
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            List <Recetas> recetas = new ArrayList<>();
            try {
            stat = conn.prepareStatement(GETALL);            
            rs =   stat.executeQuery();
                                    
            while (rs.next()){
               
                recetas.add(convertir(rs));
                
            } 
            
        } catch (SQLException ex) {throw new DAOException("Error en sql: " , ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLiteMySQLRecetasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            return recetas;
    }    

    @Override
    public Long insertarId(Recetas a) throws DAOException {
        String INSERT = "INSERT INTO recipes ( date, users_idusers, author, comments, image, serves, time, title, time_preparation, type) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";        
                
        Long idUsuario = Principal.getUsuario().getId();
        int resultado;
        Long ultimoId = null; 
                
        Date d = new Date();
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");        
        String fecha = formato.format(d);
        
        String autor = a.getAutor();
        String consejo = a.getConsejo();
        String imagen =  a.getImagen();
        int comensales = a.getComensales();
        String tiempo = a.getTiempo();
        String titulo = a.getTitulo();
        String tiempoPreparacion = a.getTiempoPreparacion();
        String tipo = a.getTipo();
        
        PreparedStatement stat = null;
        
                            
        try {                                                
                stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
                stat.setString(1, fecha);
                stat.setLong(2, idUsuario);
                stat.setString(3, autor);
                stat.setString(4, consejo);
                stat.setString(5,imagen);
                stat.setInt(6, comensales);
                stat.setString(7, tiempo);
                stat.setString(8, titulo);
                stat.setString(9,tiempoPreparacion);
                stat.setString(10, tipo);
                resultado = stat.executeUpdate();
                ResultSet rs = stat.getGeneratedKeys();
                if(rs.next()){
                   ultimoId = (new Long(rs.getInt(1)));                                                          
                }
                
                if(resultado == 0){JOptionPane.showMessageDialog(null, "Puede que la receta no se haya guardado!!");}
            } catch (SQLException ex) {                
                {JOptionPane.showMessageDialog(null, "Puede que la receta no se haya guardado!!");}
            }
        return ultimoId;
        }   
        
        
    @Override
    public void eliminarTodos(Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
    
}
