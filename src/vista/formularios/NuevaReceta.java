
package vista.formularios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import modelo.Pasos;
import modelo.logica.Logica;
import modelo.logica.Logica.ImageFondo;
import modelo.logica.ModeloTabla;


public class NuevaReceta extends javax.swing.JDialog {

    private int paso;
    private String url;
    private String extensionImagen;
    private List<Pasos> lp = new ArrayList<>();
    private int ultimoIdInsertado;
    
    public NuevaReceta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
                            
        Logica.ImageFondo image = new ImageFondo();
        image.setImage("/vista/imagenes/fondo_login.png");                
        this.setContentPane(image);
               
        this.setTitle("Nueva Receta");
        
        ModeloTabla miModelo = new ModeloTabla();
        miModelo.modeloIngredientes(this.getTablaAlimentos());
                        
        this.setResizable(false);
        
        this.getComensales().setModel(new SpinnerNumberModel(0,0,100,1));        
        this.getPreparacionHora().setModel(new SpinnerNumberModel(0,0,24,1));
        this.getPreparacionMinuto().setModel(new SpinnerNumberModel(0,0,60,1));
        this.getCoccionHora().setModel(new SpinnerNumberModel(0,0,24,1));
        this.getCoccionMinuto().setModel(new SpinnerNumberModel(0,0,60,1));
        
        this.getLblPaso().setText("");
        this.getLblCantidadIngredientes().setText("");
        this.getBtnEnviarAprocedimiento().setEnabled(false);
        this.getBtnCancelarPaso().setEnabled(false);
        this.getBtnEliminarUltimoPaso().setEnabled(false);
        this.getTxtPaso().setEnabled(false);
                    
        this.setPaso(0);
        this.setExtensionImagen("");
        this.getContentPane().add(panelPrincipal);
        
