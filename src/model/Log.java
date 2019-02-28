/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class Log {

    public Logger pathLog(String nomeClasse, String nameDb) throws IOException, SecurityException, Exception {
         //verifica pasta
        VerificaParametro verifica = new VerificaParametro();
        String pathP = verifica.VerficaNameDBgeraLogGeral(nameDb);

        //data e hora agora
        LocalDateTime date = LocalDateTime.now();
        //formatar data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm");
        String dataStr = date.format(formatter);
        //definir nome do arquivo
        String nameArquivo = "log-" + dataStr + ".log";
        //define o local do arquivo
        String path = pathP + "\\" + nameArquivo;
        //passar nome da classe para o logger
        Logger logger = Logger.getLogger(nomeClasse);
        //passar o caminho + nome do arquivo
        FileHandler fh = new FileHandler(path);
        logger.addHandler(fh);
        
        return logger;
    }
}
