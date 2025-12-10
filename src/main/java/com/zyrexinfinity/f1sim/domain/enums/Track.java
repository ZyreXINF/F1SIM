package com.zyrexinfinity.f1sim.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Track {
    ALBERT_PARK(58,77,18800,
            28553,17831,34890,
            "Albert Park Circuit", "Melbourne"),
    SHANGHAI(56,87,20300,
            25334,28437,40993,
            "Shanghai International Circuit", "Shanghai"),
    SUZUKA(53,79,20500,
            31235,41404,17700,
            "Suzuka International Racing Course", "Suzuka"),
    SAKHIR(57,91,18300,
            30457,41215,23261,
            "Bahrain International Circuit", "Bahrain"),
    JEDDAH(50,85,17800,
            33499,28888,29113,
            "Jeddah Corniche Circuit", "Jeddah"),
    MIAMI(57,84,17800,
            29833, 34065, 25335,
            "Miami International Autodrome", "Miami"),
    IMOLA(63,72,19300,
            24534,26965,26070,
            "Autodromo Enzo e Dino Ferrari", "Imola"),
    MONACO(78,60,16800,
            19145,34690,19379,
            "Circuit de Monaco", "Monaco"),
    BARCELONA_CATALUNYA(66,76,18800,
            22500,30476,22567,
            "Circuit de Barcelona-Catalunya", "Barcelona"),
    GILLES_VILLENEUVE(70,80,21300,
            20624,23410,29598,
            "Circuit Gilles Villeneuve", "Canada"),
    SPIELBERG(71,89,17800,
            17082,30284,20441,
            "Red Bull Ring", "Spielberg"),
    SILVERSTONE(52,85,17700,
            28701,36333,24299,
            "Silverstone Circuit", "Silverstone"),
    SPA_FRANCORCHAMPS(44,95,19300,
            30421,45439,28348,
            "Circuit de Spa-Francorchamps", "Spa"),
    HUNGARORING(70,73,18300,
            28557,27940, 22243,
            "Hungaroring", "Hungaroring"),
    ZANDVOORT(72,74,17800,
            24661,25567,21760,
            "Circuit Zandvoort", "Zandvoort"),
    MONZA(53,100,22100,
            28224,26411,26266,
            "Autodromo Nazionale Monza", "Monza"),
    BAKU(57,92,17300,
            36446,41638,24655,
            "Baku City Circuit", "Baku"),
    MARINA_BAY(62,75,18800,
            27993,39392,26360,
            "Marina Bay Street Circuit", "Singapore"),
    COTA(56,86,18800,
            25832,38904,32131,
            "Circuit of the Americas", "US"),
    MEXICO_CITY(71,83,18300,
            27935,31082,20506,
            "Autodromo Hermanos Rodriguez", "Mexico"),
    INTERLAGOS(71,93,19300,
            18386,36746,16536,
            "Autodromo Jose Carlos Pace", "Interlagos"),
    LAS_VEGAS(50,81,18300,
            26350,31156,35756,"Las Vegas Strip Circuit", "Las_Vegas"),
    LUSAIL(57,82,18800,
            30636,28069,23851,
            "Losail International Circuit", "Qatar"),
    YAS_MARINA(58,78,18800,
            17272,37457,31433,
            "Yas Marina Circuit", "Abu_Dhabi");

    private final int lapsNumber, overtakeability;
    private final long sector1, sector2, sector3, driveTroughTime;
    private final String trackName, trackImageName;

    Track(int lapsNumber, int overtakeability,long driveTroughTime, long sector1, long sector2, long sector3, String trackName, String trackImageName){
        this.lapsNumber = lapsNumber;
        this.overtakeability = overtakeability;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
        this.driveTroughTime = driveTroughTime;
        this.trackName = trackName;
        this.trackImageName = trackImageName;
    }

    public int getLapsNumber() {
        return lapsNumber;
    }

    public long getSectorTime(int sectorNumber){
        return switch (sectorNumber) {
            case 1 -> sector1;
            case 2 -> sector2;
            case 3 -> sector3;
            default -> 0;
        };
    }

    public long getDriveTroughTime() {
        return driveTroughTime;
    }
    public int getOvertakeability() {
        return overtakeability;
    }
    public String getTrackName(){
        return trackName;
    }
    public String getTrackImageName() {
        return trackImageName;
    }
}
