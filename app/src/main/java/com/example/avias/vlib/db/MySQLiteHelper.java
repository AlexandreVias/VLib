package com.example.avias.vlib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by avias on 23/03/2018.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int versionBD = 7;
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
    private static String requeteEtat_VeloDrop = "DROP TABLE IF EXISTS etat_velo";

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
        db.execSQL("INSERT INTO STATION VALUES ('1', 'M', 'Stalindrad', 'Bordeaux', 200, '1')");
        db.execSQL("INSERT INTO ETAT_STATION VALUES ('17/10/2017 14:27:30', '1', 'M')");
        db.execSQL("INSERT INTO ETAT_STATION VALUES ('24/12/2017 15:45:32', '1', 'F')");
        db.execSQL("INSERT INTO ETAT_STATION VALUES ('29/02/2018 23:45:35', '1', 'M')");
        db.execSQL("INSERT INTO ETAT_STATION VALUES ('12/04/2018 23:32:45', '1', 'F')");
        db.execSQL("INSERT INTO ETAT_STATION VALUES ('07/05/2018 02:16:12', '1', 'M')");
        db.execSQL("INSERT INTO PLOT VALUES ('1', '1', 'M')");
        db.execSQL("INSERT INTO ETAT_PLOT VALUES ('17/10/2017 14:27:30', '1', '1', 'M')");
        db.execSQL("INSERT INTO ETAT_PLOT VALUES ('24/12/2017 15:45:32', '1', '1', 'F')");
        db.execSQL("INSERT INTO PLOT VALUES ('1', '2', 'F')");
        db.execSQL("INSERT INTO PLOT VALUES ('1', '3', 'M')");
        db.execSQL("INSERT INTO STATION VALUES ('2', 'F', 'La Victoire', 'Bordeaux', 120, '1')");
        db.execSQL("INSERT INTO PLOT VALUES ('2', '1', 'M')");
        db.execSQL("INSERT INTO PLOT VALUES ('2', '2', 'F')");
        db.execSQL("INSERT INTO PLOT VALUES ('2', '3', 'M')");
        db.execSQL("INSERT INTO PLOT VALUES ('2', '4', 'F')");
        db.execSQL("INSERT INTO STATION VALUES ('3', 'F', 'Barrière Saint-Genès', 'Bordeaux', 50, '0')");
        db.execSQL("INSERT INTO PLOT VALUES ('3', '1', 'M')");
        db.execSQL("INSERT INTO PLOT VALUES ('3', '2', 'F')");
        db.execSQL("INSERT INTO PLOT VALUES ('3', '3', 'M')");
        db.execSQL("INSERT INTO PLOT VALUES ('3', '4', 'F')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(requeteStationDrop);
        db.execSQL(requetePlotDrop);
        db.execSQL(requeteVeloDrop);
        db.execSQL(requeteEtat_StationDrop);
        db.execSQL(requeteEtat_PlotDrop);
        db.execSQL(requeteEtat_VeloDrop);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(requeteStationDrop);
        db.execSQL(requetePlotDrop);
        db.execSQL(requeteVeloDrop);
        db.execSQL(requeteEtat_StationDrop);
        db.execSQL(requeteEtat_PlotDrop);
        db.execSQL(requeteEtat_VeloDrop);
        onCreate(db);
    }
}