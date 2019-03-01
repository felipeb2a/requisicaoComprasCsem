package view;

import dao.DestinacaoDAO;
import dao.FornecedorDAO;
import dao.ItemDAO;
import dao.MoedasDAO;
import dao.ProjetosDAO;
import dao.RequisicoesDAO;
import dao.SolicitanteDAO;
import dao.StatusArqRequisicaoDAO;
import dao.StatusRequisicaoDAO;
import dao.TipoFreteDAO;
import dao.TipoRequisicaoDAO;
import dao.UsuarioDAO;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import view.list.TelaListaRequisicao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.CreateFolder;
import model.Destinacao;
import model.EtapaRequisicao;
import model.Format;
import model.Fornecedor;
import model.Icone;
import model.Item;
import model.Log;
import model.LogArquivoTexto;
import model.Moedas;
import model.Niveis;
import model.ProdutoERP;
import model.Projetos;
import model.Report;
import model.Requisicoes;
import model.SendMail;
import model.Solicitante;
import model.StatusArqRequisicao;
import model.StatusRequisicao;
import model.TipoFrete;
import model.TipoRequisicao;
import model.Usuario;
import model.VerificaParametro;
import net.sf.jasperreports.engine.JRException;
import view.initial.TelaAlterarSenha;
import view.initial.TelaLogin;
import view.initial.TelaSelecioneAno;

/**
 *
 * @author felipe.ferreira
 */
