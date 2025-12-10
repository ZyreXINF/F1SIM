package com.zyrexinfinity.f1sim.domain.enums;

public enum TyreCompound {
    SOFT(1.0,1.85),
    MEDIUM(1.0075,1.25),
    HARD(1.01,1.0),
    INTERMEDIATE(1.0075,1.0),
    WET(1.125,1.0);

    private final double tyrePerformance, tyreDegradationRate;

    TyreCompound(double tyrePerformance, double tyreDegradationRate) {
        this.tyrePerformance = tyrePerformance;
        this.tyreDegradationRate = tyreDegradationRate;
    }

    public double getTyrePerformance() {
        return tyrePerformance;
    }

    public double getTyreDegradationRate() {
        return tyreDegradationRate;
    }
}
