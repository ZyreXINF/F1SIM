package com.zyrexinfinity.f1sim.domain.model;

public class DriverStats {
    private double driverAwareness;
    private double driverPace;
    private double overtakingRating;
    private double tyreManagementRating;

    public DriverStats(double driverAwareness, double driverPace, double overtakingRating, double tyreManagementRating) {
        this.driverAwareness = driverAwareness;
        this.driverPace = driverPace;
        this.overtakingRating = overtakingRating;
        this.tyreManagementRating = tyreManagementRating;
    }
    public DriverStats(){}

    @Override
    public String toString() {
        return "DriverStats{" +
                "driverAwareness=" + driverAwareness +
                ", driverPace=" + driverPace +
                ", overtakingRating=" + overtakingRating +
                ", tyreManagementRating=" + tyreManagementRating +
                '}';
    }

    public double getDriverAwareness() {
        return driverAwareness;
    }

    public void setDriverAwareness(double driverAwareness) {
        this.driverAwareness = driverAwareness;
    }

    public double getDriverPace() {
        return driverPace;
    }

    public void setDriverPace(double driverPace) {
        this.driverPace = driverPace;
    }

    public double getOvertakingRating() {
        return overtakingRating;
    }

    public void setOvertakingRating(double overtakingRating) {
        this.overtakingRating = overtakingRating;
    }

    public double getTyreManagementRating() {
        return tyreManagementRating;
    }

    public void setTyreManagementRating(double tyreManagementRating) {
        this.tyreManagementRating = tyreManagementRating;
    }
}
