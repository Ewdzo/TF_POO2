package org.example.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    String title;
    
    @ManyToMany
    @JoinTable(
        name = "ActorMedia",
		joinColumns = @JoinColumn(name = "media"),
	    inverseJoinColumns = @JoinColumn(name = "actor")
        )
    List<Actor> cast;

    double grade;
    Director director;
}
