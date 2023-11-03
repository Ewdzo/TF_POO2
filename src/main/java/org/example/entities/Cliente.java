package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
};
