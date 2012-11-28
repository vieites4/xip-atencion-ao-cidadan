package model;

/**
 *
 * @author nessa
 */
public enum RecibosEstados {
    Pendente(0), Pagado(1), Anulado(2) ;

    private final int value;
    private RecibosEstados(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}