package com.zyrexinfinity.f1sim.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="driver")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    private String fullName;
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private ConstructorEntity team;

    private int driverAwareness;
    private int driverPace;
    private int overtakingRating;
    private int tyreManagementRating;

    @Override
    public String toString() {
        return "DriverEntity{" +
                "driverId=" + driverId +
                ", fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", team=" + team +
                ", driverAwareness=" + driverAwareness +
                ", driverPace=" + driverPace +
                ", overtakingRating=" + overtakingRating +
                ", tyreManagementRating=" + tyreManagementRating +
                '}';
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public ConstructorEntity getTeam() {
        return team;
    }

    public void setTeam(ConstructorEntity team) {
        this.team = team;
    }

    public int getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(int driverAwareness) {
        this.driverAwareness = driverAwareness;
    }

    public int getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(int driverPace) {
        this.driverPace = driverPace;
    }

    public int getOvertakingRating() {
        return overtakingRating;
    }

    public void setOvertakingRating(int overtakingRating) {
        this.overtakingRating = overtakingRating;
    }

    public int getTyreManagementRating() {
        return tyreManagementRating;
    }

    public void setTyreManagementRating(int tyreManagementRating) {
        this.tyreManagementRating = tyreManagementRating;
    }
}
