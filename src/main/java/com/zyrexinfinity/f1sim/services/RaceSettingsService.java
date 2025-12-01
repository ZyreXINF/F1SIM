package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.factory.RaceSettingsFactory;
import com.zyrexinfinity.f1sim.simulation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceSettingsService {
    @Autowired
    private RaceSettingsFactory raceSettingsFactory;

    public RaceSettings getDefaultSettings(){
        return raceSettingsFactory.createDefaultSettings();
    }
}
