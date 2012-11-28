package dao;

import java.util.List;
import model.Ciudadano;
import model.Recibo;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joseangel.pineiro
 */
public class DAORecibos {

    // Singleton instance
    private static DAORecibos instance = new DAORecibos();
    // Singleton accessor

    public static DAORecibos getInstance() {
        return instance;
    }

    
    
    /**
     * Obtiene un item por id
     */
    public Recibo getById(Long id) {

        Recibo c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //c = (Recibo) session.load(Recibo.class, id);
        c = (Recibo) session.createCriteria(Recibo.class).
                add(Restrictions.idEq(id)).
                uniqueResult();
        session.getTransaction().commit();

        return c;

    }

    
    /**
     * Guarda o actualiza los datos de un item en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(Recibo c) {

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
    public List<Recibo> getByFilters(Long idCiudadano){
        List<Recibo> list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(Recibo.class);
        if(idCiudadano != null ){
            c.add(Restrictions.eq("ciudadano.id", idCiudadano ));
        }
        /* TODO: resto de filtros */
        list = (List<Recibo>)c.list();

        session.getTransaction().commit();

        return list;
    }
}
