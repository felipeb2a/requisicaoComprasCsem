package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Requisicoes;

/**
 *
 * @author felipe.ferreira
 */
public class ItemDAO extends AcessDB {

    public Item localizar(Item itemInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Item where CodItem = ?";
        
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, itemInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Item itemRetorno = new Item();

        while (resultado.next()) {
            itemRetorno = new Item();
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setUnidade(resultado.getString("Unidade"));
            itemRetorno.setQuantidade(resultado.getInt("Quantidade"));
            itemRetorno.setDescricaoTecnica(resultado.getString("DescricaoTecnica"));
            itemRetorno.setInformacoesAdicionais(resultado.getString("InformacoesAdicionais"));
            itemRetorno.setValorUnitario(resultado.getDouble("ValorUnitario"));
            itemRetorno.setValorTotal(resultado.getDouble("ValorTotal"));
            itemRetorno.setId(resultado.getInt("CodItem"));
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return itemRetorno;
    }

    public List<Item> ObterItem(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List item = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Item";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Item itemRetorno;
            itemRetorno = new Item();
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setUnidade(resultado.getString("Unidade"));
            itemRetorno.setQuantidade(resultado.getInt("Quantidade"));
            itemRetorno.setDescricaoTecnica(resultado.getString("DescricaoTecnica"));
            itemRetorno.setInformacoesAdicionais(resultado.getString("InformacoesAdicionais"));
            itemRetorno.setValorUnitario(resultado.getDouble("ValorUnitario"));
            itemRetorno.setValorTotal(resultado.getDouble("ValorTotal"));
            Requisicoes requisicao = new Requisicoes();
            requisicao.setId(resultado.getInt("CodRequisicao"));
            itemRetorno.setRequisicoes(requisicao);
            itemRetorno.setId(resultado.getInt("CodItem"));

            item.add(itemRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return item;
    }

    //OBTER SEQUENCIA MYSQL    
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Item";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodItem");
            retorno = id + 1;
        }

        return retorno;
    }

    public void salvar(Item item, String nameDb) throws SQLException, ClassNotFoundException {
       
        Connection conexao = conectar(nameDb);

        String sql = "insert into Item values (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        int seq = obterProximoValorSequence(nameDb);

        // alterando objeto
        item.setId(seq);

        stmt.setInt(1, item.getId());
        stmt.setString(2, item.getNomeItem());
        stmt.setString(3, item.getUnidade());
        stmt.setInt(4, item.getQuantidade());
        stmt.setString(5, item.getDescricaoTecnica());
        stmt.setString(6, item.getInformacoesAdicionais());
        stmt.setDouble(7, item.getValorUnitario());
        stmt.setDouble(8, item.getValorTotal());
        stmt.setInt(9, item.getRequisicoes().getId());
        stmt.setObject(10, item.getProdutoERP().getId());
        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public List<Item> localizarItemRequisicao(Item itemInformado, String nameDb) throws SQLException, ClassNotFoundException {
        List item = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Item where CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, itemInformado.getRequisicoes().getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Item itemRetorno = new Item();
        while (resultado.next()) {
            itemRetorno = new Item();
            itemRetorno.setId(resultado.getInt("CodItem"));
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setUnidade(resultado.getString("Unidade"));
            itemRetorno.setQuantidade(resultado.getInt("Quantidade"));
            itemRetorno.setDescricaoTecnica(resultado.getString("DescricaoTecnica"));
            itemRetorno.setInformacoesAdicionais(resultado.getString("InformacoesAdicionais"));
            itemRetorno.setValorUnitario(resultado.getDouble("ValorUnitario"));
            itemRetorno.setValorTotal(resultado.getDouble("ValorTotal"));

            item.add(itemRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return item;
    }
    
    public List<Item> localizarItemSolicitacao(Item itemInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List item = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from ItemSolicitacao where CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, itemInformado.getSolicitacoes().getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Item itemRetorno = new Item();
        while (resultado.next()) {
            itemRetorno = new Item();
            itemRetorno.setId(resultado.getInt("CodItemSolicitacao"));
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setUnidade(resultado.getString("Unidade"));
            itemRetorno.setQuantidade(resultado.getInt("Quantidade"));
            itemRetorno.setDescricaoTecnica(resultado.getString("DescricaoTecnica"));
            itemRetorno.setInformacoesAdicionais(resultado.getString("InformacoesAdicionais"));
            itemRetorno.setValorUnitario(resultado.getDouble("ValorUnitario"));
            itemRetorno.setValorTotal(resultado.getDouble("ValorTotal"));

            item.add(itemRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return item;
    }
    
    public List<Item> filtrarItem(String itemInformado, String usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List item = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select i.NomeItem, i.CodRequisicao from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where i.NomeItem like ? and u.Nome like ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        String itemLike = "%" + itemInformado + "%";
        String usuarioLike = "%" + usuarioInformado + "%";
        stm.setString(1, itemLike);
        stm.setString(2, usuarioLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Item itemRetorno;

        while (resultado.next()) {
            itemRetorno = new Item();
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setId(resultado.getInt("CodRequisicao"));

            item.add(itemRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return item;
    }

    public List<Item> listaItem(int usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List item = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select i.NomeItem, i.CodRequisicao from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, usuarioInformado);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Item itemRetorno;

        while (resultado.next()) {
            itemRetorno = new Item();
            itemRetorno.setNomeItem(resultado.getString("NomeItem"));
            itemRetorno.setId(resultado.getInt("CodRequisicao"));
            item.add(itemRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return item;
    }
    
    public void deleteItemSolicitacao(Item item, String nameDb) throws SQLException, ClassNotFoundException {
    	
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from ItemSolicitacao where CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        stm.setInt(1, item.getSolicitacoes().getId());
        
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }
    
    public void deleteItemRequisicao(Item item, String nameDb) throws SQLException, ClassNotFoundException {
    	
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from Item where CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        stm.setInt(1, item.getRequisicoes().getId());
        
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }
}