        this.setLocationRelativeTo(null);
                                
        
        
    }

    public int getPaso() {
        return paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }
    
     public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getExtensionImagen() {
        return extensionImagen;
    }

    public void setExtensionImagen(String extensionImagen) {
        this.extensionImagen = extensionImagen;
    }
    
    public List<Pasos> getLp() {
        return lp;
    }

    public void setLp(List<Pasos> lp) {
        this.lp = lp;
    }
           
    public int getUltimoIdInsertado() {
        return ultimoIdInsertado;
    }

    public void setUltimoIdInsertado(int ultimoIdInsertado) {
        this.ultimoIdInsertado = ultimoIdInsertado;
    }

    
    public JButton getBtnEliminarUltimoPaso() {
        return btnEliminarUltimoPaso;
    }

    public void setBtnEliminarUltimoPaso(JButton btnEliminarUltimoPaso) {
        this.btnEliminarUltimoPaso = btnEliminarUltimoPaso;
    }

    public JLabel getLblCantidadIngredientes() {
        return lblCantidadIngredientes;
    }

    public void setLblCantidadIngredientes(JLabel lblCantidadIngredientes) {
        this.lblCantidadIngredientes = lblCantidadIngredientes;
    }                
    
    public JButton getBtnGuardarReceta() {
        return btnGuardarReceta;
    }

    public void setBtnGuardarReceta(JButton btnGuardarReceta) {
        this.btnGuardarReceta = btnGuardarReceta;
    }

    
    public JButton getBtnCancelarPaso() {
        return btnCancelarPaso;
    }

    public void setBtnCancelarPaso(JButton btnCancelarPaso) {
        this.btnCancelarPaso = btnCancelarPaso;
    }

    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JTextField getTxtNuevoTipo() {
        return txtNuevoTipo;
    }

    public void setTxtNuevoTipo(JTextField txtNuevoTipo) {
        this.txtNuevoTipo = txtNuevoTipo;
    }

    
    public JToggleButton getBtnEnviarAprocedimiento() {
        return btnEnviarAprocedimiento;
    }

    public void setBtnEnviarAprocedimiento(JToggleButton btnEnviarAprocedimiento) {
        this.btnEnviarAprocedimiento = btnEnviarAprocedimiento;
    }
                
    public JButton getBtnQuitarIngrediente() {
        return btnQuitarIngrediente;
    }

    public void setBtnQuitarIngrediente(JButton btnQuitarIngrediente) {
        this.btnQuitarIngrediente = btnQuitarIngrediente;
    }

    
    public JLabel getLblImagen() {
        return lblImagen;
    }

    public void setLblImagen(JLabel lblImagen) {
        this.lblImagen = lblImagen;
    }

    public JLabel getLblPaso() {
        return lblPaso;
    }

    public void setLblPaso(JLabel lblPaso) {
        this.lblPaso = lblPaso;
    }

    public JTextArea getTxtConsejo() {
        return txtConsejo;
    }

    public void setTxtConsejo(JTextArea txtConsejo) {
        this.txtConsejo = txtConsejo;
    }

    public JTextArea getTxtPaso() {
        return txtPaso;
    }

    public void setTxtPaso(JTextArea txtPaso) {
        this.txtPaso = txtPaso;
    }

    public JTextArea getTxtProcedimiento() {
        return txtProcedimiento;
    }

    public void setTxtProcedimiento(JTextArea txtProcedimiento) {
        this.txtProcedimiento = txtProcedimiento;
    }
        
    public JButton getBtnAñadirAlimentos() {
        return btnAñadirAlimentos;
    }

    public void setBtnAñadirAlimentos(JButton btnAñadirAlimentos) {
        this.btnAñadirAlimentos = btnAñadirAlimentos;
    }

    public JButton getBtnAñadirFoto() {
        return btnAñadirFoto;
    }

    public void setBtnAñadirFoto(JButton btnAñadirFoto) {
        this.btnAñadirFoto = btnAñadirFoto;
    }

    public JButton getBtnAñadirPaso() {
        return btnAñadirPaso;
    }

    public void setBtnAñadirPaso(JButton btnAñadirPaso) {
        this.btnAñadirPaso = btnAñadirPaso;
    }

    public JButton getBtnQuitarFoto() {
        return btnQuitarFoto;
    }

    public void setBtnQuitarFoto(JButton btnQuitarFoto) {
        this.btnQuitarFoto = btnQuitarFoto;
    }

    public JSpinner getCoccionHora() {
        return coccionHora;
    }

    public void setCoccionHora(JSpinner coccionHora) {
        this.coccionHora = coccionHora;
    }

    public JSpinner getCoccionMinuto() {
        return coccionMinuto;
    }

    public void setCoccionMinuto(JSpinner coccionMinuto) {
        this.coccionMinuto = coccionMinuto;
    }

    public JSpinner getComensales() {
        return comensales;
    }

    public void setComensales(JSpinner comensales) {
        this.comensales = comensales;
    }

    public JSpinner getPreparacionHora() {
        return preparacionHora;
    }

    public void setPreparacionHora(JSpinner preparacionHora) {
        this.preparacionHora = preparacionHora;
    }

    public JSpinner getPreparacionMinuto() {
        return preparacionMinuto;
    }

    public void setPreparacionMinuto(JSpinner preparacionMinuto) {
        this.preparacionMinuto = preparacionMinuto;
    }

    public JTable getTablaAlimentos() {
        return tablaAlimentos;
    }

    public void setTablaAlimentos(JTable tablaAlimentos) {
        this.tablaAlimentos = tablaAlimentos;
    }

    public JComboBox<String> getTipo() {
        return tipo;
    }

    public void setTipo(JComboBox<String> tipo) {
        this.tipo = tipo;
    }

    public JTextField getTxtAutor() {
        return txtAutor;
    }

    public void setTxtAutor(JTextField txtAutor) {
        this.txtAutor = txtAutor;
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(JTextField txtTitulo) {
        this.txtTitulo = txtTitulo;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelIzq = new javax.swing.JPanel();
        btnAñadirAlimentos = new javax.swing.JButton();
        btnAñadirPaso = new javax.swing.JButton();
        btnAñadirFoto = new javax.swing.JButton();
        btnQuitarFoto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        panelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comensales = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        preparacionHora = new javax.swing.JSpinner();
        preparacionMinuto = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        coccionHora = new javax.swing.JSpinner();
        coccionMinuto = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        txtNuevoTipo = new javax.swing.JTextField();
        panelCen = new javax.swing.JPanel();
        panelConsejo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtConsejo = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlimentos = new javax.swing.JTable();
        btnQuitarIngrediente = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblCantidadIngredientes = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPaso = new javax.swing.JTextArea();
        btnCancelarPaso = new javax.swing.JButton();
        btnEnviarAprocedimiento = new javax.swing.JToggleButton();
        lblPaso = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        panelInferior = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardarReceta = new javax.swing.JButton();
        panelDerecho = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtProcedimiento = new javax.swing.JTextArea();
        btnEliminarUltimoPaso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 650));
        setResizable(false);

        panelPrincipal.setPreferredSize(new java.awt.Dimension(1100, 650));

        panelIzq.setForeground(new java.awt.Color(222, 225, 103));

        btnAñadirAlimentos.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnAñadirAlimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/kitchen-board.png"))); // NOI18N
        btnAñadirAlimentos.setText("Añadir Ingrediente");
        btnAñadirAlimentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAñadirAlimentos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadirAlimentos.setPreferredSize(new java.awt.Dimension(224, 40));

        btnAñadirPaso.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnAñadirPaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/bowl.png"))); // NOI18N
        btnAñadirPaso.setText("Añadir Paso");
        btnAñadirPaso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAñadirPaso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadirPaso.setPreferredSize(new java.awt.Dimension(224, 40));

        btnAñadirFoto.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnAñadirFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/photo-camera.png"))); // NOI18N
        btnAñadirFoto.setText("Añadir foto");
        btnAñadirFoto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAñadirFoto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadirFoto.setPreferredSize(new java.awt.Dimension(224, 40));

        btnQuitarFoto.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnQuitarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/close.png"))); // NOI18N
        btnQuitarFoto.setText("Quitar foto");
        btnQuitarFoto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuitarFoto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnQuitarFoto.setPreferredSize(new java.awt.Dimension(224, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Imagen", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(254, 254, 254))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(224, 224));

        lblImagen.setMaximumSize(new java.awt.Dimension(188, 175));
        lblImagen.setMinimumSize(new java.awt.Dimension(188, 175));
        lblImagen.setPreferredSize(new java.awt.Dimension(188, 175));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelIzqLayout = new javax.swing.GroupLayout(panelIzq);
        panelIzq.setLayout(panelIzqLayout);
        panelIzqLayout.setHorizontalGroup(
            panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzqLayout.createSequentialGroup()
                .addGroup(panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelIzqLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAñadirAlimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAñadirPaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAñadirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelIzqLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnQuitarFoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelIzqLayout.setVerticalGroup(
            panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAñadirAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAñadirPaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAñadirFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Título:");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Autor:");

        txtTitulo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        txtTitulo.setPreferredSize(new java.awt.Dimension(200, 30));

        txtAutor.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        txtAutor.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Tiempo de preparación:");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("Tiempo de cocción:");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Comensales:");

        comensales.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setText("Tipo:");

        tipo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        tipo.setMaximumRowCount(3);

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setText("hh:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("mm:");

        preparacionHora.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        preparacionMinuto.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("hh:");

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("mm:");

        coccionHora.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        coccionMinuto.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(254, 254, 254));
        jLabel11.setText("Nuevo tipo:");

        txtNuevoTipo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preparacionHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preparacionMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevoTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comensales, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coccionHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coccionMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comensales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coccionMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(coccionHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(preparacionMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(preparacionHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(txtNuevoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        panelConsejo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Consejo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 3, 18), new java.awt.Color(254, 254, 254))); // NOI18N
        panelConsejo.setForeground(new java.awt.Color(254, 254, 254));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtConsejo.setColumns(20);
        txtConsejo.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        txtConsejo.setLineWrap(true);
        txtConsejo.setRows(3);
        txtConsejo.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtConsejo);

        javax.swing.GroupLayout panelConsejoLayout = new javax.swing.GroupLayout(panelConsejo);
        panelConsejo.setLayout(panelConsejoLayout);
        panelConsejoLayout.setHorizontalGroup(
            panelConsejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsejoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelConsejoLayout.setVerticalGroup(
            panelConsejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsejoLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Ingredientes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(254, 254, 254))); // NOI18N

        tablaAlimentos.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        tablaAlimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAlimentos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaAlimentos.getTableHeader().setResizingAllowed(false);
        tablaAlimentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaAlimentos);

        btnQuitarIngrediente.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnQuitarIngrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/garbage-2.png"))); // NOI18N
        btnQuitarIngrediente.setText("Quitar ingrediente");
        btnQuitarIngrediente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuitarIngrediente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(254, 254, 254));
        jLabel12.setText("Cantidad de Ingredientes:");

        lblCantidadIngredientes.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblCantidadIngredientes.setForeground(new java.awt.Color(254, 254, 254));
        lblCantidadIngredientes.setText("jLabel13");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitarIngrediente)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuitarIngrediente)
                    .addComponent(jLabel12)
                    .addComponent(lblCantidadIngredientes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Paso", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(254, 254, 254))); // NOI18N

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtPaso.setColumns(20);
        txtPaso.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        txtPaso.setLineWrap(true);
        txtPaso.setRows(4);
        txtPaso.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtPaso);

        btnCancelarPaso.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnCancelarPaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/rewind.png"))); // NOI18N
        btnCancelarPaso.setText("Cancelar paso");
        btnCancelarPaso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelarPaso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelarPaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPasoActionPerformed(evt);
            }
        });

        btnEnviarAprocedimiento.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnEnviarAprocedimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/fast-forward.png"))); // NOI18N
        btnEnviarAprocedimiento.setText("Enviar a Procedimiento");
        btnEnviarAprocedimiento.setToolTipText("Añadir paso al procedimiento");
        btnEnviarAprocedimiento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEnviarAprocedimiento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblPaso.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblPaso.setForeground(new java.awt.Color(254, 254, 254));
        lblPaso.setText("jLabel12");

        lbl.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lbl.setForeground(new java.awt.Color(254, 254, 254));
        lbl.setText("N°: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPaso, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarPaso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarAprocedimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl)
                    .addComponent(lblPaso)
                    .addComponent(btnEnviarAprocedimiento)
                    .addComponent(btnCancelarPaso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCenLayout = new javax.swing.GroupLayout(panelCen);
        panelCen.setLayout(panelCenLayout);
        panelCenLayout.setHorizontalGroup(
            panelCenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelConsejo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCenLayout.setVerticalGroup(
            panelCenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConsejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/back.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelar.setPreferredSize(new java.awt.Dimension(224, 40));

        btnGuardarReceta.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnGuardarReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/save.png"))); // NOI18N
        btnGuardarReceta.setText("Guardar receta");
        btnGuardarReceta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardarReceta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardarReceta.setPreferredSize(new java.awt.Dimension(224, 40));

        javax.swing.GroupLayout panelInferiorLayout = new javax.swing.GroupLayout(panelInferior);
        panelInferior.setLayout(panelInferiorLayout);
        panelInferiorLayout.setHorizontalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInferiorLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(btnGuardarReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInferiorLayout.setVerticalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelDerecho.setForeground(new java.awt.Color(254, 254, 254));
        panelDerecho.setPreferredSize(new java.awt.Dimension(250, 697));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Procedimiento", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(254, 254, 254))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(254, 254, 254));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtProcedimiento.setEditable(false);
        txtProcedimiento.setColumns(20);
        txtProcedimiento.setFont(new java.awt.Font("URW Gothic L", 1, 14)); // NOI18N
        txtProcedimiento.setLineWrap(true);
        txtProcedimiento.setRows(5);
        txtProcedimiento.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtProcedimiento);

        btnEliminarUltimoPaso.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnEliminarUltimoPaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/close.png"))); // NOI18N
        btnEliminarUltimoPaso.setText("Eliminar último paso");
        btnEliminarUltimoPaso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarUltimoPaso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnEliminarUltimoPaso, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarUltimoPaso)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                        .addComponent(panelIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelCen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelCen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDerecho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(panelIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.getAccessibleContext().setAccessibleParent(panelPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarPasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPasoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarPasoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevaReceta dialog = new NuevaReceta(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadirAlimentos;
    private javax.swing.JButton btnAñadirFoto;
    private javax.swing.JButton btnAñadirPaso;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarPaso;
    private javax.swing.JButton btnEliminarUltimoPaso;
    private javax.swing.JToggleButton btnEnviarAprocedimiento;
    private javax.swing.JButton btnGuardarReceta;
    private javax.swing.JButton btnQuitarFoto;
    private javax.swing.JButton btnQuitarIngrediente;
    private javax.swing.JSpinner coccionHora;
    private javax.swing.JSpinner coccionMinuto;
    private javax.swing.JSpinner comensales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblCantidadIngredientes;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblPaso;
    private javax.swing.JPanel panelCen;
    private javax.swing.JPanel panelConsejo;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelIzq;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JSpinner preparacionHora;
    private javax.swing.JSpinner preparacionMinuto;
    private javax.swing.JTable tablaAlimentos;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextArea txtConsejo;
    private javax.swing.JTextField txtNuevoTipo;
    private javax.swing.JTextArea txtPaso;
    private javax.swing.JTextArea txtProcedimiento;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
