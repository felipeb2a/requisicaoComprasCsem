/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.mysql.fabric.xmlrpc.base.Data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import log.Log;
import model.CreateFolder;
import model.Criptografia;
import model.FilesPath;
import model.Format;
import model.Fornecedor;
import model.JavaMail;
import model.LogArquivoTexto;
import model.Moeda;
import model.Requisicoes;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import view.initial.TelaLogin;

/**
 *
 * @author felipe.ferreira
 */
public class Teste {

    public static void main(String args[]) throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException, Exception {

        //DATA HOJE
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        //Date dataA = sdf.parse(a);
        //Date dataB = sdf.parse(b); 
        Date a = null;
        Date b;
        int dias = 10;

        dias = (int) ((dataHoje.getTime() + dias) / 86400000L);
        System.out.println(dias);

        c.add(Calendar.DAY_OF_MONTH, 60);
        System.out.println("Sessenta dias depois: " + sdf.format(c.getTime()));
        
        
        //c.add(Calendar.DAY_OF_MONTH, dias);
        String dataF = sdf.format(c.getTime());
        a = c.getTime();
        System.out.println(Teste.class.getName() + "\n" + a);
        Teste.class.getMethods();
         
        List f = new ArrayList();
        Fornecedor fornc = new Fornecedor();
        for (int i = 0; i < 3; i++) {
            fornc.setNomeFornecedor("name" + i);
            f.add(fornc);
        }
        
        for(int i =0; i<f.size();i++){
            System.out.println(fornc.getNomeFornecedor());
        }

        for (Iterator it = f.iterator(); it.hasNext();) {

            fornc = (Fornecedor) it.next();
            System.out.println(fornc.getNomeFornecedor());
        }*/
        String pastaCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\2018\\";

        //java.awt.Desktop.getDesktop().open(new File(pastaCsem + 2018 + "\\" + 591));

        /*File f = new File(pastaCsem);
        f.getName().startsWith("591");
        f.getName().contains(pastaCsem);
        File[] arquivos = f.listFiles(); //retorna um array de Files
        String[] nomes = f.list(); //retorna o nome dos arquivos em Strings

        for (String s : nomes) {
            System.out.println(s);
        }
         */
        
        File f = new File(pastaCsem);
        //System.out.println(f.getName().contains("591"));
        
        File[] arquivos = f.listFiles(); //retorna um array de Files
        String[] nomes = f.list(); //retorna o nome dos arquivos em Strings
        String teste;
        for (String s : nomes) {
            if(s.contains("591")){
                //java.awt.Desktop.getDesktop().open(new File(pastaCsem + "\\" + s));
                //teste = s;
                //System.out.println(s);
            }
        }
        
        FilesPath path = new FilesPath();
        String a = "591";
        String nome = path.RcRenomeada(pastaCsem, a);
        
        System.out.println(nome);
        java.awt.Desktop.getDesktop().open(new File(pastaCsem + "\\" + nome));
    }

}
