package model;

/**
 *
 * @author felipe.ferreira
 */
public class Niveis {

    private int id;
    private String nomeNivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeNivel() {
        return nomeNivel;
    }

    public void setNomeNivel(String nomeNivel) {
        this.nomeNivel = nomeNivel;
    }

    @Override
    public String toString() {
        return "Niveis{" + "nomeNivel=" + nomeNivel + '}';
    }
    
    public Enum CompareNiveis(){
        
        return null;
        
    }

}
