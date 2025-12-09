package com.zyrexinfinity.f1sim.service;

import com.zyrexinfinity.f1sim.domain.enums.DriverStatus;
import com.zyrexinfinity.f1sim.domain.model.Driver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GridService {
    public List<Driver> setStartingPositions(List<Driver> rawGrid){
        List<Driver> positionedGrid = new ArrayList<>(rawGrid);
        for (int i = positionedGrid.size()-1; i >= 0; i--) {
            positionedGrid.get(i).getRaceStats().setRaceTime(i* 300L);
        }
        return positionedGrid;
    }

    public List<Driver> randomizeGrid(List<Driver> grid){
        Collections.shuffle(grid);
        return new ArrayList<>(grid);
    }

    public List<Driver> sortPositions(List<Driver> unsortedDriverList){
        List<Driver> sortedCopy = new ArrayList<>(unsortedDriverList);
        sortedCopy.sort((d1, d2) -> {
            boolean d1Dnf = d1.getRaceStats().getStatus() == DriverStatus.CrashDNF || d1.getRaceStats().getStatus() == DriverStatus.ReliabilityDNF || d1.getRaceStats().getStatus() == DriverStatus.DNS;
            boolean d2Dnf = d2.getRaceStats().getStatus() == DriverStatus.CrashDNF || d2.getRaceStats().getStatus() == DriverStatus.ReliabilityDNF || d2.getRaceStats().getStatus() == DriverStatus.DNS;

            if (!d1Dnf && !d2Dnf) {
                return Long.compare(d1.getRaceStats().getRaceTime(), d2.getRaceStats().getRaceTime());
            }

            if (d1Dnf && d2Dnf) {
                return Long.compare(d2.getRaceStats().getRaceTime(), d1.getRaceStats().getRaceTime());
            }

            return d1Dnf ? 1 : -1;
        });
        return sortedCopy;
    }
}
