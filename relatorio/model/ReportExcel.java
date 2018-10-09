package model;

import dao.AcessDB;
import dao.RequisicoesDAO;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 *
 * @author felipe.ferreira
 */
public class ReportExcel extends AcessDB {

    String[] nameReport;
    VerificaParametro verifica = new VerificaParametro();
    RequisicoesDAO requisicaoDao = new RequisicoesDAO();
    String nivel;

    public void gerarFollowUpExcel(String user, Date dataInicial, Date dataFinal, String tipo, String nameDb) {
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
            String path = nameReport[1] + anoBd[1] + "\\" + name + "\\" + dataIReplace + "_a_" + dataFReplace + ".xls";

            //CONSULTA
            String sql = null;
            PreparedStatement stm = null;
            
            //DISABLE GROUP BY FULL GROUP
            sql = "SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))";
            stm = conexao.prepareStatement(sql);
            
            if (tipo.equals("Nacional")) {
                //sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where tr.TipoRequisicao = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";
                 //GROUP BY
                sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where tr.TipoRequisicao = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? group by r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao order by r.CodRequisicao";
                 stm = conexao.prepareStatement(sql);
                stm.setString(1, tipo);
                stm.setDate(2, dataInicialConvert);
                stm.setDate(3, dataFinalConvert);
            }
            if (tipo.equals("Internacional")) {
                //sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where tr.TipoRequisicao = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";
                //GROUP BY
                sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where tr.TipoRequisicao = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? group by r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao order by r.CodRequisicao";
                stm = conexao.prepareStatement(sql);
                stm.setString(1, tipo);
                stm.setDate(2, dataInicialConvert);
                stm.setDate(3, dataFinalConvert);
            }
            if (tipo.equals("Completo")) {
                //sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";
                 //GROUP BY
                sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where f.Escolha = 1 and r.DataSolicitacao between ? and ? group by r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao order by r.CodRequisicao";
                 stm = conexao.prepareStatement(sql);
                stm.setDate(1, dataInicialConvert);
                stm.setDate(2, dataFinalConvert);
            } 
            
            if (tipo.equals("Selecione")) {
                //sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao";
                 //GROUP BY
                sql = "select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao from Requisicoes r inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? group by r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, f.Logistica, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome, tr.TipoRequisicao order by r.CodRequisicao";
                 stm = conexao.prepareStatement(sql);
                stm.setString(1, user);
                stm.setDate(2, dataInicialConvert);
                stm.setDate(3, dataFinalConvert);
            }
            
            ResultSet resultado = stm.executeQuery();
            
            //EXCEL
            // local do arquivo
            String filename = path;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Follow-Up");
            // criando as linhas
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Número");
            rowhead.createCell(1).setCellValue("Solicitante");
            rowhead.createCell(2).setCellValue("Solicitação");
            rowhead.createCell(3).setCellValue("Requisição");
            rowhead.createCell(4).setCellValue("Aprovação");
            rowhead.createCell(5).setCellValue("Centro de Custo");
            rowhead.createCell(6).setCellValue("Descrição");
            rowhead.createCell(7).setCellValue("Fornecedor");
            rowhead.createCell(8).setCellValue("Etapa");
            rowhead.createCell(9).setCellValue("Tempo de Produção");
            rowhead.createCell(10).setCellValue("Logística");
            rowhead.createCell(11).setCellValue("Previsão");
            rowhead.createCell(12).setCellValue("Data do Recebimento");
            rowhead.createCell(13).setCellValue("Requisitante");
            rowhead.createCell(14).setCellValue("Tipo Requisição");
            
            int cont = 1;
            while (resultado.next()) {
                //VERIFICAR DATAS
            //FORMAT DATAS
            String dataSolicitacao;
            String dataCriacao;
            String dataAprovacao;
            String dataPrevisaoEntrega;
            String dataEntrega;

            try {
                dataAprovacao = convertData.formatDataReturnString(resultado.getDate("DataAprov"));

            } catch (NullPointerException e) {
                dataAprovacao = "-";
            }

            try {
                dataCriacao = convertData.formatDataReturnString(resultado.getDate("DataCriacao"));

            } catch (NullPointerException e) {
                dataCriacao = "-";
            }

            try {
                dataSolicitacao = convertData.formatDataReturnString(resultado.getDate("DataSolicitacao"));

            } catch (NullPointerException e) {
                dataSolicitacao = "-";
            }

            try {
                dataPrevisaoEntrega = convertData.formatDataReturnString(resultado.getDate("DataPrevisaoEntrega"));

            } catch (NullPointerException e) {
                dataPrevisaoEntrega = "-";
            }

            try {
                dataEntrega = convertData.formatDataReturnString(resultado.getDate("DataEntrega"));

            } catch (NullPointerException e) {
                dataEntrega = "-";
            }
                // definindo seus valores
                // por exemplo protocolo.getProtocolo();
                HSSFRow row = sheet.createRow((short) cont);
                row.createCell(0).setCellValue(resultado.getString("CodRequisicao"));
                row.createCell(1).setCellValue(resultado.getString("NomeSolicitante"));

                row.createCell(2).setCellValue(dataSolicitacao);
                row.createCell(3).setCellValue(dataCriacao);
                row.createCell(4).setCellValue(dataAprovacao);
                row.createCell(5).setCellValue(resultado.getString("NomeProjeto"));
                row.createCell(6).setCellValue(resultado.getString("NomeItem"));
                row.createCell(7).setCellValue(resultado.getString("NomeFornecedor"));
                row.createCell(8).setCellValue(resultado.getString("EtapaRequisicao"));
                row.createCell(9).setCellValue(resultado.getString("TempoProducao"));
                row.createCell(10).setCellValue(resultado.getString("Logistica"));
                row.createCell(11).setCellValue(dataPrevisaoEntrega);
                row.createCell(12).setCellValue(dataEntrega);
                row.createCell(13).setCellValue(resultado.getString("Nome"));
                row.createCell(14).setCellValue(resultado.getString("TipoRequisicao"));

                FileOutputStream fileOut = new FileOutputStream(filename);
                workbook.write(fileOut);
                fileOut.close();
                cont++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
