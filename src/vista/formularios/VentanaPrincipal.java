
package vista.formularios;

import controlador.Controlador;
import controlador.Principal;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelo.Ingredientes;
import modelo.Pasos;
import modelo.Recetas;
import modelo.Tipos;
import modelo.Usuarios;
import modelo.logica.ModeloTabla;
import modelo.logica.Logica;
import modelo.logica.Logica.ImageFondo;

public class VentanaPrincipal extends javax.swing.JFrame {

    private  CredencialAdmin miCredencialAdmin;
    private  Preferencias miPreferencias;
    private  Login miLogin;
    private  Registrar miRegistrar;
    private  NuevaReceta miNuevaReceta;
    private  NuevoIngrediente miNuevoIngrediente;
    private  VerReceta miVerReceta;
    private  EditarReceta miEditarReceta;
    private  CantidadIngrediente miCantidadIngrediente;
    private  List<Ingredientes> miListaIngredientes;
    private  List<Recetas> miListaRecetas;
    private  List<Tipos> miListaTipos;
    private  List<Pasos> miListaPasos;
    private  String miRecetaSeleccionada;
    
    
    
    public VentanaPrincipal() throws IOException {
        initComponents();
        
        ImageIcon iconoPrincipal = new ImageIcon(getClass().getResource("/vista/imagenes/icono.png"));
        this.setIconImage(iconoPrincipal.getImage());
        
        this.setTitle("Cooking Recipes");
        Logica.ImageFondo image = new ImageFondo();
        image.setImage("/vista/imagenes/fondo_principal (copia).jpg");
        this.setContentPane(image);
        
        ImageIcon imagen = new ImageIcon (getClass().getResource("/vista/imagenes/search.png"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(this.getLblBuscador().getWidth(),this.getLblBuscador().getHeight(), Image.SCALE_DEFAULT));
        this.getLblBuscador().setIcon(icono);
        
        
        this.getContentPane().setLayout(new BorderLayout());
        
        this.getCheckSeleccionar().setSelected(false);
        this.getOpcionTitulo().setSelected(true);
        
        PanelPrincipal.setVisible(false);
        this.getContentPane().add(PanelPrincipal);
                                               
        lblUsuario.setText("");
        lblUsuario.setVisible(false);
        
        lblEncontradas.setText("");
        
        
        ModeloTabla miModelo = new ModeloTabla();
        miModelo.modeloPrincipal(this.getTablaPrincipal());
        
        this.getItemCerrarSesion().setEnabled(false);
        this.getItemIniciarSesion().setEnabled(true);
                        
        
        setExtendedState(MAXIMIZED_BOTH);
        
        repaint();
       
    }

                
    public void setControlador(VentanaPrincipal vp,Controlador c){
        
        vp.getMiCredencialAdmin().getTxtUsuario().addKeyListener(c);
        vp.getMiCredencialAdmin().getTxtContraseña().addKeyListener(c);        
        vp.getMiCredencialAdmin().getBtnAceptar().addActionListener(c);
        vp.getMiCredencialAdmin().getBtnAceptar().setActionCommand("ACEPTAR_CREDENCIAL");
        vp.getMiPreferencias().getBtnAceptar().addActionListener(c);                
        vp.getMiPreferencias().getBtnAceptar().setActionCommand("CONFIRMAR_PREFERENCIAS");
        vp.getMiPreferencias().getBtnRestaurar().addActionListener(c);
        vp.getMiPreferencias().getBtnRestaurar().setActionCommand("RESTAURAR");
        vp.getItemPreferencias().addActionListener(c);
        vp.getItemPreferencias().setActionCommand("OPCION_ITEM_PREFERENCIAS");
        vp.getMiPreferencias().getOpcionMysql().addActionListener(c);
        vp.getMiPreferencias().getOpcionMysql().setActionCommand("OPCION_MYSQL");        
        vp.getMiPreferencias().getOpcionSqlite().addActionListener(c);
        vp.getMiPreferencias().getOpcionSqlite().setActionCommand("OPCION_SQLITE");
        vp.getMiLogin().getTxtUsuario().addKeyListener(c);
        vp.getMiLogin().getTxtPass().addKeyListener(c);
        vp.getMiLogin().getBtnRegistrar().addActionListener(c);
        vp.getMiLogin().getBtnRegistrar().setActionCommand("REGISTRAR");
        vp.getMiLogin().getBtnIniciar().addActionListener(c);
        vp.getMiLogin().getBtnIniciar().setActionCommand("INICIAR");         
        vp.getMiRegistrar().getBtnRegistrar().addActionListener(c);
        vp.getMiRegistrar().getBtnRegistrar().setActionCommand("REGISTRARME");
        vp.getMiRegistrar().getTxtUsuario().addKeyListener(c);        
        vp.getMiRegistrar().getTxtContraseña().addKeyListener(c);                
        vp.getMiRegistrar().addWindowListener(c);         
        vp.getItemCerrarSesion().addActionListener(c);
        vp.getItemCerrarSesion().setActionCommand("CERRAR");
        vp.getItemIniciarSesion().addActionListener(c);
        vp.getItemIniciarSesion().setActionCommand("ABRIR");        
        vp.getTablaPrincipal().addMouseListener(c);
        vp.getTxtBuscador().addKeyListener(c);
        vp.getTablaPrincipal().getSelectionModel().addListSelectionListener(c);        
        vp.getBtnAñadirReceta().addActionListener(c);
        vp.getBtnAñadirReceta().setActionCommand("RECETA");
        vp.getBtnListaAlimentos().addActionListener(c);
        vp.getBtnListaAlimentos().setActionCommand("ALIMENTO");
        vp.getBtnBorrarReceta().addActionListener(c);
        vp.getBtnEditarReceta().addActionListener(c);
        vp.getBtnEditarReceta().setActionCommand("EDITAR_RECETA");
        vp.getBtnBorrarReceta().setActionCommand("ELIMINAR_RECETA");
        vp.getBtnImprimirReceta().addActionListener(c);
        vp.getBtnImprimirReceta().setActionCommand("IMPRIMIR_RECETA");
        vp.getCheckSeleccionar().addMouseListener(c);        
        vp.getMiNuevoIngrediente().getBtnAgregarAlimento().addActionListener(c);
        vp.getMiNuevoIngrediente().getBtnAgregarAlimento().setActionCommand("AGREGAR_ALIMENTO");
        vp.getMiNuevoIngrediente().getBtnEliminarAlimento().addActionListener(c);
        vp.getMiNuevoIngrediente().getBtnEliminarAlimento().setActionCommand("ELIMINAR_ALIMENTO");
        vp.getMiNuevoIngrediente().getTxtNuevoAlimento().addKeyListener(c);
        vp.getMiNuevaReceta().getTxtTitulo().addKeyListener(c);
        vp.getMiNuevaReceta().getBtnAñadirAlimentos().addActionListener(c);
        vp.getMiNuevaReceta().getBtnAñadirAlimentos().setActionCommand("CANTIDAD");                
        vp.getMiNuevaReceta().getBtnQuitarIngrediente().addActionListener(c);
        vp.getMiNuevaReceta().getBtnQuitarIngrediente().setActionCommand("QUITAR_INGREDIENTE");
        vp.getMiNuevaReceta().getBtnAñadirPaso().addActionListener(c);
        vp.getMiNuevaReceta().getBtnAñadirPaso().setActionCommand("AÑADIR_PASO");
        vp.getMiNuevaReceta().getBtnEnviarAprocedimiento().addActionListener(c);
        vp.getMiNuevaReceta().getBtnEnviarAprocedimiento().setActionCommand("ENVIAR_PASO_A_PROCEDIMIENTO");
        vp.getMiNuevaReceta().getBtnEliminarUltimoPaso().addActionListener(c);
        vp.getMiNuevaReceta().getBtnEliminarUltimoPaso().setActionCommand("ELIMINAR_ULTIMO_PASO");
        vp.getMiNuevaReceta().getBtnCancelar().addActionListener(c);
        vp.getMiNuevaReceta().getBtnCancelar().setActionCommand("CANCELAR_RECETA");
        vp.getMiNuevaReceta().getBtnAñadirFoto().addActionListener(c);
        vp.getMiNuevaReceta().getBtnAñadirFoto().setActionCommand("AÑADIR_FOTO");
        vp.getMiNuevaReceta().getBtnQuitarFoto().addActionListener(c);
        vp.getMiNuevaReceta().getBtnQuitarFoto().setActionCommand("QUITAR_FOTO");
        vp.getMiNuevaReceta().getBtnCancelarPaso().addActionListener(c);
        vp.getMiNuevaReceta().getBtnCancelarPaso().setActionCommand("CANCELAR_PASO");
        vp.getMiNuevaReceta().getTxtNuevoTipo().addKeyListener(c);
        vp.getMiNuevaReceta().getBtnGuardarReceta().addActionListener(c);
        vp.getMiNuevaReceta().getBtnGuardarReceta().setActionCommand("GUARDAR_RECETA");
        vp.getMiCantidadIngrediente().getTablaCantidadAlimento().addMouseListener(c);        
        vp.getMiCantidadIngrediente().getBtnIncorporar().addActionListener(c);
        vp.getMiCantidadIngrediente().getBtnIncorporar().setActionCommand("INCORPORAR");
        vp.getMiCantidadIngrediente().getBtnCancelar().addActionListener(c);
        vp.getMiCantidadIngrediente().getBtnCancelar().setActionCommand("CANCELAR_CANTIDAD");
        vp.getMiCantidadIngrediente().getTxtNuevoIngrediente().addKeyListener(c);
        vp.getMiEditarReceta().getBtnActualizar().addActionListener(c);
        vp.getMiEditarReceta().getBtnActualizar().setActionCommand("ACTUALIZAR");
        vp.getMiEditarReceta().getBtnAgregarPaso().addActionListener(c);
        vp.getMiEditarReceta().getBtnAgregarPaso().setActionCommand("AGREGAR_PASO");
        vp.getMiEditarReceta().getLblImagen().addMouseListener(c);        
        vp.getMiEditarReceta().getBtnAñadirIngrediente().addActionListener(c);
        vp.getMiEditarReceta().getBtnAñadirIngrediente().setActionCommand("AÑADIR_INGREDIENTE");
        vp.getMiEditarReceta().getBtnQuitarIngrediente().addActionListener(c);
        vp.getMiEditarReceta().getBtnQuitarIngrediente().setActionCommand("QUITAR");
        vp.getMiEditarReceta().getBtnCancelar().addActionListener(c);
        vp.getMiEditarReceta().getBtnCancelar().setActionCommand("CANCELAR_EDITAR");
                        
    }   
       
    public CredencialAdmin getMiCredencialAdmin() {
        return miCredencialAdmin;
    }

    public void setMiCredencialAdmin(CredencialAdmin miCredencialAdmin) {
        this.miCredencialAdmin = miCredencialAdmin;
    }

    public Preferencias getMiPreferencias() {
        return miPreferencias;
    }

    public void setMiPreferencias(Preferencias miPreferencias) {
        this.miPreferencias = miPreferencias;
    }
        
    
    public String getMiRecetaSeleccionada() {
        return miRecetaSeleccionada;
    }

    public void setMiRecetaSeleccionada(String miRecetaSeleccionada) {
        this.miRecetaSeleccionada = miRecetaSeleccionada;
    }

    
    public List<Pasos> getMiListaPasos() {
        return miListaPasos;
    }

    public void setMiListaPasos(List<Pasos> miListaPasos) {
        this.miListaPasos = miListaPasos;
    }

    
    public EditarReceta getMiEditarReceta() {
        return miEditarReceta;
    }

    public void setMiEditarReceta(EditarReceta miEditarReceta) {
        this.miEditarReceta = miEditarReceta;
    }
    
    
    public VerReceta getMiVerReceta() {
        return miVerReceta;
    }

    public void setMiVerReceta(VerReceta miVerReceta) {
        this.miVerReceta = miVerReceta;
    }
    
    

    public List<Ingredientes> getMiListaIngredientes() {
        return miListaIngredientes;
    }

    public void setMiListaIngredientes(List<Ingredientes> miListaIngredientes) {
        this.miListaIngredientes = miListaIngredientes;
    }

    public List<Recetas> getMiListaRecetas() {
        return miListaRecetas;
    }

    public void setMiListaRecetas(List<Recetas> miListaRecetas) {
        this.miListaRecetas = miListaRecetas;
    }

    public List<Tipos> getMiListaTipos() {
        return miListaTipos;
    }

    public void setMiListaTipos(List<Tipos> miListaTipos) {
        this.miListaTipos = miListaTipos;
    }
    
    
    
    public void limpiarTxtAlimentos(){
        getMiNuevoIngrediente().getTxtNuevoAlimento().setText("");
    }
    
    public void limpiarComboAlimentos(){
        getMiNuevoIngrediente().getComboAlimentos().removeAllItems();
    }
    
    public void abrirPreferencias(){
        getMiPreferencias().setVisible(true);
    }
    public void abrirLogin(){          
       getMiLogin().setVisible(true);
       getMiLogin().getTxtUsuario().requestFocus();
    }
    
    public void abrirRegistrar(){
        getMiRegistrar().getTxtUsuario().requestFocus();
        getMiRegistrar().setVisible(true);
    }
    
    public void abrirNuevaReceta(){
        getMiNuevaReceta().setVisible(true);
        
    }
    
    public void abrirCantidad(){
        getMiNuevaReceta().setVisible(false);
        getMiCantidadIngrediente().setVisible(true);
    }
    
    public void cerrarCantidad(){        
        getMiCantidadIngrediente().setVisible(false);
        getMiNuevaReceta().setVisible(true);
    }
    
    
    public void abrirListaAlimentos(){        
        getMiNuevoIngrediente().getTxtNuevoAlimento().setText("");
        getMiNuevoIngrediente().setVisible(true);
        getMiNuevoIngrediente().getTxtNuevoAlimento().requestFocus();
    }
    public void abrirVerReceta(){
        getMiVerReceta().setVisible(true);
    }
    public void limpiarVerReceta(){
        getMiVerReceta().getTxtConsejo().setText("");
        getMiVerReceta().getTxtIngredientes().setText("");
        getMiVerReceta().getTxtProcedimiento().setText("");
        getMiVerReceta().getLblComensales().setText("");
        getMiVerReceta().getLblTipo().setText("");
        getMiVerReceta().getLblTiempoPreparacion().setText("");
        getMiVerReceta().getLblTiempoCoccion().setText("");
        getMiVerReceta().getLblTitulo().setText("");
        
        ImageIcon imagen = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(getMiVerReceta().getLblImagen().getWidth(),getMiVerReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
        getMiNuevaReceta().getLblImagen().setIcon(icono);
        
    }
    
    public void cerrarLogin(Login l){
        l.setVisible(false);
    }
    public void cerrarRegistrar(Registrar r){
        limpiarRegistrar(r);
        r.setVisible(false);        
    }
    public void cerrarPreferencias(Preferencias ob){
        ob.setVisible(false);
    }
    public void limpiarLogin(Login l){        
        l.getTxtUsuario().setText("");
        l.getTxtPass().setText("");
        l.getTxtUsuario().requestFocus();
    }
    
    public void limpiarRegistrar(Registrar r){        
        r.getTxtUsuario().setText("");
        r.getTxtContraseña().setText("");
        r.getTxtUsuario().requestFocus();
    }
    
    public void iniciarSesion(){
        getLblUsuario().setText(Principal.getUsuario().getNombre());
        getLblUsuario().setVisible(true);
    }
    
    public void limpiarNuevaReceta(){
        ModeloTabla modelo = new ModeloTabla();
        modelo.modeloIngredientes(getMiNuevaReceta().getTablaAlimentos());
        
        getMiNuevaReceta().getTxtTitulo().setText("");
        getMiNuevaReceta().getTipo().setSelectedIndex(0);
        getMiNuevaReceta().getTxtAutor().setText(Principal.getUsuario().getNombre());
        getMiNuevaReceta().getComensales().setValue(0);
        getMiNuevaReceta().getCoccionHora().setValue(0);
        getMiNuevaReceta().getCoccionMinuto().setValue(0);
        getMiNuevaReceta().getPreparacionHora().setValue(0);
        getMiNuevaReceta().getPreparacionMinuto().setValue(0);
        getMiNuevaReceta().getTxtNuevoTipo().setText("");
        getMiNuevaReceta().getTxtProcedimiento().setText("");
        getMiNuevaReceta().getTxtConsejo().setText("");
        getMiNuevaReceta().getLblPaso().setText("");
        getMiNuevaReceta().getLblCantidadIngredientes().setText("");
        getMiNuevaReceta().getTxtPaso().setText("");
        getMiNuevaReceta().getTxtPaso().setEnabled(false);
        getMiNuevaReceta().getBtnAñadirPaso().setEnabled(true);
        getMiNuevaReceta().setPaso(0);
        
        ImageIcon imagen2 = new ImageIcon (getClass().getResource("/vista/imagenes/platovacio.jpg"));
        Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(getMiNuevaReceta().getLblImagen().getWidth(),getMiNuevaReceta().getLblImagen().getHeight(), Image.SCALE_DEFAULT));
        getMiNuevaReceta().getLblImagen().setIcon(icono2);
        getMiNuevaReceta().setUrl("");
        
        getMiNuevaReceta().setVisible(false);
    }
    
    public void cerrarSesion(){
        
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Realmente desea cerrar la sesión:\n"+Principal.getUsuario().getNombre()+"?","Cerrando Sesión", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION ){
                Principal.setUsuario(new Usuarios(null,null,null));
                getLblUsuario().setText(Principal.getUsuario().getNombre());
                getLblUsuario().setVisible(true);
                this.getItemCerrarSesion().setEnabled(false);
                this.getItemIniciarSesion().setEnabled(true);
                this.getPanelPrincipal().setVisible(false);
                Logica.limpiarTablaPrincipal(this.getTablaPrincipal());
                limpiarComboAlimentos();                
            }
    }

