package com.example.avias.vlib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.avias.vlib.R;
import com.example.avias.vlib.db.EtatStationDAO;
import com.example.avias.vlib.dto.EtatStation;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;

public class HistoryStationActivity extends Activity {
    final EtatStationDAO etatStationDAO = new EtatStationDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_station);
        Intent intent = getIntent();
        final Station station = (Station) intent.getExtras().getSerializable("station");

        TextView textViewS = (TextView) findViewById(R.id.textViewS);
        textViewS.setText("Historique de " + station.getNom());

        ArrayList<EtatStation> etatsStation =  etatStationDAO.listEtatStation(station);
        if (etatsStation == null || etatsStation.isEmpty())
            findViewById(R.id.textViewEmpty).setVisibility(View.VISIBLE);
        else {
            ArrayList<String> listEtatsStation = new ArrayList<>();
            for(EtatStation etatStation : etatsStation)
                listEtatsStation.add(etatStation.getDatem().substring(0,10) + " - " + etatStation.getEtats());

            ListView listViewES = findViewById(R.id.listviewES);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryStationActivity.this,android.R.layout.simple_list_item_1, listEtatsStation);
            listViewES.setAdapter(adapter);
        }



    }
}
