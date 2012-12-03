/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.HibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 * @author joseangel.pineiro
 */
@WebListener()
public class SessionListener implements ServletContextListener {

    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
       String enableLoginValue = sce.getServletContext().getInitParameter("enable_login");
       
       boolean enableLogin = true;
       
       try{
           enableLogin = Boolean.parseBoolean(enableLoginValue);
       }catch(Exception e){
           // Do nothing
       }
       
       AppConfig.enableLogin = enableLogin;
       
        // initialize hibernate
        HibernateUtil.getSessionFactory();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
