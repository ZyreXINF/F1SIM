package com.zyrexinfinity.f1sim.domain.model;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;
import com.zyrexinfinity.f1sim.domain.enums.RaceStatus;
import com.zyrexinfinity.f1sim.service.GridService;
import com.zyrexinfinity.f1sim.service.RaceCalculationService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Objects;

@Component
@SessionScope
public class RaceSession {
    //Services
    private RaceCalculationService calculationService;
    private GridService gridService;

    //Settings/Calculation-Related Variables
    private RaceSettings settings;
    private CalculationContext calculationContext;

    //Simulation-Related Variables
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

    public boolean update(){
        if(raceStatus == RaceStatus.RACING){
            driversList.forEach(driver -> {
                if(driver.getRaceStats().getStatus() == DriverStatus.RACING){
                    calculationContext.setDriver(driver);

                    //Calculation of DNF
                    DriverStatus status = calculationService.calculateStatus(calculationContext);

                    if(status == DriverStatus.RACING){
                        //Calculation of lap time
                        long calculatedLapTime = calculationService.calculateLapTime(calculationContext);
                        double tyreWear = calculationService.calculateTyreWear(calculationContext);
                        long driversFastestLap = driver.getRaceStats().getFastestLap();

                        //Personal Best Laptime
                        if (driversFastestLap <= 0 || calculatedLapTime < driversFastestLap) {
                            driver.getRaceStats().setFastestLap(calculatedLapTime);
                        }
                        //Overall Best Laptime
                        if (fastestLapTime <= 0 || calculatedLapTime < fastestLapTime) {
                            fastestLapTime = calculatedLapTime;
                            driversList.forEach(driver1 -> {driver1.getRaceStats().setSetFastestLap(false);});
                            driver.getRaceStats().setSetFastestLap(true);
                        }

                        driver.getRaceStats().setRaceTime(driver.getRaceStats().getRaceTime() + calculatedLapTime);
                        driver.getRaceStats().setCurrentLap(driver.getRaceStats().getCurrentLap()+1);
                        driver.getRaceStats().getTyres().setTyreAge(driver.getRaceStats().getTyres().getTyreAge()+1);
                        driver.getRaceStats().getTyres().setTyreWear(tyreWear);

                    }else{
                        driver.getRaceStats().setStatus(status);
                    }
                }
            });

            currentLap++;

            if(currentLap == settings.getTrack().getLapsNumber()){
                driversList.forEach(driver -> {
                    if(driver.getRaceStats().getStatus() == DriverStatus.RACING){
                        driver.getRaceStats().setStatus(DriverStatus.Finished);
                    }
                });
                stop();
            }

            driversList = gridService.sortPositions(driversList);
            return true;
        }
        return false;
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
