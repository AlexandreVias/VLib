package com.example.avias.vlib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;

public class StationDAO {
    private MySQLiteHelper accesBD;

    public StationDAO(Context ct){
        accesBD = new MySQLiteHelper(ct);
    }

    public ArrayList<Station> listStations() {
        try {
            ArrayList<Station> stations = new ArrayList<>();
            SQLiteDatabase bd = accesBD.getReadableDatabase();
            Cursor curseur = bd.rawQuery("SELECT NUMS, ETATCS, NOM, SITUATION, CAPACITE, PRESENCEBORNE FROM STATION", null);
            curseur.moveToFirst();
            while (!curseur.isAfterLast()){
                stations.add(new Station(
                        curseur.getString(0),
                        curseur.getString(1),
                        curseur.getString(2),
                        curseur.getString(3),
                        curseur.getInt(4),
                        curseur.getString(5)
                ));
                curseur.moveToNext();
            }
            return stations;
        }catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    public Cursor cursorStations() {
        try {
            SQLiteDatabase bd = accesBD.getReadableDatabase();
            Cursor curseur = bd.rawQuery("SELECT NUMS, ETATCS, NOM, SITUATION, CAPACITE, PRESENCEBORNE FROM STATION", null);
            return curseur;
        }catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

}
