package helper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;

public class Example {
    public static void populate() {
        Actor a1 = new Actor("000.000.000-01", "Ryan Gosling", new GregorianCalendar(1986, 11, 12).getTime(), "path");
        Actor a2 = new Actor("000.000.000-02", "Anne Hathaway", new GregorianCalendar(1985, 11, 12).getTime(), "path");
        Actor a3 = new Actor("000.000.000-03", "", new GregorianCalendar(2001, 11, 12).getTime(), "path");
        
        Director d1 = new Director("000.000.000-04", "Denis Villenueve", new GregorianCalendar(2001, 11, 12).getTime(), "path");
        
        Movie m1 = new Movie("Dune", List.of(a1, a2), d1, "Filme sobre areia e minhoca", "../assets/dune.jpeg");
        Movie m2 = new Movie("The Flash", List.of(a1, a2), d1, "Filme sobre areia e minhoca, so que o 3", "../assets/the_flash.jpeg");
        Movie m3 = new Movie("Spiderverse", List.of(a1, a2), d1, "Filme sobre areia e minhoca, so que o 3", "../assets/spiderverse.jpg");
        Movie m4 = new Movie("Whiplash", List.of(a1, a2), d1, "Filme sobre areia e minhoca, so que o 3", "../assets/whiplash.jpg");
        Movie m5 = new Movie("La La Land", List.of(a1, a2), d1, "Filme sobre areia e minhoca, so que o 3", "../assets/lalaland.jpg");

        Series s1 = new Series("Wandinha", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "../assets/wandinha.jpg", 1, 12);
        Series s2 = new Series("Breaking Bad", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "../assets/breakingbad.jpg", 1, 12);
        Series s3 = new Series("Better Call Saul", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "../assets/bettercallsaul.jpg", 1, 12);
        Series s4 = new Series("Peaky Blinders", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "../assets/peakyblinders.jpg", 1, 12);
        Series s5 = new Series("Atlanta", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "../assets/atlanta.jpg", 1, 12);

        HibernateController.registerActor(a1);
        HibernateController.registerActor(a2);
        HibernateController.registerActor(a3);

        HibernateController.registerDirector(d1);

        HibernateController.registerMovie(m1);
        HibernateController.registerMovie(m2);
        HibernateController.registerMovie(m3);
        HibernateController.registerMovie(m4);
        HibernateController.registerMovie(m5);

        HibernateController.registerSeries(s1);
        HibernateController.registerSeries(s2);
        HibernateController.registerSeries(s3);
        HibernateController.registerSeries(s4);
        HibernateController.registerSeries(s5);
    }
}
