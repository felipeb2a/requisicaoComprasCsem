package model;

/**
 *
 * @author felipe.ferreira
 */
public class Fornecedor {

    private int id;
    private String nomeFornecedor;
    private String telefone;
    private String email;
    private String contato;
    private String InfoAdicionais;
    private int tempoProducao;
    private int logistica;
    private double valorInicial;
    private double valorFinal;
    private boolean escolha;
    private String cnpj;
    private String cpf;
    private String banco;
    private String conta;
    private String agencia;
    private Requisicoes requisicao;
    private Solicitacoes solicitacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getInfoAdicionais() {
        return InfoAdicionais;
    }

    public void setInfoAdicionais(String InfoAdicionais) {
        this.InfoAdicionais = InfoAdicionais;
    }

    public int getTempoProducao() {
        return tempoProducao;
    }

    public void setTempoProducao(int tempoProducao) {
        this.tempoProducao = tempoProducao;
    }

    public int getLogistica() {
        return logistica;
    }

    public void setLogistica(int logistica) {
        this.logistica = logistica;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Boolean getEscolha() {
        return escolha;
    }

    public void setEscolha(Boolean escolha) {
        this.escolha = escolha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Requisicoes getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicoes requisicao) {
        this.requisicao = requisicao;
    }

    public Solicitacoes getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(Solicitacoes solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

}