    public JLabel getLblEncontradas() {
        return lblEncontradas;
    }

    public void setLblEncontradas(JLabel lblEncontradas) {
        this.lblEncontradas = lblEncontradas;
    }

    public NuevaReceta getMiNuevaReceta() {
        return miNuevaReceta;
    }

    public void setMiNuevaReceta(NuevaReceta miNuevaReceta) {
        this.miNuevaReceta = miNuevaReceta;
    }

    public JLabel getLblBuscador() {
        return lblBuscador;
    }

    public void setLblBuscador(JLabel lblBuscador) {
        this.lblBuscador = lblBuscador;
    }

    public JTextField getTxtBuscador() {
        return txtBuscador;
    }

    public void setTxtBuscador(JTextField txtBuscador) {
        this.txtBuscador = txtBuscador;
    }

    
    public NuevoIngrediente getMiNuevoIngrediente() {
        return miNuevoIngrediente;
    }

    public void setMiNuevoIngrediente(NuevoIngrediente miNuevoIngrediente) {
        this.miNuevoIngrediente = miNuevoIngrediente;
    }

    public CantidadIngrediente getMiCantidadIngrediente() {
        return miCantidadIngrediente;
    }

    public void setMiCantidadIngrediente(CantidadIngrediente miCantidadIngrediente) {
        this.miCantidadIngrediente = miCantidadIngrediente;
    }
    

    
    public Login getMiLogin() {
        return miLogin;
    }

