package com.example.avias.vlib.activities;

import com.example.avias.vlib.MainActivity;
import com.example.avias.vlib.db.StationDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;
import java.util.LinkedList;

import com.example.avias.vlib.dto.Station;
import com.example.avias.vlib.R;

import java.util.ArrayList;

public class ListStationsMActivity extends Activity {
    final StationDAO stationDAO = new StationDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_m);

        final ArrayList<Station> stations = stationDAO.listStations();
        final List<String[]> listStations = new LinkedList<>();
        ListView listViewStations = (ListView) findViewById(R.id.listViewStations);

        for(Station station : stations){
            listStations.add(new String[]{
                    station.getNom() + " (" + station.getSituation() + ")",
                    station.getLibelleEtatcs()});
        }

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                listStations){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                String[] entry = listStations.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        };
        listViewStations.setAdapter(adapter);

        listViewStations.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = stations.get(position);
                Intent intent = new Intent(ListStationsMActivity.this, StationMActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });

        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListStationsMActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}