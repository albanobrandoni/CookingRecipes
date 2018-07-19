
package modelo.dao.sqlitemysql;

import modelo.dao.DAOException;
import modelo.dao.UsuariosDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.logica.Logica;
import modelo.Usuarios;


public class SQLiteMySQLUsuarioDAO implements UsuariosDao {
    
                       
    private Connection conn;
    
    private Usuarios convertir(ResultSet rs) throws SQLException{
        String nombre = rs.getString("name");
        String password = rs.getString("password");        
        String perfil = rs.getString("profile");
        Usuarios usuario = new Usuarios(nombre, password, perfil);
        usuario.setId(rs.getLong("idusers"));
        return usuario;
    }
    
        
    SQLiteMySQLUsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Usuarios a) throws DAOException {
                        
        String INSERT = "INSERT INTO users ( name, password, profile) VALUES ( ?, ? ,? )";
        
        PreparedStatement stat = null;
        try{
        stat = conn.prepareStatement(INSERT);
        stat.setString(1, a.getNombre());
        stat.setString(2, Logica.sha1(a.getPassword()));
        stat.setString(3, a.getPerfil());       
        
        if(stat.executeUpdate()!=0){JOptionPane.showMessageDialog(null, "Te has registraro exitosamente!!!");}                                    
        }catch(SQLException ex){JOptionPane.showMessageDialog(null, "Puede que el usuario ya exista \n Intenta con otro nombre!");}
        finally{        
        if (stat != null){
           try {stat.close();}
           catch (SQLException ex) {throw new DAOException("error en SQL", ex); }                                                              
           }
        
         }   
    }     
    

    

    @Override
    public void eliminar(Usuarios a) throws DAOException {
        
        String DELETE = "DELETE FROM users WHERE idusers = ?";
        
        PreparedStatement stat = null;                               
            int respuesta = 0;
            int i = 0;
        try {           
            respuesta = JOptionPane.showConfirmDialog(null, "Desea eliminar el usuario:\n "+a.getNombre(), "Eliminando usuario", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                 Long id = a.getId();
                 stat = conn.prepareStatement(DELETE);
                 stat.setLong(1, id);
                 i =  stat.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "El usuario se borro con exito!");
                } 
            } else {
                    JOptionPane.showMessageDialog(null, "No pudo eliminarse el usuario");
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
    public List<Usuarios> obtenerTodos() throws DAOException {
        
        String GETALL = "SELECT * FROM users";
        
            PreparedStatement stat = null;
            ResultSet rs = null;
            List <Usuarios> usuarios = new ArrayList<>();
            try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
            while (rs.next()){
                usuarios.add(convertir(rs));
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
            return usuarios;
    }
    

    @Override
    public Usuarios obtener(Usuarios u) throws DAOException {
        final String GETONE = "SELECT * FROM users WHERE name =  ? AND password= ?";
            PreparedStatement stat = null;
            ResultSet rs = null;
            Usuarios usuario = null;
            try {
                stat = (PreparedStatement) conn.prepareStatement(GETONE);
                stat.setString(1, u.getNombre());
                stat.setString(2, u.getPassword());
                rs =  (ResultSet) stat.executeQuery();
                if (rs.next()){
                    usuario = convertir(rs);
                } 
           
            } catch (SQLException ex) {
            throw new DAOException("Error en sql: " , ex);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                   throw new DAOException("Error en sql: " , ex);
                }
            } 
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en sql: ", ex);
                }
            }
            
                        
        }
            return usuario;                 
    }


    @Override
    public List<Usuarios> obtenerTodos(Usuarios id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuarios insertarId(Usuarios a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Usuarios a, Usuarios id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTodos(Usuarios id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
 
}

