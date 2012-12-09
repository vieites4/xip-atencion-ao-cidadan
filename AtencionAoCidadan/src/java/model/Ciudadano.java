package model;

import helpers.UserCredentials;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Esta clase representa un ciudadano empadronado con todos sus datos del padrón
 * http://es.wikipedia.org/wiki/Padr%C3%B3n_municipal_de_habitantes
 * @author joseangel.pineiro
 */
@Entity
public class Ciudadano implements Serializable {

    /* Atributos */
    /**
     * Id de cada ciudadano
     */
    @Id
    @GeneratedValue
    private Long id;
    
    private String nombre;
    private String apellidos;
    
    @Column(unique = true)
    private String dni; //o pasaporte
    
    private String sexo; /* F o M */

    private String direccion;
    private String cp;
    private String nivelInstruccion;
    private String telefono; //Opcional
    private String designacion;//Opcional
    private String email;
    private String movil;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Usuario usuario;
    
    @OneToMany(mappedBy = "ciudadano")
    private List<Recibo> recibos; 

    /* Constructores */
    public Ciudadano() {
    }

    

    /* Getters & setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return 
     */
    public String getNivelInstruccion() {
        return nivelInstruccion;
    }

    /**
     * 
     * @param nivelInstruccion 
     */
    public void setNivelInstruccion(String nivelInstruccion) {
        this.nivelInstruccion = nivelInstruccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDesignacion() {
        return designacion;
    }

    public void setDesignacion(String designacion) {
        this.designacion = designacion;
    }

     public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }
    
     public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public void onCreate() {
        this.usuario = new Usuario();
        this.usuario.setTipo(TiposUsuarios.Ciudadano);
        this.usuario.setCiudadano(this);
        
        UserCredentials credentials = UserCredentials.forUser(usuario);
        this.usuario.setPassword(credentials.getPassword());
        this.usuario.setUsuario(credentials.getUsername());
    }

    public boolean validate() {
        return (nombre != null && apellidos != null && dni != null);
    }

    public List<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(List<Recibo> recibos) {
        this.recibos = recibos;
    }

    @Override
    public String toString() {
        return "Ciudadano{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", sexo=" + sexo + ", direccion=" + direccion + ", cp=" + cp + ", nivelInstruccion=" + nivelInstruccion + ", telefono=" + telefono + ", designacion=" + designacion + '}';
    }
    
    
}
