package org.example.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    String email;
    
    String password;
    String username;
    String photo;
}
