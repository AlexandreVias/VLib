package com.example.avias.vlib.dto;

public class EtatStation {
    private String datem;
    private Station station;
    private String etats;


    public EtatStation(String datem, Station station, String etats) {
        this.datem = datem;
        this.station = station;
        this.etats = etats;
    }

    public String getDatem() {
        return datem;
    }

    public void setDatem(String datem) {
        this.datem = datem;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getEtats() {
        return etats;
    }

    public void setEtats(String etats) {
        this.etats = etats;
    }
}
