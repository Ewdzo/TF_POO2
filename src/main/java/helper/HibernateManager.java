package helper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManager {
    String puName = "pu-name";
    EntityManagerFactory emf;
    EntityManager em;

    HibernateManager() {
        emf = Persistence.createEntityManagerFactory("HibernatePOO2");
        em = emf.createEntityManager();
    }
}
