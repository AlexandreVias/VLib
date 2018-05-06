package com.example.avias.vlib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by avias on 23/03/2018.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int versionBD = 2;
    private static final String nomBD = "vlib.db";

    private String requeteStation = "CREATE TABLE IF NOT EXISTS STATION(" +
            "   NUMS VARCHAR(4) NOT NULL," +
            "   ETATCS VARCHAR(1)," +
            "   NOM VARCHAR(40)," +
            "   SITUATION VARCHAR(100)," +
            "   CAPACITE INT," +
            "   PRESENCEBORNE VARCHAR(1)," +
            "   PRIMARY KEY (NUMS)" +
            " ) ;";

    private String requetePlot = "CREATE TABLE IF NOT EXISTS PLOT(" +
            "   NUMS VARCHAR(4) NOT NULL," +
            "   NUMP VARCHAR(4) NOT NULL," +
            "   ETATCP VARCHAR(1)," +
            "   PRIMARY KEY (NUMS,NUMP)," +
            "   FOREIGN KEY (NUMS) REFERENCES STATION (NUMS)" +
            " ) ;";

    private String requeteVelo = "CREATE TABLE IF NOT EXISTS VELO(" +
            "   NUMV VARCHAR(4) NOT NULL," +
            "   ETATCV VARCHAR(1)," +
            "   DMEC VARCHAR(10)," +
            "   MODÈLE VARCHAR(20)," +
            "   PRIMARY KEY (NUMV)" +
            " ) ;";

    private String requeteEtat_Station = "CREATE TABLE IF NOT EXISTS ETAT_STATION" +
            " (" +
            "   DATEM VARCHAR(10) NOT NULL," +
            "   NUMS VARCHAR(4) NOT NULL," +
            "   ETATS VARCHAR(1)," +
            "   PRIMARY KEY (DATEM,NUMS)," +
            "   FOREIGN KEY (NUMS) REFERENCES STATION (NUMS)" +
            " ) ;";

    private String requeteEtat_Plot = "CREATE TABLE IF NOT EXISTS ETAT_PLOT" +
            " (" +
            "   DATEM VARCHAR(10) NOT NULL," +
            "   NUMS VARCHAR(4) NOT NULL," +
            "   NUMP VARCHAR(4) NOT NULL," +
            "   ETATP VARCHAR(1)," +
            "   PRIMARY KEY (DATEM,NUMS,NUMP)," +
            "   FOREIGN KEY (NUMS,NUMP) REFERENCES PLOT (NUMS,NUMP)" +
            " ) ;";

    private String requeteEtat_Velo = "CREATE TABLE IF NOT EXISTS ETAT_VELO" +
            " (" +
            "   DATEM VARCHAR(10) NOT NULL," +
            "   NUMV VARCHAR(4) NOT NULL," +
            "   ETATV VARCHAR(1)," +
            "   PRIMARY KEY (DATEM,NUMV)," +
            "   FOREIGN KEY (NUMV) REFERENCES VELO (NUMV)" +
            " ) ;";

    private static String requeteStationDrop = "DROP TABLE IF EXISTS station";
    private static String requetePlotDrop = "DROP TABLE IF EXISTS plot";
    private static String requeteVeloDrop = "DROP TABLE IF EXISTS velo";
    private static String requeteEtat_StationDrop = "DROP TABLE IF EXISTS etat_station";
    private static String requeteEtat_PlotDrop = "DROP TABLE IF EXISTS etat_plot";
    private static String requeteEtat_veloDrop = "DROP TABLE IF EXISTS etat_velo";

    public MySQLiteHelper(Context context){
        super(context, nomBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(requeteStation);
        db.execSQL(requetePlot);
        db.execSQL(requeteVelo);
        db.execSQL(requeteEtat_Station);
        db.execSQL(requeteEtat_Plot);
        db.execSQL(requeteEtat_Velo);
        db.execSQL("INSERT INTO STATION VALUES (1, 'M', 'Stalindrad', 'Bordeaux', 200, '1')");
        db.execSQL("INSERT INTO STATION VALUES (2, 'F', 'La Victoire', 'Bordeaux', 120, '1')");
        db.execSQL("INSERT INTO STATION VALUES (3, 'F', 'Barrière Saint-Genès', 'Bordeaux', 50, '0')");
        Log.d("Test", "Passage dans onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("Test", "Passage dans onUpgrade");
        db.execSQL(requeteStationDrop);
        db.execSQL(requetePlotDrop);
        db.execSQL(requeteVeloDrop);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("Test", "Passage dans onDowngrade");
        db.execSQL(requeteStationDrop);
        db.execSQL(requetePlotDrop);
        db.execSQL(requeteVeloDrop);
        onCreate(db);
    }
}