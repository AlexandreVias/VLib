package com.example.avias.vlib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.avias.vlib.dto.Plot;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;

public class PlotDAO {
    private MySQLiteHelper accesBD;
    public PlotDAO(Context ct){
        accesBD = new MySQLiteHelper(ct);
    }

    public ArrayList<Plot> listPlotStation(Station station){
        try {
            ArrayList<Plot> plots = new ArrayList<>();
            SQLiteDatabase bd = accesBD.getReadableDatabase();
            Cursor curseur = bd.rawQuery("SELECT NUMP, ETATCP FROM PLOT WHERE NUMS=" + station.getNums(), null);
            curseur.moveToFirst();
            while (!curseur.isAfterLast()){
                plots.add(new Plot(
                        station,
                        curseur.getString(0),
                        curseur.getString(1)
                        ));
                curseur.moveToNext();
            }
            return plots;
        }catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

}
