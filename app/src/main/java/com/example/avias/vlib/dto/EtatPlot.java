package com.example.avias.vlib.dto;

public class EtatPlot {
    private String datem;
    private Station station;
    private Plot plot;
    private String etatp;

    public EtatPlot(String datem, Station station, Plot plot, String etatp) {
        this.datem = datem;
        this.station = station;
        this.plot = plot;
        this.etatp = etatp;
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

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public String getEtatp() {
        return etatp;
    }

    public void setEtatp(String etatp) {
        this.etatp = etatp;
    }
}
