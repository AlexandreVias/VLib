package com.example.avias.vlib.dto;

import java.io.Serializable;

public class Plot implements Serializable {
    private Station station;
    private String nump;
    private String etatcp;

    public Plot(Station station, String nump, String etatcp){
        this.station = station;
        this.nump = nump;
        this.etatcp = etatcp;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getNump() {
        return nump;
    }

    public void setNump(String nump) {
        this.nump = nump;
    }

    public String getEtatcp() {
        return etatcp;
    }
    public String getLibelleEtatcp(){
        if (this.etatcp.equals("M"))
            return "En maintenance";
        else if(this.etatcp.equals("F"))
            return "En fonctionnement";
        else
            return "Non enregistré";
    }

    public void setEtatcp(String etatcp) {
        this.etatcp = etatcp;
    }
}
