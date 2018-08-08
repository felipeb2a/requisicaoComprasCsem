package model;

import java.io.File;

/**
 *
 * @author felipe.ferreira
 */
public class FilesPath {
    
    //DEFINIR PASTAS PRINCIPAL
    
    //RC
    private static String pastaRcCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";
    private static String pastaRcSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";
    private static String pastaRcSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";

    //RC RH
    private static String pastaRcCsemRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\01. ORDENS DE PAGAMENTO\\";
    private static String pastaRcSunewRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\";
    private static String pastaRcSunewGeradoresRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\";

    //OC
    private static String pastaOcCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";
    private static String pastaOcSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";
    private static String pastaOcSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\";

    //OC RH
    private static String pastaOcCsemRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\01. ORDENS DE PAGAMENTO\\";
    private static String pastaOcSunewRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\";
    private static String pastaOcSunewGeradoresRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\";

    //ATIVOS
    private static String ativosCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio CSEM.xlsx";
    private static String ativosSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio SUNEW 1504.xlsx";
    private static String ativosSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio SUNEW GERADORES 1504.xlsx";

    //DADOS
    private static String dadosCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\DADOS CADASTRAIS CSEM.PDF";
    private static String dadosSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\Cadastro Padrão Sunew.pdf";
    private static String dadosSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\Cadastro Padrão Sunew Geradores.pdf";
    
    //FOLLOW-UP
    private static String pastaCsemFollowUp = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\04. FOLLOW UP\\";
    private static String pastaSunewFollowUp = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\03. FOLLOW UP\\";
    private static String pastaSunewGeradoresFollowUp = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\03. FOLLOW UP\\";

    //FOLLOW-UP RH
    private static String pastaCsemFollowUpRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\02. FOLLOW-UP\\";
    private static String pastaSunewFollowUpRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\02. FOLLOW-UP\\";
    private static String pastaSunewGeradoresFollowUpRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\02. FOLLOW-UP\\";
    
     //LOG
    private static String pastaCsemLog = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\";
    private static String pastaSunewLog = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\";
    private static String pastaSunewGeradoresLog = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\";
    
/*###################################################################################################################################################################################*/
    /*
    //DEFINIR PASTAS TESTE
    //RC
    private static String pastaRcCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";
    private static String pastaRcSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";
    private static String pastaRcSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";

    //RC RH
    private static String pastaRcCsemRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";
    private static String pastaRcSunewRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";
    private static String pastaRcSunewGeradoresRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";

    //OC
    private static String pastaOcCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";
    private static String pastaOcSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";
    private static String pastaOcSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\02. ORDENS DE COMPRAS\\01 - TESTE\\";

    //OC RH
    private static String pastaOcCsemRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";
    private static String pastaOcSunewRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";
    private static String pastaOcSunewGeradoresRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\01. ORDENS DE PAGAMENTO\\01 - TESTE\\";

    //ATIVOS
    private static String ativosCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio CSEM.xlsx";
    private static String ativosSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio SUNEW 1504.xlsx";
    private static String ativosSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\02. CONTROLE DE ATIVOS\\Patrimonio SUNEW GERADORES 1504.xlsx";

    //DADOS
    private static String dadosCsem = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\Dados cadastrais - CSEM Brasil.pdf";
    private static String dadosSunew = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\Cadastro Padrão Sunew.pdf";
    private static String dadosSunewGeradores = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\Cadastro Padrão Sunew Geradores.pdf";

    //FOLLOW-UP
    private static String pastaCsemFollowUp = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\04. FOLLOW UP\\01 - TESTE\\";
    private static String pastaSunewFollowUp = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\03. FOLLOW UP\\01 - TESTE\\";
    private static String pastaSunewGeradoresFollowUp = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\03. FOLLOW UP\\01 - TESTE\\";

    //FOLLOW-UP RH
    private static String pastaCsemFollowUpRh = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\FINANCEIRO\\Funcionários\\RH\\02. FOLLOW-UP\\01 - TESTE\\";
    private static String pastaSunewFollowUpRh = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\02. FOLLOW-UP\\01 - TESTE\\";
    private static String pastaSunewGeradoresFollowUpRh = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\RH\\02. FOLLOW-UP\\01 - TESTE\\";

    //LOG
    private static String pastaCsemLog = "\\\\15.0.0.3\\Team\\01. CSEM Brasil\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\01 - TESTE\\";
    private static String pastaSunewLog = "\\\\15.0.0.3\\Team\\02. SUNEW\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\01 - TESTE\\";
    private static String pastaSunewGeradoresLog = "\\\\15.0.0.3\\Team\\06. SUNEW GERADORES\\01. ADMINISTRATIVO-FINANCEIRO\\COMPRAS\\01. CONTROLE DE COMPRAS\\07. LOGs\\01 - TESTE\\";

    /*###################################################################################################################################################################################*/
    public static String getPastaRcCsem() {
        return pastaRcCsem;
    }

