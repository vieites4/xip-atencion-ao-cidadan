package helpers;

import dao.DAOCuentaBancaria;
import dao.DAODomiciliacion;
import dao.DAORecibos;
import dao.DAORecibosCategorias;
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
public class DAOHelperCiudadano {
    
    static final Logger log = Logger.getLogger(DAOHelperCiudadano.class.getSimpleName());
    
    public String onListRecibos(HttpServletRequest request, HttpServletResponse response, Usuario u) {
        Long ciudadanoId = u.getCiudadano().getId();
        List<Recibo> recibos = DAORecibos.getInstance().getByFilters(ciudadanoId);
        request.setAttribute("listRecibos", recibos);
        return "listRecibos.jsp";
    }
    
    public String onViewRecibo(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("ver", true);
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

    public String onViewDomiciliar(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        List<RecibosCategoria> categorias = DAORecibosCategorias.getInstance().getByFilters(true);
        request.setAttribute("listCategorias", categorias);
        return "addDomiciliacion.jsp";
    }

    public String onAddDomiciliacion(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        Domiciliacion d = new Domiciliacion();
        Long idCategoria = Long.parseLong(request.getParameter("categoria"));
        d.setCategoria(DAORecibosCategorias.getInstance().getById(idCategoria));
        d.setReferencia(request.getParameter("referencia"));
        CuentaBancaria c = new CuentaBancaria();
        c.getTitulares().add(u.getCiudadano());
        c.setDescripcion(request.getParameter("descripcion"));
        c.setCodigo(request.getParameter("codigo"));
        c.setBanco(request.getParameter("banco"));
        d.setCuenta(c);
        d.setCiudadano(u.getCiudadano());
        if (c.validate() && d.validate()) {
            DAOCuentaBancaria.getInstance().saveOrUpdate(c);
            DAODomiciliacion.getInstance().saveOrUpdate(d);
            request.setAttribute("top_message", "Domiciliación registrada correctamente!");
        }
        else{
            request.setAttribute("error_cause", "Los datos proporcionados no son válidos");
        }
        
        
        return onViewDomiciliar(request, response, u);
    }
       
}