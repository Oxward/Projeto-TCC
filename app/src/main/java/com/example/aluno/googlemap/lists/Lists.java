package com.example.aluno.googlemap.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.classes.PontosDeParada;
import com.example.aluno.googlemap.database.Pontos_ViewModel;

import java.util.ArrayList;

public class Lists extends AppCompatActivity {

    private static final String TAG = "Lists";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Log.d(TAG, "onCreate: Iniciou");

        ListView lists = findViewById(R.id.list_view);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.header, lists, false);
        lists.addHeaderView(viewGroup);

        //Adicionando pontos de parada
        /*PontosDeParada pdp = new PontosDeParada("06:00", "CTF");
        PontosDeParada pdp1 = new PontosDeParada("06:10", "Posto");
        PontosDeParada pdp2 = new PontosDeParada("06:20", "Sei lá");
        PontosDeParada pdp3 = new PontosDeParada("06:30", "No Carai");*/

        Pontos_ViewModel pontos_viewModel = new Pontos_ViewModel(getApplication());
        ArrayList<PontosDeParada> mList = new ArrayList<>(pontos_viewModel.getAllPoints());

        /*
        pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);       pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);       pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);        pontosDeParadas.add(pdp1);       pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);*/

        PontosDeParadaListAdapter arrayAdapter = new PontosDeParadaListAdapter(this, R.layout.adapter_view_layout, mList);
        lists.setAdapter(arrayAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}