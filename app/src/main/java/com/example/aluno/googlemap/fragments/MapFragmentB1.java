package com.example.aluno.googlemap.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapFragmentB1 extends SupportMapFragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragmentB1";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final String[] textMarcadoresB1 =
            new String[]{"CTF", "Procuradoria Federal", "Agespisa", "Posto R Sá", "Posto Fiscal dos Pontões",
                    "Posto Fiscal do Barão", "Posto de Combustível", "Hospital do Barão", "Posto de Combustível",
                    "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa", "Procuradoria Federal", "CTF"};
    private GoogleMap mMap;
    private ArrayList<LatLng> onibus1 = new ArrayList<>();

    /**
     * CTF -> -6.785664, -43.041863
     * PROCURADORIA FEDERAL-> -6.777723, -43.031713
     * AGESPISA -> -6.782055, -43.021958
     * POSTO R SA; -> -6.777710, -43.009763
     * POSTO FISCAL PONTOES -> -6.763639, -43.010490
     * POSTO FISCAL BARAO -> -6.755167, -43.013962
     * POSTO DE COMBUSTIVEL ->
     * HOSPITAL DO BARAO ->
     * POSTO DE COMBUSTIVEL ->
     * POSTO FISCAL DOS PONTOES -> -6.763639, -43.010490
     * POSTO R SA; -> -6.777710, -43.009763
     * FM -> -6.767777, -43.017825
     * AGESPISA -> -6.782055, -43.021958
     * PROCURADORIA FEDERAL -> -6.777723, -43.031713
     * CTF -> -6.785664, -43.041863
     */

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        enableMyLocation();
        Log.d(TAG, "onMapReady: Map Ready");

        LatLng ctf = new LatLng(-6.785664, -43.041863);
        mMap.addMarker(new MarkerOptions().position(ctf).title("Marcador no CTF"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, 13f));

        rotasBus1();
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
     * Traça as rotas do Primeiro Ônibus
     */
    private void rotasBus1() {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b1 = new ArrayList<>();

        onibus1.add(new LatLng(-6.785664, -43.041863)); //CTF
        onibus1.add(new LatLng(-6.785455, -43.042095));   //portao +
        onibus1.add(new LatLng(-6.777718, -43.033510));   //drogaria +
        onibus1.add(new LatLng(-6.777052, -43.033168));   //rotatoria +
        onibus1.add(new LatLng(-6.777723, -43.031713)); //PROCURADORIA
        onibus1.add(new LatLng(-6.782055, -43.021958)); //AGESPISA
        onibus1.add(new LatLng(-6.784738, -43.015697));   //curva+
        onibus1.add(new LatLng(-6.777710, -43.009763)); //POSTO R SA
        onibus1.add(new LatLng(-6.774250, -43.007421));  //curva +
        onibus1.add(new LatLng(-6.773099, -43.007534));  //curva2 +
        onibus1.add(new LatLng(-6.772417, -43.006933));  //curva3 +
        onibus1.add(new LatLng(-6.763649, -43.010132)); //POSTO FISCAL PONTOES
        onibus1.add(new LatLng(-6.756697, -43.012651));  //curva +
        onibus1.add(new LatLng(-6.755376, -43.013981));  //POSTO FISCAL BARAO
        onibus1.add(new LatLng(-6.755167, -43.013962)); //POSTO DE COMBUSTIVEL
        onibus1.add(new LatLng(-6.755167, -43.013962)); //HOSPITAL BARAO
        onibus1.add(new LatLng(-6.755167, -43.013962)); //POSTO DE COMBUSTIVEL
        onibus1.add(new LatLng(-6.756697, -43.012651));  //curva +
        onibus1.add(new LatLng(-6.763639, -43.010490)); //POSTO FISCAL PONTOES
        onibus1.add(new LatLng(-6.772417, -43.006933));  //curva +
        onibus1.add(new LatLng(-6.773099, -43.007534));  //curva2 +
        onibus1.add(new LatLng(-6.774250, -43.007421));  //curva3 +
        onibus1.add(new LatLng(-6.777710, -43.009763)); //POSTO R SA
        onibus1.add(new LatLng(-6.784738, -43.015697)); //FM
        onibus1.add(new LatLng(-6.782055, -43.021958)); //AGESPISA
        onibus1.add(new LatLng(-6.777723, -43.031713)); //PROCURADORIA
        onibus1.add(new LatLng(-6.777052, -43.033168)); //rotatoria +
        onibus1.add(new LatLng(-6.777718, -43.033510)); //drogaria +
        onibus1.add(new LatLng(-6.785455, -43.042095)); //portao +
        onibus1.add(new LatLng(-6.785664, -43.041863)); //CTF
        /*
         CTF -> -6.785664, -43.041863 +
         PROCURADORIA FEDERAL-> -6.777723, -43.031713 +
         AGESPISA -> -6.782055, -43.021958 +
         POSTO R SÁ -> -6.777710, -43.009763 +
         POSTO FISCAL PONTÕES -> -6.763639, -43.010490 +
         POSTO FISCAL BARÃO -> -6.755167, -43.013962 +
         POSTO DE COMBUSTÍVEL -> ?
         HOSPITAL DO BARÃO -> ?
         POSTO DE COMBUSTÍVEL -> ?
         POSTO FISCAL DOS PONTÕES -> -6.763639, -43.010490 +
         POSTO R SÁ -> -6.777710, -43.009763 +
         FM -> -6.784738, -43.015697 +
         AGESPISA -> -6.782055, -43.021958 +
         PROCURADORIA FEDERAL -> -6.777723, -43.031713 +
         CTF -> -6.785664, -43.041863 +
         */
        b1.add(new LatLng(-6.785664, -43.041863));
        b1.add(new LatLng(-6.777723, -43.031713));
        b1.add(new LatLng(-6.782055, -43.021958));
        b1.add(new LatLng(-6.777710, -43.009763));
        b1.add(new LatLng(-6.763649, -43.010132));
        b1.add(new LatLng(-6.755376, -43.013981));
        b1.add(new LatLng(-6.755167, -43.013962));
        b1.add(new LatLng(-6.755167, -43.013962));
        b1.add(new LatLng(-6.755167, -43.013962));
        b1.add(new LatLng(-6.763639, -43.010490));
        b1.add(new LatLng(-6.777710, -43.009763));
        b1.add(new LatLng(-6.784738, -43.015697));
        b1.add(new LatLng(-6.782055, -43.021958));
        b1.add(new LatLng(-6.777723, -43.031713));
        b1.add(new LatLng(-6.785664, -43.041863));

        //Percorre cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus1.size(); i++) {
            linha.add(onibus1.get(i));
        }
        //Adiciona marcadores no mapa com a descrição do ponto de parada
        for (int i = 0; i < b1.size(); i++) {
            mMap.addMarker(mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .position(b1.get(i)).title(textMarcadoresB1[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline(linha.color(Color.GREEN).width(4f));

    }
}