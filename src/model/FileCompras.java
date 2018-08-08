package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.list.TelaListaRequisicao;

/**
 *
 * @author felipe.ferreira
 */
public class FileCompras {
    
    //DEFINE PASTAS E ARQUIVOS
    FilesPath path = new FilesPath();
    
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
        } catch (IOException ex) {
            Logger.getLogger(TelaListaRequisicao.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex) {
            Logger.getLogger(TelaListaRequisicao.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex) {
            Logger.getLogger(TelaListaRequisicao.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        } catch (IOException ex) {
            Logger.getLogger(TelaListaRequisicao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
