package model;

import java.io.Serializable;
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
public class RecibosCategoria implements Serializable {
    /* Atributos */
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String abreviatura;
}
