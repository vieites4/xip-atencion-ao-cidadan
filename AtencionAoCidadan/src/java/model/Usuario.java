package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author nessa
 */
@Entity
public class Usuario implements Serializable{
    /* Atributos */
    @Id
    @GeneratedValue
    private Long id;
    private String usuario;
    private String email; //javax.mail.internet.InternetAddress
    private String movil;
    @Column(columnDefinition="bigint")
    private TiposUsuarios tipo; 
    
    /* Constructores */
    public Usuario(){}
    
    public Usuario(String usuario){
        
    }

    /* Getters & setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTipo() {
        return tipo.getValue();
    }

    public void setTipo(TiposUsuarios tipo) {
        this.tipo = tipo;
    }
    
    
    
    //TODO: añadir los campos que deba tener el usuario
    
    //TODO
    public Boolean isAdministrativo(){
        //return (tipo == TiposUsuarios.Administrativo);
        return true;
    }
    
    
}
