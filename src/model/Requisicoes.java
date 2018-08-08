package model;

import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class Requisicoes {

    private int id;
    private Date dataSolicitacao;
    private Date dataCriacao;
    private Date dataAprovacao;
    private Date dataAprovacaoTecnico;
    private Date dataEntrega;
    private Date dataPrevisaoEntrega;
    private String Justificativa;
    private String motivo;
    private int vinculacao;
    private String aprovador;
    private String aprovadorTecnico;
    private String tipoAprovador;

    //COMPOSITION
    private Destinacao destinacao;
    private Moedas moedas;
    private Projetos projetos;
    private TipoRequisicao tipoRequisicao;
    private StatusRequisicao statusRequisicao;
    private TipoFrete tipoFrete;
    private Usuario usuario;
    private StatusArqRequisicao statusArqRequisicao;
    private Solicitante solicitante;
    private Item item;
    private Fornecedor fornecedor;
    private EtapaRequisicao etapaRequisicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Date getDataAprovacaoTecnico() {
        return dataAprovacaoTecnico;
    }

    public void setDataAprovacaoTecnico(Date dataAprovacaoTecnico) {
        this.dataAprovacaoTecnico = dataAprovacaoTecnico;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataPrevisaoEntrega() {
        return dataPrevisaoEntrega;
    }

    public void setDataPrevisaoEntrega(Date dataPrevisaoEntrega) {
        this.dataPrevisaoEntrega = dataPrevisaoEntrega;
    }
    
    public String getJustificativa() {
        return Justificativa;
    }

    public void setJustificativa(String Justificativa) {
        this.Justificativa = Justificativa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getVinculacao() {
        return vinculacao;
    }

    public void setVinculacao(int vinculacao) {
        this.vinculacao = vinculacao;
    }

    public Destinacao getDestinacao() {
        return destinacao;
    }

    public void setDestinacao(Destinacao destinacao) {
        this.destinacao = destinacao;
    }

    public Moedas getMoedas() {
        return moedas;
    }

    public void setMoedas(Moedas moedas) {
        this.moedas = moedas;
    }

    public Projetos getProjetos() {
        return projetos;
    }

    public void setProjetos(Projetos projetos) {
        this.projetos = projetos;
    }

    public TipoRequisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }

    public StatusRequisicao getStatusRequisicao() {
        return statusRequisicao;
    }

    public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
        this.statusRequisicao = statusRequisicao;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TipoFrete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusArqRequisicao getStatusArqRequisicao() {
        return statusArqRequisicao;
    }

    public void setStatusArqRequisicao(StatusArqRequisicao statusArqRequisicao) {
        this.statusArqRequisicao = statusArqRequisicao;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public String getAprovador() {
        return aprovador;
    }

    public void setAprovador(String aprovador) {
        this.aprovador = aprovador;
    }

    public String getTipoAprovador() {
        return tipoAprovador;
    }

    public void setTipoAprovador(String tipoAprovador) {
        this.tipoAprovador = tipoAprovador;
    }

    public String getAprovadorTecnico() {
        return aprovadorTecnico;
    }

    public void setAprovadorTecnico(String aprovadorTecnico) {
        this.aprovadorTecnico = aprovadorTecnico;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EtapaRequisicao getEtapaRequisicao() {
        return etapaRequisicao;
    }

    public void setEtapaRequisicao(EtapaRequisicao etapaRequisicao) {
        this.etapaRequisicao = etapaRequisicao;
    }
    
    @Override
    public String toString() {
        return "Requisicoes{" + "id=" + id + ", dataSolicitacao=" + dataSolicitacao + ", dataCriacao=" + dataCriacao + ", dataAprovacao=" + dataAprovacao + ", Justificativa=" + Justificativa + ", vinculacao=" + vinculacao + ", aprovador=" + aprovador + ", tipoAprovador=" + tipoAprovador + ", destinacao=" + destinacao + ", moedas=" + moedas + ", projetos=" + projetos + ", tipoRequisicao=" + tipoRequisicao + ", statusRequisicao=" + statusRequisicao + ", tipoFrete=" + tipoFrete + ", usuario=" + usuario + ", statusArqRequisicao=" + statusArqRequisicao + ", solicitante=" + solicitante + '}';
    }

}
