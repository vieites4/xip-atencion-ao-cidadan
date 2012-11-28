package model;

/**
 *
 * @author nessa
 */
public enum TiposUsuarios {
    Administrador(0), Administrativo(1), Ciudadano(2) ;

    private final int value;
    private TiposUsuarios(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