    public static String getPastaRcSunew() {
        return pastaRcSunew;
    }

    public static String getPastaRcSunewGeradores() {
        return pastaRcSunewGeradores;
    }

    public static String getPastaRcCsemRh() {
        return pastaRcCsemRh;
    }

    public static String getPastaRcSunewRh() {
        return pastaRcSunewRh;
    }

    public static String getPastaRcSunewGeradoresRh() {
        return pastaRcSunewGeradoresRh;
    }

    public static String getPastaOcCsem() {
        return pastaOcCsem;
    }

    public static String getPastaOcSunew() {
        return pastaOcSunew;
    }

    public static String getPastaOcSunewGeradores() {
        return pastaOcSunewGeradores;
    }

    public static String getPastaOcCsemRh() {
        return pastaOcCsemRh;
    }

    public static String getPastaOcSunewRh() {
        return pastaOcSunewRh;
    }

    public static String getPastaOcSunewGeradoresRh() {
        return pastaOcSunewGeradoresRh;
    }

    public static String getAtivosCsem() {
        return ativosCsem;
    }

    public static String getAtivosSunew() {
        return ativosSunew;
    }

    public static String getAtivosSunewGeradores() {
        return ativosSunewGeradores;
    }

    public static String getDadosCsem() {
        return dadosCsem;
    }

    public static String getDadosSunew() {
        return dadosSunew;
    }

    public static String getDadosSunewGeradores() {
        return dadosSunewGeradores;
    }

    public static String getPastaCsemFollowUp() {
        return pastaCsemFollowUp;
    }

    public static String getPastaSunewFollowUp() {
        return pastaSunewFollowUp;
    }

    public static String getPastaSunewGeradoresFollowUp() {
        return pastaSunewGeradoresFollowUp;
    }

    public static String getPastaCsemFollowUpRh() {
        return pastaCsemFollowUpRh;
    }

    public static String getPastaSunewFollowUpRh() {
        return pastaSunewFollowUpRh;
    }

    public static String getPastaSunewGeradoresFollowUpRh() {
        return pastaSunewGeradoresFollowUpRh;
    }

    public static String getPastaCsemLog() {
        return pastaCsemLog;
    }

    public static String getPastaSunewLog() {
        return pastaSunewLog;
    }

    public static String getPastaSunewGeradoresLog() {
        return pastaSunewGeradoresLog;
    }

    //VERIFICAR RC RENOMEADA
    public String RcRenomeada(String caminho, String id) {

        //DEFINE CAMINHO
        File f = new File(caminho);
        //STRING RETORNO
        String retorno = "";
        //ARRAY DE FILES NO CAMINHO ESPECIFICADO
        File[] arquivos = f.listFiles(); //retorna um array de Files
        //CONVERTE LISTA DE ARQUIVOS EM STRING
        String[] nomes = f.list(); //retorna o nome dos arquivos em Strings
        //PERSISTE A LISTA E VERIFICA SE EXISTE A PASTA FOI RENOMEADA
        for (String s : nomes) {
            if (s.contains(id)) {
                retorno = caminho + "\\"+ s;
            }
        }
        return retorno;
    }
}
