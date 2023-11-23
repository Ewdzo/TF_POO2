package entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Director extends Person {

    public Director(){};

    public Director(String CPF, String name, Date birthDate, String photo) {
        super(CPF, name, birthDate, photo);
    }
    
}
