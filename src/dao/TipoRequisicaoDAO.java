package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Projetos;
import model.TipoRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class TipoRequisicaoDAO extends AcessDB {

    public TipoRequisicao localizar(TipoRequisicao tipoRequisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from TipoReq where TipoRequisicao = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, tipoRequisicaoInformado.getTipoRequisicao());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        TipoRequisicao tipoRequisicaoRetorno = new TipoRequisicao();

        while (resultado.next()) {
            tipoRequisicaoRetorno = new TipoRequisicao();
            tipoRequisicaoRetorno.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            tipoRequisicaoRetorno.setId(resultado.getInt("CodTipoReq"));
        }
        // Encerrando a conexão.
        conexao.close();
        return tipoRequisicaoRetorno;
    }

    public List<TipoRequisicao> ObterTipoRequisicao(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List tipoRequisicao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from TipoReq";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            TipoRequisicao tipoRequisicaoRetorno;
            tipoRequisicaoRetorno = new TipoRequisicao();
            tipoRequisicaoRetorno.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            tipoRequisicaoRetorno.setId(resultado.getInt("CodTipoReq"));

            tipoRequisicao.add(tipoRequisicaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return tipoRequisicao;
    }
}
