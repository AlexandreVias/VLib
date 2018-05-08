package com.example.avias.vlib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avias.vlib.R;
import com.example.avias.vlib.db.EtatPlotDAO;
import com.example.avias.vlib.dto.EtatPlot;
import com.example.avias.vlib.dto.Plot;
import com.example.avias.vlib.dto.Station;

import java.util.ArrayList;

public class HistoryPlotActivity extends Activity {
    final EtatPlotDAO etatPlotDAO = new EtatPlotDAO(this);
    Plot plot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_plot);
        Intent intent = getIntent();
        if (intent.getExtras() != null)
            plot = (Plot) intent.getExtras().getSerializable("plot");

        TextView textViewP = findViewById(R.id.textViewP);
        String text = "";
        if (plot != null)
                text = "Historique de " + plot.getStation().getNom() + " (Plot " + plot.getNump() + ")";
        textViewP.setText(text);

        RadioButton radioButtonF = findViewById((R.id.radioButtonF));
        RadioButton radioButtonM = findViewById((R.id.radioButtonM));
        if (plot.getEtatcp().equals("F"))
            radioButtonF.setChecked(true);
        else if (plot.getEtatcp().equals("M"))
            radioButtonM.setChecked(true);

        ArrayList<EtatPlot> etatsPlot =  etatPlotDAO.listEtatPlot(plot);
        if (etatsPlot == null || etatsPlot.isEmpty())
            findViewById(R.id.textViewEmpty).setVisibility(View.VISIBLE);
        else {
            ArrayList<String> listEtatsPlot = new ArrayList<>();
            for(EtatPlot etatPlot : etatsPlot)
                listEtatsPlot.add(etatPlot.getDatem().substring(0,10) + " - " + etatPlot.getEtatp());

            ListView listViewEP = findViewById(R.id.listviewEP);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(HistoryPlotActivity.this,android.R.layout.simple_list_item_1, listEtatsPlot);
            listViewEP.setAdapter(adapter);
        }

        final Button buttonV = findViewById(R.id.buttonV);
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Erreur";
                RadioButton radioButtonF = findViewById(R.id.radioButtonF);
                RadioButton radioButtonM = findViewById(R.id.radioButtonM);

                //Vérifie qu'il y a bien un changement d'état
                if ((radioButtonF.isChecked() && plot.getEtatcp().equals("F")) || (radioButtonM.isChecked() && plot.getEtatcp().equals("M"))) {
                    text = "Changer l'état pour valider";
                    Toast.makeText(HistoryPlotActivity.this, text, Toast.LENGTH_SHORT).show();
                    return;
                }

                //Change l'état de l'objet station
                if (radioButtonF.isChecked())
                    plot.setEtatcp("F");
                else
                    plot.setEtatcp("M");

                //Change l'état de la station dans la base de données, et l'enregesitre dans l'historique des états
                if (etatPlotDAO.addEtatPlot(plot)) {
                    text = "Changement d'état réussi";
                    Intent intent = new Intent(HistoryPlotActivity.this, StationMActivity.class);
                    intent.putExtra("station", plot.getStation());
                    startActivity(intent);
                }
                Toast.makeText(HistoryPlotActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryPlotActivity.this, StationMActivity.class);
                intent.putExtra("station", plot.getStation());
                startActivity(intent);
            }
        });
    }
}