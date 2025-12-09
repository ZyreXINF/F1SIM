package com.zyrexinfinity.f1sim.domain.model;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;

public class RaceStats {
    private int currentLap;
    private long raceTime;
    private long fastestLap;

    private DriverStatus status;
    private TyreSet tyres;

    private boolean setFastestLap;

    public RaceStats(TyreSet tyres) {
        this.tyres = tyres;

        this.status = DriverStatus.DNS;

        this.currentLap = 0;
        this.raceTime = 0;
        this.fastestLap = 0;

        this.setFastestLap = false;
    }
    public RaceStats(){
        this.status = DriverStatus.DNS;

        this.currentLap = 0;
        this.raceTime = 0;
        this.fastestLap = 0;

        this.setFastestLap = false;
    }

    @Override
    public String toString() {
        return "RaceStats{" +
                "currentLap=" + currentLap +
                ", raceTime=" + raceTime +
                ", fastestLap=" + fastestLap +
                ", status=" + status +
                ", tyres=" + tyres +
                ", setFastestLap=" + setFastestLap +
                '}';
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public long getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(long raceTime) {
        this.raceTime = raceTime;
    }

    public long getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(long fastestLap) {
        this.fastestLap = fastestLap;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public TyreSet getTyres() {
        return tyres;
    }

    public void setTyres(TyreSet tyres) {
        this.tyres = tyres;
    }

    public boolean isSetFastestLap() {
        return setFastestLap;
    }

    public void setSetFastestLap(boolean setFastestLap) {
        this.setFastestLap = setFastestLap;
    }
}
