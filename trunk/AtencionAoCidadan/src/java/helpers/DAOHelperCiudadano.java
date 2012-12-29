package helpers;

import dao.DAOCuentaBancaria;
import dao.DAODomiciliacion;
import dao.DAORecibos;
import dao.DAORecibosCategorias;
import dao.DAOTareas;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ciudadano;
import model.CuentaBancaria;
import model.Domiciliacion;
import model.Recibo;
import model.RecibosCategoria;
import model.RecibosEstados;
import model.Tarea;
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
        } else {
            request.setAttribute("error_cause", "Los datos proporcionados no son válidos");
        }


        return onViewDomiciliar(request, response, u);
    }

    public String onViewDireccion(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {

        //Ciudadano c = DAOCiudadanos.getInstance().getById(u.getId()); 
        Ciudadano c = u.getCiudadano();
        request.setAttribute("ciudadano", c);
        return "cambioDireccion.jsp";
    }

    public String onChangeDireccion(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        String nuevaDir = request.getParameter("direccion_nueva");
        Tarea t = new Tarea();
        t.setEstado("Pendiente");
        t.setRealizadaPor(u);
        t.setTipo("Cambio de Domicilio");
        t.setDescripcion("Nueva Direccion: " + nuevaDir);

        DAOTareas.getInstance().saveOrUpdate(t);
        request.setAttribute("top_message", "Solicitud de Cambio de Domicilio Enviada.");
        return "index.jsp";
    }

    public String onSolicitarCert(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        Ciudadano c = u.getCiudadano();
        Tarea t = new Tarea();
        t.setEstado("Pendiente");
        t.setRealizadaPor(u);
        t.setTipo("Solicitud de Certificado de Empadronamiento");
        t.setDescripcion("Datos del Certificado: ");//c.getCiudadano();

        DAOTareas.getInstance().saveOrUpdate(t);
        request.setAttribute("top_message", "Solicitud de Certificado de Empadronamiento Enviada.");
        return "index.jsp";
    }
public String onTarxetaAparcamento(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        Ciudadano c = u.getCiudadano();
        Tarea t = new Tarea();
        t.setEstado("Pendiente");
        t.setRealizadaPor(u);
        t.setTipo("Solicitud de Tarxeta Aparcamento");
        //t.setDescripcion("Datos del Certificado: ");//c.getCiudadano();

        DAOTareas.getInstance().saveOrUpdate(t);
        request.setAttribute("top_message", "Solicitud de Tarxeta de Aparcamento Enviada.");
        return "index.jsp";
    }



    public String onSolicitarCertCorrientePago(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {

        List<Recibo> recibos = DAORecibos.getInstance().getByFilters(u.getCiudadano().getId());

        boolean corriente = true;

        for (Recibo r : recibos) {

            int estado = RecibosEstados.Pendente.getValue();

            try {
                estado = Integer.parseInt(r.getEstado());
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error al solicitar certificado de corriente de pago", e);
            }

            if (estado == RecibosEstados.Pendente.getValue()) {
                corriente = false;
                break;
            }
        }

        if (corriente) {
            Tarea t = new Tarea();
            t.setEstado("Pendiente");
            t.setRealizadaPor(u);
            t.setTipo("Solicitud certificado corriente de pago");
            t.setDescripcion("Estado actual: Sin recibos pendientes");

            DAOTareas.getInstance().saveOrUpdate(t);
            request.setAttribute("top_message", "Su solicitud ha sido realizada con éxito. <br/> En breve la recibirá en su domicilio mediante correo postal.");
        } else {
            request.setAttribute("top_message", "No ha sido posible realizar su solicitud. Esto puede deberse a que en estos momentos tiene algún recibo pendiente de pago. Para más información vaya a la sección <a href='FrontController?action=view_recibos'>Recibos</a> o contacte con nosotros en nuestro número de atención al público.");
        }

        return "index.jsp";
    }
    
    public String onPayRecibo(HttpServletRequest request, HttpServletResponse response) {
        
        Long id = Long.parseLong(request.getParameter("id"));
        Recibo r = DAORecibos.getInstance().getById(id);
        request.setAttribute("recibo", r);
        return "recibo.jsp";
    }
    
    
}
