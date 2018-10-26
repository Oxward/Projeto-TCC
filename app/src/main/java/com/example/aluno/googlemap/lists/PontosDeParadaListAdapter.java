package com.example.aluno.googlemap.lists;

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
import java.util.Objects;

public class PontosDeParadaListAdapter extends ArrayAdapter<PontosDeParada>
{
    private static final String TAG = "PontosDeParadaListAdapt";
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder
    {
        TextView hora;
        TextView parada;
    }

    PontosDeParadaListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PontosDeParada> objects)
    {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Informações dos pontos
        String hora = Objects.requireNonNull(getItem(position)).getHoraPdP();
        String parada = Objects.requireNonNull(getItem(position)).getLocalPdP();

        //Cria um objeto ponto de parada
        PontosDeParada pontosDeParada = new PontosDeParada(hora, parada);

        //Cria uma view para "mostrar" a animação
        final View result;
        //Objeto ViewHolder
        ViewHolder holder;

        if( convertView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(mResource, parent, false);

            //Código android developers
            holder = new ViewHolder();
            holder.hora = convertView.findViewById(R.id.txtViewHora);
            holder.parada = convertView.findViewById(R.id.txtViewPontoPar);
            result = convertView;
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim );
        result.startAnimation(animation);
        lastPosition = position;

        holder.hora.setText(pontosDeParada.getHoraPdP());
        holder.parada.setText(pontosDeParada.getLocalPdP());

        return convertView;
    }

}