package model;

/**
 *
 * @author felipe.ferreira
 */
public class InformacoesTipoEnum {

    //NOME BANCO DE DADOS
    public static enum NameDB {
        csem_, sunew_, sunewgeradores_
    };

    //NOME TIPO REQUISICAO
    //NIVEIS
    public static enum Niveis {
        administrador(1), teste(2);
        private final int codigo;

        Niveis(int codigo) {
            this.codigo = codigo;
        }

        int codigo() {
            return codigo;
        }

    }
}
