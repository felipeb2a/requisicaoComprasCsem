package view;

import dao.FornecedorDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Fornecedor;
import model.Icone;
import model.Log;
import model.LogArquivoTexto;
import model.Requisicoes;

/**
 *
 * @author felipe.ferreira
 */
public class TelaInfomacoesFinanceiras extends javax.swing.JFrame {

    //VARIAVEIS GLOBAIS
    private ArrayList mostrarTela = new ArrayList();
    private String nameDb;
    private Logger logger = null;

    public TelaInfomacoesFinanceiras() {
        initComponents();
        //RESTRINGIR BOTAO FECHAR DO JFRAME
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        //MaximizeTela();
    }
    //LOGGER

    public Logger Definirlogger() {
        Log log = new Log();
        try {
            logger = log.pathLog(TelaInfomacoesFinanceiras.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex1);
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

    public void MaximizeTela() {
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    //NOME DB    
    public void nameDb(String nameDb) throws SQLException, ClassNotFoundException {
        this.nameDb = nameDb;
        icone();
    }

    //COD REQ
    public void CodReq(Fornecedor fornecedor) {

        txtCodRequisicao.setEnabled(false);
        txtTitular.setEnabled(false);
        txtCodRequisicao.setText(Integer.toString(fornecedor.getRequisicao().getId()));
        txtTitular.setText(fornecedor.getNomeFornecedor());

        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        //Requisicoes req = new Requisicoes();
        //req.setId(Integer.parseInt(txtCodRequisicao.getText()));
        //fornecedor.setRequisicao(req);
        try {
            //BUSCAR FORNECEDOR SELECIONADO
            fornecedor = fornecedorDAO.localizarFornecedorInfAdicionais(fornecedor, nameDb);

            //PREENCHER CAMPOS
            txtCpf.setText(fornecedor.getCpf());
            txtCnpj.setText(fornecedor.getCnpj());
            txtBanco.setText(fornecedor.getBanco());
            txtAgencia.setText(fornecedor.getAgencia());
            txtContaCorrente.setText(fornecedor.getConta());
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

    //RELATORIO
    /*public void Relatorio() {

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = new Fornecedor();
        Requisicoes req = new Requisicoes();

        req.setId(Integer.parseInt(txtCodRequisicao.getText()));
        fornecedor.setRequisicao(req);
        try {
            //BUSCAR FORNECEDOR SELECIONADO
            fornecedor = fornecedorDAO.localizarFornecedorInfAdicionais(fornecedor);

            //PREENCHER CAMPOS
            txtCpf.setText(fornecedor.getCpf());
            txtCnpj.setText(fornecedor.getCnpj());
            txtBanco.setText(Integer.toString(fornecedor.getBanco()));
            txtAgencia.setText(Integer.toString(fornecedor.getAgencia()));
            txtContaCorrente.setText(fornecedor.getConta());
        } catch (SQLException ex) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbContaCorrente = new javax.swing.JLabel();
        lbReferencia = new javax.swing.JLabel();
        txtCodRequisicao = new javax.swing.JTextField();
        lbTitular = new javax.swing.JLabel();
        txtTitular = new javax.swing.JTextField();
        lbCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtCnpj = new javax.swing.JFormattedTextField();
        lbCnpj = new javax.swing.JLabel();
        lbBanco = new javax.swing.JLabel();
        lbAgencia = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        txtBuscaFornecedor = new javax.swing.JTextField();
        lbBuscarFornecedor = new javax.swing.JLabel();
        btBuscar = new javax.swing.JButton();
        txtContaCorrente = new javax.swing.JTextField();
        txtBanco = new javax.swing.JTextField();
        txtAgencia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lbTitulo.setText("INFORMAÇÕES FINANCEIRAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        lbContaCorrente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbContaCorrente.setText("Conta Corrente:");

        lbReferencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbReferencia.setText("Referência:");

        txtCodRequisicao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbTitular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitular.setText("Titular:");

        txtTitular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCpf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCpf.setText("CPF:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpj.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbCnpj.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCnpj.setText("CNPJ:");

        lbBanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBanco.setText("Banco:");

        lbAgencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbAgencia.setText("Agência:");

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
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

        txtBuscaFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lbBuscarFornecedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBuscarFornecedor.setText("Pesquisar Fornecedor:");

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        txtContaCorrente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtBanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBancoKeyTyped(evt);
            }
        });

        txtAgencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAgencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgenciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbContaCorrente, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbAgencia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbBanco, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbCnpj, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbCpf, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbTitular, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbReferencia, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(4, 4, 4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbBuscarFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscaFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(txtCodRequisicao, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(txtTitular)
                            .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(txtCnpj)
                            .addComponent(txtContaCorrente)
                            .addComponent(txtBanco)
                            .addComponent(txtAgencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar)
                        .addContainerGap())))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAgencia, txtBanco, txtBuscaFornecedor, txtCnpj, txtCodRequisicao, txtContaCorrente, txtCpf, txtTitular});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btLimpar, btSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBuscarFornecedor)
                    .addComponent(txtBuscaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbReferencia)
                    .addComponent(txtCodRequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitular)
                    .addComponent(txtTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCnpj)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBanco)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAgencia)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContaCorrente)
                    .addComponent(txtContaCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btLimpar)
                    .addComponent(btSalvar)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btBuscar, lbAgencia, lbBanco, lbBuscarFornecedor, lbCnpj, lbContaCorrente, lbCpf, lbReferencia, lbTitular, txtAgencia, txtBanco, txtBuscaFornecedor, txtCnpj, txtCodRequisicao, txtContaCorrente, txtCpf, txtTitular});

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

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (txtCodRequisicao.getText().equals("")) {
            msgErro += "- Favor preencher campo Referência\n";
            valida = false;
        }
        if (txtTitular.getText().equals("")) {
            msgErro += "- Favor preencher campo Titular\n";
            valida = false;
        }
        if (txtCpf.getText().equals("") || txtCnpj.getText().equals("")) {
            msgErro += "- Favor preencher campo CPF ou CNPJ\n";
            valida = false;
        }
        if (txtBanco.getText().equals("")) {
            msgErro += "- Favor preencher campo Banco\n";
            valida = false;
        }
        if (txtAgencia.getText().equals("")) {
            msgErro += "- Favor preencher campo Agência\n";
            valida = false;
        }
        if (txtContaCorrente.getText().equals("")) {
            msgErro += "- Favor preencher campo Conta Corrente\n";
            valida = false;
        }

        //CAMPOS VALIDADOS
        if (valida) {
            try {
                //MODEL
                Fornecedor fornecedor = new Fornecedor();
                Requisicoes req = new Requisicoes();

                //DAO
                FornecedorDAO fornecedorDao = new FornecedorDAO();

                req.setId(Integer.parseInt(txtCodRequisicao.getText()));
                fornecedor.setRequisicao(req);

                //BUSCAR FORNECEDOR SELECIONADO
                fornecedor = fornecedorDao.localizarFornecedorInfAdicionais(fornecedor, nameDb);

                //VERIFICA CAMPO CPF OU CNPJ
                if (txtCpf.getText().equals("")) {
                    fornecedor.setCpf(null);
                } else {
                    fornecedor.setCpf(txtCpf.getText());
                }

                if (txtCnpj.getText().equals("")) {
                    fornecedor.setCnpj(null);
                } else {
                    fornecedor.setCnpj(txtCnpj.getText());
                }

                fornecedor.setBanco(txtBanco.getText());
                fornecedor.setAgencia(txtAgencia.getText());
                fornecedor.setConta(txtContaCorrente.getText());

                fornecedorDao.salvarInformacoesFinanceiras(fornecedor, nameDb);

                btCancelarActionPerformed(evt);
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

        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        //TELA REQ
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Object[] options = {"Confirmar", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, "CLIQUE CONFIRMAR PARA LIMPAR! " + "\n", "TODOS OS CAMPOS SERÃO RESETADOS!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (opcao == 0) {
            txtCpf.setText("");
            txtCnpj.setText("");
            txtBanco.setText("");
            txtAgencia.setText("");
            txtContaCorrente.setText("");

            JOptionPane.showMessageDialog(this, "Limpeza realizada com sucesso!");

        } else {
            JOptionPane.showMessageDialog(this, "Cancelado");
        }
    }//GEN-LAST:event_btLimparActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        boolean valida = true;
        String msgErro = "";

        //VERIFICA CAMPOS
        if (txtBuscaFornecedor.getText().equals("")) {
            msgErro += "- Favor preencher campo Busca Fornecedor\n";
            valida = false;
        }
        if (valida) {
            try {
                Fornecedor fornecedor = new Fornecedor();
                Requisicoes requisicao = new Requisicoes();
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor.setNomeFornecedor(txtBuscaFornecedor.getText());
                requisicao.setId(Integer.parseInt(txtCodRequisicao.getText()));
                fornecedor.setRequisicao(requisicao);
                fornecedor = fornecedorDao.localizarFornecedorInfAdicionaisPreencher(fornecedor, nameDb);
                txtCpf.setText(fornecedor.getCpf());
                txtCnpj.setText(fornecedor.getCnpj());
                txtBanco.setText(fornecedor.getBanco());
                txtAgencia.setText(fornecedor.getAgencia());
                txtContaCorrente.setText(fornecedor.getConta());

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
        } else {
            JOptionPane.showMessageDialog(this, msgErro);
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    private void txtBancoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBancoKeyTyped

    private void txtAgenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgenciaKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAgenciaKeyTyped

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
            java.util.logging.Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInfomacoesFinanceiras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInfomacoesFinanceiras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAgencia;
    private javax.swing.JLabel lbBanco;
    private javax.swing.JLabel lbBuscarFornecedor;
    private javax.swing.JLabel lbCnpj;
    private javax.swing.JLabel lbContaCorrente;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbReferencia;
    private javax.swing.JLabel lbTitular;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtBuscaFornecedor;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtCodRequisicao;
    private javax.swing.JTextField txtContaCorrente;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtTitular;
    // End of variables declaration//GEN-END:variables
}
