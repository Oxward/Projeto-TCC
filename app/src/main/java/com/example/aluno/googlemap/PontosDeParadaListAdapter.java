package com.example.aluno.googlemap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class PontosDeParadaListAdapter extends ArrayAdapter<PontosDeParada>
{
    private static final String TAG = "PontosDeParadaListAdapt";
    private Context context;


    public PontosDeParadaListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PontosDeParada> objects)
    {
        super(context, resource, objects);
    }
}