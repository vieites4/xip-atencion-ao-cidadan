package dao;

import java.util.List;
import model.CuentaBancaria;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nessa
 */
public class DAOCuentaBancaria {
    // Singleton instance
    private static DAOCuentaBancaria instance = new DAOCuentaBancaria();
    // Singleton accessor

    public static DAOCuentaBancaria getInstance() {
        return instance;
    }
    
    /**
     * Obtiene un item por id
     */
    public CuentaBancaria getById(Long id) {

        CuentaBancaria c = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //c = (Recibo) session.load(Recibo.class, id);
        c = (CuentaBancaria) session.createCriteria(CuentaBancaria.class).
                add(Restrictions.idEq(id)).
                uniqueResult();
        session.getTransaction().commit();

        return c;

    }

    
    /**
     * Guarda o actualiza los datos de un item en función de si está o no en la base de datos
     * @param c 
     */
    public void saveOrUpdate(CuentaBancaria c) {

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
    public List<CuentaBancaria> getByFilters(Long idCiudadano){
        List<CuentaBancaria> list = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // consulta filtrando por ...
        Criteria c = session.createCriteria(CuentaBancaria.class);
        if(idCiudadano != null ){
            c.add(Restrictions.eq("ciudadano.id", idCiudadano ));
        }
        /* TODO: resto de filtros */
        list = (List<CuentaBancaria>)c.list();

        session.getTransaction().commit();

        return list;
    }
}
