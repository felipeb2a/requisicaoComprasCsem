package dao;

/**
 *
 * @author felipe.ferreira
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Projetos;
import model.Solicitacoes;
import model.Solicitante;
import model.StatusArqRequisicao;
import model.StatusRequisicao;
import model.TipoRequisicao;
import model.Usuario;

public class SolicitacoesDAO extends AcessDB {

    public List<Solicitacoes> localizarSolicitacoesPorUser(Solicitacoes solicitacaoInformada, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodUsuario = ? and s.CodArq = 1 and st.CodStatus = 1 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, solicitacaoInformada.getUsuario().getId());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public Solicitacoes localizarSolicitacoes(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        //List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, s.Motivo, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante where s.CodSolicitacao = ? and st.CodStatus = 1 order by CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, solicitacao.getId());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        Solicitacoes solicitacaoRetorno = new Solicitacoes();

        // criando objeto de retorno
        while (resultado.next()) {
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao")); 
            //JUSTIFICATIVA
            solicitacaoRetorno.setJustificativa(resultado.getString("Justificativa")); 
            //MOTIVO
             solicitacaoRetorno.setMotivo(resultado.getString("Motivo")); 
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacaoRetorno;
    }
    
    public Solicitacoes localizarSolicitacoesCompra(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        //List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, u.Nome, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join Usuario u on s.CodUsuario = u.CodUsuario where s.CodSolicitacao = ? order by CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, solicitacao.getId());
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        Solicitacoes solicitacaoRetorno = new Solicitacoes();

        // criando objeto de retorno
        while (resultado.next()) {
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao")); 
            //JUSTIFICATIVA
            solicitacaoRetorno.setJustificativa(resultado.getString("Justificativa")); 
            //REQUISITANTE
            usuario.setLogin(resultado.getString("Nome"));
            solicitacaoRetorno.setUsuario(usuario);
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacaoRetorno;
    }
    
    public Solicitacoes localizarSolicitacoesEmail(int id, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        //List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select s.EmailSolicitante, s.DataSolicitacao, s.Motivo, u.EmailFunc, u.Nome from Solicitacoes s inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, id);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();
        
        Solicitacoes solicitacaoRetorno = new Solicitacoes();

        // criando objeto de retorno
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            //Solicitante solicitante = new Solicitante();            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setEmailSolicitante(resultado.getString("EmailSolicitante"));
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));
            solicitacaoRetorno.setMotivo(resultado.getString("Motivo"));
            usuario.setEmail(resultado.getString("EmailFunc"));
            usuario.setLogin(resultado.getString("Nome"));            
            solicitacaoRetorno.setUsuario(usuario);
            
                        
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacaoRetorno;
    }
    
    public List<Solicitacoes> FiltrarSolicitacoesUser(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where u.Nome like ? and st.CodStatus = 1 and s.CodArq = 1 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        String userLike= "%"+userInformado+"%";
        stm.setString(1, userLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public List<Solicitacoes> FiltrarSolicitacoesUserCancelada(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where u.Nome like ? and st.CodStatus = 5 and s.CodArq = 1 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        String userLike= "%"+userInformado+"%";
        stm.setString(1, userLike);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public List<Solicitacoes> listaSolicitacoesArquivadas(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodArq = 2 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public List<Solicitacoes> listaSolicitacoesEnviada(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodigoStatus = 2 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public List<Solicitacoes> listaSolicitacoesCancelada(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where u.Nome = ? and s.CodigoStatus = 5 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, userInformado);
        System.out.println(userInformado);
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public List<Solicitacoes> listaSolicitacoesRecusada(String userInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitacao = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodigoStatus = 4 order by s.CodSolicitacao desc";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
        
        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitacoes solicitacaoRetorno;
            Projetos projetos = new Projetos();
            TipoRequisicao tipoRequisicao = new TipoRequisicao();
            StatusRequisicao statusRequisicao = new StatusRequisicao();
            Usuario usuario = new Usuario();
            Solicitante solicitante = new Solicitante();
            StatusArqRequisicao statusArqRequisicao = new StatusArqRequisicao();
            
            solicitacaoRetorno = new Solicitacoes();
            solicitacaoRetorno.setId(resultado.getInt("CodSolicitacao"));
            //DATA
            solicitacaoRetorno.setDataSolicitacao(resultado.getDate("DataSolicitacao"));              
            //PROJETO
            projetos.setProjeto(resultado.getString("NomeProjeto"));
            solicitacaoRetorno.setProjetos(projetos);
            //TIPO REQ
            tipoRequisicao.setTipoRequisicao(resultado.getString("TipoRequisicao"));
            solicitacaoRetorno.setTipoRequisicao(tipoRequisicao);
            //STATUS
            statusRequisicao.setStatusRequisicao(resultado.getString("Status"));
            solicitacaoRetorno.setStatusRequisicao(statusRequisicao);                      
            //SOLICITANTE
            solicitante.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitacaoRetorno.setSolicitante(solicitante);
                        
            solicitacao.add(solicitacaoRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitacao;
    }
    
    public void updateStatus(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodigoStatus = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        //STATUS ENVIADA COD 2
        stm.setInt(1, 2);
        stm.setInt(2, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void arquivarSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodArq = ?, s.CodigoStatus = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        stm.setInt(1, 2);
        stm.setInt(2, 8);
        stm.setInt(3, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void desarquivarSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodArq = ?, s.CodigoStatus = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
          
        stm.setInt(1, 1);
        stm.setInt(2, 9);
        stm.setInt(3, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void cancelarSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodigoStatus = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        //STATUS CANCELADA COD 5
        stm.setInt(1, 5);
        stm.setInt(2, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void desCancelarSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodigoStatus = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        //STATUS NOVA COD 1
        stm.setInt(1, 1);
        stm.setInt(2, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void alterarRequisitanteSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodUsuario = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        //STATUS CANCELADA COD 5
        stm.setInt(1, solicitacao.getUsuario().getId());
        stm.setInt(2, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
    
    public void recusarSolicitacao(Solicitacoes solicitacao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
                
        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "update Solicitacoes s set s.CodigoStatus = ?, s.Motivo = ? where s.CodSolicitacao = ?";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);
               
        //STATUS RECUSADA COD 4
        stm.setInt(1, 4);
        stm.setString(2, solicitacao.getMotivo());
        stm.setInt(3, solicitacao.getId());
        // recebendo o resultado da consulta
        stm.executeUpdate();
        stm.close();
    }
}
