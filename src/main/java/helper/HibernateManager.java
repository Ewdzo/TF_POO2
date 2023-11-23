package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManager {
  static final String puName = "pu-name";
  static EntityManagerFactory emf;
  static EntityManager em;

  public static EntityManager getEntityManager() {
    emf = Persistence.createEntityManagerFactory("HibernatePOO2");
    em = emf.createEntityManager();

    return em;
  }
}
