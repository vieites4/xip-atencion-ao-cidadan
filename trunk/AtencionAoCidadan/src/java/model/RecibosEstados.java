package model;

/**
 *
 * @author nessa
 */
public enum RecibosEstados {
    Pendente(0, "Pendente"), Pagado(1,"Pagado"), Anulado(2,"Anulado") ;

    private final int value;
    private final String nombre;
    private RecibosEstados(int value, String nombre) {
        this.value = value;
        this.nombre = nombre;
    }

    public int getValue() {
        return value;
    }
    
    public String getNombre(){
        return nombre;
    }  
}