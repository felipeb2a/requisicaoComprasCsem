package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Format;
import model.OrdemPagamento;
import model.TipoCobranca;

/**
 *
 * @author felipe.ferreira
 */
public class OrdemPagamentoDAO extends AcessDB {

    public OrdemPagamento localizar(OrdemPagamento ordemPagamentoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select max(o.DataOP) as DataOP, o.Parcela, o.ValorPagar, o.DataVencimento, tc.TipoCobranca, o.NCM, o.PrevisaoEmbarque, o.Comentarios from OrdemPagto o inner join TipoCobranca tc on tc.CodTipoCobranca = o.CodTipoCobranca where o.CodRequisicao = ? group by o.CodOrdemPagamento";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, ordemPagamentoInformado.getRequisicoes().getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno = new OrdemPagamento();
        TipoCobranca tipoCobranca = new TipoCobranca();
        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));
            ordemPagamentoRetorno.setComentario(resultado.getString("Comentarios"));
            ordemPagamentoRetorno.setValorPagar(resultado.getDouble("ValorPagar"));
            tipoCobranca.setTipoCobranca(resultado.getString("TipoCobranca"));
            ordemPagamentoRetorno.setTipoCobranca(tipoCobranca);
            //ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));

        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamentoRetorno;
    }

    public List<OrdemPagamento> localizarOrdemPagamento(OrdemPagamento ordemPagamentoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List ordemPagamento = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select f.NomeFornecedor, f.Telefone, f.Email, f.Contato, f.ValorTotal, f.Escolha from Fornecedores f where f.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, ordemPagamentoInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno = new OrdemPagamento();
        TipoCobranca tipoCobranca = new TipoCobranca();
        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));
            ordemPagamentoRetorno.setComentario(resultado.getString("Comentarios"));
            tipoCobranca.setTipoCobranca(resultado.getString("TipoCobranca"));
            ordemPagamentoRetorno.setTipoCobranca(tipoCobranca);
            ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));

            ordemPagamento.add(ordemPagamentoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamento;
    }

    public OrdemPagamento localizarOrdemPagamentoAlterar(OrdemPagamento ordemPagamentoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select o.CodOrdemPagamento, o.DataOP, o.Parcela, o.ValorPagar, o.DataVencimento, tc.TipoCobranca, o.NCM, o.PrevisaoEmbarque, o.Comentarios from OrdemPagto o inner join TipoCobranca tc on tc.CodTipoCobranca = o.CodTipoCobranca where o.CodRequisicao = ? and o.Parcela = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, ordemPagamentoInformado.getRequisicoes().getId());
        stm.setInt(2, ordemPagamentoInformado.getParcela());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno = new OrdemPagamento();
        TipoCobranca tipoCobranca = new TipoCobranca();
        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setId(resultado.getInt("CodOrdemPagamento"));
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));
            ordemPagamentoRetorno.setComentario(resultado.getString("Comentarios"));
            ordemPagamentoRetorno.setValorPagar(resultado.getDouble("ValorPagar"));
            tipoCobranca.setTipoCobranca(resultado.getString("TipoCobranca"));
            ordemPagamentoRetorno.setTipoCobranca(tipoCobranca);
            //ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));

        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamentoRetorno;
    }

    public List<OrdemPagamento> ObterOrdemPagamento(int id, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List ordemPagamento = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select o.Parcela from OrdemPagto o where o.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, id);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            OrdemPagamento ordemPagamentoRetorno;

            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));

            ordemPagamento.add(ordemPagamentoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamento;
    }

    public void salvar(OrdemPagamento ordemPagamento, String nameDb) throws SQLException, ClassNotFoundException, IOException, ParseException {

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date dataOrdem = ordemPagamento.getDataOrdem();
        java.util.Date dataVencimento = ordemPagamento.getDataVencimento();
        java.util.Date previcaoEmbarque = ordemPagamento.getPrevisaoEmbarque();

        java.sql.Date dataOrdemConvert;
        java.sql.Date dataVencimentoConvert;
        java.sql.Date previsaoEmbarqueConvert;

        dataOrdemConvert = convertData.convertDataSql(dataOrdem);
        dataVencimentoConvert = convertData.convertDataSql(dataVencimento);

        if (previcaoEmbarque == null) {
            previsaoEmbarqueConvert = null;
        } else {
            previsaoEmbarqueConvert = convertData.convertDataSql(previcaoEmbarque);
        }

        Connection conexao = conectar(nameDb);

        String sql = "insert into OrdemPagto values (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        //SEQUENCIA
        //<-----------------------------------
        int seq = obterProximoValorSequence(nameDb);

        //alterando objeto
        ordemPagamento.setId(seq);

        stmt.setInt(1, ordemPagamento.getId());
        stmt.setDate(2, dataOrdemConvert);
        stmt.setDate(3, dataVencimentoConvert);
        stmt.setInt(4, ordemPagamento.getParcela());
        stmt.setDouble(5, ordemPagamento.getValorPagar());
        stmt.setString(6, ordemPagamento.getNcm());
        stmt.setDate(7, previsaoEmbarqueConvert);
        stmt.setString(8, ordemPagamento.getComentario());
        stmt.setInt(9, ordemPagamento.getTipoCobranca().getId());
        stmt.setInt(10, ordemPagamento.getRequisicoes().getId());
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
        String sql = "select * from OrdemPagto";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodOrdemPagamento");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        return retorno;
    }

    public List<OrdemPagamento> filtrarOrdemPagamento(String usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List ordemPagamento = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select o.CodRequisicao, o.Parcela, o.ValorPagar, o.DataOP, o.DataVencimento, o.NCM, o.PrevisaoEmbarque from OrdemPagto o inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome like ? order by o.CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        String usuarioLike = "%" + usuarioInformado + "%";
        stm.setString(1, usuarioLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno;

        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setValorPagar(resultado.getDouble("ValorPagar"));
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));

            ordemPagamento.add(ordemPagamentoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamento;
    }

    public List<OrdemPagamento> listaOrdemPagamento(int usuarioInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List ordemPagamento = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select o.CodRequisicao, o.Parcela, o.ValorPagar, o.DataOP, o.DataVencimento, o.NCM, o.PrevisaoEmbarque from OrdemPagto o inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = ? order by o.CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, usuarioInformado);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno;

        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setValorPagar(resultado.getDouble("ValorPagar"));
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));

            ordemPagamento.add(ordemPagamentoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamento;
    }

    public OrdemPagamento updateOrdemPagamento(OrdemPagamento ordemPagamentoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, ordemPagamentoInformado.getRequisicoes().getId());
        stm.setInt(2, ordemPagamentoInformado.getParcela());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        // criando objeto de retorno
        OrdemPagamento ordemPagamentoRetorno = new OrdemPagamento();
        TipoCobranca tipoCobranca = new TipoCobranca();
        while (resultado.next()) {
            ordemPagamentoRetorno = new OrdemPagamento();
            ordemPagamentoRetorno.setDataOrdem(resultado.getDate("DataOP"));
            ordemPagamentoRetorno.setParcela(resultado.getInt("Parcela"));
            ordemPagamentoRetorno.setDataVencimento(resultado.getDate("DataVencimento"));
            ordemPagamentoRetorno.setNcm(resultado.getString("NCM"));
            ordemPagamentoRetorno.setPrevisaoEmbarque(resultado.getDate("PrevisaoEmbarque"));
            ordemPagamentoRetorno.setComentario(resultado.getString("Comentarios"));
            ordemPagamentoRetorno.setValorPagar(resultado.getDouble("ValorPagar"));
            tipoCobranca.setTipoCobranca(resultado.getString("TipoCobranca"));
            ordemPagamentoRetorno.setTipoCobranca(tipoCobranca);
            //ordemPagamentoRetorno.setId(resultado.getInt("CodRequisicao"));

        }
        // Encerrando a conexão.
        conexao.close();
        return ordemPagamentoRetorno;
    }

    public void update(OrdemPagamento ordemPagamento, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date dataOrdem = ordemPagamento.getDataOrdem();
        java.util.Date dataVencimento = ordemPagamento.getDataVencimento();
        java.util.Date previcaoEmbarque = ordemPagamento.getPrevisaoEmbarque();

        java.sql.Date dataOrdemConvert;
        java.sql.Date dataVencimentoConvert;
        java.sql.Date previsaoEmbarqueConvert;

        dataOrdemConvert = convertData.convertDataSql(dataOrdem);
        dataVencimentoConvert = convertData.convertDataSql(dataVencimento);

        if (previcaoEmbarque == null) {
            previsaoEmbarqueConvert = null;
        } else {
            previsaoEmbarqueConvert = convertData.convertDataSql(previcaoEmbarque);
        }
        
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update OrdemPagto o set o.DataOP = ?, o.ValorPagar = ?, o.DataVencimento = ?, o.CodTipoCobranca = ?, o.NCM = ?, o.PrevisaoEmbarque = ?, o.Comentarios = ? where o.CodOrdemPagamento = ? and o.Parcela = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setDate(1, dataOrdemConvert);
        stmt.setDouble(2, ordemPagamento.getValorPagar());
        stmt.setDate(3, dataVencimentoConvert);
        stmt.setInt(4, ordemPagamento.getTipoCobranca().getId());
        stmt.setString(5, ordemPagamento.getNcm());
        stmt.setDate(6, previsaoEmbarqueConvert);
        stmt.setString(7, ordemPagamento.getComentario());
        //stmt.setInt(8, ordemPagamento.getRequisicoes().getId());
        stmt.setInt(8, ordemPagamento.getId());
        stmt.setInt(9, ordemPagamento.getParcela());

        // recebendo o resultado da consulta
        stmt.executeUpdate();
        stmt.close();
    }

}
