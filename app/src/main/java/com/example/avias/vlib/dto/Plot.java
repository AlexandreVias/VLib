package com.example.avias.vlib.dto;

/**
 * Created by jcorbu on 06/04/2018.
 */

public class    Plot {
    private String nums;
    private String nump;
    private String etatcp;

    public Plot(String nums,  String nump, String etatcp){
        this.nums = nums;
        this.nump = nump;
        this.etatcp = etatcp;
    }

    public String getNums() {
        return nums;
    }

    public String getEtatcp() {
        return etatcp;
    }

    public String getNump() {
        return nump;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public void setEtatcp(String etatcp) {
        this.etatcp = etatcp;
    }

    public void setNump(String nump) {
        this.nump = nump;
    }

}