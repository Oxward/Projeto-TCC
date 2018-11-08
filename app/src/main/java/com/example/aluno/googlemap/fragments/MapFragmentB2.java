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

public class MapFragmentB2 extends SupportMapFragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragmentB2";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final String[] textMarcadoresB2 =
            new String[]{"CTF", "Procuradoria Federal", "Fartote Freitas", "Educandário", "Paraíba", "Antiga Yamaha",
                    "Hotel Rio Parnaíba", "Rodoviária Nova", "Posto R Sá", "Rádio FM", "Agespisa",
                    "Procuradoria Federal", "CTF"};
    private GoogleMap mMap;
    private ArrayList<LatLng> onibus2 = new ArrayList<>();

    /**
     * CTF -> -6.785664, -43.041863
     * PROCURADORIA FEDERAL -> -6.777723, -43.031713
     * FREITAS -> -6.781160, -43.022939
     * EDUCANDARIO -> -6.771816, -43.023986
     * PARAIBA -> -6.768726, -43.019117
     * ANTIGA YAMAHA -> -6.771097, -43.012466
     * HOTEL POUSADA -> -6.774108, -43.009409
     * RODOVIA;RIA NOVA -> -6.784655, -42.996132
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, 15f));

        rotasBus2();
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
     * Traça as rotas do Segundo Ônibus
     */
    private void rotasBus2() {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b2 = new ArrayList<>();

        onibus2.add(new LatLng(-6.785664, -43.041863)); //CTF +
        onibus2.add(new LatLng(-6.785455, -43.042095)); //portao +
        onibus2.add(new LatLng(-6.777718, -43.033510)); //drogaria +
        onibus2.add(new LatLng(-6.777052, -43.033168)); //rotatoria +
        onibus2.add(new LatLng(-6.777723, -43.031713));//PROCURADORIA +
        onibus2.add(new LatLng(-6.781280, -43.023365));//antes freitas +
        onibus2.add(new LatLng(-6.771816, -43.023986));//EDUCANDARIO
        onibus2.add(new LatLng(-6.769026, -43.024119));//esquina após educ
        onibus2.add(new LatLng(-6.768677, -43.019115));//PARAIBA
        onibus2.add(new LatLng(-6.768524, -43.017495));//curva1
        onibus2.add(new LatLng(-6.768868, -43.016999));//curva2
        onibus2.add(new LatLng(-6.771097, -43.012466));//ANTIGA YAMAHA
        onibus2.add(new LatLng(-6.771260, -43.012229));//curva1
        onibus2.add(new LatLng(-6.772475, -43.011449));//curva2
        onibus2.add(new LatLng(-6.773010, -43.010518));//curva3
        onibus2.add(new LatLng(-6.774108, -43.009409));//HOTEL
        onibus2.add(new LatLng(-6.778520, -43.004591));//curva
        onibus2.add(new LatLng(-6.778523, -43.004476));//curva2
        onibus2.add(new LatLng(-6.780612, -43.002353));//curva3
        onibus2.add(new LatLng(-6.781262, -43.001591));//curva4
        onibus2.add(new LatLng(-6.784655, -42.996132));//RODOVIARIA
        onibus2.add(new LatLng(-6.778669, -43.004371));//curva
        onibus2.add(new LatLng(-6.778930, -43.005090));//curva2
        onibus2.add(new LatLng(-6.778664, -43.005149));//curva3
        onibus2.add(new LatLng(-6.778824, -43.005836));//curva4
        onibus2.add(new LatLng(-6.778424, -43.006458));//curva5
        onibus2.add(new LatLng(-6.778170, -43.006652));//curva6
        onibus2.add(new LatLng(-6.776103, -43.008721));//curva7
        onibus2.add(new LatLng(-6.777710, -43.009763));//POSTO R SA
        onibus2.add(new LatLng(-6.784738, -43.015697));//FM
        onibus2.add(new LatLng(-6.782055, -43.021958));//AGESPISA
        onibus2.add(new LatLng(-6.777723, -43.031713));//PROCURADORIA
        onibus2.add(new LatLng(-6.777052, -43.033168));//rotatoria trevo
        onibus2.add(new LatLng(-6.777718, -43.033510));//drogaria
        onibus2.add(new LatLng(-6.785455, -43.042095));//portao ctf
        onibus2.add(new LatLng(-6.785664, -43.041863));//CTF
        /*
         * CTF -> -6.785664, -43.041863 +
         *   PORTAO CTF -> -6.785455, -43.042095+
         *   DROGARIA -> -6.777718, -43.033510+
         *   ROTATÓRIA TREVO -> -6.777052, -43.033168+
         * PROCURADORIA FEDERAL -> -6.777723, -43.031713 +
         *   ANTES DO FREITAS -> -6.781280, -43.023365 +
         * FREITAS -> -6.781160, -43.022939 +
         * EDUCANDARIO -> -6.771816, -43.023986 +
         *   ESQUINA APÓS EDUC -> -6.769026, -43.024119 +
         * PARAIBA -> -6.768677, -43.019115 +
         *   CURVA -> -6.768524, -43.017495 +
         *   CURVA2 -> -6.768868, -43.016999 +
         * ANTIGA YAMAHA -> -6.771097, -43.012466 +
         *   CURVA -> -6.771260, -43.012229 +
         *   CURVA2 -> -6.772475, -43.011449 +
         *   CURVA3 -> -6.773010, -43.010518 +
         * HOTEL POUSADA -> -6.774108, -43.009409 +
         *   CURVA -> -6.778520, -43.004591 +
         *   CURVA2 -> -6.778523, -43.004476 +
         *   CURVA3 -> -6.780612, -43.002353 +
         *   CURVA4 -> -6.781262, -43.001591 +
         * RODOVIARIA NOVA -> -6.784655, -42.996132 +
         *   CURVA -> -6.778669, -43.004371 +
         *   CURVA2 -> -6.778930, -43.005090 +
         *   CURVA3 -> -6.778664, -43.005149 +
         *   CURVA4 -> -6.778824, -43.005836 +
         *   CURVA5 -> -6.778424, -43.006458 +
         *   CURVA6 -> -6.778170, -43.006652 +
         *   CURVA7 -> -6.776103, -43.008721 +
         * POSTO R SA; -> -6.777710, -43.009763 +
         * FM -> -6.784738, -43.015697 +
         * AGESPISA -> -6.782055, -43.021958 +
         * PROCURADORIA FEDERAL -> -6.777723, -43.031713 +
         *   ROTATÓRIA TREVO -> -6.777052, -43.033168
         *   DROGARIA -> -6.777718, -43.033510
         *   PORTAO CTF -> -6.785455, -43.042095
         * CTF -> -6.785664, -43.041863 +
         */

        b2.add(new LatLng(-6.785664, -43.041863));
        b2.add(new LatLng(-6.777723, -43.031713));
        b2.add(new LatLng(-6.781160, -43.022939));
        b2.add(new LatLng(-6.771816, -43.023986));
        b2.add(new LatLng(-6.768677, -43.019115));
        b2.add(new LatLng(-6.771097, -43.012466));
        b2.add(new LatLng(-6.774108, -43.009409));
        b2.add(new LatLng(-6.784655, -42.996132));
        b2.add(new LatLng(-6.777710, -43.009763));
        b2.add(new LatLng(-6.784738, -43.015697));
        b2.add(new LatLng(-6.782055, -43.021958));
        b2.add(new LatLng(-6.777723, -43.031713));
        b2.add(new LatLng(-6.785664, -43.041863));

        //Percorrer cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus2.size(); i++) {
            linha.add(onibus2.get(i));
        }

        //Adiciona marcadores com a descrição do ponto de parada
        for (int i = 0; i < b2.size(); i++) {
            mMap.addMarker(mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .position(b2.get(i)).title(textMarcadoresB2[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline(linha.color(Color.RED).width(4f));
    }
}
