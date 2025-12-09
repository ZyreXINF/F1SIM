package com.zyrexinfinity.f1sim.service;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;
import com.zyrexinfinity.f1sim.domain.enums.RaceStatus;
import com.zyrexinfinity.f1sim.domain.factory.SessionFactory;
import com.zyrexinfinity.f1sim.domain.model.Driver;
import com.zyrexinfinity.f1sim.domain.model.RaceSession;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
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
        drivers = raceCalculationService.setStartingTyreCompounds(drivers, userRaceSettings);
        userRaceSession = raceSessionFactory.createRaceSession(userRaceSettings, drivers);
        userRaceSession.setRaceStatus(RaceStatus.READY);

        drivers.forEach((driver)->{
            System.out.println("\nDriver: " + driver.getFullName() +
                    "\nPace | Awareness | Overtaking | Tyre Management | Tyre Compound" +
                    "\n" + driver.getDriverStats().getDriverPace() + " | " + driver.getDriverStats().getDriverAwareness() + " | " + driver.getDriverStats().getOvertakingRating() + " | " + driver.getDriverStats().getTyreManagementRating() + " | "  + driver.getRaceStats().getTyres().getCompound());
        });

        return userRaceSession;
    }

    public RaceSession startRace() {
        if (!Objects.isNull(userRaceSession) && userRaceSession.getRaceStatus() == RaceStatus.READY) {
            userRaceSession.setRaceStatus(RaceStatus.RACING);
            userRaceSession.getDriversList().forEach(driver -> {
                driver.getRaceStats().setStatus(DriverStatus.RACING);
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