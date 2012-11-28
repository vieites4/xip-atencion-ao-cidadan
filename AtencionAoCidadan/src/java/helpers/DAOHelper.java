package helpers;

import dao.DAOCiudadanos;
import dao.DAOUsuarios;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ciudadano;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOHelper {

    static final Logger log = Logger.getLogger(DAOHelper.class.getSimpleName());

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
            

            if (dni != null && !dni.isEmpty()) {
                Ciudadano c = DAOCiudadanos.getInstance().getByDni(dni);
                if (c == null) {
                    request.setAttribute("top_message", "No se encontró al ciudadano");
                    return "listadoCiudadanos.jsp";
                } else {
                    request.setAttribute("ciudadano", c);
                    return "ciudadano.jsp";
                }
            }

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
    
}
