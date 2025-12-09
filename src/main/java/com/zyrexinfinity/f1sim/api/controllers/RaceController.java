package com.zyrexinfinity.f1sim.api.controllers;

import com.zyrexinfinity.f1sim.service.RaceService;
import com.zyrexinfinity.f1sim.domain.model.RaceSession;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class RaceController {
    @Autowired
    private RaceService raceService;

    @RequestMapping("/race")
    public String race(Model model, HttpSession httpSession){
        RaceSession userRaceSession = (RaceSession) httpSession.getAttribute("userRaceSession");
        if(Objects.isNull(userRaceSession)){
            userRaceSession = raceService.initRace((RaceSettings) httpSession.getAttribute("userRaceSettings"));
            httpSession.setAttribute("userRaceSession", userRaceSession);
            model.addAttribute("drivers", userRaceSession.getDriversList());
        }else{
            model.addAttribute("drivers", userRaceSession.getDriversList());
        }
        return "race.html";
    }
}
