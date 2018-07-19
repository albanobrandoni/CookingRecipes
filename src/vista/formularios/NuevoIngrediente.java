
package vista.formularios;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import modelo.logica.Logica;
import modelo.logica.Logica.ImageFondo;


public class NuevoIngrediente extends javax.swing.JDialog {

    
    public NuevoIngrediente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Logica.ImageFondo image = new ImageFondo();
        image.setImage("/vista/imagenes/tabla.jpg");
        this.setContentPane(image);
        
        this.getContentPane().add(panelPrincipal);
        
        this.setTitle("Mi lista de almientos");
        
        this.setLocationRelativeTo(null);
        
        
    }

    public JTextField getTxtNuevoAlimento() {
        return txtNuevoAlimento;
    }

    public void setTxtNuevoAlimento(JTextField txtNuevoAlimento) {
        this.txtNuevoAlimento = txtNuevoAlimento;
    }

   
    public JButton getBtnAgregarAlimento() {
        return btnAgregarAlimento;
    }

    public void setBtnAgregarAlimento(JButton btnAgregarAlimento) {
        this.btnAgregarAlimento = btnAgregarAlimento;
    }

    public JButton getBtnEliminarAlimento() {
        return btnEliminarAlimento;
    }

    public void setBtnEliminarAlimento(JButton btnEliminarAlimento) {
        this.btnEliminarAlimento = btnEliminarAlimento;
    }

    public JComboBox<String> getComboAlimentos() {
        return comboAlimentos;
    }

    public void setComboAlimentos(JComboBox<String> comboAlimentos) {
        this.comboAlimentos = comboAlimentos;
    }
               
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboAlimentos = new javax.swing.JComboBox<>();
        txtNuevoAlimento = new javax.swing.JTextField();
        btnEliminarAlimento = new javax.swing.JButton();
        btnAgregarAlimento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Purisa", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mis alimentos:");

        comboAlimentos.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        comboAlimentos.setMaximumRowCount(5);
        comboAlimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAlimentosActionPerformed(evt);
            }
        });

        txtNuevoAlimento.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        btnEliminarAlimento.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnEliminarAlimento.setText("Eliminar");
        btnEliminarAlimento.setPreferredSize(new java.awt.Dimension(68, 34));
        btnEliminarAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlimentoActionPerformed(evt);
            }
        });

        btnAgregarAlimento.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnAgregarAlimento.setText("Agregar");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarAlimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboAlimentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarAlimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNuevoAlimento))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(btnEliminarAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarAlimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboAlimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAlimentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAlimentosActionPerformed

    private void btnEliminarAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAlimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarAlimentoActionPerformed

    
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
            java.util.logging.Logger.getLogger(NuevoIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoIngrediente dialog = new NuevoIngrediente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarAlimento;
    private javax.swing.JButton btnEliminarAlimento;
    private javax.swing.JComboBox<String> comboAlimentos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtNuevoAlimento;
    // End of variables declaration//GEN-END:variables
}
