package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author nessa
 */
@Entity
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    public Usuario(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //TODO: añadir los campos que deba tener el usuario
    
}
