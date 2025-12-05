package com.zyrexinfinity.f1sim.services;

import com.zyrexinfinity.f1sim.enums.DriverStatus;
import com.zyrexinfinity.f1sim.simulation.Driver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GridService {

    public Map<String, Integer> getGridsList(){
        Map<String, Integer> grids = new TreeMap<>();
        grids.put("2025", 20);
        grids.put("2024", 20);
        grids.put("2023", 20);
        grids.put("2022", 20);
        grids.put("2021", 20);
        grids.put("2020", 20);
        grids.put("2019", 20);
        grids.put("2018", 20);
        grids.put("2017", 20);
        grids.put("2016", 22);
        grids.put("2015", 20);
        grids.put("2014", 22);
        grids.put("2013", 22);
        grids.put("2012", 24);
        grids.put("2011", 24);
        grids.put("2010", 24);
        grids.put("2009", 20);
        grids.put("2008", 22);
        grids.put("2007", 22);
        grids.put("2006", 22);
        grids.put("2005", 20);
        grids.put("2004", 20);
        grids.put("2003", 20);
        grids.put("2002", 20);
        grids.put("2001", 22);
        grids.put("2000", 22);
        return ((NavigableMap<String, Integer>) grids).descendingMap();
    }

    public List<Driver> setStartingPositions(List<Driver> rawGrid){
        List<Driver> positionedGrid = new ArrayList<>(rawGrid);
        for (int i = positionedGrid.size()-1; i >= 0; i--) {
            positionedGrid.get(i).setRaceTime(i* 300L);
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
            boolean d1Dnf = d1.getStatus() == DriverStatus.CrashDNF || d1.getStatus() == DriverStatus.ReliabilityDNF || d1.getStatus() == DriverStatus.DNS;
            boolean d2Dnf = d2.getStatus() == DriverStatus.CrashDNF || d2.getStatus() == DriverStatus.ReliabilityDNF || d2.getStatus() == DriverStatus.DNS;

            if (!d1Dnf && !d2Dnf) {
                return Long.compare(d1.getRaceTime(), d2.getRaceTime());
            }

            if (d1Dnf && d2Dnf) {
                return Long.compare(d2.getRaceTime(), d1.getRaceTime());
            }

            return d1Dnf ? 1 : -1;
        });
        return sortedCopy;
    }
}
