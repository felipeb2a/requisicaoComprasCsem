/*@author FELIPE*/

package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

public class ReportImprimir {
    
    
    //JASPERPRINT DIRETO PARA IMPRESSORA
    public void imprimir(final JasperPrint jasperprint)  
    {  
        Thread thread = new Thread(  
            new Runnable(){  
                public void run(){  
                    try{   
                        JasperPrint jasperPrint = jasperprint;
                        
                        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();  
                        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();  
                        printRequestAttributeSet.add(new Copies(1));  
  
                        JRPrintServiceExporter exporter = new JRPrintServiceExporter();  
  
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);    
                        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);  
                        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);  
                        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);  
                        //exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, showDialog);  
                        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, PrintServiceLookup.lookupDefaultPrintService());  
  
                        exporter.exportReport();                                       
//                                    JasperPrintManager.printReport(jasperPrint, true);  
                    }  
                    catch (Exception ex){  
                        ex.printStackTrace();  
                    }  
                    
                }  
            }  
        );  
        thread.start();  
    }
    
    public void imprimirArquivo() throws Exception {
       try{  
        FileInputStream prin = new FileInputStream("relatorio/view/report name.txt");   
        DocFlavor.INPUT_STREAM docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
        SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);   
        PrintService impressora = PrintServiceLookup.lookupDefaultPrintService(); // pega a //impressora padrao  
        PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();  
        printerAttributes.add(new JobName("Impressao", null));  
        printerAttributes.add(OrientationRequested.PORTRAIT);   
        printerAttributes.add(MediaSizeName.ISO_A4); // informa o tipo de folha  
        DocPrintJob printJob = impressora.createPrintJob();  

        try{   
        printJob.print(documentoTexto, (PrintRequestAttributeSet)printerAttributes); //tenta imprimir  
        }   
        catch(PrintException e){  
        JOptionPane.showMessageDialog(null, "Não foi possível realizar a impressão !!", "Erro", JOptionPane.ERROR_MESSAGE); // mostra //mensagem de erro  
        }   
        prin.close();  
        }  
        catch(Exception e){  
        }

    }    
        
}