    public void setMiLogin(Login miLogin) {
        this.miLogin = miLogin;
    }

    public Registrar getMiRegistrar() {
        return miRegistrar;
    }

    public void setMiRegistrar(Registrar miRegistrar) {
        this.miRegistrar = miRegistrar;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JPanel getPanelPrincipal() {
        return PanelPrincipal;
    }

    public void setPanelPrincipal(JPanel PanelPrincipal) {
        this.PanelPrincipal = PanelPrincipal;
    }

    public JButton getBtnListaAlimentos() {
        return btnListaAlimentos;
    }

    public void setBtnListaAlimentos(JButton btnListaAlimentos) {
        this.btnListaAlimentos = btnListaAlimentos;
    }
    
    
    
    public JButton getBtnBorrarReceta() {
        return btnBorrarReceta;
    }

    public JButton getBtnAñadirReceta() {
        return btnAñadirReceta;
    }

    public void setBtnAñadirReceta(JButton btnAñadirReceta) {
        this.btnAñadirReceta = btnAñadirReceta;
    }
    
    

    public void setBtnBorrarReceta(JButton btnBorrarRecta) {
        this.btnBorrarReceta = btnBorrarRecta;
    }

    public JButton getBtnEditarReceta() {
        return btnEditarReceta;
    }

    public void setBtnEditarReceta(JButton btnEditarReceta) {
        this.btnEditarReceta = btnEditarReceta;
    }

    public JButton getBtnImprimirReceta() {
        return btnImprimirReceta;
    }

    public void setBtnImprimirReceta(JButton btnImprimirReceta) {
        this.btnImprimirReceta = btnImprimirReceta;
    }

    public JTable getTablaPrincipal() {
        return tablaPrincipal;
    }

    public void setTablaPrincipal(JTable tablaPrincipal) {
        this.tablaPrincipal = tablaPrincipal;
    }

    public JMenuItem getItemCerrarSesion() {
        return itemCerrarSesion;
    }

    public void setItemCerrarSesion(JMenuItem itemCerrarSesion) {
        this.itemCerrarSesion = itemCerrarSesion;
    }

    public JMenuItem getItemIniciarSesion() {
        return itemIniciarSesion;
    }

    public void setItemIniciarSesion(JMenuItem itemIniciarSesion) {
        this.itemIniciarSesion = itemIniciarSesion;
    }

    public JMenuItem getItemPreferencias() {
        return itemPreferencias;
    }

    public void setItemPreferencias(JMenuItem itemPreferencias) {
        this.itemPreferencias = itemPreferencias;
    }

    
    public JRadioButton getOpcionTipo() {
        return opcionTipo;
    }

    public void setOpcionTipo(JRadioButton opcionTipo) {
        this.opcionTipo = opcionTipo;
    }

    public JRadioButton getOpcionTitulo() {
        return opcionTitulo;
    }

    public void setOpcionTitulo(JRadioButton opcionTitulo) {
        this.opcionTitulo = opcionTitulo;
    }

    public JCheckBox getCheckSeleccionar() {
        return checkSeleccionar;
    }

    public void setCheckSeleccionar(JCheckBox checkSeleccionar) {
        this.checkSeleccionar = checkSeleccionar;
    }

    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PanelPrincipal = new javax.swing.JPanel();
        PanelInferior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEncontradas = new javax.swing.JLabel();
        PanelIzquierdo = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        btnEditarReceta = new javax.swing.JButton();
        btnAñadirReceta = new javax.swing.JButton();
        btnBorrarReceta = new javax.swing.JButton();
        btnImprimirReceta = new javax.swing.JButton();
        btnListaAlimentos = new javax.swing.JButton();
        PanelCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPrincipal = new javax.swing.JTable();
        PanelDerecho = new javax.swing.JPanel();
        PanelDerechoSuperior = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        opcionTitulo = new javax.swing.JRadioButton();
        opcionTipo = new javax.swing.JRadioButton();
        txtBuscador = new javax.swing.JTextField();
        lblBuscador = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        checkSeleccionar = new javax.swing.JCheckBox();
        panelSuperior = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuSesion = new javax.swing.JMenu();
        itemCerrarSesion = new javax.swing.JMenuItem();
        itemIniciarSesion = new javax.swing.JMenuItem();
        menuOpciones = new javax.swing.JMenu();
        itemPreferencias = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setOpaque(false);
        PanelPrincipal.setLayout(new java.awt.BorderLayout());

        PanelInferior.setBackground(java.awt.Color.white);
        PanelInferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setText("Usuario:");
        PanelInferior.add(jLabel1);

        lblUsuario.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(30, 128, 0));
        lblUsuario.setText("jLabel2");
        PanelInferior.add(lblUsuario);

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Recetas encontradas:");
        PanelInferior.add(jLabel4);

