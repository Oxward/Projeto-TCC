package com.example.aluno.googlemap.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.adapter.ListaAdapter;
import com.example.aluno.googlemap.classes.PontosDeParada;
import com.example.aluno.googlemap.database.Pontos_ViewModel;

import java.util.ArrayList;

public class Bus1 extends Fragment {

    private static final String TAG = "Bus1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView listsView = view.findViewById(R.id.list_view);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listsView, false);
        listsView.addHeaderView(viewGroup);

        /*
        list.add(new PontosDeParada("12:30", "CTF"));
        list.add(new PontosDeParada("13:30", "Ali"));
        list.add(new PontosDeParada("14:30", "Aqui"));
        list.add(new PontosDeParada("15:30", "Freitas"));
        list.add(new PontosDeParada("16:30", "Rádio"));
        list.add(new PontosDeParada("17:30", "Terra"));
        list.add(new PontosDeParada("18:30", "Céu"));
        list.add(new PontosDeParada("19:30", "Outro Lado"));
        list.add(new PontosDeParada("20:30", "Bolsonaro"));
        list.add(new PontosDeParada("12:30", "CTF"));
        list.add(new PontosDeParada("13:30", "Ali"));
        list.add(new PontosDeParada("14:30", "Aqui"));
        list.add(new PontosDeParada("15:30", "Freitas"));
        list.add(new PontosDeParada("16:30", "Rádio"));
        list.add(new PontosDeParada("17:30", "Terra"));
        list.add(new PontosDeParada("18:30", "Céu"));
        list.add(new PontosDeParada("19:30", "Outro Lado"));
        list.add(new PontosDeParada("20:30", "Bolsonaro"));
        list.add(new PontosDeParada("12:30", "CTF"));
        list.add(new PontosDeParada("13:30", "Ali"));
        list.add(new PontosDeParada("14:30", "Aqui"));
        list.add(new PontosDeParada("15:30", "Freitas"));
        list.add(new PontosDeParada("16:30", "Rádio"));
        list.add(new PontosDeParada("17:30", "Terra"));
        list.add(new PontosDeParada("18:30", "Céu"));
        list.add(new PontosDeParada("19:30", "Outro Lado"));
        list.add(new PontosDeParada("20:30", "Bolsonaro"));
        list.add(new PontosDeParada("12:30", "CTF"));
        list.add(new PontosDeParada("13:30", "Ali"));
        list.add(new PontosDeParada("14:30", "Aqui"));
        list.add(new PontosDeParada("15:30", "Freitas"));
        list.add(new PontosDeParada("16:30", "Rádio"));
        list.add(new PontosDeParada("17:30", "Terra"));
        list.add(new PontosDeParada("18:30", "Céu"));
        list.add(new PontosDeParada("19:30", "Outro Lado"));
        list.add(new PontosDeParada("20:30", "Bolsonaro"));
        */

        ArrayList<PontosDeParada> list = new ArrayList<>(new Pontos_ViewModel(getActivity().getApplication()).getAllPoints());

        Log.d(TAG, "onCreateView: " + list.size());

        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, list);
        listsView.setAdapter(adapter);

        //new fillList().execute();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    /*
    private class fillList extends AsyncTask<Void, Void, ArrayList<PontosDeParada>>{

        private static final String TAG = "fillList";

        private ArrayList<PontosDeParada> lista;
        private Pontos_ViewModel pontos_viewModel;

        private fillList(){
            lista = new ArrayList<>();
            pontos_viewModel = new Pontos_ViewModel(getActivity().getApplication());
        }

        private fillList(Pontos_ViewModel pontos_viewModel, ArrayList<PontosDeParada> list){
            this.pontos_viewModel = pontos_viewModel;
            this.lista = list;
        }

        @Override
        protected ArrayList<PontosDeParada> doInBackground(Void... voids) {
            lista.addAll( pontos_viewModel.getAllPoints() );
            Log.d(TAG, "doInBackground: "+lista.size());
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<PontosDeParada> pontosDeParadas) {
            super.onPostExecute(pontosDeParadas);
            ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, lista);
            listsView.setAdapter(adapter);
        }
    }
    */
}