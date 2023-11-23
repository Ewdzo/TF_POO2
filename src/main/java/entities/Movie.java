package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Movie extends Media {
    public Movie(String title, List<Actor> cast, Director director, String description, String photo) {
        super(title, cast, director, description, photo);
    };
     
    public Movie(){};
}
