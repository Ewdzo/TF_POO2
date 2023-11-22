package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

    double grade = 10;
    int reviews = 1;

    @ManyToOne
    Director director;
    String description;
    String photo;
    

    public Media(String title, List<Actor> cast, Director director, String description, String photo) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.description = description;
        this.photo = photo;
    }

    public void setGrade(double grade){
        reviews++;
        this.grade += ((grade - this.grade) / reviews );
    }

}
