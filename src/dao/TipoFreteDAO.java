package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TipoFrete;

/**
 *
 * @author felipe.ferreira
 */
public class TipoFreteDAO extends AcessDB {

    public TipoFrete localizar(TipoFrete tipoFreteInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from TipoFrete where TipoFrete = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, tipoFreteInformado.getTipoFrete());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        TipoFrete tipoFreteRetorno = new TipoFrete();

        while (resultado.next()) {
            tipoFreteRetorno = new TipoFrete();
            tipoFreteRetorno.setTipoFrete(resultado.getString("TipoFrete"));
            tipoFreteRetorno.setId(resultado.getInt("CodFrete"));
        }
        // Encerrando a conexão.
        conexao.close();
        return tipoFreteRetorno;
    }

    public List<TipoFrete> ObterTipoFrete(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List tipoFrete = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from TipoFrete";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            TipoFrete tipoFreteRetorno;
            tipoFreteRetorno = new TipoFrete();
            tipoFreteRetorno.setTipoFrete(resultado.getString("TipoFrete"));
            tipoFreteRetorno.setId(resultado.getInt("CodFrete"));

            tipoFrete.add(tipoFreteRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return tipoFrete;
    }
}
