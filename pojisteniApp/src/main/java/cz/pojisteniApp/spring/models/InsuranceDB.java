package cz.pojisteniApp.spring.models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class InsuranceDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long insuranceID;

    private String insuranceType;
    private int insuranceValue;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDB user;

    public long getInsuranceID() {
        return insuranceID;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public int getInsuranceValue() {
        return insuranceValue;
    }

    public UserDB getUser() {
        return user;
    }

    public void setInsuranceID(long insuranceID) {
        this.insuranceID = insuranceID;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public void setInsuranceValue(int insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }
}