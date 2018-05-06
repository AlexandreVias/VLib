package com.example.avias.vlib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.avias.vlib.R;
import com.example.avias.vlib.dto.Station;

public class SetStationMActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_m);

        Intent intent = getIntent();
        Station station = (Station) intent.getExtras().getSerializable("station");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(station.getNom());
    }
}