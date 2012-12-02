/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import model.Tarea;
import org.hibernate.classic.Session;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOTareas {

    // Singleton instance
    private static DAOTareas instance = new DAOTareas();
    // Singleton accessor

    public static DAOTareas getInstance() {
        return instance;
    }

    
    
    /**
     * Obtiene una tarea por id
     */
    public Tarea getById(Long id) {

        Tarea c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        c = (Tarea) session.load(Tarea.class, id);
        session.getTransaction().commit();

        return c;

    }

    

    /**
     * Guarda o actualiza los datos de un tarea en función de si está o no en la base de datos
     * @param t 
     */
    public void saveOrUpdate(Tarea t) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(t);
        session.getTransaction().commit();
    }
    
     public List<Tarea> getByFilt(Long id, String descripcion,String estado, Date fecha, String tipo, Long realizadopor_){
        List list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria t = session.createCriteria(Tarea.class);
        
        if(tipo != null && !tipo.isEmpty()){
            t.add(Restrictions.like("tipo", "%" + tipo + "%"));
        }
        if(descripcion != null && !descripcion.isEmpty()){
            t.add(Restrictions.like("descripcion", "%" + descripcion + "%"));
        }
        /* TODO: resto de filtros */
        list = (List<Tarea>)t.list();

        session.getTransaction().commit();

        return list;
    }
    
    
    
    
}
