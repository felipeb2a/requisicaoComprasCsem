package view.initial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class TelaSelecioneSistema extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private int armazenaAno;

    public TelaSelecioneSistema() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {
        this.setVisible(true);
        this.mostrarTela = Tela;
    }

    //ARMAZENA ANO
    public void armazenaAno(Integer armazenaAno) {
        this.armazenaAno = armazenaAno;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneLogin = new javax.swing.JPanel();
        btSunewGeradores = new javax.swing.JButton();
        btCsem = new javax.swing.JButton();
        btSunew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        btSunewGeradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_sunew_geradores.png"))); // NOI18N
        btSunewGeradores.setContentAreaFilled(false);
        btSunewGeradores.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/over/logo_sunew_geradores_over.png"))); // NOI18N
        btSunewGeradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSunewGeradoresActionPerformed(evt);
            }
        });

        btCsem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_csem.png"))); // NOI18N
        btCsem.setContentAreaFilled(false);
        btCsem.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/over/logo_csem_over.png"))); // NOI18N
        btCsem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCsemActionPerformed(evt);
            }
        });

        btSunew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        btSunew.setContentAreaFilled(false);
        btSunew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/over/logo_over.png"))); // NOI18N
        btSunew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSunewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneLoginLayout = new javax.swing.GroupLayout(paneLogin);
        paneLogin.setLayout(paneLoginLayout);
        paneLoginLayout.setHorizontalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btCsem, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSunew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSunewGeradores)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneLoginLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCsem, btSunew, btSunewGeradores});

        paneLoginLayout.setVerticalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSunewGeradores)
                    .addComponent(btCsem, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSunew))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        paneLoginLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCsem, btSunew, btSunewGeradores});

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setText("SELECIONE SISTEMA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(paneLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(464, 464, 464)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSunewGeradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSunewGeradoresActionPerformed
        try {
            String dbSunewGeradores;
            dbSunewGeradores = "sunewgeradores_" + armazenaAno;
            //TELA SELECIONE SISTEMA
            TelaLogin tela = new TelaLogin();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
            tela.nameDb(dbSunewGeradores);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSunewGeradoresActionPerformed

    private void btCsemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCsemActionPerformed
        try {
            String dbCsem;
            dbCsem = "csem_" + armazenaAno;
            //TELA SELECIONE SISTEMA
            TelaLogin tela = new TelaLogin();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
            tela.nameDb(dbCsem);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCsemActionPerformed

    private void btSunewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSunewActionPerformed
        try {
            String dbSunew;
            dbSunew = "sunew_" + armazenaAno;
            //TELA SELECIONE SISTEMA
            TelaLogin tela = new TelaLogin();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
            tela.nameDb(dbSunew);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSunewActionPerformed

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
            java.util.logging.Logger.getLogger(TelaSelecioneSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSelecioneSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSelecioneSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSelecioneSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaSelecioneSistema().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaSelecioneSistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCsem;
    private javax.swing.JButton btSunew;
    private javax.swing.JButton btSunewGeradores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel paneLogin;
    // End of variables declaration//GEN-END:variables
}
