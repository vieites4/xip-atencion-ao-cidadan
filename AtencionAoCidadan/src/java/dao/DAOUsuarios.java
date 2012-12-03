/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Tarea;
import model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOUsuarios {

    // Singleton instance
    private static DAOUsuarios instance = new DAOUsuarios();
    // Singleton accessor

    public static DAOUsuarios getInstance() {
        return instance;
    }
    
    /**
     * Obtiene una tarea por id
     */
    public Usuario getById(Long id) {

        Usuario c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        c = (Usuario) session.load(Usuario.class, id);
        session.getTransaction().commit();

        return c;

    }
    
    
    
     public Usuario getByUserName(String username) {

        Usuario c = null;

        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria t = session.createCriteria(Usuario.class);
        t.add(Restrictions.eq("usuario", username));
        
        c = (Usuario) t.uniqueResult();
        
        session.getTransaction().commit();

        return c;

    }

    

    /**
     * Guarda o actualiza los datos de un tarea en función de si está o no en la base de datos
     * @param t 
     */
    public void saveOrUpdate(Usuario t) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(t);
        session.getTransaction().commit();
    }
}
