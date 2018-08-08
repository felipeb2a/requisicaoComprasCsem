package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.StatusRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class StatusRequisicaoDAO extends AcessDB {

    public StatusRequisicao localizar(StatusRequisicao statusRequisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from StatusRequisicao where Status = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, statusRequisicaoInformado.getStatusRequisicao());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        StatusRequisicao statusRequisicaoRetorno = new StatusRequisicao();

        while (resultado.next()) {
            statusRequisicaoRetorno = new StatusRequisicao();
            statusRequisicaoRetorno.setStatusRequisicao(resultado.getString("Status"));
            statusRequisicaoRetorno.setId(resultado.getInt("CodStatus"));
        }
        // Encerrando a conexão.
        conexao.close();
        return statusRequisicaoRetorno;
    }

    public List<StatusRequisicao> ObterStatusRequisicao(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List statusRequisicao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from StatusRequisicao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            StatusRequisicao statusRequisicaoRetorno;
            statusRequisicaoRetorno = new StatusRequisicao();
            statusRequisicaoRetorno.setStatusRequisicao(resultado.getString("Status"));
            statusRequisicaoRetorno.setId(resultado.getInt("CodStatus"));

            statusRequisicao.add(statusRequisicaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return statusRequisicao;
    }
}
