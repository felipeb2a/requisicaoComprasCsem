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
import model.FileCompras;
import model.Icone;
import model.Log;
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
public class TelaListaRequisicao extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    private String nameDb;
    private Logger logger = null;

    public TelaListaRequisicao() {
        initComponents();
        AlteraFontTable();
        MaximizeTela();
    }

    //LOGGER
    public Logger Definirlogger() {
        Log log = new Log();
        try {
            logger = log.pathLog(TelaListaRequisicao.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(TelaListaRequisicao.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(TelaListaRequisicao.class.getName()).log(Level.SEVERE, null, ex1);
        }

        return logger;
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
            List listaRequisicao = requisicaoDAO.localizarRequisicoesUser(usuario, nameDb);
            DefaultTableModel model = (DefaultTableModel) tbRequisicao.getModel();
            model.setNumRows(0);

            for (Iterator it = listaRequisicao.iterator(); it.hasNext();) {

                Requisicoes requisicoes = (Requisicoes) it.next();
                Object linha[]
                        = {requisicoes.getId(), requisicoes.getStatusRequisicao().getStatusRequisicao(),
                            requisicoes.getDataCriacao(), requisicoes.getDataSolicitacao(), requisicoes.getUsuario().getLogin(),
                            requisicoes.getProjetos().getProjeto(), requisicoes.getTipoRequisicao().getTipoRequisicao(),
                            requisicoes.getEtapaRequisicao().getEtapaRequisicao()};

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

    //FILTRO
    public void Filtro() {
        try {
            //ListarCombobox();
            RequisicoesDAO requisicaoDAO = new RequisicoesDAO();
            String usuario = (String) cbRequisitante.getSelectedItem();
            String status = (String) cbStatus.getSelectedItem();
            String codRequisicao = txtCodRequisicao.getText();
            List listaRequisicao = new ArrayList();
            if (txtCodRequisicao.getText().length() == 0) {
                listaRequisicao = requisicaoDAO.FiltrarRequisicoesUserStatus(usuario, status, nameDb);
            }
            if (!(txtCodRequisicao.getText().length() == 0)) {
                listaRequisicao = requisicaoDAO.FiltrarRequisicoesCodRequisicao(codRequisicao, nameDb);
            }
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
            } catch (Exception ex) {
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

            this.setVisible(false);
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
        lbCodRequisicao = new javax.swing.JLabel();
        txtCodRequisicao = new javax.swing.JTextField();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btAlterarSenha = new javax.swing.JMenuItem();
        jMenuRequisicaoCriar = new javax.swing.JMenu();
        btCriar = new javax.swing.JMenuItem();
        btRequisicoesArquivadas1 = new javax.swing.JMenuItem();
        btCriar1 = new javax.swing.JMenuItem();
        btRequisicoesArquivadas = new javax.swing.JMenuItem();
        btVisualizar = new javax.swing.JMenuItem();
        jMenuSolicitacoesEnviadas = new javax.swing.JMenu();
        btBuscarItem = new javax.swing.JMenuItem();
        btBuscarFornecedor = new javax.swing.JMenuItem();
        jMenuSolicitacoesEnviadas1 = new javax.swing.JMenu();
        btRelacaoOp = new javax.swing.JMenuItem();
        btRelatorioOp = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btVoltar1 = new javax.swing.JMenuItem();
        btPastaFollowUp = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        btOC = new javax.swing.JMenuItem();
        btAtivos = new javax.swing.JMenuItem();
        btDados = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("REQUISIÇÃO");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario.setText("Usuário:");

        lbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbRequisicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Requisição", "Status", "Data Criação", "Data Solicitação", "Requisitante", "Projeto", "Tipo de Requisição", "Etapa Requisição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        cbRequisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRequisitanteActionPerformed(evt);
            }
        });

        cbStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status" }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btBuscar.setText("Filtrar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        lbCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodRequisicao.setText("Cod. Requisicão:");

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodRequisicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodRequisicaoActionPerformed(evt);
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
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCodRequisicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodRequisicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar)
                        .addGap(18, 18, 18)
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodRequisicao)
                            .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBuscar))
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUsuario)
                            .addComponent(lbLogin)
                            .addComponent(lbNivel))
                        .addGap(0, 557, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbTitulo)
                        .addGap(0, 545, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbUsuario});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btBuscar, cbRequisitante, cbStatus, lbCodRequisicao, lbTitulo, txtCodRequisicao});

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

        jMenuRequisicaoCriar.setText("Requisição");
        jMenuRequisicaoCriar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btCriar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        btCriar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCriar.setText("Criar");
        btCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btCriar);

        btRequisicoesArquivadas1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        btRequisicoesArquivadas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRequisicoesArquivadas1.setText("Criar a partir desta");
        btRequisicoesArquivadas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRequisicoesArquivadas1ActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btRequisicoesArquivadas1);

        btCriar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK));
        btCriar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCriar1.setText("Canceladas");
        btCriar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriar1ActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btCriar1);

        btRequisicoesArquivadas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        btRequisicoesArquivadas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRequisicoesArquivadas.setText("Arquivadas");
        btRequisicoesArquivadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRequisicoesArquivadasActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btRequisicoesArquivadas);

        btVisualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        btVisualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVisualizar.setText("Visualizar");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btVisualizar);

        menu.add(jMenuRequisicaoCriar);

        jMenuSolicitacoesEnviadas.setText("Pesquisa");
        jMenuSolicitacoesEnviadas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btBuscarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        btBuscarItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btBuscarItem.setText("Itens");
        btBuscarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarItemActionPerformed(evt);
            }
        });
        jMenuSolicitacoesEnviadas.add(btBuscarItem);

        btBuscarFornecedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        btBuscarFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btBuscarFornecedor.setText("Fornecedores");
        btBuscarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarFornecedorActionPerformed(evt);
            }
        });
        jMenuSolicitacoesEnviadas.add(btBuscarFornecedor);

        menu.add(jMenuSolicitacoesEnviadas);

        jMenuSolicitacoesEnviadas1.setText("OP");
        jMenuSolicitacoesEnviadas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btRelacaoOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        btRelacaoOp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRelacaoOp.setText("Relação OP");
        btRelacaoOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelacaoOpActionPerformed(evt);
            }
        });
        jMenuSolicitacoesEnviadas1.add(btRelacaoOp);

        btRelatorioOp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        btRelatorioOp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRelatorioOp.setText("Relatório OP");
        btRelatorioOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelatorioOpActionPerformed(evt);
            }
        });
        jMenuSolicitacoesEnviadas1.add(btRelatorioOp);

        menu.add(jMenuSolicitacoesEnviadas1);

        jMenu6.setText("Follow-up");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVoltar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        btVoltar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVoltar1.setText("Relatório");
        btVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar1ActionPerformed(evt);
            }
        });
        jMenu6.add(btVoltar1);

        btPastaFollowUp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        btPastaFollowUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btPastaFollowUp.setText("Abrir Pasta");
        btPastaFollowUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPastaFollowUpActionPerformed(evt);
            }
        });
        jMenu6.add(btPastaFollowUp);

        menu.add(jMenu6);

        jMenu7.setText("Utilitarios");
        jMenu7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btOC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btOC.setText("PASTA OCs");
        btOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOCActionPerformed(evt);
            }
        });
        jMenu7.add(btOC);

        btAtivos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAtivos.setText("ATIVOS");
        btAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtivosActionPerformed(evt);
            }
        });
        jMenu7.add(btAtivos);

        btDados.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btDados.setText("DADOS CADASTRAIS");
        btDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDadosActionPerformed(evt);
            }
        });
        jMenu7.add(btDados);

        menu.add(jMenu7);

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

    private void btCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriarActionPerformed
        try {
            //TELA CRIA REQ
            TelaRequisicaoNova tela = new TelaRequisicaoNova();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btCriarActionPerformed

    private void btBuscarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarItemActionPerformed
        try {
            //TELA BUSCA ITEM
            TelaListaItem tela = new TelaListaItem();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btBuscarItemActionPerformed

    private void btBuscarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarFornecedorActionPerformed
        try {
            //TELA BUSCA FORNECEDOR
            TelaListaFornecedor tela = new TelaListaFornecedor();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btBuscarFornecedorActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        try {
            //TELA REQ
            TelaSelecioneLista tela = new TelaSelecioneLista();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
        }
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btRequisicoesArquivadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRequisicoesArquivadasActionPerformed
        try {
            //TELA REQUISICAO ARQUIVADA
            TelaListaRequisicaoArquivada tela = new TelaListaRequisicaoArquivada();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btRequisicoesArquivadasActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        Visualiza();
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btRelacaoOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelacaoOpActionPerformed
        try {
            //TELA BUSCA ITEM
            TelaListaOp tela = new TelaListaOp();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btRelacaoOpActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (txtCodRequisicao.getText().length() == 0) {
            if (cbRequisitante.getSelectedItem().equals("Usuario")) {
                msgErro += "- Favor selecionar campo Usuário\n";
                valida = false;
            }
            if (cbStatus.getSelectedItem().equals("Status")) {
                msgErro += "- Favor selecionar campo Status\n";
                valida = false;
            }
            msgErro += "- Caso queira filtrar pelo Código da Requisição favor preencher campo Cód. Requisição\n";
        }
        if (valida) {
            Filtro();
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btRequisicoesArquivadas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRequisicoesArquivadas1ActionPerformed
        if (tbRequisicao.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Favor selecionar uma Requisição");
        } else {

            try {
                TelaRequisicaoNova tela = new TelaRequisicaoNova();
                // recuperando o numero da linha selecionada
                int numeroLinha = tbRequisicao.getSelectedRow();

                Requisicoes requisicao = new Requisicoes();

                Integer id = (Integer) tbRequisicao.getValueAt(numeroLinha, 0);
                requisicao.setId(id);
                tela.nameDb(nameDb);
                tela.carregarRequisicaoApartir(requisicao);
                tela.ObterLogin(obterLogin);
                this.setVisible(false);
            } catch (Exception ex) {
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
    }//GEN-LAST:event_btRequisicoesArquivadas1ActionPerformed

    private void btCriar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriar1ActionPerformed
        try {
            //TELA REQUISICAO CANCELADA
            TelaListaRequisicaoCancelada tela = new TelaListaRequisicaoCancelada();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btCriar1ActionPerformed

    private void btVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar1ActionPerformed
        try {
            //TELA FOLLOW UP
            TelaFollowUpRequisicao tela = new TelaFollowUpRequisicao();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
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
    }//GEN-LAST:event_btVoltar1ActionPerformed

    private void btOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOCActionPerformed
        FileCompras fileCompras = new FileCompras();
        String nivel = lbNivel.getText();
        fileCompras.OC(nameDb, nivel);
    }//GEN-LAST:event_btOCActionPerformed

    private void btAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtivosActionPerformed
        FileCompras fileCompras = new FileCompras();
        fileCompras.ATIVOS(nameDb);
    }//GEN-LAST:event_btAtivosActionPerformed

    private void btDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDadosActionPerformed
        FileCompras fileCompras = new FileCompras();
        fileCompras.DADOS(nameDb);
    }//GEN-LAST:event_btDadosActionPerformed

    private void btPastaFollowUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPastaFollowUpActionPerformed
        FileCompras fileCompras = new FileCompras();
        String user = lbLogin.getText();
        fileCompras.FOLLOWUP(nameDb, user);
    }//GEN-LAST:event_btPastaFollowUpActionPerformed

    private void btAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarSenhaActionPerformed
        try {
            //TELA INF FINC
            TelaAlterarSenha tela = new TelaAlterarSenha();
            //this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
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

    private void btRelatorioOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelatorioOpActionPerformed
        try {
            //TELA RELATORIO OP
            TelaRelatorioOrdemPagamento tela = new TelaRelatorioOrdemPagamento();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (Exception ex) {
            logger = Definirlogger();
            logger.log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERRO: " + ex);
            //LOG
//            LogArquivoTexto log = new LogArquivoTexto();
//            String classe = TelaListaRequisicao.class.getName();
//            String texto = classe + "\n" + "ERRO: " + ex;
//            try {
//                log.escreverGeral(texto, nameDb);
//            } catch (Exception ex1) {
//                Logger.getLogger(TelaListaRequisicao.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }//GEN-LAST:event_btRelatorioOpActionPerformed

    private void txtCodRequisicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodRequisicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodRequisicaoActionPerformed

    private void cbRequisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRequisitanteActionPerformed
        txtCodRequisicao.setText("");
    }//GEN-LAST:event_cbRequisitanteActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        txtCodRequisicao.setText("");
    }//GEN-LAST:event_cbStatusActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListaRequisicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListaRequisicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListaRequisicao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btAlterarSenha;
    private javax.swing.JMenuItem btAtivos;
    private javax.swing.JButton btBuscar;
    private javax.swing.JMenuItem btBuscarFornecedor;
    private javax.swing.JMenuItem btBuscarItem;
    private javax.swing.JMenuItem btCriar;
    private javax.swing.JMenuItem btCriar1;
    private javax.swing.JMenuItem btDados;
    private javax.swing.JMenuItem btOC;
    private javax.swing.JMenuItem btPastaFollowUp;
    private javax.swing.JMenuItem btRelacaoOp;
    private javax.swing.JMenuItem btRelatorioOp;
    private javax.swing.JMenuItem btRequisicoesArquivadas;
    private javax.swing.JMenuItem btRequisicoesArquivadas1;
    private javax.swing.JMenuItem btVisualizar;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JMenuItem btVoltar1;
    private javax.swing.JComboBox<String> cbRequisitante;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu jMenuRequisicaoCriar;
    private javax.swing.JMenu jMenuSolicitacoesEnviadas;
    private javax.swing.JMenu jMenuSolicitacoesEnviadas1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCodRequisicao;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTable tbRequisicao;
    private javax.swing.JTextField txtCodRequisicao;
    // End of variables declaration//GEN-END:variables
}
