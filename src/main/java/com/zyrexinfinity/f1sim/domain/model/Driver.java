package com.zyrexinfinity.f1sim.domain.model;

import com.zyrexinfinity.f1sim.persistence.entity.DriverEntity;

public class Driver {
    private String fullName;
    private String nationality;

    private Bolid bolid;
    private Team team;

    private DriverStats driverStats;
    private RaceStats raceStats;

    public Driver(DriverEntity driverBluePrint) {
        this.fullName = driverBluePrint.getFullName();
        this.nationality = driverBluePrint.getNationality();

        this.bolid = new Bolid(driverBluePrint.getTeam().getBolid());
        this.team = new Team(driverBluePrint.getTeam());

        this.driverStats = new DriverStats(driverBluePrint.getDriverAwareness(),
                driverBluePrint.getDriverPace(),
                driverBluePrint.getOvertakingRating(),
                driverBluePrint.getTyreManagementRating());
        this.raceStats = new RaceStats();
    }
    public Driver(){}

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", bolid=" + bolid +
                ", team=" + team +
                ", driverStats=" + driverStats +
                ", raceStats=" + raceStats +
                '}';
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

    public Bolid getBolid() {
        return bolid;
    }

    public void setBolid(Bolid bolid) {
        this.bolid = bolid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public DriverStats getDriverStats() {
        return driverStats;
    }

    public void setDriverStats(DriverStats driverStats) {
        this.driverStats = driverStats;
    }

    public RaceStats getRaceStats() {
        return raceStats;
    }

    public void setRaceStats(RaceStats raceStats) {
        this.raceStats = raceStats;
    }
}
