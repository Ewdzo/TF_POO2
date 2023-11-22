package org.example.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class Media {
    @Id
    String title;
    
    @ManyToMany
    @JoinTable(
        name = "ActorMedia",
		joinColumns = @JoinColumn(name = "media"),
	    inverseJoinColumns = @JoinColumn(name = "actor")
        )
    List<Actor> cast;

    double grade;

    @OneToOne
    Director director;
    String description;
    String photo;

    public Media(String title, List<Actor> cast, double grade, Director director, String description, String photo) {
        this.title = title;
        this.cast = cast;
        this.grade = grade;
        this.director = director;
        this.description = description;
        this.photo = photo;
    }
}
