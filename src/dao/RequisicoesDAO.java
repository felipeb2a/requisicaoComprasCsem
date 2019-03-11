package dao;

/**
 *
 * @author felipe.ferreira
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Format;
import model.Destinacao;
import model.EtapaRequisicao;
import model.Fornecedor;
import model.Item;
import model.Moedas;
import model.Niveis;
import model.Projetos;
import model.Requisicoes;
import model.Solicitante;
import model.StatusArqRequisicao;
import model.StatusRequisicao;
import model.TipoFrete;
import model.TipoRequisicao;
import model.Usuario;

public class RequisicoesDAO extends AcessDB {

    public Requisicoes localizarRequisicoes(Requisicoes requisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.Nome=? and u.Senha=?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicaoInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Requisicoes requisicaoRetorno = new Requisicoes();
        Destinacao destinacao = new Destinacao();
        Moedas moedas = new Moedas();
        Projetos projetos = new Projetos();
        TipoRequisicao tipoRequisicao = new TipoRequisicao();
        StatusRequisicao statusRequisicao = new StatusRequisicao();
        TipoFrete tipoFrete = new TipoFrete();
        Usuario usuario = new Usuario();
        StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
        Solicitante solicitante = new Solicitante();

        while (resultado.next()) {
            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setDataAprovacao(resultado.getDate("DataSolicitacao"));
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));

            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));

            destinacao.setId(resultado.getInt("CodDest"));
            destinacao.setDestinacao(resultado.getString("Destinacao"));
            requisicaoRetorno.setDestinacao(destinacao);

        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        
        return requisicaoRetorno;
    }

    public Requisicoes localizarRequisicoesArqv(Requisicoes requisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodArq from Requisicoes r where r.CodRequisicao = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicaoInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Requisicoes requisicaoRetorno = new Requisicoes();
        StatusArqRequisicao arq = new StatusArqRequisicao();
        while (resultado.next()) {
            requisicaoRetorno = new Requisicoes();
            arq.setId(resultado.getInt("CodArq"));
            requisicaoRetorno.setStatusArqRequisicao(arq);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return requisicaoRetorno;
    }

    public List<Requisicoes> localizarRequisicoesUser(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        //String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = ? and r.CodArq = 1 and sr.Status <> 'Cancelada' order by CodRequisicao desc";
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome, e.EtapaRequisicao from Requisicoes r inner join EtapaRequisicao e on r.CodEtapaRequisicao = e.CodEtapaRequisicao inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = ? and r.CodArq = 1 and sr.Status <> 'Cancelada' order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, userInformado);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            EtapaRequisicao etapa = new EtapaRequisicao();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);
            //ETAPA REQ
            etapa.setEtapaRequisicao(resultado.getString("EtapaRequisicao"));
            requisicaoRetorno.setEtapaRequisicao(etapa);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> localizarRequisicoesArquivadaUser(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = ? and r.CodArq = 2 order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, userInformado);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> localizarRequisicoesCanceladaUser(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = ? and r.CodigoStatus = ? order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS CANCELADA 5
        stm.setString(1, userInformado);
        stm.setInt(2, 5);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> FiltrarRequisicoesUserStatus(String userInformado, String statusInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome like ? and sr.Status like ? and r.CodArq = 1 order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        String userLike = "%" + userInformado + "%";
        String statusLike = "%" + statusInformado + "%";
        stm.setString(1, userLike);
        stm.setString(2, statusLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> FiltrarRequisicoesCodRequisicao(String codRequisicao, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, "
                + " p.CodProjeto, p.NomeProjeto, "
                + " tr.CodTipoReq, tr.TipoRequisicao, "
                + " sr.CodStatus, sr.Status, "
                + " u.CodUsuario, u.Nome "
                + " from Requisicoes r "
                + " inner join Projetos p on p.CodProjeto = r.CodProjeto "
                + " inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq "
                + " inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus "
                + " inner join Usuario u on u.CodUsuario = r.CodUsuario "
                + " where r.CodRequisicao = ? "
                + " and r.CodArq = 1 order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
     
        stm.setString(1, codRequisicao);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> FollowUpRequisicoesFiltro(String user, java.util.Date dataInicial, java.util.Date dataFinal, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        //CONVERT DATA MYSQL
        Format convertData = new Format();

        java.sql.Date dataInicialConvert;
        java.sql.Date dataFinalConvert;

        dataInicialConvert = convertData.convertDataSql(dataInicial);
        dataFinalConvert = convertData.convertDataSql(dataFinal);

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodRequisicao inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //Date inicialLike= "%"+dataInicial+"%";
        //Date finalLike= "%"+dataFinal+"%";
        stm.setString(1, user);
        stm.setDate(2, dataInicialConvert);
        stm.setDate(3, dataFinalConvert);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Solicitante solicitante = new Solicitante();
            Projetos projetos = new Projetos();
            Item item = new Item();
            Fornecedor fornecedor = new Fornecedor();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            requisicaoRetorno.setSolicitante(solicitante);
            //DATAS
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataAprovacao(resultado.getDate("DataAprov"));
            requisicaoRetorno.setDataEntrega(resultado.getDate("DataEntrega"));
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //ITEM
            item.setNomeItem(resultado.getString("NomeItem"));
            requisicaoRetorno.setItem(item);
            //FORNECEDOR
            fornecedor.setTempoProducao(resultado.getInt("TempoProducao"));
            fornecedor.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            requisicaoRetorno.setFornecedor(fornecedor);
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> FollowUpRequisicoes(String user, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 order by r.CodRequisicao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, user);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Solicitante solicitante = new Solicitante();
            Projetos projetos = new Projetos();
            Item item = new Item();
            Fornecedor fornecedor = new Fornecedor();
            Usuario usuario = new Usuario();
            EtapaRequisicao etapa = new EtapaRequisicao();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            requisicaoRetorno.setSolicitante(solicitante);
            //DATAS
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataAprovacao(resultado.getDate("DataAprov"));
            requisicaoRetorno.setDataEntrega(resultado.getDate("DataEntrega"));
            requisicaoRetorno.setDataPrevisaoEntrega(resultado.getDate("DataPrevisaoEntrega"));
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //ITEM
            item.setNomeItem(resultado.getString("NomeItem"));
            requisicaoRetorno.setItem(item);
            //FORNECEDOR
            fornecedor.setTempoProducao(resultado.getInt("TempoProducao"));
            fornecedor.setLogistica(resultado.getInt("Logistica"));
            fornecedor.setNomeFornecedor(resultado.getString("NomeFornecedor"));
            requisicaoRetorno.setFornecedor(fornecedor);
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //ETAPA
            etapa.setEtapaRequisicao(resultado.getString("EtapaRequisicao"));
            requisicaoRetorno.setEtapaRequisicao(etapa);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> localizarRequisicoesUserAprovadorTecnico(String aprovador, String nivel, String nameDb) throws SQLException, ClassNotFoundException {

        //UsuarioDAO usuarioDao = new UsuarioDAO();
        //Usuario user;
        //user = usuarioDao.localizarNivelUsuario(userInformado, nameDb);
        //String nivel = user.getNivel().getNomeNivel();
        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where r.TipoAprovador = ? and r.AprovadorTecnico = ? and sr.Status = 'Enviada' order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, nivel);
        stm.setString(2, aprovador);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> localizarRequisicoesUserAprovadorFinanceiro(String aprovador, String nivel, String nameDb) throws SQLException, ClassNotFoundException {

        //UsuarioDAO usuarioDao = new UsuarioDAO();
        //Usuario user;
        //user = usuarioDao.localizarNivelUsuario(userInformado, nameDb);
        //String nivel = user.getNivel().getNomeNivel();
        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where r.TipoAprovador = ? and r.Aprovador = ? and sr.Status = 'Enviada' or sr.Status = 'Aprovada Tecnico' order by CodRequisicao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, nivel);
        stm.setString(2, aprovador);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();

            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(statusRequisicao);
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public List<Requisicoes> ObterRequisicoesVinculacao(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List req = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao from Requisicoes r";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Requisicoes requisicaoRetorno;
            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));

            req.add(requisicaoRetorno);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return req;
    }

    public void salvar(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException, IOException {

        //SALVAR SOLICITANTE
        SolicitanteDAO solic = new SolicitanteDAO();
        solic.salvar(requisicao.getSolicitante(), nameDb);

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date solicitacao = requisicao.getDataSolicitacao();
        java.util.Date criacao = requisicao.getDataCriacao();
        java.util.Date previsao = requisicao.getDataPrevisaoEntrega();

        java.sql.Date solicitacaoConvert;
        java.sql.Date criacaoConvert;
        java.sql.Date previsaoConvert;

        solicitacaoConvert = convertData.convertDataSql(solicitacao);
        criacaoConvert = convertData.convertDataSql(criacao);
        previsaoConvert = convertData.convertDataSql(previsao);

        Connection conexao = conectar(nameDb);

        String sql = "insert into Requisicoes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        //SEQUENCIA
        //<-----------------------------------
        int seq = obterProximoValorSequence(nameDb);

        //alterando objeto
        requisicao.setId(seq);

        stmt.setInt(1, requisicao.getId());
        //----------------------------------->
        stmt.setDate(2, solicitacaoConvert);
        stmt.setDate(3, criacaoConvert);
        //DATA APROVACAO
        stmt.setDate(4, null);
        //DATA ENTREGA
        stmt.setDate(5, null);
        //DATA APROV TECNICO
        stmt.setDate(6, null);
        //DATA PREVISAO ENTREGA
        stmt.setDate(7, previsaoConvert);
        stmt.setString(8, requisicao.getJustificativa());
        stmt.setString(9, requisicao.getMotivo());
        stmt.setInt(10, requisicao.getVinculacao());
        stmt.setString(11, requisicao.getAprovador());
        stmt.setString(12, requisicao.getAprovadorTecnico());
        stmt.setString(13, requisicao.getTipoAprovador());
        stmt.setInt(14, requisicao.getDestinacao().getId());
        stmt.setInt(15, requisicao.getMoedas().getId());
        stmt.setInt(16, requisicao.getProjetos().getId());
        stmt.setInt(17, requisicao.getTipoRequisicao().getId());
        stmt.setInt(18, requisicao.getStatusRequisicao().getId());
        stmt.setInt(19, requisicao.getTipoFrete().getId());
        stmt.setInt(20, requisicao.getUsuario().getId());
        stmt.setInt(21, requisicao.getStatusArqRequisicao().getId());
        stmt.setInt(22, requisicao.getSolicitante().getId());
        stmt.setInt(23, requisicao.getEtapaRequisicao().getId());

        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public void update(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException, IOException {
        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date previsao = requisicao.getDataPrevisaoEntrega();
        java.util.Date entrega = requisicao.getDataEntrega();
        
        java.sql.Date previsaoConvert;
        java.sql.Date entregaConvert;
        
        previsaoConvert = convertData.convertDataSql(previsao);
        if(entrega != null){
        entregaConvert = convertData.convertDataSql(entrega);
        }else{
            entregaConvert = null;
        }

        //REMOVE FORNECEDOR E ITEM
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        ItemDAO itemDao = new ItemDAO();
        Fornecedor fornecedor = new Fornecedor();
        Item item = new Item();
        fornecedor.setRequisicao(requisicao);
        fornecedorDao.deleteFornecedorRequisicao(fornecedor, nameDb);
        item.setRequisicoes(requisicao);
        itemDao.deleteItemRequisicao(item, nameDb);

        Connection conexao = conectar(nameDb);

        String sql = "update Requisicoes r set r.CodProjeto = ?, r.DataEntrega = ?, r.CodDest = ?, r.CodTipoReq = ?, r.CodMoeda = ?, r.TipoAprovador = ?, r.Aprovador = ? ,r.AprovadorTecnico = ? , r.Justificativa = ?, r.Motivo = ?, r.DataPrevisaoEntrega = ?, r.CodEtapaRequisicao = ?, r.CodigoStatus = ? where r.CodRequisicao = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, requisicao.getProjetos().getId());
        stmt.setObject(2, entregaConvert);
        stmt.setInt(3, requisicao.getDestinacao().getId());
        stmt.setInt(4, requisicao.getTipoRequisicao().getId());
        stmt.setInt(5, requisicao.getMoedas().getId());
        stmt.setString(6, requisicao.getTipoAprovador());
        stmt.setString(7, requisicao.getAprovador());
        stmt.setString(8, requisicao.getAprovadorTecnico());
        stmt.setString(9, requisicao.getJustificativa());
        stmt.setString(10, requisicao.getMotivo());
        stmt.setDate(11, previsaoConvert);
        stmt.setInt(12, requisicao.getEtapaRequisicao().getId());
        stmt.setInt(13, requisicao.getStatusRequisicao().getId());
        stmt.setInt(14, requisicao.getId());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    //OBTER SEQUENCIA MYSQL
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        Integer id = null;
        int retorno = 0;

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Requisicoes";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        while (resultado.next()) {
            id = resultado.getInt("CodRequisicao");
            retorno = id + 1;
        }
        // Encerrando a conexão.
        conexao.close();
        resultado.close();
        stm.close();
        return retorno;
    }

    public Requisicoes localizarRequisicaoParaAprovar(Requisicoes requisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        //List req = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataEntrega, r.DataPrevisaoEntrega, r.Justificativa, r.Motivo, r.Vinculacao, r.TipoAprovador, r.Aprovador, r.AprovadorTecnico, d.Destinacao, m.Abrev, p.NomeProjeto, tr.TipoRequisicao, tf.TipoFrete, sr.Status, u.Nome, s.NomeSolicitante, e.EtapaRequisicao from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest inner join Moedas m on r.CodMoeda = m.CodMoeda inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join TipoFrete tf on tf.CodFrete = r.CodFrete inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where r.CodRequisicao = ? order by CodRequisicao";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicaoInformado.getId());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        Requisicoes requisicaoRetorno = new Requisicoes();

        // criando objeto de retorno
        while (resultado.next()) {

            Destinacao destinacao = new Destinacao();
            Moedas moedas = new Moedas();
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            TipoFrete tipoFrete = new TipoFrete();
            StatusRequisicao status = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            EtapaRequisicao etapa = new EtapaRequisicao();

            requisicaoRetorno.setId(resultado.getInt("CodRequisicao"));
            requisicaoRetorno.setJustificativa(resultado.getString("Justificativa"));
            requisicaoRetorno.setMotivo(resultado.getString("Motivo"));
            requisicaoRetorno.setVinculacao(resultado.getInt("Vinculacao"));
            requisicaoRetorno.setTipoAprovador(resultado.getString("TipoAprovador"));
            //DATAS
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            requisicaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            requisicaoRetorno.setDataEntrega(resultado.getDate("DataEntrega"));
            requisicaoRetorno.setDataPrevisaoEntrega(resultado.getDate("DataPrevisaoEntrega"));
            //APROVADOR
            requisicaoRetorno.setAprovador(resultado.getString("Aprovador"));
            requisicaoRetorno.setAprovadorTecnico(resultado.getString("AprovadorTecnico"));
            //DESTINACAO
            destinacao.setDestinacao(resultado.getString("Destinacao"));
            requisicaoRetorno.setDestinacao(destinacao);
            //MOEDAS
            moedas.setMoedaAbreviada(resultado.getString("Abrev"));
            requisicaoRetorno.setMoedas(moedas);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            requisicaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            requisicaoRetorno.setTipoRequisicao(tipoRequisicao);
            //TIPO FRETE
            tipoFrete.setTipoFrete(resultado.getString("TipoFrete"));
            requisicaoRetorno.setTipoFrete(tipoFrete);
            //STATUS
            status.setStatusRequisicao(resultado.getString("Status"));
            requisicaoRetorno.setStatusRequisicao(status);
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            requisicaoRetorno.setUsuario(usuario);
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            requisicaoRetorno.setSolicitante(solicitante);
            //ETAPA REQUISICAO
            etapa.setEtapaRequisicao(resultado.getString("EtapaRequisicao"));
            requisicaoRetorno.setEtapaRequisicao(etapa);

        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return requisicaoRetorno;
    }

    public void aprovarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date data = new java.util.Date();
        java.util.Date aprovacao = convertData.formatDataHoje(data);

        java.sql.Date aprovacaoConvert;

        aprovacaoConvert = convertData.convertDataSql(aprovacao);

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.DataAprov = ?, r.CodigoStatus = ?, r.Aprovador = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setDate(1, aprovacaoConvert);
        //STATUS APROVADA COD 3
        stm.setInt(2, 3);
        stm.setString(3, requisicao.getAprovador());
        stm.setInt(4, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void aprovarTecnicoRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date data = new java.util.Date();
        java.util.Date aprovacaoTecnico = convertData.formatDataHoje(data);

        java.sql.Date aprovacaoTecnicoConvert;

        aprovacaoTecnicoConvert = convertData.convertDataSql(aprovacaoTecnico);

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.DataAprovTecnico = ?, r.CodigoStatus = ?, r.AprovadorTecnico = ?, r.TipoAprovador = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        String aprovadorFinanceiro = "Aprovador Financeiro";
        stm.setDate(1, aprovacaoTecnicoConvert);
        //STATUS APROVADA TEC COD 7
        stm.setInt(2, 7);
        stm.setString(3, requisicao.getAprovadorTecnico());
        stm.setString(4, aprovadorFinanceiro);
        stm.setInt(5, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void recusarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.CodigoStatus = ?, r.Aprovador = ?, r.Motivo = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS RECUSADA COD 4
        stm.setInt(1, 4);
        stm.setString(2, requisicao.getAprovador());
        stm.setString(3, requisicao.getMotivo());
        stm.setInt(4, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void arquivarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.CodArq = ?, r.CodigoStatus = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS ARQUIVADA COD 8
        stm.setInt(1, 2);
        stm.setInt(2, 8);
        stm.setInt(3, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void desarquivarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.CodArq = ?, r.CodigoStatus = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS APROVADA COD 3
        stm.setInt(1, 1);
        stm.setInt(2, 9);
        stm.setInt(3, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void cancelarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.CodigoStatus = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS CANCELADA COD 5
        stm.setInt(1, 5);
        stm.setInt(2, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void finalizarRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.DataEntrega = ?, r.CodigoStatus = ? where r.CodRequisicao = ?";

        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date entrega = requisicao.getDataEntrega();
        java.sql.Date entregaConvert;
        entregaConvert = convertData.convertDataSql(entrega);

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS FINALIZADA COD 6
        stm.setDate(1, entregaConvert);
        stm.setInt(2, 6);
        stm.setInt(3, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }
    
    public void retornaRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.CodigoStatus = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS FINALIZADA COD 6
        stm.setInt(1, requisicao.getStatusRequisicao().getId());
        stm.setInt(2, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public void updateRequisicao(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
        //CONVERT DATA MYSQL
        Format convertData = new Format();
        java.util.Date previsao = requisicao.getDataPrevisaoEntrega();

        java.sql.Date dataPrevisaoConvert;

        dataPrevisaoConvert = convertData.convertDataSql(previsao);

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Requisicoes r set r.Justificativa = ?, r.DataPrevisaoEntrega = ?, r.CodEtapaRequisicao = ? where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        //STATUS FINALIZADA COD 6
        stm.setString(1, requisicao.getJustificativa());
        stm.setDate(2, dataPrevisaoConvert);
        stm.setInt(3, requisicao.getEtapaRequisicao().getId());
        stm.setInt(4, requisicao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
        conexao.close();
    }

    public Requisicoes localizarRequisicaoEmail(int id, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        //List solicitacao = new ArrayList();
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select r.CodRequisicao, r.DataCriacao, u.Nome, u.EmailFunc, u2.EmailFunc as EmailAprov from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Usuario u2 on u2.Nome = r.AprovadorTecnico where r.CodRequisicao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, id);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        Requisicoes requisicaoRetorno = new Requisicoes();

        // criando objeto de retorno
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            //Solicitante solicitante = new Solicitante();            
            requisicaoRetorno = new Requisicoes();
            requisicaoRetorno.setDataCriacao(resultado.getDate("DataCriacao"));
            usuario.setLogin(resultado.getString("Nome"));
            usuario.setEmail(resultado.getString("EmailFunc"));
            usuario.setEmailAprovadorTecnico(resultado.getString("EmailAprov"));
            requisicaoRetorno.setUsuario(usuario);

        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return requisicaoRetorno;
    }

    public Requisicoes ObterEmail(Requisicoes requisicao, String dbName) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List user = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(dbName);

        // contruindo a consulta
        String sql = "select u.EmailFunc, u2.EmailFunc as EmailAprov from Usuario u inner join Usuario u2 where u.Nome = ? and u2.Nome = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, requisicao.getUsuario().getLogin());
        stm.setString(2, requisicao.getAprovadorTecnico());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        // criando objeto de retorno
        Requisicoes requisicaoRetorno = new Requisicoes();
        while (resultado.next()) {
            requisicaoRetorno = new Requisicoes();
            Usuario usuario = new Usuario();
            usuario.setEmail(resultado.getString("EmailFunc"));
            usuario.setEmailAprovadorTecnico(resultado.getString("EmailAprov"));
            requisicaoRetorno.setUsuario(usuario);
        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return requisicaoRetorno;
    }

    public String localizarNivel(Requisicoes requisicaoInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select n.NomeNivel from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Niveis n on n.CodNivel = u.CodNivel where r.CodRequisicao = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicaoInformado.getId());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Requisicoes requisicaoRetorno = new Requisicoes();
        Usuario usuario = new Usuario();
        Niveis nivel = new Niveis();
        String nivelRetorno = null;

        while (resultado.next()) {
            nivel.setNomeNivel(resultado.getString("NomeNivel"));
            usuario.setNivel(nivel);
            requisicaoRetorno.setUsuario(usuario);
            nivelRetorno = nivel.getNomeNivel();

        }
        // Encerrando a conexão.
        resultado.close();
        stm.close();
        conexao.close();
        return nivelRetorno;
    }

}