        lblEncontradas.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblEncontradas.setForeground(new java.awt.Color(30, 128, 0));
        lblEncontradas.setText("jLabel5");
        PanelInferior.add(lblEncontradas);

        PanelPrincipal.add(PanelInferior, java.awt.BorderLayout.PAGE_END);

        PanelIzquierdo.setBackground(java.awt.Color.pink);
        PanelIzquierdo.setOpaque(false);

        panelBotones.setBackground(new java.awt.Color(1, 1, 1));
        panelBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.white));

        btnEditarReceta.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnEditarReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/mixer.png"))); // NOI18N
        btnEditarReceta.setText("Editar Receta");
        btnEditarReceta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEditarReceta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnAñadirReceta.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnAñadirReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/chef.png"))); // NOI18N
        btnAñadirReceta.setText("Nueva Receta");
        btnAñadirReceta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAñadirReceta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnBorrarReceta.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnBorrarReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/cleaver.png"))); // NOI18N
        btnBorrarReceta.setText("Eliminar Receta");
        btnBorrarReceta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarReceta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnImprimirReceta.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnImprimirReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/pdf.png"))); // NOI18N
        btnImprimirReceta.setText("Imprimir Receta/s");
        btnImprimirReceta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimirReceta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImprimirReceta.setPreferredSize(new java.awt.Dimension(186, 40));

        btnListaAlimentos.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnListaAlimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/list.png"))); // NOI18N
        btnListaAlimentos.setText("Lista de Alimentos");
        btnListaAlimentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnListaAlimentos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirReceta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAñadirReceta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarReceta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrarReceta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListaAlimentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnAñadirReceta)
                .addGap(18, 18, 18)
                .addComponent(btnEditarReceta)
                .addGap(18, 18, 18)
                .addComponent(btnBorrarReceta)
                .addGap(18, 18, 18)
                .addComponent(btnImprimirReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnListaAlimentos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelIzquierdoLayout = new javax.swing.GroupLayout(PanelIzquierdo);
        PanelIzquierdo.setLayout(PanelIzquierdoLayout);
        PanelIzquierdoLayout.setHorizontalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIzquierdoLayout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelIzquierdoLayout.setVerticalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIzquierdoLayout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 221, Short.MAX_VALUE))
        );

        PanelPrincipal.add(PanelIzquierdo, java.awt.BorderLayout.LINE_START);

        PanelCentral.setPreferredSize(new java.awt.Dimension(300, 300));

        tablaPrincipal.setAutoCreateRowSorter(true);
        tablaPrincipal.setFont(new java.awt.Font("Purisa", 1, 18)); // NOI18N
        tablaPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPrincipal.setOpaque(false);
        tablaPrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPrincipal.getTableHeader().setResizingAllowed(false);
        tablaPrincipal.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaPrincipal);

        javax.swing.GroupLayout PanelCentralLayout = new javax.swing.GroupLayout(PanelCentral);
        PanelCentral.setLayout(PanelCentralLayout);
        PanelCentralLayout.setHorizontalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );
        PanelCentralLayout.setVerticalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelPrincipal.add(PanelCentral, java.awt.BorderLayout.CENTER);

        PanelDerecho.setBackground(java.awt.Color.green);
        PanelDerecho.setOpaque(false);

        PanelDerechoSuperior.setBackground(new java.awt.Color(1, 1, 1));
        PanelDerechoSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.white));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Busqueda", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(254, 254, 254))); // NOI18N

        buttonGroup1.add(opcionTitulo);
        opcionTitulo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        opcionTitulo.setForeground(new java.awt.Color(254, 254, 254));
        opcionTitulo.setText("Titulo");
        opcionTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionTituloActionPerformed(evt);
            }
        });

        buttonGroup1.add(opcionTipo);
        opcionTipo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        opcionTipo.setForeground(new java.awt.Color(254, 254, 254));
        opcionTipo.setText("Tipo");

        txtBuscador.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblBuscador.setForeground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opcionTitulo)
                            .addComponent(opcionTipo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(opcionTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(opcionTipo)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Selección", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(254, 254, 254))); // NOI18N

        checkSeleccionar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        checkSeleccionar.setForeground(new java.awt.Color(254, 254, 254));
        checkSeleccionar.setText("Seleccionar todas las recetas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelDerechoSuperiorLayout = new javax.swing.GroupLayout(PanelDerechoSuperior);
        PanelDerechoSuperior.setLayout(PanelDerechoSuperiorLayout);
        PanelDerechoSuperiorLayout.setHorizontalGroup(
            PanelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDerechoSuperiorLayout.setVerticalGroup(
            PanelDerechoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoSuperiorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelDerechoLayout = new javax.swing.GroupLayout(PanelDerecho);
        PanelDerecho.setLayout(PanelDerechoLayout);
        PanelDerechoLayout.setHorizontalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelDerechoSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelDerechoLayout.setVerticalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoLayout.createSequentialGroup()
                .addComponent(PanelDerechoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 221, Short.MAX_VALUE))
        );

        PanelPrincipal.add(PanelDerecho, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        PanelPrincipal.add(panelSuperior, java.awt.BorderLayout.PAGE_START);

        menuSesion.setText("Sesion");
        menuSesion.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N

        itemCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itemCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/locked-2.png"))); // NOI18N
        itemCerrarSesion.setText("Cerrar Sesion");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarSesionActionPerformed(evt);
            }
        });
        menuSesion.add(itemCerrarSesion);

        itemIniciarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        itemIniciarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/locked-3.png"))); // NOI18N
        itemIniciarSesion.setText("Iniciar Sesión");
        itemIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIniciarSesionActionPerformed(evt);
            }
        });
        menuSesion.add(itemIniciarSesion);

        jMenuBar1.add(menuSesion);

        menuOpciones.setText("Opciones");
        menuOpciones.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        itemPreferencias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        itemPreferencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/preferencias.png"))); // NOI18N
        itemPreferencias.setText("Preferencias (Administrador)");
        itemPreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPreferenciasActionPerformed(evt);
            }
        });
        menuOpciones.add(itemPreferencias);

        jMenuBar1.add(menuOpciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIniciarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIniciarSesionActionPerformed

    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void opcionTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionTituloActionPerformed

    private void itemPreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPreferenciasActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCentral;
    private javax.swing.JPanel PanelDerecho;
    private javax.swing.JPanel PanelDerechoSuperior;
    private javax.swing.JPanel PanelInferior;
    private javax.swing.JPanel PanelIzquierdo;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnAñadirReceta;
    private javax.swing.JButton btnBorrarReceta;
    private javax.swing.JButton btnEditarReceta;
    private javax.swing.JButton btnImprimirReceta;
    private javax.swing.JButton btnListaAlimentos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkSeleccionar;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemIniciarSesion;
    private javax.swing.JMenuItem itemPreferencias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscador;
    private javax.swing.JLabel lblEncontradas;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenu menuSesion;
    private javax.swing.JRadioButton opcionTipo;
    private javax.swing.JRadioButton opcionTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JTable tablaPrincipal;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
