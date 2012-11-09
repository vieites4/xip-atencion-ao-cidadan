/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import dao.DAOCiudadanos;
import java.io.IOException;
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

    public void onAddCiudadano(HttpServletRequest request, HttpServletResponse response) throws IOException {



        try {
            Ciudadano c = new Ciudadano();
            c.setNombre(request.getParameter("name")); // 
            c.setApellidos(request.getParameter("surname")); // ;

            DAOCiudadanos.getInstance().saveOrUpdate(c);
            
            request.getSession().setAttribute("top_message", "Added!");
            response.sendRedirect("index.jsp");
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error addind ciudadano", e);
            request.getSession().setAttribute("error_cause", e.getMessage());
            response.sendRedirect("error.jsp");
        }


    }

    // @TODO Completar
    public void onUpdateCiudadano(HttpServletRequest request, HttpServletResponse response) {

        try {
            Ciudadano c = new Ciudadano();

            c.setDireccion("Santiago de Compostela"); // request.getParameter("direction");
            // ...

            DAOCiudadanos.getInstance().saveOrUpdate(c);
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
        }


    }
}
