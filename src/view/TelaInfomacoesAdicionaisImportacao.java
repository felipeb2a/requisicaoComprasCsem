package view;

import java.util.ArrayList;

/**
 *
 * @author felipe.ferreira
 */
public class TelaInfomacoesAdicionaisImportacao extends javax.swing.JFrame {
    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    
    public TelaInfomacoesAdicionaisImportacao() {
        initComponents();
        bloquearCodReq();
    }    
    //MOSTRAR TELA    
    public void mostrarTela (ArrayList Tela){
        
        this.setVisible(true);
        
        this.mostrarTela = Tela;        
    }
    
    //BLOQUEAR COD REQ
    public void bloquearCodReq(){
        txtCodRequisicao.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbImpostos = new javax.swing.JLabel();
        lbReferencia = new javax.swing.JLabel();
        txtCodRequisicao = new javax.swing.JTextField();
        txtImpostos = new javax.swing.JFormattedTextField();
        lbFrete = new javax.swing.JLabel();
        txtFrete = new javax.swing.JTextField();
        lbTaxaSiscomex = new javax.swing.JLabel();
        txtTaxaSiscomex = new javax.swing.JTextField();
        btCancelar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("INFORMAÇÕES ADICIONAIS - IMPORTAÇÃO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lbTitulo)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbImpostos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbImpostos.setText("Impostos:");

        lbReferencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbReferencia.setText("Referência:");

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtImpostos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbFrete.setText("Frete:");

        txtFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbTaxaSiscomex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTaxaSiscomex.setText("Taxa Siscomex:");

        txtTaxaSiscomex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSalvar.setText("Salvar");

        btLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btLimpar.setText("Limpar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbImpostos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTaxaSiscomex, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbFrete, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbReferencia, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(txtFrete, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTaxaSiscomex, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtImpostos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCodRequisicao, txtFrete, txtImpostos, txtTaxaSiscomex});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btLimpar, btSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbReferencia)
                    .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFrete)
                    .addComponent(txtFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTaxaSiscomex)
                    .addComponent(txtTaxaSiscomex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbImpostos)
                    .addComponent(txtImpostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btLimpar)
                    .addComponent(btSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbFrete, lbImpostos, lbReferencia, lbTaxaSiscomex, txtCodRequisicao, txtFrete, txtImpostos, txtTaxaSiscomex});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btLimpar, btSalvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaInfomacoesAdicionaisImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesAdicionaisImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesAdicionaisImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesAdicionaisImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInfomacoesAdicionaisImportacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbFrete;
    private javax.swing.JLabel lbImpostos;
    private javax.swing.JLabel lbReferencia;
    private javax.swing.JLabel lbTaxaSiscomex;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextField txtCodRequisicao;
    private javax.swing.JTextField txtFrete;
    private javax.swing.JFormattedTextField txtImpostos;
    private javax.swing.JTextField txtTaxaSiscomex;
    // End of variables declaration//GEN-END:variables
}
