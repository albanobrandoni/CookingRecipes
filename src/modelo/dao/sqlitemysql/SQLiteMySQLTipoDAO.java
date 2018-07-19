
package modelo.dao.sqlitemysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Tipos;
import modelo.dao.DAOException;
import modelo.dao.TipoDao;


public class SQLiteMySQLTipoDAO implements TipoDao{
    
    private Connection conn;
    
    SQLiteMySQLTipoDAO (Connection con){
        this.conn = con;
    }
    
    private Tipos convertir(ResultSet rs) throws SQLException{        
        String nombre = rs.getString("name");
        Long idUsers = rs.getLong("users_idusers");
        Tipos tipo = new Tipos(nombre, idUsers);
        tipo.setId(rs.getLong("idtype"));
        return tipo;
    }
    
    @Override
    public void insertar(Tipos a) throws DAOException {
        String INSERT = "INSERT INTO type ( name, users_idusers) VALUES (?, ?)";
        Long id = controlador.Principal.getUsuario().getId();        
        PreparedStatement stat = null;
        try{
        stat = conn.prepareStatement(INSERT);
        stat.setString(1, a.getNombre());
        stat.setLong(2, id);
        if(stat.executeUpdate()>0){
            JOptionPane.showMessageDialog(null, "El tipo "+a.getNombre()+" se añadió exitosamente!!");
        }                            
        }catch(SQLException ex){JOptionPane.showMessageDialog( null, "puede que el tipo ya se encuentre en la lista");}
        finally{                 
            if (stat != null){
                try {stat.close();}
                catch (SQLException ex) {throw new DAOException("Error en sql: ", ex);}
            }            
         }
    }

    @Override
    public void modificar(Tipos a, Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Tipos a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipos> obtenerTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tipos obtener(Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipos> obtenerTodos(Long id) throws DAOException {
        String GETALL = "SELECT * FROM type WHERE users_idusers ="+id+" ORDER BY name ASC";        
        PreparedStatement stat = null;
        ResultSet rs = null;
        List <Tipos> tipos = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);            
            rs =  (ResultSet) stat.executeQuery();
            while (rs.next()){
                tipos.add(convertir(rs));
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
            return tipos;
    }

    @Override
    public Long insertarId(Tipos a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTodos(Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
