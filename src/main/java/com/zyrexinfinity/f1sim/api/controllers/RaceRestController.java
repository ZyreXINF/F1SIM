package com.zyrexinfinity.f1sim.api.controllers;

import com.zyrexinfinity.f1sim.domain.factory.SessionFactory;
import com.zyrexinfinity.f1sim.api.dto.SessionData;
import com.zyrexinfinity.f1sim.service.RaceService;
import com.zyrexinfinity.f1sim.domain.model.RaceSession;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class RaceRestController {
    @Autowired private RaceService raceService;
    @Autowired private SessionFactory sessionFactory;

    @PostMapping("/startRace")
    public void startRace(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("userRaceSession").hashCode());
        RaceSession updatedUserRaceSession = raceService.startRace();
        httpSession.setAttribute("userRaceSession", updatedUserRaceSession);
        System.out.println(httpSession.getAttribute("userRaceSession").hashCode());
    }

    @PostMapping("/restartRace")
    public void restartRace(HttpSession httpSession){
        raceService.initRace((RaceSettings) httpSession.getAttribute("userRaceSettings"));
        RaceSession updatedUserRaceSession = raceService.startRace();
        httpSession.setAttribute("userRaceSession", updatedUserRaceSession);
    }

    @GetMapping("/getSessionData")
    public SessionData getSessionData() {
        if (!Objects.isNull(raceService.getUserRaceSession())) {
            return sessionFactory.createSessionData(raceService.getUserRaceSession());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No active race session found"
            );
        }
    }
}