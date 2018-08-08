
package model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author felipe.ferreira
 */
public class JavaMail {
     public void sendMail(String[] defineTo, String defineSubject, String defineMessage) {
       
        //DEFINE O EMAIL FROM E CRIA UM AUTENTICADOR
        SimpleAuth auth = new  SimpleAuth();
        
        //DECLARA VARIAVEIS DO SERVIDOR DE EMAIL
        String mailSMTPServer = auth.getServer();
        String from = auth.getUsername();
        String mailSMTPServerPort = auth.getPorta();
        String subject = defineSubject;
        String message = defineMessage+"\n\n"+auth.getAssinatura();
        String[] to = defineTo;
        
        Properties props = new Properties();
        
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(false); //Habilita o LOG das aÃƒÂ§ÃƒÂµes executadas durante o envio do email
        //Objeto que contÃ©m a mensagem
        Message mensagem = new MimeMessage(session);
        try {
            for (int i = 0; i < to.length; i++) {
                //Setando o destinatário
                mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                //Setando a origem do email
                mensagem.setFrom(new InternetAddress(from));
                //Setando o assunto
                mensagem.setSubject(subject);
                //Setando o conteúdo do corpo do email
                mensagem.setContent(message, "text/plain");

                //Objeto encarregado de enviar os dados para o email
                Transport tr;
                try {
                    tr = session.getTransport("smtp"); //define smtp para transporte
                    tr.connect(mailSMTPServer, auth.getUsername(), auth.getPassword());
                    mensagem.saveChanges();
                    //envio da mensagem
                    tr.sendMessage(mensagem, mensagem.getAllRecipients());
                    tr.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    //System.out.println(">> Erro: Envio Mensagem");
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            //System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }
    }
}
