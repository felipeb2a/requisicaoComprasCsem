package model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author felipe.ferreira
 */
class SimpleAuth extends Authenticator {

   private static String username = "compras.csem.sunew@gmail.com";
    private static String password = "Compr@s1";
    private static String server = "smtp.gmail.com";
    private static String porta = "587";
    private static String assinatura = "Compras\n" + "Requisicao de Compras\n" + "compras.csem.sunew@csembrasil.com.br\n"+
            "T +55 31 3326 1607 (Nacional)\n"+"T +55 31 3326 1610 (Internacional)\n"+
            "http://requisicao.csembrasil.com.br/\n";

    public static String getAssinatura() {
        return assinatura;
    }
    
    
    
    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getServer() {
        return server;
    }

    public static String getPorta() {
        return porta;
    }

    SimpleAuth() {
        String username = this.username;
        String password = this.password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
