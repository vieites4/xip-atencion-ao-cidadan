package dao;

import java.util.List;
import model.Domiciliacion;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nessa
 */
public class DAODomiciliacion {
    // Singleton instance
    private static DAODomiciliacion instance = new DAODomiciliacion();
    // Singleton accessor

    public static DAODomiciliacion getInstance() {
        return instance;
    }
    
    /**
     * Obtiene un item por id
     */
    public Domiciliacion getById(Long id) {

        Domiciliacion c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //c = (Recibo) session.load(Recibo.class, id);
        c = (Domiciliacion) session.createCriteria(Domiciliacion.class).
                add(Restrictions.idEq(id)).
                uniqueResult();
        session.getTransaction().commit();

        return c;

    }

    
    /**
     * Guarda o actualiza los datos de un item en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(Domiciliacion c) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(c);
        session.getTransaction().commit();
    }
    
    /**
     * 
     * @param idCiudadano
     * @return 
     */
    public List<Domiciliacion> getByFilters(Long idCiudadano){
        List<Domiciliacion> list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(Domiciliacion.class);
        if(idCiudadano != null ){
            c.add(Restrictions.eq("ciudadano.id", idCiudadano ));
        }
        /* TODO: resto de filtros */
        list = (List<Domiciliacion>)c.list();

        session.getTransaction().commit();

        return list;
    }
}
