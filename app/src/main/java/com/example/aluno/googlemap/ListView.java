package com.example.aluno.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListView extends AppCompatActivity
{

    private static final String TAG = "ListView";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Log.d(TAG, "onCreate: Iniciou");
        android.widget.ListView listView = findViewById(R.id.list_view);

        //Adicionando pontos de parada
        PontosDeParada pdp = new PontosDeParada("06:00", "CTF");
        PontosDeParada pdp1 = new PontosDeParada("06:10", "Posto");
        PontosDeParada pdp2 = new PontosDeParada("06:20", "Sei lá");
        PontosDeParada pdp3 = new PontosDeParada("06:30", "No Carai");

        //ArrayList para o list_view
        ArrayList<PontosDeParada> pontosDeParadas = new ArrayList<>();
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);

        PontosDeParadaListAdapter adapter = new PontosDeParadaListAdapter(this, R.layout.adapter_view_layout, pontosDeParadas);
        listView.setAdapter(adapter);
    }
}
