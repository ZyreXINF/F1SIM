package com.zyrexinfinity.f1sim.domain.model;
import com.zyrexinfinity.f1sim.config.RaceSettingsProperties;
import com.zyrexinfinity.f1sim.domain.enums.Gamemode;
import com.zyrexinfinity.f1sim.domain.enums.Track;
import com.zyrexinfinity.f1sim.domain.enums.Weather;

public class RaceSettings{
    private Track track;
    private Weather weather;
    private Gamemode gamemode;
    private double maxTimeDeviation,
            maxCircuitPaceDeviation,
            maxLapPaceDeviation,
            carPerformanceRatio,
            driverPerformanceRatio,
            crashRate, rainCrashRate, engineFailureRate,
            maxAddedCrashRate, maxAddedEngineFailureRate,
            tyreDegradationRate;
    private boolean equalBolidPerformance;

    public RaceSettings(Track track, Weather weather, Gamemode gamemode, double maxTimeDeviation, double maxCircuitPaceDeviation, double maxLapPaceDeviation, double carPerformanceRatio, double driverPerformanceRatio, double crashRate, double rainCrashRate, double engineFailureRate, double maxAddedCrashRate, double maxAddedEngineFailureRate, double tyreDegradationRate, boolean equalBolidPerformance) {
        this.track = track;
        this.weather = weather;
        this.gamemode = gamemode;
        this.maxTimeDeviation = maxTimeDeviation;
        this.maxCircuitPaceDeviation = maxCircuitPaceDeviation;
        this.maxLapPaceDeviation = maxLapPaceDeviation;
        this.carPerformanceRatio = carPerformanceRatio;
        this.driverPerformanceRatio = driverPerformanceRatio;
        this.crashRate = crashRate;
        this.rainCrashRate = rainCrashRate;
        this.engineFailureRate = engineFailureRate;
        this.maxAddedCrashRate = maxAddedCrashRate;
        this.maxAddedEngineFailureRate = maxAddedEngineFailureRate;
        this.tyreDegradationRate = tyreDegradationRate;
        this.equalBolidPerformance = equalBolidPerformance;
    }

    public RaceSettings(RaceSettings other) {
        this.track = other.getTrack();
        this.weather = other.getWeather();
        this.gamemode = other.getGamemode();
        this.maxTimeDeviation = other.getMaxTimeDeviation();
        this.maxCircuitPaceDeviation = other.getMaxCircuitPaceDeviation();
        this.maxLapPaceDeviation = other.getMaxLapPaceDeviation();
        this.carPerformanceRatio = other.getCarPerformanceRatio();
        this.driverPerformanceRatio = other.getDriverPerformanceRatio();
        this.crashRate = other.getCrashRate();
        this.engineFailureRate = other.getEngineFailureRate();
        this.maxAddedCrashRate = other.getMaxAddedCrashRate();
        this.maxAddedEngineFailureRate = other.getMaxAddedEngineFailureRate();
        this.equalBolidPerformance = other.isEqualBolidPerformance();
        this.tyreDegradationRate = other.getTyreDegradationRate();
    }

    public RaceSettings(RaceSettingsProperties defaults) {
        this.track = defaults.getTrack();
        this.weather = defaults.getWeather();
        this.gamemode = defaults.getGamemode();
        this.maxTimeDeviation = defaults.getMaxTimeDeviation();
        this.maxCircuitPaceDeviation = defaults.getMaxCircuitPaceDeviation();
        this.maxLapPaceDeviation = defaults.getMaxLapPaceDeviation();
        this.carPerformanceRatio = defaults.getCarPerformanceRatio();
        this.driverPerformanceRatio = defaults.getDriverPerformanceRatio();
        this.crashRate = defaults.getCrashRate();
        this.engineFailureRate = defaults.getEngineFailureRate();
        this.maxAddedCrashRate = defaults.getMaxAddedCrashRate();
        this.maxAddedEngineFailureRate = defaults.getMaxAddedEngineFailureRate();
        this.tyreDegradationRate = defaults.getTyreDegradationRate();
        this.equalBolidPerformance = defaults.isEqualBolidPerformance();
    }

