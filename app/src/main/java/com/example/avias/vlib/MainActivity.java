package com.example.avias.vlib;

import android.content.Intent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.avias.vlib.activities.ListStationsMActivity;
import com.example.avias.vlib.db.MySQLiteHelper;

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

    }
}
