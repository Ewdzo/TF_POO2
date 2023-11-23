package helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import com.mysql.cj.Session;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;
import entities.User;

//Facade
public class HibernateController {
		
	public static void registerActor(Actor newActor) {

        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();
            
            em.persist(newActor);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void registerDirector(Director newDirector) {

        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();
            
            em.persist(newDirector);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void registerMovie(Movie newMovie) {
        
        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(newMovie);

            em.getTransaction().commit();

        } finally {
            em.close();
        }  
    }

    public static void registerSeries(Series newSeries) {

        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(newSeries);

            em.getTransaction().commit();

        } finally {
            em.close();
        }  
    }

    public static boolean reviewSeries(double grade, String title) {
        EntityManager em = HibernateManager.getEntityManager();

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
        EntityManager em = HibernateManager.getEntityManager();

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
        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();

            Actor actor = em.find(Actor.class, CPF);

            em.getTransaction().commit();

            return actor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static Director searchDirector(String CPF) {
        EntityManager em = HibernateManager.getEntityManager();

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
        EntityManager em = HibernateManager.getEntityManager();

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
        EntityManager em = HibernateManager.getEntityManager();

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

    public static User searchUser(String email) {
        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();

            User user = em.find(User.class, email);

            em.getTransaction().commit();

            return user;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean registerUser(User newUser) {
        EntityManager em = HibernateManager.getEntityManager();

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
        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();
            
            User user = em.find(User.class, email);
            if(password.equals(user.password)) return true;
            return false;

        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }  
    }

    public static ArrayList<Movie> getMovies(List<String> Movies) {
        EntityManager em = HibernateManager.getEntityManager();
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            em.getTransaction().begin();
            
            for (String title : Movies) {
                Movie movie = em.find(Movie.class, title);
                movies.add(movie);
            }

            em.getTransaction().commit();

            return movies;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static ArrayList<Series> getSeries(List<String> Series) {
        EntityManager em = HibernateManager.getEntityManager();
        ArrayList<Series> series = new ArrayList<>();

        try {
            em.getTransaction().begin();
            
            for (String title : Series) {
                Series serie = em.find(Series.class, title);
                series.add(serie);
            };

            em.getTransaction().commit();

            return series;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
