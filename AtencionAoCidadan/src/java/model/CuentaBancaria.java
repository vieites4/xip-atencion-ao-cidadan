package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author nessa
 */
@Entity
public class CuentaBancaria implements Serializable {
 
    /* Atributos */
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String codigo;
    private String descripcion;
    private String banco;
    
    @ManyToMany
    List<Ciudadano> titulares;
    
    /* Constructores */
    public CuentaBancaria(){
        titulares = new ArrayList<Ciudadano>();
    }
    
    /* Getters & setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.replaceAll(" ", "");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public List<Ciudadano> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Ciudadano> titulares) {
        this.titulares = titulares;
    }
    
    public boolean validate(){
        //return codigo!= null && !codigo.isEmpty() && codigo.length()==20;
        return true;
    }
    
    
    
}
