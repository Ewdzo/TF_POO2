package helper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManager {
    static final String puName = "pu-name";
    static EntityManagerFactory emf;
    static EntityManager em;

    public EntityManager getEntityManager() {
        if(emf == null) emf = Persistence.createEntityManagerFactory("HibernatePOO2");
        if(em == null) em = emf.createEntityManager();
        
        return em; 
    }
}
