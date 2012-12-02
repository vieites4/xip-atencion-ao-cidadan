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
public class Domiciliacion implements Serializable {
    /* Atributos */
    @Id
    @GeneratedValue
    private Long id;
    private String referencia;
    private boolean esAutoliquidacion = false;
    @ManyToOne 
    private RecibosCategoria categoria;
    @ManyToOne
    private Ciudadano ciudadano;
    @ManyToOne
    private CuentaBancaria cuenta;
    
    
    /* Constructores */
    public Domiciliacion(){}
    
    /* Getters & setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isEsAutoliquidacion() {
        return esAutoliquidacion;
    }

    public void setEsAutoliquidacion(boolean esAutoliquidacion) {
        this.esAutoliquidacion = esAutoliquidacion;
    }

    public RecibosCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(RecibosCategoria categoria) {
        this.categoria = categoria;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }
    
    // TODO: hacer función
    public boolean validate(){
        return true;
    }
    
    
}
