package servlet;

import helpers.DAOHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        log.log(Level.INFO, "Request action: {0}", action);
        
        if (ADD_CIUDADANO.equalsIgnoreCase(action)) {
            daoHelper.onAddCiudadano(request, response);

        } else if (UPDATE_CIUDADANO.equalsIgnoreCase(action)) {
            daoHelper.onUpdateCiudadano(request, response);
        } else if (SEARCH_CIUDADANO.equalsIgnoreCase(action)){
            daoHelper.onSearchCiudadano(request, response);
        } else if ("view_alta".equalsIgnoreCase(action)) {    
            response.sendRedirect("addCiudadano.jsp");
        } else if ("view_certificado".equalsIgnoreCase(action)) {    
            response.sendRedirect("getCertificado.jsp");
        } else if ("view_cambio".equalsIgnoreCase(action)) {    
            response.sendRedirect("cambioDireccion.jsp");
        } else if ("view_buscar".equalsIgnoreCase(action)) { 
            response.sendRedirect("findCiudadano.jsp");
        } else if ("view_tarefas".equalsIgnoreCase(action)) {    
            response.sendRedirect("listTarefas.jsp");
        } else {
            log.log(Level.INFO, "No action performed!");
            //@TODO Handle else
        }
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
