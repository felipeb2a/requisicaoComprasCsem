package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Destinacao;
import model.Projetos;
import model.TipoRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class DestinacaoDAO extends AcessDB {

    public Destinacao localizar(Destinacao destinacaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Destinacao where Destinacao = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, destinacaoInformado.getDestinacao());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Destinacao destinacaoRetorno = new Destinacao();

        while (resultado.next()) {
            destinacaoRetorno = new Destinacao();
            destinacaoRetorno.setDestinacao(resultado.getString("Destinacao"));
            destinacaoRetorno.setId(resultado.getInt("CodDest"));
        }
        // Encerrando a conexão.
        conexao.close();
        return destinacaoRetorno;
    }

    public List<Destinacao> ObterDestinacao(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List destinacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Destinacao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Destinacao destinacaoRetorno;
            destinacaoRetorno = new Destinacao();
            destinacaoRetorno.setDestinacao(resultado.getString("Destinacao"));
            destinacaoRetorno.setId(resultado.getInt("CodDest"));

            destinacao.add(destinacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return destinacao;
    }
}
