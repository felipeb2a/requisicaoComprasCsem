package view.changes;

import view.list.TelaListaRequisitantes;
import dao.NiveisDAO;
import dao.UsuarioDAO;
import java.awt.Image;
import java.awt.Toolkit;
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
import model.Criptografia;
import model.Icone;
import model.LogArquivoTexto;
import model.Niveis;
import model.Requisitante;
import model.Usuario;
import view.TelaInfomacoesFinanceiras;
import view.initial.TelaLogin;
import view.initial.TelaSelecioneAno;

/**
 *
 * @author felipe.ferreira
 */
public class TelaAlterarRequisitantes extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private String nameDb;

    public TelaAlterarRequisitantes() throws SQLException, ClassNotFoundException, ClassNotFoundException {
        initComponents();
        txtCodigo.setEnabled(false);
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
        ListarNiveis(nameDb);
        icone();
    }

    //LISTAR NIVEIS COMBOBOX
    public void ListarNiveis(String nameDb) throws SQLException, ClassNotFoundException {
        try {
            NiveisDAO dao = new NiveisDAO();
            Niveis niveis;
            List nivel = dao.ObterNivel(nameDb);
            Iterator it = nivel.iterator();

            while (it.hasNext()) {
                niveis = (Niveis) it.next();
                String nome = niveis.getNomeNivel();

                //INSERIR NOME
                cbNivel.addItem(nome);
            }
        } catch (SQLException ex) {

            if (ex.getMessage().contains(new String("The Network Adapter could not establish the connection"))) {

                JOptionPane.showMessageDialog(this, "Não foi possivel Conectar Com o Banco de Dados!");

            }

            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(this, "Erro de Desconhecido!");

            //ex.printStackTrace();
        } catch (Exception ex) {
            //LOG
            LogArquivoTexto log = new LogArquivoTexto();
            String classe = TelaInfomacoesFinanceiras.class.getName();
            String texto = classe + "\n" + "ERRO: " + ex;
            try {
                log.escreverGeral(texto, nameDb);
            } catch (Exception ex1) {
                Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void carregarUsuario(Usuario usuario) {
        this.setVisible(true);
        txtCodigo.setText(Integer.toString(usuario.getId()));
        txtNome.setText(usuario.getLogin());
        txtPassword.setText(usuario.getSenha());
        txtEmail.setText(usuario.getEmail());

        //NIVEIS
        cbNivel.setSelectedItem(usuario.getNivel().getNomeNivel());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        cbNivel = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        lbSenha = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        btAlterar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbNome1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setText("ALTERAR REQUISITANTE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(239, 239, 239))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome.setText("Nome requisitante:");

        cbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSenha.setText("Senha:");

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNivel.setText("Nível");

        btAlterar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbEmail.setText("E-mail:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNome1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome1.setText("Codigo Usuario");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNome)
                            .addComponent(lbSenha)
                            .addComponent(lbNivel)
                            .addComponent(lbEmail)
                            .addComponent(lbNome1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword)
                            .addComponent(cbNivel, 0, 647, Short.MAX_VALUE)
                            .addComponent(txtNome)
                            .addComponent(txtCodigo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail))
                .addGap(29, 29, 29)
                .addComponent(btAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar)
                .addContainerGap(74, Short.MAX_VALUE))
        );

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

        jMenu5.setText("Voltar");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        btVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        jMenu5.add(btVoltar);

        menu.add(jMenu5);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        btVoltarActionPerformed(evt);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (txtNome.getText().length() == 0) {
            msgErro += "- Favor preencher campo nome requisitante\n";
            valida = false;
        }
        if (cbNivel.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar nível\n";
            valida = false;
        }
        if (new String(txtPassword.getPassword()).length() == 0) {
            msgErro += "- Favor preencher campo senha\n";
            valida = false;
        } else if (new String(txtPassword.getPassword()).length() < 5) {
            msgErro += "- Favor inserir uma senha com mais de 5 caracteres\n";
            valida = false;
        } else {
            valida = true;
        }
        if (txtEmail.getText().length() == 0) {
            msgErro += "- Favor preencher campo e-mail\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {

            try {

                //Criptografar senha
                Criptografia cript = new Criptografia();
                String senha = new String(txtPassword.getPassword());
                senha = cript.criptografaSHA256(senha);

                Usuario usuario = new Usuario();
                UsuarioDAO daoUsuario = new UsuarioDAO();

                usuario.setId(new Integer(txtCodigo.getText()));
                usuario.setLogin(txtNome.getText());
                usuario.setSenha(senha);
                Niveis nivel = new Niveis();
                NiveisDAO daoNivel = new NiveisDAO();
                nivel.setNomeNivel((String) cbNivel.getSelectedItem());

                //BUSCAR ID NIVEL
                nivel = daoNivel.localizarNiveis(nivel, nameDb);

                usuario.setNivel(nivel);
                usuario.setEmail(txtEmail.getText());

                //CONFIRMAR DADOS
                Object[] options = {"Confirmar", "Cancelar"};
                int opcao = JOptionPane.showOptionDialog(null, "Clique Confirmar para continuar! \n" + "\n NOME: " + usuario.getLogin()
                        + "\n NÍVEL: " + cbNivel.getSelectedItem() + "\n E-MAIL: " + usuario.getEmail(), "CONFIRMAÇÃO DOS DADOS", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (opcao == 0) {
                    try {
                        daoUsuario.alterar(usuario, nameDb);
                        btCancelarActionPerformed(evt);

                    } catch (SQLException ex) {

                        if (ex.getMessage().contains(new String("The Network Adapter could not establish the connection"))) {

                            JOptionPane.showMessageDialog(this, "Não foi possivel Conectar Com o Banco de Dados!");

                        } else if (ex.getMessage().contains(new String("ORA-00001"))) {

                            JOptionPane.showMessageDialog(this, "Este Nome de usuário já está sendo utilizado, tente cadastrar com outro Nome!");

                        } else if (ex.getMessage().contains(new String("ORA-00933"))) {

                            JOptionPane.showMessageDialog(this, "Este registro não consta em nosso Banco de Dados!");

                        } else if (ex.getMessage().contains(new String("Duplicate entry"))) {

                            JOptionPane.showMessageDialog(this, "Este Nome de usuário já está sendo utilizado, tente cadastrar com outro Nome!");

                        } else {

                            JOptionPane.showMessageDialog(this, "Erro Desconhecido!");

                            ex.printStackTrace();
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(this, "Altere os dados com erros para finalizar o cadastro!");
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                //LOG
                LogArquivoTexto log = new LogArquivoTexto();
                String classe = TelaInfomacoesFinanceiras.class.getName();
                String texto = classe + "\n" + "ERRO: " + ex;
                try {
                    log.escreverGeral(texto, nameDb);
                } catch (Exception ex1) {
                    Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

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
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            TelaSelecioneAno tela = new TelaSelecioneAno();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        try {
            //TELA ADM
            TelaListaRequisitantes tela = new TelaListaRequisitantes();
            this.setVisible(false);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaAlterarRequisitantes().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaAlterarRequisitantes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JComboBox<String> cbNivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNome1;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
