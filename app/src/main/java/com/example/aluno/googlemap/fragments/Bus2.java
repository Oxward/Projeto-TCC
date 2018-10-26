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

public class Bus2 extends Fragment {

    private static final String TAG = "Bus2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view, container, false);

        ListView listsView = view.findViewById(R.id.list_view);
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listsView, false);
        listsView.addHeaderView(viewGroup);

        return view;
    }
}