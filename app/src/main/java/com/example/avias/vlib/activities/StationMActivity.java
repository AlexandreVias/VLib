package com.example.avias.vlib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avias.vlib.R;
import com.example.avias.vlib.db.EtatStationDAO;
import com.example.avias.vlib.db.PlotDAO;
import com.example.avias.vlib.dto.Plot;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StationMActivity extends Activity {
    final PlotDAO plotDAO = new PlotDAO(this);
    final EtatStationDAO etatStationDAO = new EtatStationDAO(this);
    Station station = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_m);
        Intent intent = getIntent();
        if (intent.getExtras() != null)
            station = (Station) intent.getExtras().getSerializable("station");

        TextView textView = findViewById(R.id.textViewP);
        textView.setText(station.getNom());
        RadioButton radioButtonF = findViewById((R.id.radioButtonF));
        RadioButton radioButtonM = findViewById((R.id.radioButtonM));
        if (station.getEtatcs().equals("F"))
            radioButtonF.setChecked(true);
        else
            radioButtonM.setChecked(true);

        final ArrayList<Plot> plots = plotDAO.listPlotStation(station);
        final List<String[]> listPlots = new LinkedList<>();
        ListView listViewPlots = findViewById(R.id.listViewPlots);

        for(Plot plot : plots){
            listPlots.add(new String[]{
                    "Plot " + plot.getNump(),
                    plot.getLibelleEtatcp()});
        }

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                listPlots){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                String[] entry = listPlots.get(position);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        };
        listViewPlots.setAdapter(adapter);

        listViewPlots.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plot plot = plots.get(position);
                Intent intent = new Intent(StationMActivity.this, HistoryPlotActivity.class);
                intent.putExtra("plot", plot);
                startActivity(intent);
            }
        });

        final Button buttonH = findViewById(R.id.buttonH);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StationMActivity.this, HistoryStationActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });

        final Button buttonV = findViewById(R.id.buttonV);
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Erreur";
                RadioButton radioButtonF = findViewById(R.id.radioButtonF);
                RadioButton radioButtonM = findViewById(R.id.radioButtonM);

                //Vérifie qu'il y a bien un changement d'état
                if ((radioButtonF.isChecked() && station.getEtatcs().equals("F")) || (radioButtonM.isChecked() && station.getEtatcs().equals("M"))) {
                    text = "Changer l'état pour valider";
                    Toast.makeText(StationMActivity.this, text, Toast.LENGTH_SHORT).show();
                    return;
                }

                //Change l'état de l'objet station
                if (radioButtonF.isChecked())
                    station.setEtatcs("F");
                else
                    station.setEtatcs("M");

                //Change l'état de la station dans la base de données, et l'enregesitre dans l'historique des états
                if (etatStationDAO.addEtatStation(station)) {
                    text = "Changement d'état réussi";
                    Intent intent = new Intent(StationMActivity.this, ListStationsMActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(StationMActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StationMActivity.this, ListStationsMActivity.class);
                startActivity(intent);
            }
        });
    }
}