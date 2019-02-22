package model;

import dao.AcessDB;
import dao.RequisicoesDAO;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 *
 * @author felipe.ferreira
 */
public class ReportOrdemPagamentoExcel extends AcessDB {

    String[] nameReport;
    VerificaParametro verifica = new VerificaParametro();
    RequisicoesDAO requisicaoDao = new RequisicoesDAO();
    String nivel;

    public void exportOrdemPagamaentoExcel(String user, String projeto, String nameDb) {
        try {
            Format convertData = new Format();
            String[] anoBd = nameDb.split("_");
            //NOME RELATORIO
            String name = user;

            //CONVERT DATA MYSQL
            java.sql.Date dataInicialConvert;
            java.sql.Date dataFinalConvert;

            Connection conexao = conectar(nameDb);

            //DEFINE RELATORIO DE CADA EMPRESA RC
            nameReport = verifica.VerficaNameDBgeraRelatorioExportOrdemPagamento(nameDb);
            String path = nameReport[1] + anoBd[1] + "\\" + name + "\\" + projeto + ".xls";

            //CONSULTA
            String sql = null;
            PreparedStatement stm = null;

            //DISABLE GROUP BY FULL GROUP
            sql = "SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))";
            stm = conexao.prepareStatement(sql);
            stm.executeQuery();

            if (projeto.equals("Completo")) {
                //GROUP BY
                sql = "SELECT r.CodRequisicao as Codigo_OP, p.NomeProjeto as Projeto, f.NomeFornecedor "
                        + " as Fornecedor, o.DataOP, (SELECT max(o.DataVencimento)) as DataVencimento, "
                        + " (SELECT max(o.Parcela)) as Parcelas, o.ValorPagar as ValorParcela, "
                        + " sum(o.ValorPagar) as Total_Pago, f.ValorFinal as Total, (select f.ValorFinal) - (select sum(o.ValorPagar)) as Falta_Pagar "
                        + " FROM OrdemPagto o "
                        + " inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao "
                        + " inner join Projetos p on p.CodProjeto = r.CodProjeto "
                        + " inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao "
                        + " WHERE f.Escolha = 1 "
                        + " group by o.CodRequisicao "
                        + " order by p.NomeProjeto";
                stm = conexao.prepareStatement(sql);
            } else {
                //GROUP BY
                sql = "SELECT r.CodRequisicao as Codigo_OP, p.NomeProjeto as Projeto, f.NomeFornecedor "
                        + " as Fornecedor, o.DataOP, (SELECT max(o.DataVencimento)) as DataVencimento, "
                        + " (SELECT max(o.Parcela)) as Parcelas, o.ValorPagar as ValorParcela, "
                        + " sum(o.ValorPagar) as Total_Pago, f.ValorFinal as Total, (select f.ValorFinal) - (select sum(o.ValorPagar)) as Falta_Pagar "
                        + " FROM OrdemPagto o "
                        + " inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao "
                        + " inner join Projetos p on p.CodProjeto = r.CodProjeto "
                        + " inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao "
                        + " WHERE f.Escolha = 1 and p.NomeProjeto = ? "
                        + " group by o.CodRequisicao "
                        + " order by o.CodRequisicao";
                stm = conexao.prepareStatement(sql);
                stm.setString(1, projeto);
            }

            ResultSet resultado = stm.executeQuery();

            //EXCEL
            // local do arquivo
            String filename = path;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Export-OrdemPagamento");
            // criando as linhas
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Código OP");
            rowhead.createCell(1).setCellValue("Projeto");
            rowhead.createCell(2).setCellValue("Fornecedor");
            rowhead.createCell(3).setCellValue("Data OP");
            rowhead.createCell(4).setCellValue("Data Vencimento");
            rowhead.createCell(5).setCellValue("Parcelas");
            rowhead.createCell(6).setCellValue("Valor Parcela");
            rowhead.createCell(7).setCellValue("Total Pago");
            rowhead.createCell(8).setCellValue("Total");
            rowhead.createCell(9).setCellValue("Falta Pagar");

            int cont = 1;
            while (resultado.next()) {
                //VERIFICAR DATAS
                //FORMAT DATAS
                String dataOP;
                String dataVencimento;
                double valorParcela = resultado.getDouble("ValorParcela");
                double totalPago = resultado.getDouble("Total_Pago");
                double total = resultado.getDouble("Total");
                double faltaPagar = resultado.getDouble("Falta_Pagar");
                               
                try {
                    dataVencimento = convertData.formatDataReturnString(resultado.getDate("DataVencimento"));

                } catch (NullPointerException e) {
                    dataVencimento = "-";
                }

                try {
                    dataOP = convertData.formatDataReturnString(resultado.getDate("DataOP"));

                } catch (NullPointerException e) {
                    dataOP = "-";
                }

                // definindo seus valores
                // por exemplo protocolo.getProtocolo();
                HSSFRow row = sheet.createRow((short) cont);
                row.createCell(0).setCellValue(resultado.getString("Codigo_OP"));
                row.createCell(1).setCellValue(resultado.getString("Projeto"));
                row.createCell(2).setCellValue(resultado.getString("Fornecedor"));
                row.createCell(3).setCellValue(dataOP);
                row.createCell(4).setCellValue(dataVencimento);
                row.createCell(5).setCellValue(resultado.getString("Parcelas"));
                
                row.createCell(6).setCellValue(new DecimalFormat("###,###.###").format(valorParcela));
                row.createCell(7).setCellValue(new DecimalFormat("###,###.###").format(totalPago));
                row.createCell(8).setCellValue(new DecimalFormat("###,###.###").format(total));
                row.createCell(9).setCellValue(new DecimalFormat("###,###.###").format(faltaPagar));
                
//                row.createCell(6).setCellValue(resultado.getString("ValorParcela"));
//                row.createCell(7).setCellValue(resultado.getString("Total_Pago"));
//                row.createCell(8).setCellValue(resultado.getString("Total"));
//                row.createCell(9).setCellValue(resultado.getString("Falta_Pagar"));
//                new DecimalFormat("###,###.###").format(valorParcela)

                FileOutputStream fileOut = new FileOutputStream(filename);
                workbook.write(fileOut);
                fileOut.close();
                cont++;
            }
            resultado.close();
            stm.close();
            conexao.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
