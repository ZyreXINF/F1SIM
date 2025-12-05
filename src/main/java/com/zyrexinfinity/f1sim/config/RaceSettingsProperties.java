package com.zyrexinfinity.f1sim.config;

import com.zyrexinfinity.f1sim.enums.Gamemode;
import com.zyrexinfinity.f1sim.enums.Track;
import com.zyrexinfinity.f1sim.enums.Weather;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:race.properties")
@Component
@ConfigurationProperties(prefix = "race.default")
public class RaceSettingsProperties {

    private Track track;
    private Weather weather;
    private Gamemode gamemode;
    private double maxTimeDeviation;
    private double maxCircuitPaceDeviation;
    private double maxLapPaceDeviation;
    private double carPerformanceRatio;
    private double driverPerformanceRatio;
    private double crashRate;
    private double rainCrashRate;
    private double maxAddedCrashRate;
    private double engineFailureRate;
    private double maxAddedEngineFailureRate;
    private boolean equalBolidPerformance;

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

    public double getMaxAddedCrashRate() {
        return maxAddedCrashRate;
    }

    public void setMaxAddedCrashRate(double maxAddedCrashRate) {
        this.maxAddedCrashRate = maxAddedCrashRate;
    }

    public double getEngineFailureRate() {
        return engineFailureRate;
    }

    public void setEngineFailureRate(double engineFailureRate) {
        this.engineFailureRate = engineFailureRate;
    }

    public double getMaxAddedEngineFailureRate() {
        return maxAddedEngineFailureRate;
    }

    public void setMaxAddedEngineFailureRate(double maxAddedEngineFailureRate) {
        this.maxAddedEngineFailureRate = maxAddedEngineFailureRate;
    }

    public boolean isEqualBolidPerformance() {
        return equalBolidPerformance;
    }

    public void setEqualBolidPerformance(boolean equalBolidPerformance) {
        this.equalBolidPerformance = equalBolidPerformance;
    }
}
