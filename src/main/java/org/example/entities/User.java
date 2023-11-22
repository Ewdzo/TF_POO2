package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    public String email;
    
    public String password;
    public String username;
    public String photo;

    public User(String email, String password, String username, String photo) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.photo = photo;
    }
}
