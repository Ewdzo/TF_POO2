package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @ManyToMany(fetch = FetchType.EAGER)
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
    
    public Media(){};

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

    public String getTitle() {
        return this.title;
    }

    public String getCast() {
        String castString = "";

        for (Actor actor : cast) {
            castString += actor.getName() + "  ";
        }
        return castString;
    }

    public double getGrade() {
        return this.grade;
    }

    public int getReviews() {
        return this.reviews;
    }

    public Director getDirector() {
        return this.director;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPhoto() {
        return this.photo;
    }
}
