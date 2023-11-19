package org.example.entities;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Actor extends Person {

    public Actor(String CPF, String name, Date birthDate, String photo) {
        super(CPF, name, birthDate, photo);
    }
}
