package com.zyrexinfinity.f1sim.simulation;
import com.zyrexinfinity.f1sim.config.RaceSettingsProperties;
import com.zyrexinfinity.f1sim.enums.Gamemode;
import com.zyrexinfinity.f1sim.enums.Track;
import com.zyrexinfinity.f1sim.enums.Weather;

public class RaceSettings{
    private Track track;
    private Weather weather;
    private Gamemode gamemode;
    private double maxTimeDeviation,
            maxCircuitPaceDeviation,
            maxLapPaceDeviation,
            carPerformanceRatio,
            driverPerformanceRatio,
            driverPaceModifier,
            aerodynamicRatingModifier,
            crashRate, engineFailureRate,
            spinOutModifier, rainSpinoutModifier,
            baseCrashRate, maxAddedCrashRate,
            baseEngineFailureRate, maxAddedEngineFailureRate;
    private String gridYear;

    public RaceSettings(){}

    public RaceSettings(Track track, Weather weather, Gamemode gamemode, double maxTimeDeviation, double maxCircuitPaceDeviation, double maxLapPaceDeviation, double carPerformanceRatio, double driverPerformanceRatio, double driverPaceModifier, double aerodynamicRatingModifier, double crashRate, double engineFailureRate, double spinOutModifier, double rainSpinoutModifier, double baseCrashRate, double maxAddedCrashRate, double baseEngineFailureRate, double maxAddedEngineFailureRate, String gridYear) {
        this.track = track;
        this.weather = weather;
        this.gamemode = gamemode;
        this.maxTimeDeviation = maxTimeDeviation;
        this.maxCircuitPaceDeviation = maxCircuitPaceDeviation;
        this.maxLapPaceDeviation = maxLapPaceDeviation;
        this.carPerformanceRatio = carPerformanceRatio;
        this.driverPerformanceRatio = driverPerformanceRatio;
        this.driverPaceModifier = driverPaceModifier;
        this.aerodynamicRatingModifier = aerodynamicRatingModifier;
        this.crashRate = crashRate;
        this.engineFailureRate = engineFailureRate;
        this.spinOutModifier = spinOutModifier;
        this.rainSpinoutModifier = rainSpinoutModifier;
        this.baseCrashRate = baseCrashRate;
        this.maxAddedCrashRate = maxAddedCrashRate;
        this.baseEngineFailureRate = baseEngineFailureRate;
        this.maxAddedEngineFailureRate = maxAddedEngineFailureRate;
        this.gridYear = gridYear;
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
        this.driverPaceModifier = other.getDriverPaceModifier();
        this.aerodynamicRatingModifier = other.getAerodynamicRatingModifier();
        this.crashRate = other.getCrashRate();
        this.engineFailureRate = other.getEngineFailureRate();
        this.spinOutModifier = other.getSpinOutModifier();
        this.rainSpinoutModifier = other.getRainSpinoutModifier();
        this.baseCrashRate = other.getBaseCrashRate();
        this.maxAddedCrashRate = other.getMaxAddedCrashRate();
        this.baseEngineFailureRate = other.getBaseEngineFailureRate();
        this.maxAddedEngineFailureRate = other.getMaxAddedEngineFailureRate();
        this.gridYear = other.getGridYear();
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
        this.driverPaceModifier = defaults.getDriverPaceModifier();
        this.aerodynamicRatingModifier = defaults.getAerodynamicRatingModifier();
        this.crashRate = defaults.getCrashRate();
        this.engineFailureRate = defaults.getEngineFailureRate();
        this.spinOutModifier = defaults.getSpinOutModifier();
        this.rainSpinoutModifier = defaults.getRainSpinoutModifier();
        this.baseCrashRate = defaults.getBaseCrashRate();
        this.maxAddedCrashRate = defaults.getMaxAddedCrashRate();
        this.baseEngineFailureRate = defaults.getBaseEngineFailureRate();
        this.maxAddedEngineFailureRate = defaults.getMaxAddedEngineFailureRate();
        this.gridYear = defaults.getGridYear();
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
                ", driverPaceModifier=" + driverPaceModifier +
                ", aerodynamicRatingModifier=" + aerodynamicRatingModifier +
                ", crashRate=" + crashRate +
                ", engineFailureRate=" + engineFailureRate +
                ", spinOutModifier=" + spinOutModifier +
                ", rainSpinoutModifier=" + rainSpinoutModifier +
                ", baseCrashRate=" + baseCrashRate +
                ", maxAddedCrashRate=" + maxAddedCrashRate +
                ", baseEngineFailureRate=" + baseEngineFailureRate +
                ", maxAddedEngineFailureRate=" + maxAddedEngineFailureRate +
                ", gridYear='" + gridYear + '\'' +
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

    public double getDriverPaceModifier() {
        return driverPaceModifier;
    }

    public void setDriverPaceModifier(double driverPaceModifier) {
        this.driverPaceModifier = driverPaceModifier;
    }

    public double getAerodynamicRatingModifier() {
        return aerodynamicRatingModifier;
    }

    public void setAerodynamicRatingModifier(double aerodynamicRatingModifier) {
        this.aerodynamicRatingModifier = aerodynamicRatingModifier;
    }

    public double getCrashRate() {
        return crashRate;
    }

    public void setCrashRate(double crashRate) {
        this.crashRate = crashRate;
    }

    public double getEngineFailureRate() {
        return engineFailureRate;
    }

    public void setEngineFailureRate(double engineFailureRate) {
        this.engineFailureRate = engineFailureRate;
    }

    public double getSpinOutModifier() {
        return spinOutModifier;
    }

    public void setSpinOutModifier(double spinOutModifier) {
        this.spinOutModifier = spinOutModifier;
    }

    public double getRainSpinoutModifier() {
        return rainSpinoutModifier;
    }

    public void setRainSpinoutModifier(double rainSpinoutModifier) {
        this.rainSpinoutModifier = rainSpinoutModifier;
    }

    public double getBaseCrashRate() {
        return baseCrashRate;
    }

    public void setBaseCrashRate(double baseCrashRate) {
        this.baseCrashRate = baseCrashRate;
    }

    public double getMaxAddedCrashRate() {
        return maxAddedCrashRate;
    }

    public void setMaxAddedCrashRate(double maxAddedCrashRate) {
        this.maxAddedCrashRate = maxAddedCrashRate;
    }

    public double getBaseEngineFailureRate() {
        return baseEngineFailureRate;
    }

    public void setBaseEngineFailureRate(double baseEngineFailureRate) {
        this.baseEngineFailureRate = baseEngineFailureRate;
    }

    public double getMaxAddedEngineFailureRate() {
        return maxAddedEngineFailureRate;
    }

    public void setMaxAddedEngineFailureRate(double maxAddedEngineFailureRate) {
        this.maxAddedEngineFailureRate = maxAddedEngineFailureRate;
    }

    public String getGridYear() {
        return gridYear;
    }

    public void setGridYear(String gridYear) {
        this.gridYear = gridYear;
    }
}
