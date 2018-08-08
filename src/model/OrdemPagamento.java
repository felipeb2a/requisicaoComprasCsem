package model;

import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class OrdemPagamento {
    private int id;
    private Date dataOrdem;
    private int parcela;
    private Double valorPagar;
    private Date dataVencimento;
    private TipoCobranca tipoCobranca;
    private String ncm;
    private Date previsaoEmbarque;
    private String comentario;
    private Requisicoes requisicoes;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataOrdem() {
        return dataOrdem;
    }

    public void setDataOrdem(Date dataOrdem) {
        this.dataOrdem = dataOrdem;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public Double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(Double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public TipoCobranca getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(TipoCobranca tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Date getPrevisaoEmbarque() {
        return previsaoEmbarque;
    }

    public void setPrevisaoEmbarque(Date previsaoEmbarque) {
        this.previsaoEmbarque = previsaoEmbarque;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Requisicoes getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(Requisicoes requisicoes) {
        this.requisicoes = requisicoes;
    }
}
