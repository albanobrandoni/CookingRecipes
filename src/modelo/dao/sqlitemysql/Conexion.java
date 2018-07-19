
package modelo.dao.sqlitemysql;


import java.io.IOException;
import modelo.dao.DAOManager;
import modelo.dao.IngredienteRecetaDao;
import modelo.dao.IngredientesDao;
import modelo.dao.PasosDao;
import modelo.dao.UsuariosDao;
import java.sql.DriverManager;
import java.sql.SQLException;
import modelo.dao.RecetasDao;
import java.sql.Connection;
import modelo.dao.TipoDao;
import modelo.logica.Config;

public class Conexion implements DAOManager{
    
    private static Connection conn = null;
             
    private IngredienteRecetaDao ingredienteReceta = null;
    private IngredientesDao ingredientes = null;
    private PasosDao pasos = null;    
    private UsuariosDao usuarios = null;
    private RecetasDao recetas = null;
    private TipoDao tipo = null;
    
    public Conexion() throws SQLException, IOException, ClassNotFoundException{
            
        
           if(Config.getConfig("dataBase").equals("0")){
               String url = "cook.db";               
               Class.forName("org.sqlite.JDBC");
               conn = DriverManager.getConnection("jdbc:sqlite:"+ url);
           }
           
           if(Config.getConfig("dataBase").equals("1")){
               
              String host = Config.getConfig("hostMysql");
              String database = Config.getConfig("databaseMysql");
              String username = Config.getConfig("userMysql");
              String password = Config.getConfig("passMysql");
                      
              conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,username,password);    
           }
           
    }
    
    public void cerrarConexion(Conexion c) throws SQLException{
        conn.close();
    }
    
    public void abrirConexion() throws SQLException, IOException{
           String host = Config.getConfig("hostMysql");
           String database = Config.getConfig("databaseMysql");
           String username = Config.getConfig("userMysql");
           String password = Config.getConfig("passMysql");
           conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,username,password);                    
        
    }

    @Override
    public IngredienteRecetaDao getIngredienteRecetaDao() {
        if(ingredienteReceta == null){
            
            ingredienteReceta = new SQLiteMySQLIngredienteRecetaDAO(conn);
        }
        return ingredienteReceta;
    }

    @Override
    public IngredientesDao getIngredientesDao() {
        if (ingredientes == null){            
           
            ingredientes = new SQLiteMySQLIngredientesDAO(conn);
        }
        return ingredientes;
    }

    @Override
    public PasosDao getPasosDao() {                
        if(pasos == null){
           
            pasos = new SQLiteMySQLPasosDAO(conn);
        }
        return pasos;
    }

    

    @Override
    public UsuariosDao getUsuariosDao() {
        if(usuarios == null){
           
            usuarios = new SQLiteMySQLUsuarioDAO(conn);
        }
        return usuarios;
    }
       
    @Override
    public RecetasDao getRecetasDao() {
     
           
        if(recetas == null){
            
            recetas = new SQLiteMySQLRecetasDAO(conn);            
        }
        return recetas;
    }
           
    @Override
    public TipoDao getTipoDao() {
        if(tipo == null){
            tipo = new SQLiteMySQLTipoDAO(conn);
        }
        return tipo;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Conexion.conn = conn;
    }
           
    
}
