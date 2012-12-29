package dao;

import java.util.List;
import model.Mensaje;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOMensajes {

    // Singleton instance
    private static DAOMensajes instance = new DAOMensajes();
    // Singleton accessor

    public static DAOMensajes getInstance() {
        return instance;
    }
    
    
    /**
     * Obtiene un item por id
     */
    public Mensaje getById(Long id) {

        Mensaje c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //c = (Mensaje) session.load(Mensaje.class, id);
        c = (Mensaje) session.createCriteria(Mensaje.class).
                add(Restrictions.idEq(id)).
                uniqueResult();
        session.getTransaction().commit();

        return c;

    }

    
    /**
     * Guarda o actualiza los datos de un item en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(Mensaje c) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(c);
        session.getTransaction().commit();
    }
    
    /**
     * 
     * @param idUsuario
     * @return 
     */
    public List<Mensaje> getByFilters(Long destinatarioId, Long remitenteId){
        List<Mensaje> list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(Mensaje.class);
        if(destinatarioId != null ){
            c.add(Restrictions.eq("destinatario.id", destinatarioId ));
        }
        if(remitenteId != null ){
            c.add(Restrictions.eq("remitente.id", remitenteId ));
        }
        /* TODO: resto de filtros */
        list = (List<Mensaje>)c.list();

        session.getTransaction().commit();

        return list;
    }
}
