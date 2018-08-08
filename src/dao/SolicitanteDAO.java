package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Solicitante;

/**
 *
 * @author felipe.ferreira
 */
public class SolicitanteDAO extends AcessDB {

    public Solicitante localizar(Solicitante solicitanteInformado, String nameDb) throws SQLException, ClassNotFoundException {

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Solicitante where NomeSolicitante = ?";
        //String sql = "select * from usuario where Nome= ? and Senha= ?";
        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setString(1, solicitanteInformado.getNomeSolicitante());

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        Solicitante solicitanteRetorno = new Solicitante();

        while (resultado.next()) {
            solicitanteRetorno = new Solicitante();
            solicitanteRetorno.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitanteRetorno.setId(resultado.getInt("CodSolicitante"));
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitanteRetorno;
    }

    public List<Solicitante> ObterSolicitante(String nameDb) throws SQLException, ClassNotFoundException {

        // lista de retorno
        List solicitante = new ArrayList();

        // conectando ao banco de dados
        Connection conexao = conectar(nameDb);

        // contruindo a consulta
        String sql = "select * from Solicitante";

        // criando o objeto que vai executar a consulta no banco
        PreparedStatement stm = conexao.prepareStatement(sql);

        // recebendo o resultado da consulta
        ResultSet resultado = stm.executeQuery();

        // criando objeto de retorno
        while (resultado.next()) {
            Solicitante solicitanteRetorno;
            solicitanteRetorno = new Solicitante();
            solicitanteRetorno.setNomeSolicitante(resultado.getString("NomeSolicitante"));
            solicitanteRetorno.setId(resultado.getInt("CodSolicitante"));

            solicitante.add(solicitanteRetorno);
        }
        // Encerrando a conexão.
        conexao.close();
        return solicitante;
    }
    
    //OBTER SEQUENCIA MYSQL    
    private Integer obterProximoValorSequence(String nameDb) throws SQLException, ClassNotFoundException{

            // lista de retorno
            Integer id = null;
            int retorno = 0;
            
            // conectando ao banco de dados
            Connection conexao = conectar(nameDb);
            
            // contruindo a consulta
            String sql = "select * from Solicitante";
            
            // criando o objeto que vai executar a consulta no banco
            PreparedStatement stm = conexao.prepareStatement(sql);
                        
            // recebendo o resultado da consulta
            ResultSet resultado = stm.executeQuery();
                       
            
            while(resultado.next()){
                id = resultado.getInt("CodSolicitante");
                retorno = id+1;
            }
                    
            return retorno;     
    }

    public void salvar (Solicitante solicitante, String nameDb) throws SQLException, ClassNotFoundException{

            Connection conexao = conectar(nameDb);
            
            String sql = "insert into Solicitante values (?,?)";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            int seq = obterProximoValorSequence(nameDb);
            
            // alterando objeto
            solicitante.setId(seq);
            
            stmt.setInt(1, solicitante.getId());
            stmt.setString(2, solicitante.getNomeSolicitante());
            stmt.execute();            
            stmt.close();
    }
}
