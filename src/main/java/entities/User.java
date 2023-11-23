package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPhoto() {
        return this.photo;
    }
}