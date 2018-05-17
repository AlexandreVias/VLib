package com.example.avias.vlib.dto;

import java.io.Serializable;

/**
 * Created by avias on 30/03/2018.
 */

public class Station implements Serializable{
    private String nums;
    private String etatcs;
    private String nom;
    private String situation;
    private int capacite;
    private String presenceborne;

    public Station(String nums, String etatcs, String nom, String situation, int capacite, String presenceborne){
        this.nums = nums;
        this.etatcs = etatcs;
        this.nom = nom;
        this.situation = situation;
        this.capacite = capacite;
        this.presenceborne = presenceborne;
    }

    public String getNums() {
        return nums;
    }

    public String getEtatcs() {
        return etatcs;
    }

    public String getLibelleEtatcs(){
        if (this.etatcs.equals("M"))
            return "En maintenance";
        else if(this.etatcs.equals("F"))
            return "En fonctionnement";
        else
            return "Non enregistré";
    }

    public String getNom() {
        return nom;
    }

    public String getSituation() {
        return situation;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getPresenceborne() {
        return presenceborne;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public void setEtatcs(String etatcs) {
        this.etatcs = etatcs;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setPresenceborne(String presenceborne) {
        this.presenceborne = presenceborne;
    }

}