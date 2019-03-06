/*@author FELIPE*/
package dao;

import model.TipoCobranca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoCobrancaDAO extends AcessDB {

    public List<TipoCobranca> ObterTipoCobranca(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List tipoCobranca = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select tc.TipoCobranca from TipoCobranca tc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            TipoCobranca tipoCobrancaRetorno;
            tipoCobrancaRetorno = new TipoCobranca();
            tipoCobrancaRetorno.setTipoCobranca(resultado.getString("TipoCobranca"));

            tipoCobranca.add(tipoCobrancaRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return tipoCobranca;
    }

    public TipoCobranca localizarTipoCobranca(TipoCobranca tipoCobrancaInformado, String nameDb) throws SQLException, ClassNotFoundException {
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select tc.CodTipoCobranca from TipoCobranca tc where tc.TipoCobranca = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, tipoCobrancaInformado.getTipoCobranca());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //System.out.println(resultado.getString("Nome"));
        // criando objeto de retorno
        TipoCobranca tipoCobrancaRetorno = new TipoCobranca();

        while (resultado.next()) {
            tipoCobrancaRetorno = new TipoCobranca();
            tipoCobrancaRetorno.setId(resultado.getInt("CodTipoCobranca"));
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return tipoCobrancaRetorno;
    }
}
