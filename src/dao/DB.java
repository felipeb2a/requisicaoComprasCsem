/*@author felipe.ferreira*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DB extends AcessDB {
/*
    public ResultSet consultar(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = conectar();
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        return resultado;
    }

    public void executar(String insercao, Object[] parametros, Object[] tipos) throws Exception {
        try {
            // armazena a conexão com o banco.
            Connection conn = conectar();

            PreparedStatement stm = conn.prepareStatement(insercao);

            for (int i = 0; i < parametros.length; i++) {

                if (tipos[i] == String.class) {
                    if (parametros[i] != null) {
                        stm.setString(i + 1, (String) parametros[i]);
                    } else {
                        stm.setNull(i + 1, Types.VARCHAR);
                    }
                } else if (tipos[i] == Integer.class) {
                    if (parametros[i] != null) {
                        stm.setInt(i + 1, (Integer) parametros[i]);
                    } else {
                        stm.setNull(i + 1, Types.INTEGER);
                    }
                } else if (tipos[i] == Double.class) {
                    if (parametros[i] != null) {
                        stm.setDouble(i + 1, (Double) parametros[i]);
                    } else {
                        stm.setNull(i + 1, Types.DOUBLE);
                    }
                }
            }

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro Desconhecido!");
            e.printStackTrace();
            throw e;
        }
    }
*/
}
