package com.example.avias.vlib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.avias.vlib.dto.EtatStation;
import com.example.avias.vlib.dto.Station;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EtatStationDAO {
    private MySQLiteHelper accesBD;
    public EtatStationDAO(Context ct){
        accesBD = new MySQLiteHelper(ct);
    }

    public ArrayList<EtatStation> listEtatStation(Station station){
        try(SQLiteDatabase bd = accesBD.getReadableDatabase()) {
            ArrayList<EtatStation> etatsStation = new ArrayList<>();
            Cursor curseur = bd.rawQuery("SELECT DATEM, NUMS, ETATS FROM ETAT_STATION WHERE NUMS=" + station.getNums(), null);
            curseur.moveToFirst();
            while (!curseur.isAfterLast()){
                etatsStation.add(new EtatStation(
                        curseur.getString(0),
                        station,
                        curseur.getString(2)
                ));
                curseur.moveToNext();
            }
            curseur.close();
            return etatsStation;
        }catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    public boolean addEtatStation(Station station) {
        try(SQLiteDatabase bd = accesBD.getWritableDatabase()){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String date = df.format(Calendar.getInstance().getTime());
            bd.execSQL("INSERT INTO ETAT_STATION VALUES('" + date + "','" + station.getNums() + "','" + station.getEtatcs() + "')");
            this.updateEtatStation(station);
            return true;
        }catch (SQLException e) {
            System.out.println("Erreur SQL :" + e);
            return false;
        }
    }

    public void updateEtatStation(Station station){
        try(SQLiteDatabase bd = accesBD.getWritableDatabase()){
            bd.execSQL("UPDATE STATION SET ETATCS='" + station.getEtatcs() + "' WHERE NUMS='" + station.getNums() + "'");
        }
    }

}
