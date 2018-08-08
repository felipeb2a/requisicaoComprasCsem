/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class LogArquivoTexto {

    public void escrever(String user, String texto, String nameDb) throws IOException, Exception {
        //cria pasta
        
        //verifica pasta
        VerificaParametro verifica = new VerificaParametro();
        String pathP = verifica.VerficaNameDBgeraLog(user, nameDb);
        
        //data hoje
        Format f = new Format();
        int ano = f.dataAnoAtual();
        //data de registro no log
        String data;
        data = f.dataTimeHojeString();
        //nome do arquivo de log
        String dataNameArquivo = f.dataHojeString();
        String dataf = dataNameArquivo.replace("/", "-");
        String nameArquivo = "log-"+dataf+".txt";
        //define o local do arquivo
        String path = pathP + "\\" + nameArquivo;
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter conexao = new BufferedWriter(fw);
        conexao.write(data + ": " + texto);
        conexao.newLine();
        conexao.close();
    }
    
    public void escreverGeral(String texto, String nameDb) throws IOException, Exception {
        //cria pasta
        
        //verifica pasta
        VerificaParametro verifica = new VerificaParametro();
        String pathP = verifica.VerficaNameDBgeraLogGeral(nameDb);
        
        //data hoje
        Format f = new Format();
        int ano = f.dataAnoAtual();
        //data de registro no log
        String data;
        data = f.dataTimeHojeString();
        //nome do arquivo de log
        String dataNameArquivo = f.dataHojeString();
        String dataf = dataNameArquivo.replace("/", "-");
        String nameArquivo = "log-"+dataf+".txt";
        //define o local do arquivo
        String path = pathP + "\\" + nameArquivo;
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter conexao = new BufferedWriter(fw);
        conexao.write(data + ": " + texto);
        conexao.newLine();
        conexao.close();
    }
}
