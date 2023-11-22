package helper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;
import entities.User;

//Facade
public class HibernateController {
		
	public static void registerActor(Actor newActor) {

        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();
            
            em.persist(newActor);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void registerDirector(Director newDirector) {

        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();
            
            em.persist(newDirector);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void registerMovie(Movie newMovie) {
        
        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();

            em.persist(newMovie);

            em.getTransaction().commit();

        } finally {
            em.close();
        }  
    }

    public static void registerSeries(Series newSeries) {

        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();

            em.persist(newSeries);

            em.getTransaction().commit();

        } finally {
            em.close();
        }  
    }

    public static boolean reviewSeries(double grade, String title) {
        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();
            
            Series series = em.find(Series.class, title);
            series.setGrade(grade);
            
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }  
    }

    public static boolean reviewMovie(double grade, String title) {
        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();
            
            Movie movie = em.find(Movie.class, title);
            movie.setGrade(grade);
            
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }  
    }

    public static Actor searchActor(String CPF) {
        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();

            Actor actor = em.find(Actor.class, CPF);

            em.getTransaction().commit();

            return actor;
        } catch (Exception e) {
            return null;
        }finally {
            em.close();
        }
    }

    public static Director searchDirector(String CPF) {
        EntityManager em = HibernateManager.em;

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
        EntityManager em = HibernateManager.em;

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
        EntityManager em = HibernateManager.em;

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

    public static boolean registerUser(User newUser) {
        EntityManager em = HibernateManager.em;

        try {
            em.getTransaction().begin();
            
            em.persist(newUser);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }finally {
            em.close();
        }
    }

    public static boolean login(String email, String password) {
        EntityManager em = HibernateManager.em;

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
