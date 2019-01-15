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
import model.Destinacao;
import model.Format;
import model.Fornecedor;
import model.Icone;
import model.Item;
import model.LogArquivoTexto;
import model.Moedas;
import model.OrdemPagamento;
import model.Projetos;
import model.Report;
import model.Requisicoes;
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
import view.list.TelaListaRequisicaoAprovacao;
import view.list.TelaListaSolicitacoes;

/**
 *
 * @author felipe.ferreira
 */
public class TelaSolicitacaoCompra extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    private String nameDb;

    public TelaSolicitacaoCompra() {
        initComponents();
        AlteraFontTable();
        MaximizeTela();
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

        //txtRequisitante.setText(login.getLogin());
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

    //VERIFICA STATUS
    public void verificaStatus() throws SQLException, ClassNotFoundException {

        String status = txtStatus.getText();
        if (status.equals("Enviada")) {
            bloquearCamposEnviada();
        } else if (status.equals("Aprovada")) {
            bloquearCamposAprovada();
        } else if (status.equals("Aprovada Tecnico")) {
            bloquearCamposEnviada();
        } else if (status.equals("Recusada")) {
            bloquearCamposRecusada();
        } else if (status.equals("Finalizada")) {
            bloquearCamposFinalizada();
        } else if (status.equals("Arquivada")) {
            bloquearCamposArquivada();
        } else if (status.equals("Desarquivada")) {
            bloquearCamposRecusada();
        } else {
            bloquearCamposEnviada();
        }

    }

    //CARREGAR SOLICITACAO P/ APROVACAO
    public void carregarSolicitacao(Solicitacoes solicitacao) throws SQLException, ClassNotFoundException {
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
            List listaSolicitacao, listaFornecedor, listaItem;

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
            //BUSCAR SOL
            solicitacao = solicitacaoDao.localizarSolicitacoesCompra(solicitacao, nameDb);

            //ATRIBUIR VALORES REQUISICAO        
            txtStatus.setText(solicitacao.getStatusRequisicao().getStatusRequisicao());
            txtCodSolicTitulo.setText(Integer.toString(solicitacao.getId()));

            //VERIFICA STATUS
            verificaStatus();
            //txtDataRequisicao.setDate(solicitacao.getDataCriacao());
            //txtDataEntrega.setDate(solicitacao.getDataEntrega());
            cbTipoRequisicao.setSelectedItem(solicitacao.getTipoRequisicao().getTipoRequisicao());
            txtCodRequisicao.setText(Integer.toString(solicitacao.getId()));
            txtRequisitante.setText(solicitacao.getUsuario().getLogin());
            txtSolicitante.setText(solicitacao.getSolicitante().getNomeSolicitante());
            txtDataSolicitacao.setDate(solicitacao.getDataSolicitacao());
            cbProjeto.setSelectedItem(solicitacao.getProjetos().getProjeto());
            //cbDestinacao.setSelectedItem(solicitacao.getDestinacao().getDestinacao());
            //cbTipoFrete.setSelectedItem(solicitacao.getTipoFrete().getTipoFrete());
            //cbVinculacao.setSelectedItem(requisicao.getVinculacao());
            //cbMoeda.setSelectedItem(solicitacao.getMoedas().getMoedaAbreviada());
            //String aprovador = solicitacao.getTipoAprovador();

            /*if (aprovador.equals("Aprovador Financeiro")) {
            cbTipoAprovador.setSelectedItem("Financeiro");
        } else {
            cbTipoAprovador.setSelectedItem("Tecnico");
        }*/
            txtJustificativa.setText(solicitacao.getJustificativa());

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

            //MODEL
            TipoRequisicao tipoRequisicao;
            Destinacao destinacao;
            TipoFrete tipoFrete;
            Projetos projetos;
            Moedas moedas;

            //LISTAS
            List listTipoRequisicao = tipoReqDao.ObterTipoRequisicao(nameDb);
            List listDestinacao = destinacaoDao.ObterDestinacao(nameDb);
            List listTipoFrete = tipoFreteDao.ObterTipoFrete(nameDb);
            List listProjetos = projetosDao.ObterProjeto(nameDb);
            List listMoedas = moedasDao.ObterMoedas(nameDb);

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
        btDesarquivar.setEnabled(false);
        btCancelar.setEnabled(true);
        btFinalizar.setEnabled(true);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtRequisitante.setEnabled(false);
        txtSolicitante.setEnabled(false);
        txtDataSolicitacao.setEnabled(false);
        cbProjeto.setEnabled(false);
        cbDestinacao.setEnabled(false);
        cbTipoFrete.setEnabled(false);
        txtDataEntrega.setEnabled(true);
        cbMoeda.setEnabled(false);
        cbTipoAprovador.setEnabled(false);
        txtJustificativa.setEnabled(true);
        txtMotivo.setEnabled(false);
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
        btDesarquivar.setEnabled(false);
        btCancelar.setEnabled(false);
        btFinalizar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtRequisitante.setEnabled(false);
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
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(false);
        btDesarquivar.setEnabled(false);
        btCancelar.setEnabled(false);
        btFinalizar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtRequisitante.setEnabled(false);
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

    public void bloquearCamposFinalizada() {
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(false);
        btDesarquivar.setEnabled(false);
        btCancelar.setEnabled(false);
        btFinalizar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtRequisitante.setEnabled(false);
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

    public void bloquearCamposArquivada() {
        btEnviar.setEnabled(false);
        btArquivar.setEnabled(false);
        btDesarquivar.setEnabled(true);
        btCancelar.setEnabled(false);
        btFinalizar.setEnabled(false);
        txtDataRequisicao.setEnabled(false);
        cbTipoRequisicao.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtRequisitante.setEnabled(false);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbFornecedor1 = new javax.swing.JTable();
        lbDataRequisicao1 = new javax.swing.JLabel();
        cbTipoRequisicao1 = new javax.swing.JComboBox<>();
        lbTipoRequisicao1 = new javax.swing.JLabel();
        txtCodRequisicao1 = new javax.swing.JTextField();
        lbCodigoRequisicao4 = new javax.swing.JLabel();
        lbRequisitante1 = new javax.swing.JLabel();
        txtSolicitante1 = new javax.swing.JTextField();
        lbSolicitante1 = new javax.swing.JLabel();
        lbDataSolicitacao1 = new javax.swing.JLabel();
        cbProjeto1 = new javax.swing.JComboBox<>();
        lbProjeto1 = new javax.swing.JLabel();
        cbDestinacao1 = new javax.swing.JComboBox<>();
        lbDestinacao1 = new javax.swing.JLabel();
        cbTipoFrete1 = new javax.swing.JComboBox<>();
        lbTipoFrete2 = new javax.swing.JLabel();
        lbTituloFornecedor1 = new javax.swing.JLabel();
        lbTipoFrete3 = new javax.swing.JLabel();
        cbVinculacao1 = new javax.swing.JComboBox<>();
        lbCodigoRequisicao5 = new javax.swing.JLabel();
        btAbirRc1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtJustificativa1 = new javax.swing.JTextArea();
        lbCodigoRequisicao6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbItens1 = new javax.swing.JTable();
        lbTituloItens1 = new javax.swing.JLabel();
        cbMoeda1 = new javax.swing.JComboBox<>();
        lbTitulo1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbUsuario1 = new javax.swing.JLabel();
        lbLogin1 = new javax.swing.JLabel();
        lbNivel1 = new javax.swing.JLabel();
        lbCodigoRequisicao7 = new javax.swing.JLabel();
        cbTipoAprovador1 = new javax.swing.JComboBox<>();
        txtDataRequisicao1 = new com.toedter.calendar.JDateChooser();
        txtDataSolicitacao1 = new com.toedter.calendar.JDateChooser();
        txtRequisitante1 = new javax.swing.JTextField();
        menu1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        MenuRequisicao1 = new javax.swing.JMenu();
        btAprova = new javax.swing.JMenuItem();
        btRecusar = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btVoltar1 = new javax.swing.JMenuItem();
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
        btFinalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtJustificativa = new javax.swing.JTextArea();
        lbCodigoRequisicao2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbItens = new javax.swing.JTable();
        lbTituloItens = new javax.swing.JLabel();
        cbMoeda = new javax.swing.JComboBox<>();
        btAddLinhaItem = new javax.swing.JButton();
        btAddLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaFornecedor = new javax.swing.JButton();
        btRemoverLinhaItem = new javax.swing.JButton();
        txtCodSolicTitulo = new javax.swing.JLabel();
        cbTipoAprovador = new javax.swing.JComboBox<>();
        lbCodigoRequisicao3 = new javax.swing.JLabel();
        txtDataRequisicao = new com.toedter.calendar.JDateChooser();
        txtDataSolicitacao = new com.toedter.calendar.JDateChooser();
        txtRequisitante = new javax.swing.JTextField();
        txtDataEntrega = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbCodigoRequisicao8 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        jMenuEmpresa = new javax.swing.JMenu();
        MenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btAlterarSenha = new javax.swing.JMenuItem();
        MenuRequisicao = new javax.swing.JMenu();
        btEnviar = new javax.swing.JMenuItem();
        btArquivar = new javax.swing.JMenuItem();
        btDesarquivar = new javax.swing.JMenuItem();
        btCancelar = new javax.swing.JMenuItem();
        btDesCancelar = new javax.swing.JMenuItem();
        MenuVoltar = new javax.swing.JMenu();
        btVoltar = new javax.swing.JMenuItem();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbFornecedor1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbFornecedor1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Telefone", "Email", "Contato", "Valor Total", "Selecionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbFornecedor1);

        lbDataRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataRequisicao1.setText("Data da Requisição:");

        cbTipoRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoRequisicao1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbTipoRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoRequisicao1.setText("Tipo de Requisição:");

        txtCodRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCodigoRequisicao4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao4.setText("Código da Requisição:");

        lbRequisitante1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbRequisitante1.setText("Requisitante:");

        txtSolicitante1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbSolicitante1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSolicitante1.setText("Solicitante:");

        lbDataSolicitacao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataSolicitacao1.setText("Data da Solicitação:");

        cbProjeto1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbProjeto1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbProjeto1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbProjeto1.setText("Projeto:");

        cbDestinacao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbDestinacao1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbDestinacao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDestinacao1.setText("Destinação:");

        cbTipoFrete1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoFrete1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbTipoFrete2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete2.setText("Tipo de Frete:");

        lbTituloFornecedor1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTituloFornecedor1.setText("POSSÍVEIS FORNECEDORES");

        lbTipoFrete3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoFrete3.setText("Vinculação:");

        cbVinculacao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbVinculacao1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbCodigoRequisicao5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao5.setText("Moeda:");

        btAbirRc1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAbirRc1.setText("Abrir RC");

        txtJustificativa1.setColumns(20);
        txtJustificativa1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJustificativa1.setRows(5);
        jScrollPane5.setViewportView(txtJustificativa1);

        lbCodigoRequisicao6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao6.setText("Justificativa da Solicitação:");

        tbItens1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbItens1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Unidade", "Quantidade", "Valor Unitário", "Valor Total", "Descrição Técnica", "Informações Adicionais"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbItens1);

        lbTituloItens1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTituloItens1.setText("ITENS");

        cbMoeda1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbMoeda1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        lbTitulo1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo1.setText("REQUISIÇÃO DE COMPRA (APROVAÇÃO)");

        lbUsuario1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario1.setText("Usuário:");
        lbUsuario1.setOpaque(true);

        lbLogin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLogin1.setOpaque(true);

        lbNivel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNivel1.setOpaque(true);

        lbCodigoRequisicao7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao7.setText("Tipo Aprovador:");

        cbTipoAprovador1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoAprovador1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Administrativo", "Tecnico" }));

        txtDataRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataSolicitacao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtRequisitante1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        menu1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jMenu4.setText("File");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem5.setText("Trocar Usuário");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem6.setText("Trocar Sistema");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        menu1.add(jMenu4);

        MenuRequisicao1.setText("Requisição");
        MenuRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btAprova.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        btAprova.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAprova.setText("Aprovar");
        btAprova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAprovaActionPerformed(evt);
            }
        });
        MenuRequisicao1.add(btAprova);

        btRecusar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        btRecusar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btRecusar.setText("Recusar");
        btRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRecusarActionPerformed(evt);
            }
        });
        MenuRequisicao1.add(btRecusar);

        menu1.add(MenuRequisicao1);

        jMenu6.setText("Voltar");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVoltar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        btVoltar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVoltar1.setText("Voltar");
        btVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar1ActionPerformed(evt);
            }
        });
        jMenu6.add(btVoltar1);

        menu1.add(jMenu6);

        jFrame1.setJMenuBar(menu1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane4)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                                .addComponent(lbTitulo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbUsuario1)
                                .addGap(6, 6, 6)
                                .addComponent(lbLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCodigoRequisicao4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRequisitante1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSolicitante1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataSolicitacao1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbProjeto1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDestinacao1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTipoFrete2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSolicitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDataSolicitacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbProjeto1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDestinacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoFrete1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoRequisicao5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbCodigoRequisicao6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbTipoFrete3, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(txtRequisitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbVinculacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbMoeda1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoRequisicao7)
                                    .addComponent(btAbirRc1))
                                .addGap(10, 10, 10)
                                .addComponent(cbTipoAprovador1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 161, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                        .addGap(589, 589, 589)
                        .addComponent(lbTituloItens1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addComponent(lbTituloFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo1)
                    .addComponent(lbLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsuario1))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDataRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoFrete3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbVinculacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAbirRc1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTipoRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigoRequisicao5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMoeda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTipoAprovador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCodigoRequisicao7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addComponent(lbCodigoRequisicao4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbRequisitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRequisitante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(lbSolicitante1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodRequisicao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCodigoRequisicao6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addComponent(txtSolicitante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addComponent(txtDataSolicitacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbProjeto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbDestinacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(cbTipoFrete1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addComponent(lbDataSolicitacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbProjeto1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbDestinacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lbTipoFrete2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5)
                .addComponent(lbTituloFornecedor1)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbTituloItens1)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("SOLICITAÇÃO DE COMPRA - ");

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
                "Fornecedor", "Telefone", "Email", "Contato", "Info. Adicionais", "Tempo Produção", "logística", "Valor Inicial", "Valor Final", "Selecionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
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

        btFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btFinalizar.setText("Finalizar");
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        txtJustificativa.setColumns(20);
        txtJustificativa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJustificativa.setRows(5);
        jScrollPane2.setViewportView(txtJustificativa);

        lbCodigoRequisicao2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao2.setText("Justificativa da Solicitação:");

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

        txtCodSolicTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N

        cbTipoAprovador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoAprovador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Financeiro", "Tecnico" }));

        lbCodigoRequisicao3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao3.setText("Tipo Aprovador:");

        txtDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataSolicitacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtRequisitante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMotivo.setRows(5);
        jScrollPane7.setViewportView(txtMotivo);

        lbCodigoRequisicao8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCodigoRequisicao8.setText("Recusar Solicitação Motivo:");

        menu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jMenuEmpresa.setForeground(new java.awt.Color(0, 102, 255));
        jMenuEmpresa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        menu.add(jMenuEmpresa);

        MenuFile.setText("File");
        MenuFile.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuFile.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem2.setText("Trocar Usuário");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MenuFile.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem3.setText("Trocar Sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MenuFile.add(jMenuItem3);

        btAlterarSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAlterarSenha.setText("Alterar Senha");
        btAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarSenhaActionPerformed(evt);
            }
        });
        MenuFile.add(btAlterarSenha);

        menu.add(MenuFile);

        MenuRequisicao.setText("Solicitação");
        MenuRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btEnviar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        btEnviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });
        MenuRequisicao.add(btEnviar);

        btArquivar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        btArquivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btArquivar.setText("Arquivar");
        btArquivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArquivarActionPerformed(evt);
            }
        });
        MenuRequisicao.add(btArquivar);

        btDesarquivar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        btDesarquivar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btDesarquivar.setText("Desarquivar");
        btDesarquivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesarquivarActionPerformed(evt);
            }
        });
        MenuRequisicao.add(btDesarquivar);

        btCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        MenuRequisicao.add(btCancelar);

        btDesCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        btDesCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btDesCancelar.setText("Descancelar");
        btDesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesCancelarActionPerformed(evt);
            }
        });
        MenuRequisicao.add(btDesCancelar);

        menu.add(MenuRequisicao);

        MenuVoltar.setText("Voltar");
        MenuVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        btVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        MenuVoltar.add(btVoltar);

        menu.add(MenuVoltar);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodSolicTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(lbUsuario)
                        .addGap(6, 6, 6)
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbStatus)
                        .addGap(6, 6, 6)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(558, 558, 558)
                                .addComponent(lbTituloFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
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
                        .addGap(0, 0, Short.MAX_VALUE))
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
                            .addComponent(txtRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbTipoFrete1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbCodigoRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbCodigoRequisicao2, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(lbCodigoRequisicao8)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(lbCodigoRequisicao3))
                                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTipoAprovador, 0, 161, Short.MAX_VALUE)
                                    .addComponent(btFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane7))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsuario)
                    .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodSolicTitulo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbTitulo)))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDataRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTipoFrete1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCodigoRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRequisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigoRequisicao2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addGap(25, 25, 25)
                                .addComponent(lbCodigoRequisicao8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTituloItens)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAddLinhaItem)
                            .addComponent(btRemoverLinhaItem))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbTitulo, txtCodSolicTitulo});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btFinalizar, cbMoeda, cbTipoAprovador, lbCodigoRequisicao1, lbCodigoRequisicao2, lbCodigoRequisicao3, lbCodigoRequisicao8, lbTipoFrete1, txtDataEntrega});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbDestinacao, cbProjeto, cbTipoFrete, cbTipoRequisicao, lbCodigoRequisicao, lbDataRequisicao, lbDataSolicitacao, lbDestinacao, lbProjeto, lbRequisitante, lbSolicitante, lbTipoFrete, lbTipoRequisicao, txtCodRequisicao, txtDataRequisicao, txtDataSolicitacao, txtRequisitante, txtSolicitante});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbUsuario});

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
            Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVoltarActionPerformed

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
                double verifica = 0;

                if (tbFornecedor.getValueAt(i, 0).equals("")) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Fornecedor! (Linha " + numFornecedor + ")");
                    valida = false;
                }
                /*
                try {
                    if (tbFornecedor.getValueAt(i, 5).equals(verifica)) {
                        JOptionPane.showMessageDialog(this, "Favor preencher a tabela FORNECEDORES, coluna Valor Inicial! (Linha " + numFornecedor + ")");
                        valida = false;
                    }
                    if (tbFornecedor.getValueAt(i, 6).equals(verifica)) {
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
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Quantidade! (Linha " + numItem + ")");
                    valida = false;
                }
                try {
                    if (tbItens.getValueAt(i, 3).equals(verifica)) {
                        valida = false;
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Favor preencher a tabela ITENS, coluna Valor Unitário! (Linha " + numItem + ")");
                    valida = false;
                }
                try {
                    if (tbItens.getValueAt(i, 4).equals(verifica)) {
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
                //TIPO APROVADOR
                if (cbTipoAprovador.getSelectedItem().equals("Financeiro")) {
                    requisicao.setTipoAprovador("Aprovador Financeiro");
                } else {
                    requisicao.setTipoAprovador("Aprovador Tecnico");
                }
                //JUSTIFICATIVA
                requisicao.setJustificativa(txtJustificativa.getText());

                //SALVAR REQUISICAO
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                requisicoesDao.update(requisicao, nameDb);
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

                    itemDao.salvar(item, nameDb);
                }

                //CRIAR PASTA ORDENS DE COMPRAS
                VerificaParametro verifica = new VerificaParametro();
                verifica.VerficaNameDBCriaPastaRC(nameDb, requisicao);

                //UPDATE STATUS SOLICITACAO
                SolicitacoesDAO solicitacaoDao = new SolicitacoesDAO();
                Solicitacoes solicitacao = new Solicitacoes();
                solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
                solicitacaoDao.updateStatus(solicitacao, nameDb);

                //VOLTAR
                btVoltarActionPerformed(evt);

            } catch (SQLException ex) {
                Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TelaSolicitacaoNova.class.getName()).log(Level.SEVERE, null, ex);
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
    }//GEN-LAST:event_btEnviarActionPerformed

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
            JOptionPane.showMessageDialog(this, "Requisição arquivada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btArquivarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA CANCELAR A SOLICITACAO! " + "\n", "A SOLICITAÇÃO SERÁ CANCELADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
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
            JOptionPane.showMessageDialog(this, "Requisição cancelada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
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
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            TelaSelecioneAno tela = new TelaSelecioneAno();
            this.setVisible(false);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btAprovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAprovaActionPerformed
        RequisicoesDAO requisicaoDao = new RequisicoesDAO();
        Requisicoes requisicao = new Requisicoes();
        requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
        requisicao.setAprovador(lbLogin.getText());
        try {
            requisicaoDao.aprovarRequisicao(requisicao, nameDb);
            //IMPRIMIR RELATORIO
            Report report = new Report();
            report.geraRelatorioRC(requisicao, nameDb);

            //VOLTAR
            btVoltarActionPerformed(evt);
        } catch (SQLException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAprovaActionPerformed

    private void btRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRecusarActionPerformed
        RequisicoesDAO requisicaoDao = new RequisicoesDAO();
        Requisicoes requisicao = new Requisicoes();
        requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
        requisicao.setAprovador(lbLogin.getText());
        try {
            requisicaoDao.recusarRequisicao(requisicao, nameDb);
            btVoltarActionPerformed(evt);
        } catch (SQLException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btRecusarActionPerformed

    private void btVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar1ActionPerformed
        try {
            //TELA REQ
            TelaListaRequisicaoAprovacao tela = new TelaListaRequisicaoAprovacao();
            this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaRequisicaoAprovacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVoltar1ActionPerformed

    private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //DATA ENTREGA
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar entregaCalendar = txtDataEntrega.getCalendar();
        Date entregaDate = txtDataEntrega.getDate();
        try {
            String entregaSolic = sdf.format(entregaDate);
        } catch (NullPointerException e) {
            msgErro += "- Favor preencher campo Data da Entrega";
            valida = false;
        }

        if (valida) {
            Object[] options = {"Confirmar", "Cancelar"};
            int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA FINALIZAR! " + "\n", "A REQUISIÇÃO SERÁ FINALIZADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (opcao == 0) {
                RequisicoesDAO requisicaoDao = new RequisicoesDAO();
                Requisicoes requisicao = new Requisicoes();
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                requisicao.setDataEntrega(txtDataEntrega.getDate());
                try {
                    requisicaoDao.finalizarRequisicao(requisicao, nameDb);
                    JOptionPane.showMessageDialog(this, "Requisição finalizada com sucesso!");
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

            } else {
                JOptionPane.showMessageDialog(this, "Cancelado");
            }
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btFinalizarActionPerformed

    private void btDesarquivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesarquivarActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA DESARQUIVAR! " + "\n", "A SOLICITAÇÃO SERÁ DESARQUIVADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
            Solicitacoes solicitacao = new Solicitacoes();
            solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
            try {
                solicitacoesDao.desarquivarSolicitacao(solicitacao, nameDb);
                JOptionPane.showMessageDialog(this, "Solicitação desarquivada com sucesso!");
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

        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btDesarquivarActionPerformed

    private void btAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarSenhaActionPerformed
        try {
            //TELA INF FINC
            TelaAlterarSenha tela = new TelaAlterarSenha();
            //this.setVisible(false);
            tela.ObterLogin(obterLogin);
            tela.nameDb(nameDb);
            tela.mostrarTela(mostrarTela);
        } catch (SQLException ex) {
            Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAlterarSenhaActionPerformed

    private void btDesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesCancelarActionPerformed
        Object[] options = {"Confirmar", "Descancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA DEFINIR SOLICITACAO COM STATUS NOVA! " + "\n", "A SOLICITAÇÃO SERÁ CANCELADA!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            SolicitacoesDAO solicitacoesDao = new SolicitacoesDAO();
            Solicitacoes solicitacao = new Solicitacoes();
            solicitacao.setId(Integer.parseInt(txtCodSolicTitulo.getText()));
            try {
                solicitacoesDao.desCancelarSolicitacao(solicitacao, nameDb);
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
            JOptionPane.showMessageDialog(this, "Requisição descancelada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btDesCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSolicitacaoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaSolicitacaoCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuRequisicao;
    private javax.swing.JMenu MenuRequisicao1;
    private javax.swing.JMenu MenuVoltar;
    private javax.swing.JButton btAbirRc1;
    private javax.swing.JButton btAddLinhaFornecedor;
    private javax.swing.JButton btAddLinhaItem;
    private javax.swing.JMenuItem btAlterarSenha;
    private javax.swing.JMenuItem btAprova;
    private javax.swing.JMenuItem btArquivar;
    private javax.swing.JMenuItem btCancelar;
    private javax.swing.JMenuItem btDesCancelar;
    private javax.swing.JMenuItem btDesarquivar;
    private javax.swing.JMenuItem btEnviar;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JMenuItem btRecusar;
    private javax.swing.JButton btRemoverLinhaFornecedor;
    private javax.swing.JButton btRemoverLinhaItem;
    private javax.swing.JMenuItem btVoltar;
    private javax.swing.JMenuItem btVoltar1;
    private javax.swing.JComboBox<String> cbDestinacao;
    private javax.swing.JComboBox<String> cbDestinacao1;
    private javax.swing.JComboBox<String> cbMoeda;
    private javax.swing.JComboBox<String> cbMoeda1;
    private javax.swing.JComboBox<String> cbProjeto;
    private javax.swing.JComboBox<String> cbProjeto1;
    private javax.swing.JComboBox<String> cbTipoAprovador;
    private javax.swing.JComboBox<String> cbTipoAprovador1;
    private javax.swing.JComboBox<String> cbTipoFrete;
    private javax.swing.JComboBox<String> cbTipoFrete1;
    private javax.swing.JComboBox<String> cbTipoRequisicao;
    private javax.swing.JComboBox<String> cbTipoRequisicao1;
    private javax.swing.JComboBox<String> cbVinculacao1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenuEmpresa;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCodigoRequisicao;
    private javax.swing.JLabel lbCodigoRequisicao1;
    private javax.swing.JLabel lbCodigoRequisicao2;
    private javax.swing.JLabel lbCodigoRequisicao3;
    private javax.swing.JLabel lbCodigoRequisicao4;
    private javax.swing.JLabel lbCodigoRequisicao5;
    private javax.swing.JLabel lbCodigoRequisicao6;
    private javax.swing.JLabel lbCodigoRequisicao7;
    private javax.swing.JLabel lbCodigoRequisicao8;
    private javax.swing.JLabel lbDataRequisicao;
    private javax.swing.JLabel lbDataRequisicao1;
    private javax.swing.JLabel lbDataSolicitacao;
    private javax.swing.JLabel lbDataSolicitacao1;
    private javax.swing.JLabel lbDestinacao;
    private javax.swing.JLabel lbDestinacao1;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbLogin1;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNivel1;
    private javax.swing.JLabel lbProjeto;
    private javax.swing.JLabel lbProjeto1;
    private javax.swing.JLabel lbRequisitante;
    private javax.swing.JLabel lbRequisitante1;
    private javax.swing.JLabel lbSolicitante;
    private javax.swing.JLabel lbSolicitante1;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTipoFrete;
    private javax.swing.JLabel lbTipoFrete1;
    private javax.swing.JLabel lbTipoFrete2;
    private javax.swing.JLabel lbTipoFrete3;
    private javax.swing.JLabel lbTipoRequisicao;
    private javax.swing.JLabel lbTipoRequisicao1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTitulo1;
    private javax.swing.JLabel lbTituloFornecedor;
    private javax.swing.JLabel lbTituloFornecedor1;
    private javax.swing.JLabel lbTituloItens;
    private javax.swing.JLabel lbTituloItens1;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel lbUsuario1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuBar menu1;
    private javax.swing.JTable tbFornecedor;
    private javax.swing.JTable tbFornecedor1;
    private javax.swing.JTable tbItens;
    private javax.swing.JTable tbItens1;
    private javax.swing.JTextField txtCodRequisicao;
    private javax.swing.JTextField txtCodRequisicao1;
    private javax.swing.JLabel txtCodSolicTitulo;
    private com.toedter.calendar.JDateChooser txtDataEntrega;
    private com.toedter.calendar.JDateChooser txtDataRequisicao;
    private com.toedter.calendar.JDateChooser txtDataRequisicao1;
    private com.toedter.calendar.JDateChooser txtDataSolicitacao;
    private com.toedter.calendar.JDateChooser txtDataSolicitacao1;
    private javax.swing.JTextArea txtJustificativa;
    private javax.swing.JTextArea txtJustificativa1;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtRequisitante;
    private javax.swing.JTextField txtRequisitante1;
    private javax.swing.JTextField txtSolicitante;
    private javax.swing.JTextField txtSolicitante1;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
