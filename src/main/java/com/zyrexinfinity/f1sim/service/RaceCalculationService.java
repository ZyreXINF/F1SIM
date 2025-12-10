package com.zyrexinfinity.f1sim.service;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;
import com.zyrexinfinity.f1sim.domain.enums.TyreCompound;
import com.zyrexinfinity.f1sim.domain.enums.Weather;
import com.zyrexinfinity.f1sim.domain.model.CalculationContext;
import com.zyrexinfinity.f1sim.domain.model.Driver;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
import com.zyrexinfinity.f1sim.domain.model.TyreSet;
import org.apache.catalina.webresources.DirResourceSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RaceCalculationService {
    public List<Driver> randomizeDriverCircuitPace(List<Driver> unmodifiedGrid, RaceSettings settings){
        List<Driver> modifiedGrid = new ArrayList<>(unmodifiedGrid);
        for (Driver driver : modifiedGrid) {
            double modifiedDriverPace = normalize(clamp(
                    normalize(driver.getDriverStats().getDriverPace()/100) +
                            (normalize(ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
                                    * settings.getMaxCircuitPaceDeviation())),
                    0,1));
            driver.getDriverStats().setDriverPace(normalize(modifiedDriverPace * 100));
        }
        return modifiedGrid;
    }
    public List<Driver> setStartingTyreCompounds(List<Driver> unmodifiedGrid, RaceSettings settings){
        List<Driver> modifiedGrid = new ArrayList<>(unmodifiedGrid);
        switch (settings.getWeather()) {
            case Weather.CLEAR:
                modifiedGrid.forEach(driver -> {
                    driver.getRaceStats().setTyres(new TyreSet(pickDryCompound()));
                });
                break;

            case Weather.RAIN:
                modifiedGrid.forEach(driver -> {
                    driver.getRaceStats().setTyres(new TyreSet(TyreCompound.INTERMEDIATE));
                });
                break;
            case Weather.HEAVY_RAIN:
                modifiedGrid.forEach(driver -> {
                    driver.getRaceStats().setTyres(new TyreSet(TyreCompound.WET));
                });
                break;
        }

        return modifiedGrid;
    }
    public TyreCompound pickDryCompound() {
        int roll = ThreadLocalRandom.current().nextInt(100);
        if (roll <= 30) {
            return TyreCompound.SOFT;
        } else if (roll > 30 && roll <= 75) {
            return TyreCompound.MEDIUM;
        }else{
            return TyreCompound.HARD;
        }
    }

    public long calculateLapTime(CalculationContext calculationContext){
        long lapTime = 0L;
        double lapPaceRandomMultiplier = normalize(ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
        for(int i = 1; i <= 3; i++){
            lapTime += calculateSectorTime(i, lapPaceRandomMultiplier, calculationContext);
        }
        return lapTime;
    }
    private long calculateSectorTime(int sectorNumber, double lapPaceRandomMultiplier, CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();
        double driverPace = normalize(driver.getDriverStats().getDriverPace()/100);
        double maxDriverPaceDeviation = settings.getMaxLapPaceDeviation();
        double driverPerformanceRatio = settings.getDriverPerformanceRatio();
        double carPerformanceRatio = settings.getCarPerformanceRatio();
        double maxTimeDeviation = settings.getMaxTimeDeviation();
        long baseSectorTime = settings.getTrack().getSectorTime(sectorNumber);


        double modifiedDriverPace = normalize(clamp(driverPace +
                        (lapPaceRandomMultiplier *
                                maxDriverPaceDeviation),
                0,1));
        double modifiedBolidPace = settings.isEqualBolidPerformance() ?
                1 : normalize(driver.getBolid().getBolidPace() / 100);

        double score = normalize(driverPerformanceRatio * (1 - modifiedDriverPace) +
                carPerformanceRatio * (1 - modifiedBolidPace));
        double delta = normalize(clamp(score * maxTimeDeviation, 0, maxTimeDeviation));

        double projectedSectorTime = normalize(baseSectorTime * (1 + delta));
        projectedSectorTime = Math.max(projectedSectorTime, baseSectorTime);

        TyreSet tyres = driver.getRaceStats().getTyres();
        double tyreWear = clamp(tyres.getTyreWear(), 0, 1);
        double performanceMultiplier = tyres.getCompound().getTyrePerformance();
        projectedSectorTime *= performanceMultiplier;

        double wearPenalty = 1.0 + (tyreWear * tyreWear * 0.05);
        projectedSectorTime *= wearPenalty;

//        System.out.println(driver.getFullName() +
//                " | D: " + driverPace +
//                " | Dm " + modifiedDriverPace +
//                " | B: " + driverPace +
//                " | Bm " + modifiedBolidPace +
//                 " | T: " + projectedSectorTime);

        return Math.round(projectedSectorTime);
    }

    public double calculateTyreWear(CalculationContext calculationContext) {
        RaceSettings raceSettings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();
        TyreSet tyres = driver.getRaceStats().getTyres();
        double baseWear = raceSettings.getTyreDegradationRate();
        double compoundMultiplier = tyres.getCompound().getTyreDegradationRate();
        double tyreManagementRating = normalize(driver.getDriverStats().getTyreManagementRating() / 100.0);

        double driverEffect = 1.0 - (tyreManagementRating * 0.5);
        double wearThisLap = baseWear * compoundMultiplier * driverEffect;
        double calculatedWear = tyres.getTyreWear() + wearThisLap;

        return normalize(clamp(calculatedWear, 0.0, 1.0));
    }

    public boolean calculatePitProbability(CalculationContext calculationContext) {
        Driver driver = calculationContext.getDriver();

        double wear = driver.getRaceStats().getTyres().getTyreWear();
        int currentLap = driver.getRaceStats().getCurrentLap();
        int totalLaps = calculationContext.getSettings().getTrack().getLapsNumber();

        if (currentLap >= totalLaps * 0.90) {
            return false;
        }

        if (wear >= 0.35) {
            return true;
        } else if (wear >= 0.30) {
            return ThreadLocalRandom.current().nextDouble() < 0.30;
        } else if (wear >= 0.25) {
            return ThreadLocalRandom.current().nextDouble() < 0.125;
        } else {
            return false;
        }
    }
    public long calculatePitStopTime() {
        double baseSeconds = 2.2;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double roll = random.nextDouble();  // 0.0–1.0
        double finalSeconds;

        if (roll <= 0.05) {
            finalSeconds = 6.0 + random.nextDouble() * 6.0;
        } else if (roll > 0.05 && roll < 0.15) {
            finalSeconds = 3.0 + random.nextDouble() * 3.0;
        } else {
            finalSeconds = 1.8 + random.nextDouble() * 1.2;
        }

        long millis = Math.round(finalSeconds * 1000);
        return millis;
    }

    private double clamp(double value, double minRangeBorder, double maxRangeBorder){
        if(value < minRangeBorder){
            return minRangeBorder;
        }else if(value > maxRangeBorder){
            return maxRangeBorder;
        }return value;
    }
    private double normalize(double value){
        return (double) Math.round(value * 100000) /100000;
    }

    public DriverStatus calculateStatus(CalculationContext calculationContext){
        if(calculateEngineFailure(calculationContext)){
            return DriverStatus.ReliabilityDNF;
        }else if(calculateCrash(calculationContext)){
            return DriverStatus.CrashDNF;
        }
        return DriverStatus.RACING;
    }
    private boolean calculateCrash(CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();
        double awareness = driver.getDriverStats().getDriverAwareness()/100 * settings.getCrashRate();

        double baseChance = calculationContext.getSettings().getCrashRate();
        double maxAdded = calculationContext.getSettings().getMaxAddedCrashRate();
        double chance = baseChance + (maxAdded * awareness);
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0) <= chance;
    }
    private boolean calculateEngineFailure(CalculationContext calculationContext){
        RaceSettings settings = calculationContext.getSettings();
        Driver driver = calculationContext.getDriver();

        double reliability;
        if(!calculationContext.getSettings().isEqualBolidPerformance()){
            reliability = driver.getBolid().getReliability()/100 * settings.getEngineFailureRate();
        }else{
            reliability = 1;
        }

        double baseChance = calculationContext.getSettings().getEngineFailureRate();
        double maxAdded = calculationContext.getSettings().getMaxAddedEngineFailureRate();
        double chance = baseChance + (maxAdded * reliability);
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0) <= chance;
    }
}
