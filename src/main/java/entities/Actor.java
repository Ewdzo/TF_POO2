package entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Actor extends Person {

    public Actor(String CPF, String name, Date birthDate, String photo) {
        super(CPF, name, birthDate, photo);
    }
}
