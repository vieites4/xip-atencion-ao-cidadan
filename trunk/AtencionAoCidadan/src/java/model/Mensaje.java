package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.Column;
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
    private String asunto;
    
    @Column(length=9000, columnDefinition="text")
    private String texto;
    private boolean estaLeido = false;
    private GregorianCalendar fecha;
    
    @ManyToOne
    private Usuario destinatario;
    
    @ManyToOne
    private Usuario remitente;
    
    
    public Mensaje(){
        fecha = new GregorianCalendar();
    }

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

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    
    
    
}
