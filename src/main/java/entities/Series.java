package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Series extends Media {
    public int seasons;
    public int episodes;

    public Series(String title, List<Actor> cast, Director director, String description, String photo, int seasons, int episodes) {
        super(title, cast, director, description, photo);
        this.seasons = seasons;
        this.episodes = episodes;
    };


    public int getSeasons() {
        return this.seasons;
    }

    public int getEpisodes() {
        return this.episodes;
    }
}
