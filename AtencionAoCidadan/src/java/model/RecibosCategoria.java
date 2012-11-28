package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
insert into `RecibosTipos`(`nombre`, `abreviatura`)
values ('IBI', 'Imposto de Bens Inmobles'), ('IVTM','Imposto de Vehículos de Tracción Mecánica');
*/

/**
 *
 * @author nessa
 */
@Entity
public class RecibosCategoria implements Serializable {
    /* Atributos */
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String abreviatura;
    
    /* Constructores */
    public RecibosCategoria(){
        
    }

    /* Getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    
}
