package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author nessa
 */
@Entity
public class Recibo implements Serializable {
    /* Atributos */
    @Id
    @GeneratedValue
    private Long id;
    private Long numeroRecibo;
    private String referencia;
    private String descripcion;
    private Float importe;
    private boolean esAutoliquidacion = false;
    private int ano; //Ano liquidcion
    private String tipo;
    private GregorianCalendar dataAprobacion;
    private GregorianCalendar dataCobro;
    private GregorianCalendar dataLimitePagamento;
    private RecibosEstados estado;
    
    @ManyToOne 
    private RecibosCategoria categoria;
    
    @ManyToOne
    private Ciudadano ciudadano;
    
    /* Constructores */
    public Recibo(){
        
    }

    /*Getters & setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Long getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Long numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public boolean isEsAutoliquidacion() {
        return esAutoliquidacion;
    }

    public void setEsAutoliquidacion(boolean esAutoliquidacion) {
        this.esAutoliquidacion = esAutoliquidacion;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if("RB".equalsIgnoreCase(tipo) || "AU".equalsIgnoreCase(tipo)){
            this.tipo = tipo;
        }
    }

    public RecibosCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(RecibosCategoria categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado.getNombre();
    }

    public void setEstado(RecibosEstados estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public GregorianCalendar getDataAprobacion() {
        return dataAprobacion;
    }

    public void setDataAprobacion(GregorianCalendar dataAprobacion) {
        this.dataAprobacion = dataAprobacion;
    }

    public GregorianCalendar getDataCobro() {
        return dataCobro;
    }

    public void setDataCobro(GregorianCalendar dataCobro) {
        this.dataCobro = dataCobro;
    }

    public GregorianCalendar getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    public void setDataLimitePagamento(GregorianCalendar dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
    }
    
    
}
