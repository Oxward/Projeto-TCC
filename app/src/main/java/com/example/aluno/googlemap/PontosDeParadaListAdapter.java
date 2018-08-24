package com.example.aluno.googlemap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class PontosDeParadaListAdapter extends ArrayAdapter<PontosDeParada>
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

    public PontosDeParadaListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PontosDeParada> objects)
    {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Informações dos pontos
        String hora = getItem(position).getHora();
        String parada = getItem(position).getParada();

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
            holder.hora = (TextView) convertView.findViewById(R.id.txtViewHora);
            holder.parada = (TextView) convertView.findViewById(R.id.txtViewPontoPar);
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

        holder.hora.setText(pontosDeParada.getHora());
        holder.parada.setText(pontosDeParada.getParada());

//        TextView txHora = convertView.findViewById(R.id.txtViewHora);
//        TextView txPontos = convertView.findViewById(R.id.txtViewHora);
//        txHora.setText(hora);
//        txPontos.setText(parada);

        return convertView;
    }
}