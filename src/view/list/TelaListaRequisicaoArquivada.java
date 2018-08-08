package view.list;

import dao.RequisicoesDAO;
import dao.StatusRequisicaoDAO;
import dao.UsuarioDAO;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.Icone;
import model.LogArquivoTexto;
import model.Requisicoes;
import model.StatusRequisicao;
import model.Usuario;
import view.TelaInfomacoesFinanceiras;
import view.TelaRequisicaoCompra;
import view.TelaRequisicaoNova;
import view.initial.TelaAlterarSenha;
import view.initial.TelaLogin;
import view.initial.TelaSelecioneAno;
import view.initial.TelaSelecioneLista;

/**
 *
 * @author felipe.ferreira
 */
public class TelaListaRequisicaoArquivada extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    private String nameDb;

    public TelaListaRequisicaoArquivada() {
        initComponents();
        AlteraFontTable();
        MaximizeTela();
    }

    //MOSTRAR TELA    
    public void mostrarTela(ArrayList Tela) {
        this.setVisible(true);
        this.mostrarTela = Tela;
    }

    //LOGIN
    public void ObterLogin(Usuario login) {

        this.obterLogin = login;

        lbLogin.setText(login.getLogin());
        lbNivel.setText(login.getNivel().getNomeNivel());
        int id = login.getId();

        this.setVisible(true);
        lbLogin.setVisible(false);
        lbNivel.setVisible(false);
        lbUsuario.setVisible(false);
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
        JTableHeader cabecalhoFornecedor = tbRequisicao.getTableHeader();
        cabecalhoFornecedor.setFont(new Font("Tahoma", Font.BOLD, 18));
    }

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //RELATORIO
    public void Relatorio() {
        try {
            ListarCombobox();
            RequisicoesDAO requisicaoDAO = new RequisicoesDAO();
            String usuario = lbLogin.getText();
            List listaRequisicao = requisicaoDAO.localizarRequisicoesArquivadaUser(usuario, nameDb);
            DefaultTableModel model = (DefaultTableModel) tbRequisicao.getModel();
            model.setNumRows(0);

            for (Iterator it = listaRequisicao.iterator(); it.hasNext();) {

                Requisicoes requisicoes = (Requisicoes) it.next();
                Object linha[]
                        = {requisicoes.getId(), requisicoes.getStatusRequisicao().getStatusRequisicao(),
                            requisicoes.getDataCriacao(), requisicoes.getDataSolicitacao(), requisicoes.getUsuario().getLogin(),
                            requisicoes.getProjetos().getProjeto(), requisicoes.getTipoRequisicao().getTipoRequisicao()};

                model.addRow(linha);
            }

        } catch (SQLException ex) {

            if (ex.getMessage().contains(new String("The Network Adapter could not establish the connection"))) {

                JOptionPane.showMessageDialog(this, "Não foi possivel Conectar Com o Banco de Dados!");

            } else {

                JOptionPane.showMessageDialog(this, "Erro Desconhecido!");

                ex.printStackTrace();
            }

        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(this, "Erro de Desconhecido!");

            ex.printStackTrace();
        } catch (NullPointerException ex) {

            JOptionPane.showMessageDialog(this, "Null Pointer!");

            ex.printStackTrace();
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

    //FILTRO
    public void Filtro() {
        try {
            //ListarCombobox();
            RequisicoesDAO requisicaoDAO = new RequisicoesDAO();
            String usuario = (String) cbRequisitante.getSelectedItem();
            String status = (String) cbStatus.getSelectedItem();
            List listaRequisicao = requisicaoDAO.FiltrarRequisicoesUserStatus(usuario, status, nameDb);
            DefaultTableModel model = (DefaultTableModel) tbRequisicao.getModel();
            model.setNumRows(0);

            for (Iterator it = listaRequisicao.iterator(); it.hasNext();) {

                Requisicoes requisicoes = (Requisicoes) it.next();
                Object linha[]
                        = {requisicoes.getId(), requisicoes.getStatusRequisicao().getStatusRequisicao(),
                            requisicoes.getDataCriacao(), requisicoes.getDataSolicitacao(), requisicoes.getUsuario().getLogin(),
                            requisicoes.getProjetos().getProjeto(), requisicoes.getTipoRequisicao().getTipoRequisicao()};

                model.addRow(linha);
            }

        } catch (SQLException ex) {

            if (ex.getMessage().contains(new String("The Network Adapter could not establish the connection"))) {

                JOptionPane.showMessageDialog(this, "Não foi possivel Conectar Com o Banco de Dados!");

            } else {

                JOptionPane.showMessageDialog(this, "Erro Desconhecido!");

                ex.printStackTrace();
            }

        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(this, "Erro de Desconhecido!");

            ex.printStackTrace();
        } catch (NullPointerException ex) {

            JOptionPane.showMessageDialog(this, "Null Pointer!");

            ex.printStackTrace();
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

    //LISTAR COMBOBOX
    public void ListarCombobox() throws SQLException, ClassNotFoundException {
        try {
            //DAO
            StatusRequisicaoDAO statusRequisicaoDao = new StatusRequisicaoDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();

            //MODEL
            StatusRequisicao statusRequisicao;
            Usuario usuario;

            //LISTAS
            List listStatusRequisicao = statusRequisicaoDao.ObterStatusRequisicao(nameDb);
            List listUsuario = usuarioDao.ObterNome(nameDb);

            //STATUS REQUISICAO
            Iterator it = listStatusRequisicao.iterator();

            while (it.hasNext()) {
                statusRequisicao = (StatusRequisicao) it.next();
                String nome = statusRequisicao.getStatusRequisicao();

                //INSERIR
                cbStatus.addItem(nome);
            }

            //REQUISITANTE
            it = listUsuario.iterator();

            while (it.hasNext()) {
                usuario = (Usuario) it.next();
                String nome = usuario.getLogin();

                //INSERIR
                cbRequisitante.addItem(nome);
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

    public void Visualiza() {
        if (tbRequisicao.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Favor selecionar uma Requisição");
        } else {
            TelaRequisicaoCompra tela = new TelaRequisicaoCompra();
            // recuperando o numero da linha selecionada
            int numeroLinha = tbRequisicao.getSelectedRow();

            Requisicoes requisicao = new Requisicoes();

            Integer id = (Integer) tbRequisicao.getValueAt(numeroLinha, 0);
            requisicao.setId(id);

            try {
                tela.nameDb(nameDb);
                tela.carregarRequisicao(requisicao);
                tela.ObterLogin(obterLogin);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(TelaListaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaListaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbUsuario = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRequisicao = new javax.swing.JTable();
        cbRequisitante = new javax.swing.JComboBox<>();
        cbStatus = new javax.swing.JComboBox<>();
        btBuscar = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btAlterarSenha = new javax.swing.JMenuItem();
        jMenuRequisicaoArquivadas = new javax.swing.JMenu();
        btVisualizar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("REQUISIÇÃO ARQUIVADAS");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario.setText("Usuário:");

        lbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbRequisicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Requisição", "Status", "Data Criação", "Data Solicitação", "Requisitante", "Projeto", "Tipo de Requisição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRequisicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRequisicaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRequisicao);

        cbRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbRequisitante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario" }));

        cbStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status" }));

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btBuscar.setText("Filtrar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btBuscar)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitulo)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbUsuario)
                        .addComponent(lbLogin)
                        .addComponent(lbNivel)))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbUsuario});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btBuscar, cbRequisitante, cbStatus, lbTitulo});

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

        btAlterarSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAlterarSenha.setText("Alterar Senha");
        btAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarSenhaActionPerformed(evt);
            }
        });
        jMenu3.add(btAlterarSenha);

        menu.add(jMenu3);

        jMenuRequisicaoArquivadas.setText("Arquivadas");
        jMenuRequisicaoArquivadas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVisualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        btVisualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVisualizar.setText("Visualizar");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });
        jMenuRequisicaoArquivadas.add(btVisualizar);

        menu.add(jMenuRequisicaoArquivadas);

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
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            //TELA REQ
            TelaListaRequisicao tela = new TelaListaRequisicao();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        Visualiza();
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (cbRequisitante.getSelectedItem().equals("Usuario")) {
            msgErro += "- Favor selecionar campo Usuário\n";
            valida = false;
        }
        if (cbStatus.getSelectedItem().equals("Status")) {
            msgErro += "- Favor selecionar campo Status\n";
            valida = false;
        }
        if (valida) {
            Filtro();
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarSenhaActionPerformed
        try {
            //TELA INF FINC
            TelaAlterarSenha tela = new TelaAlterarSenha();
            //this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAlterarSenhaActionPerformed

    private void tbRequisicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRequisicaoMouseClicked
        int click = 0;
        if (click == 0) {
            int count = evt.getClickCount();
            count++;
            if (count == 3) {
                Visualiza();
            }
        }
    }//GEN-LAST:event_tbRequisicaoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicaoArquivada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaRequisicaoArquivada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btAlterarSenha;
    private javax.swing.JButton btBuscar;
    private javax.swing.JMenuItem btVisualizar;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JComboBox<String> cbRequisitante;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu jMenuRequisicaoArquivadas;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTable tbRequisicao;
    // End of variables declaration//GEN-END:variables
}
