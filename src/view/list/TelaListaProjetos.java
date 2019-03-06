package view.list;

import dao.ProjetosDAO;
import dao.UsuarioDAO;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Icone;
import model.Log;
import model.LogArquivoTexto;
import model.Projetos;
import model.Usuario;
import view.TelaInfomacoesFinanceiras;
import view.initial.TelaAdministrador;
import view.TelaProjetos;
import view.changes.TelaAlterarProjetos;
import view.changes.TelaAlterarRequisitantes;
import view.initial.TelaLogin;
import view.initial.TelaSelecioneAno;

/**
 *
 * @author felipe.ferreira
 */
public class TelaListaProjetos extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private String nameDb;
    private Logger logger = null;

    public TelaListaProjetos() {
        initComponents();
        AlteraFontTable();
    }

    //LOGGER
    public Logger Definirlogger() {
        Log log = new Log();
        try {
            logger = log.pathLog(TelaListaProjetos.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(TelaListaProjetos.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(TelaListaProjetos.class.getName()).log(Level.SEVERE, null, ex1);
        }

        return logger;
    }

    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {

        this.setVisible(true);

        this.mostrarTela = Tela;
    }

    //ALTERAR ICONE JAVA
    Icone icon = new Icone();

    public void icone() {
        URL url = icon.getIcone(nameDb);
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException {
        this.nameDb = nameDb;
        jMenuEmpresa.setText(nameDb);
        Relatorio();
        icone();
    }

    public void AlteraFontTable() {
        JTableHeader cabecalhoFornecedor = tbProjetos.getTableHeader();
        cabecalhoFornecedor.setFont(new Font("Tahoma", Font.BOLD, 18));
    }

    //RELATORIO
    public void Relatorio() {
        try {

            ProjetosDAO dao = new ProjetosDAO();
            List lista = dao.ObterProjeto(nameDb);
            DefaultTableModel model = (DefaultTableModel) tbProjetos.getModel();
            model.setNumRows(0);

            for (Iterator it = lista.iterator(); it.hasNext();) {

                Projetos projeto = (Projetos) it.next();
                Object linha[]
                        = {projeto.getId(), projeto.getProjeto()};

                model.addRow(linha);
            }

        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
            //LOG
//            LogArquivoTexto log = new LogArquivoTexto();
//            String classe = TelaInfomacoesFinanceiras.class.getName();
//            String texto = classe + "\n" + "ERRO: " + ex;
//            try {
//                log.escreverGeral(texto, nameDb);
//            } catch (Exception ex1) {
//                Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }

    public void Visualiza() {
        try {
            TelaAlterarProjetos tela = new TelaAlterarProjetos();

            // recuperando o numero da linha selecionada
            int numeroLinha = tbProjetos.getSelectedRow();

            Projetos idInformado = new Projetos();

            Integer id = (Integer) tbProjetos.getValueAt(numeroLinha, 0);
            idInformado.setId(id);

            ProjetosDAO dao = new ProjetosDAO();
            Projetos projeto = dao.LocalizarParaAlterar(idInformado, nameDb);

            tela.nameDb(nameDb);
            tela.carregarProjeto(projeto);
            this.setVisible(false);

        } catch (Exception ex) {
            if (ex.getMessage().contains(new String("-1"))) {

                JOptionPane.showMessageDialog(this, "Favor Selecionar uma linha da tabela para ser alterada!");

            } else {
                logger = Definirlogger();
                logger.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                //LOG
//                LogArquivoTexto log = new LogArquivoTexto();
//                String classe = TelaInfomacoesFinanceiras.class.getName();
//                String texto = classe + "\n" + "ERRO: " + ex;
//                try {
//                    log.escreverGeral(texto, nameDb);
//                } catch (Exception ex1) {
//                    Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
//                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProjetos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jProjeto = new javax.swing.JMenu();
        btAdd = new javax.swing.JMenuItem();
        btExcluir = new javax.swing.JMenuItem();
        btEditar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbProjetos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbProjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Projeto", "Projeto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProjetosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProjetos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setText("PROJETOS");

        menu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jMenuEmpresa.setForeground(new java.awt.Color(0, 102, 255));
        jMenuEmpresa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        menu.add(jMenuEmpresa);

        jMenu3.setText("File");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem2.setText("Trocar Usuário");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem3.setText("Trocar Sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        menu.add(jMenu3);

        jProjeto.setText("Projetos");
        jProjeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btAdd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        btAdd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jProjeto.add(btAdd);

        btExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        btExcluir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jProjeto.add(btExcluir);

        btEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        btEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        jProjeto.add(btEditar);

        menu.add(jProjeto);

        jMenu5.setText("Voltar");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVoltar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        btVoltar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVoltar1.setText("Voltar");
        btVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar1ActionPerformed(evt);
            }
        });
        jMenu5.add(btVoltar1);

        menu.add(jMenu5);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            TelaLogin tela = new TelaLogin();
            this.setVisible(false);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            TelaSelecioneAno tela = new TelaSelecioneAno();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar1ActionPerformed
        try {
            //TELA ADM
            TelaAdministrador tela = new TelaAdministrador();
            this.setVisible(false);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
        }
    }//GEN-LAST:event_btVoltar1ActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        try {
            //TELA PROJ
            TelaProjetos tela = new TelaProjetos();
            this.setVisible(false);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
            //LOG
//            LogArquivoTexto log = new LogArquivoTexto();
//            String classe = TelaInfomacoesFinanceiras.class.getName();
//            String texto = classe + "\n" + "ERRO: " + ex;
//            try {
//                log.escreverGeral(texto, nameDb);
//            } catch (Exception ex1) {
//                Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbProjetos.getModel();
        int rowSelect = tbProjetos.getSelectedRow();
        if (rowSelect >= 0) {

            // recuperando o numero da linha selecionada
            int linha = tbProjetos.getSelectedRow();

            String nome = (String) tbProjetos.getValueAt(linha, 1);

            //CONFIRMAR DADOS
            Object[] options = {"Confirmar", "Cancelar"};
            int opcao = JOptionPane.showOptionDialog(null, "Clique confirmar para EXCLUIR! " + "\n NOME: " + nome + "\n", "EXCLUIR!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (opcao == 0) {
                try {
                    // recuperando o numero da linha selecionada
                    int numeroLinha = tbProjetos.getSelectedRow();

                    // buscando valor na linha selecionada e na coluna 0 (onde está o id)
                    Integer id = (Integer) tbProjetos.getValueAt(numeroLinha, 0);

                    Projetos projetoRemovido = new Projetos();
                    projetoRemovido.setId(id);

                    //Removendo
                    ProjetosDAO dao = new ProjetosDAO();

                    dao.remover(projetoRemovido, nameDb);

                    JOptionPane.showMessageDialog(this, "Registro removido com sucesso!");

                    // retirando a linha do grid
                    model.removeRow(numeroLinha);
                } catch (Exception ex) {

                    if (ex.getMessage().contains(new String("-1"))) {

                        JOptionPane.showMessageDialog(this, "Favor Selecionar uma linha da tabela para ser removida!");

                    } else {
                        logger = Definirlogger();
                        logger.log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                        //LOG
//                        LogArquivoTexto log = new LogArquivoTexto();
//                        String classe = TelaInfomacoesFinanceiras.class.getName();
//                        String texto = classe + "\n" + "ERRO: " + ex;
//                        try {
//                            log.escreverGeral(texto, nameDb);
//                        } catch (Exception ex1) {
//                            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
//                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(this, "Cancelado");
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        Visualiza();
    }//GEN-LAST:event_btEditarActionPerformed

    private void tbProjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProjetosMouseClicked
        int click = 0;
        if (click == 0) {
            int count = evt.getClickCount();
            count++;
            if (count == 3) {
                Visualiza();
            }
        }
    }//GEN-LAST:event_tbProjetosMouseClicked

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
            java.util.logging.Logger.getLogger(TelaListaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaProjetos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btAdd;
    private javax.swing.JMenuItem btEditar;
    private javax.swing.JMenuItem btExcluir;
    private javax.swing.JMenuItem btVoltar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenu jProjeto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTable tbProjetos;
    // End of variables declaration//GEN-END:variables
}
