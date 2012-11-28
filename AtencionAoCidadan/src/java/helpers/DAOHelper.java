package helpers;

import dao.DAOCiudadanos;
import dao.DAOTareas;
import model.Tarea;
import dao.DAOUsuarios;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ciudadano;
import model.Recibo;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOHelper {

    static final Logger log = Logger.getLogger(DAOHelper.class.getSimpleName());

    
    public String onViewCiudadano(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

            if (c.validate()) {
                DAOCiudadanos.getInstance().saveOrUpdate(c);
                c.onCreate();
                DAOUsuarios.getInstance().saveOrUpdate(c.getUsuario());
                request.setAttribute("top_message", "Ciudadano '" + c.getDni() + "' registrado correctamente!");
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
    public String onUpdateCiudadano(HttpServletRequest request, HttpServletResponse response) {

        try {
            Ciudadano c = new Ciudadano();

            c.setDireccion("Santiago de Compostela"); // request.getParameter("direction");
            // ...

            DAOCiudadanos.getInstance().saveOrUpdate(c);
            return "ciudadano.jsp";
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
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
    public String onSearchTarea(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id1 = request.getParameter("id");
            Long id = Long.parseLong(id1);
            String fecha2=request.getParameter("fecha");
            SimpleDateFormat f=new SimpleDateFormat("dd/MM/yy");
            Date fecha= f.parse(fecha2);
            String tipo = request.getParameter("tipo");
            String descripcion = request.getParameter("descripcion");
            String estado = request.getParameter("estado");

            
            List<Tarea> list = DAOTareas.getInstance().getByFilt(id,tipo, descripcion, estado, fecha);
            request.setAttribute("list", list);
            return "listTarefas.jsp";
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
            return "index.jsp";
        }
    }

}