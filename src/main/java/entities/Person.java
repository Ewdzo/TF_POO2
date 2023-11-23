package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    public String CPF;
    
    String name;
    Date birthDate;
    String photo;

    public Person(String CPF, String name, Date birthDate, String photo){
        this.CPF = CPF;
        this.name = name;
        this.birthDate = birthDate;
        this.photo = photo;
    }

    public String getCPF() {
        return this.CPF;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getPhoto() {
        return this.photo;
    }
}
