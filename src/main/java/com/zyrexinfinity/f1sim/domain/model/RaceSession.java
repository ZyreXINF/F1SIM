package com.zyrexinfinity.f1sim.domain.model;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;
import com.zyrexinfinity.f1sim.domain.enums.RaceStatus;
import com.zyrexinfinity.f1sim.domain.enums.TyreCompound;
import com.zyrexinfinity.f1sim.domain.enums.Weather;
import com.zyrexinfinity.f1sim.service.GridService;
import com.zyrexinfinity.f1sim.service.RaceCalculationService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class RaceSession {
    private RaceCalculationService calculationService;
    private GridService gridService;

    private RaceSettings settings;
    private CalculationContext calculationContext;

    private RaceStatus raceStatus;
    private int currentLap;
    private long fastestLapTime;
    private List<Driver> driversList;

    public RaceSession(RaceSettings settings, List<Driver> grid,
                       RaceCalculationService calc,
                       GridService gridService) {
        this.settings = settings;
        this.calculationContext = new CalculationContext(settings);
        this.driversList = grid;
        this.calculationService = calc;
        this.gridService = gridService;
        this.currentLap = grid.get(0).getRaceStats().getCurrentLap();
        this.fastestLapTime = 0;
    }

    public boolean update() {
        if (raceStatus != RaceStatus.RACING) return false;

        driversList.forEach(this::processDriver);
        currentLap++;

        if (currentLap == settings.getTrack().getLapsNumber()) {
            finishAllRacingDrivers();
            stop();
        }

        driversList = gridService.sortPositions(driversList);
        return true;
    }
    private void processDriver(Driver driver) {
        var stats = driver.getRaceStats();

        if (stats.getStatus() != DriverStatus.RACING) return;

        calculationContext.setDriver(driver);
        DriverStatus newStatus = calculationService.calculateStatus(calculationContext);

        if (newStatus != DriverStatus.RACING) {
            stats.setStatus(newStatus);
            return;
        }

        handleLapProgress(driver, stats);
    }
    private void handleLapProgress(Driver driver, RaceStats stats) {
        long lapTime = calculationService.calculateLapTime(calculationContext);
        double tyreWear = calculationService.calculateTyreWear(calculationContext);

        updateFastestLaps(driver, stats, lapTime);

        stats.setRaceTime(stats.getRaceTime() + lapTime);
        stats.setCurrentLap(stats.getCurrentLap() + 1);
        stats.getTyres().incrementAge();
        stats.getTyres().setTyreWear(tyreWear);

        if (calculationService.calculatePitProbability(calculationContext)) {
            performPitStop(driver, stats, tyreWear);
        }
    }

    private void updateFastestLaps(Driver driver, RaceStats stats, long lapTime) {
        long personalBest = stats.getFastestLap();
        if (personalBest <= 0 || lapTime < personalBest) {
            stats.setFastestLap(lapTime);
        }

        if (fastestLapTime <= 0 || lapTime < fastestLapTime) {
            fastestLapTime = lapTime;

            driversList.forEach(d -> d.getRaceStats().setSetFastestLap(false));
            stats.setSetFastestLap(true);
        }
    }

    private void performPitStop(Driver driver, RaceStats stats, double tyreWear) {
        long pitTime = calculationService.calculatePitStopTime()
                + settings.getTrack().getDriveTroughTime();

        stats.setRaceTime(stats.getRaceTime() + pitTime);

        TyreCompound newCompound = pickCompound(settings.getWeather());
        stats.setTyres(new TyreSet(newCompound));

        System.out.println(driver.getFullName() + " pitted for " + newCompound +
                " | tyreWear: " + tyreWear + " | pitTime: " + pitTime +
                " | lap: " + currentLap);
    }
    private TyreCompound pickCompound(Weather weather) {
        if (weather == Weather.CLEAR) return calculationService.pickDryCompound();
        if (weather == Weather.RAIN) return TyreCompound.INTERMEDIATE;
        return TyreCompound.WET;
    }

    private void finishAllRacingDrivers() {
        driversList.forEach(driver -> {
            if (driver.getRaceStats().getStatus() == DriverStatus.RACING) {
                driver.getRaceStats().setStatus(DriverStatus.Finished);
            }
        });
    }

    private void stop(){
        raceStatus = RaceStatus.FINISHED;
    }

    public RaceStatus getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(RaceStatus raceStatus) {
        this.raceStatus = raceStatus;
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public RaceSettings getSettings() {
        return settings;
    }
}
