/*@author FELIPE*/
package model;

import dao.AcessDB;
import dao.RequisicoesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import view.TelaInfomacoesFinanceiras;

public class Report extends AcessDB {

    Format anoAtual = new Format();
    //int ano = anoAtual.dataAnoAtual();
    //DEFINE RELATORIO DE CADA EMPRESA
    String[] nameReport;
    VerificaParametro verifica = new VerificaParametro();
    RequisicoesDAO requisicaoDao = new RequisicoesDAO();
    String nivel;

    public void geraRelatorioRC(Requisicoes requisicoes, String nameDb) throws JRException, Exception {
        String[] anoBd = nameDb.split("_");
        //NOME RELATORIO
        String name = Integer.toString(requisicoes.getId());

        Connection conexao = conectar(nameDb);

        //DEFINE RELATORIO DE CADA EMPRESA RC
        nivel = requisicaoDao.localizarNivel(requisicoes, nameDb);
        nameReport = verifica.VerficaNameDBgeraRelatorioRC(nivel, name, nameDb);
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.DataAprovTecnico, r.Justificativa, r.Aprovador, r.AprovadorTecnico, p.NomeProjeto, tr.TipoRequisicao, u.Nome, s.NomeSolicitante from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante where r.CodRequisicao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicoes.getId());

        ResultSet resultado = stm.executeQuery();

        //implementacao da interface JRDataSource para DataSource ResultSet    
        JRResultSetDataSource jrRS = new JRResultSetDataSource(resultado);

        //HashMap de parametros utilizados no relatorio. Sempre instanciados    
        Map parameters = new HashMap();

        //parametro list
        parameters.put("REPORT_CONNECTION", conexao);

