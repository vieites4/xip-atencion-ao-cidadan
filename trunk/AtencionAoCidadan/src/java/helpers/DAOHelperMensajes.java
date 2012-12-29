/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import dao.DAOMensajes;
import dao.DAOUsuarios;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mensaje;
import model.Usuario;

/**
 *
 * @author nessa
 */
public class DAOHelperMensajes {
    
    static final Logger log = Logger.getLogger(DAOHelperMensajes.class.getSimpleName());
    
    public String onListMensajes(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        u.setMensajesEnviados(DAOMensajes.getInstance().getByFilters(null, u.getId()));
        /*for(int i=0; i<u.getMensajesEnviados().size(); i++){
            Mensaje m = u.getMensajesEnviados().get(i);
            m = DAOMensajes.getInstance().getById(m.getId());
            u.getMensajesEnviados().set(i, m);
        }*/
        u.setMensajesRecibidos(DAOMensajes.getInstance().getByFilters(u.getId(), null));
        /*for(int i=0; i<u.getMensajesRecibidos().size(); i++){
            Mensaje m = u.getMensajesRecibidos().get(i);
            m = DAOMensajes.getInstance().getById(m.getId());
            u.getMensajesRecibidos().set(i, m);
        }*/
        return "buzonMensajes.jsp";
    }
    
    public String onBorrarMensaje(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        DAOMensajes.getInstance().delete(DAOMensajes.getInstance().getById(id));
        return onListMensajes(request, response, u);
    }
    
    public String onMarcarMensaje(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Boolean leido = Boolean.parseBoolean(request.getParameter("leido"));
        Mensaje m = DAOMensajes.getInstance().getById(id);
        //if(m.getDestinatario().getId().equals(u.getId())){
            m.setEstaLeido(leido);
            DAOMensajes.getInstance().saveOrUpdate(m);
        //}
        
        return onListMensajes(request, response, u);
    }
    
    public String onSendMensaje(HttpServletRequest request, HttpServletResponse response, Usuario u) throws IOException {
        try{
            Mensaje m = new Mensaje();
            m.setAsunto(request.getParameter("asunto"));
            m.setTexto(request.getParameter("texto"));
            m.setRemitente(u);
            m.setDestinatario(DAOUsuarios.getInstance().getByUserName(request.getParameter("destinatario")));
            DAOMensajes.getInstance().saveOrUpdate(m);
            
            request.setAttribute("top_message", "Mensaje enviado correctamente!");
            return onListMensajes(request, response, u);
        } catch (Exception e) {
            // log error
            // redirect to error page or show error message
            request.setAttribute("top_message", "Error al enviar el mensaje " );
            return "buzonMensajes.jsp";
        }
    }
    
    public static boolean enviarMensaje(String asunto, String texto, Usuario remitente, Long destinatarioId){
        return enviarMensaje(asunto, texto, remitente, DAOUsuarios.getInstance().getById(destinatarioId));
    }
    
    public static boolean enviarMensaje(String asunto, String texto, Usuario remitente, Usuario destinatario){
        try{
            Mensaje m = new Mensaje();
            m.setAsunto(asunto);
            m.setTexto(texto);
            m.setRemitente(remitente);
            m.setDestinatario(destinatario);
            DAOMensajes.getInstance().saveOrUpdate(m);
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al enviar un mensaje de buzón", e);
            return false;
        }
    }
}
