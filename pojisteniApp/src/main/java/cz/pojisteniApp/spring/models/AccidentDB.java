package cz.pojisteniApp.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class AccidentDB {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long incidentID;
    private LocalDate dateOfIncident;
    private String placeOfIncident;
    private String descriptionIncident;
    private String insuranceType;

    public LocalDate getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(LocalDate dateOfIncident) {
        this.dateOfIncident = dateOfIncident;

    }

    public String getPlaceOfIncident() {
        return placeOfIncident;
    }

    public void setPlaceOfIncident(String placeOfIncident) {
        this.placeOfIncident = placeOfIncident;
    }

    public String getDescriptionIncident() {
        return descriptionIncident;
    }

    public void setDescriptionIncident(String descriptionIncident) {
        this.descriptionIncident = descriptionIncident;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType=insuranceType;
    }
}