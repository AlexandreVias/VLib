package com.example.avias.vlib.db;

import android.content.Context;

/**
 * Created by jcorbu on 06/04/2018.
 */

public class PlotDAO {

    private MySQLiteHelper accesBD;

    public PlotDAO(Context ct){
        accesBD = new MySQLiteHelper(ct);
    }
}