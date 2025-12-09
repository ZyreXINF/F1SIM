package com.zyrexinfinity.f1sim.api.controllers;

import com.zyrexinfinity.f1sim.domain.enums.Track;
import com.zyrexinfinity.f1sim.service.GridService;
import com.zyrexinfinity.f1sim.service.RaceSettingsService;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class SettingsController {
    @Autowired
    private RaceSettingsService raceSettingsService;
    @Autowired
    private GridService gridService;

    @RequestMapping("/raceSetup")
    public String startMenu(Model model, HttpSession session){
        RaceSettings userSettings = (RaceSettings) session.getAttribute("userRaceSettings");
        if (Objects.isNull(userSettings)) {
            userSettings = raceSettingsService.getDefaultSettings();
            session.setAttribute("userRaceSettings", userSettings);
        }
        model.addAttribute("chunkedTracks", getChunkedTracksArray(4.0));
        model.addAttribute("track", userSettings.getTrack());
        return "raceSetup.html";
    }

    private Track[][] getChunkedTracksArray(double rowCapacity){
        Track[] tracks = Track.values();
        int rows = (int) (tracks.length / rowCapacity);
        final int overflow = (int) (tracks.length % rowCapacity);
        if(overflow != 0){
            rows++;
        }
        Track[][] chunkedTracks = new Track[rows][];
        for(int i = 0; i < rows; i++){
            if(overflow != 0 && i+1 == rows){
                chunkedTracks[i] = new Track[overflow];
            }else{
                chunkedTracks[i] = new Track[(int) rowCapacity];
            }
            for (int j = 0; j < chunkedTracks[i].length; j++){
                chunkedTracks[i][j] = tracks[j+(i*(int)rowCapacity)];
            }
        }
        return chunkedTracks;
    }
}
