package com.zyrexinfinity.f1sim.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Track {
    ALBERT_PARK(58,28553,17831,34890,
            "Albert Park Circuit", "Melbourne"),
    SHANGHAI(56,25334,28437,40993,
            "Shanghai International Circuit", "Shanghai"),
    SUZUKA(53,31235,41404,17700,
            "Suzuka International Racing Course", "Suzuka"),
    SAKHIR(57, 30457,41215,23261,
            "Bahrain International Circuit", "Bahrain"),
    JEDDAH(50,33499,28888,29113,
            "Jeddah Corniche Circuit", "Jeddah"),
    MIAMI(57,29833, 34065, 25335,
            "Miami International Autodrome", "Miami"),
    IMOLA(63,24534,26965,26070,
            "Autodromo Enzo e Dino Ferrari", "Imola"),
    MONACO(78,19145,34690,19379,
            "Circuit de Monaco", "Monaco"),
    BARCELONA_CATALUNYA(66,22500,30476,22567,
            "Circuit de Barcelona-Catalunya", "Barcelona"),
    GILLES_VILLENEUVE(70,20624,23410,29598,
            "Circuit Gilles Villeneuve", "Canada"),
    SPIELBERG(71,17082,30284,20441,
            "Red Bull Ring", "Spielberg"),
    SILVERSTONE(52,28701,36333,24299,
            "Silverstone Circuit", "Silverstone"),
    SPA_FRANCORCHAMPS(44,30421,45439,28348,
            "Circuit de Spa-Francorchamps", "Spa"),
    HUNGARORING(70,28557,27940, 22243,
            "Hungaroring", "Hungaroring"),
    ZANDVOORT(72, 24661,25567,21760,
            "Circuit Zandvoort", "Zandvoort"),
    MONZA(53, 28224,26411,26266,
            "Autodromo Nazionale Monza", "Monza"),
    BAKU(57,36446,41638,24655,
            "Baku City Circuit", "Baku"),
    MARINA_BAY(62,27993,39392,26360,
            "Marina Bay Street Circuit", "Singapore"),
    COTA(56,25832,38904,32131,
            "Circuit of the Americas", "US"),
    MEXICO_CITY(71, 27935,31082,20506,
            "Autodromo Hermanos Rodriguez", "Mexico"),
    INTERLAGOS(71,18386,36746,16536,
            "Autodromo Jose Carlos Pace", "Interlagos"),
    LAS_VEGAS(50,26350,31156,35756,"Las Vegas Strip Circuit", "Las_Vegas"),
    LUSAIL(57,30636,28069,23851,
            "Losail International Circuit", "Qatar");
//    YAS_MARINA(58,x,x,x,"Yas Marina Circuit", "Abu_Dhabi");
//    TODO ADD DATA TO THIS CIRCUITS

    private final int lapsNumber;
    private final long sector1, sector2, sector3;
    private final String trackName, trackImageName;

    Track(int lapsNumber, long sector1, long sector2, long sector3, String trackName, String trackImageName){
        this.lapsNumber = lapsNumber;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
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
    public String getTrackName(){
        return trackName;
    }
    public String getTrackImageName() {
        return trackImageName;
    }
}
