package helper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManager {
    String puName = "pu-name";
    Map<String, String> props = new HashMap<>();
    EntityManagerFactory emf;
    EntityManager em;

    HibernateManager() {
        emf = Persistence.createEntityManagerFactory("HibernatePOO2");
        em = emf.createEntityManager();
    }

    public static void registerActor(String CPF, String name, Date birthDate, String photo) {
        Actor newActor = new Actor(CPF, name, birthDate, photo);

        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            em.persist(newActor);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Registro de Ator Falhou;");
        } finally {
            em.close();
        }
    }

    public static void registerDirector(String CPF, String name, Date birthDate, String photo) {
        Director newDirector = new Director(CPF, name, birthDate, photo);

        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            em.persist(newDirector);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Registro de Diretor Falhou;");
        } finally {
            em.close();
        }
    }

    public static void registerMovie(String title, List<Actor> cast, double grade, Director director, String description, String photo) {
        Movie newMovie = new Movie(title, cast, grade, director, description, photo);
        
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            em.persist(newMovie);

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Registro de Filme Falhou;");
        } finally {
            em.close();
        }
    }

    public static void registerSeries(String title, List<Actor> cast, double grade, Director director, String description, String photo, int seasons, int episodes) {
        Series newSeries = new Series(title, cast, grade, director, description, photo, seasons, episodes);

        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            em.persist(newSeries);

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Registro de Serie Falhou;");
        } finally {
            em.close();
        }
    }

    public static Actor searchActor(String CPF) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            Actor actor = em.find(Actor.class, CPF);

            em.getTransaction().commit();

            return actor;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static Director searchDirector(String CPF) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            Director director = em.find(Director.class, CPF);

            em.getTransaction().commit();

            return director;
        } catch (Exception e) {
            return null;
        }finally {
            em.close();
        }
    }

    public static Movie searchMovie(String title) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            Movie movie = em.find(Movie.class, title);

            em.getTransaction().commit();

            return movie;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static Series searchSeries(String title) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();

            Series movie = em.find(Series.class, title);

            em.getTransaction().commit();

            return movie;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean registerUser(String CPF, String name, Date birthDate, String photo) {
        Director newDirector = new Director(CPF, name, birthDate, photo);

        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            em.persist(newDirector);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }finally {
            em.close();
        }
    }

    public static boolean login(String email, String password) {
        EntityManager em = new HibernateManager().em;

        try {
            em.getTransaction().begin();
            
            User user = em.find(User.class, email);
            
            if(password == user.password) return true;
            return false;

        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }  
    }
}
