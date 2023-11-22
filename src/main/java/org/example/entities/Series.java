package org.example.entities;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Series extends Media {
    public int seasons;
    public int episodes;

    public Series(String title, List<Actor> cast, double grade, Director director, String description, String photo, int seasons, int episodes) {
        super(title, cast, grade, director, description, photo);
        this.seasons = seasons;
        this.episodes = episodes;
    };

}
