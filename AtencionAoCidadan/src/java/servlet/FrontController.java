package servlet;

import dao.DAORecibos;
import helpers.DAOHelper;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Recibo;
import model.Usuario;

/**
 *
 * @author joseangel.pineiro
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    
    static final Logger log = Logger.getLogger(FrontController.class.getSimpleName());
    
    // ACTIONS
    private static String ADD_CIUDADANO = "ADD_CIUDADANO";
    private static String UPDATE_CIUDADANO = "UPDATE_CIUDADANO";
    private static String SEARCH_CIUDADANO = "SEARCH_CIUDADANO";
    private static String Listo_CIUDADANO = "Listo_CIUDADANO";
    private static String Listo_tarefa="Listo_tarefa";
    private static String VIEW_CIUDADANO = "VIEW_CIUDADANO";
    private static DAOHelper daoHelper = new DAOHelper();

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String action = request.getParameter("action");
        String dir = "index.jsp";
        log.log(Level.INFO, "Request action: {0}", action);
        
        //TODO: borrar esto cuando haya inicio de sesion
        HttpSession session = request.getSession(true);
        session.setAttribute("usuario", new Usuario());
        
        if (ADD_CIUDADANO.equalsIgnoreCase(action)) {
            dir = daoHelper.onAddCiudadano(request, response);
        } else if (UPDATE_CIUDADANO.equalsIgnoreCase(action)) {
            dir = daoHelper.onUpdateCiudadano(request, response);
        } else if (SEARCH_CIUDADANO.equalsIgnoreCase(action)){
            dir = daoHelper.onSearchCiudadano(request, response);
        } else if (Listo_CIUDADANO.equalsIgnoreCase(action)){
            dir = daoHelper.onTodosCiudadano(request, response);
        } else if (Listo_tarefa.equalsIgnoreCase(action)){
            dir = daoHelper.onSearchTarea(request, response);
        } else if (VIEW_CIUDADANO.equalsIgnoreCase(action)){
            dir = daoHelper.onViewCiudadano(request, response);
           
        //Redirección a vistas
              }  
        else if ("view_alta".equalsIgnoreCase(action)) {    
            dir = "addCiudadano.jsp";
        } else if ("view_cert_padron".equalsIgnoreCase(action)) {    
            dir = "getCertificado.jsp";
        } else if ("view_direccion".equalsIgnoreCase(action)) {    
            dir = "cambioDireccion.jsp";
        } else if ("view_buscar".equalsIgnoreCase(action)) { 
            dir = "findCiudadano.jsp";
        } else if ("view_tarefas".equalsIgnoreCase(action)) {    
            dir = "listTarefas.jsp";
        } else if ("view_listatarefa".equalsIgnoreCase(action)) {    
            dir = "listTarefas.jsp";
        } else if ("view_listado".equalsIgnoreCase(action)) {    
            dir = "listadoCiudadanos.jsp";
            
        //Recibos
        } else if ("view_recibos".equalsIgnoreCase(action)) {
            dir = daoHelper.onListRecibos(request, response);
            
        } else if ("view_recibo".equalsIgnoreCase(action)) {
            dir = daoHelper.onViewRecibo(request, response);
            
        } else {
            log.log(Level.INFO, "No action performed!");
            //@TODO Handle else
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+dir);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
