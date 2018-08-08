package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author felipe.ferreira
 */
public class Format {

    Moeda moeda;

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

//---------------------------------DATA
    //CONVERT DATE SQL
    public static java.sql.Date convertDataSql(Date date) throws ParseException {
        return new java.sql.Date(date.getTime());
    }

    public Date formatDataHoje(Date data) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);

        return data;
    }

    public String dataHojeString() throws ParseException {
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);

        return dataFormat;
    }

    public String dataTimeHojeString() throws ParseException {
        Calendar c = Calendar.getInstance();
        Date dataHoje = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        String dataFormat = sdf.format(dataHoje);
        dataHoje = sdf.parse(dataFormat);

        return dataFormat;
    }

    public Date formatData(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(data);
        data = sdf.parse(dataFormat);

        return data;
    }

    public Date formatDataString(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateFormat = sdf.parse(data);

        return dateFormat;
    }

    public String formatDataReturnString(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = sdf.format(data);
        //data = sdf.parse(dataFormat);

        return dataFormat;
    }

    public Date dataIncrementMesAno(Date data, int i) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + i - 1);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR));
        String a = (new SimpleDateFormat(" dd/MM/yyyy").format(c.getTime()));
        data = sdf.parse(a);
        return data;
    }

    public Integer dataAnoAtual() {
        Calendar c = Calendar.getInstance();
        int i = c.get(c.YEAR);
        return i;
    }

    public Integer dataSubtracao(Date a, Date b) {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        //Date dataA = sdf.parse(a);
        //Date dataB = sdf.parse(b);        
        int dias;

        dias = (int) ((a.getTime() - b.getTime()) / 86400000L);
        return dias;
    }
    
    public Date dataSomaDias(Date data, int dias) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DAY_OF_MONTH, dias);        
        data = c.getTime();
        return data;
    }

//--------------------------------- MOEDA
    public Double formatDouble(String valor, int i) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        Double valorF = Double.parseDouble(valor);
        return valorF;
    }

    public Double formatDivideDoubleValorPagar(Double valor, int i) {
        String valorF;
        valorF = String.format(Locale.US, "%.2f", valor / i);
        valor = Double.parseDouble(valorF);
        return valor;
    }

    public String formatDoubleValorPagar(Double valor) {
        String valorF;
        valorF = String.format(Locale.US, "%.2f", valor);
        return valorF;
    }

    public Double formatDoubleValorPagarVirgulaParcela(String valor, int i) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        Double valorF = Double.parseDouble(valor);
        valor = String.format(Locale.US, "%.2f", valorF / i);
        valorF = Double.parseDouble(valor);
        return valorF;
    }

    public Double formatDoubleValorPagarVirgula(String valor) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        Double valorF = Double.parseDouble(valor);
        valor = String.format(Locale.US, "%.2f", valorF);
        valorF = Double.parseDouble(valor);
        return valorF;
    }

    public Double formatDoubleValorPagarPonto(String valor) {
        //valor = valor.replace(".", "");
        valor = valor.replace(".", ",");
        Double valorF = Double.parseDouble(valor);
        valor = String.format(Locale.US, "%.2f", valorF);
        valorF = Double.parseDouble(valor);
        return valorF;
    }

    public BigDecimal formatBigDecimal(String valor) throws ParseException {
        // Create a DecimalFormat that fits your requirements
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        // parse the string
        BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(valor);

        return bigDecimal;
    }
}
