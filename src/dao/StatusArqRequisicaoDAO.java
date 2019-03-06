package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.StatusArqRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class StatusArqRequisicaoDAO extends AcessDB {

    public StatusArqRequisicao localizar(StatusArqRequisicao statusArqInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from StatusArq where Arqvd = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, statusArqInformado.getArquivada());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        StatusArqRequisicao statusArqRetorno = new StatusArqRequisicao();

        while (resultado.next()) {
            statusArqRetorno = new StatusArqRequisicao();
            statusArqRetorno.setArquivada(resultado.getString("Arqvd"));
            statusArqRetorno.setId(resultado.getInt("CodArq"));
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return statusArqRetorno;
    }

    public List<StatusArqRequisicao> ObterTipoFrete(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List tipoFrete = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from StatusArq";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            StatusArqRequisicao statusArqvRetorno;
            statusArqvRetorno = new StatusArqRequisicao();
            statusArqvRetorno.setArquivada(resultado.getString("Arqvd"));
            statusArqvRetorno.setId(resultado.getInt("CodArq"));

            tipoFrete.add(statusArqvRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return tipoFrete;
    }
}
