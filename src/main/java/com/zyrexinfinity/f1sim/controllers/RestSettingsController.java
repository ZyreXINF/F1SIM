package com.zyrexinfinity.f1sim.controllers;

import com.zyrexinfinity.f1sim.enums.Track;
import com.zyrexinfinity.f1sim.services.RaceSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestSettingsController {
    @Autowired
    private RaceSettingsService raceSettingsService;

    @GetMapping("/{trackId}")
    public Track getTrackById(@PathVariable String trackId) {
        try {
            Track pickedTrack = Track.valueOf(trackId);
            raceSettingsService.getSettings().setTrack(pickedTrack);
            return pickedTrack;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Track not found");
        }
    }
}
