package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author nessa
 */
@Entity
public class Mensaje implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String texto;
    private boolean estaLeido;
    
    @ManyToOne
    private Usuario destinatario;
    
    @ManyToOne
    private Usuario remitente;
    
    
    public Mensaje(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEstaLeido() {
        return estaLeido;
    }

    public void setEstaLeido(boolean estaLeido) {
        this.estaLeido = estaLeido;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }
    
    
    
    
}
