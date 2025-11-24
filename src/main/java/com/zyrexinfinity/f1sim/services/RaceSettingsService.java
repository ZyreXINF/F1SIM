package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.factory.RaceSettingsFactory;
import com.zyrexinfinity.f1sim.simulation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceSettingsService {
    private RaceSettingsFactory raceSettingsFactory;
    private RaceSettings settings;

    @Autowired
    public RaceSettingsService(RaceSettingsFactory raceSettingsFactory) {
        this.raceSettingsFactory = raceSettingsFactory;
        this.settings = raceSettingsFactory.createDefaultSettings();  // <-- auto–initialize here
    }

    public RaceSettings getDefaultSettings(){
        return raceSettingsFactory.createDefaultSettings();
    }
    public RaceSettings getSettings() {
        return settings;
    }
    public void setSettings(RaceSettings settings) {
        this.settings = settings;
    }
}
