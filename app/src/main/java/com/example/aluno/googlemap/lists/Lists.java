package com.example.aluno.googlemap.lists;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.classes.PontosDeParada;
import com.example.aluno.googlemap.database.Pontos_ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Lists extends AppCompatActivity {

    private static final String TAG = "Lists";
    private static List<PontosDeParada> mList = new ArrayList<>();

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

        new selectAsync(new Pontos_ViewModel(getApplication()), mList).execute();
        ArrayList<PontosDeParada> pontosDeParadas = (ArrayList<PontosDeParada>) mList;

        /*
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);
        pontosDeParadas.add(pdp);
        pontosDeParadas.add(pdp1);
        pontosDeParadas.add(pdp2);
        pontosDeParadas.add(pdp3);*/

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.adapter_view_layout, pontosDeParadas);
        PontosDeParadaListAdapter arrayAdapter = new PontosDeParadaListAdapter(this, R.layout.adapter_view_layout, pontosDeParadas);
        lists.setAdapter(arrayAdapter);
    }

    private static class selectAsync extends AsyncTask<Void, Void, List<PontosDeParada>> {

        private Pontos_ViewModel pontos_viewModel;
        private List<PontosDeParada> mAsyncList;

        private selectAsync(Pontos_ViewModel markers_viewModel, List<PontosDeParada> mList) {
            this.pontos_viewModel = markers_viewModel;
            this.mAsyncList = mList;
        }

        @Override
        protected List<PontosDeParada> doInBackground(Void... voids) {
            Log.d("asd", "doInBackground: " + mAsyncList.size());
            mAsyncList.addAll(pontos_viewModel.getAllPoints());
            Log.d("asd", "doInBackground: " + mAsyncList.size());
            return mAsyncList;
        }

        @Override
        protected void onPostExecute(List<PontosDeParada> list) {
            Log.d("asd", "doInBackground: " + list.size());
            mList = list;
            Log.d("asd", "doInBackground: " + mList.size());
            mAsyncList.clear();
            mList.clear();
        }
    }

}