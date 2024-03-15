package cz.pojisteniApp.spring.models;
import com.fasterxml.jackson.core.sym.Name;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.boot.model.relational.Sequence;

import java.time.LocalDate;

@Entity
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private String placeOfBirth;
    private String street;
    private String city;
    private String email;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {

        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
    }
    public String street() {
        return street;
    }
    public void setStreet(String street) {
        this.street=street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city=city;
    }

    public String getPlaceOfBirth() {
        return  placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
