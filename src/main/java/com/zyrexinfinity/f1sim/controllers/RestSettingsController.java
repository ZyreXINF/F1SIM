package com.zyrexinfinity.f1sim.controllers;

import com.fasterxml.jackson.core.JsonToken;
import com.zyrexinfinity.f1sim.enums.Gamemode;
import com.zyrexinfinity.f1sim.enums.Track;
import com.zyrexinfinity.f1sim.enums.Weather;
import com.zyrexinfinity.f1sim.model.RaceSettingsDTO;
import com.zyrexinfinity.f1sim.simulation.RaceSettings;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestSettingsController {
    @GetMapping("/{trackId}")
    public Track getTrackById(@PathVariable String trackId, HttpSession httpSession) {
        try {
            Track pickedTrack = Track.valueOf(trackId);
            RaceSettings userRaceSettings = (RaceSettings) httpSession.getAttribute("userRaceSettings");
            userRaceSettings.setTrack(pickedTrack);
            return pickedTrack;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track not found");
        }
    }

    @PostMapping("/setupSettings")
    public boolean setupRaceSettings(@RequestBody RaceSettingsDTO request, HttpSession httpSession){
        System.out.println("Received settings setup request: " + request);
        RaceSettings userSettings = (RaceSettings) httpSession.getAttribute("userRaceSettings");
        try{
            userSettings.setTrack(Track.valueOf(request.getTrackName()));
            userSettings.setGamemode(Gamemode.valueOf(request.getRaceMode()));
            userSettings.setWeather(Weather.valueOf(request.getWeather()));
            userSettings.setEqualBolidPerformance(request.isEqualPerformance());
            httpSession.setAttribute("userRaceSettings", userSettings);
            httpSession.setAttribute("userRaceSession", null);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Failed to setup a race settings"
            );
        }
    }
}
