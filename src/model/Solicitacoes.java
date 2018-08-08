package model;

import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class Solicitacoes {

    private int id;
    private Date dataSolicitacao;
    private String justificativa;
    private String emailSolicitante;
    private String motivo;
    //COMPOSITION    
    private Projetos projetos;
    private TipoRequisicao tipoRequisicao;
    private StatusRequisicao statusRequisicao;
    private Usuario usuario;
    private Solicitante solicitante;
    private StatusArqRequisicao statusArqRequisicao;

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

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public StatusArqRequisicao getStatusArqRequisicao() {
        return statusArqRequisicao;
    }

    public void setStatusArqRequisicao(StatusArqRequisicao statusArqRequisicao) {
        this.statusArqRequisicao = statusArqRequisicao;
    }

}
