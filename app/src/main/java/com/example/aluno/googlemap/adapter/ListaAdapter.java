package com.example.aluno.googlemap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aluno.googlemap.R;
import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.ArrayList;

public class ListaAdapter extends ArrayAdapter<PontosDeParada> {

    private static final String TAG = "ListaAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    public ListaAdapter(Context mContext, int resource, ArrayList<PontosDeParada> mList) {
        super(mContext, resource, mList);
        this.mContext = mContext;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String hora = getItem(position).getHoraPdP();
        String parada = String.valueOf(getItem(position).getLocalPdP());

        PontosDeParada pdp = new PontosDeParada(hora, parada);

        final View result;
        ViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(mResource, parent, false);

            mViewHolder = new ViewHolder();
            mViewHolder.hora = convertView.findViewById(R.id.txtViewHora);
            mViewHolder.parada = convertView.findViewById(R.id.txtViewPontoPar);

            result = convertView;
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation mAnimation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(mAnimation);
        lastPosition = position;

        mViewHolder.hora.setText(pdp.getHoraPdP());
        mViewHolder.parada.setText(pdp.getLocalPdP());

        return convertView;
    }

    static class ViewHolder {
        TextView hora;
        TextView parada;
    }
}