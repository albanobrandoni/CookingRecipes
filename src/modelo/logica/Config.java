
package modelo.logica;

import controlador.Principal;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class Config {
                        
    public static void setConfig(String clave, String valor) throws FileNotFoundException, IOException{
         
        Properties pr = new Properties();
         pr.load(new FileReader(Principal.getCfgPath()));
         pr.setProperty(clave, valor);
         pr.store(new FileWriter(Principal.getCfgPath()), null);
                   
    }
   
    public static String getConfig(String clave) throws FileNotFoundException, IOException{
        
        String valor="";
        Properties pr = new Properties();
        pr.load(new FileReader(Principal.getCfgPath()));
        valor = pr.getProperty(clave);
        
        return valor;
    }
    
}
