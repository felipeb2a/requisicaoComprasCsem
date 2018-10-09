package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.Projetos;
import model.Requisicoes;
import model.StatusRequisicao;
import model.Usuario;

/**
 *
 * @author felipe.ferreira
 */
public class FornecedorDAO extends AcessDB {

    public Fornecedor localizar(Fornecedor fornecedorInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Fornecedores where CodFornecedor = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, fornecedorInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Fornecedor fornecedorRetorno = new Fornecedor();
        Requisicoes requisicoes = new Requisicoes();
        while (resultado.next()) {
            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setTelefone(resultado.getString("Telefone"));
            fornecedorRetorno.setEmail(resultado.getString("Email"));
            fornecedorRetorno.setContato(resultado.getString("Contato"));
            fornecedorRetorno.setInfoAdicionais(resultado.getString("InformacoesAdicionais"));
            fornecedorRetorno.setValorInicial(resultado.getDouble("ValorInicial"));
            fornecedorRetorno.setValorFinal(resultado.getDouble("ValorFinal"));
            fornecedorRetorno.setEscolha(resultado.getBoolean("Escolha"));
            fornecedorRetorno.setCnpj(resultado.getString("CNPJ"));
            fornecedorRetorno.setCpf(resultado.getString("CPF"));
            fornecedorRetorno.setBanco(resultado.getString("Banco"));
            fornecedorRetorno.setConta(resultado.getString("Conta"));
            fornecedorRetorno.setAgencia(resultado.getString("Agencia"));
            requisicoes.setId(resultado.getInt("CodRequisicao"));
            fornecedorRetorno.setRequisicao(requisicoes);
            fornecedorRetorno.setId(resultado.getInt("CodFornecedor"));
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedorRetorno;
    }

    public List<Fornecedor> localizarFornecedorRequisicao(Fornecedor fornecedorInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List fornecedor = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.Telefone, f.Email, f.Contato, f.InformacoesAdicionais, f.TempoProducao, f.Logistica, f.ValorInicial, f.ValorFinal, f.Escolha from Fornecedores f where f.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, fornecedorInformado.getRequisicao().getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Fornecedor fornecedorRetorno = new Fornecedor();
        while (resultado.next()) {
            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setTelefone(resultado.getString("Telefone"));
            fornecedorRetorno.setEmail(resultado.getString("Email"));
            fornecedorRetorno.setContato(resultado.getString("Contato"));
            fornecedorRetorno.setInfoAdicionais(resultado.getString("InformacoesAdicionais"));
            fornecedorRetorno.setTempoProducao(resultado.getInt("TempoProducao"));
            fornecedorRetorno.setLogistica(resultado.getInt("Logistica"));
            fornecedorRetorno.setValorInicial(resultado.getDouble("ValorInicial"));
            fornecedorRetorno.setValorFinal(resultado.getDouble("ValorFinal"));
            fornecedorRetorno.setEscolha(resultado.getBoolean("Escolha"));

            fornecedor.add(fornecedorRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedor;
    }

    public List<Fornecedor> filtrarFornecedor(String fornecedorInformado, String usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List fornecedor = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.CodRequisicao from Fornecedores f inner join Requisicoes r on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where f.NomeFornecedor like ? and u.Nome like ?  and f.Escolha = true";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        String fornecedorLike = "%" + fornecedorInformado + "%";
        String usuarioLike = "%" + usuarioInformado + "%";
        stm.setString(1, fornecedorLike);
        stm.setString(2, usuarioLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Fornecedor fornecedorRetorno;

        while (resultado.next()) {
            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setId(resultado.getInt("CodRequisicao"));

            fornecedor.add(fornecedorRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedor;
    }

    public List<Fornecedor> listaFornecedor(int usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List fornecedor = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.CodRequisicao from Fornecedores f inner join Requisicoes r on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = ? and f.Escolha = true";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, usuarioInformado);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Fornecedor fornecedorRetorno;

        while (resultado.next()) {
            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setId(resultado.getInt("CodRequisicao"));
            fornecedor.add(fornecedorRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedor;
    }

    public List<Fornecedor> localizarFornecedorSolicitacao(Fornecedor fornecedorInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List fornecedor = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.Telefone, f.Email, f.Contato, f.InformacoesAdicionais, f.ValorTotal from FornecedoresSolicitacao f where f.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, fornecedorInformado.getSolicitacoes().getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Fornecedor fornecedorRetorno = new Fornecedor();
        while (resultado.next()) {
            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setTelefone(resultado.getString("Telefone"));
            fornecedorRetorno.setEmail(resultado.getString("Email"));
            fornecedorRetorno.setContato(resultado.getString("Contato"));
            fornecedorRetorno.setInfoAdicionais(resultado.getString("InformacoesAdicionais"));
            fornecedorRetorno.setValorInicial(resultado.getDouble("ValorTotal"));
            //fornecedorRetorno.setValorFinal(resultado.getDouble("ValorFinal"));

            fornecedor.add(fornecedorRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedor;
    }

    public List<Fornecedor> ObterFornecedor(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List fornecedor = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Fornecedores";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Fornecedor fornecedorRetorno;
            Requisicoes requisicoes = new Requisicoes();

            fornecedorRetorno = new Fornecedor();
            fornecedorRetorno.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            fornecedorRetorno.setTelefone(resultado.getString("Telefone"));
            fornecedorRetorno.setEmail(resultado.getString("Email"));
            fornecedorRetorno.setContato(resultado.getString("Contato"));
            fornecedorRetorno.setInfoAdicionais(resultado.getString("InformacoesAdicionais"));
            fornecedorRetorno.setValorInicial(resultado.getDouble("ValorInicial"));
            fornecedorRetorno.setValorFinal(resultado.getDouble("ValorFinal"));
            fornecedorRetorno.setEscolha(resultado.getBoolean("Escolha"));
            fornecedorRetorno.setCnpj(resultado.getString("CNPJ"));
            fornecedorRetorno.setCpf(resultado.getString("CPF"));
            fornecedorRetorno.setBanco(resultado.getString("Banco"));
            fornecedorRetorno.setConta(resultado.getString("Conta"));
            fornecedorRetorno.setAgencia(resultado.getString("Agencia"));
            requisicoes.setId(resultado.getInt("CodRequisicao"));
            fornecedorRetorno.setRequisicao(requisicoes);
            fornecedorRetorno.setId(resultado.getInt("CodFornecedor"));

            fornecedor.add(fornecedorRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedor;
    }

    //OBTER SEQUENCIA MYSQL    
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Fornecedores";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodFornecedor");
            retorno = id + 1;
        }

        return retorno;
    }

    public void salvar(Fornecedor fornecedor, String nameDb) throws SQLException, ClassNotFoundException, IOException {

        Connection conexao = conectar(nameDb);

        String sql = "insert into Fornecedores values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        int seq = obterProximoValorSequence(nameDb);

        // alterando objeto
        fornecedor.setId(seq);

        stmt.setInt(1, fornecedor.getId());
        stmt.setString(2, fornecedor.getNomeFornecedor());
        stmt.setString(3, fornecedor.getTelefone());
        stmt.setString(4, fornecedor.getEmail());
        stmt.setString(5, fornecedor.getContato());
        stmt.setString(6, fornecedor.getInfoAdicionais());
        stmt.setDouble(7, fornecedor.getValorInicial());
        stmt.setDouble(8, fornecedor.getValorFinal());
        stmt.setBoolean(9, fornecedor.getEscolha());
        stmt.setString(10, fornecedor.getCnpj());
        stmt.setString(11, fornecedor.getCpf());
        stmt.setString(12, fornecedor.getBanco());
        stmt.setString(13, fornecedor.getConta());
        stmt.setString(14, fornecedor.getAgencia());
        stmt.setInt(15, fornecedor.getTempoProducao());
        stmt.setInt(16, fornecedor.getLogistica());
        stmt.setInt(17, fornecedor.getRequisicao().getId());
        stmt.execute();
        stmt.close();
    }

    public void salvarInformacoesFinanceiras(Fornecedor fornecedor, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Fornecedores f set f.CPF = ?, f.CNPJ = ?, f.Banco = ?, f.Agencia = ?, f.Conta = ? where f.CodFornecedor = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS APROVADA COD 3
        stm.setString(1, fornecedor.getCpf());
        stm.setString(2, fornecedor.getCnpj());
        stm.setString(3, fornecedor.getBanco());
        stm.setString(4, fornecedor.getAgencia());
        stm.setString(5, fornecedor.getConta());
        stm.setInt(6, fornecedor.getId());

        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void updateTempoProducaoLogistica(Fornecedor fornecedor, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Fornecedores f set f.TempoProducao = ?, f.Logistica = ? where f.CodRequisicao = ? and f.Escolha = 1";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, fornecedor.getTempoProducao());
        stm.setInt(2, fornecedor.getLogistica());
        stm.setInt(3, fornecedor.getRequisicao().getId());

        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }

    public Fornecedor localizarFornecedorInfAdicionais(Fornecedor fornecedorInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.CodFornecedor, f.CPF, f.CNPJ, f.Banco, f.Agencia, f.Conta from Fornecedores f where f.Escolha = 1 and f.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        //DEFINE ESCOLHA
        stm.setInt(1, fornecedorInformado.getRequisicao().getId());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        Fornecedor fornecedorRetorno = new Fornecedor();

        // criando objeto de retorno
        while (resultado.next()) {

            fornecedorRetorno.setId(resultado.getInt("CodFornecedor"));
            fornecedorRetorno.setCpf(resultado.getString("CPF"));
            fornecedorRetorno.setCnpj(resultado.getString("CNPJ"));
            fornecedorRetorno.setBanco(resultado.getString("Banco"));
            fornecedorRetorno.setAgencia(resultado.getString("Agencia"));
            fornecedorRetorno.setConta(resultado.getString("Conta"));
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedorRetorno;
    }

    public Fornecedor localizarFornecedorInfAdicionaisPreencher(Fornecedor fornecedorInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.CodFornecedor, f.CPF, f.CNPJ, f.Banco, f.Agencia, f.Conta from Fornecedores f where f.NomeFornecedor like ? order by f.CodFornecedor desc limit 1";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        //DEFINE NOME FORNECEDOR
        String nomeFornecedor = fornecedorInformado.getNomeFornecedor() + "%";
        stm.setString(1, nomeFornecedor);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        Fornecedor fornecedorRetorno = new Fornecedor();

        // criando objeto de retorno
        while (resultado.next()) {
            fornecedorRetorno.setId(resultado.getInt("CodFornecedor"));
            fornecedorRetorno.setCpf(resultado.getString("CPF"));
            fornecedorRetorno.setCnpj(resultado.getString("CNPJ"));
            fornecedorRetorno.setBanco(resultado.getString("Banco"));
            fornecedorRetorno.setAgencia(resultado.getString("Agencia"));
            fornecedorRetorno.setConta(resultado.getString("Conta"));
            
            System.out.println(fornecedorRetorno.getNomeFornecedor());
        }
        // Encerrando a conexão.
        conexao.close();
        return fornecedorRetorno;
    }
    
    public void deleteFornecedorSolicitacao(Fornecedor fornecedor, String nameDb) throws SQLException, ClassNotFoundException {
    	
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from FornecedoresSolicitacao where CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        stm.setInt(1, fornecedor.getSolicitacoes().getId());
        
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void deleteFornecedorRequisicao(Fornecedor fornecedor, String nameDb) throws SQLException, ClassNotFoundException {
    	
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "delete from Fornecedores where CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        stm.setInt(1, fornecedor.getRequisicao().getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
}
