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

import com.example.avias.vlib.MainActivity;
import com.example.avias.vlib.R;
import com.example.avias.vlib.db.PlotDAO;
import com.example.avias.vlib.dto.Plot;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StationMActivity extends Activity {
    final PlotDAO plotDAO = new PlotDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_m);
        Intent intent = getIntent();
        final Station station = (Station) intent.getExtras().getSerializable("station");

        TextView textView = findViewById(R.id.textViewS);
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

        final Button buttonH = findViewById(R.id.buttonH);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StationMActivity.this, HistoryStationActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });
    }
}