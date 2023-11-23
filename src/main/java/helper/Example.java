package helper;

import java.util.GregorianCalendar;
import java.util.List;

import entities.Actor;
import entities.Director;
import entities.Movie;
import entities.Series;

public class Example {
    public static void populate() {
        Actor a1 = new Actor("000.000.000-01", "Ryan Gosling", new GregorianCalendar(2001, 11, 12).getTime(), "path");
        Actor a2 = new Actor("000.000.000-02", "Anne Hathaway", new GregorianCalendar(2001, 11, 12).getTime(), "path");
        
        Director d1 = new Director("000.000.000-03", "Denis Villenueve", new GregorianCalendar(2001, 11, 12).getTime(), "path");
        
        Movie m1 = new Movie("Dune 3", List.of(a1, a2), d1, "Filme sobre areia e minhoca, so que o 3", "path");
        Series s1 = new Series("Wandinha", List.of(a1, a2), d1, "Serie sobre tiktokers mágicas", "path", 1, 12);
    }
}
