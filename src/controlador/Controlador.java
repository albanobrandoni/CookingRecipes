
package controlador;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.IngredienteReceta;
import modelo.Ingredientes;
import modelo.Pasos;
import modelo.Recetas;
import vista.reportes.ReporteLibroRecetas;
import modelo.Tipos;
import modelo.logica.Logica;
import modelo.Usuarios;
import modelo.dao.DAOException;
import modelo.dao.sqlitemysql.Conexion;
import modelo.logica.Config;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.formularios.VentanaPrincipal;
import vista.reportes.ReporteIndiceReceta;

public class Controlador implements ActionListener, KeyListener, WindowListener, ListSelectionListener, MouseListener{
    
    private VentanaPrincipal principalVista;
        
    public Controlador(Object vista) throws SQLException {
                                                 
        this.principalVista = (VentanaPrincipal)vista;                                            
    }
                                             
    @Override
    public void actionPerformed(ActionEvent e){
                               
        try {
                        
            Conexion cn = new Conexion();
                                                                    
            String accion = e.getActionCommand();
            
            switch (accion){   
                
                case "ACEPTAR_CREDENCIAL":
                    
                    String usuarioAdmin = Logica.sha1(new String(principalVista.getMiCredencialAdmin().getTxtUsuario().getText()));
                    String contraseñaAdmin = Logica.sha1(new String(principalVista.getMiCredencialAdmin().getTxtContraseña().getPassword()));
                    
                    if(Config.getConfig("userAdmin").equals(usuarioAdmin) && Config.getConfig("passAdmin").equals(contraseñaAdmin)){ 
                        
                        principalVista.getMiCredencialAdmin().cerrarCredencialAdmin();
                        principalVista.getMiCredencialAdmin().limpiarCredencialAdmin();
                        principalVista.abrirPreferencias();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos!");
                    }
                break;
                
                case "OPCION_MYSQL":
                    
                    principalVista.getMiPreferencias().activarCredencialesMysql();
                    principalVista.getMiPreferencias().desactivarBotonRestaurar();
                    
                break;
                
                case "OPCION_SQLITE":  
                    
                    principalVista.getMiPreferencias().desactivarCredencialesMysql();
                    principalVista.getMiPreferencias().activarBotonRestaurar();
                    
                break;                    
                
                case "RESTAURAR":
                    
                    if(Config.getConfig("dataBase").equals("1")){
                        JOptionPane.showMessageDialog(null, "La restauración solo esta disponible para SQLite");
                    } else {
                            if(JOptionPane.showConfirmDialog(null, "Si restaura la configuración de fabrica se perderán todos los datos!\n¿Desea restaurar de todos modos?", "Restaurar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                String pass = Logica.sha1(new String(JOptionPane.showInputDialog("Ingrese la contraseña administrador!")));
                                if(pass.equals(Config.getConfig("passAdmin"))){
                                                                       
                                    File archivoProperties = new File("cfg.properties");      
                                    BufferedWriter out = new BufferedWriter(new FileWriter(archivoProperties, true));
                                    out.write("dataBase=0\n"                                   
                                    +"userAdmin=d033e22ae348aeb5660fc2140aec35850c4da997\n"//admin sha1
                                    +"passAdmin=a2ffe4fca984196dbb5061a26b7f1fc6561eec42\n"//cookingadmin sha1
                                    +"userMysql=\n"
                                    +"passMysql=\n"        
                                    +"hostMysql=\n"
                                    +"databaseMysql=\n");        
                                    out.close();
                                    
                                    InputStream is = getClass().getResourceAsStream("/modelo/recursos/databases/cook.db");
                                    File sqliteDestino = new File("cook.db");        
                                    FileOutputStream fos = new FileOutputStream(sqliteDestino);          
                                    Logica.copiarStreams(is, fos);
                                    fos.close();
                                    is.close();
                                    
                                    File CSImages = new File("CSImages");
                                    if(CSImages.exists() && CSImages.isDirectory()){
                                        for(File f : CSImages.listFiles()){
                                            f.delete();
                                        }
                                        CSImages.delete();
                                    }
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Contraseña administrador incorrecta!");
                                }
                            }
                    }
                break;
                
                case "CONFIRMAR_PREFERENCIAS":
                    
                    JOptionPane.showMessageDialog(null, "Se cerrará la sesión actual!");
                    principalVista.getMiPreferencias().cerrarPreferencias();
                    principalVista.cerrarSesion();
                    
                    if(Principal.getUsuario().getNombre() == null){
                       
                        if(principalVista.getMiPreferencias().getOpcionMysql().isSelected()){
                        
                            Config.setConfig("dataBase", "1");
                            if(Config.getConfig("dataBase").equals("1"))JOptionPane.showMessageDialog(null, "La base de datos cambio de SQLite a MySql!");
                            Config.setConfig("userMysql", principalVista.getMiPreferencias().getTxtUsername().getText());
                            Config.setConfig("passMysql", principalVista.getMiPreferencias().getTxtPassword().getText());
                            Config.setConfig("hostMysql", principalVista.getMiPreferencias().getTxtHost().getText());
                            Config.setConfig("databaseMysql", principalVista.getMiPreferencias().getTxtDatabase().getText());
                                                
                        }
                    
                        if(principalVista.getMiPreferencias().getOpcionSqlite().isSelected()){
                            Config.setConfig("dataBase", "0");
                            if(Config.getConfig("dataBase").equals("0"))JOptionPane.showMessageDialog(null, "La base de datos cambio de MySql a SQLite!");
                        }
                                                                                                   
                        principalVista.abrirLogin();
                    
                    } 
                    
                break;
                
                case "OPCION_ITEM_PREFERENCIAS":        
                
                    principalVista.getMiCredencialAdmin().abrirCredencialAdmin();                    
                
                break;
                
                case "REGISTRAR":
                    
                    principalVista.cerrarLogin(principalVista.getMiLogin());
                    principalVista.abrirRegistrar();
                    
                break;                
                
                case "INICIAR":
                    
                    String usuario, contraseña;
                    usuario = principalVista.getMiLogin().getTxtUsuario().getText();
                    contraseña = Logica.sha1(new String(principalVista.getMiLogin().getTxtPass().getPassword()));                    
                                        
                    Usuarios uInsertado = new Usuarios(usuario, contraseña, "default");
                    
                    Usuarios uDevuelto = cn.getUsuariosDao().obtener(uInsertado);
                                  
                    if(uDevuelto != null){
                        principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR)); 
                        
                        Principal.setUsuario(uDevuelto);                        
                        JOptionPane.showMessageDialog(null,"Bienvenido "+uDevuelto.getNombre()+"!!!");
                        principalVista.getMiNuevaReceta().getTxtAutor().setText(Principal.getUsuario().getNombre());
                                               
                        principalVista.iniciarSesion();
                                               
                        principalVista.cerrarLogin(principalVista.getMiLogin());
                        principalVista.getItemCerrarSesion().setEnabled(true);
                        principalVista.getItemIniciarSesion().setEnabled(false);                                                                                                                                     
                                               
                        List <Recetas> lr = cn.getRecetasDao().obtenerTodos(Principal.getUsuario().getId());                         
                        List <Ingredientes> li = cn.getIngredientesDao().obtenerTodos(Principal.getUsuario().getId());
                        List <Tipos> lt = cn.getTipoDao().obtenerTodos(Principal.getUsuario().getId());
                        
                        principalVista.setMiListaRecetas(lr);
                        principalVista.setMiListaIngredientes(li);
                        principalVista.setMiListaTipos(lt);
                                                
                        Logica.cargarComboTipos(principalVista.getMiNuevaReceta().getTipo(), principalVista.getMiListaTipos());
                        Logica.cargarComboIngredientes(principalVista.getMiNuevoIngrediente().getComboAlimentos(), principalVista.getMiListaIngredientes());
                        Logica.limpiarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
                        Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(), principalVista.getMiListaIngredientes());
                        Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                        Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                        
                        principalVista.getLblEncontradas().setText(String.valueOf(principalVista.getTablaPrincipal().getRowCount()));
                                                                                                
                        
                        principalVista.limpiarTxtAlimentos();
                        principalVista.limpiarVerReceta();
                        principalVista.getPanelPrincipal().setVisible(true);  
                        principalVista.limpiarNuevaReceta();
                        
                        cn.cerrarConexion(cn);
                        
                        principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));
                    } else{     
                        JOptionPane.showMessageDialog(null, "El usuario no es correcto");
                      }
                    
                    principalVista.limpiarLogin(principalVista.getMiLogin());
                    
                break;
                
                case "REGISTRARME":
                    
                    if( (principalVista.getMiRegistrar().getTxtUsuario().getText().length()>0) && (principalVista.getMiRegistrar().getTxtContraseña().getPassword().length>0)){
                        usuario = principalVista.getMiRegistrar().getTxtUsuario().getText();
                        contraseña = new String(principalVista.getMiRegistrar().getTxtContraseña().getPassword());
                        uInsertado = new Usuarios(usuario, contraseña, "default");
                        cn.getUsuariosDao().insertar(uInsertado);
                        principalVista.limpiarRegistrar(principalVista.getMiRegistrar());
                        principalVista.cerrarRegistrar(principalVista.getMiRegistrar());
                        principalVista.abrirLogin();
                        cn.cerrarConexion(cn);
                    } else {
                            JOptionPane.showMessageDialog(null, "No puede dejar campos vacíos");
                            principalVista.limpiarRegistrar(principalVista.getMiRegistrar());                       
                      }
                    
                break;     
                
                case "CERRAR":                    
                    
                    principalVista.cerrarSesion();                    
                    
                break;
                
                case "ABRIR":
                    
                    principalVista.abrirLogin();
                    
                break;
                
                case "RECETA":                    
                    
                    principalVista.abrirNuevaReceta();
                    principalVista.getMiNuevaReceta().getTxtTitulo().requestFocus();
                    
                break;
                
                case "ALIMENTO":
                    
                    principalVista.abrirListaAlimentos();
                    
                break;
                
                case "AGREGAR_ALIMENTO":
                    
                    if(!principalVista.getMiNuevoIngrediente().getTxtNuevoAlimento().getText().isEmpty()){
                      Ingredientes ing = new Ingredientes(principalVista.getMiNuevoIngrediente().getTxtNuevoAlimento().getText(), Principal.getUsuario().getId());                                           
                      cn.getIngredientesDao().insertar(ing);
                      principalVista.limpiarTxtAlimentos();
                      principalVista.limpiarComboAlimentos();
                      List <Ingredientes> li = cn.getIngredientesDao().obtenerTodos(Principal.getUsuario().getId());
                      principalVista.setMiListaIngredientes(li);
                      Logica.cargarComboIngredientes(principalVista.getMiNuevoIngrediente().getComboAlimentos(), principalVista.getMiListaIngredientes());
                      Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(), li);
                      principalVista.getMiNuevoIngrediente().getComboAlimentos().updateUI();                      
                      cn.cerrarConexion(cn);
                                            
                    } else {
                        JOptionPane.showMessageDialog(null, "No dejar el campo vacío!!");
                      }                                                                  
                    
                break;
                
                case "ELIMINAR_ALIMENTO":
                    
                    Ingredientes ing = new Ingredientes((String) principalVista.getMiNuevoIngrediente().getComboAlimentos().getSelectedItem(), Principal.getUsuario().getId());
                    cn.getIngredientesDao().eliminar(ing);
                    principalVista.limpiarComboAlimentos();
                    List <Ingredientes> li = cn.getIngredientesDao().obtenerTodos(Principal.getUsuario().getId());                    
                    principalVista.setMiListaIngredientes(li);
                    Logica.cargarComboIngredientes(principalVista.getMiNuevoIngrediente().getComboAlimentos(), principalVista.getMiListaIngredientes());
                    principalVista.getMiNuevoIngrediente().getComboAlimentos().updateUI();
                    cn.cerrarConexion(cn);
                    
                break; 
                
                case "CANTIDAD":
                    
                    Logica.limpiarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
                    Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(), principalVista.getMiListaIngredientes());
                    principalVista.abrirCantidad();
                    
                break;                
                
                case "INCORPORAR":
                    
                    if(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento().isEditing()){
                         principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento().getCellEditor().stopCellEditing();
                    };
                    
                    List <Ingredientes> li2 = cn.getIngredientesDao().obtenerTodos(Principal.getUsuario().getId());
                    principalVista.setMiListaIngredientes(li2);
                    Logica.transladar(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(), principalVista.getMiNuevaReceta().getTablaAlimentos());
                    int cantidadIngredientes = principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount();
                    principalVista.getMiNuevaReceta().getLblCantidadIngredientes().setText(String.valueOf(cantidadIngredientes));
                    Logica.limpiarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
                    Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(),principalVista.getMiListaIngredientes());
                    
                    principalVista.cerrarCantidad();
                    
                    cn.cerrarConexion(cn);
                    
                break;
                
                case "CANCELAR_CANTIDAD":   
                    
                    Logica.limpiarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
                    Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(),principalVista.getMiListaIngredientes());
                    cn.cerrarConexion(cn);
                    principalVista.cerrarCantidad();                    
                    
                break;  
                
                case "QUITAR_INGREDIENTE":                    
                    
                    int fila = principalVista.getMiNuevaReceta().getTablaAlimentos().getSelectedRow();                    
                    if(fila == -1){
                        JOptionPane.showMessageDialog(null, "Seleccione el ingrediente que desea quitar!");
                    } else {
                            DefaultTableModel modelo = (DefaultTableModel) principalVista.getMiNuevaReceta().getTablaAlimentos().getModel();
                            modelo.removeRow(fila);
                            int cantidadIngrediente = principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount();
                            principalVista.getMiNuevaReceta().getLblCantidadIngredientes().setText(String.valueOf(cantidadIngrediente));
                    
                      }
                    
                break;
                
                case "AÑADIR_PASO":
                                        
                    principalVista.getMiNuevaReceta().getBtnEnviarAprocedimiento().setEnabled(true);
                    principalVista.getMiNuevaReceta().getBtnCancelarPaso().setEnabled(true);
                    principalVista.getMiNuevaReceta().setPaso(principalVista.getMiNuevaReceta().getPaso()+1);
                    principalVista.getMiNuevaReceta().getLblPaso().setText(String.valueOf(principalVista.getMiNuevaReceta().getPaso()));
                    principalVista.getMiNuevaReceta().getTxtPaso().setEnabled(true);
                    principalVista.getMiNuevaReceta().getBtnAñadirPaso().setEnabled(false);
                    principalVista.getMiNuevaReceta().getTxtPaso().requestFocus();                   
                    
                break;
                
                case "CANCELAR_PASO":
                                                            
                    principalVista.getMiNuevaReceta().getBtnAñadirPaso().setEnabled(true);
                    principalVista.getMiNuevaReceta().getBtnEnviarAprocedimiento().setEnabled(false);
                    principalVista.getMiNuevaReceta().getBtnCancelarPaso().setEnabled(false);
                    principalVista.getMiNuevaReceta().getTxtPaso().setText("");
                    principalVista.getMiNuevaReceta().getTxtPaso().setEnabled(false);
                    principalVista.getMiNuevaReceta().setPaso(principalVista.getMiNuevaReceta().getPaso()-1);
                    principalVista.getMiNuevaReceta().getLblPaso().setText("");
                    
                break;                
                
                case "ENVIAR_PASO_A_PROCEDIMIENTO":
                                                                                   
                    String descripcion = principalVista.getMiNuevaReceta().getTxtPaso().getText();
                    int orden = principalVista.getMiNuevaReceta().getPaso();
                    
                    Pasos paso = new Pasos(descripcion, orden);
                                        
                    principalVista.getMiNuevaReceta().getLp().add(paso);
                    
                    String procedimiento = principalVista.getMiNuevaReceta().getTxtProcedimiento().getText();
                    String numeroPaso = "Paso n°: "+principalVista.getMiNuevaReceta().getLblPaso().getText()+"\n";
                    principalVista.getMiNuevaReceta().getBtnEnviarAprocedimiento().setEnabled(false);
                    principalVista.getMiNuevaReceta().getBtnAñadirPaso().setEnabled(true);
                    principalVista.getMiNuevaReceta().getBtnCancelarPaso().setEnabled(false);
                    principalVista.getMiNuevaReceta().getBtnEliminarUltimoPaso().setEnabled(true);
                    
                    for(Pasos p: principalVista.getMiNuevaReceta().getLp()){
                        principalVista.getMiNuevaReceta().getTxtProcedimiento().setText(procedimiento+numeroPaso+p.getDescripcion()+"\n\n");
                    }
                    
                    principalVista.getMiNuevaReceta().getTxtPaso().setText("");
                    principalVista.getMiNuevaReceta().getTxtPaso().setEnabled(false);
                    principalVista.getMiNuevaReceta().getLblPaso().setText("");
                                        
                break;
                
                case "ELIMINAR_ULTIMO_PASO":                                                                               
                    
                    String procedimientoB = "";                    
                    int longitud = principalVista.getMiNuevaReceta().getLp().size();
                    
                    if(longitud == 1 ){
                        principalVista.getMiNuevaReceta().getLp().clear();
                        principalVista.getMiNuevaReceta().getTxtProcedimiento().setText("");
                        principalVista.getMiNuevaReceta().setPaso(principalVista.getMiNuevaReceta().getPaso()-1);
                    }
                    
                    if(longitud > 1){
                        principalVista.getMiNuevaReceta().getLp().remove(longitud-1);
                        for(Pasos p: principalVista.getMiNuevaReceta().getLp()){                        
                            principalVista.getMiNuevaReceta().getTxtProcedimiento().setText(procedimientoB+="Paso n°: "+String.valueOf(p.getOrden())+"\n"+p.getDescripcion()+"\n\n");                        
                        }
                        principalVista.getMiNuevaReceta().setPaso(principalVista.getMiNuevaReceta().getPaso()-1);
                    }                                      
                    
                    if(principalVista.getMiNuevaReceta().getTxtProcedimiento().getText().isEmpty()){
                        principalVista.getMiNuevaReceta().getBtnEliminarUltimoPaso().setEnabled(false);
                    }
                                                                                                                                                              
                break;    
                
                case "CANCELAR_RECETA":
                    
                    principalVista.limpiarNuevaReceta();
                    
                break;
                
                case "AÑADIR_FOTO":
                    
                    String nombreArchivo, ruta, nuevaRuta;                  
                    File archivo;                    
                    ImageIcon imagenBD;
                    FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, BMP, PNG", "jpg", "gif", "png");
                    JFileChooser abrirArchivo = new JFileChooser();
                    abrirArchivo.setFileFilter(filtro);
                    
                    int abrir = abrirArchivo.showOpenDialog(principalVista.getMiNuevaReceta());
                    if(abrir == JFileChooser.APPROVE_OPTION){
                        
                        principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR));
                        archivo = abrirArchivo.getSelectedFile();
                        ruta = archivo.getAbsolutePath();                        
                        nombreArchivo = archivo.getName();
                        String extensionArchivo = nombreArchivo.substring(nombreArchivo.length()-4, nombreArchivo.length());                        
                        principalVista.getMiNuevaReceta().setUrl(ruta);
                        principalVista.getMiNuevaReceta().setExtensionImagen(extensionArchivo);
                                                                        
                        Image icono = ImageIO.read(abrirArchivo.getSelectedFile()).getScaledInstance(principalVista.getMiNuevaReceta().getLblImagen().getWidth(),principalVista.getMiNuevaReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT);
                        
                        principalVista.getMiNuevaReceta().getLblImagen().setIcon(new ImageIcon(icono));
                        
                        principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));
                    }
                                        
                break;
                
                case "QUITAR_FOTO":
                    
                    ImageIcon imagen2 = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
                    Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(principalVista.getMiNuevaReceta().getLblImagen().getWidth(),principalVista.getMiNuevaReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
                    principalVista.getMiNuevaReceta().getLblImagen().setIcon(icono2);
                    principalVista.getMiNuevaReceta().setUrl("");    
                    
                break;
                
                case "GUARDAR_RECETA":
                                        
                    if(principalVista.getMiNuevaReceta().getTxtTitulo().getText().isEmpty() 
                            || principalVista.getMiNuevaReceta().getTxtProcedimiento().getText().isEmpty()
                            || principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount() > 14
                            || principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount() == 0){
                        
                        if(principalVista.getMiNuevaReceta().getTxtTitulo().getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "No puede dejar el campo <Título> vacío!!");
                            principalVista.getMiNuevaReceta().getTxtTitulo().requestFocus();
                        }
                        if(principalVista.getMiNuevaReceta().getTxtProcedimiento().getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Debe haber al menos 1 paso en el procedimiento!!");
                        }
                        if(principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount() == 0){
                            JOptionPane.showMessageDialog(null, "Debe agregar al menos un alimento a la receta!!");
                        }
                        if(principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount() > 14){
                            JOptionPane.showMessageDialog(null, "Se recomienda un máximo de 14 ingredientes por receta!");
                        }
                                                                        
                    } else {
                        
                        try{
                        principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR));    
                            
                        cn.getConn().setAutoCommit(false);
                        
                        Long ultimoId;
                        String titulo = principalVista.getMiNuevaReceta().getTxtTitulo().getText();
                        String autor = principalVista.getMiNuevaReceta().getTxtAutor().getText();
                        String consejo = principalVista.getMiNuevaReceta().getTxtConsejo().getText();
                        
                        int comensales = (int) principalVista.getMiNuevaReceta().getComensales().getValue();
                        
                        int minutosCoccion = (int) principalVista.getMiNuevaReceta().getCoccionMinuto().getValue();
                        int minutosPreparacion = (int) principalVista.getMiNuevaReceta().getPreparacionMinuto().getValue();
                        
                        String tiempo;
                        String tiempoPreparacion;
                       
                        if(minutosCoccion < 10){                            
                            tiempo = String.valueOf(principalVista.getMiNuevaReceta().getCoccionHora().getValue())+":0"+principalVista.getMiNuevaReceta().getCoccionMinuto().getValue(); 
                        } else {
                            tiempo = String.valueOf(principalVista.getMiNuevaReceta().getCoccionHora().getValue())+":"+minutosCoccion;
                          };
                          
                        if(minutosPreparacion < 10){                            
                            tiempoPreparacion = String.valueOf(principalVista.getMiNuevaReceta().getPreparacionHora().getValue())+":0"+principalVista.getMiNuevaReceta().getPreparacionMinuto().getValue();
                        } else {
                            tiempoPreparacion = String.valueOf(principalVista.getMiNuevaReceta().getPreparacionHora().getValue())+":"+minutosPreparacion;
                          };
                         
                        
                        String imagen = "";
                        File directorio = null;
                        if(Config.getConfig("dataBase").equals("0")){

                            directorio = new File("CSImages");
                            if(!directorio.exists())directorio.mkdir();
                            if(!principalVista.getMiNuevaReceta().getExtensionImagen().equals("")){
                                imagen = String.valueOf(directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());
                            }    
                        }
                        if(Config.getConfig("dataBase").equals("1")){

                            directorio = new File("CMImages");
                            if(!directorio.exists())directorio.mkdir();
                            if(!principalVista.getMiNuevaReceta().getExtensionImagen().equals("")){
                                imagen = String.valueOf(directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());
                            }    
                        }
                        
                                                
                        String stringTemporal = (String) principalVista.getMiNuevaReceta().getTipo().getSelectedItem();
                        String tipo = stringTemporal.replaceAll("-", "");
                        Recetas receta = new Recetas(autor,titulo, consejo, imagen, comensales, tiempo, tiempoPreparacion, tipo);
                       
                        ultimoId = cn.getRecetasDao().insertarId(receta);
                                                
                        if(!imagen.equals("")){
                            Logica.copiarArchivo(principalVista.getMiNuevaReceta().getUrl(), directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());                                                                                                
                        }
                        
                        principalVista.getMiNuevaReceta().setExtensionImagen("");
                        int cantidad = principalVista.getMiNuevaReceta().getTablaAlimentos().getRowCount();
                        Long idIngrediente = null;
                        
                        List<IngredienteReceta> lir = new ArrayList<>();
                        
                        
                        for(int i = 0 ; i < cantidad ; i++ ){
                            
                            String nombreAlimento = String.valueOf(principalVista.getMiNuevaReceta().getTablaAlimentos().getValueAt(i, 0));                            
                            String cantidadAlimento = String.valueOf(principalVista.getMiNuevaReceta().getTablaAlimentos().getValueAt(i, 1));
                                                        
                            IngredienteReceta ir = new IngredienteReceta(Logica.obtenerIdIngrediente(principalVista.getMiListaIngredientes(), nombreAlimento), ultimoId, cantidadAlimento);
                           
                            lir.add(ir);
                        }                       
                        
                        for(IngredienteReceta i: lir){
                            cn.getIngredienteRecetaDao().insertar(i);
                        }
                        
                        for(Pasos p: principalVista.getMiNuevaReceta().getLp()){
                            p.setIdRecetas(ultimoId);
                            cn.getPasosDao().insertar(p);
                        }
                                                                        
                        List<Recetas> lr = cn.getRecetasDao().obtenerTodos(Principal.getUsuario().getId());
                        principalVista.setMiListaRecetas(lr);
                        
                        Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                        Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                        principalVista.getLblEncontradas().setText(String.valueOf(principalVista.getTablaPrincipal().getRowCount()));
                        principalVista.getMiNuevaReceta().getLp().clear();
                        cn.getConn().commit();
                                                                       
                        }catch(SQLException ex){
                            cn.getConn().rollback();
                            JOptionPane.showMessageDialog(null, "La receta no se ha podido guardar!!");
                            
                            ex.printStackTrace();
                        }finally{
                            principalVista.limpiarNuevaReceta();                           
                            cn.cerrarConexion(cn); 
                            principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));
                        }
                        
                      }
                                         
                break;
                
                case "EDITAR_RECETA":
                    
                    if(principalVista.getTablaPrincipal().getSelectedRow() == -1){
                        JOptionPane.showMessageDialog(null, "Seleccione una receta en la tabla!!");                        
                    } else {
                            
                            principalVista.getMiEditarReceta().getComboIngredientes().removeAllItems();
                            Logica.limpiarTablaEditarIngredientes(principalVista.getMiEditarReceta().getTablaIngredientes());
                            Font fuente = new Font("Noto Sans", Font.BOLD, 14);
                                                        
                            principalVista.setMiRecetaSeleccionada(String.valueOf(principalVista.getTablaPrincipal().getValueAt(principalVista.getTablaPrincipal().getSelectedRow(), 1))); 
                            
                            Recetas r = Logica.obtenerReceta(principalVista.getMiListaRecetas(), principalVista.getMiRecetaSeleccionada());
                            List<Pasos> lp = cn.getPasosDao().obtenerTodos(r.getId());
                            principalVista.setMiListaPasos(lp);
                             
                            
                            for(Ingredientes i: principalVista.getMiListaIngredientes()){
                                principalVista.getMiEditarReceta().getComboIngredientes().addItem(i.getNombre());
                            }
                            
                            List<IngredienteReceta> lir = cn.getIngredienteRecetaDao().obtenerTodos(r.getId());
                            
                            if(r.getImagen().equals("")){                                
                                ImageIcon imagen = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
                                Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(principalVista.getMiEditarReceta().getLblImagen().getWidth(),principalVista.getMiEditarReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
                                principalVista.getMiEditarReceta().getLblImagen().setIcon(icono);                            
                            } else {                            
                                    ImageIcon imagenB = new ImageIcon (r.getImagen());
                                    Icon iconoB = new ImageIcon(imagenB.getImage().getScaledInstance(principalVista.getMiEditarReceta().getLblImagen().getWidth(),principalVista.getMiEditarReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
                                    principalVista.getMiEditarReceta().getLblImagen().setIcon(iconoB); 
                              }
                            
                            Logica.cargarTablaEditarIngredientes(principalVista.getMiEditarReceta().getTablaIngredientes(), lir, principalVista.getMiListaIngredientes());
                            
                            principalVista.getMiEditarReceta().getTxtTitulo().setText(r.getTitulo());
                            principalVista.getMiEditarReceta().getTxtTitulo().setCaretPosition(0);
                            principalVista.getMiEditarReceta().getTxtAutor().setText(r.getAutor());
                            principalVista.getMiEditarReceta().getTxtAutor().setCaretPosition(0);
                            principalVista.getMiEditarReceta().getTxtTipo().setText(r.getTipo());
                            principalVista.getMiEditarReceta().getTxtTipo().setCaretPosition(0);
                            principalVista.getMiEditarReceta().getTxtComensales().setText(String.valueOf(r.getComensales()));
                            principalVista.getMiEditarReceta().getTxtPreparacion().setText(r.getTiempoPreparacion());
                            principalVista.getMiEditarReceta().getTxtCoccion().setText(r.getTiempo());
                            principalVista.getMiEditarReceta().getTxtConsejo().setText(r.getConsejo());
                                                                                                                
                            llenarPanelProcedimiento(principalVista.getMiListaPasos(), principalVista.getMiEditarReceta().getPanelProcedimiento());
                                                                                                                                          
                            principalVista.getMiEditarReceta().getPanelProcedimiento().updateUI();
                                                                                     
                            principalVista.getMiEditarReceta().setVisible(true);
                            
                            cn.cerrarConexion(cn);
                    }                    
                    
                break; 
                
                case "AGREGAR_PASO":
                    
                    Pasos pa = new Pasos(principalVista.getMiEditarReceta().getTxtPaso().getText(), principalVista.getMiListaPasos().size() + 1);
                    principalVista.getMiListaPasos().add(pa);
                    llenarPanelProcedimiento(principalVista.getMiListaPasos(), principalVista.getMiEditarReceta().getPanelProcedimiento());
                    principalVista.getMiEditarReceta().getTxtPaso().setText("");
                    
                break;
                
                case "AÑADIR_INGREDIENTE":
                    
                    String ingrediente = String.valueOf(principalVista.getMiEditarReceta().getComboIngredientes().getSelectedItem());
                    
                    if(Logica.estaEnLaTabla(principalVista.getMiEditarReceta().getTablaIngredientes(), ingrediente, 0)){
                        JOptionPane.showMessageDialog(null, "El ingrediente ya se encuentra en la tabla");
                    } else {
                            int i = JOptionPane.showConfirmDialog(null, "¿Desea incorporar "+ingrediente+" a la tabla?", "Agregando ingrediente", JOptionPane.YES_NO_OPTION);
                            if( i == JOptionPane.YES_OPTION){
                                Logica.agregarIngredienteTablaEditar(principalVista.getMiEditarReceta().getTablaIngredientes(), ingrediente);                    
                            }
                      }
                    
                break;
                
                case "QUITAR":
                    
                    int filaSeleccionada = principalVista.getMiEditarReceta().getTablaIngredientes().getSelectedRow();                    
                    if(filaSeleccionada == -1){
                        JOptionPane.showMessageDialog(null, "Seleccione el ingrediente que desea quitar!");
                    } else {
                            DefaultTableModel modelo = (DefaultTableModel) principalVista.getMiEditarReceta().getTablaIngredientes().getModel();
                            modelo.removeRow(filaSeleccionada);
                      }
                    
                break;
                
                case "ACTUALIZAR":
                    
                    if(principalVista.getMiEditarReceta().getTablaIngredientes().isEditing()){
                        principalVista.getMiEditarReceta().getTablaIngredientes().getCellEditor().stopCellEditing();
                    }
                    
                    String titulo = principalVista.getMiEditarReceta().getTxtTitulo().getText();
                    String autor = principalVista.getMiEditarReceta().getTxtAutor().getText();
                    String tipo = principalVista.getMiEditarReceta().getTxtTipo().getText();
                    String preparacion = principalVista.getMiEditarReceta().getTxtPreparacion().getText();
                    String coccion = principalVista.getMiEditarReceta().getTxtCoccion().getText();
                    String consejo = principalVista.getMiEditarReceta().getTxtConsejo().getText();
                    String imagenActualizada = "";
                    int comensales = Integer.parseInt(principalVista.getMiEditarReceta().getTxtComensales().getText());
                                                            
                    Recetas recetaActual = Logica.obtenerReceta(principalVista.getMiListaRecetas(), principalVista.getMiRecetaSeleccionada());
                                                              
                    File fichero = new File(recetaActual.getImagen());
                    fichero.delete();
                    
                    File directorio = null;
                    if(Config.getConfig("dataBase").equals("0")){
                        directorio = new File("CSImages");
                        if(!directorio.exists())directorio.mkdir();
                        if(!principalVista.getMiNuevaReceta().getExtensionImagen().equals("")){
                                imagenActualizada = String.valueOf(directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());
                        }
                    }
                    if(Config.getConfig("dataBase").equals("1")){
                        directorio = new File("CMImages");
                        if(!directorio.exists())directorio.mkdir();                            
                        if(!principalVista.getMiNuevaReceta().getExtensionImagen().equals("")){
                                imagenActualizada = String.valueOf(directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());
                        }
                    }
                    
                    
                    if(!imagenActualizada.equals("")){
                            Logica.copiarArchivo(principalVista.getMiNuevaReceta().getUrl(), directorio.getAbsolutePath()+File.separator+titulo+"-"+Principal.getUsuario().getId()+principalVista.getMiNuevaReceta().getExtensionImagen());                                                                                                
                    }
                    
                    cn.getPasosDao().eliminarTodos(recetaActual.getId());                    
                    
                    for(Pasos p : principalVista.getMiListaPasos()){
                        Pasos pasoNuevo = new Pasos(p.getDescripcion(), p.getOrden(), recetaActual.getId());
                        cn.getPasosDao().insertar(pasoNuevo);
                    }
                    
                    cn.getIngredienteRecetaDao().eliminarTodos(recetaActual.getId());
                    for(int i = 0 ; i < principalVista.getMiEditarReceta().getTablaIngredientes().getRowCount(); i++){
                        IngredienteReceta iRe = 
                        new IngredienteReceta(Logica.obtenerIdIngrediente(principalVista.getMiListaIngredientes(),String.valueOf( principalVista.getMiEditarReceta().getTablaIngredientes().getValueAt(i, 0))),
                                              recetaActual.getId(), 
                                              String.valueOf( principalVista.getMiEditarReceta().getTablaIngredientes().getValueAt(i, 1)));
                        
                        cn.getIngredienteRecetaDao().insertar(iRe);                        
                    }
                                                                                
                    Recetas recetaModificada = new Recetas(autor, titulo, consejo, imagenActualizada, comensales, coccion, preparacion, tipo);
                    cn.getRecetasDao().modificar(recetaModificada, recetaActual.getId());
                                        
                    List<Recetas> listaActualizada = cn.getRecetasDao().obtenerTodos(Principal.getUsuario().getId());
                    principalVista.setMiListaRecetas(listaActualizada);
                    Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                    Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                                                            
                    cn.cerrarConexion(cn);
                    principalVista.getMiEditarReceta().setVisible(false);
                    
                break;
                
                case "CANCELAR_EDITAR":
                    
                    principalVista.getMiEditarReceta().dispose();
                    
                break;                
                
                case "ELIMINAR_RECETA":
                    
                    if(principalVista.getTablaPrincipal().getSelectedRow() == -1){
                        JOptionPane.showMessageDialog(null, "Seleccione una receta en la tabla!!");                        
                    } else {
                        
                        int filaElegida = principalVista.getTablaPrincipal().getSelectedRow();
                        
                        Recetas r = Logica.obtenerReceta(principalVista.getMiListaRecetas(), String.valueOf(principalVista.getTablaPrincipal().getValueAt(filaElegida, 1)));
                        cn.getRecetasDao().eliminar(r);
                        File foto = new File(r.getImagen()); 
                        foto.delete();
                        
                        List<Recetas> lr = cn.getRecetasDao().obtenerTodos(Principal.getUsuario().getId());
                        principalVista.setMiListaRecetas(lr);
                        Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                        Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                        principalVista.getLblEncontradas().setText(String.valueOf(principalVista.getTablaPrincipal().getRowCount()));
                        
                        cn.cerrarConexion(cn);
                      }                                                            
                    
                break;    
                
                case "IMPRIMIR_RECETA":
                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR));    
                        
                    InputStream rutaLibroRecetas = getClass().getResourceAsStream("/vista/reportes/LibroRecetas.jasper");
                    InputStream rutaIndiceRecetas = getClass().getResourceAsStream("/vista/reportes/IndiceRecetas.jasper");
                        
                    JasperReport jrLibroRecetas = (JasperReport)JRLoader.loadObject(rutaLibroRecetas);
                    JasperReport jrIndiceRecetas = (JasperReport) JRLoader.loadObject(rutaIndiceRecetas);
                        
                    List<Recetas> listaR = new ArrayList<>();
                    List<Pasos> listaP = new ArrayList<>();
                    List<Ingredientes> listaI = new ArrayList<>();
                    List<IngredienteReceta> lir = new ArrayList<>();                             
                    List<ReporteLibroRecetas> reportes = new ArrayList<>();
                    List<ReporteIndiceReceta> indice = new ArrayList<>();
                                                
                    String cantidad = "";
                    String pasoProcedimiento = "";
                    Recetas r;

                    ImageIcon imagen = null;
                    int numPagina = 0;
                    for( int i = 0 ; i < principalVista.getTablaPrincipal().getRowCount() ; i++ ){
                        
                        if ((boolean)principalVista.getTablaPrincipal().getValueAt(i, 0)){
                            numPagina++;
                            List<String> lIngredientes = new ArrayList<>();
                            List<String> lPasos = new ArrayList<>();

                            r = Logica.obtenerReceta(principalVista.getMiListaRecetas(), String.valueOf(principalVista.getTablaPrincipal().getValueAt(i, 1)));

                            lir = cn.getIngredienteRecetaDao().obtenerTodos(r.getId());
                            listaP = cn.getPasosDao().obtenerTodos(r.getId());

                            if(r.getImagen().equals("")){
                                imagen = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
                            } else {
                                imagen = new ImageIcon (r.getImagen());
                              }

                            for(IngredienteReceta ir : lir){
                                cantidad = Logica.obtenerIngrediente(principalVista.getMiListaIngredientes(), ir.getIdIngrediente())+" "+ir.getCantidad()+".";
                                lIngredientes.add(cantidad);                                    
                            }

                            for(Pasos p : listaP){                                    
                                pasoProcedimiento = p.getDescripcion();
                                lPasos.add(pasoProcedimiento);
                            }

                            ReporteLibroRecetas reporte = new ReporteLibroRecetas(r.getTitulo(), r.getAutor(), 
                            r.getTipo(), String.valueOf(r.getComensales()),r.getTiempoPreparacion() , 
                            r.getTiempo(), lIngredientes, lPasos, r.getConsejo(), 
                            Logica.imageIconToImage(imagen));        


                            ReporteIndiceReceta indi = new ReporteIndiceReceta(r.getTitulo(), String.valueOf(numPagina));

                            reportes.add(reporte);
                            indice.add(indi);

                        } 

                    }
                        
                    if(numPagina==0){
                        JOptionPane.showMessageDialog(null, "No hay recetas seleccionadas!!");
                        Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                        Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                    } else {

                    JasperPrint jp1 = JasperFillManager.fillReport(jrIndiceRecetas, null  ,new JRBeanCollectionDataSource(indice));
                    JasperPrint jp2 = JasperFillManager.fillReport(jrLibroRecetas, null  ,new JRBeanCollectionDataSource(reportes));

                    List paginas = jp2.getPages();
                    for(int i = 0 ; i < paginas.size() ; i++ ){
                       JRPrintPage object = (JRPrintPage)paginas.get(i); 
                       jp1.addPage(object);
                    }


                    JasperViewer jv = new JasperViewer(jp1, false);

                    ImageIcon iconoViewer = new ImageIcon (getClass().getResource("/vista/imagenes/icono.png"));
                    jv.setIconImage(Logica.imageIconToImage(iconoViewer));
                    jv.setTitle("Libro de Recetas");

                    jv.setVisible(true);

                    Logica.limpiarTablaPrincipal(principalVista.getTablaPrincipal());
                    Logica.cargarTablaPrincipal(principalVista.getTablaPrincipal(), principalVista.getMiListaRecetas());
                    }
                    cn.cerrarConexion(cn);

                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));    
                        
                        
            break;    
            
    }
    } catch (SQLException ex) {
        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DAOException ex) {
          Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
              }
                        
}

               
    @Override
    public void keyPressed(KeyEvent e) {
        
       if(principalVista.getMiLogin().getTxtUsuario()==e.getSource() && e.getKeyCode() == e.VK_ENTER){
            principalVista.getMiLogin().getTxtPass().requestFocus();
        }
       
       if(principalVista.getMiLogin().getTxtPass() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           principalVista.getMiLogin().getBtnIniciar().doClick();
       }
              
       if(principalVista.getMiRegistrar().getTxtUsuario()==e.getSource() && e.getKeyCode() == e.VK_ENTER){
            principalVista.getMiRegistrar().getTxtContraseña().requestFocus();
       }
      
       if(principalVista.getMiRegistrar().getTxtContraseña()== e.getSource() && e.getKeyCode() == e.VK_ENTER){
           principalVista.getMiRegistrar().getBtnRegistrar().doClick();
       }
       
       if(principalVista.getMiCredencialAdmin().getTxtUsuario() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           principalVista.getMiCredencialAdmin().getTxtContraseña().requestFocus();
       }
       
       if(principalVista.getMiCredencialAdmin().getTxtContraseña() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           principalVista.getMiCredencialAdmin().getBtnAceptar().doClick();
       }
       
       if(principalVista.getMiNuevoIngrediente().getTxtNuevoAlimento() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           principalVista.getMiNuevoIngrediente().getBtnAgregarAlimento().doClick();
       }
       
       if(principalVista.getMiCantidadIngrediente().getTxtNuevoIngrediente() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           if(principalVista.getMiCantidadIngrediente().getTxtNuevoIngrediente().getText().isEmpty()){
               JOptionPane.showMessageDialog(principalVista,"El campo no puede quedar vacío!!");
           } else {
                   try {
                       Conexion cn = new Conexion();


                       Ingredientes ing = new Ingredientes(principalVista.getMiCantidadIngrediente().getTxtNuevoIngrediente().getText(), Principal.getUsuario().getId());
                       cn.getIngredientesDao().insertar(ing);
                       principalVista.getMiCantidadIngrediente().getTxtNuevoIngrediente().setText("");
                       principalVista.limpiarTxtAlimentos();
                       principalVista.limpiarComboAlimentos();
                       List <Ingredientes> li = cn.getIngredientesDao().obtenerTodos(Principal.getUsuario().getId());
                       principalVista.setMiListaIngredientes(li);
                       Logica.cargarComboIngredientes(principalVista.getMiNuevoIngrediente().getComboAlimentos(), principalVista.getMiListaIngredientes());
                       Logica.limpiarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
                       Logica.cargarTablaCantidadIngredientes(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento(), principalVista.getMiListaIngredientes());
                       principalVista.getMiNuevoIngrediente().getComboAlimentos().updateUI();                              
                       cn.cerrarConexion(cn);
                       
                   } catch (DAOException ex) {
                       Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (SQLException ex) {
                          Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (ClassNotFoundException ex) {
                           Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (IOException ex) {
                             Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                           }
                   }
               
       }
       
       if (principalVista.getMiNuevaReceta().getTxtNuevoTipo() == e.getSource() && e.getKeyCode() == e.VK_ENTER){
           if(principalVista.getMiNuevaReceta().getTxtNuevoTipo().getText().isEmpty()){
               JOptionPane.showMessageDialog(principalVista,"El campo no puede quedar vacío!!");
           } else {
                   try {
                       Conexion cn = new Conexion();

                       String tipo = principalVista.getMiNuevaReceta().getTxtNuevoTipo().getText();                                      
                       Tipos t = new Tipos(tipo, Principal.getUsuario().getId());
                       cn.getTipoDao().insertar(t);
                       List<Tipos> lt = cn.getTipoDao().obtenerTodos(Principal.getUsuario().getId());
                       principalVista.setMiListaTipos(lt);
                       Logica.cargarComboTipos(principalVista.getMiNuevaReceta().getTipo(), principalVista.getMiListaTipos());
                       principalVista.getMiNuevaReceta().getTxtNuevoTipo().setText("");                   
                       cn.cerrarConexion(cn);

                   } catch (SQLException ex) {
                       Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (DAOException ex) {
                         Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (ClassNotFoundException ex) {
                           Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (IOException ex) {
                             Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                           }
             }           
       }
                   
    }
                     
    

    @Override
    public void keyTyped(KeyEvent e) {
        
       if(principalVista.getMiLogin().getTxtUsuario()==e.getSource() && principalVista.getMiLogin().getTxtUsuario().getText().length() > 12) e.consume();
            
       if(principalVista.getMiRegistrar().getTxtUsuario()==e.getSource() && principalVista.getMiRegistrar().getTxtUsuario().getText().length() > 12) e.consume();            
       
       if(principalVista.getMiNuevaReceta().getTxtTitulo() == e.getSource() && principalVista.getMiNuevaReceta().getTxtTitulo().getText().length() == 40) e.consume();
                  
   }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
       boolean blockMayus = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
       
       if(blockMayus){
          principalVista.getMiLogin().getLblMayus().setVisible(true);
          principalVista.getMiRegistrar().getLblMayus().setVisible(true);
       } else {
          principalVista.getMiLogin().getLblMayus().setVisible(false); 
          principalVista.getMiRegistrar().getLblMayus().setVisible(false);
         }
       
       if (principalVista.getTxtBuscador() == e.getSource() && principalVista.getOpcionTitulo().isSelected()){    
           TableModel tm = principalVista.getTablaPrincipal().getModel();
           TableRowSorter <TableModel> modeloordenado = new TableRowSorter(principalVista.getTablaPrincipal().getModel());
           principalVista.getTablaPrincipal().setRowSorter(modeloordenado);           
           modeloordenado.setRowFilter(RowFilter.regexFilter("(?i)"+principalVista.getTxtBuscador().getText(), 1));
           principalVista.getTablaPrincipal().getRowSorter().toggleSortOrder(1 );
           principalVista.getLblEncontradas().setText(String.valueOf(principalVista.getTablaPrincipal().getRowCount()));
       }
       
       if (principalVista.getTxtBuscador() == e.getSource() && principalVista.getOpcionTipo().isSelected()){    
           TableModel tm = principalVista.getTablaPrincipal().getModel();
           TableRowSorter <TableModel> modeloordenado = new TableRowSorter(principalVista.getTablaPrincipal().getModel());
           principalVista.getTablaPrincipal().setRowSorter(modeloordenado);           
           modeloordenado.setRowFilter(RowFilter.regexFilter("(?i)"+principalVista.getTxtBuscador().getText(), 2));
           principalVista.getTablaPrincipal().getRowSorter().toggleSortOrder(1 );
           principalVista.getLblEncontradas().setText(String.valueOf(principalVista.getTablaPrincipal().getRowCount()));
       }
       
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        principalVista.cerrarRegistrar(principalVista.getMiRegistrar());
        principalVista.abrirLogin();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
                
         
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(principalVista.getTablaPrincipal() == e.getSource()){            
            if(e.getClickCount()>1){
                try {                    
                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR));
                    
                    Conexion cn = new Conexion();
                    
                    String titulo = (String) principalVista.getTablaPrincipal().getValueAt(principalVista.getTablaPrincipal().getSelectedRow(), 1);
                    Recetas r;
                    r = Logica.obtenerReceta(principalVista.getMiListaRecetas(), titulo);
                    String procedimiento = "";
                    String ingredientes = "";                   
                    
                    principalVista.getMiVerReceta().getLblTitulo().setText(r.getTitulo());
                    principalVista.getMiVerReceta().getLblComensales().setText(String.valueOf(r.getComensales()));
                    principalVista.getMiVerReceta().getLblTipo().setText(r.getTipo());
                    principalVista.getMiVerReceta().getLblTiempoPreparacion().setText(r.getTiempoPreparacion());
                    principalVista.getMiVerReceta().getLblTiempoCoccion().setText(r.getTiempo());
                    principalVista.getMiVerReceta().getTxtConsejo().setText(r.getConsejo());
                    
                    List<IngredienteReceta> ir = cn.getIngredienteRecetaDao().obtenerTodos(r.getId());
                    
                    for(IngredienteReceta i: ir){
                        ingredientes += Logica.obtenerIngrediente(principalVista.getMiListaIngredientes(),i.getIdIngrediente())+": "+i.getCantidad()+"\n";
                    }
                    
                    principalVista.getMiVerReceta().getTxtIngredientes().setText(ingredientes);
                    principalVista.getMiVerReceta().getTxtIngredientes().setCaretPosition(0);
                    
                    List<Pasos> lp = cn.getPasosDao().obtenerTodos(r.getId());
                    
                    for(Pasos p: lp){
                        procedimiento += String.valueOf(p.getOrden())+") "+p.getDescripcion()+"\n\n";
                    }
                    
                    principalVista.getMiVerReceta().getTxtProcedimiento().setText(procedimiento);
                    principalVista.getMiVerReceta().getTxtProcedimiento().setCaretPosition(0);
                    
                    
                    if(r.getImagen().equals("")){
                        ImageIcon imagen = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
                        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(principalVista.getMiVerReceta().getLblImagen().getWidth(),principalVista.getMiVerReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
                        principalVista.getMiVerReceta().getLblImagen().setIcon(icono);
                    } else {                                            
                            ImageIcon imagenB = new ImageIcon(r.getImagen());
                            Icon iconoB = new ImageIcon(imagenB.getImage().getScaledInstance(principalVista.getMiVerReceta().getLblImagen().getWidth(),principalVista.getMiVerReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
                            principalVista.getMiVerReceta().getLblImagen().setIcon(iconoB);                        
                      } 
                                                                                                        
                    principalVista.abrirVerReceta();
                    
                    cn.cerrarConexion(cn);
                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (DAOException ex) {
                      Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        }
        
        if(principalVista.getCheckSeleccionar() == e.getSource()){
            if(principalVista.getCheckSeleccionar().isSelected()){
                for( int i = 0 ; i < principalVista.getTablaPrincipal().getRowCount() ; i++){
                    principalVista.getTablaPrincipal().setValueAt(true, i, 0);
                }
            } else {
                    for( int i = 0 ; i < principalVista.getTablaPrincipal().getRowCount() ; i++){
                        principalVista.getTablaPrincipal().setValueAt(false, i, 0);
                    }
              }
        }
        
        if( principalVista.getMiEditarReceta().getLblImagen() == e.getSource()){
            
            String nombreArchivo, ruta, nuevaRuta;
                  
            File archivo;

            ImageIcon imagenBD;
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, BMP, PNG", "jpg", "gif", "png");
            JFileChooser abrirArchivo = new JFileChooser();
            abrirArchivo.setFileFilter(filtro);

            int abrir = abrirArchivo.showOpenDialog(principalVista.getMiNuevaReceta());
            if(abrir == JFileChooser.APPROVE_OPTION){                       
                try {
                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.WAIT_CURSOR));
                    archivo = abrirArchivo.getSelectedFile();
                    ruta = archivo.getAbsolutePath();
                    nombreArchivo = archivo.getName();
                    String extensionArchivo = nombreArchivo.substring(nombreArchivo.length()-4,nombreArchivo.length());
                    
                    principalVista.getMiNuevaReceta().setUrl(ruta);
                    principalVista.getMiNuevaReceta().setExtensionImagen(extensionArchivo);
                    
                    Image icono = ImageIO.read(abrirArchivo.getSelectedFile()).getScaledInstance(principalVista.getMiEditarReceta().getLblImagen().getWidth(),principalVista.getMiEditarReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT);
                    
                    principalVista.getMiEditarReceta().getLblImagen().setIcon(new ImageIcon(icono));
                    
                    principalVista.setCursor(new java.awt.Cursor( java.awt.Cursor.DEFAULT_CURSOR));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar cargar el archivo!");
                  }
                    }
       }
                    
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento() == e.getSource()){
           Logica.remarcar(principalVista.getMiCantidadIngrediente().getTablaCantidadAlimento());
        }
        
        
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    public void llenarPanelProcedimiento(List<Pasos> pasos, JPanel panel){              
        
        panel.removeAll();           
                
        List<JPanel> Procedimiento = new ArrayList<>();
        
        List<Pasos> listaActualizada = new ArrayList();
                
        Font fuente = new Font("Noto Sans", Font.BOLD, 14);
                        
        for(Pasos p : pasos){
            
            JButton boton = new JButton("Quitar");                                                
            boton.setFont(fuente);
            
            JLabel orden = new JLabel();
            orden.setFont(fuente);
        
            JTextArea txtDescripcion = new JTextArea();
            txtDescripcion.setSize(new Dimension(320, 50));                                
            txtDescripcion.setWrapStyleWord(true);
            txtDescripcion.setLineWrap(true);
            txtDescripcion.setFont(fuente);
            
            
            JPanel panelPasos = new JPanel();        
            panelPasos.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            boton.setName(String.valueOf(p.getOrden()));
            orden.setText(String.valueOf(p.getOrden()));
            txtDescripcion.setText(p.getDescripcion());
            
            panelPasos.add(orden);
            panelPasos.add(txtDescripcion);
            panelPasos.add(boton);
            
            listaActualizada.add(p);
            panel.add(panelPasos);
                         
            boton.addActionListener(new ActionListener() { 
                                                
            @Override
            public void actionPerformed(ActionEvent e) {
              
                listaActualizada.clear();
                                               
                JButton obj = (JButton)e.getSource();
                String nombre = obj.getName();
                int i = 1;                 
                for(Pasos paso: pasos){                                       
                    if(paso.getOrden() != Integer.parseInt(nombre)){
                        Pasos nuevoPaso = new Pasos(paso.getDescripcion(), i);
                        listaActualizada.add(nuevoPaso);
                        i++;
                    }
                    
                } 
                    llenarPanelProcedimiento(listaActualizada, panel);
                }                      
            });                                           
        }
       
       principalVista.setMiListaPasos(listaActualizada);
    }
                      
}
