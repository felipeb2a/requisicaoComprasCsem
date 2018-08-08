package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Moedas;

/**
 *
 * @author felipe.ferreira
 */
public class MoedasDAO extends AcessDB {

    public Moedas localizar(Moedas moedasInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Moedas where Abrev = ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, moedasInformado.getMoedaAbreviada());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Moedas moedasRetorno = new Moedas();

        while (resultado.next()) {
            moedasRetorno = new Moedas();
            moedasRetorno.setMoedaAbreviada(resultado.getString("Abrev"));
            moedasRetorno.setId(resultado.getInt("CodMoeda"));            
        }
        // Encerrando a conexão.
        conexao.close();
        return moedasRetorno;
    }

    public List<Moedas> ObterMoedas(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List moedas = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Moedas";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Moedas moedasRetorno;
            moedasRetorno = new Moedas();
            moedasRetorno.setMoeda(resultado.getString("Moeda"));
            moedasRetorno.setMoedaAbreviada(resultado.getString("Abrev"));
            moedasRetorno.setId(resultado.getInt("CodMoeda"));

            moedas.add(moedasRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return moedas;
    }
}