public class TelaRequisicaoNova extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    private String Status = "Nova";
    private String nameDb;
    private Logger logger = null;

    public TelaRequisicaoNova() throws ParseException, SQLException, ClassNotFoundException {
        initComponents();
        AlteraFontTable();
        MaximizeTela();
        txtStatus.setText(Status);
        txtDataEntrega.setEnabled(false);
        btFinalizar.setEnabled(false);

    }

    //LOGGER
    public Logger Definirlogger() {
        Log log = new Log();
        try {
            logger = log.pathLog(TelaSolicitacaoNova.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex1);
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
        icone();
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException, ParseException {
        this.nameDb = nameDb;
        jMenuEmpresa.setText(nameDb);
        CodRequisicao();
        DefineData();
        ListarCombobox();
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
        txtRequisitante.setText(login.getLogin());
        txtRequisitante.setEnabled(false);

    }

    public void AlteraFontTable() {
        JTableHeader cabecalhoFornecedor = tbFornecedor.getTableHeader();
        cabecalhoFornecedor.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTableHeader cabecalhoItens = tbItens.getTableHeader();
        cabecalhoItens.setFont(new Font("Tahoma", Font.BOLD, 18));
    }

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //CAMPO COD REQUISICAO
    public void CodRequisicao() {
        txtCodRequisicao.setText("Nova");
        txtCodRequisicao.setEnabled(false);
    }

    //DATA
    public void DefineData() throws ParseException {
        txtDataRequisicao.setEnabled(false);
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);

        txtDataRequisicao.setDate(dataHoje);
        txtDataSolicitacao.setDate(dataHoje);
    }

    //LISTAR COMBOBOX
    public void ListarCombobox() throws SQLException, ClassNotFoundException {

        try {
            //DAO
            TipoRequisicaoDAO tipoReqDao = new TipoRequisicaoDAO();
            DestinacaoDAO destinacaoDao = new DestinacaoDAO();
            TipoFreteDAO tipoFreteDao = new TipoFreteDAO();
            ProjetosDAO projetosDao = new ProjetosDAO();
            MoedasDAO moedasDao = new MoedasDAO();
            RequisicoesDAO requisicaoDao = new RequisicoesDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();

            //MODEL
            TipoRequisicao tipoRequisicao;
            Destinacao destinacao;
            TipoFrete tipoFrete;
            Projetos projetos;
            Moedas moedas;
            Requisicoes requisicoes = new Requisicoes();
            Usuario usuario;

            //LISTAS
            List listTipoRequisicao = tipoReqDao.ObterTipoRequisicao(nameDb);
            List listDestinacao = destinacaoDao.ObterDestinacao(nameDb);
            List listTipoFrete = tipoFreteDao.ObterTipoFrete(nameDb);
            List listProjetos = projetosDao.ObterProjeto(nameDb);
            List listMoedas = moedasDao.ObterMoedas(nameDb);
            List listRequicicoes = requisicaoDao.ObterRequisicoesVinculacao(nameDb);
            List listAprovador = usuarioDao.ObterNomeAprovador(nameDb);

            //TIPO REQUISICAO
            Iterator it = listTipoRequisicao.iterator();

            while (it.hasNext()) {
                tipoRequisicao = (TipoRequisicao) it.next();
                String nome = tipoRequisicao.getTipoRequisicao();

                //INSERIR
                cbTipoRequisicao.addItem(nome);
            }

            //DESTINACAO
            it = listDestinacao.iterator();

            while (it.hasNext()) {
                destinacao = (Destinacao) it.next();
                String nome = destinacao.getDestinacao();

                //INSERIR
                cbDestinacao.addItem(nome);
            }

            //TIPO FRETE
            it = listTipoFrete.iterator();

            while (it.hasNext()) {
                tipoFrete = (TipoFrete) it.next();
                String nome = tipoFrete.getTipoFrete();

                //INSERIR
                cbTipoFrete.addItem(nome);
            }

            //PROJETOS
            it = listProjetos.iterator();

            while (it.hasNext()) {
                projetos = (Projetos) it.next();
                String nome = projetos.getProjeto();

                //INSERIR
                cbProjeto.addItem(nome);
            }

            //MOEDAS
            it = listMoedas.iterator();

            while (it.hasNext()) {
                moedas = (Moedas) it.next();
                String nome = moedas.getMoedaAbreviada();

                //INSERIR
                cbMoeda.addItem(nome);
            }

            //APROVADOR
            it = listAprovador.iterator();

            while (it.hasNext()) {
                usuario = (Usuario) it.next();
                String nome = usuario.getLogin();

                //INSERIR
                cbTipoAprovador.addItem(nome);
            }

            //VINCULACAO
            /*
            it = listRequicicoes.iterator();

            while (it.hasNext()) {
                requisicoes = (Requisicoes) it.next();
                int nome = requisicoes.getId();

                //INSERIR
                cbVinculacao.addItem(Integer.toString(nome));
            }
             */
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

    //LIMPAR CAMPOS
    public void limpaCamppos() {
        cbTipoRequisicao.setSelectedItem("Selecione");
        txtSolicitante.setText("");
        try {
            DefineData();
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbProjeto.setSelectedItem("Selecione");
        cbDestinacao.setSelectedItem("Selecione");
        cbTipoFrete.setSelectedItem("Selecione");
        txtDataEntrega.setDate(null);
        cbMoeda.setSelectedItem("Selecione");
        cbTipoAprovador.setSelectedItem("Selecione");
        txtJustificativa.setText("");
        DefaultTableModel tabela = (DefaultTableModel) tbFornecedor.getModel();
        tabela.setNumRows(0);
        tabela = (DefaultTableModel) tbItens.getModel();
        tabela.setNumRows(0);
    }

    //CARREGAR REQUISICAO
    public void carregarRequisicaoApartir(Requisicoes requisicao) throws SQLException, ClassNotFoundException {

        try {

            ListarCombobox();
            //StatusRequisicao status = new StatusRequisicao();
            //status.setStatusRequisicao(Status);
            //StatusArqRequisicaoDAO statusDao = new StatusArqRequisicaoDAO();

            this.setVisible(true);
            //MODEL
            Fornecedor fornecedor = new Fornecedor();
            Item item = new Item();

            //LIST
            List listaRequisicao, listaFornecedor, listaItem;

            //MODEL TABLE
            DefaultTableModel modelFornecedor = (DefaultTableModel) tbFornecedor.getModel();
            DefaultTableModel modelItem = (DefaultTableModel) tbItens.getModel();
            modelFornecedor.setNumRows(0);
            modelItem.setNumRows(0);

            //DAO
            RequisicoesDAO requisicaoDao = new RequisicoesDAO();
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            ItemDAO itemDao = new ItemDAO();

            //BUSCAR FORNECEDOR
            fornecedor.setRequisicao(requisicao);
            listaFornecedor = fornecedorDao.localizarFornecedorRequisicao(fornecedor, nameDb);
            //BUSCAR ITEM
            item.setRequisicoes(requisicao);
            listaItem = itemDao.localizarItemRequisicao(item, nameDb);
            //BUSCAR REQ
            requisicao = requisicaoDao.localizarRequisicaoParaAprovar(requisicao, nameDb);

            //ATRIBUIR VALORES REQUISICAO
            //txtStatus.setText(requisicao.getStatusRequisicao().getStatusRequisicao());
            //txtCodReqTitulo.setText(Integer.toString(requisicao.getId()));
            //VERIFICA STATUS
            //verificaStatus();
            //txtDataRequisicao.setDate(requisicao.getDataCriacao());
            txtDataEntrega.setDate(requisicao.getDataEntrega());
            cbTipoRequisicao.setSelectedItem(requisicao.getTipoRequisicao().getTipoRequisicao());
            //txtCodRequisicao.setText(Integer.toString(requisicao.getId()));
            //txtRequisitante.setText(requisicao.getUsuario().getLogin());
            txtSolicitante.setText(requisicao.getSolicitante().getNomeSolicitante());
            //txtDataSolicitacao.setDate(requisicao.getDataSolicitacao());
            cbProjeto.setSelectedItem(requisicao.getProjetos().getProjeto());
            cbDestinacao.setSelectedItem(requisicao.getDestinacao().getDestinacao());
            cbTipoFrete.setSelectedItem(requisicao.getTipoFrete().getTipoFrete());
            //cbVinculacao.setSelectedItem(requisicao.getVinculacao());
            cbMoeda.setSelectedItem(requisicao.getMoedas().getMoedaAbreviada());
            String aprovador = requisicao.getTipoAprovador();

            if (aprovador.equals("Aprovador Financeiro")) {
                cbTipoAprovador.setSelectedItem("Financeiro");
            } else {
                cbTipoAprovador.setSelectedItem("Tecnico");
            }
            txtJustificativa.setText(requisicao.getJustificativa());

            //ATRIBUIR VALORES A TABLE FORNECEDOR
            for (Iterator it = listaFornecedor.iterator(); it.hasNext();) {

                fornecedor = (Fornecedor) it.next();
                Object linha[]
                        = {fornecedor.getNomeFornecedor(), fornecedor.getTelefone(), fornecedor.getEmail(), fornecedor.getContato(),
                            fornecedor.getInfoAdicionais(), fornecedor.getTempoProducao(), fornecedor.getLogistica(),
                            fornecedor.getValorInicial(), fornecedor.getValorFinal(), fornecedor.getEscolha()};

                modelFornecedor.addRow(linha);
            }

            //ATRIBUIR VALORES A TABLE ITEM
            for (Iterator it = listaItem.iterator(); it.hasNext();) {

                item = (Item) it.next();
                Object linha[]
                        = {item.getNomeItem(), item.getUnidade(), item.getQuantidade(), item.getValorUnitario(),
                            item.getValorTotal(), item.getDescricaoTecnica(), item.getInformacoesAdicionais()};

                modelItem.addRow(linha);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbFornecedor = new javax.swing.JTable();
        lbTituloFornecedor = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbItens = new javax.swing.JTable();
        lbTituloItens = new javax.swing.JLabel();
        btAddLinhaItem = new javax.swing.JButton();
        btAddLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaItem = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbUsuario = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        lbDataRequisicao = new javax.swing.JLabel();
        lbTipoFrete1 = new javax.swing.JLabel();
        cbMoeda = new javax.swing.JComboBox<>();
        btFinalizar = new javax.swing.JButton();
        lbCodigoRequisicao2 = new javax.swing.JLabel();
        lbCodigoRequisicao1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtJustificativa = new javax.swing.JTextArea();
        cbTipoRequisicao = new javax.swing.JComboBox<>();
        lbTipoRequisicao = new javax.swing.JLabel();
        lbCodigoRequisicao = new javax.swing.JLabel();
        lbRequisitante = new javax.swing.JLabel();
        lbSolicitante = new javax.swing.JLabel();
        lbDataSolicitacao = new javax.swing.JLabel();
        lbProjeto = new javax.swing.JLabel();
        lbDestinacao = new javax.swing.JLabel();
        lbTipoFrete = new javax.swing.JLabel();
        cbTipoFrete = new javax.swing.JComboBox<>();
        cbDestinacao = new javax.swing.JComboBox<>();
        cbProjeto = new javax.swing.JComboBox<>();
        txtSolicitante = new javax.swing.JTextField();
        txtCodRequisicao = new javax.swing.JTextField();
        txtRequisitante = new javax.swing.JTextField();
        txtDataRequisicao = new com.toedter.calendar.JDateChooser();
        txtDataSolicitacao = new com.toedter.calendar.JDateChooser();
        lbCodigoRequisicao3 = new javax.swing.JLabel();
        cbTipoAprovador = new javax.swing.JComboBox<>();
        txtDataEntrega = new com.toedter.calendar.JDateChooser();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btAlterarSenha = new javax.swing.JMenuItem();
        btLimpar = new javax.swing.JMenu();
        btEnviar = new javax.swing.JMenuItem();
        btArquivar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Telefone", "Email", "Contato", "Info. Adicionais", "Tempo de Produção", "Logística", "Valor Inicial", "Valor Final", "Selecionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbFornecedor);

        lbTituloFornecedor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTituloFornecedor.setText("FORNECEDORES");

        tbItens.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Unidade", "Quantidade", "Valor Unitário", "Valor Total", "Descrição Técnica", "Informações Adicionais"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbItens);

        lbTituloItens.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTituloItens.setText("ITENS");

        btAddLinhaItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAddLinhaItem.setText("Adicionar Linha");
        btAddLinhaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddLinhaItemActionPerformed(evt);
            }
        });

        btAddLinhaFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAddLinhaFornecedor.setText("Adicionar Linha");
        btAddLinhaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddLinhaFornecedorActionPerformed(evt);
            }
        });

        btRemoverLinhaFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRemoverLinhaFornecedor.setText("Remover Linha");
        btRemoverLinhaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverLinhaFornecedorActionPerformed(evt);
            }
        });

        btRemoverLinhaItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRemoverLinhaItem.setText("Remover Linha");
        btRemoverLinhaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverLinhaItemActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("REQUISIÇÃO DE COMPRA (NOVA)");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario.setText("Usuário:");

        lbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 102, 51));
        lbStatus.setText("Status:");

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(0, 102, 255));

        lbDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataRequisicao.setText("Data da Requisição:");

        lbTipoFrete1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete1.setText("Data de Entrega");

        cbMoeda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbMoeda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btFinalizar.setText("Finalizar");

        lbCodigoRequisicao2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao2.setText("Justificativa da Solicitação:");

        lbCodigoRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao1.setText("Moeda:");

        txtJustificativa.setColumns(20);
        txtJustificativa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJustificativa.setRows(5);
        jScrollPane2.setViewportView(txtJustificativa);

        cbTipoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoRequisicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cbTipoRequisicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoRequisicaoActionPerformed(evt);
            }
        });

        lbTipoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoRequisicao.setText("Tipo de Requisição:");

        lbCodigoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao.setText("Código da Requisição:");

        lbRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbRequisitante.setText("Requisitante:");

        lbSolicitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSolicitante.setText("Solicitante:");

        lbDataSolicitacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataSolicitacao.setText("Data da Solicitação:");

        lbProjeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbProjeto.setText("Projeto:");

        lbDestinacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDestinacao.setText("Destinação:");

        lbTipoFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete.setText("Tipo de Frete:");

        cbTipoFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoFrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        cbDestinacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbDestinacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        cbProjeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbProjeto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        txtSolicitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataSolicitacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCodigoRequisicao3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao3.setText("Aprovador:");

        cbTipoAprovador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoAprovador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        txtDataEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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

        btLimpar.setText("Requisição");
        btLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btEnviar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        btEnviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });
        btLimpar.add(btEnviar);

        btArquivar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        btArquivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btArquivar.setText("Limpar");
        btArquivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArquivarActionPerformed(evt);
            }
        });
        btLimpar.add(btArquivar);

        menu.add(btLimpar);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSolicitante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTipoFrete, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDestinacao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbProjeto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataSolicitacao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRequisitante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCodigoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTipoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataRequisicao, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataSolicitacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbTipoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDestinacao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtDataRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoRequisicao2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCodigoRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTipoFrete1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(lbCodigoRequisicao3))
                                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTipoAprovador, 0, 281, Short.MAX_VALUE)
                                    .addComponent(btFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208)
                        .addComponent(lbUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(lbStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(554, 554, 554)
                                .addComponent(lbTituloFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(btAddLinhaFornecedor)
                                .addGap(6, 6, 6)
                                .addComponent(btRemoverLinhaFornecedor))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(589, 589, 589)
                                .addComponent(lbTituloItens, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)
                                .addComponent(btAddLinhaItem)
                                .addGap(6, 6, 6)
                                .addComponent(btRemoverLinhaItem)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo)
                    .addComponent(lbUsuario))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRequisitante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRequisitante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSolicitante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSolicitante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbProjeto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProjeto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDestinacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDestinacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoFrete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoFrete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btAddLinhaFornecedor)
                                .addComponent(lbTituloFornecedor))
                            .addComponent(btRemoverLinhaFornecedor))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btAddLinhaItem)
                                .addComponent(lbTituloItens))
                            .addComponent(btRemoverLinhaItem))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTipoFrete1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btFinalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtDataEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbTipoAprovador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbCodigoRequisicao3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbMoeda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbCodigoRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoRequisicao2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbDestinacao, cbProjeto, cbTipoFrete, cbTipoRequisicao, lbCodigoRequisicao, lbDataRequisicao, lbDataSolicitacao, lbDestinacao, lbProjeto, lbRequisitante, lbSolicitante, lbTipoFrete, lbTipoRequisicao, txtCodRequisicao, txtDataRequisicao, txtDataSolicitacao, txtRequisitante, txtSolicitante});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btFinalizar, cbMoeda, cbTipoAprovador, lbCodigoRequisicao1, lbCodigoRequisicao2, lbCodigoRequisicao3, lbTipoFrete1, txtDataEntrega});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddLinhaFornecedor, btRemoverLinhaFornecedor, lbTituloFornecedor});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddLinhaItem, btRemoverLinhaItem, lbTituloItens});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbStatus, lbUsuario, txtStatus});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddLinhaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddLinhaItemActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbItens.getModel();
        String campo = "";
        Object[] row = {campo, campo};
        model.addRow(row);
    }//GEN-LAST:event_btAddLinhaItemActionPerformed

    private void btAddLinhaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddLinhaFornecedorActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbFornecedor.getModel();
        String campo = "";
        Object[] row = {campo, campo};
        model.addRow(row);
    }//GEN-LAST:event_btAddLinhaFornecedorActionPerformed

    private void btRemoverLinhaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverLinhaFornecedorActionPerformed
        //DELETAR LINHA JTABLE
        DefaultTableModel model = (DefaultTableModel) tbFornecedor.getModel();
        int rowSelect = tbFornecedor.getSelectedRow();
        if (rowSelect >= 0) {
            //REMOVER LINHA DO GRID
            model.removeRow(rowSelect);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
        }
    }//GEN-LAST:event_btRemoverLinhaFornecedorActionPerformed

    private void btRemoverLinhaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverLinhaItemActionPerformed
        //DELETAR LINHA JTABLE
        DefaultTableModel model = (DefaultTableModel) tbItens.getModel();
        int rowSelect = tbItens.getSelectedRow();
        if (rowSelect >= 0) {
            //REMOVER LINHA DO GRID
            model.removeRow(rowSelect);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
        }
    }//GEN-LAST:event_btRemoverLinhaItemActionPerformed

    private void cbTipoRequisicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoRequisicaoActionPerformed
        if (cbTipoRequisicao.getSelectedItem().equals("Nacional")
                || cbTipoRequisicao.getSelectedItem().equals("RH")) {
            cbMoeda.setSelectedItem("BRL");
        } else {
            cbMoeda.setSelectedItem("Selecione");
        }
    }//GEN-LAST:event_cbTipoRequisicaoActionPerformed

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

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //DATA HOJE
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        //DATA SOLICITANTE
        Calendar solicCalendar = txtDataSolicitacao.getCalendar();
        Date solicDate = txtDataSolicitacao.getDate();
        try {
            String dataSolic = sdf.format(solicDate);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "- Favor preencher campo Data da Solicitação");
        }
        int solicMonth = solicCalendar.get(Calendar.MONTH);
        int solicYear = solicCalendar.get(Calendar.YEAR);

        String dataFormat = sdf.format(dataHoje);
        try {
            dataHoje = sdf.parse(dataFormat);
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
        }

        //VERIFICA CAMPOS
        if (txtDataRequisicao.getCalendar().equals(dataHoje)) {
            msgErro += "- Favor preencher campo Data de Requisição\n";
            valida = false;
        }
        if (cbTipoRequisicao.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Tipo de Requisição\n";
            valida = false;
        }
        if (txtRequisitante.getText().length() == 0) {
            msgErro += "- Favor preencher campo Requisitante\n";
            valida = false;
        }
        if (txtSolicitante.getText().length() == 0) {
            msgErro += "- Favor preencher campo Solicitante\n";
            valida = false;
        }
        if (txtDataSolicitacao.getCalendar().equals("")) {
            msgErro += "- Favor preencher campo Data da Solicitação\n";
            valida = false;
        }
        if (month != solicMonth) {
            msgErro += "- Favor preencher o mês corretamente no campo Data da Solicitação\n";
            valida = false;
        }
        if (year != solicYear) {
            msgErro += "- Favor preencher o ano corretamente no campo Data da Solicitação\n";
            valida = false;
        }
        if (cbProjeto.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Projeto\n";
            valida = false;
        }
        if (cbDestinacao.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Destinação\n";
            valida = false;
        }
        if (cbTipoFrete.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Tipo de Frete\n";
            valida = false;
        }
        if (cbMoeda.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Moeda\n";
            valida = false;
        }
        if (cbTipoAprovador.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor selecionar Tipo Aprovador\n";
            valida = false;
        }
        if (txtJustificativa.getText().length() == 0) {
            msgErro += "- Favor preencher campo Justificativa da Solicitação\n";
            valida = false;
        }
        if (tbFornecedor.getRowCount() == 0) {
            msgErro += "- Favor adicionar Fornecedores\n";
            valida = false;
        }
        //CONTATOR PARA VERIFICAR VALOR FINAL
        int count = 0;
        if (tbFornecedor.getRowCount() > 0) {
            int cont = 0;
            boolean verificaSelecionado = true;
            for (int i = 0; i < tbFornecedor.getRowCount(); i++) {
                int numFornecedor = i + 1;
                Double d = null;
                double verifica = 0;
                if (tbFornecedor.getValueAt(i, 0).equals("")) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Fornecedor! (Linha " + numFornecedor + ")");
                    valida = false;
                }
                try {
                    if (tbFornecedor.getValueAt(i, 7).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Inicial! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Inicial! (Linha " + numFornecedor + ")");
                    valida = false;
                }

                try {
                    if (tbFornecedor.getValueAt(i, 8).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Final! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Final! (Linha " + numFornecedor + ")");
                    valida = false;
                }

                try {
                    if (tbFornecedor.getValueAt(i, 5).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Tempo de Produção (PREENCHA COM 0 SE NÃO TIVER)! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Tempo de Produção (PREENCHA COM 0 SE NÃO TIVER)! (Linha " + numFornecedor + ")");
                    valida = false;
                }

                try {
                    if (tbFornecedor.getValueAt(i, 6).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Logística (PREENCHA COM 0 SE NÃO TIVER)! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Logística (PREENCHA COM 0 SE NÃO TIVER)! (Linha " + numFornecedor + ")");
                    valida = false;
                }

                //VERIFICA FORNECEDOR SELECIONADO
                try {
                    if (tbFornecedor.getValueAt(i, 9).equals(verificaSelecionado)) {
                        cont += 1;
                    }
                } catch (NullPointerException e) {
                    if (cont != 0) {
                        cont = cont;
                    } else {
                        cont = 0;
                    }
                }
            }
            //TESTA COLUNA VALOR FINAL
            if (tbFornecedor.getRowCount() == count) {
                JOptionPane.showMessageDialog(this, "Favor preencher pelo menos um campo na tabela FORNECEDORES, coluna Valor Final!");
                valida = false;
            }
            if (cont == 0) {
                //JOptionPane.showMessageDialog(this, "Favor selecionar um FORNECEDOR");
                msgErro += "- Favor selecionar um FORNECEDOR\n";
                valida = false;
            }
            if (cont > 1) {
                //JOptionPane.showMessageDialog(this, "Favor selecionar apenas um FORNECEDOR");
                msgErro += "- Favor selecionar apenas um FORNECEDOR\n";
                valida = false;
            }
        }
        if (tbItens.getRowCount() == 0) {
            msgErro += "- Favor adicionar Itens\n";
            valida = false;
        }

        if (tbItens.getRowCount() > 0) {
            for (int i = 0; i < tbItens.getRowCount(); i++) {
                int numItem = i + 1;
                Double d = null;
                double verifica = 0;
                if (tbItens.getValueAt(i, 0).equals("")) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Item! (Linha " + numItem + ")");
                    valida = false;
                }
                try {
                    if (tbItens.getValueAt(i, 2).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Quantidade! (Linha " + numItem + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Quantidade! (Linha " + numItem + ")");
                    valida = false;
                }
                try {
                    if (tbItens.getValueAt(i, 3).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Valor Unitário! (Linha " + numItem + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Valor Unitário! (Linha " + numItem + ")");
                    valida = false;
                }
                try {
                    if (tbItens.getValueAt(i, 4).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Valor Total! (Linha " + numItem + ")");
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Valor Total! (Linha " + numItem + ")");
                    valida = false;
                }

            }
        }
        //CAMPOS VALIDADOS
        if (valida) {
            //MODEL
            Requisicoes requisicao = new Requisicoes();
            Destinacao destinacao = new Destinacao();
            Moedas moedas = new Moedas();
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            TipoFrete tipoFrete = new TipoFrete();
            Usuario usuario = new Usuario();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            Solicitante solicitante = new Solicitante();
            Fornecedor fornecedor = new Fornecedor();
            Item item = new Item();
            ProdutoERP produtoERP = new ProdutoERP();
            EtapaRequisicao etapaRequisicao = new EtapaRequisicao();

            //DAO
            RequisicoesDAO requisicoesDao = new RequisicoesDAO();
            DestinacaoDAO destinacaoDao = new DestinacaoDAO();
            MoedasDAO moedasDao = new MoedasDAO();
            ProjetosDAO projetosDao = new ProjetosDAO();
            TipoRequisicaoDAO tipoRequisicaoDao = new TipoRequisicaoDAO();
            StatusRequisicaoDAO statusRequisicaoDao = new StatusRequisicaoDAO();
            TipoFreteDAO tipoFreteDao = new TipoFreteDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();
            StatusArqRequisicaoDAO statusArqRequisicaoDao = new StatusArqRequisicaoDAO();
            SolicitanteDAO solicitanteDao = new SolicitanteDAO();
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            ItemDAO itemDao = new ItemDAO();

            try {
                //DATA REQUISICAO
                requisicao.setDataCriacao(txtDataRequisicao.getDate());
                //BUSCAR TIPO REQUISICAO
                tipoRequisicao.setTipoRequisicao((String) cbTipoRequisicao.getSelectedItem());
                tipoRequisicao = tipoRequisicaoDao.localizar(tipoRequisicao, nameDb);
                requisicao.setTipoRequisicao(tipoRequisicao);
                //BUSCAR REQUISITANTE
                usuario.setLogin(txtRequisitante.getText());
                usuario = usuarioDao.localizarIdUsuario(usuario, nameDb);
                requisicao.setUsuario(usuario);
                //SOLICITANTE
                solicitante.setNomeSolicitante(txtSolicitante.getText());
                requisicao.setSolicitante(solicitante);
                //DATA SOLICITACAO
                requisicao.setDataSolicitacao(txtDataSolicitacao.getDate());
                //DATA PREVISAO
                //PEGAR VALORES PRODUCAO E LOGISTICA
                int diasTempoProduc = 0;
                int diasLogistica = 0;
                for (int i = 0; i < tbFornecedor.getRowCount(); i++) {
                    Boolean selecionadoTb = (Boolean) tbFornecedor.getValueAt(i, 9);

                    //VERIFICA SELECIONADO
                    if (selecionadoTb == null || selecionadoTb == false) {
                        selecionadoTb = false;
                    } else {
                        diasTempoProduc = (Integer) tbFornecedor.getValueAt(i, 5);
                        diasLogistica = (Integer) tbFornecedor.getValueAt(i, 6);
                    }
                }
                Format format = new Format();
                int dias = diasTempoProduc + diasLogistica;
                Date dataPrevisao = format.dataSomaDias(dataHoje, dias);

                requisicao.setDataPrevisaoEntrega(dataPrevisao);
                //BUSCAR PROJETO
                projetos.setProjeto((String) cbProjeto.getSelectedItem());
                projetos = projetosDao.localizarProjeto(projetos, nameDb);
                requisicao.setProjetos(projetos);
                //BUSCAR DESTINACAO
                destinacao.setDestinacao((String) cbDestinacao.getSelectedItem());
                destinacao = destinacaoDao.localizar(destinacao, nameDb);
                requisicao.setDestinacao(destinacao);
                //BUSCAR TIPO FRETE
                tipoFrete.setTipoFrete((String) cbTipoFrete.getSelectedItem());
                tipoFrete = tipoFreteDao.localizar(tipoFrete, nameDb);
                requisicao.setTipoFrete(tipoFrete);
                //DEFINIR STATUS
                statusRequisicao.setId(2);
                requisicao.setStatusRequisicao(statusRequisicao);
                //DEFINIR ARQUIVREQUISICAO
                statusArqRequisicao.setId(1);
                requisicao.setStatusArqRequisicao(statusArqRequisicao);
                //DEFINIR ETAPA REQUISICAO
                etapaRequisicao.setId(1);
                requisicao.setEtapaRequisicao(etapaRequisicao);
                //VINCULACAO
                /*if (cbVinculacao.getSelectedItem().equals("Selecione")) {
                    requisicao.setVinculacao(0);
                } else {
                    requisicao.setVinculacao(Integer.parseInt((String) cbVinculacao.getSelectedItem()));
                }*/
                //BUSCAR MOEDA
                moedas.setMoedaAbreviada((String) cbMoeda.getSelectedItem());
                moedas = moedasDao.localizar(moedas, nameDb);
                requisicao.setMoedas(moedas);
                //TIPO APROVADOR e APROVADOR
                Usuario aprovador = new Usuario();
                aprovador.setLogin((String) cbTipoAprovador.getSelectedItem());
                aprovador = usuarioDao.ObterNivelAprovador(aprovador, nameDb);

                if (aprovador.getNivel().getNomeNivel().equals("Aprovador Financeiro")) {
                    requisicao.setTipoAprovador("Aprovador Financeiro");
                    requisicao.setAprovador((String) cbTipoAprovador.getSelectedItem());
                } else {
                    requisicao.setTipoAprovador("Aprovador Tecnico");
                    requisicao.setAprovadorTecnico((String) cbTipoAprovador.getSelectedItem());
                }
                //JUSTIFICATIVA
                requisicao.setJustificativa(txtJustificativa.getText());

                //SALVAR REQUISICAO
                requisicoesDao.salvar(requisicao, nameDb);

                //SALVAR FORNECEDOR
                for (int i = 0; i < tbFornecedor.getRowCount(); i++) {
                    //ARMAZENAR VALORES TABELA
                    String fornecedorTb = (String) tbFornecedor.getValueAt(i, 0);
                    String telefoneTb = (String) tbFornecedor.getValueAt(i, 1);
                    String emailTb = (String) tbFornecedor.getValueAt(i, 2);
                    String contatoTb = (String) tbFornecedor.getValueAt(i, 3);
                    String infoTb = (String) tbFornecedor.getValueAt(i, 4);
                    int tempoProduc = (Integer) tbFornecedor.getValueAt(i, 5);
                    int logistica = (Integer) tbFornecedor.getValueAt(i, 6);
                    Double valorInicialTb = (Double) tbFornecedor.getValueAt(i, 7);
                    Double valorFinalTb = (Double) tbFornecedor.getValueAt(i, 8);
                    Boolean selecionadoTb = (Boolean) tbFornecedor.getValueAt(i, 9);

                    fornecedor = new Fornecedor();
                    fornecedor.setNomeFornecedor(fornecedorTb);
                    fornecedor.setTelefone(telefoneTb);
                    fornecedor.setEmail(emailTb);
                    fornecedor.setContato(contatoTb);
                    fornecedor.setInfoAdicionais(infoTb);
                    fornecedor.setTempoProducao(tempoProduc);
                    fornecedor.setLogistica(logistica);
                    fornecedor.setValorInicial(valorInicialTb);
                    fornecedor.setValorFinal(valorFinalTb);
                    //VERIFICA SELECIONADO
                    if (selecionadoTb == null || selecionadoTb == false) {
                        selecionadoTb = false;
                    } else {
                        selecionadoTb = true;
                    }

                    fornecedor.setEscolha(selecionadoTb);
                    fornecedor.setRequisicao(requisicao);

                    //VERIFICA ID REQ INICIAL
                    if (fornecedor.getRequisicao().getId() == 0) {
                        //Requisicoes req = new Requisicoes();
                        requisicao.setId(1);
                        fornecedor.setRequisicao(requisicao);
                    }

                    //METODO SALVAR FORNECEDOR
                    fornecedorDao.salvar(fornecedor, nameDb);
                }

                //SALVAR ITEM
                for (int i = 0; i < tbItens.getRowCount(); i++) {
                    //ARMAZENAR VALORES TABELA
                    String itemTb = (String) tbItens.getValueAt(i, 0);
                    String unidadeTb = (String) tbItens.getValueAt(i, 1);
                    Integer quantidadeTb = (Integer) tbItens.getValueAt(i, 2);
                    Double valorUnitarioTb = (Double) tbItens.getValueAt(i, 3);
                    Double valorTotalTb = (Double) tbItens.getValueAt(i, 4);
                    String descricaoTecnicaTb = (String) tbItens.getValueAt(i, 5);
                    String informacoesAdicionaisTb = (String) tbItens.getValueAt(i, 6);

                    item = new Item();
                    item.setNomeItem(itemTb);
                    item.setUnidade(unidadeTb);
                    item.setQuantidade(quantidadeTb);
                    item.setValorUnitario(valorUnitarioTb);
                    item.setValorTotal(valorTotalTb);
                    item.setDescricaoTecnica(descricaoTecnicaTb);
                    item.setInformacoesAdicionais(informacoesAdicionaisTb);
                    item.setRequisicoes(requisicao);
                    item.setProdutoERP(produtoERP);

                    itemDao.salvar(item, nameDb);
                }

                //CRIAR PASTA ORDENS DE COMPRAS
                //SETAR NIVEL
                Niveis nivel = new Niveis();
                nivel.setNomeNivel(lbNivel.getText());
                usuario.setNivel(nivel);
                requisicao.setUsuario(usuario);
                //VERIFICA
                VerificaParametro verifica = new VerificaParametro();
                verifica.VerficaNameDBCriaPastaRC(nameDb, requisicao);
                JOptionPane.showMessageDialog(this, "Requisição " + requisicao.getId() + " Enviada com sucesso!");

                //SEND EMAIL APROVADOR TECNICO
                if (requisicao.getTipoAprovador().equals("Aprovador Tecnico")) {
                    SendMail mail = new SendMail();
                    //int id = Integer.parseInt(txtCodSolicTitulo.getText());
                    //BUSCAR EMAIL    
                    usuario.setLogin(txtRequisitante.getText());
                    requisicao.setUsuario(usuario);
                    mail.sendMailRequisicaoTecnico(requisicao, nameDb);
                }
                //LIMPAR CAMPOS
                limpaCamppos();

            } catch (Exception ex) {
                logger = Definirlogger();
                logger.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                //LOG
//                LogArquivoTexto log = new LogArquivoTexto();
//                String classe = TelaRequisicaoNova.class.getName();
//                String texto = usuario.getLogin() + " " + classe + "\n " + "ERRO: " + ex;
//                try {
//                    log.escreverGeral(texto, nameDb);
//                } catch (Exception ex1) {
//                    Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
//                }
            }

        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btEnviarActionPerformed

    private void btArquivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArquivarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA LIMPAR! " + "\n", "TODOS OS CAMPOS SERÃO RESETADOS!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            limpaCamppos();

            JOptionPane.showMessageDialog(this, "Limpeza realizada com sucesso!");

        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btArquivarActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        try {
            //TELA REQ
            TelaListaRequisicao tela = new TelaListaRequisicao();
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
            java.util.logging.Logger.getLogger(TelaRequisicaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRequisicaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                try {
                    new TelaRequisicaoNova().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddLinhaFornecedor;
    private javax.swing.JButton btAddLinhaItem;
    private javax.swing.JMenuItem btAlterarSenha;
    private javax.swing.JMenuItem btArquivar;
    private javax.swing.JMenuItem btEnviar;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JMenu btLimpar;
    private javax.swing.JButton btRemoverLinhaFornecedor;
    private javax.swing.JButton btRemoverLinhaItem;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JComboBox<String> cbDestinacao;
    private javax.swing.JComboBox<String> cbMoeda;
    private javax.swing.JComboBox<String> cbProjeto;
    private javax.swing.JComboBox<String> cbTipoAprovador;
    private javax.swing.JComboBox<String> cbTipoFrete;
    private javax.swing.JComboBox<String> cbTipoRequisicao;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCodigoRequisicao;
    private javax.swing.JLabel lbCodigoRequisicao1;
    private javax.swing.JLabel lbCodigoRequisicao2;
    private javax.swing.JLabel lbCodigoRequisicao3;
    private javax.swing.JLabel lbDataRequisicao;
    private javax.swing.JLabel lbDataSolicitacao;
    private javax.swing.JLabel lbDestinacao;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbProjeto;
    private javax.swing.JLabel lbRequisitante;
    private javax.swing.JLabel lbSolicitante;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTipoFrete;
    private javax.swing.JLabel lbTipoFrete1;
    private javax.swing.JLabel lbTipoRequisicao;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTituloFornecedor;
    private javax.swing.JLabel lbTituloItens;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTable tbFornecedor;
    private javax.swing.JTable tbItens;
    private javax.swing.JTextField txtCodRequisicao;
    private com.toedter.calendar.JDateChooser txtDataEntrega;
    private com.toedter.calendar.JDateChooser txtDataRequisicao;
    private com.toedter.calendar.JDateChooser txtDataSolicitacao;
    private javax.swing.JTextArea txtJustificativa;
    private javax.swing.JTextField txtRequisitante;
    private javax.swing.JTextField txtSolicitante;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
