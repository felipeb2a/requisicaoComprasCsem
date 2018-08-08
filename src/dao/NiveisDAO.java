/*@author FELIPE*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Niveis;
import model.Usuario;

public class NiveisDAO extends AcessDB {

    public List<Niveis> ObterNivel(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List nivel = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select n.NomeNivel from Niveis n";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Niveis niveisRetorno;
            niveisRetorno = new Niveis();
            niveisRetorno.setNomeNivel(resultado.getString("NomeNivel"));

            nivel.add(niveisRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return nivel;
    }

    public Niveis localizarNiveis(Niveis nivelInformado, String nameDb) throws SQLException, ClassNotFoundException {
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select n.CodNivel from Niveis n where n.NomeNivel = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, nivelInformado.getNomeNivel());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //System.out.println(resultado.getString("Nome"));
        // criando objeto de retorno
        Niveis niveisRetorno = new Niveis();

        while (resultado.next()) {
            niveisRetorno = new Niveis();
            niveisRetorno.setId(resultado.getInt("CodNivel"));
        }
        // Encerrando a conexão.
        conexao.close();
        return niveisRetorno;
    }
}
