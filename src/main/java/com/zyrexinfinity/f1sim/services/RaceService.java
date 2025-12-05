package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.enums.*;
import com.zyrexinfinity.f1sim.factory.SessionFactory;
import com.zyrexinfinity.f1sim.simulation.Driver;
import com.zyrexinfinity.f1sim.simulation.RaceSession;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@SessionScope
public class RaceService {
    @Autowired private GridService gridService;
    @Autowired private DriverService driverService;
    @Autowired private RaceSettingsService raceSettingsService;
    @Autowired private RaceCalculationService raceCalculationService;
    @Autowired private SessionFactory raceSessionFactory;

    private ScheduledExecutorService scheduler;
    @Autowired private RaceSession userRaceSession;

    public RaceSession initRace(RaceSettings userRaceSettings) {
        List<Driver> drivers = driverService.fetchDrivers();
        drivers = gridService.randomizeGrid(drivers);
        drivers = gridService.setStartingPositions(drivers);
        if(Objects.isNull(userRaceSettings)){
            userRaceSettings = raceSettingsService.getDefaultSettings();
        }
        drivers = raceCalculationService.randomizeDriverCircuitPace(drivers, userRaceSettings);
        userRaceSession = raceSessionFactory.createRaceSession(userRaceSettings, drivers);
        userRaceSession.setRaceStatus(RaceStatus.READY);
        return userRaceSession;
    }

    public RaceSession startRace() {
        if (!Objects.isNull(userRaceSession) && userRaceSession.getRaceStatus() == RaceStatus.READY) {
            userRaceSession.setRaceStatus(RaceStatus.RACING);
            userRaceSession.getDriversList().forEach(driver -> {
                driver.setStatus(DriverStatus.RACING);
            });
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                if(!userRaceSession.update()){
                    shutdownScheduler();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
        return userRaceSession;
    }

    @PreDestroy
    private void shutdownScheduler() {
        scheduler.shutdown();
    }

    public RaceSession getUserRaceSession() {
        return userRaceSession;
    }
}