package com.zyrexinfinity.f1sim.service;

import com.zyrexinfinity.f1sim.domain.factory.RaceSettingsFactory;
import com.zyrexinfinity.f1sim.domain.model.RaceSettings;
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
