package com.example.avias.vlib;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.avias.vlib.activities.AjoutStationActivity;
import com.example.avias.vlib.activities.ListStationActivity;
import com.example.avias.vlib.activities.ListStationsMActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonMaintenance = (Button) findViewById(R.id.btnMaintenance);
        buttonMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListStationsMActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonVelos = (Button) findViewById(R.id.btnVelos);
        buttonVelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListStationActivity.class);
                startActivity(intent);
            }
        });
        final Button buttonAjouter = (Button) findViewById(R.id.btnAjouterStation);
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListStationActivity.class, AjoutStationActivity.class);
                startActivity(intent);
            }
        });
    }
}
