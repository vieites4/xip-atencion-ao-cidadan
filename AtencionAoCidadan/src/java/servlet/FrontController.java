package servlet;

import helpers.DAOHelperAdministrativo;
import helpers.DAOHelperCiudadano;
import helpers.LoginHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TiposUsuarios;
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
    private static String VIEW_LISTADO = "VIEW_LISTADO";
    private static String VIEW_CIUDADANO = "VIEW_CIUDADANO";
    private static String EDIT_CIUDADANO = "EDIT_CIUDADANO";
    private static String VIEW_CERT_CORRIENTE_PAGO = "VIEW_CERT_CORRIENTE_PAGO";
    private static String VIEW_TARXETA = "view_tarxeta_aparcamento";
    
    private static String LOGIN = "LOGIN";
    private static String LOGOUT = "LOGOUT";
    private static DAOHelperAdministrativo helperAdministrativo = new DAOHelperAdministrativo();
    private static DAOHelperCiudadano helperCiudadano = new DAOHelperCiudadano();

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

        try {

            log.log(Level.INFO, "Request action: {0}", action);

            HttpSession session = request.getSession(true);




            Usuario u = null;

            u = (Usuario) session.getAttribute("usuario");

            
            if (!AppConfig.enableLogin) {
                if (u == null) {
                    u = new Usuario();
                    u.setTipo(TiposUsuarios.Ciudadano);
                    u.setCiudadano(dao.DAOCiudadanos.getInstance().getById(new Long(125)));
                    u.setTipo(TiposUsuarios.Administrativo); //Comentar para ser ciudadano
                    session.setAttribute("usuario", u);
                }
            }
            
            
            if (u == null && LOGIN.equalsIgnoreCase(action)) {

                dir = LoginHelper.onUserLogin(request, response);

            } else if (u != null && LOGOUT.equalsIgnoreCase(action)) {
                u = null;
                dir = LoginHelper.onUserLogout(request, response);
                /*----------------------------------*
                 * ADMINISTRATIVO
                 *----------------------------------*/
            } else if (u != null && u.isAdministrativo()) {
                //Ciudadano
                if (ADD_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onAddCiudadano(request, response);
                } else if (UPDATE_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onUpdateCiudadano(request, response);
                } else if (SEARCH_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onSearchCiudadano(request, response);
                } else if (VIEW_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onViewCiudadano(request, response);
                } else if (VIEW_LISTADO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onTodosCiudadano(request, response);
                } else if (EDIT_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onEditCiudadano(request, response);

                 
                    //Tareas
                } else if ("view_tarefas".equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onSearchTarea(request, response,u);
 } else if ("view_tarea".equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onPdf(request, response,u);

                    //Recibos
                
                    //Recibos
                } else if ("view_recibos".equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onListRecibos(request, response);
                } else if ("view_recibo".equalsIgnoreCase(action)) {
                    dir = helperAdministrativo.onViewRecibo(request, response);


                    //Redirección a vistas
                } else if ("view_alta".equalsIgnoreCase(action)) {
                    dir = "addCiudadano.jsp";
                } else if ("view_cert_padron".equalsIgnoreCase(action)) {
                    dir = "getCertificado.jsp";
                } else if ("view_direccion".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onViewDireccion(request,response,u);
                } else if ("view_buscar".equalsIgnoreCase(action)) {
                    dir = "findCiudadano.jsp";
                } else {//Sin accion
                    log.log(Level.INFO, "No action performed!");
                    //@TODO Handle else
                }

                /*----------------------------------*
                 * ADMINISTRADOR
                 *----------------------------------*/
            } else if (u != null && u.isAdministrador()) {
                if ("search_usuario".equalsIgnoreCase(action)) {
                    dir = "searchUsuario.jsp";
                } else {//Sin accion
                    log.log(Level.INFO, "No action performed!");
                    //@TODO Handle else
                }


                /*----------------------------------*
                 * CIUDADANO
                 *----------------------------------*/
            } else if (u != null && u.isCiudadano()) {
                if ("view_cert_padron".equalsIgnoreCase(action)) {
                    dir = "getCertificado.jsp";
                } else if ("view_direccion".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onViewDireccion(request, response, u);
                } else if ("view_recibos".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onListRecibos(request, response, u);
              } else if (VIEW_TARXETA.equalsIgnoreCase(action)) {
                    dir = "tarxeta.jsp";

                } else if ("view_recibo".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onViewRecibo(request, response);
                } else if ("view_domiciliar".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onViewDomiciliar(request, response, u);
                } else if ("add_domiciliacion".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onAddDomiciliacion(request, response, u);
                } else if (VIEW_CIUDADANO.equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onViewCiudadano(request, response, u);
                } else if ("change_direccion".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onChangeDireccion(request, response, u);
                } else if ("solicitar_cert".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onSolicitarCert(request, response, u);
                } else if ("tarx_aparcamento".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onTarxetaAparcamento(request, response, u);
                } else if ("pay_recibo".equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onPayRecibo(request, response);
                }else if (VIEW_CERT_CORRIENTE_PAGO.equalsIgnoreCase(action)) {
                    dir = helperCiudadano.onSolicitarCertCorrientePago(request, response, u);
                } else {//Sin accion
                    log.log(Level.INFO, "No action performed!");
                    //@TODO Handle else
                }
            } else {//Sin accion
                log.log(Level.INFO, "No action performed!");
                //@TODO Handle else
            }



        } catch (Exception e) {

            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            request.setAttribute("error_cause", errors.toString());
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + dir);
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
