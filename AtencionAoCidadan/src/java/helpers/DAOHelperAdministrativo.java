package helpers;

import dao.DAOCiudadanos;
import dao.DAORecibos;
import dao.DAOTareas;
import dao.DAOUsuarios;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ciudadano;
import model.Recibo;
import model.Tarea;
import model.Usuario;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOHelperAdministrativo {

    static final Logger log = Logger.getLogger(DAOHelperAdministrativo.class.getSimpleName());

    public String onViewCiudadano(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("ver", true);
        return onEditCiudadano(request, response);
    }
            
    public String onEditCiudadano(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Ciudadano c = DAOCiudadanos.getInstance().getById(id);
        if(c == null){
            request.setAttribute("error_cause", "El ciudadano ya no existe en los registros");  
            return "index.jsp";
        }else{
            //c.setRecibos(DAORecibos.getInstance().getByFilters(id));
            request.setAttribute("ciudadano", c);
            //List<Recibo> listRecibos = DAORecibos.getInstance().getByFilters(id);
            //request.setAttribute("listRecibos", listRecibos);
            return "ciudadano.jsp";
        }
    }
    
    public String onAddCiudadano(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {


            Ciudadano c = new Ciudadano();
            c.setNombre(request.getParameter("name"));
            c.setApellidos(request.getParameter("surname"));
            c.setDni(request.getParameter("dni"));
            c.setDireccion(request.getParameter("direccion"));
            c.setDesignacion(request.getParameter("designacion"));
            c.setTelefono(request.getParameter("telefono"));
            c.setNivelInstruccion(request.getParameter("nivelInstruccion"));
            c.setMovil(request.getParameter("movil"));
            c.setEmail(request.getParameter("email"));
            c.setSexo(request.getParameter("sexo"));
            c.setCp(request.getParameter("cp"));

            if (c.validate()) {
                DAOCiudadanos.getInstance().saveOrUpdate(c);
                c.onCreate();
                DAOUsuarios.getInstance().saveOrUpdate(c.getUsuario());
                request.setAttribute("top_message", "Ciudadano '" + c.getDni() + "' registrado correctamente!<br>Credenciales: " + c.getUsuario().getUsuario() + ", " + c.getUsuario().getPassword());
            }
            else{
                request.setAttribute("error_cause", "Los datos proporcionados no son válidos");
            }
            
            return "addCiudadano.jsp";

        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al guardar los datos de un ciudadano", e);
            request.setAttribute("error_cause", e.getMessage());
            return "addCiudadano.jsp";
        }
    }

    // @TODO Completar
    public String onUpdateCiudadano(HttpServletRequest request, HttpServletResponse response, Usuario u) {

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Ciudadano c = DAOCiudadanos.getInstance().getById(id);
            if(c == null){
                request.setAttribute("error_cause", "El ciudadano ya no existe en los registros");  
                return "index.jsp";
            }else{
                c.setNombre(request.getParameter("name"));
                c.setApellidos(request.getParameter("surname"));
                c.setDni(request.getParameter("dni"));
                c.setDireccion(request.getParameter("direccion"));
                c.setDesignacion(request.getParameter("designacion"));
                c.setTelefono(request.getParameter("telefono"));
                c.setNivelInstruccion(request.getParameter("nivelInstruccion"));
                c.setMovil(request.getParameter("movil"));
                c.setEmail(request.getParameter("email"));
                c.setSexo(request.getParameter("sexo"));
                c.setCp(request.getParameter("cp"));
                
                if (c.validate()) {
                    DAOCiudadanos.getInstance().saveOrUpdate(c);
                    c.onCreate();
                   
                    request.setAttribute("top_message", "Ciudadano '" + c.getDni() + "' modificado correctamente!");
                    DAOHelperMensajes.enviarMensaje("Modificación de datos", "Estimado ciudadano " + c.getNombre() 
                            + " He modificado sus datos para corregir errores. Para más inforción contacte con "
                            + "el teléfono 982 379 251. Concello de Antas de Ulla.", u, id);
                }
                else{
                    request.setAttribute("error_cause", "Los datos proporcionados no son válidos");
                } 
                        
                return "index.jsp";
            }
            
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
            request.setAttribute("top_message", "Error al Guardar Cambios " );
            return "index.jsp";
        }


    }

    public String onTodosCiudadano(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("name");
            String apellidos = request.getParameter("surname");
            String dni = request.getParameter("dni");
            String sexo = request.getParameter("sexo");
            
            List<Ciudadano> list = DAOCiudadanos.getInstance().getByFilters(nombre, apellidos, sexo);
            request.setAttribute("list", list);
            return "listadoCiudadanos.jsp";
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
            return "index.jsp";
        }
    }
    public String onSearchCiudadano(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("name");
            String apellidos = request.getParameter("surname");
            String dni = request.getParameter("dni");
            String sexo = request.getParameter("sexo");
            


            if (dni != null && !dni.isEmpty()) {
                Ciudadano c = DAOCiudadanos.getInstance().getByDni(dni);
                if (c == null) {
                    request.setAttribute("top_message", "No se encontró al ciudadano");
                    return "findCiudadano.jsp";
                } else {
                    request.setAttribute("ciudadano", c);
                    return "ciudadano.jsp";
                }
            }

            List<Ciudadano> list = DAOCiudadanos.getInstance().getByFilters(nombre, apellidos, sexo);
            request.setAttribute("list", list);
            return "findCiudadano.jsp";
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
            return "index.jsp";
        }
    }
    public String onSearchTarea(HttpServletRequest request, HttpServletResponse response, Usuario u) {
        try {
            //            String realizadopor_2= request.getParameter("realizadopor_");
  //          Long realizadopor_ = Long.parseLong(realizadopor_2);
         
                      
            List<Tarea> list = DAOTareas.getInstance().getAll();
            request.setAttribute("listTarefas", list);
            return "listTarefas.jsp";
        } catch (Exception e) {
            
            Logger.getLogger(DAOHelperAdministrativo.class.getName()).log(Level.SEVERE, null, e);
            // log error
            // redirect to error page or show error message
            return "index.jsp";
        }
    }
    public String onPdf(HttpServletRequest request, HttpServletResponse response, Usuario u) {
        try {
            //            String realizadopor_2= request.getParameter("realizadopor_");
  //          Long realizadopor_ = Long.parseLong(realizadopor_2);
        Long id = Long.parseLong(request.getParameter("id"));
        Tarea t = DAOTareas.getInstance().getById(id);
        request.setAttribute("tarea", t);
        request.setAttribute("ver", true);
         ///   aqui tería que condicionalo a esa petición
            return "FacerTarefas.jsp";
        } catch (Exception e) {
            
            Logger.getLogger(DAOHelperAdministrativo.class.getName()).log(Level.SEVERE, null, e);
            // log error
            // redirect to error page or show error message
            return "index.jsp";
        }
    }
    
    public String onListRecibos(HttpServletRequest request, HttpServletResponse response) {
        Long ciudadanoId = Long.parseLong(request.getParameter("ciudadano"));
        request.setAttribute("ciudadano", ciudadanoId);
        List<Recibo> recibos = DAORecibos.getInstance().getByFilters(ciudadanoId);
        request.setAttribute("listRecibos", recibos);
        return "listRecibos.jsp";
    }
    
    
    public String onViewRecibo(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        Recibo r = DAORecibos.getInstance().getById(id);
        request.setAttribute("recibo", r);
        request.setAttribute("ver", true);
        return "recibo.jsp";
    }
    
    public String onSolveTarea(HttpServletRequest request, HttpServletResponse response,Usuario u)throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Tarea t = DAOTareas.getInstance().getById(id);
        if(t==null){
           request.setAttribute("error_cause", "La Tarea ya no existe en los registros");  
            
        } else{
            String Tipo = t.getTipo();
            if("Cambio de Domicilio".equalsIgnoreCase(Tipo)){
                //c.setDireccion(t.getDescripcion());
                request.setAttribute("top_message",t.getDescripcion() +"guardada para "+ t.getRealizadaPor().getUsuario() );
                t.setEstado("Resuelta");
                DAOTareas.getInstance().saveOrUpdate(t);
            } else if("Solicitud de Certificado de Empadronamiento".equalsIgnoreCase(Tipo)){
                request.setAttribute("top_message", "Certificado Emitido para "+ t.getRealizadaPor().getUsuario() );
                t.setEstado("Resuelta");
                DAOTareas.getInstance().saveOrUpdate(t);
            }
            
        }
        return onSearchTarea(request,response,u); 
    }
     
}
