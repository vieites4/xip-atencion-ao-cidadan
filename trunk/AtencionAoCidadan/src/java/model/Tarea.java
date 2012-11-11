package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nessa
 */
@Entity
public class Tarea  implements Serializable{
    /**
     * Id de cada ciudadano
     */
    @Id
    @GeneratedValue
    private Long id;
    
    private String tipo;
    
    private String descripcion;
    
    private String estado;
    
    @ManyToOne
    private Usuario realizadaPor;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    /* Constructores */
    
    public Tarea(){}
    
    /* Getters & setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getRealizadaPor() {
        return realizadaPor;
    }

    public void setRealizadaPor(Usuario realizadaPor) {
        this.realizadaPor = realizadaPor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
