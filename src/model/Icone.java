/*@author FELIPE*/
package model;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Icone {
    
    private URL url;
    
    //ALTERAR ICONE JAVA
    public URL getIcone(String nameDb){
         
       Format format = new Format();
        if (nameDb.equals("csem_" + format.dataAnoAtual())) {
            url = this.getClass().getResource("/images/icon_system_csem.png");
        }
        if (nameDb.equals("sunew_" + format.dataAnoAtual())) {
            url = this.getClass().getResource("/images/icon_system_sunew.png");
        }
        if (nameDb.equals("sunewgeradores_" + format.dataAnoAtual())) {
            url = this.getClass().getResource("/images/icon_system_sunew_geradores.png");
        }        
        return url;
    }
    
    public void setIcone(URL url){
        this.url = url;
    }
    
}
