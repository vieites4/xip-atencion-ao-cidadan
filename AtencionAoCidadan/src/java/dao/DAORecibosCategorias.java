package dao;

import java.util.List;
import model.Ciudadano;
import model.RecibosCategoria;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;

/**
 *
 * @author joseangel.pineiro
 */
public class DAORecibosCategorias {

    // Singleton instance
    private static DAORecibosCategorias instance = new DAORecibosCategorias();
    // Singleton accessor

    public static DAORecibosCategorias getInstance() {
        return instance;
    }

    
    
    /**
     * Obtiene un ciudadano por id
     */
    public RecibosCategoria getById(Long id) {

        RecibosCategoria c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        c = (RecibosCategoria) session.load(Ciudadano.class, id);
        session.getTransaction().commit();

        return c;

    }

    /**
     * Guarda o actualiza los datos de un ciudadano en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(RecibosCategoria c) {

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
    public List<RecibosCategoria> getByFilters(){
        List<RecibosCategoria> list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(Ciudadano.class);
        /*if(nombre != null && !nombre.isEmpty()){
            c.add(Restrictions.like("nombre", "%" + nombre + "%"));
        }*/
       
        /* TODO: filtros */
        list = (List<RecibosCategoria>)c.list();

        session.getTransaction().commit();

        return list;
    }
}