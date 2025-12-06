package com.zyrexinfinity.f1sim.model;

public class RaceSettingsDTO {
    private String trackName;
    private String raceMode;
    private String weather;
    private boolean equalPerformance;

    @Override
    public String toString() {
        return "RaceSettingsDTO{" +
                "trackName='" + trackName + '\'' +
                ", raceMode='" + raceMode + '\'' +
                ", weather='" + weather + '\'' +
                ", equalPerformance=" + equalPerformance +
                '}';
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getRaceMode() {
        return raceMode;
    }

    public void setRaceMode(String raceMode) {
        this.raceMode = raceMode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public boolean isEqualPerformance() {
        return equalPerformance;
    }

    public void setEqualPerformance(boolean equalPerformance) {
        this.equalPerformance = equalPerformance;
    }
}
