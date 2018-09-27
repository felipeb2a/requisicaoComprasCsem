/*@author felipe.ferreira*/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessDB {

    protected Connection conectar(String nameDb) throws ClassNotFoundException, SQLException {
        Class.forName //MYSQL
                ("com.mysql.jdbc.Driver");
        /*
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
        
        //TESTE        
        //String endereco = "15.0.9.184";        
        String endereco = "15.0.0.8";
        String database = nameDb;
        //MYSQL 8.0 REQUER SSL E TIMEZONE
        String ssl = "?useSSL=false";
        String timeZone = "&useTimezone=true&serverTimezone=UTC";
        String usuario = "root";
        String senha = "root";
        //String url = "jdbc:mysql://" + endereco + "/" + database + ssl + timeZone;
        String url = "jdbc:mysql://" + endereco + "/" + database;
        //System.out.println(url);
        return DriverManager.getConnection(url, usuario, senha);
        /*######################################################################################*/
    }

}
