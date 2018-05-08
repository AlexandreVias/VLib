package com.example.avias.vlib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.avias.vlib.dto.EtatPlot;
import com.example.avias.vlib.dto.EtatStation;
import com.example.avias.vlib.dto.Plot;
import com.example.avias.vlib.dto.Station;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EtatPlotDAO {
    private MySQLiteHelper accesBD;
    public EtatPlotDAO(Context ct){
        accesBD = new MySQLiteHelper(ct);
    }

    public ArrayList<EtatPlot> listEtatPlot(Plot plot){
        try(SQLiteDatabase bd = accesBD.getReadableDatabase()) {
            ArrayList<EtatPlot> etatsPlot = new ArrayList<>();
            Cursor curseur = bd.rawQuery("SELECT DATEM, NUMS, NUMP, ETATP FROM ETAT_PLOT WHERE NUMP=" + plot.getNump() + " AND NUMS=" + plot.getStation().getNums(), null);
            curseur.moveToFirst();
            while (!curseur.isAfterLast()){
                etatsPlot.add(new EtatPlot(
                        curseur.getString(0),
                        plot.getStation(),
                        plot,
                        curseur.getString(3)
                ));
                curseur.moveToNext();
            }
            curseur.close();
            return etatsPlot;
        }catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
            return null;
        }
    }

    public boolean addEtatPlot(Plot plot) {
        try(SQLiteDatabase bd = accesBD.getWritableDatabase()){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String date = df.format(Calendar.getInstance().getTime());
            bd.execSQL("INSERT INTO ETAT_PLOT VALUES('" + date + "','" + plot.getStation().getNums() + "','" + plot.getNump() + "','" + plot.getEtatcp()+ "')");
            this.updateEtatPlot(plot);
            return true;
        }catch (SQLException e) {
            System.out.println("Erreur SQL :" + e);
            return false;
        }
    }

    public void updateEtatPlot(Plot plot){
        try(SQLiteDatabase bd = accesBD.getWritableDatabase()){
            bd.execSQL("UPDATE PLOT SET ETATCP='" + plot.getEtatcp() + "' WHERE NUMP='" + plot.getNump() + "'");
        }
    }

}
