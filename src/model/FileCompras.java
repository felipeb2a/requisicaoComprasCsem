package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipe.ferreira
 */
public class FileCompras {

    //DEFINE PASTAS E ARQUIVOS
    FilesPath path = new FilesPath();
    private Logger logger = null;

    //LOGGER
    public Logger Definirlogger(String nameDb) {
        Log log = new Log();
        try {
            logger = log.pathLog(FileCompras.class.getName(), nameDb);
        } catch (SecurityException ex1) {
            Logger.getLogger(FileCompras.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (Exception ex1) {
            Logger.getLogger(FileCompras.class.getName()).log(Level.SEVERE, null, ex1);
        }

        return logger;
    }

    public void OC(String nameDb, String nivel) {

        try {
            //Format format = new Format();
            String[] anoBd = nameDb.split("_");
            if (nameDb.equals("csem_" + anoBd[1])) {
                if (nivel.equals("RH")) {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcCsemRh() + anoBd[1]));
                } else {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcCsem() + anoBd[1]));
                }
            }
            if (nameDb.equals("sunew_" + anoBd[1])) {
                if (nivel.equals("RH")) {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcSunewRh() + anoBd[1]));
                } else {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcSunew() + anoBd[1]));
                }
            }
            if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
                if (nivel.equals("RH")) {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcSunewGeradoresRh() + anoBd[1]));
                } else {
                    java.awt.Desktop.getDesktop().open(new File(path.getPastaRcSunewGeradores() + anoBd[1]));
                }
            }
        } catch (Exception ex) {
            logger = Definirlogger(nameDb);
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void ATIVOS(String nameDb) {
        try {
            //Format format = new Format();
            String[] anoBd = nameDb.split("_");
            if (nameDb.equals("csem_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getAtivosCsem()));
            }
            if (nameDb.equals("sunew_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getAtivosSunew()));
            }
            if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getAtivosSunewGeradores()));
            }
        } catch (Exception ex) {
            logger = Definirlogger(nameDb);
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void DADOS(String nameDb) {
        try {
            //Format format = new Format();
            String[] anoBd = nameDb.split("_");
            if (nameDb.equals("csem_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getDadosCsem()));
            }
            if (nameDb.equals("sunew_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getDadosSunew()));
            }
            if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getDadosSunewGeradores()));
            }
        } catch (Exception ex) {
            logger = Definirlogger(nameDb);
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void FOLLOWUP(String nameDb, String user) {
        try {
            //Format format = new Format();
            String[] anoBd = nameDb.split("_");
            if (nameDb.equals("csem_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaCsemFollowUp() + anoBd[1] + "\\" + user));
            }
            if (nameDb.equals("sunew_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaSunewFollowUp() + anoBd[1] + "\\" + user));
            }
            if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaSunewGeradoresFollowUp() + anoBd[1] + "\\" + user));
            }
        } catch (Exception ex) {
            logger = Definirlogger(nameDb);
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void ORDEMPAGAMENTO(String nameDb, String user) {
        try {
            //Format format = new Format();
            String[] anoBd = nameDb.split("_");
            if (nameDb.equals("csem_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaCsemExportOrdemPagamento() + anoBd[1] + "\\" + user));
            }
            if (nameDb.equals("sunew_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaSunewExportOrdemPagamento() + anoBd[1] + "\\" + user));
            }
            if (nameDb.equals("sunewgeradores_" + anoBd[1])) {
                java.awt.Desktop.getDesktop().open(new File(path.getPastaSunewGeradoresExportOrdemPagamento() + anoBd[1] + "\\" + user));
            }
        } catch (Exception ex) {
            logger = Definirlogger(nameDb);
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
