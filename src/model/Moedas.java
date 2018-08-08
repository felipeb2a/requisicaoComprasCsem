package model;

/**
 *
 * @author felipe.ferreira
 */
public class Moedas {

    private int id;
    private String moeda;
    private String moedaAbreviada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getMoedaAbreviada() {
        return moedaAbreviada;
    }

    public void setMoedaAbreviada(String moedaAbreviada) {
        this.moedaAbreviada = moedaAbreviada;
    }
}
