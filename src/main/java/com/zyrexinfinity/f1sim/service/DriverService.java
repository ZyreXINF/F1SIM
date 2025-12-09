package com.zyrexinfinity.f1sim.service;

import com.zyrexinfinity.f1sim.persistence.repository.DriverRepo;
import com.zyrexinfinity.f1sim.domain.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private RaceSettingsService settingsService;

    public List<Driver> fetchDrivers(){
        try{
            List<Driver> drivers = new ArrayList<>();
            driverRepo.findAll().forEach((driverEntity) -> {
                drivers.add(new Driver(driverEntity));
            });
            return drivers;
        }catch (Exception e){
            return null;
        }
    }
}
