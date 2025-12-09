package com.zyrexinfinity.f1sim.domain.factory;

import com.zyrexinfinity.f1sim.api.dto.SessionData;
import com.zyrexinfinity.f1sim.service.GridService;
import com.zyrexinfinity.f1sim.service.RaceCalculationService;
import com.zyrexinfinity.f1sim.domain.model.Driver;
import com.zyrexinfinity.f1sim.domain.model.RaceSession;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionFactory {
    @Autowired
    private RaceCalculationService calc;
    @Autowired
    private GridService grid;

    public RaceSession createRaceSession(RaceSettings settings, List<Driver> drivers) {
        return new RaceSession(settings, drivers, calc, grid);
    }
    public SessionData createSessionData(RaceSession session){
        return new SessionData(session);
    }
    //create SessionData here
}
