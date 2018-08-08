package model;

import dao.RequisicoesDAO;
import dao.SolicitacoesDAO;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class SendMail {

    public String sendMailSolicitacaoRecusada(int id, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
        // ENVIA EMAIL
        JavaMail mail = new JavaMail();
        // BUSCA COMPRADORA E SOLICITANTE
        SolicitacoesDAO solicitacaoDao = new SolicitacoesDAO();
        Solicitacoes solicitacao = new Solicitacoes();
        solicitacao = solicitacaoDao.localizarSolicitacoesEmail(id, nameDb);

        //SETA NOME DB E VERIFICA 
        Format format = new Format();
        int ano = format.dataAnoAtual();
        String inputNameDbCsem = "csem_" + ano;
        String inputNameDbSunew = "sunew_" + ano;
        String inputNameDbSunewGeradores = "sunewgeradores_" + ano;
        //ATRIBUIR VALORES AO EMAIL
        String subject = null;
        String message;

        if (nameDb.equals(inputNameDbCsem)) {
            subject = "[RC " + id + " CSEM] - SOLICITACAO RECUSADA";
        }

        if (nameDb.equals(inputNameDbSunew)) {
            subject = "[RC " + id + " SUNEW] - SOLICITACAO RECUSADA";
        }

        if (nameDb.equals(inputNameDbSunewGeradores)) {
            subject = "[RC " + id + " SUNEW GERADORES] - SOLICITACAO RECUSADA";
        }

        String data = format.formatDataReturnString(solicitacao.getDataSolicitacao());
        message = "Solicitacao recusada!\n"
                + "Compradora: " + solicitacao.getUsuario().getLogin() + "\n"
                + "Motivo: " + solicitacao.getMotivo() + "\n"
                + "Data da Solicitacao: " + data;

        //DEFINIR EMAIL P/ ENVIO
        String emailRequisitante = solicitacao.getUsuario().getEmail();
        String emailSolicitante = solicitacao.getEmailSolicitante();
        //String[] to = {emailRequisitante, emailSolicitante};

        // VERIFICA MAIS DE UM EMAIL
        String pontoVirgula = ";";
        if (emailSolicitante.contains(pontoVirgula)) {
            // SEPARAR E-MAIL
            String[] emails = emailSolicitante.split(";");
            // ENVIAR EMAILs SOLICITANTE
            for (int i = 0; i < emails.length; i++) {
                String[] to = {emails[i]};
                // ENVIA EMAIL
                mail.sendMail(to, subject, message);
            }
            // ENVIAR EMAIL REQUISITANTE
            String[] to = {emailRequisitante};
            mail.sendMail(to, subject, message);
        } else {
            String[] to = {emailRequisitante, emailSolicitante};
            // ENVIA EMAIL
            mail.sendMail(to, subject, message);
        }

        return null;
    }

    public String sendMailRequisicaoTecnico(Requisicoes requisicao, String nameDb) throws SQLException, ClassNotFoundException, ParseException {
        // ENVIA EMAIL
        JavaMail mail = new JavaMail();
        // BUSCA COMPRADORA E SOLICITANTE
        RequisicoesDAO requisicaoDao = new RequisicoesDAO();
        Requisicoes requisicaoEmail;
        requisicaoEmail = requisicaoDao.ObterEmail(requisicao, nameDb);

        //SETA NOME DB E VERIFICA 
        Format format = new Format();
        int ano = format.dataAnoAtual();
        String inputNameDbCsem = "csem_" + ano;
        String inputNameDbSunew = "sunew_" + ano;
        String inputNameDbSunewGeradores = "sunewgeradores_" + ano;
        //ATRIBUIR VALORES AO EMAIL
        String subject = null;
        String message;

        if (nameDb.equals(inputNameDbCsem)) {
            subject = "[RC " + requisicao.getId() + " CSEM] - REQUISICAO AGUARDANDO APROVACAO";
        }

        if (nameDb.equals(inputNameDbSunew)) {
            subject = "[RC " + requisicao.getId() + " SUNEW] - REQUISICAO AGUARDANDO APROVACAO";
        }

        if (nameDb.equals(inputNameDbSunewGeradores)) {
            subject = "[RC " + requisicao.getId() + " SUNEW GERADORES] - REQUISICAO AGUARDANDO APROVACAO";
        }

        //DATA
        Date data = requisicao.getDataCriacao();
        String dataF = format.formatDataReturnString(data);
        message = "Requisicao aguardando aprovacao!\n"
                + "Requisicao: " + requisicao.getId() + "\n"
                + "Compradora: " + requisicao.getUsuario().getLogin() + "\n"
                + "Data da Requisicao: " + dataF;

        //DEFINIR EMAIL P/ ENVIO
        String emailRequisitante = requisicaoEmail.getUsuario().getEmail();
        String emailAprovador = requisicaoEmail.getUsuario().getEmailAprovadorTecnico();
        String[] to = {emailRequisitante, emailAprovador};

        //ENVIA EMAIL
        mail.sendMail(to, subject, message);

        return null;
    }
}
