package model;

/**
 *
 * @author felipe.ferreira
 */
public class StatusRequisicao {

    private int id;
    private String statusRequisicao;

    public enum StatusRequisicaoEnum {
        Nova("Nova", 1),
        Enviada("Enviada", 2),
        Aprovada("Aprovada", 3),
        Recusada("Recusada", 4),
        Cancelada("Cancelada", 5),
        Finalizada("Finalizada", 6),
        AprovadaTecnico("Aprovada Tecnico", 7),
        Arquivada("Arquivada", 8),
        Desarquivada("Desarquivada", 9);

        private int codigo;
        private String nome;

        StatusRequisicaoEnum(String nome, int codigo) {
            this.nome = nome;
            this.codigo = codigo;
        }

        public int getCodigoStatusRequisicao() {
            return this.codigo;
        }

        public String getNomeStatusRequisicao() {
            return this.nome;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusRequisicao() {
        return statusRequisicao;
    }

    public void setStatusRequisicao(String statusRequisicao) {
        this.statusRequisicao = statusRequisicao;
    }

}
