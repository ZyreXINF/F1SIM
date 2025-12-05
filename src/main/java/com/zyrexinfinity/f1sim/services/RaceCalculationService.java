package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.enums.DriverStatus;
import com.zyrexinfinity.f1sim.simulation.CalculationContext;
import com.zyrexinfinity.f1sim.simulation.Driver;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RaceCalculationService {
    public List<Driver> randomizeDriverCircuitPace(List<Driver> unmodifiedGrid, RaceSettings settings){
        List<Driver> modifiedGrid = new ArrayList<>(unmodifiedGrid);
        System.out.println(settings);
        for (Driver driver : modifiedGrid) {
            double modifiedDriverPace = normalize(clamp(
                    normalize(driver.getDriverPace()/100) +
                            (normalize(ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
                                    * settings.getMaxCircuitPaceDeviation())),
                    0,1));
            System.out.println(driver.getFullName() + " {basePace: " + driver.getDriverPace()
                    + "; finalizedPace: " + (modifiedDriverPace) + "}");
            driver.setDriverPace(modifiedDriverPace * 100);
        }
        return modifiedGrid;
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

        double driverPace = normalize(driver.getDriverPace()/100);
        double maxDriverPaceDeviation = settings.getMaxLapPaceDeviation();
        // DriverPace + Random * MaxRandomDeviation
        double modifiedDriverPace = normalize(clamp(driverPace +
                        (lapPaceRandomMultiplier *
                                maxDriverPaceDeviation),
                0,1));

        double modifiedBolidPace;
        if(!calculationContext.getSettings().isEqualBolidPerformance()){
            modifiedBolidPace = normalize(driver.getBolid().getBolidPace()/100);
        }else{
            modifiedBolidPace = 1;
        }

        double driverPerformanceRatio = settings.getDriverPerformanceRatio();
        double carPerformanceRatio = settings.getCarPerformanceRatio();
        // driverPerformanceRatio * (1-modifiedDriverPace) + carPerformanceRatio * (1 - aerodynamicRating)
        double score = normalize(driverPerformanceRatio * (1 - modifiedDriverPace) +
                carPerformanceRatio * (1 - modifiedBolidPace));

        double maxTimeDeviation = settings.getMaxTimeDeviation();
        //score * maxTimeDeviation
        double delta = normalize(clamp(score * maxTimeDeviation, 0, maxTimeDeviation));

        long baseSectorTime = settings.getTrack().getSectorTime(sectorNumber);
        //baseTime * (1 + delta)
        double projectedSectorTime = normalize(baseSectorTime * (1 + delta));
        projectedSectorTime = Math.max(projectedSectorTime, baseSectorTime);

        System.out.println(driver.getFullName() + " | Base Pace: " + driverPace +
                " | Pace: " + modifiedDriverPace +
                " | Score: " + score +
                " | Delta: " + delta +
                 " | SectorTime: " + projectedSectorTime);

        return Math.round(projectedSectorTime);
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
        double awareness = driver.getDriverAwareness()/100 * settings.getCrashRate();

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
