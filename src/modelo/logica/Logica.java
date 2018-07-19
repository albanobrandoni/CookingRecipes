
package modelo.logica;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.IngredienteReceta;
import modelo.Ingredientes;
import modelo.Recetas;
import modelo.Tipos;


public class Logica {
    
    
    
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
            
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
    
    
    public static class ImageFondo extends JPanel{
    private Image fondo=null;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),null);      
       repaint();
    }
    public void setImage(String image){
        if (image!=null) {
            fondo=new ImageIcon(getClass().getResource(image)).getImage();
        }
    }            
}
   
    public static void cargarTablaPrincipal (JTable  tabla, List<Recetas> lista){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tabla.getModel();
        Object [] fila = new Object[4];
        
        for(Recetas r: lista){
            fila[0] = false;
            fila[1] = r.getTitulo();            
            fila[2] = r.getTipo();       
            fila[3] = r.getFecha();
            
            dtm.addRow(fila);
        }
        tabla.setModel(dtm);
        tabla.getRowSorter().toggleSortOrder(1 );
    }
    
    public static void limpiarTablaPrincipal(JTable tabla){
        ModeloTabla miModelo = new ModeloTabla();
        miModelo.modeloPrincipal(tabla);                
    }
    
    public static void cargarTablaCantidadIngredientes (JTable  tabla, List<Ingredientes> lista){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tabla.getModel();
        Object [] fila = new Object[5];
        
        for(Ingredientes i: lista){
            fila[0] = false;
            fila[1] = i.getNombre();            
            fila[2] = null;       
            fila[3] = null;          
            
            dtm.addRow(fila);
        }
        tabla.setModel(dtm);
    }
    
    public static void limpiarTablaCantidadIngredientes(JTable tabla){
        ModeloTabla miModelo = new ModeloTabla();
        miModelo.modeloCantidadIngredientes(tabla);
    }
    
    public static void remarcar(JTable tabla){
        
        tabla.getSelectionModel().clearSelection();
        int filastotales = tabla.getRowCount();
            for(int i =0; i< filastotales; i++){                
                if (tabla.getValueAt(i, 0).equals(true)){                    
                    tabla.getSelectionModel().addSelectionInterval(i, i);                    
                }                     
            }
    }
    
    public static void cargarTablaEditarIngredientes (JTable  tabla, List<IngredienteReceta> lista, List<Ingredientes> listaIngredientes){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tabla.getModel();
        Object [] fila = new Object[2];
        
        for(IngredienteReceta ir: lista){
            fila[0] = Logica.obtenerIngrediente(listaIngredientes, ir.getIdIngrediente());
            fila[1] = ir.getCantidad();            
            
            dtm.addRow(fila);
        }
        tabla.setModel(dtm);
    }
    
    public static void agregarIngredienteTablaEditar(JTable tabla, String nombreIngrediente){
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tabla.getModel();
        Object [] fila = new Object[2];
        
        fila[0] = nombreIngrediente;
        fila[1] = "";
        
        dtm.addRow(fila);
        tabla.setModel(dtm);
    }
    
    public static boolean estaEnLaTabla (JTable tabla, String nombreElemento, int columna){
        boolean respuesta = false;
        
        for(int i = 0 ; i < tabla.getRowCount() ; i++){
            if(tabla.getValueAt(i, columna).equals(nombreElemento))respuesta = true;
        }
        
        return respuesta;
    }
    
    public static void limpiarTablaEditarIngredientes(JTable tabla){
        ModeloTabla miModelo = new ModeloTabla();
        miModelo.modeloEditarIngredientes(tabla);                
    }
    
    public static void cargarComboIngredientes(JComboBox combo, List<Ingredientes> lista){
        for(Ingredientes i: lista){
            combo.addItem(i.getNombre());
        }
    }
    
    public static void cargarComboTipos(JComboBox combo, List<Tipos> lista){
        combo.removeAllItems();
        String[] tiposNativos = {"Carnes", "Ensaladas", "Guarnicion", "Pastas","Pescados", "Pizzas", "Postres", "Snacks", "Tragos"};
        for(String s: tiposNativos){
            combo.addItem(s);
        }
        for(Tipos t: lista){
            combo.addItem("--"+t.getNombre()+"--");
        }
    }
    
    
    public static void transladar(JTable origen, JTable destino){
            DefaultTableModel dtmo, dtmd;
                      
            dtmd = (DefaultTableModel) destino.getModel();
            
            int filastotales = origen.getRowCount();
            
            for (int i=0; i< filastotales; i++){
            
            if(origen.getValueAt(i, 0).equals(true)){
                if ( origen.getValueAt(i,3) == null){
                    JOptionPane.showMessageDialog(null,"Los Campos seleccionados no pueden quedar vacíos");
                    break;
                } else {                    
                    
                String peso = (String) origen.getValueAt(i,2);
                String unidad = String.valueOf(origen.getValueAt(i,3));
                if(peso==null)peso="";
                String cantidad = peso+" "+unidad;
                Object[] fila = new Object[2];
                fila[0] = origen.getValueAt(i, 1) ;
                fila[1] = cantidad;
                
                dtmd.addRow(fila);                                   
                }
            }
                                                          
            }
             destino.setModel(dtmd);                       
        }  
    public static Long obtenerIdIngrediente(List<Ingredientes> li, String nombreIngrediente){
        Long id = null;
        for(Ingredientes i: li){
            if(i.getNombre().equals(nombreIngrediente)){
                id = i.getId();
                break;
            }            
        }
        return id;
    }
    
    public static Recetas obtenerReceta(List<Recetas> lr , String titulo){
            
        Recetas receta = null;
        for(Recetas r: lr){
            if(r.getTitulo() == titulo){
                receta = r;
                break;
            }
        }
        return receta;
    }
    public static String obtenerIngrediente(List<Ingredientes> li , Long id){
        String ingrediente = "";    
        for(Ingredientes i : li){
            if(i.getId().equals(id)){
                ingrediente = i.getNombre();
                break;
            }
        }
        return ingrediente;    
    }
    
   
    public static ImageIcon obtenerImagen(Blob image) throws SQLException{
               ImageIcon icono = null;
               
               int bloblength = (int) image.length();
               byte[] blobasbytes = image.getBytes(1, bloblength);
               icono = new ImageIcon(blobasbytes);
                              
               return icono;            
    }
    
    public static Image imageIconToImage(ImageIcon imageIcon){
        if(imageIcon!=null){
        Image imgReturn=(Image)imageIcon.getImage();
        return imgReturn;
        }else{
            
            return null;
        }
    }
    
    public static void copiarArchivo(String urlOrigen, String urlDestino) throws IOException{
        
        Path rutaOrigen = Paths.get(urlOrigen);
        Path rutaDestino = Paths.get(urlDestino);
        
        CopyOption[] opciones = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(rutaOrigen, rutaDestino, opciones);
    }
    
    public static void copiarStreams(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
}
                    
}