    @Override
    public String toString() {
        return "RaceSettings{" +
                "track=" + track +
                ", weather=" + weather +
                ", gamemode=" + gamemode +
                ", maxTimeDeviation=" + maxTimeDeviation +
                ", maxCircuitPaceDeviation=" + maxCircuitPaceDeviation +
                ", maxLapPaceDeviation=" + maxLapPaceDeviation +
                ", carPerformanceRatio=" + carPerformanceRatio +
                ", driverPerformanceRatio=" + driverPerformanceRatio +
                ", crashRate=" + crashRate +
                ", rainCrashRate=" + rainCrashRate +
                ", engineFailureRate=" + engineFailureRate +
                ", maxAddedCrashRate=" + maxAddedCrashRate +
                ", maxAddedEngineFailureRate=" + maxAddedEngineFailureRate +
                ", tyreDegradationRate=" + tyreDegradationRate +
                ", equalBolidPerformance=" + equalBolidPerformance +
                '}';
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }

    public void setGamemode(Gamemode gamemode) {
        this.gamemode = gamemode;
    }

    public double getMaxTimeDeviation() {
        return maxTimeDeviation;
    }

    public void setMaxTimeDeviation(double maxTimeDeviation) {
        this.maxTimeDeviation = maxTimeDeviation;
    }

    public double getMaxCircuitPaceDeviation() {
        return maxCircuitPaceDeviation;
    }

    public void setMaxCircuitPaceDeviation(double maxCircuitPaceDeviation) {
        this.maxCircuitPaceDeviation = maxCircuitPaceDeviation;
    }

    public double getMaxLapPaceDeviation() {
        return maxLapPaceDeviation;
    }

    public void setMaxLapPaceDeviation(double maxLapPaceDeviation) {
        this.maxLapPaceDeviation = maxLapPaceDeviation;
    }

    public double getCarPerformanceRatio() {
        return carPerformanceRatio;
    }

    public void setCarPerformanceRatio(double carPerformanceRatio) {
        this.carPerformanceRatio = carPerformanceRatio;
    }

    public double getDriverPerformanceRatio() {
        return driverPerformanceRatio;
    }

    public void setDriverPerformanceRatio(double driverPerformanceRatio) {
        this.driverPerformanceRatio = driverPerformanceRatio;
    }

    public double getCrashRate() {
        return crashRate;
    }

    public void setCrashRate(double crashRate) {
        this.crashRate = crashRate;
    }

    public double getRainCrashRate() {
        return rainCrashRate;
    }

    public void setRainCrashRate(double rainCrashRate) {
        this.rainCrashRate = rainCrashRate;
    }

    public double getEngineFailureRate() {
        return engineFailureRate;
    }

    public void setEngineFailureRate(double engineFailureRate) {
        this.engineFailureRate = engineFailureRate;
    }

    public double getMaxAddedCrashRate() {
        return maxAddedCrashRate;
    }

    public void setMaxAddedCrashRate(double maxAddedCrashRate) {
        this.maxAddedCrashRate = maxAddedCrashRate;
    }

    public double getMaxAddedEngineFailureRate() {
        return maxAddedEngineFailureRate;
    }

    public void setMaxAddedEngineFailureRate(double maxAddedEngineFailureRate) {
        this.maxAddedEngineFailureRate = maxAddedEngineFailureRate;
    }

    public double getTyreDegradationRate() {
        return tyreDegradationRate;
    }

    public void setTyreDegradationRate(double tyreDegradationRate) {
        this.tyreDegradationRate = tyreDegradationRate;
    }

    public boolean isEqualBolidPerformance() {
        return equalBolidPerformance;
    }

    public void setEqualBolidPerformance(boolean equalBolidPerformance) {
        this.equalBolidPerformance = equalBolidPerformance;
    }
}
