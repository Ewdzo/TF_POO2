package org.example.entities;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Director extends Person{

    public Director(String CPF, String name, Date birthDate, String photo) {
        super(CPF, name, birthDate, photo);
    }
    
}
