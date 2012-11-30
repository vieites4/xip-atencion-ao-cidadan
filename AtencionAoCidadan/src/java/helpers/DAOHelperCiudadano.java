package helpers;

import dao.DAORecibos;
import dao.DAORecibosCategorias;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Recibo;
import model.RecibosCategoria;
import model.Usuario;

/**
 *
 * @author nessa
 */
public class DAOHelperCiudadano {
    
    static final Logger log = Logger.getLogger(DAOHelperCiudadano.class.getSimpleName());
    
    public String onListRecibos(HttpServletRequest request, HttpServletResponse response, Usuario u) {
        Long ciudadanoId = u.getCiudadano().getId();
        List<Recibo> recibos = DAORecibos.getInstance().getByFilters(ciudadanoId);
        request.setAttribute("listRecibos", recibos);
        return "listRecibos.jsp";
    }
    
    public String onViewRecibo(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        Recibo r = DAORecibos.getInstance().getById(id);
        request.setAttribute("recibo", r);
        return "recibo.jsp";
    }
    
    public String onViewCiudadano(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        request.setAttribute("ver", true);
        request.setAttribute("ciudadano", u.getCiudadano());
        return "ciudadano.jsp";
    }

    public String onViewDomiciliacion(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        List<RecibosCategoria> categorias = DAORecibosCategorias.getInstance().getByFilters(true);
        request.setAttribute("listCategorias", categorias);
        return "addDomiciliacion.jsp";
    }
       
}
