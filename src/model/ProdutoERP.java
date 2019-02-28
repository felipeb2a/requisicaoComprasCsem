/*@author FELIPE*/
package model;

public class ProdutoERP{

    private Integer id;
    private Integer codigoProdutoERP;
    private String descricao;
    private String unidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoProdutoERP() {
        return codigoProdutoERP;
    }

    public void setCodigoProdutoERP(Integer codigoProdutoERP) {
        this.codigoProdutoERP = codigoProdutoERP;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

}
