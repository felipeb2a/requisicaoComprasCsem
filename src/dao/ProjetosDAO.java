package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Projetos;

/**
 *
 * @author felipe.ferreira
 */
public class ProjetosDAO extends AcessDB {

    public Projetos localizarProjeto(Projetos projetoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List usuario = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Projetos p where p.NomeProjeto=?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, projetoInformado.getProjeto());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Projetos projetoRetorno = new Projetos();

        while (resultado.next()) {
            projetoRetorno = new Projetos();
            projetoRetorno.setProjeto(resultado.getString("NomeProjeto"));
            projetoRetorno.setId(resultado.getInt("CodProjeto"));
        }
        // Encerrando a conexão.
        conexao.close();
        return projetoRetorno;
    }

    public void remover(Projetos projeto, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from Projetos where CodProjeto = ? ";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, projeto.getId());

        // recebendo o resultado da consulta
        stm.executeUpdate();

        // Encerrando a conexão.
        conexao.close();
    }

    public List<Projetos> ObterProjeto(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List projeto = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Projetos";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Projetos projetoRetorno;
            projetoRetorno = new Projetos();
            projetoRetorno.setProjeto(resultado.getString("NomeProjeto"));
            projetoRetorno.setId(resultado.getInt("CodProjeto"));

            projeto.add(projetoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return projeto;
    }

    public void salvar(Projetos projeto, String nameDb) throws SQLException, ClassNotFoundException {

        Connection conexao = conectar(nameDb);

        String sql = "insert into Projetos values(?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        //SEQUENCIA
        //<-----------------------------------
        int seq = obterProximoValorSequence(nameDb);

        //alterando objeto
        projeto.setId(seq);

        stmt.setInt(1, projeto.getId());
        //----------------------------------->

        stmt.setString(2, projeto.getProjeto());

        stmt.execute();
        stmt.close();
    }

    //OBTER SEQUENCIA MYSQL
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Projetos";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodProjeto");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        return retorno;
    }

    public Projetos LocalizarParaAlterar(Projetos projetoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Projetos p where p.CodProjeto = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, projetoInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Projetos projetoRetorno = new Projetos();

        while (resultado.next()) {
            projetoRetorno.setProjeto(resultado.getString("NomeProjeto"));
            projetoRetorno.setId(resultado.getInt("CodProjeto"));
        }
        // Encerrando a conexão.
        conexao.close();
        return projetoRetorno;
    }

    public void alterar(Projetos projeto, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Projetos p set p.NomeProjeto = ? where p.CodProjeto = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, projeto.getProjeto());
        stm.setInt(2, projeto.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
}
