package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.EtapaRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class EtapaRequisicaoDAO extends AcessDB {

    public EtapaRequisicao localizar(EtapaRequisicao etapaRequisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from EtapaRequisicao where EtapaRequisicao = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, etapaRequisicaoInformado.getEtapaRequisicao());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        EtapaRequisicao etapaRequisicaoRetorno = new EtapaRequisicao();

        while (resultado.next()) {
            etapaRequisicaoRetorno = new EtapaRequisicao();
            etapaRequisicaoRetorno.setEtapaRequisicao(resultado.getString("EtapaRequisicao"));
            etapaRequisicaoRetorno.setId(resultado.getInt("CodEtapaRequisicao"));
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return etapaRequisicaoRetorno;
    }

    public List<EtapaRequisicao> ObterEtapaRequisicao(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List statusRequisicao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from EtapaRequisicao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            EtapaRequisicao statusRequisicaoRetorno;
            statusRequisicaoRetorno = new EtapaRequisicao();
            statusRequisicaoRetorno.setEtapaRequisicao(resultado.getString("EtapaRequisicao"));
            statusRequisicaoRetorno.setId(resultado.getInt("CodEtapaRequisicao"));

            statusRequisicao.add(statusRequisicaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return statusRequisicao;
    }
}
