package helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;
import entities.User;



public class HibernateControllerTest {

    @BeforeEach
    public void connectionTest() {
        EntityManager em = HibernateManager.getEntityManager();

        try {
            em.getTransaction().begin();
        } finally {
            em.close();
        }
    }

    @Test
    public void testRegisterActor() {
        String cpf = "000.000.000-01";
        String name = "Ryan Gosling";
        Date birthDate = new GregorianCalendar(1995, 11, 12).getTime();
        String photo = "path";
        Actor newActor = new Actor(cpf, name, birthDate, photo);

        HibernateController.registerActor(newActor);

        Actor actor = HibernateController.searchActor(cpf);
        assertNotNull(actor);
        assertEquals(name, actor.getName());
        assertEquals(birthDate, actor.getBirthDate());
        assertEquals(photo, actor.getPhoto());
    }

    @Test
    public void testRegisterDirector() {
        String cpf = "000.000.000-02";
        String name = "Christopher Nolan";
        Date birthDate = new Date();
        String photo = "nolan_photo.jpg";
        Director newDirector = new Director(cpf, name, birthDate, photo);

        HibernateController.registerDirector(newDirector);

        Director director = HibernateController.searchDirector(cpf);
        assertNotNull(director);
        assertEquals(name, director.getName());
        assertEquals(birthDate, director.getBirthDate());
        assertEquals(photo, director.getPhoto());
    }

    @Test
    public void testRegisterMovie() {
        String title = "Inception";
        List<Actor> cast = new ArrayList<>();
        double grade = 9.2;
        Director director = new Director("000.000.000-02", "Christopher Nolan", new Date(), "nolan_photo.jpg");
        String description = "Dom Cobb é um ladrão com a rara habilidade de roubar segredos do inconsciente, obtidos durante o estado de sono. Impedido de retornar para sua família, ele recebe a oportunidade de se redimir ao realizar uma tarefa aparentemente impossível: plantar uma ideia na mente do herdeiro de um império.";
        String photo = "inception_poster.jpg";
        Movie newMovie = new Movie(title, cast, director, description, photo);

        HibernateController.registerMovie(newMovie);

        Movie movie = HibernateController.searchMovie(title);
        assertNotNull(movie);
        assertEquals(title, movie.getTitle());
        assertEquals(cast, movie.getCast());
        assertEquals(grade, movie.getGrade(), 0.01);
        assertEquals(director, movie.getDirector());
        assertEquals(description, movie.getDescription());
        assertEquals(photo, movie.getPhoto());
    }

    @Test
    public void testRegisterSeries() {
        String title = "Better Call Saul";
        List<Actor> cast = List.of(HibernateController.searchActor("000.000.000-01"));
        Director director = new Director("000.000.000-04", "Vince Gillian", new Date(), "bravovince.jpg");
        String description = "Jimmy McGill, também como conhecido como Saul Goodman, tenta ser um homem honesto e construir uma carreira de respeito. Mas há um lado seu que só quer aplicar golpes e se tornar um advogado picareta.";
        String photo = "saul3D.gif";
        int seasons = 6;
        int episodes = 63;
        double grade = 10;

        Series newSeries = new Series(title, cast, director, description, photo, seasons, episodes);
        HibernateController.registerSeries(newSeries);

        Series series = HibernateController.searchSeries(title);
        assertNotNull(series);
        assertEquals(title, series.getTitle());
        assertEquals(cast, series.getCast());
        assertEquals(grade, series.getGrade(), 0.01);
        assertEquals(director, series.getDirector());
        assertEquals(description, series.getDescription());
        assertEquals(photo, series.getPhoto());
        assertEquals(seasons, series.getSeasons());
        assertEquals(episodes, series.getEpisodes());
    }

    @Test
    public void testReviewSeries() {
        double newGrade = 9.5;
        String title = "Better Call Saul";

        boolean result = HibernateController.reviewSeries(newGrade, title);

        assertTrue(result);

        Series series = HibernateController.searchSeries(title);
        assertNotNull(series);
        assertEquals(newGrade, series.getGrade(), 0.01);
    }

    @Test
    public void testReviewMovie() {
        double newGrade = 8.5;
        String title = "Inception";

        boolean result = HibernateController.reviewMovie(newGrade, title);

        assertTrue(result);

        Movie movie = HibernateController.searchMovie(title);
        assertNotNull(movie);
        assertEquals(newGrade, movie.getGrade(), 0.01);
    }

    @Test
    public void testSearchActor() {
        String cpf = "000.000.000-01";
        Actor actor = HibernateController.searchActor(cpf);

        assertNotNull(actor);
        assertEquals("Ryan Gosling", actor.getName());
    }

    @Test
    public void testSearchDirector() {
        String cpf = "000.000.000-02";
        Director director = HibernateController.searchDirector(cpf);

        assertNotNull(director);
        assertEquals("Christopher Nolan", director.getName());
    }

    @Test
    public void testSearchMovie() {
        String title = "Inception";
        Movie movie = HibernateController.searchMovie(title);

        assertNotNull(movie);
        assertEquals("Christopher Nolan", movie.getDirector().getName());
    }

    @Test
    public void testSearchSeries() {
        String title = "Better Call Saul";
        Series series = HibernateController.searchSeries(title);

        assertNotNull(series);
        assertEquals("Vince Gillian", series.getDirector().getName());
    }

    @Test
    public void testRegisterUser() {
        String email = "user@example.com";
        String username = "User Name";
        String password = "password123";
        String photo = "user_photo.jpg";
        User user = new User(email, username, password, photo);

        boolean result = HibernateController.registerUser(user);

        assertTrue(result);

        User user1 = HibernateController.searchUser(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(photo, user.getPhoto());
    }

    @Test
    public void testLogin() {
        String email = "user@example.com";
        String correctPassword = "password123";
        String incorrectPassword = "wrongpassword";
        User user = new User(email, correctPassword, "User Name", "user_photo.jpg");

        HibernateController.registerUser(user);

        assertTrue(HibernateController.login(email, correctPassword));
        assertFalse(HibernateController.login(email, incorrectPassword));
    }
}
