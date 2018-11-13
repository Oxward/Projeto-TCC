package com.example.aluno.googlemap.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aluno.googlemap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapFragmentB3 extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragmentB3";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final String[] textMarcadoresB3 =
            new String[]{"CTF", "Procuradoria Federal", "Fartote Freitas", "Agespisa", "Rádio FM", "Posto R de Sá",
                    "Posto Fiscal do Barão", "Posto Fiscal dos Pontões", "Barão", "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa",
                    "Procuradoria Federal", "CTF"};
    private GoogleMap mMap;
    private ArrayList<LatLng> onibus3 = new ArrayList<>();
    private View mView;
    /**
     * CTF ->  -6.785664, -43.041863
     * PROCURADORIA FEDERAL -> -6.777723, -43.031713
     * FREITAS -> -6.781160, -43.022939
     * AGESPISA -> -6.782055, -43.021958
     * FM -> -6.767777, -43.017825
     * POSTO R. SA; -> -6.777710, -43.009763
     * RODOVIARIA NOVA -> -6.784655, -42.996132
     * POSTO FISCAL PONTO;ES -> -6.763639, -43.010490
     * BARAO -> -6.754303, -43.026132
     * POSTO FISCAL PONTOES -> -6.763639, -43.010490
     * POSTO R SA; -> -6.777710, -43.009763
     * FM -> -6.767777, -43.017825
     * AGESPISA -> -6.782055, -43.021958
     * PROCURADORIA FEDERAL -> -6.777723, -43.031713
     * CTF ->  -6.785664, -43.041863
     */

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_maps, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapView mMapView = mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        enableMyLocation();
        Log.d(TAG, "onMapReady: Map Ready");

        LatLng ctf = new LatLng(-6.785664, -43.041863);
        mMap.addMarker(new MarkerOptions().position(ctf).title("Marcador no CTF"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, 13f));

        rotasBus3();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    //Ativa a Localização Atual(GPS)
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    /*
     * Traça as rotas do Terceiro Ônibus
     */
    private void rotasBus3() {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b3 = new ArrayList<>();

        onibus3.add(new LatLng(-6.785664, -43.041863)); //CTF +
        onibus3.add(new LatLng(-6.785455, -43.042095)); //portao +
        onibus3.add(new LatLng(-6.777718, -43.033510)); //drogaria +
        onibus3.add(new LatLng(-6.777052, -43.033168)); //rotatoria +
        onibus3.add(new LatLng(-6.777723, -43.031713)); //PROCURADORIA FEDERAL +
        onibus3.add(new LatLng(-6.781453, -43.023127)); //FREITAS +
        onibus3.add(new LatLng(-6.782055, -43.021958)); //AGESPISA +
        onibus3.add(new LatLng(-6.784738, -43.015697)); //FM +
        onibus3.add(new LatLng(-6.777710, -43.009763)); //POSTO R SA +

        onibus3.add(new LatLng(-6.774165, -43.007390)); //curva3
        onibus3.add(new LatLng(-6.773200, -43.007552)); //curva2
        onibus3.add(new LatLng(-6.772401, -43.006962)); //curva

        onibus3.add(new LatLng(-6.763506, -43.010171)); //POSTO FISCAL PONTOES +
        onibus3.add(new LatLng(-6.756897, -43.012577)); //curva +
        onibus3.add(new LatLng(-6.755963, -43.013278)); //curva2 +
        onibus3.add(new LatLng(-6.755357, -43.014076)); //curva3 +
        onibus3.add(new LatLng(-6.754952, -43.015042)); //curva4 +
        onibus3.add(new LatLng(-6.754851, -43.016807)); //curva5 +
        onibus3.add(new LatLng(-6.755395, -43.022050)); //curva6 +
        onibus3.add(new LatLng(-6.755253, -43.023092)); //curva7 +
        onibus3.add(new LatLng(-6.754303, -43.026132)); //BARAO +
        onibus3.add(new LatLng(-6.755253, -43.023092)); //curva7 +
        onibus3.add(new LatLng(-6.755395, -43.022050)); //curva6 +
        onibus3.add(new LatLng(-6.754851, -43.016807)); //curva5 +
        onibus3.add(new LatLng(-6.754952, -43.015042)); //curva4 +
        onibus3.add(new LatLng(-6.755357, -43.014076)); //curva3 +
        onibus3.add(new LatLng(-6.755963, -43.013278)); //curva2 +
        onibus3.add(new LatLng(-6.756897, -43.012577)); //curva +
        onibus3.add(new LatLng(-6.763506, -43.010171)); //POSTO FISCAL PONTOES
        onibus3.add(new LatLng(-6.772401, -43.006962)); //curva
        onibus3.add(new LatLng(-6.773200, -43.007552)); //curva2
        onibus3.add(new LatLng(-6.774165, -43.007390)); //curva3
        onibus3.add(new LatLng(-6.777710, -43.009763)); //POSTO R SA
        onibus3.add(new LatLng(-6.784738, -43.015697)); //FM
        onibus3.add(new LatLng(-6.782055, -43.021958)); //AGESPISA
        onibus3.add(new LatLng(-6.777723, -43.031713)); //PROCURADORIA FEDERAL
        onibus3.add(new LatLng(-6.777052, -43.033168)); //rotatoria +
        onibus3.add(new LatLng(-6.777718, -43.033510)); //drogaria +
        onibus3.add(new LatLng(-6.785455, -43.042095)); //portao +
        onibus3.add(new LatLng(-6.785664, -43.041863)); //CTF
        /*
         CTF ->  -6.785664, -43.041863 +
         PROCURADORIA FEDERAL -> -6.777723, -43.031713 +
         FREITAS -> -6.781160, -43.022939 +
         AGESPISA -> -6.782055, -43.021958 +
         FM -> -6.784738, -43.015697 +
         POSTO R. SA; -> -6.777710, -43.009763 +
         RODOVIARIA NOVA -> -6.784655, -42.996132 +
         POSTO FISCAL PONTOES -> -6.763639, -43.010490 +
         BARAO -> -6.754303, -43.026132 +
         POSTO FISCAL PONTOES -> -6.763639, -43.010490 +
         POSTO R SA -> -6.777710, -43.009763 +
         FM -> -6.784738, -43.015697 +
         AGESPISA -> -6.782055, -43.021958 +
         PROCURADORIA FEDERAL -> -6.777723, -43.031713 +
         CTF ->  -6.785664, -43.041863
         */

        b3.add(new LatLng(-6.785664, -43.041863));
        b3.add(new LatLng(-6.777723, -43.031713));
        b3.add(new LatLng(-6.781160, -43.022939));
        b3.add(new LatLng(-6.782055, -43.021958));
        b3.add(new LatLng(-6.784738, -43.015697));
        b3.add(new LatLng(-6.777710, -43.009763));
        b3.add(new LatLng(-6.755376, -43.013981));
        b3.add(new LatLng(-6.763639, -43.010490));
        b3.add(new LatLng(-6.754303, -43.026132));
        b3.add(new LatLng(-6.763639, -43.010490));
        b3.add(new LatLng(-6.777710, -43.009763));
        b3.add(new LatLng(-6.784738, -43.015697));
        b3.add(new LatLng(-6.782055, -43.021958));
        b3.add(new LatLng(-6.777723, -43.031713));
        b3.add(new LatLng(-6.785664, -43.041863));

        //Percorre cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus3.size(); i++) {
            linha.add(onibus3.get(i));
        }

        //Adiciona marcadores com a descrição do ponto de parada
        for (int i = 0; i < b3.size(); i++) {
            mMap.addMarker(mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(b3.get(i)).title(textMarcadoresB3[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline(linha.color(Color.BLUE).width(4f));
    }
}
