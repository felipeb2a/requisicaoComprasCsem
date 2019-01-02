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
       String verificaIcone [];
       verificaIcone = nameDb.split("_");
        if (verificaIcone[0].equals("csem")) {
            url = this.getClass().getResource("/images/icon_system_csem.png");
        }
        if (verificaIcone[0].equals("sunew")) {
            url = this.getClass().getResource("/images/icon_system_sunew.png");
        }
        if (verificaIcone[0].equals("sunewgeradores")) {
            url = this.getClass().getResource("/images/icon_system_sunew_geradores.png");
        }        
        return url;
    }
    
    public void setIcone(URL url){
        this.url = url;
    }
    
}
