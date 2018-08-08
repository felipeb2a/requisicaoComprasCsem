/*@author FELIPE*/
package dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Criptografia;
import model.Niveis;
import model.Requisicoes;
import model.Usuario;

public class UsuarioDAO extends AcessDB {

    public Usuario localizarUsuario(Usuario usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //Criptografar senha
        Criptografia cript = new Criptografia();
        String senha = usuarioInformado.getSenha();
        senha = cript.criptografaSHA256(senha);
        
        // lista de retorno
        List usuario = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.Nome=? and u.Senha=?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, usuarioInformado.getLogin());
        stm.setString(2, senha);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Usuario usuarioRetorno = new Usuario();
        Niveis niveis = new Niveis();

        while (resultado.next()) {
            usuarioRetorno = new Usuario();
            usuarioRetorno.setLogin(resultado.getString("Nome"));
            usuarioRetorno.setSenha(resultado.getString("Senha"));
            usuarioRetorno.setId(resultado.getInt("CodUsuario"));
            niveis.setId(resultado.getInt("CodNivel"));
            niveis.setNomeNivel(resultado.getString("NomeNivel"));
            usuarioRetorno.setNivel(niveis);

        }
        // Encerrando a conexão.
        conexao.close();
        return usuarioRetorno;
    }

    public void remover(Usuario usuario, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from Usuario where CodUsuario = ? ";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, usuario.getId());

        // recebendo o resultado da consulta
        stm.executeUpdate();

        // Encerrando a conexão.
        conexao.close();
    }

    public List<Usuario> ObterNome(String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List user = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select u.CodUsuario, u.Nome from Usuario u";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Usuario usuarioRetorno;
            usuarioRetorno = new Usuario();
            usuarioRetorno.setLogin(resultado.getString("Nome"));

            user.add(usuarioRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return user;
    }
    
    public List<Usuario> ObterNomeAprovador(String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List user = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select u.Nome from Usuario u where u.CodNivel in (2, 3)";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Usuario usuarioRetorno;
            usuarioRetorno = new Usuario();
            usuarioRetorno.setLogin(resultado.getString("Nome"));

            user.add(usuarioRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return user;
    }
    
    public Usuario ObterNivelAprovador(Usuario aprovador, String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List user = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select u.Nome, n.NomeNivel from Usuario u inner join Niveis n on n.CodNivel = u.CodNivel where u.Nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, aprovador.getLogin());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Usuario usuarioRetorno = new Usuario();
        while (resultado.next()) {
            usuarioRetorno = new Usuario();
            Niveis nivel = new Niveis();
            usuarioRetorno.setLogin(resultado.getString("Nome"));
            nivel.setNomeNivel(resultado.getString("NomeNivel"));
            usuarioRetorno.setNivel(nivel);
        }
        // Encerrando a conexão.
        conexao.close();
        return usuarioRetorno;
    }

    public void salvar(Usuario usuario, String nameDb) throws SQLException, ClassNotFoundException {

        Connection conexao = conectar(nameDb);

        String sql = "insert into Usuario values(?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        //SEQUENCIA
        //<-----------------------------------
        int seq = obterProximoValorSequence(nameDb);

        //alterando objeto
        usuario.setId(seq);

        stmt.setInt(1, usuario.getId());
        //----------------------------------->

        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getEmail());
        stmt.setInt(5, usuario.getNivel().getId());

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
        String sql = "select * from Usuario";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodUsuario");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        return retorno;
    }

    public List<Usuario> ObterTodos(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List user = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel  order by u.Nome";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Usuario usuarioRetorno;
            usuarioRetorno = new Usuario();
            Niveis niveis = new Niveis();
            usuarioRetorno.setLogin(resultado.getString("Nome"));
            usuarioRetorno.setSenha(resultado.getString("Senha"));
            usuarioRetorno.setEmail(resultado.getString("EmailFunc"));
            usuarioRetorno.setId(resultado.getInt("CodUsuario"));
            niveis.setId(resultado.getInt("CodNivel"));
            niveis.setNomeNivel(resultado.getString("NomeNivel"));
            usuarioRetorno.setNivel(niveis);
            user.add(usuarioRetorno);
        }

        // Encerrando a conexão.
        conexao.close();
        return user;
    }

    public Usuario LocalizarParaAlterar(Usuario usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.CodUsuario = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, usuarioInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Usuario usuarioRetorno = new Usuario();
        Niveis niveis = new Niveis();

        while (resultado.next()) {
            usuarioRetorno.setLogin(resultado.getString("Nome"));
            usuarioRetorno.setSenha(resultado.getString("Senha"));
            usuarioRetorno.setEmail(resultado.getString("EmailFunc"));
            usuarioRetorno.setId(resultado.getInt("CodUsuario"));
            niveis.setId(resultado.getInt("CodNivel"));
            niveis.setNomeNivel(resultado.getString("NomeNivel"));
            usuarioRetorno.setNivel(niveis);
        }
        // Encerrando a conexão.
        conexao.close();
        return usuarioRetorno;
    }

    public void alterar(Usuario usuario, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Usuario u set u.Nome = ?, u.Senha = ?, u.EmailFunc = ?, u.CodNivel = ? where u.CodUsuario = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, usuario.getLogin());
        stm.setString(2, usuario.getSenha());
        stm.setString(3, usuario.getEmail());
        stm.setInt(4, usuario.getNivel().getId());
        stm.setInt(5, usuario.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void alterarSenha(Usuario usuario, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Usuario u set u.Senha = ? where u.Nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, usuario.getSenha());
        stm.setString(2, usuario.getLogin());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }

    public Usuario localizarIdUsuario(Usuario usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select u.CodUsuario from Usuario u where u.Nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, usuarioInformado.getLogin());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //System.out.println(resultado.getString("Nome"));
        // criando objeto de retorno
        Usuario usuarioRetorno = new Usuario();

        while (resultado.next()) {
            usuarioRetorno = new Usuario();
            usuarioRetorno.setId(resultado.getInt("CodUsuario"));
        }
        // Encerrando a conexão.
        conexao.close();
        return usuarioRetorno;
    }

    public Usuario localizarNivelUsuario(String usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select n.NomeNivel from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.Nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, usuarioInformado);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        //System.out.println(resultado.getString("Nome"));
        // criando objeto de retorno
        Usuario usuarioRetorno = new Usuario();

        while (resultado.next()) {
            usuarioRetorno = new Usuario();
            Niveis nivel = new Niveis();
            nivel.setNomeNivel(resultado.getString("NomeNivel"));
            usuarioRetorno.setNivel(nivel);
        }
        // Encerrando a conexão.
        conexao.close();
        return usuarioRetorno;
    }
}
