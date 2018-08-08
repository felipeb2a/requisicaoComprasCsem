package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author felipe.ferreira
 */
public class CreateFolder {

    Format anoAtual = new Format();
    //int ano = anoAtual.dataAnoAtual();
    
    //DEFINE PASTAS E ARQUIVOS
    FilesPath path = new FilesPath();
    String RcRenomeada;
    
    public void createFolderRCCsem(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcCsem() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();//mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
        }        
    }

    public void createFolderRCSunew(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcSunew() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderRCSunewGeradores(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcSunewGeradores() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //RH RC
    public void createFolderRCCsemRh(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcCsemRh() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();//mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
        }
    }

    public void createFolderRCSunewRh(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcSunewRh() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderRCSunewGeradoresRh(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaRcSunewGeradoresRh() + anoBd[1] + "\\" + codRequisicao);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //CRIAR PASTA ORDENS DE COMPRA SE NÂO EXISTIR
    public void createFolderOCCsem(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaOcCsem() + anoBd[1]);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderOCSunew(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaOcSunew() + anoBd[1]);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderOCSunewGeradores(Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaOcSunewGeradores() + anoBd[1]);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //CRIAR PASTA FOLLOW UP SE NÂO EXISTIR
    public void createFolderFollowUpCsem(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaCsemFollowUp() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderFollowUpSunew(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewFollowUp() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderFollowUpSunewGeradores(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewGeradoresFollowUp() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //CRIAR PASTA FOLLOW UP RH SE NÂO EXISTIR
    public void createFolderFollowUpCsemRh(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaCsemFollowUpRh() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderFollowUpSunewRh(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewFollowUpRh() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderFollowUpSunewGeradoresRh(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewGeradoresFollowUpRh() + anoBd[1] + "\\" + user);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //CRIAR PASTA LOG SE NÂO EXISTIR
    public void createFolderLogCsem(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaCsemLog() + anoBd[1] + "\\"+user+"\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderLogSunew(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewLog() + anoBd[1] + "\\"+user+"\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderLogSunewGeradores(String user, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewGeradoresLog() + anoBd[1] + "\\"+user+"\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }
    
    //CRIAR PASTA LOG GERAL SE NÂO EXISTIR
    public void createFolderLogCsemGeral(String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaCsemLog() + anoBd[1] + "\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderLogSunewGeral(String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewLog() + anoBd[1] + "\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void createFolderLogSunewGeradoresGeral(String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        File diretorio = new File(path.getPastaSunewGeradoresLog() + anoBd[1] + "\\");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    //RENAME
    public void renameFolderRCCsem(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcCsem() + anoBd[1], id);
        //String old_name = path.getPastaRcCsem() + anoBd[1] + "\\" + codRequisicao;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

    public void renameFolderRCSunew(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcSunew() + anoBd[1], id);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

    public void renameFolderRCSunewGeradores(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcSunewGeradores() + anoBd[1], id);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

    //RH RC
    public void renameFolderRCCsemRh(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcCsemRh() + anoBd[1], id);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

    public void renameFolderRCSunewRh(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcSunewRh() + anoBd[1], id);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

    public void renameFolderRCSunewGeradoresRh(String verifica, Integer codRequisicao, String nameDb) throws IOException {
        String[] anoBd = nameDb.split("_");
        String id = Integer.toString(codRequisicao);
        String old_name = path.RcRenomeada(path.getPastaRcSunewGeradoresRh() + anoBd[1], id);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File oldfile = new File(old_name);
        if (!oldfile.exists()) {
            System.out.println("File or directory does not exist.");
            //System.exit(0);
        }
        if (verifica.equals("CANCELADA")) {
            String new_name = old_name + " - CANCELADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("ARQUIVADA")) {
            String new_name = old_name + " - ARQUIVADA";
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
        if (verifica.equals("DESARQUIVAR")) {
            oldfile = new File(old_name + " - ARQUIVADA");
            String new_name = old_name;
            File newfile = new File(new_name);
            oldfile.renameTo(newfile);
        }
    }

}
