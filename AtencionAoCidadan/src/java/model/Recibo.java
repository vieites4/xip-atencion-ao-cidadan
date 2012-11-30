package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/*
INSERT INTO recibo(
            id, ano, dataaprobacion, datacobro, datalimitepagamento, descripcion, 
            esautoliquidacion, estado, importe, numerorecibo, referencia, 
            tipo, categoria_id, ciudadano_id)
    VALUES (1, 2010, '2010-10-01 10:10', '2010-10-01 10:10', '2010-10-01 10:10', '', 
            false, 1, 10.0, 1, '234s234', 
            'rb', 1, 24);
*/

/**
 *
 * @author nessa
 */
@Entity
public class Recibo implements Serializable {
    @Transient
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
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
    @Column(columnDefinition="tinyint")
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
            this.tipo = tipo.toUpperCase();
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
    
    public String getDataAprobacionFormateada(){
        return format.format(dataAprobacion.getTime());
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
    
    public String getDataCobroFormateada(){
        return format.format(dataCobro.getTime());
    }

    public GregorianCalendar getDataLimitePagamento() {
        return dataLimitePagamento;
    }
    
    public String getDataLimiteFormateada(){
        return format.format(dataLimitePagamento.getTime());
    }

    public void setDataLimitePagamento(GregorianCalendar dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
    }
    
    
}
