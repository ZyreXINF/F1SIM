package com.zyrexinfinity.f1sim.simulation;

import com.zyrexinfinity.f1sim.model.BolidEntity;

public class Bolid {
    private String bolidModel;

    private double bolidPace;
    private double reliability;

    public Bolid(BolidEntity bolidBlueprint) {
        this.bolidModel = bolidBlueprint.getBolidModel();
        this.bolidPace = bolidBlueprint.getAerodynamicRating();
        this.reliability = bolidBlueprint.getReliability();
    }

    @Override
    public String toString() {
        return "Bolid{" +
                "bolidModel='" + bolidModel + '\'' +
                ", bolidPace=" + bolidPace +
                ", reliability=" + reliability +
                '}';
    }

    public String getBolidModel() {
        return bolidModel;
    }

    public void setBolidModel(String bolidModel) {
        this.bolidModel = bolidModel;
    }

    public double getReliability() {
        return reliability;
    }

    public void setReliability(double reliability) {
        this.reliability = reliability;
    }

    public double getBolidPace() {
        return bolidPace;
    }

    public void setBolidPace(double bolidPace) {
        this.bolidPace = bolidPace;
    }
}
