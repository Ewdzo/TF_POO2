package org.example;

import java.util.HashMap;
import java.util.Map;

import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class HibernateManager {
    String puName = "pu-name";
    Map<String, String> props = new HashMap<>();
    EntityManagerFactory emf;
    EntityManager em;

    HibernateManager() {
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");

        emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);
        em = emf.createEntityManager();
    }
}
