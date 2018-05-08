package com.example.avias.vlib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.avias.vlib.R;
import com.example.avias.vlib.db.EtatStationDAO;
import com.example.avias.vlib.dto.EtatStation;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;

public class HistoryStationActivity extends Activity {
    final EtatStationDAO etatStationDAO = new EtatStationDAO(this);
    Station station = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_station);
        Intent intent = getIntent();
        if (intent.getExtras() != null)
            station = (Station) intent.getExtras().getSerializable("station");

        TextView textViewS = findViewById(R.id.textViewP);
        String text = "";
        if (station != null)
            text = "Historique de " + station.getNom();
        textViewS.setText(text);

        ArrayList<EtatStation> etatsStation =  etatStationDAO.listEtatStation(station);
        if (etatsStation == null || etatsStation.isEmpty())
            findViewById(R.id.textViewEmpty).setVisibility(View.VISIBLE);
        else {
            ArrayList<String> listEtatsStation = new ArrayList<>();
            for(EtatStation etatStation : etatsStation)
                listEtatsStation.add(etatStation.getDatem().substring(0,10) + " - " + etatStation.getEtats());

            ListView listViewES = findViewById(R.id.listviewES);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(HistoryStationActivity.this,android.R.layout.simple_list_item_1, listEtatsStation);
            listViewES.setAdapter(adapter);
        }

        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryStationActivity.this, StationMActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });
    }
}