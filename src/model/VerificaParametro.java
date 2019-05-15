package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class VerificaParametro {
    
    //DEFINE PASTAS E ARQUIVOS
    FilesPath path = new FilesPath();
    String RcRenomeada;
    
    //RC
    public void VerficaNameDBCriaPastaRC(String nameDb, Requisicoes requisicao) throws IOException {
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.createFolderRCCsemRh(requisicao.getId(), nameDb);
            } else {
                pasta.createFolderRCCsem(requisicao.getId(), nameDb);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.createFolderRCSunewRh(requisicao.getId(), nameDb);
            } else {
                pasta.createFolderRCSunew(requisicao.getId(), nameDb);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.createFolderRCSunewGeradoresRh(requisicao.getId(), nameDb);
            } else {
                pasta.createFolderRCSunewGeradores(requisicao.getId(), nameDb);
            }
        }
    }
    
    //RENAME CANCELADA ARQUIVADA RC
    public void VerficaNameDBRenamePastaRC(String verifica, String nameDb, Requisicoes requisicao) throws IOException {
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.renameFolderRCCsemRh(verifica, requisicao.getId(), nameDb);
            } else {
                pasta.renameFolderRCCsem(verifica, requisicao.getId(), nameDb);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.renameFolderRCSunewRh(verifica, requisicao.getId(), nameDb);
            } else {
                pasta.renameFolderRCSunew(verifica, requisicao.getId(), nameDb);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            if (requisicao.getUsuario().getNivel().getNomeNivel().equals("RH")) {
                pasta.renameFolderRCSunewGeradores(verifica, requisicao.getId(), nameDb);
            } else {
                pasta.renameFolderRCSunewGeradoresRh(verifica, requisicao.getId(), nameDb);
            }
        }
    }
    
    //FOLLOW-UP
    public void VerficaNameDBCriaPastaFollowUp(String nivel, String nameDb, String user) throws IOException {
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderFollowUpCsem(user, nameDb);
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderFollowUpSunew(user, nameDb);
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderFollowUpSunewGeradores(user, nameDb);
        }
    }
    
     //EXPORT OP
    public void VerficaNameDBCriaPastaRelatorioOP(String nivel, String nameDb, String user) throws IOException {
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderRelatorioOPCsem(user, nameDb);
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderRelatorioOPSunew(user, nameDb);
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderRelatorioOPSunewGeradores(user, nameDb);
        }
    }

    //RELATORIO RC
    public String[] VerficaNameDBgeraRelatorioRC(String nivel, String id, String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio.view.RequisicaoCompraCsem.jasper";

            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsemRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsem()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/RequisicaoCompraSunew.jasper";
            
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunew()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/RequisicaoCompraSunewGeradores.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradoresRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradores()+ anoBd[1], id);
            }
        }

        return nameReport;
    }
    
    //RELATORIO RC CANCELADA
    public String[] VerficaNameDBgeraRelatorioRCCancelada(String nivel, String id, String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/RequisicaoCompraCsemCancelada.jasper";

            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsemRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsem()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/RequisicaoCompraSunewCancelada.jasper";
            
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunew()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/RequisicaoCompraSunewGeradoresCancelada.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradoresRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradores()+ anoBd[1], id);
            }
        }

        return nameReport;
    }

    //RELATORIO OC
    public String[] VerficaNameDBgeraRelatorioOC(String nivel, String id, String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemCompraCsem.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsemRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsem()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemCompraSunew.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunew()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemCompraSunewGeradores.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradoresRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradores()+ anoBd[1], id);
            }
        }

        return nameReport;
    }

    //RELATORIO OP
    public String[] VerficaNameDBgeraRelatorioOP(String nivel, String id, String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemPagamentoCsem.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsemRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcCsem()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemPagamentoSunew.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunew()+ anoBd[1], id);
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/OrdemPagamentoSunewGeradores.jasper";
            if (nivel.equals("RH")) {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradoresRh()+ anoBd[1], id);
            } else {
                nameReport[1] = path.RcRenomeada(path.getPastaRcSunewGeradores()+ anoBd[1], id);
            }
        }

        return nameReport;
    }

    public void VerficaNameDBAbrirPasta(String nivel, String id, String nameDb) throws IOException, Exception {
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            if (nivel.equals("RH")) {
                RcRenomeada = path.RcRenomeada(path.getPastaRcCsemRh()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            } else {
                RcRenomeada = path.RcRenomeada(path.getPastaRcCsem()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            }
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {            
            if (nivel.equals("RH")) {
                RcRenomeada = path.RcRenomeada(path.getPastaRcSunewRh()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            } else {
                RcRenomeada = path.RcRenomeada(path.getPastaRcSunew()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            }
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {            
            if (nivel.equals("RH")) {
                RcRenomeada = path.RcRenomeada(path.getPastaRcSunewGeradoresRh()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            } else {
                RcRenomeada = path.RcRenomeada(path.getPastaRcSunewGeradores()+ anoBd[1], id);
                java.awt.Desktop.getDesktop().open(new File(RcRenomeada));
            }
        }
    }

    public String[] VerficaNameDBgeraRelatorioFollowUp(String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraCsem.jasper";
            nameReport[1] = path.getPastaCsemFollowUp();
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraSunew.jasper";
            nameReport[1] = path.getPastaSunewFollowUp();
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraSunewGeradores.jasper";
            nameReport[1] = path.getPastaSunewGeradoresFollowUp();
        }

        return nameReport;
    }
    
    public String[] VerficaNameDBgeraRelatorioExportOrdemPagamento(String nameDb) throws IOException, Exception {
        String[] nameReport = new String[2];
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraCsem.jasper";
            nameReport[1] = path.getPastaCsemExportOrdemPagamento();
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraSunew.jasper";
            nameReport[1] = path.getPastaSunewExportOrdemPagamento();
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            nameReport[0] = "relatorio/view/FollowUpCompraSunewGeradores.jasper";
            nameReport[1] = path.getPastaSunewGeradoresExportOrdemPagamento();
        }

        return nameReport;
    }

    public String VerficaNameDBgeraLog(String user, String nameDb) throws IOException, Exception {
        String pastaLog = null;
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogCsem(user, nameDb); 
            pastaLog = path.getPastaCsemLog() + anoBd[1]+"\\"+user+"\\";
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogSunew(user, nameDb);
            pastaLog = path.getPastaSunewLog() + anoBd[1]+"\\"+user+"\\";
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogSunewGeradores(user, nameDb);
            pastaLog = path.getPastaSunewGeradoresLog() + anoBd[1]+"\\"+user+"\\";
        }

        return pastaLog;
    }
    
    public String VerficaNameDBgeraLogGeral(String nameDb) throws IOException, Exception {
        String pastaLog = null;
        //Format format = new Format();
        String[] anoBd = nameDb.split("_");
        if (nameDb.equals("csem_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogCsemGeral(nameDb); 
            pastaLog = path.getPastaCsemLog() + anoBd[1]+"\\";
        }
        if (nameDb.equals("sunew_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogSunewGeral(nameDb);
            pastaLog = path.getPastaSunewLog() + anoBd[1]+"\\";
        }
        if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
            CreateFolder pasta = new CreateFolder();
            pasta.createFolderLogSunewGeradoresGeral(nameDb);
            pastaLog = path.getPastaSunewGeradoresLog() + anoBd[1]+"\\";
        }

        return pastaLog;
    }
}
