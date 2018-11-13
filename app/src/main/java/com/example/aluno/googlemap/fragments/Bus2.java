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
import com.example.aluno.googlemap.database.Pontos_ViewModel;

import java.util.ArrayList;

public class Bus2 extends Fragment {

    private static final String TAG = "Bus2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView listsView = view.findViewById(R.id.list_view);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listsView, false);
        listsView.addHeaderView(viewGroup);

        ArrayList<PontosDeParada> list = new ArrayList<>(
                new Pontos_ViewModel(getActivity().getApplication()).getPdPByTurnPlaca((byte) 3, "OEE-2466")
        );

        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, list);
        listsView.setAdapter(adapter);

        return view;
    }
}