        //String printFileName = JasperFillManager.fillReportToFile("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        //JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(nameReport[0], parameters, jrRS);
        //EXPORT TO PDF
        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporterPdf.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\SUNEW\\" + ano + "\\" + name + "\\RC - " + name + ".pdf");
        exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + "\\RC - " + name + ".pdf");
        exporterPdf.exportReport();

        //EXPORT TO EXCEL
        //JRXlsExporter exporterXls = new JRXlsExporter();
        //exporterXls.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\"+ano+"\\"+name+"\\RC - " + name + ".xls");
        //exporterXls.exportReport();
        conexao.close();
    }

    public void geraRelatorioRCCancelada(Requisicoes requisicoes, String nameDb) throws JRException, Exception {
        String[] anoBd = nameDb.split("_");
        //NOME RELATORIO
        String name = Integer.toString(requisicoes.getId());

        Connection conexao = conectar(nameDb);

        //DEFINE RELATORIO DE CADA EMPRESA RC
        nivel = requisicaoDao.localizarNivel(requisicoes, nameDb);
        nameReport = verifica.VerficaNameDBgeraRelatorioRCCancelada(nivel, name, nameDb);
        
        String sql = "select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.DataAprovTecnico, r.Justificativa, r.Aprovador, r.AprovadorTecnico, p.NomeProjeto, tr.TipoRequisicao, u.Nome, s.NomeSolicitante from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante where r.CodRequisicao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicoes.getId());

        ResultSet resultado = stm.executeQuery();

        //implementacao da interface JRDataSource para DataSource ResultSet    
        JRResultSetDataSource jrRS = new JRResultSetDataSource(resultado);

        //HashMap de parametros utilizados no relatorio. Sempre instanciados    
        Map parameters = new HashMap();

        //parametro list
        parameters.put("REPORT_CONNECTION", conexao);

        //String printFileName = JasperFillManager.fillReportToFile("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        //JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(nameReport[0], parameters, jrRS);
        //EXPORT TO PDF
        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporterPdf.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\SUNEW\\" + ano + "\\" + name + "\\RC - " + name + ".pdf");
        exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + "\\RC - " + name + ".pdf");
        exporterPdf.exportReport();

        //EXPORT TO EXCEL
        //JRXlsExporter exporterXls = new JRXlsExporter();
        //exporterXls.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\"+ano+"\\"+name+"\\RC - " + name + ".xls");
        //exporterXls.exportReport();
        conexao.close();
    }

    public void geraRelatorioOC(Requisicoes requisicoes, String nameDb) throws JRException, Exception {
        String[] anoBd = nameDb.split("_");
        //NOME RELATORIO
        String name = Integer.toString(requisicoes.getId());

        Connection conexao = conectar(nameDb);

        //DEFINE RELATORIO DE CADA EMPRESA RC
        nivel = requisicaoDao.localizarNivel(requisicoes, nameDb);
        nameReport = verifica.VerficaNameDBgeraRelatorioOC(nivel, name, nameDb);
        
        String sql = "select r.CodRequisicao, r.DataSolicitacao, u.Nome, u.EmailFunc from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario where r.CodRequisicao = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, requisicoes.getId());

        ResultSet resultado = stm.executeQuery();

        //implementacao da interface JRDataSource para DataSource ResultSet    
        JRResultSetDataSource jrRS = new JRResultSetDataSource(resultado);

        //HashMap de parametros utilizados no relatorio. Sempre instanciados    
        Map parameters = new HashMap();

        //parametro list
        parameters.put("REPORT_CONNECTION", conexao);

        //String printFileName = JasperFillManager.fillReportToFile("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        //JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(nameReport[0], parameters, jrRS);
        //EXPORT TO PDF
        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporterPdf.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\SUNEW\\" + ano + "\\" + name + "\\RC - " + name + ".pdf");
        exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + "\\OC - " + name + ".pdf");
        exporterPdf.exportReport();

        //EXPORT TO EXCEL
        //JRXlsExporter exporterXls = new JRXlsExporter();
        //exporterXls.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
        //exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\"+ano+"\\"+name+"\\RC - " + name + ".xls");
        //exporterXls.exportReport();
        conexao.close();
    }

    public void geraRelatorioOP(OrdemPagamento ordemPagamento, String nameDb) throws JRException, Exception {
        try {
            String[] anoBd = nameDb.split("_");
            //NOME RELATORIO
            String name = Integer.toString(ordemPagamento.getRequisicoes().getId());
            String numOp = Integer.toString(ordemPagamento.getParcela());
            Connection conexao = conectar(nameDb);
            //System.out.println("Parcela "+ordemPagamento.getParcela() +" ID O "+ ordemPagamento.getId()  +" ID R "+  ordemPagamento.getRequisicoes().getId());
            //DEFINE RELATORIO DE CADA EMPRESA RC
            Requisicoes requisicoes = new Requisicoes();
            requisicoes.setId(ordemPagamento.getRequisicoes().getId());
            nivel = requisicaoDao.localizarNivel(requisicoes, nameDb);
            //nameReport = verifica.VerficaNameDBgeraRelatorioOP(nivel, nameDb);
            nameReport = verifica.VerficaNameDBgeraRelatorioOP(nivel, name, nameDb);
            
            String sql = "select op.CodRequisicao, op.Parcela, op.DataOP, op.DataVencimento, op.NCM, op.PrevisaoEmbarque, f.NomeFornecedor, p.NomeProjeto, d.Destinacao, t.TipoCobranca, f.CNPJ, f.CPF, f.Banco, f.Agencia, f.Conta, op.ValorPagar, m.Abrev, op.Comentarios from OrdemPagto op  inner join Fornecedores f on f.CodRequisicao = op.CodRequisicao  inner join Requisicoes r on r.CodRequisicao = op.CodRequisicao  inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Destinacao d on d.CodDest = r.CodDest inner join TipoCobranca t on t.CodTipoCobranca = op.CodTipoCobranca inner join Moedas m on m.CodMoeda = r.CodMoeda where op.CodOrdemPagamento = ? and f.Escolha = 1";

            PreparedStatement stm = conexao.prepareStatement(sql);

            stm.setInt(1, ordemPagamento.getId());
            
            ResultSet resultado = stm.executeQuery();
            //implementacao da interface JRDataSource para DataSource ResultSet    
            JRResultSetDataSource jrRS = new JRResultSetDataSource(resultado);

            //HashMap de parametros utilizados no relatorio. Sempre instanciados    
            Map parameters = new HashMap();

            //parametro list
            parameters.put("REPORT_CONNECTION", conexao);

            //String printFileName = JasperFillManager.fillReportToFile("relatorio/view/RequisicaoCompra.jasper", parameters, jrRS);
            //NOME RELATORIO JASPER
            //JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio/view/OrdemPagamento.jasper", parameters, jrRS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(nameReport[0], parameters, jrRS);

            //EXPORT TO PDF
            JRPdfExporter exporterPdf = new JRPdfExporter();
            exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporterPdf.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
            //exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\SUNEW\\" + ano + "\\" + name + "\\OP - " + name + "." + numOp + ".pdf");
            exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + "\\OP - " + name + "." + numOp + ".pdf");
            exporterPdf.exportReport();

            //EXPORT TO EXCEL
            //JRXlsExporter exporterXls = new JRXlsExporter();
            //exporterXls.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
            //exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\felipe.ferreira\\Downloads\\compras\\ORDENS DE COMPRAS\\"+ano+"\\"+name+"\\RC - " + name + ".xls");
            //exporterXls.exportReport();
            conexao.close();
        } catch (Exception ex) {
            //LOG
            LogArquivoTexto log = new LogArquivoTexto();
            String classe = TelaInfomacoesFinanceiras.class.getName();
            String texto = classe + "\n" + "ERRO: " + ex;
            try {
                log.escreverGeral(texto, nameDb);
            } catch (Exception ex1) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void geraRelatorioFollowUp(String user, Date dataInicial, Date dataFinal, String nameDb) throws JRException, Exception {
        try {
            Format convertData = new Format();
            String[] anoBd = nameDb.split("_");
            //NOME RELATORIO
            String name = user;
            String dataI = convertData.formatDataReturnString(dataInicial);
            String dataF = convertData.formatDataReturnString(dataFinal);
            String dataIReplace = dataI.replace("/", "-");
            String dataFReplace = dataF.replace("/", "-");

            //CONVERT DATA MYSQL
            java.sql.Date dataInicialConvert;
            java.sql.Date dataFinalConvert;

            dataInicialConvert = convertData.convertDataSql(dataInicial);
            dataFinalConvert = convertData.convertDataSql(dataFinal);
            Connection conexao = conectar(nameDb);

            //DEFINE RELATORIO DE CADA EMPRESA RC
            nameReport = verifica.VerficaNameDBgeraRelatorioFollowUp(nameDb);
            //nameReport = verifica.VerficaNameDBgeraRelatorioFollowUp(name, nameDb);
            
            //String sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? group by r.CodRequisicao order by r.CodRequisicao";
            String sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";
            PreparedStatement stm = conexao.prepareStatement(sql);

            stm.setString(1, user);
            //stm.setString(2, dataIReplace + " 00:00:00");
            //stm.setString(3, dataFReplace + " 00:00:00");
            stm.setDate(2, dataInicialConvert);
            stm.setDate(3, dataFinalConvert);
            ResultSet resultado = stm.executeQuery();

            //implementacao da interface JRDataSource para DataSource ResultSet    
            JRResultSetDataSource jrRS = new JRResultSetDataSource(resultado);

            //HashMap de parametros utilizados no relatorio. Sempre instanciados    
            Map parameters = new HashMap();

            //parametro list
            parameters.put("REPORT_CONNECTION", conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(nameReport[0], parameters, jrRS);
            //EXPORT TO PDF
            //JRPdfExporter exporterPdf = new JRPdfExporter();
            //exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            //exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + ano + "\\" + name + "\\" + dataIReplace + "_a_" + dataFReplace + ".pdf");
            //exporterPdf.exportReport();

            JRXlsxExporter exporterXls = new JRXlsxExporter();
            exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nameReport[1] + anoBd[1] + "\\" + name + "\\" + dataIReplace + "_a_" + dataFReplace + ".xlsx");
            exporterXls.exportReport();
            conexao.close();
        } catch (Exception ex) {
            //LOG
            LogArquivoTexto log = new LogArquivoTexto();
            String classe = Report.class.getName();
            String texto = classe + "\n" + "ERRO: " + ex;
            try {
                log.escreverGeral(texto, nameDb);
                //JOptionPane.showMessageDialog(this, "- Favor preencher campo Data da Inicial");
            } catch (Exception ex1) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
