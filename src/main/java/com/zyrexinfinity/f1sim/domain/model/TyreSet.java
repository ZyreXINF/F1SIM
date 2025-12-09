package com.zyrexinfinity.f1sim.domain.model;

import com.zyrexinfinity.f1sim.domain.enums.TyreCompound;

public class TyreSet {
    private TyreCompound compound;
    private double tyreWear;
    private int tyreAge;

    public TyreSet(TyreCompound compound) {
        this.compound = compound;
        this.tyreWear = 0;
        this.tyreAge = 0;
    }

    @Override
    public String toString() {
        return "TyreSet{" +
                "compound=" + compound +
                ", tyreWear=" + tyreWear +
                ", tyreAge=" + tyreAge +
                '}';
    }

    public TyreCompound getCompound() {
        return compound;
    }

    public void setCompound(TyreCompound compound) {
        this.compound = compound;
    }

    public double getTyreWear() {
        return tyreWear;
    }

    public void setTyreWear(double tyreWear) {
        this.tyreWear = tyreWear;
    }

    public int getTyreAge() {
        return tyreAge;
    }

    public void setTyreAge(int tyreAge) {
        this.tyreAge = tyreAge;
    }
}
