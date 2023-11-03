package org.example.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    public String CPF;
    String name;
    Date birthDate;

    public Person(String CPF, String name, Date birthDate){
        this.CPF = CPF;
        this.name = name;
        this.birthDate = birthDate;
    }
}
