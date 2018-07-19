

package vista.formularios;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelo.logica.Config;
import modelo.logica.Logica;
import modelo.logica.Logica.ImageFondo;



public class Preferencias extends javax.swing.JDialog {

    
    public Preferencias(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        
        Logica.ImageFondo image = new ImageFondo();
        image.setImage("/vista/imagenes/fondo_login.png");                
        this.setContentPane(image);
        
        this.setTitle("Configuración de preferencias");
        
        this.getOpcionSqlite().setSelected(true);
        
        this.getContentPane().add(panelPrincipal);
        
        this.getTxtUsername().setText(Config.getConfig("userMysql"));
        this.getTxtPassword().setText(Config.getConfig("passMysql"));
        this.getTxtHost().setText(Config.getConfig("hostMysql"));
        this.getTxtDatabase().setText(Config.getConfig("databaseMysql"));
        if(Config.getConfig("dataBase").equals("0")){
            this.desactivarCredencialesMysql();
            this.activarBotonRestaurar();
        }
        if(Config.getConfig("dataBase").equals("1")){
            this.activarCredencialesMysql();
            this.desactivarBotonRestaurar();
        }
        
        this.setLocationRelativeTo(null);
    }

    
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnRestaurar() {
        return btnRestaurar;
    }

    public void setBtnRestaurar(JButton btnRestaurar) {
        this.btnRestaurar = btnRestaurar;
    }
    
    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    public JRadioButton getOpcionMysql() {
        return opcionMysql;
    }

    public void setOpcionMysql(JRadioButton opcionMysql) {
        this.opcionMysql = opcionMysql;
    }

    
    public JRadioButton getOpcionSqlite() {
        return opcionSqlite;
    }

    public void setOpcionSqlite(JRadioButton opcionSqlite) {
        this.opcionSqlite = opcionSqlite;
    }

    public JTextField getTxtDatabase() {
        return txtDatabase;
    }

    public void setTxtDatabase(JTextField txtDatabase) {
        this.txtDatabase = txtDatabase;
    }

    public JTextField getTxtHost() {
        return txtHost;
    }

    public void setTxtHost(JTextField txtHost) {
        this.txtHost = txtHost;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JTextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public void activarCredencialesMysql(){
       this.getTxtUsername().setEnabled(true);
       this.getTxtPassword().setEnabled(true);
       this.getTxtHost().setEnabled(true);
       this.getTxtDatabase().setEnabled(true);
    }
    public void desactivarCredencialesMysql(){
       this.getTxtUsername().setEnabled(false);
       this.getTxtPassword().setEnabled(false);
       this.getTxtHost().setEnabled(false);
       this.getTxtDatabase().setEnabled(false);
    }
    public void limpiarCredencialesMysql(){
       this.getTxtUsername().setText("");
       this.getTxtPassword().setText("");
       this.getTxtHost().setText("");
       this.getTxtDatabase().setText("");
    }
    
    public void desactivarBotonRestaurar(){
        this.getBtnRestaurar().setEnabled(false);
    }
    public void activarBotonRestaurar(){
        this.getBtnRestaurar().setEnabled(true);
    }
    
    public void cerrarPreferencias(){
        this.setVisible(false);
    }
    public void abrirPreferencias(){
        this.setVisible(true);
    }
                  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoOpciones = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panelBaseDeDatos = new javax.swing.JPanel();
        opcionMysql = new javax.swing.JRadioButton();
        opcionSqlite = new javax.swing.JRadioButton();
        panelCredencialesMysql = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        txtDatabase = new javax.swing.JTextField();
        btnRestaurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnAceptar.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnAceptar.setText("Aceptar");

        btnCancelar.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        panelBaseDeDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Base de datos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(254, 254, 254))); // NOI18N

        grupoOpciones.add(opcionMysql);
        opcionMysql.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        opcionMysql.setForeground(new java.awt.Color(254, 254, 254));
        opcionMysql.setText("MySQL (Requiere servidor instalado)");
        opcionMysql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMysqlActionPerformed(evt);
            }
        });

        grupoOpciones.add(opcionSqlite);
        opcionSqlite.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        opcionSqlite.setForeground(new java.awt.Color(254, 254, 254));
        opcionSqlite.setText("SQLite (Portable)");

        panelCredencialesMysql.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(254, 254, 254), new java.awt.Color(254, 254, 254)), "Credenciales MySql", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(254, 254, 254))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("UserName:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Password;");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Host:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setText("DataBase:");

        txtUsername.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        txtHost.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        txtDatabase.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelCredencialesMysqlLayout = new javax.swing.GroupLayout(panelCredencialesMysql);
        panelCredencialesMysql.setLayout(panelCredencialesMysqlLayout);
        panelCredencialesMysqlLayout.setHorizontalGroup(
            panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCredencialesMysqlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword)
                    .addComponent(txtHost)
                    .addComponent(txtDatabase))
                .addContainerGap())
        );
        panelCredencialesMysqlLayout.setVerticalGroup(
            panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCredencialesMysqlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCredencialesMysqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBaseDeDatosLayout = new javax.swing.GroupLayout(panelBaseDeDatos);
        panelBaseDeDatos.setLayout(panelBaseDeDatosLayout);
        panelBaseDeDatosLayout.setHorizontalGroup(
            panelBaseDeDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseDeDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBaseDeDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcionMysql)
                    .addComponent(opcionSqlite))
                .addGap(18, 18, 18)
                .addComponent(panelCredencialesMysql, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBaseDeDatosLayout.setVerticalGroup(
            panelBaseDeDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseDeDatosLayout.createSequentialGroup()
                .addComponent(panelCredencialesMysql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBaseDeDatosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(opcionMysql)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(opcionSqlite)
                .addGap(53, 53, 53))
        );

        btnRestaurar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnRestaurar.setText("Restaurar configuración de fábrica");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(btnRestaurar)
                        .addGap(94, 94, 94)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBaseDeDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBaseDeDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)
                    .addComponent(btnRestaurar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionMysqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMysqlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionMysqlActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Preferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Preferencias dialog = new Preferencias(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.ButtonGroup grupoOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton opcionMysql;
    private javax.swing.JRadioButton opcionSqlite;
    private javax.swing.JPanel panelBaseDeDatos;
    private javax.swing.JPanel panelCredencialesMysql;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
