/*@author felipe.ferreira*/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class AcessDB {

    protected Connection conectar(String nameDb) throws ClassNotFoundException, SQLException {
        
        //PRINCIPAL POOL CONECTIONs      
        //PROPRIEDADES DE CONEXAO DO BANCO       
        String endereco = "15.0.0.9";
        String database = nameDb;
        //MYSQL 8.0 REQUER SSL E TIMEZONE
        String ssl = "?useSSL=false";
        String timeZone = "&useTimezone=true&serverTimezone=UTC";
        
        String urlSSL = "jdbc:mysql://" + endereco + "/" + database + ssl + timeZone;
        String url = "jdbc:mysql://" + endereco + "/" + database;
        //USUARIO DE ACESSO AO BANCO
        String usuario = "compras";
        String senha = "Compr@s2017";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(urlSSL);
        dataSource.setUsername(usuario);
        dataSource.setPassword(senha);
        
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(200);
        
        Connection con = dataSource.getConnection();
        return con;
        
        /*######################################################################################*/
        /*
        Class.forName //MYSQL
                ("com.mysql.jdbc.Driver");        
        
        //PRINCIPAL       
        
        String endereco = "15.0.0.9";        
        String database = nameDb;
        //MYSQL 8.0 REQUER SSL E TIMEZONE
        //String ssl = "?useSSL=false";
        //String timeZone = "&useTimezone=true&serverTimezone=UTC";
        String usuario = "compras";
        String senha = "Compr@s2017";
        String url = "jdbc:mysql://" + endereco + "/" + database;

        return DriverManager.getConnection(url, usuario, senha);        
/*######################################################################################*/
        /*
        //TESTE        
        //String endereco = "15.0.9.184";        
        String endereco = "15.0.0.8";
        String database = nameDb;
        //MYSQL 8.0 REQUER SSL E TIMEZONE
        String ssl = "?useSSL=false";
        String timeZone = "&useTimezone=true&serverTimezone=UTC";
        String usuario = "root";
        String senha = "root";
        //String urcl = "jdbc:mysql://" + endereco + "/" + database + ssl + timeZone;
        String url = "jdbc:mysql://" + endereco + "/" + database;
        //System.out.println(url);
        return DriverManager.getConnection(url, usuario, senha);
        /*######################################################################################*/
        /*
        //TESTE POOL CONECTIONs      
        //PROPRIEDADES DE CONEXAO DO BANCO
        //String endereco = "15.0.9.184";        
        String endereco = "15.0.0.8";
        String database = nameDb;
        //MYSQL 8.0 REQUER SSL E TIMEZONE
        String ssl = "?useSSL=false";
        String timeZone = "&useTimezone=true&serverTimezone=UTC";
        
        String urlSSL = "jdbc:mysql://" + endereco + "/" + database + ssl + timeZone;
        String url = "jdbc:mysql://" + endereco + "/" + database;
        //USUARIO DE ACESSO AO BANCO
        String usuario = "root";
        String senha = "root";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(urlSSL);
        dataSource.setUsername(usuario);
        dataSource.setPassword(senha);
        
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(200);
        
        Connection con = dataSource.getConnection();
        return con;
        /*######################################################################################*/
    }

}
