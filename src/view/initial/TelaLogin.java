package view.initial;

import view.list.TelaListaRequisicaoAprovacao;
import view.list.TelaListaRequisicao;
import dao.UsuarioDAO;
import java.awt.Image;
import java.awt.Toolkit;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Icone;
import model.Log;
import model.LogArquivoTexto;
import model.Usuario;
import view.list.TelaListaSolicitacoes;

/**
 *
 * @author felipe.ferreira
 */
public class TelaLogin extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private String nameDb;
    private Logger logger = null;

    public TelaLogin() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    //LOGGER
    public Logger Definirlogger() {
        Log log = new Log();
        try {
            logger = log.pathLog(TelaLogin.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex1);
        }

        return logger;
    }

    //ALTERAR ICONE JAVA
    Icone icon = new Icone();

    public void icone() {
        URL url = icon.getIcone(nameDb);
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);
    }

    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {
        this.setVisible(true);
        this.mostrarTela = Tela;
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException {
        this.nameDb = nameDb;
        ListarUsuario(nameDb);
        icone();
    }

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //LISTAR USUARIO COMBOBOX
    public void ListarUsuario(String nameDb) throws SQLException, ClassNotFoundException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario;
            List user = dao.ObterNome(nameDb);
            Iterator it = user.iterator();

            while (it.hasNext()) {
                usuario = (Usuario) it.next();
                String nome = usuario.getLogin();

                //INSERIR NOME
                cbLogin.addItem(nome);
            }
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
        }
    }

    //ACTION BUTTON ENTRAR
    public void Entrar() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (cbLogin.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar usuário\n";
            valida = false;
        }
        if (new String(txtPasswod.getPassword()).length() == 0) {
            msgErro += "- Favor inserir uma senha válida\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {

            Usuario usuarioInformado = new Usuario();
            usuarioInformado.setLogin((String) cbLogin.getSelectedItem());
            usuarioInformado.setSenha(new String(txtPasswod.getPassword()));
            UsuarioDAO dao = new UsuarioDAO();

            try {
                Usuario usuario = dao.localizarUsuario(usuarioInformado, nameDb);

                //LOG
                LogArquivoTexto log = new LogArquivoTexto();
                String userLogin = (String) cbLogin.getSelectedItem();
                String user = "Usuário logado: " + userLogin;

                String[] nivel = {"Administrador", "Aprovador Financeiro", "Aprovador Tecnico", "Compras", "Administrativo", "Financeiro", "RH"};
                if (usuario == null) {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos");
                    cbLogin.setSelectedItem("Selecione");
                    txtPasswod.setText("");
                } else if (usuario.getNivel().getNomeNivel().equals(nivel[0])) {
                    JOptionPane.showMessageDialog(this, "Bem Vindo(a) " + usuario.getLogin());
                    //LOG
                    log.escrever(userLogin, user, nameDb);
                    //TELA ADM
                    TelaAdministrador tela = new TelaAdministrador();
                    this.setVisible(false);
                    tela.nameDb(nameDb);
                    tela.mostrarTela(mostrarTela);
                } else if (usuario.getNivel().getNomeNivel().equals(nivel[1])) {
                    JOptionPane.showMessageDialog(this, "Bem Vindo(a) " + usuario.getLogin());
                    //LOG
                    log.escrever(userLogin, user, nameDb);
                    //TELA APRV ADM
                    TelaListaRequisicaoAprovacao tela = new TelaListaRequisicaoAprovacao();
                    this.setVisible(false);
                    tela.ObterLogin(usuario);
                    tela.nameDb(nameDb);
                    tela.mostrarTela(mostrarTela);
                    tela.Relatorio();
                } else if (usuario.getNivel().getNomeNivel().equals(nivel[2])) {
                    JOptionPane.showMessageDialog(this, "Bem Vindo(a) " + usuario.getLogin());
                    //LOG
                    log.escrever(userLogin, user, nameDb);
                    //TELA APROV TEC
                    TelaListaRequisicaoAprovacao tela = new TelaListaRequisicaoAprovacao();
                    this.setVisible(false);
                    tela.ObterLogin(usuario);
                    tela.nameDb(nameDb);
                    tela.mostrarTela(mostrarTela);
                    tela.Relatorio();
                } else {
                    JOptionPane.showMessageDialog(this, "Bem Vindo(a) " + usuario.getLogin());
                    //LOG
                    log.escrever(userLogin, user, nameDb);
                    //TELA SELECIONE LISTA
                    TelaSelecioneLista tela = new TelaSelecioneLista();
                    this.setVisible(false);
                    tela.ObterLogin(usuario);
                    tela.nameDb(nameDb);
                    tela.mostrarTela(mostrarTela);
                }
            } catch (NullPointerException exN) {

                JOptionPane.showMessageDialog(this, "Login não Efetuado!");
                txtPasswod.setText("");
            } catch (Exception ex) {
                logger = Definirlogger();
                logger.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                //LOG
//                LogArquivoTexto log = new LogArquivoTexto();
//                String classe = TelaLogin.class.getName();
//                String texto = classe + "\n" + "ERRO: " + ex;
//                try {
//                    log.escreverGeral(texto, nameDb);
//                } catch (Exception ex1) {
//                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex1);
//                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneLogin = new javax.swing.JPanel();
        txtPasswod = new javax.swing.JPasswordField();
        jLabelLogin = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        cbLogin = new javax.swing.JComboBox<>();
        btEntrar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        txtPasswod.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabelLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelLogin.setText("Login:");

        jLabelSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelSenha.setText("Senha:");

        cbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbLogin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btEntrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEntrar.setText("Entrar");
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });
        btEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btEntrarKeyPressed(evt);
            }
        });

        btLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneLoginLayout = new javax.swing.GroupLayout(paneLogin);
        paneLogin.setLayout(paneLoginLayout);
        paneLoginLayout.setHorizontalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLoginLayout.createSequentialGroup()
                        .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLogin, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbLogin, 0, 237, Short.MAX_VALUE)
                            .addComponent(txtPasswod)))
                    .addGroup(paneLoginLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btEntrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)))
                .addContainerGap())
        );

        paneLoginLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btEntrar, btLimpar});

        paneLoginLayout.setVerticalGroup(
            paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbLogin))
                .addGap(11, 11, 11)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPasswod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLimpar))
                .addContainerGap())
        );

        paneLoginLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btEntrar, btLimpar});

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setText("LOGIN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(paneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        try {
            Entrar();

        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
            //LOG
//            LogArquivoTexto log = new LogArquivoTexto();
//            String classe = TelaLogin.class.getName();
//            String texto = classe + "\n" + "ERRO: " + ex;
//            try {
//                log.escreverGeral(texto, nameDb);
//            } catch (Exception ex1) {
//                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }//GEN-LAST:event_btEntrarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        cbLogin.setSelectedItem("Selecione");
        txtPasswod.setText("");
    }//GEN-LAST:event_btLimparActionPerformed

    private void btEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btEntrarKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            try {
                Entrar();
            } catch (Exception ex) {
                logger = Definirlogger();
                logger.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
            }
        }
    }//GEN-LAST:event_btEntrarKeyPressed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaLogin().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEntrar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JComboBox<String> cbLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel paneLogin;
    private javax.swing.JPasswordField txtPasswod;
    // End of variables declaration//GEN-END:variables
}
