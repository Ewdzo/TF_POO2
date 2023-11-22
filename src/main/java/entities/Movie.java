package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Movie extends Media {
    public Movie(String title, List<Actor> cast, double grade, Director director, String description, String photo) {
        super(title, cast, grade, director, description, photo);
    };
}
