package view;

import dao.OrdemPagamentoDAO;
import model.TipoCobranca;
import dao.TipoCobrancaDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Format;
import model.Icone;
import model.LogArquivoTexto;
import model.Moeda;
import model.OrdemPagamento;
import model.Report;
import model.Requisicoes;
import model.Usuario;
import model.VerificaParametro;
import view.initial.TelaLogin;

/**
 *
 * @author felipe.ferreira
 */
public class TelaOrdemPagamento extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private Usuario obterLogin;
    //private String Status = "Nova";
    private String nameDb;
    private Integer numParcela;

    public TelaOrdemPagamento() {
        initComponents();
        //RESTRINGIR BOTAO FECHAR DO JFRAME
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        MaximizeTela();
        btAlterar.setEnabled(false);
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
        icone();
    }

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //DATA
    public void DefineData() throws ParseException {
        txtDataOrdem.setEnabled(false);
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);

        txtDataOrdem.setDate(dataHoje);
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

    public void Limpar() {
        txtParcela.setText("");
        txtValorPagar.setText("");
        txtDataVencimento.setDate(null);
        cbTipoCobranca.setSelectedItem("");
        txtNcm.setText("");
        txtPrevisaoEmbarque.setDate(null);
        txtComentário.setText("");
    }

    //LISTAR COMBOBOX
    public void ListarCombobox() throws SQLException, ClassNotFoundException {
        try {
            //DAO
            TipoCobrancaDAO tipoCobrancaDao = new TipoCobrancaDAO();

            //MODEL
            TipoCobranca tipoCobranca;

            //LISTAS
            List listTipoCobranca = tipoCobrancaDao.ObterTipoCobranca(nameDb);

            //TIPO REQUISICAO
            Iterator it = listTipoCobranca.iterator();

            while (it.hasNext()) {
                tipoCobranca = (TipoCobranca) it.next();
                String nome = tipoCobranca.getTipoCobranca();

                //INSERIR
                cbTipoCobranca.addItem(nome);
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

    //LISTAR ORDEMPAGAMENTO
    public void ListarComboboxOrdemPagamento(int id) throws SQLException, ClassNotFoundException {
        try {
            //DAO
            OrdemPagamentoDAO ordemPagamentoDao = new OrdemPagamentoDAO();

            //MODEL
            OrdemPagamento ordemPagamento = new OrdemPagamento();

            //LISTAS
            List listTOrdemPagamento = ordemPagamentoDao.ObterOrdemPagamento(id, nameDb);

            //ORDEM PAGAMENTO
            Iterator it = listTOrdemPagamento.iterator();

            while (it.hasNext()) {
                ordemPagamento = (OrdemPagamento) it.next();
                String nome = Integer.toString(ordemPagamento.getParcela());

                //INSERIR
                cbOrdemPagamento.addItem(nome);
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

    public void bloqueiaCampos() {
        btSalvar.setEnabled(false);
        //btCalc.setEnabled(false);
        btLimpar.setEnabled(false);
        txtParcela.setEnabled(false);
        txtValorPagar.setEnabled(false);
        txtDataVencimento.setEnabled(false);
        cbTipoCobranca.setEnabled(false);
        txtNcm.setEnabled(false);
        txtPrevisaoEmbarque.setEnabled(false);
        txtComentário.setEnabled(false);
    }

    //COD REQ
    public void CodReq(OrdemPagamento ordemPagamento, Double valor) throws SQLException, SQLException, ClassNotFoundException, ParseException {
        //FORMATAR VALORES
        Format format = new Format();
        //CARREGAR COMBOBOX
        ListarCombobox();
        int id = ordemPagamento.getRequisicoes().getId();
        ListarComboboxOrdemPagamento(id);

        //BLOQUEAR CAMPOS
        txtCodOp.setEnabled(false);
        txtCodRequisicao.setEnabled(false);
        txtDataOrdem.setEnabled(false);
        //txtValorTotal.setEnabled(false);
        txtValorPagar.setEnabled(true);
        //CARREGAR VALORES
        txtCodRequisicao.setText(Integer.toString(ordemPagamento.getRequisicoes().getId()));
        String numOp = Integer.toString(ordemPagamento.getRequisicoes().getId());
        //FORMAT
        //Double total = valor;
        //String totalFormat = format.formatDoubleValorPagar(total);
        //txtValorTotal.setText(totalFormat);
        //DAO
        OrdemPagamentoDAO ordemPagamentoDao = new OrdemPagamentoDAO();

        try {

            Requisicoes req = new Requisicoes();
            req.setId(Integer.parseInt(txtCodRequisicao.getText()));
            ordemPagamento.setRequisicoes(req);
            ordemPagamento = ordemPagamentoDao.localizar(ordemPagamento, nameDb);

            //RECEBE OS DADOS PARA VERIFICAR NULL
            Date dataOrdem = ordemPagamento.getDataOrdem();
            this.numParcela = ordemPagamento.getParcela();

            Double valorPagar = ordemPagamento.getValorPagar();
            Date dataVencimento = ordemPagamento.getDataVencimento();
            //String tipoCobranca = ordemPagamento.getTipoCobranca().getTipoCobranca();
            String ncm = ordemPagamento.getNcm();
            Date previsaoEmbarque = ordemPagamento.getPrevisaoEmbarque();
            String comentario = ordemPagamento.getComentario();

            //PREENCHER CAMPOS
            if (dataOrdem != null) {
                //txtDataOrdem.setDate(ordemPagamento.getDataOrdem());
                DefineData();
                cbTipoCobranca.setSelectedItem(ordemPagamento.getTipoCobranca().getTipoCobranca());
            } else {
                DefineData();
            }
            if (this.numParcela != 0) {
                //txtParcela.setText(Integer.toString(ordemPagamento.getParcela()));
                txtParcela.setText("");
                txtNumOP.setText(numOp + "/" + Integer.toString(numParcela));
                //bloqueiaCampos();

            } else {
                txtParcela.setText("");
            }
            txtValorPagar.setText("");
            //txtDataVencimento.setDate(null);

            if (ncm != null) {
                txtNcm.setText(ordemPagamento.getNcm());
            } else {
                txtNcm.setText(null);
            }
            if (previsaoEmbarque != null) {
                txtPrevisaoEmbarque.setDate(ordemPagamento.getPrevisaoEmbarque());
            } else {
                txtPrevisaoEmbarque.setDate(null);
            }
            if (comentario != null) {
                txtComentário.setText(ordemPagamento.getComentario());
            } else {
                txtComentário.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex);
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

    //DEFINIR REQUISITANTE
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btAbrirOrdemPagamento = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btCancelar = new javax.swing.JButton();
        lbDataRequisicao = new javax.swing.JLabel();
        txtCodRequisicao = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentário = new javax.swing.JTextArea();
        lbDataOrdem = new javax.swing.JLabel();
        lbParcela = new javax.swing.JLabel();
        lbValorPagar = new javax.swing.JLabel();
        lbDataVencimento = new javax.swing.JLabel();
        lbTipoCobranca = new javax.swing.JLabel();
        lbNcm = new javax.swing.JLabel();
        lbPrevisaoEmbarque = new javax.swing.JLabel();
        lbComentario = new javax.swing.JLabel();
        txtNumOP = new javax.swing.JLabel();
        cbTipoCobranca = new javax.swing.JComboBox<>();
        txtDataOrdem = new com.toedter.calendar.JDateChooser();
        txtDataVencimento = new com.toedter.calendar.JDateChooser();
        txtPrevisaoEmbarque = new com.toedter.calendar.JDateChooser();
        txtNcm = new javax.swing.JFormattedTextField();
        txtParcela = new javax.swing.JFormattedTextField();
        txtValorPagar = new javax.swing.JFormattedTextField();
        btLimpar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        lbNivel = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        cbOrdemPagamento = new javax.swing.JComboBox<>();
        btAlterar = new javax.swing.JButton();
        lbDataRequisicao1 = new javax.swing.JLabel();
        txtCodOp = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btAbrirOrdemPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAbrirOrdemPagamento.setText("Abrir Pasta da Ordem de Pagamento");
        btAbrirOrdemPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirOrdemPagamentoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("ORDEM DE PAGAMENTO - ");

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbDataRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataRequisicao.setText("Código Requisição:");

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtComentário.setColumns(20);
        txtComentário.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtComentário.setRows(5);
        jScrollPane2.setViewportView(txtComentário);

        lbDataOrdem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataOrdem.setText("Data da Ordem:");

        lbParcela.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbParcela.setText("Parcela:");

        lbValorPagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbValorPagar.setText("Valor a Pagar:");

        lbDataVencimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataVencimento.setText("Data de Vencimento:");

        lbTipoCobranca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTipoCobranca.setText("Tipo de Cobrança:");

        lbNcm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNcm.setText("NCM:");

        lbPrevisaoEmbarque.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPrevisaoEmbarque.setText("Previsão de Embarque:");

        lbComentario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbComentario.setText("Comentários:");

        txtNumOP.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N

        cbTipoCobranca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        txtDataOrdem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDataVencimento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPrevisaoEmbarque.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        try {
            txtNcm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNcm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtParcela.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtParcela.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParcelaActionPerformed(evt);
            }
        });

        txtValorPagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtValorPagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorPagarActionPerformed(evt);
            }
        });

        btLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUsuario.setText("Usuário:");

        cbOrdemPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbOrdemPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cbOrdemPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbOrdemPagamentoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbOrdemPagamentoMouseEntered(evt);
            }
        });
        cbOrdemPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOrdemPagamentoActionPerformed(evt);
            }
        });

        btAlterar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        lbDataRequisicao1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDataRequisicao1.setText("Código OP:");

        txtCodOp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCancelar))
                    .addComponent(btAbrirOrdemPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbOrdemPagamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbComentario, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbPrevisaoEmbarque, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNcm, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbTipoCobranca, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbDataVencimento, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbValorPagar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbParcela, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbDataOrdem, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbDataRequisicao, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(9, 9, 9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbDataRequisicao1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodOp)
                            .addComponent(txtNcm, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(cbTipoCobranca, 0, 680, Short.MAX_VALUE)
                            .addComponent(txtDataVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(txtValorPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(txtParcela, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(txtDataOrdem, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(txtPrevisaoEmbarque, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(lbUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumOP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btCancelar, btLimpar, btSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumOP)
                    .addComponent(lbTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsuario))
                .addGap(0, 0, 0)
                .addComponent(btAbrirOrdemPagamento)
                .addGap(7, 7, 7)
                .addComponent(cbOrdemPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDataRequisicao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodOp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDataRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodRequisicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDataOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPrevisaoEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrevisaoEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btLimpar)
                    .addComponent(btSalvar)
                    .addComponent(btAlterar))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbTitulo, txtNumOP});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTipoCobranca, lbComentario, lbDataOrdem, lbDataRequisicao, lbDataVencimento, lbNcm, lbParcela, lbPrevisaoEmbarque, lbTipoCobranca, lbValorPagar, txtCodRequisicao, txtDataOrdem, txtDataVencimento, txtNcm, txtParcela, txtPrevisaoEmbarque, txtValorPagar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btCancelar, btLimpar, btSalvar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbLogin, lbNivel, lbUsuario});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAbrirOrdemPagamento, cbOrdemPagamento});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        //TELA REQ
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btAbrirOrdemPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirOrdemPagamentoActionPerformed
        try {
            String id = txtCodRequisicao.getText();
            String nivel = lbNivel.getText();
            VerificaParametro pasta = new VerificaParametro();
            pasta.VerficaNameDBAbrirPasta(nivel, id, nameDb);
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
    }//GEN-LAST:event_btAbrirOrdemPagamentoActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA LIMPAR! " + "\n", "TODOS OS CAMPOS SERÃO RESETADOS!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            Limpar();

            JOptionPane.showMessageDialog(this, "Limpeza realizada com sucesso!");

        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btLimparActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //DATA HOJE
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();

        //VERIFICA CAMPOS
        if (txtCodRequisicao.getText().equals("")) {
            msgErro += "- Favor preencher campo Código Requisição\n";
            valida = false;
        }
        //VERIFICAR PARCELA
        int parcela = 0;
        if (txtParcela.getText().equals("")) {
            msgErro += "- Favor preencher campo Parcela\n";
            valida = false;
        } else {
            parcela = Integer.parseInt(txtParcela.getText());
        }
        if (parcela <= this.numParcela) {
            msgErro += "- O número da parcela informada é menor ou igual da última parcela\n";
            valida = false;
        }
        if (txtValorPagar.getText().equals("")) {
            msgErro += "- Favor preencher campo Valor a Pagar\n";
            valida = false;
        }
        //DATA VENCIMENTO        
        Date vencimentoDate = txtDataVencimento.getDate();
        try {
            String dataVencimento = sdf.format(vencimentoDate);
        } catch (NullPointerException e) {
            msgErro += "- Favor preencher campo Data de Vencimento\n";
            valida = false;
        }
        String dataFormat = sdf.format(dataHoje);
        try {
            dataHoje = sdf.parse(dataFormat);
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbTipoCobranca.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor preencher campo Tipo Cobrança\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {

            try {
                //MODEL
                OrdemPagamento ordemPagamento = new OrdemPagamento();
                TipoCobranca tipoCobranca = new TipoCobranca();

                //DAO
                OrdemPagamentoDAO ordemPagamentoDao = new OrdemPagamentoDAO();
                TipoCobrancaDAO tipoCobrancaDao = new TipoCobrancaDAO();

                //CARREGA VALORES NO OBJETO
                ordemPagamento.setDataOrdem(txtDataOrdem.getDate());

                //BUSCAR TIPO COBRANCA
                tipoCobranca.setTipoCobranca((String) cbTipoCobranca.getSelectedItem());

                tipoCobranca = tipoCobrancaDao.localizarTipoCobranca(tipoCobranca, nameDb);

                ordemPagamento.setTipoCobranca(tipoCobranca);
                ordemPagamento.setNcm(txtNcm.getText());
                ordemPagamento.setPrevisaoEmbarque(txtPrevisaoEmbarque.getDate());
                ordemPagamento.setComentario(txtComentário.getText());
                Requisicoes requisicao = new Requisicoes();
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                ordemPagamento.setRequisicoes(requisicao);
                //int parcela = (Integer.parseInt(txtParcela.getText()));
                ordemPagamento.setParcela(Integer.parseInt(txtParcela.getText()));
                //TESTE
                ordemPagamento.setDataVencimento(txtDataVencimento.getDate());
                //CONVERT VALOR PAGAR
                Format f = new Format();
                String convertValor = txtValorPagar.getText();
                Double total = f.formatDoubleValorPagarVirgula(convertValor);
                ordemPagamento.setValorPagar(total);

                //SALVA OBJETO NO DB
                ordemPagamentoDao.salvar(ordemPagamento, nameDb);
                Report report = new Report();
                report.geraRelatorioOP(ordemPagamento, nameDb);
            } catch (SQLException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
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
            btCancelarActionPerformed(evt);
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void txtValorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorPagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorPagarActionPerformed

    private void txtParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParcelaActionPerformed

    }//GEN-LAST:event_txtParcelaActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //DATA HOJE
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();

        //VERIFICA CAMPOS
        if (txtCodRequisicao.getText().equals("")) {
            msgErro += "- Favor preencher campo Código Requisição\n";
            valida = false;
        }
        //VERIFICAR PARCELA
        int parcela = 0;
        if (txtParcela.getText().equals("")) {
            msgErro += "- Favor preencher campo Parcela\n";
            valida = false;
        } else {
            parcela = Integer.parseInt(txtParcela.getText());
        }
        if (txtValorPagar.getText().equals("")) {
            msgErro += "- Favor preencher campo Valor a Pagar\n";
            valida = false;
        }
        //DATA VENCIMENTO        
        Date vencimentoDate = txtDataVencimento.getDate();
        try {
            String dataVencimento = sdf.format(vencimentoDate);
        } catch (NullPointerException e) {
            msgErro += "- Favor preencher campo Data de Vencimento\n";
            valida = false;
        }
        String dataFormat = sdf.format(dataHoje);
        try {
            dataHoje = sdf.parse(dataFormat);
        } catch (ParseException ex) {
            Logger.getLogger(TelaRequisicaoNova.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbTipoCobranca.getSelectedItem().equals("Selecione")) {
            msgErro += "- Favor preencher campo Tipo Cobrança\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {
            try {
                //MODEL
                OrdemPagamento ordemPagamento = new OrdemPagamento();
                TipoCobranca tipoCobranca = new TipoCobranca();

                //DAO
                OrdemPagamentoDAO ordemPagamentoDao = new OrdemPagamentoDAO();
                TipoCobrancaDAO tipoCobrancaDao = new TipoCobrancaDAO();

                //CARREGA VALORES NO OBJETO
                ordemPagamento.setDataOrdem(txtDataOrdem.getDate());

                //BUSCAR TIPO COBRANCA
                tipoCobranca.setTipoCobranca((String) cbTipoCobranca.getSelectedItem());

                tipoCobranca = tipoCobrancaDao.localizarTipoCobranca(tipoCobranca, nameDb);

                ordemPagamento.setId(Integer.parseInt(txtCodOp.getText()));
                ordemPagamento.setTipoCobranca(tipoCobranca);
                ordemPagamento.setNcm(txtNcm.getText());
                ordemPagamento.setPrevisaoEmbarque(txtPrevisaoEmbarque.getDate());
                ordemPagamento.setComentario(txtComentário.getText());
                Requisicoes requisicao = new Requisicoes();
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                ordemPagamento.setRequisicoes(requisicao);
                //int parcela = (Integer.parseInt(txtParcela.getText()));
                ordemPagamento.setParcela(Integer.parseInt(txtParcela.getText()));
                //TESTE
                ordemPagamento.setDataVencimento(txtDataVencimento.getDate());
                //CONVERT VALOR PAGAR
                Format f = new Format();
                String convertValor = txtValorPagar.getText();
                Double total = f.formatDoubleValorPagarVirgula(convertValor);
                ordemPagamento.setValorPagar(total);

                //ALTERAR OBJETO NO DB
                ordemPagamentoDao.update(ordemPagamento, nameDb);
                Report report = new Report();
                report.geraRelatorioOP(ordemPagamento, nameDb);
                //System.out.println("ID ORDEM " + ordemPagamento.getId() + "ID REQUISICAO" + ordemPagamento.getRequisicoes().getId());
            } catch (java.io.FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "O arquivo está aberto");
            } catch (SQLException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
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

            btCancelarActionPerformed(evt);
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void cbOrdemPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOrdemPagamentoActionPerformed
               
        if (cbOrdemPagamento.getSelectedItem().equals("Selecione")) {
            try {
                Limpar();
                DefineData();
                btAlterar.setEnabled(false);
                btSalvar.setEnabled(true);
            } catch (ParseException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                btAlterar.setEnabled(true);
                btSalvar.setEnabled(false);
                OrdemPagamentoDAO ordemPagamentoDao = new OrdemPagamentoDAO();
                OrdemPagamento ordemPagamento = new OrdemPagamento();
                Requisicoes requisicao = new Requisicoes();
                String parcela = cbOrdemPagamento.getSelectedItem().toString();
                ordemPagamento.setParcela(Integer.parseInt(parcela));
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                ordemPagamento.setRequisicoes(requisicao);
                ordemPagamento = ordemPagamentoDao.localizarOrdemPagamentoAlterar(ordemPagamento, nameDb);

                txtCodOp.setEnabled(false);
                txtParcela.setEnabled(false);
                txtDataOrdem.setEnabled(true);
                txtDataOrdem.setDate(ordemPagamento.getDataOrdem());
                txtParcela.setText(Integer.toString(ordemPagamento.getParcela()));

                //CONVERT VALOR PAGAR
                Moeda moeda = new Moeda();
                double convertValor = ordemPagamento.getValorPagar();
                String total = moeda.mascaraDinheiro(convertValor, Moeda.DINHEIRO_REAL);

                txtCodOp.setText(Integer.toString(ordemPagamento.getId()));
                txtValorPagar.setText(total);
                txtDataVencimento.setDate(ordemPagamento.getDataVencimento());
                cbTipoCobranca.setSelectedItem(ordemPagamento.getTipoCobranca());
                txtNcm.setText(ordemPagamento.getNcm());
                txtPrevisaoEmbarque.setDate(ordemPagamento.getPrevisaoEmbarque());
                txtComentário.setText(ordemPagamento.getComentario());

            } catch (SQLException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TelaOrdemPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbOrdemPagamentoActionPerformed

    private void cbOrdemPagamentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOrdemPagamentoMouseEntered


    }//GEN-LAST:event_cbOrdemPagamentoMouseEntered

    private void cbOrdemPagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOrdemPagamentoMouseClicked
        
    }//GEN-LAST:event_cbOrdemPagamentoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaOrdemPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaOrdemPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaOrdemPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaOrdemPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaOrdemPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAbrirOrdemPagamento;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox<String> cbOrdemPagamento;
    private javax.swing.JComboBox<String> cbTipoCobranca;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbComentario;
    private javax.swing.JLabel lbDataOrdem;
    private javax.swing.JLabel lbDataRequisicao;
    private javax.swing.JLabel lbDataRequisicao1;
    private javax.swing.JLabel lbDataVencimento;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNcm;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbParcela;
    private javax.swing.JLabel lbPrevisaoEmbarque;
    private javax.swing.JLabel lbTipoCobranca;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel lbValorPagar;
    private javax.swing.JFormattedTextField txtCodOp;
    private javax.swing.JFormattedTextField txtCodRequisicao;
    private javax.swing.JTextArea txtComentário;
    private com.toedter.calendar.JDateChooser txtDataOrdem;
    private com.toedter.calendar.JDateChooser txtDataVencimento;
    private javax.swing.JFormattedTextField txtNcm;
    private javax.swing.JLabel txtNumOP;
    private javax.swing.JFormattedTextField txtParcela;
    private com.toedter.calendar.JDateChooser txtPrevisaoEmbarque;
    private javax.swing.JFormattedTextField txtValorPagar;
    // End of variables declaration//GEN-END:variables
}
