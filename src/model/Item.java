package model;

/**
 *
 * @author felipe.ferreira
 */
public class Item {

    private int id;
    private String nomeItem;
    private String unidade;
    private int quantidade;
    private String descricaoTecnica;
    private String informacoesAdicionais;
    private double valorUnitario;
    private double valorTotal;
    private Requisicoes requisicoes;
    private Solicitacoes solicitacoes;
    
    public Requisicoes getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(Requisicoes requisicoes) {
        this.requisicoes = requisicoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Solicitacoes getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(Solicitacoes solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
    
}
