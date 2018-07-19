
package controlador;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import modelo.dao.DAOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Usuarios;
import modelo.logica.Config;
import modelo.logica.Logica;
import vista.formularios.CantidadIngrediente;
import vista.formularios.CredencialAdmin;
import vista.formularios.EditarReceta;
import vista.formularios.Login;
import vista.formularios.NuevaReceta;
import vista.formularios.NuevoIngrediente;
import vista.formularios.Preferencias;
import vista.formularios.Registrar;
import vista.formularios.VentanaPrincipal;
import vista.formularios.VerReceta;

public class Principal {
    
    private static Usuarios usuario;    
    private static String cfgPath;    
           
    public static void main(String[] args) throws SQLException, DAOException, IOException, FontFormatException {
       
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
              Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
              } catch (UnsupportedLookAndFeelException ex) {
                  Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                              
        Principal miPrincipal = new Principal();
        miPrincipal.iniciar();
            
    }
    
    
    public void iniciar() throws SQLException, IOException, FontFormatException{
               
      this.usuario = new Usuarios(null, null, null);
                        
      InputStream cookiesFuente = getClass().getResourceAsStream("/vista/fuentes/Cookies.ttf");
      InputStream purisaFuente = getClass().getResourceAsStream("/vista/fuentes/Purisa.ttf");      
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cookiesFuente));
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, purisaFuente));
                  
      File archivoProperties = new File("cfg.properties");      
      if(!archivoProperties.exists()){          
         BufferedWriter out = new BufferedWriter(new FileWriter(archivoProperties, true));
         out.write("dataBase=0\n"                                   
         +"userAdmin=d033e22ae348aeb5660fc2140aec35850c4da997\n"//admin sha1
         +"passAdmin=a2ffe4fca984196dbb5061a26b7f1fc6561eec42\n"//cookingadmin sha1
         +"userMysql=\n"
         +"passMysql=\n"        
         +"hostMysql=\n"
         +"databaseMysql=\n");        
         out.close();
         
         setCfgPath(archivoProperties.getAbsolutePath());
      }
      
      setCfgPath(archivoProperties.getAbsolutePath());
      
      InputStream is = getClass().getResourceAsStream("/modelo/recursos/databases/cook.db");                   
      File sqliteDestino = new File("cook.db");        
      if(!sqliteDestino.exists()){
        FileOutputStream fos = new FileOutputStream(sqliteDestino);          
        Logica.copiarStreams(is, fos);
        fos.close();
        is.close();

      }    
               
      VentanaPrincipal vp = new VentanaPrincipal();
      CredencialAdmin ca = new CredencialAdmin(vp, true);
      Preferencias p = new Preferencias(vp, true);
      Login l = new Login(vp, true);
      Registrar r = new Registrar(vp, true);
      NuevaReceta nr = new NuevaReceta(vp, true);
      NuevoIngrediente ni = new NuevoIngrediente(vp, true);
      CantidadIngrediente ci = new CantidadIngrediente(vp, true);
      VerReceta vr = new VerReceta(vp, true);
      EditarReceta er = new EditarReceta(vp, true);
      
      vp.setMiCredencialAdmin(ca);
      vp.setMiPreferencias(p);
      vp.setMiLogin(l);
      vp.setMiRegistrar(r);
      vp.setMiNuevaReceta(nr);
      vp.setMiNuevoIngrediente(ni);
      vp.setMiCantidadIngrediente(ci);
      vp.setMiVerReceta(vr);
      vp.setMiEditarReceta(er);
                  
      Controlador miControladorPrincipal = new Controlador(vp);
                
      vp.setControlador(vp, miControladorPrincipal);
      
      if(Config.getConfig("dataBase").equals("0")){
        vp.getMiPreferencias().getOpcionSqlite().setSelected(true);
      }
      
      if(Config.getConfig("dataBase").equals("1")){
        vp.getMiPreferencias().getOpcionMysql().setSelected(true);
      }
      
      vp.setVisible(true);
            
      vp.abrirLogin();            
      
    }
        
    
    public static Usuarios getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuarios usuario) {
        Principal.usuario = usuario;
    }

    public static String getCfgPath() {
        return cfgPath;
    }

    public static void setCfgPath(String cfgPath) {
        Principal.cfgPath = cfgPath;
    }
        
    
}
