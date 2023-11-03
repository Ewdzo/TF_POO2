package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class Series extends Media {
    int seasons;
    int episodes;
}
