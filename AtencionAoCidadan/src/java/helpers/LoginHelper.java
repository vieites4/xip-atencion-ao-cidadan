package helpers;

import dao.DAOCiudadanos;
import dao.DAOCuentaBancaria;
import dao.DAODomiciliacion;
import dao.DAORecibos;
import dao.DAORecibosCategorias;
import dao.DAOUsuarios;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CuentaBancaria;
import model.Domiciliacion;
import model.Recibo;
import model.RecibosCategoria;
import model.Usuario;

/**
 *
 * @author nessa
 */
public class LoginHelper {
    
    static final Logger log = Logger.getLogger(LoginHelper.class.getSimpleName());
    
    public static String onUserLogin(HttpServletRequest request, HttpServletResponse response) {
        
        Usuario u = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username != null && password != null) {
            
            u = DAOUsuarios.getInstance().getByUserName(username);
            
            if (u != null) {
                System.out.println("Usuario: " + u.toString());
                
                if (password.equals(u.getPassword())) {
                    
                    if (u.isCiudadano()) {
                        u.setCiudadano(DAOCiudadanos.getInstance().getByUser(u));
                    }
                    
                    request.getSession().setAttribute("usuario", u);
                    request.setAttribute("top_message", "Bienvenido!");// + u.getCiudadano().getNombre() + "!");
                } else {
                    request.setAttribute("error_cause", "La contraseña es incorrecta");
                }
            } else {
                request.setAttribute("error_cause", "El usuario indicado no existe");
            }
        } else {
            request.setAttribute("error_cause", "Los datos introducidos no son válidos");
        }
        return "index.jsp";
    }
    
    public static String onUserLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("usuario");
        request.getSession().invalidate();
        request.setAttribute("top_message", "Sesión cerrada correctamente!");
        return "index.jsp";
    }
}
