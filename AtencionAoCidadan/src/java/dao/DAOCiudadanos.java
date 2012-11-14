package dao;

import java.util.List;
import model.Ciudadano;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joseangel.pineiro
 */
public class DAOCiudadanos {

    // Singleton instance
    private static DAOCiudadanos instance = new DAOCiudadanos();
    // Singleton accessor

    public static DAOCiudadanos getInstance() {
        return instance;
    }

    
    
    /**
     * Obtiene un ciudadano por id
     */
    public Ciudadano getById(Long id) {

        Ciudadano c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        c = (Ciudadano) session.load(Ciudadano.class, id);
        session.getTransaction().commit();

        return c;

    }

    /**
     * Obtiene un ciudadano por dni
     * @param dni
     * @return 
     */
    public Ciudadano getByDni(String dni) {

        Ciudadano c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // consulta filtrando por dni
        c = (Ciudadano) session.createCriteria(Ciudadano.class).
                add(Restrictions.like("dni", dni)).
                uniqueResult();

        session.getTransaction().commit();

        return c;

    }

    /**
     * Guarda o actualiza los datos de un ciudadano en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(Ciudadano c) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(c);
        session.getTransaction().commit();
    }
    
    /**
     * 
     * @param nombre
     * @return 
     */
    public List<Ciudadano> getByFilters(String nombre){
        List list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        /*list = (List<Ciudadano>) session.createCriteria(Ciudadano.class).
                add(Restrictions.like("nombre", nombre)).list();*/
        list = (List<Ciudadano>) session.createCriteria(Ciudadano.class).list();

        session.getTransaction().commit();

        return list;
    }
}
