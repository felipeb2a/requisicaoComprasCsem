package view;

import dao.DestinacaoDAO;
import dao.FornecedorDAO;
import dao.ItemDAO;
import dao.MoedasDAO;
import dao.ProjetosDAO;
import dao.RequisicoesDAO;
import dao.SolicitacoesDAO;
import dao.SolicitanteDAO;
import dao.StatusArqRequisicaoDAO;
import dao.StatusRequisicaoDAO;
import dao.TipoFreteDAO;
import dao.TipoRequisicaoDAO;
import dao.UsuarioDAO;
import java.awt.Color;
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
import javax.swing.UIManager;
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
import model.Requisicoes;
import model.SendMail;
import model.Solicitacoes;
import model.Solicitante;
import model.StatusArqRequisicao;
import model.StatusRequisicao;
import model.TipoFrete;
import model.TipoRequisicao;
import model.Usuario;
import model.VerificaParametro;
import view.initial.TelaAlterarSenha;
import view.initial.TelaLogin;
import view.initial.TelaSelecioneAno;
import view.list.TelaListaSolicitacoes;

/**
 *
 * @author felipe.ferreira
 */
public class TelaSolicitacaoNova extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    private String nameDb;
    private String Status = "Nova";
    private Logger logger = null;

    public TelaSolicitacaoNova() {
        initComponents();
        AlteraFontTable();
        MaximizeTela();
        txtDataEntrega.setEnabled(false);
        btFinaliza.setEnabled(false);
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
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException {
        this.nameDb = nameDb;
        jMenuEmpresa.setText(nameDb);
        icone();
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

    //LOGIN
    public void ObterLogin(Usuario login) {

        this.obterLogin = login;

        lbLogin.setText(login.getLogin());
        lbNivel.setText(login.getNivel().getNomeNivel());
        int id = login.getId();
        cbRequisitante.setSelectedItem(login.getLogin());

        this.setVisible(true);

        //txtRequisitante.setText(login.getLogin());
        lbLogin.setVisible(false);
        lbNivel.setVisible(false);
        lbUsuario.setVisible(false);
    }

    //DATA
    public void DefineData() throws ParseException {
        txtDataRequisicao.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);
        txtDataRequisicao.setDate(dataHoje);
    }

    //VERIFICA STATUS
    public void verificaStatus() {

        String status = txtStatus.getText();
        if (status.equals("Enviada")) {
            bloquearCamposEnviada();
        } else if (status.equals("Aprovada")) {
            bloquearCamposAprovada();
        } else if (status.equals("Recusada")) {
            bloquearCamposRecusada();
        } else if (status.equals("Finalizada")) {
            bloquearCamposFinalizada();
        } else if (status.equals("Nova")) {
            bloquearCamposNova();
        } else {
            bloquearCamposEnviada();
        }
    }

    //CARREGAR SOLICITACAO P/ APROVACAO
    public void carregarSolicitacao(Solicitacoes solicitacao) throws SQLException, ClassNotFoundException, ParseException {
        try {
            ListarCombobox();
            DefineData();
            CodRequisicao();
            txtCodSolicTitulo.setText(Integer.toString(solicitacao.getId()));
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
            SolicitacoesDAO solicitacaoDao = new SolicitacoesDAO();
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            ItemDAO itemDao = new ItemDAO();

            //BUSCAR FORNECEDOR
            fornecedor.setSolicitacoes(solicitacao);
            listaFornecedor = fornecedorDao.localizarFornecedorSolicitacao(fornecedor, nameDb);
            //BUSCAR ITEM
            item.setSolicitacoes(solicitacao);
            listaItem = itemDao.localizarItemSolicitacao(item, nameDb);
            //BUSCAR SOLICITACAO
            solicitacao = solicitacaoDao.localizarSolicitacoes(solicitacao, nameDb);
            //ATRIBUIR VALORES REQUISICAO
            txtStatus.setText(solicitacao.getStatusRequisicao().getStatusRequisicao());
            //txtCodReqTitulo.setText(Integer.toString(solicitacao.getId()));

            //VERIFICA STATUS
            verificaStatus();
            //txtDataRequisicao.setDate(solicitacao.getDataCriacao());
            cbTipoRequisicao.setSelectedItem(solicitacao.getTipoRequisicao().getTipoRequisicao());
            //txtCodRequisicao.setText(Integer.toString(solicitacao.getId()));
            txtSolicitante.setText(solicitacao.getSolicitante().getNomeSolicitante());
            txtDataSolicitacao.setDate(solicitacao.getDataSolicitacao());
            cbProjeto.setSelectedItem(solicitacao.getProjetos().getProjeto());
            txtJustificativa.setText(solicitacao.getJustificativa());
            txtMotivo.setText(solicitacao.getMotivo());
            //ATRIBUIR VALORES A TABLE FORNECEDOR
            for (Iterator it = listaFornecedor.iterator(); it.hasNext();) {

                fornecedor = (Fornecedor) it.next();
                Object linha[]
                        = {fornecedor.getNomeFornecedor(), fornecedor.getTelefone(), fornecedor.getEmail(), fornecedor.getContato(),
                            fornecedor.getInfoAdicionais(), 0, 0, fornecedor.getValorInicial(), fornecedor.getValorFinal(), fornecedor.getEscolha()};

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
            TipoRequisicaoDAO tipoReqDao = new TipoRequisicaoDAO();
            DestinacaoDAO destinacaoDao = new DestinacaoDAO();
            TipoFreteDAO tipoFreteDao = new TipoFreteDAO();
            ProjetosDAO projetosDao = new ProjetosDAO();
            MoedasDAO moedasDao = new MoedasDAO();
            UsuarioDAO usuarioDao = new UsuarioDAO();

            //MODEL
            TipoRequisicao tipoRequisicao;
            Destinacao destinacao;
            TipoFrete tipoFrete;
            Projetos projetos;
            Moedas moedas;
            Usuario usuario;

            //LISTAS
            List listTipoRequisicao = tipoReqDao.ObterTipoRequisicao(nameDb);
            List listDestinacao = destinacaoDao.ObterDestinacao(nameDb);
            List listTipoFrete = tipoFreteDao.ObterTipoFrete(nameDb);
            List listProjetos = projetosDao.ObterProjeto(nameDb);
            List listMoedas = moedasDao.ObterMoedas(nameDb);
            List listUsuario = usuarioDao.ObterNome(nameDb);
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

            //REQUISITANTE
            it = listUsuario.iterator();

            while (it.hasNext()) {
                usuario = (Usuario) it.next();
                String nome = usuario.getLogin();

                //INSERIR
                cbRequisitante.addItem(nome);
            }

            //APROVADOR
            it = listAprovador.iterator();

            while (it.hasNext()) {
                usuario = (Usuario) it.next();
                String nome = usuario.getLogin();

                //INSERIR
                cbTipoAprovador.addItem(nome);
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

    public void bloquearCamposAprovada() {
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(true);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        cbRequisitante.setEnabled(false);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(false);
        cbDestinacao.setEnabled(false);
        cbTipoFrete.setEnabled(false);
        txtDataEntrega.setEnabled(true);
        cbMoeda.setEnabled(false);
        cbTipoAprovador.setEnabled(false);
        txtJustificativa.setEnabled(false);
        btAddLinhaFornecedor.setEnabled(false);
        btRemoverLinhaFornecedor.setEnabled(false);
        tbFornecedor.setEnabled(false);
        tbItens.setEnabled(false);
        btAddLinhaItem.setEnabled(false);
        btRemoverLinhaItem.setEnabled(false);
    }

    public void bloquearCamposEnviada() {
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        cbRequisitante.setEnabled(false);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(false);
        cbDestinacao.setEnabled(false);
        cbTipoFrete.setEnabled(false);
        txtDataEntrega.setEnabled(false);
        cbMoeda.setEnabled(false);
        cbTipoAprovador.setEnabled(false);
        txtJustificativa.setEnabled(false);
        txtMotivo.setEnabled(false);
        btAddLinhaFornecedor.setEnabled(false);
        btRemoverLinhaFornecedor.setEnabled(false);
        tbFornecedor.setEnabled(false);
        tbItens.setEnabled(false);
        btAddLinhaItem.setEnabled(false);
        btRemoverLinhaItem.setEnabled(false);
    }

    public void bloquearCamposRecusada() {
        btEnviar.setEnabled(true);
        btArquivar.setEnabled(true);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        cbRequisitante.setEnabled(false);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(true);
        cbDestinacao.setEnabled(true);
        cbTipoFrete.setEnabled(true);
        txtDataEntrega.setEnabled(false);
        cbMoeda.setEnabled(true);
        cbTipoAprovador.setEnabled(true);
        txtJustificativa.setEnabled(true);
        txtMotivo.setEnabled(false);
        btAddLinhaFornecedor.setEnabled(true);
        btRemoverLinhaFornecedor.setEnabled(true);
        tbFornecedor.setEnabled(true);
        tbItens.setEnabled(true);
        btAddLinhaItem.setEnabled(true);
        btRemoverLinhaItem.setEnabled(true);
    }

    public void bloquearCamposFinalizada() {
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        cbRequisitante.setEnabled(false);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(false);
        cbDestinacao.setEnabled(false);
        cbTipoFrete.setEnabled(false);
        txtDataEntrega.setEnabled(false);
        cbMoeda.setEnabled(false);
        cbTipoAprovador.setEnabled(false);
        txtJustificativa.setEnabled(false);
        txtMotivo.setEnabled(false);
        btAddLinhaFornecedor.setEnabled(false);
        btRemoverLinhaFornecedor.setEnabled(false);
        tbFornecedor.setEnabled(false);
        tbItens.setEnabled(false);
        btAddLinhaItem.setEnabled(false);
        btRemoverLinhaItem.setEnabled(false);
    }

    public void bloquearCamposNova() {
        btEnviar.setEnabled(true);
        btArquivar.setEnabled(true);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        cbRequisitante.setEnabled(true);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(true);
        cbDestinacao.setEnabled(true);
        cbTipoFrete.setEnabled(true);
        txtDataEntrega.setEnabled(false);
        cbMoeda.setEnabled(true);
        cbTipoAprovador.setEnabled(true);
        txtJustificativa.setEnabled(true);
        txtMotivo.setEnabled(true);
        btAddLinhaFornecedor.setEnabled(true);
        btRemoverLinhaFornecedor.setEnabled(true);
        tbFornecedor.setEnabled(true);
        tbItens.setEnabled(true);
        btAddLinhaItem.setEnabled(true);
        btRemoverLinhaItem.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbUsuario = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFornecedor = new javax.swing.JTable();
        lbDataRequisicao = new javax.swing.JLabel();
        cbTipoRequisicao = new javax.swing.JComboBox<>();
        lbTipoRequisicao = new javax.swing.JLabel();
        txtCodRequisicao = new javax.swing.JTextField();
        lbCodigoRequisicao = new javax.swing.JLabel();
        lbRequisitante = new javax.swing.JLabel();
        txtSolicitante = new javax.swing.JTextField();
        lbSolicitante = new javax.swing.JLabel();
        lbDataSolicitacao = new javax.swing.JLabel();
        cbProjeto = new javax.swing.JComboBox<>();
        lbProjeto = new javax.swing.JLabel();
        cbDestinacao = new javax.swing.JComboBox<>();
        lbDestinacao = new javax.swing.JLabel();
        cbTipoFrete = new javax.swing.JComboBox<>();
        lbTipoFrete = new javax.swing.JLabel();
        lbTituloFornecedor = new javax.swing.JLabel();
        lbTipoFrete1 = new javax.swing.JLabel();
        lbCodigoRequisicao1 = new javax.swing.JLabel();
        btFinaliza = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbItens = new javax.swing.JTable();
        lbTituloItens = new javax.swing.JLabel();
        cbMoeda = new javax.swing.JComboBox<>();
        btAddLinhaItem = new javax.swing.JButton();
        btAddLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaItem = new javax.swing.JButton();
        cbTipoAprovador = new javax.swing.JComboBox<>();
        lbCodigoRequisicao3 = new javax.swing.JLabel();
        txtDataRequisicao = new com.toedter.calendar.JDateChooser();
        txtDataSolicitacao = new com.toedter.calendar.JDateChooser();
        txtCodSolicTitulo = new javax.swing.JLabel();
        txtDataEntrega = new com.toedter.calendar.JDateChooser();
        lbCodigoRequisicao2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtJustificativa = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbCodigoRequisicao8 = new javax.swing.JLabel();
        cbRequisitante = new javax.swing.JComboBox<>();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btAlterarSenha = new javax.swing.JMenuItem();
        jMenuRequisicaoCriar = new javax.swing.JMenu();
        btEnviar = new javax.swing.JMenuItem();
        btArquivar = new javax.swing.JMenuItem();
        btCancelar = new javax.swing.JMenuItem();
        btRecusar = new javax.swing.JMenuItem();
        btRecusar1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("SOLICITAÇÃO NOVA - ");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario.setText("Usuário:");

        lbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 102, 51));
        lbStatus.setText("Status:");

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(0, 102, 255));

        tbFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Telefone", "Email", "Contato", "Info. Adicionais", "Tempo Produção", "Logística", "Valor Inicial", "Valor Total", "Selecionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbFornecedor);

        lbDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataRequisicao.setText("Data da Requisição:");

        cbTipoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoRequisicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbTipoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoRequisicao.setText("Tipo de Requisição:");

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCodigoRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao.setText("Código da Requisição:");

        lbRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbRequisitante.setText("Requisitante:");

        txtSolicitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbSolicitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSolicitante.setText("Solicitante:");

        lbDataSolicitacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataSolicitacao.setText("Data da Solicitação:");

        cbProjeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbProjeto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbProjeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbProjeto.setText("Projeto:");

        cbDestinacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbDestinacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbDestinacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDestinacao.setText("Destinação:");

        cbTipoFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoFrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbTipoFrete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete.setText("Tipo de Frete:");

        lbTituloFornecedor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTituloFornecedor.setText("FORNECEDORES");

        lbTipoFrete1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete1.setText("Data da Entrega:");

        lbCodigoRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao1.setText("Moeda:");

        btFinaliza.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btFinaliza.setText("Finalizar");

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

        cbMoeda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbMoeda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

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

        cbTipoAprovador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoAprovador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbCodigoRequisicao3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao3.setText("Aprovador:");

        txtDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataSolicitacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCodSolicTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N

        txtDataEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCodigoRequisicao2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao2.setText("Justificativa da Solicitação:");

        txtJustificativa.setColumns(20);
        txtJustificativa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJustificativa.setRows(5);
        jScrollPane4.setViewportView(txtJustificativa);

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMotivo.setRows(5);
        jScrollPane2.setViewportView(txtMotivo);

        lbCodigoRequisicao8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao8.setText("Recusar Solicitação Motivo:");

        cbRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbRequisitante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

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

        jMenuRequisicaoCriar.setText("Solicitação");
        jMenuRequisicaoCriar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btEnviar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        btEnviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btEnviar);

        btArquivar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        btArquivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btArquivar.setText("Arquivar");
        btArquivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArquivarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btArquivar);

        btCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btCancelar);

        btRecusar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        btRecusar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRecusar.setText("Recusar");
        btRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRecusarActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btRecusar);

        btRecusar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK));
        btRecusar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRecusar1.setText("Encaminhar Compra");
        btRecusar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRecusar1ActionPerformed(evt);
            }
        });
        jMenuRequisicaoCriar.add(btRecusar1);

        menu.add(jMenuRequisicaoCriar);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodSolicTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbUsuario)
                                .addGap(6, 6, 6)
                                .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbStatus)
                                .addGap(6, 6, 6)
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(558, 558, 558)
                                .addComponent(lbTituloFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(btAddLinhaFornecedor)
                                .addGap(6, 6, 6)
                                .addComponent(btRemoverLinhaFornecedor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(589, 589, 589)
                                .addComponent(lbTituloItens, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)
                                .addComponent(btAddLinhaItem)
                                .addGap(6, 6, 6)
                                .addComponent(btRemoverLinhaItem)))
                        .addGap(0, 183, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataRequisicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCodigoRequisicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRequisitante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSolicitante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataSolicitacao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbProjeto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDestinacao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTipoFrete, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDataSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDestinacao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbTipoFrete1)
                                    .addComponent(lbCodigoRequisicao1)
                                    .addComponent(lbCodigoRequisicao8)
                                    .addComponent(lbCodigoRequisicao2)))
                            .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(lbCodigoRequisicao3))
                                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTipoAprovador, 0, 230, Short.MAX_VALUE)
                                    .addComponent(btFinaliza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsuario)
                    .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitulo)
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodSolicTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTipoFrete1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTipoRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigoRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTipoAprovador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCodigoRequisicao3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCodigoRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDataSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbDestinacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbTipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbDataSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbDestinacao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbTipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigoRequisicao2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigoRequisicao8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloFornecedor)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAddLinhaFornecedor)
                            .addComponent(btRemoverLinhaFornecedor))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloItens)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAddLinhaItem)
                            .addComponent(btRemoverLinhaItem))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btFinaliza, cbMoeda, cbTipoAprovador, lbCodigoRequisicao1, lbCodigoRequisicao3, lbTipoFrete1});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbDestinacao, cbProjeto, cbTipoFrete, cbTipoRequisicao, lbCodigoRequisicao, lbDataRequisicao, lbDataSolicitacao, lbDestinacao, lbProjeto, lbRequisitante, lbSolicitante, lbTipoFrete, lbTipoRequisicao, txtCodRequisicao, txtDataRequisicao, txtDataSolicitacao, txtSolicitante});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbUsuario});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbTitulo, txtCodSolicTitulo});

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
        if (cbRequisitante.getSelectedItem().equals("Selecione")) {
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
        /*if (month != solicMonth) {
            msgErro += "- Favor preencher o mês corretamente no campo Data da Solicitação\n";
            valida = false;
        }*/
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
                double verifica = 0;

                if (tbFornecedor.getValueAt(i, 0).equals("")) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Fornecedor! (Linha " + numFornecedor + ")");
                    valida = false;
                }

                /*try {
                    if (tbFornecedor.getValueAt(i, 6).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Inicial! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                    if (tbFornecedor.getValueAt(i, 7).equals(verifica)) {
                        count++;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Inicial! (Linha " + numFornecedor + ")");
                }*/
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
                msgErro += "Favor preencher pelo menos um campo na tabela FORNECEDORES, coluna Valor Final!";
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

            //LOG           
            logger = Definirlogger();
            
            //PROGRESS
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
                usuario.setLogin((String) cbRequisitante.getSelectedItem());
                usuario = usuarioDao.localizarIdUsuario(usuario, nameDb);
                requisicao.setUsuario(usuario);
                //SOLICITANTE
                solicitante.setNomeSolicitante(txtSolicitante.getText());
                requisicao.setSolicitante(solicitante);
                //DATA SOLICITACAO
                requisicao.setDataSolicitacao(txtDataSolicitacao.getDate());
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
                //VINCULACAO
                /*if (cbVinculacao.getSelectedItem().equals("Selecione")) {
                    requisicao.setVinculacao(0);
                } else {
                    requisicao.setVinculacao((Integer) cbVinculacao.getSelectedItem());
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

                    // VERIFICA ID REC INICIAL
                    if (fornecedor.getRequisicao().getId() == 0) {
                        // Solicitacoes solic = new Solicitacoes();
                        requisicao.setId(1);
                        fornecedor.setRequisicao(requisicao);
                    }

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
//                    item.setProdutoERP(produtoERP);

                    itemDao.salvar(item, nameDb);
                }

                //CRIAR PASTA ORDENS DE COMPRAS
                //CRIAR PASTA ORDENS DE COMPRAS
                //SETAR NIVEL
                Niveis nivel = new Niveis();
                nivel.setNomeNivel(lbNivel.getText());
                usuario.setNivel(nivel);
                requisicao.setUsuario(usuario);

                VerificaParametro verifica = new VerificaParametro();
                verifica.VerficaNameDBCriaPastaRC(nameDb, requisicao);

                //UPDATE STATUS SOLICITACAO
                SolicitacoesDAO solicitacaoDao = new SolicitacoesDAO();
                Solicitacoes solicitacao = new Solicitacoes();
                solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
                solicitacaoDao.updateStatus(solicitacao, nameDb);

                //SEND EMAIL APROVADOR TECNICO
                if (requisicao.getTipoAprovador().equals("Aprovador Tecnico")) {
                    SendMail mail = new SendMail();
                    int id = Integer.parseInt(txtCodSolicTitulo.getText());
                    //BUSCAR EMAIL    
                    usuario.setLogin((String) cbRequisitante.getSelectedItem());
                    requisicao.setUsuario(usuario);
                    mail.sendMailRequisicaoTecnico(requisicao, nameDb);
                }

                //VOLTAR
                btVoltarActionPerformed(evt);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                logger.log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                logger.log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                logger.log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                logger.log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERRO: " + ex);
                logger.log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btEnviarActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        try {
            //TELA REQ
            TelaListaSolicitacoes tela = new TelaListaSolicitacoes();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.Relatorio();
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btArquivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArquivarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA ARQUIVAR A SOLICITACAO! " + "\n", "A SOLICITAÇÃO SERÁ ARQUIVADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
            Solicitacoes solicitacao = new Solicitacoes();
            solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
            try {
                solicitacoesDao.arquivarSolicitacao(solicitacao, nameDb);
                btVoltarActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(this, "Solicitação arquivada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btArquivarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA CANCELADA A SOLICITACAO! " + "\n", "A SOLICITAÇÃO SERÁ CANCELADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
            Solicitacoes solicitacao = new Solicitacoes();
            solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
            try {
                solicitacoesDao.cancelarSolicitacao(solicitacao, nameDb);
                btVoltarActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(this, "Solicitação cancelada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRecusarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA RECUSAR A SOLICITACAO! " + "\n", "A SOLICITAÇÃO SERÁ RECUSADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
            Solicitacoes solicitacao = new Solicitacoes();
            solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
            solicitacao.setMotivo(txtMotivo.getText());
            try {
                solicitacoesDao.recusarSolicitacao(solicitacao, nameDb);
                SendMail mail = new SendMail();
                int id = Integer.parseInt(txtCodSolicTitulo.getText());
                mail.sendMailSolicitacaoRecusada(id, nameDb);
                btVoltarActionPerformed(evt);
            } catch (SQLException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(this, "Solicitação recusada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btRecusarActionPerformed

    private void btRecusar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRecusar1ActionPerformed
        boolean valida = true;
        String msgErro = "";
        String name = (String) cbRequisitante.getSelectedItem();
        if (cbRequisitante.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor preencher campo Requisitante\n";
            valida = false;
        }
        if (valida) {
            Object[] options = {"Confirmar", "Cancelar"};
            int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA ENCAMINHAR A SOLICITACAO P/\n" + name + " !" + "\n", "A SOLICITAÇÃO SERÁ ENCAMINHADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (opcao == 0) {
                SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
                Solicitacoes solicitacao = new Solicitacoes();
                solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
                UsuarioDAO usuarioDao = new UsuarioDAO();
                Usuario usuario = new Usuario();
                usuario.setLogin((String) cbRequisitante.getSelectedItem());

                try {
                    usuario = usuarioDao.localizarIdUsuario(usuario, nameDb);
                    solicitacao.setUsuario(usuario);
                    solicitacoesDao.alterarRequisitanteSolicitacao(solicitacao, nameDb);
                    btVoltarActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showMessageDialog(this, "Solicitação encaminhada P/ " + name + " com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Cancelado");
            }
        }
    }//GEN-LAST:event_btRecusar1ActionPerformed

    private void btAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarSenhaActionPerformed
        try {
            //TELA INF FINC
            TelaAlterarSenha tela = new TelaAlterarSenha();
            //this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaSolicitacaoNova().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddLinhaFornecedor;
    private javax.swing.JButton btAddLinhaItem;
    private javax.swing.JMenuItem btAlterarSenha;
    private javax.swing.JMenuItem btArquivar;
    private javax.swing.JMenuItem btCancelar;
    private javax.swing.JMenuItem btEnviar;
    private javax.swing.JButton btFinaliza;
    private javax.swing.JMenuItem btRecusar;
    private javax.swing.JMenuItem btRecusar1;
    private javax.swing.JButton btRemoverLinhaFornecedor;
    private javax.swing.JButton btRemoverLinhaItem;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JComboBox<String> cbDestinacao;
    private javax.swing.JComboBox<String> cbMoeda;
    private javax.swing.JComboBox<String> cbProjeto;
    private javax.swing.JComboBox<String> cbRequisitante;
    private javax.swing.JComboBox<String> cbTipoAprovador;
    private javax.swing.JComboBox<String> cbTipoFrete;
    private javax.swing.JComboBox<String> cbTipoRequisicao;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu jMenuRequisicaoCriar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCodigoRequisicao;
    private javax.swing.JLabel lbCodigoRequisicao1;
    private javax.swing.JLabel lbCodigoRequisicao2;
    private javax.swing.JLabel lbCodigoRequisicao3;
    private javax.swing.JLabel lbCodigoRequisicao8;
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
    private javax.swing.JLabel txtCodSolicTitulo;
    private com.toedter.calendar.JDateChooser txtDataEntrega;
    private com.toedter.calendar.JDateChooser txtDataRequisicao;
    private com.toedter.calendar.JDateChooser txtDataSolicitacao;
    private javax.swing.JTextArea txtJustificativa;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtSolicitante;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
