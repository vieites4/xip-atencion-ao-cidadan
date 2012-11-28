package dao;

import java.util.List;
import model.Ciudadano;
import org.hibernate.Criteria;
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
    public List<Ciudadano> getByFilters(String nombre, String apellidos, String sexo){
        List list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(Ciudadano.class);
        if(nombre != null && !nombre.isEmpty()){
            c.add(Restrictions.like("nombre", "%" + nombre + "%"));
        }
        if(apellidos != null && !apellidos.isEmpty()){
            c.add(Restrictions.like("apellidos", "%" + apellidos + "%"));
        }
        if(sexo != null && !sexo.isEmpty()){
            c.add(Restrictions.like("sexo", sexo));
        }
        /* TODO: resto de filtros */
        list = (List<Ciudadano>)c.list();

        session.getTransaction().commit();

        return list;
    }
    
}
