package com.example.aluno.googlemap.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.adapter.ListaAdapter;
import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.ArrayList;

public class Bus3 extends Fragment {

    private static final String TAG = "Bus3";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView listsView = view.findViewById(R.id.list_view);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listsView, false);
        listsView.addHeaderView(viewGroup);

        ArrayList<PontosDeParada> list = new ArrayList<>();

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


        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, list);
        listsView.setAdapter(adapter);

        return view;
    }
}