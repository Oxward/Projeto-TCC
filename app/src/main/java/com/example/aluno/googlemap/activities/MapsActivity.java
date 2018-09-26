package com.example.aluno.googlemap.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aluno.googlemap.DirectionsParser;
import com.example.aluno.googlemap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private ArrayList<LatLng> listaPontos = new ArrayList<>();

    private ArrayList<LatLng> onibus1 = new ArrayList<>();
    private final String[] textMarcadoresB1 =
            new String[]{"CTF", "Procuradoria Federal", "Agespisa", "Posto R Sá", "Posto Fiscal dos Pontões",
                         "Posto Fiscal do Barão", "Posto de Combustível", "Hospital do Barão", "Posto de Combustível",
                         "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa", "Procuradoria Federal", "CTF"};
    /**
     CTF -> -6.785664, -43.041863
     PROCURADORIA FEDERAL-> -6.777723, -43.031713
     AGESPISA -> -6.782055, -43.021958
     POSTO R SA; -> -6.777710, -43.009763
     POSTO FISCAL PONTOES -> -6.763639, -43.010490
     POSTO FISCAL BARAO -> -6.755167, -43.013962
     POSTO DE COMBUSTIVEL ->
     HOSPITAL DO BARAO ->
     POSTO DE COMBUSTIVEL ->
     POSTO FISCAL DOS PONTOES -> -6.763639, -43.010490
     POSTO R SA; -> -6.777710, -43.009763
     FM -> -6.767777, -43.017825
     AGESPISA -> -6.782055, -43.021958
     PROCURADORIA FEDERAL -> -6.777723, -43.031713
     CTF -> -6.785664, -43.041863
     */
    private ArrayList<LatLng> onibus2 = new ArrayList<>();
    private final String[] textMarcadoresB2 =
            new String[] {"CTF", "Procuradoria Federal", "Fartote Freitas", "Educandário", "Paraíba", "Antiga Yamaha",
                          "Hotel Rio Parnaíba", "Rodoviária Nova", "Posto R Sá", "Rádio FM", "Agespisa",
                          "Procuradoria Federal", "CTF"};
    /**
     CTF -> -6.785664, -43.041863
     PROCURADORIA FEDERAL -> -6.777723, -43.031713
     FREITAS -> -6.781160, -43.022939
     EDUCANDARIO -> -6.771816, -43.023986
     PARAIBA -> -6.768726, -43.019117
     ANTIGA YAMAHA -> -6.771097, -43.012466
     HOTEL POUSADA -> -6.774108, -43.009409
     RODOVIA;RIA NOVA -> -6.784655, -42.996132
     POSTO R SA; -> -6.777710, -43.009763
     FM -> -6.767777, -43.017825
     AGESPISA -> -6.782055, -43.021958
     PROCURADORIA FEDERAL -> -6.777723, -43.031713
     CTF -> -6.785664, -43.041863
     */
    private ArrayList<LatLng> onibus3 = new ArrayList<>();
    private final String[] textMarcadoresB3 =
            new String[] {"CTF", "Procuradoria Federal", "Fartote Freitas", "Agespisa", "Rádio FM", "Posto R de Sá", "Rodoviária Nova",
                          "Posto Fiscal dos Pontões", "Barão", "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa",
                          "Procuradoria Federal", "CTF"};
    /**
     CTF ->  -6.785664, -43.041863
     PROCURADORIA FEDERAL -> -6.777723, -43.031713
     FREITAS -> -6.781160, -43.022939
     AGESPISA -> -6.782055, -43.021958
     FM -> -6.767777, -43.017825
     POSTO R. SA; -> -6.777710, -43.009763
     RODOVIARIA NOVA -> -6.784655, -42.996132
     POSTO FISCAL PONTO;ES -> -6.763639, -43.010490
     BARAO -> -6.754303, -43.026132
     POSTO FISCAL PONTOES -> -6.763639, -43.010490
     POSTO R SA; -> -6.777710, -43.009763
     FM -> -6.767777, -43.017825
     AGESPISA -> -6.782055, -43.021958
     PROCURADORIA FEDERAL -> -6.777723, -43.031713
     CTF ->  -6.785664, -43.041863
     */


    //Cria um Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Menu que contém as rotas de cada Ônibus
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.bus1:
                mMap.clear();
                rotasBus1();
                return true;

            case R.id.bus2:
                mMap.clear();
                rotasBus2();
                return true;

            case R.id.bus3:
                mMap.clear();
                rotasBus3();
                return true;

            case R.id.limpaRota:
                mMap.clear();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    //Rotas ao pressionar os botões de demonstração
    public void buttonPress(View view)
    {
        switch ( view.getId() )
        {
            case R.id.btBus1:
                mMap.clear();
                rotasBus1();
                break;

            case R.id.btBus2:
                mMap.clear();
                rotasBus2();
                break;

            case R.id.btBus3:
                mMap.clear();
                rotasBus3();
                break;
        }
    }

    //Adiciona o mapa ao fragment(layouts)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        enableMyLocation();
        // Adiciona um marcador no CTF e move a câmera
        LatLng ctf = new LatLng(-6.785664, -43.041863);
        mMap.addMarker(new MarkerOptions().position(ctf).title("Marcador no CTF"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, 15f));
        //GroundOverlayOptions homeOverlay = new GroundOverlayOptions()
        //                .image(BitmapDescriptorFactory.fromResource(R.drawable.android)).position(ctf, 100);
        //        //mMap.addGroundOverlay(homeOverlay);
        //        //setMapLongClick(mMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch(requestCode)
        {
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    enableMyLocation();
                    break;
                }
        }
    }

    /**
     * Traça as rotas do Primeiro Ônibus
     */
    private void rotasBus1()
    {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b1 = new ArrayList<>();

        onibus1.add( new LatLng(-6.785664, -43.041863 )); //CTF
         onibus1.add( new LatLng(-6.785455, -43.042095 ));   //portao +
         onibus1.add( new LatLng(-6.777718, -43.033510 ));   //drogaria +
         onibus1.add( new LatLng(-6.777052, -43.033168 ));   //rotatoria +
        onibus1.add( new LatLng(-6.777723, -43.031713 )); //PROCURADORIA
        onibus1.add( new LatLng(-6.782055, -43.021958 )); //AGESPISA
         onibus1.add( new LatLng(-6.784738, -43.015697 ));   //curva+
        onibus1.add( new LatLng(-6.777710, -43.009763 )); //POSTO R SA
         onibus1.add( new LatLng(-6.774250, -43.007421 ));  //curva +
         onibus1.add( new LatLng(-6.773099, -43.007534 ));  //curva2 +
         onibus1.add( new LatLng(-6.772417, -43.006933 ));  //curva3 +
        onibus1.add( new LatLng(-6.763649, -43.010132 )); //POSTO FISCAL PONTOES
         onibus1.add( new LatLng(-6.756697, -43.012651 ));  //curva +
        onibus1.add( new LatLng(-6.755376, -43.013981 ));  //POSTO FISCAL BARAO
        onibus1.add( new LatLng(-6.755167, -43.013962 )); //POSTO DE COMBUSTIVEL
        onibus1.add( new LatLng(-6.755167, -43.013962 )); //HOSPITAL BARAO
        onibus1.add( new LatLng(-6.755167, -43.013962 )); //POSTO DE COMBUSTIVEL
         onibus1.add( new LatLng(-6.756697, -43.012651 ));  //curva +
        onibus1.add( new LatLng(-6.763639, -43.010490 )); //POSTO FISCAL PONTOES
         onibus1.add( new LatLng(-6.772417, -43.006933 ));  //curva +
         onibus1.add( new LatLng(-6.773099, -43.007534 ));  //curva2 +
         onibus1.add( new LatLng(-6.774250, -43.007421 ));  //curva3 +
        onibus1.add( new LatLng(-6.777710, -43.009763 )); //POSTO R SA
        onibus1.add( new LatLng(-6.784738, -43.015697 )); //FM
        onibus1.add( new LatLng(-6.782055, -43.021958 )); //AGESPISA
        onibus1.add( new LatLng(-6.777723, -43.031713 )); //PROCURADORIA
         onibus1.add( new LatLng(-6.777052, -43.033168 )); //rotatoria +
         onibus1.add( new LatLng(-6.777718, -43.033510 )); //drogaria +
         onibus1.add( new LatLng(-6.785455, -43.042095 )); //portao +
        onibus1.add( new LatLng(-6.785664, -43.041863 )); //CTF
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
        b1.add( new LatLng(-6.785664, -43.041863) );
        b1.add( new LatLng(-6.777723, -43.031713) );
        b1.add( new LatLng(-6.782055, -43.021958) );
        b1.add( new LatLng(-6.777710, -43.009763) );
        b1.add( new LatLng(-6.763649, -43.010132) );
        b1.add( new LatLng(-6.755376, -43.013981) );
        b1.add( new LatLng(-6.755167, -43.013962) );
        b1.add( new LatLng(-6.755167, -43.013962) );
        b1.add( new LatLng(-6.755167, -43.013962) );
        b1.add( new LatLng(-6.763639, -43.010490) );
        b1.add( new LatLng(-6.777710, -43.009763) );
        b1.add( new LatLng(-6.784738, -43.015697) );
        b1.add( new LatLng(-6.782055, -43.021958) );
        b1.add( new LatLng(-6.777723, -43.031713) );
        b1.add( new LatLng(-6.785664, -43.041863) );

        //Percorre cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus1.size(); i++)
        {
            linha.add( onibus1.get(i) );
        }
        //Adiciona marcadores no mapa com a descrição do ponto de parada
        for (int i = 0; i < b1.size(); i++)
        {
            mMap.addMarker( mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .position(b1.get(i)).title(textMarcadoresB1[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline( linha.color(Color.GREEN).width(4f) );

    }

    /**
     * Traça as rotas do Segundo Ônibus
     */
    private void rotasBus2()
    {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b2 = new ArrayList<>();

        onibus2.add( new LatLng(-6.785664, -43.041863 )); //CTF +
         onibus2.add( new LatLng(-6.785455, -43.042095 )); //portao +
         onibus2.add( new LatLng(-6.777718, -43.033510 )); //drogaria +
         onibus2.add( new LatLng(-6.777052, -43.033168 )); //rotatoria +
        onibus2.add( new LatLng(-6.777723, -43.031713 ));//PROCURADORIA +
         onibus2.add( new LatLng(-6.781280, -43.023365 ));//antes freitas +
        onibus2.add( new LatLng(-6.771816, -43.023986 ));//EDUCANDARIO
         onibus2.add( new LatLng(-6.769026, -43.024119 ));//esquina após educ
        onibus2.add( new LatLng(-6.768677, -43.019115 ));//PARAIBA
         onibus2.add( new LatLng(-6.768524, -43.017495 ));//curva1
         onibus2.add( new LatLng(-6.768868, -43.016999 ));//curva2
        onibus2.add( new LatLng(-6.771097, -43.012466 ));//ANTIGA YAMAHA
         onibus2.add( new LatLng(-6.771260, -43.012229 ));//curva1
         onibus2.add( new LatLng(-6.772475, -43.011449 ));//curva2
         onibus2.add( new LatLng(-6.773010, -43.010518 ));//curva3
        onibus2.add( new LatLng(-6.774108, -43.009409 ));//HOTEL
         onibus2.add( new LatLng(-6.778520, -43.004591 ));//curva
         onibus2.add( new LatLng(-6.778523, -43.004476 ));//curva2
         onibus2.add( new LatLng(-6.780612, -43.002353 ));//curva3
         onibus2.add( new LatLng(-6.781262, -43.001591 ));//curva4
        onibus2.add( new LatLng(-6.784655, -42.996132 ));//RODOVIARIA
         onibus2.add( new LatLng(-6.778669, -43.004371 ));//curva
         onibus2.add( new LatLng(-6.778930, -43.005090 ));//curva2
         onibus2.add( new LatLng(-6.778664, -43.005149 ));//curva3
         onibus2.add( new LatLng(-6.778824, -43.005836 ));//curva4
         onibus2.add( new LatLng(-6.778424, -43.006458 ));//curva5
         onibus2.add( new LatLng(-6.778170, -43.006652 ));//curva6
         onibus2.add( new LatLng(-6.776103, -43.008721 ));//curva7
        onibus2.add( new LatLng(-6.777710, -43.009763 ));//POSTO R SA
        onibus2.add( new LatLng(-6.784738, -43.015697 ));//FM
        onibus2.add( new LatLng(-6.782055, -43.021958 ));//AGESPISA
        onibus2.add( new LatLng(-6.777723, -43.031713 ));//PROCURADORIA
         onibus2.add( new LatLng(-6.777052, -43.033168 ));//rotatoria trevo
         onibus2.add( new LatLng(-6.777718, -43.033510 ));//drogaria
         onibus2.add( new LatLng(-6.785455, -43.042095 ));//portao ctf
        onibus2.add( new LatLng(-6.785664, -43.041863 ));//CTF
        /*
         *
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
        b2.add( new LatLng(-6.785664, -43.041863) );
        b2.add( new LatLng(-6.777723, -43.031713) );
        b2.add( new LatLng(-6.781160, -43.022939) );
        b2.add( new LatLng(-6.771816, -43.023986) );
        b2.add( new LatLng(-6.768677, -43.019115) );
        b2.add( new LatLng(-6.771097, -43.012466) );
        b2.add( new LatLng(-6.774108, -43.009409) );
        b2.add( new LatLng(-6.784655, -42.996132) );
        b2.add( new LatLng(-6.777710, -43.009763) );
        b2.add( new LatLng(-6.784738, -43.015697) );
        b2.add( new LatLng(-6.782055, -43.021958) );
        b2.add( new LatLng(-6.777723, -43.031713) );
        b2.add( new LatLng(-6.785664, -43.041863) );

        //Percorrer cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus2.size() ; i++)
        {
            linha.add( onibus2.get(i) );
        }

        //Adiciona marcadores com a descrição do ponto de parada
        for (int i = 0; i < b2.size(); i++)
        {
            mMap.addMarker( mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .position(b2.get(i)).title(textMarcadoresB2[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline( linha.color(Color.RED).width(4f) );
    }

    /**
     * Traça as rotas do Terceiro Ônibus
     */
    private void rotasBus3()
    {
        PolylineOptions linha = new PolylineOptions(); //Desenha as linhas no mapa
        MarkerOptions mark = new MarkerOptions(); //Marcador para os pontos de parada
        ArrayList<LatLng> b3 = new ArrayList<>();

        onibus3.add( new LatLng(-6.785664, -43.041863 )); //CTF +
         onibus3.add( new LatLng(-6.785455, -43.042095 )); //portao +
         onibus3.add( new LatLng(-6.777718, -43.033510 )); //drogaria +
         onibus3.add( new LatLng(-6.777052, -43.033168 )); //rotatoria +
        onibus3.add( new LatLng(-6.777723, -43.031713 )); //PROCURADORIA FEDERAL +
        onibus3.add( new LatLng(-6.781453, -43.023127 )); //FREITAS +
        onibus3.add( new LatLng(-6.782055, -43.021958 )); //AGESPISA +
        onibus3.add( new LatLng(-6.784738, -43.015697 )); //FM +
        onibus3.add( new LatLng(-6.777710, -43.009763 )); //POSTO R SA +
         onibus3.add( new LatLng(-6.775273, -43.008032 )); //curva +
         onibus3.add( new LatLng(-6.778453, -43.004693 )); //curva2 +
         onibus3.add( new LatLng(-6.778544, -43.004475 )); //curva3 +
        onibus3.add( new LatLng(-6.784655, -42.996132 )); //RODOVIARIA NOVA +
         onibus3.add( new LatLng(-6.778589, -43.004469 )); //curva +
         onibus3.add( new LatLng(-6.777685, -43.005003 )); //curva2 +
        onibus3.add( new LatLng(-6.763506, -43.010171 )); //POSTO FISCAL PONTOES +
         onibus3.add( new LatLng(-6.756897, -43.012577 )); //curva +
         onibus3.add( new LatLng(-6.755963, -43.013278 )); //curva2 +
         onibus3.add( new LatLng(-6.755357, -43.014076 )); //curva3 +
         onibus3.add( new LatLng(-6.754952, -43.015042 )); //curva4 +
         onibus3.add( new LatLng(-6.754851, -43.016807 )); //curva5 +
         onibus3.add( new LatLng(-6.755395, -43.022050 )); //curva6 +
         onibus3.add( new LatLng(-6.755253, -43.023092 )); //curva7 +
        onibus3.add( new LatLng(-6.754303, -43.026132 )); //BARAO +
         onibus3.add( new LatLng(-6.755253, -43.023092 )); //curva7 +
         onibus3.add( new LatLng(-6.755395, -43.022050 )); //curva6 +
         onibus3.add( new LatLng(-6.754851, -43.016807 )); //curva5 +
         onibus3.add( new LatLng(-6.754952, -43.015042 )); //curva4 +
         onibus3.add( new LatLng(-6.755357, -43.014076 )); //curva3 +
         onibus3.add( new LatLng(-6.755963, -43.013278 )); //curva2 +
         onibus3.add( new LatLng(-6.756897, -43.012577 )); //curva +
        onibus3.add( new LatLng(-6.763506, -43.010171 )); //POSTO FISCAL PONTOES
         onibus3.add( new LatLng(-6.772401, -43.006962 )); //curva
         onibus3.add( new LatLng(-6.773200, -43.007552 )); //curva2
         onibus3.add( new LatLng(-6.774165, -43.007390 )); //curva3
        onibus3.add( new LatLng(-6.777710, -43.009763 )); //POSTO R SA
        onibus3.add( new LatLng(-6.784738, -43.015697 )); //FM
        onibus3.add( new LatLng(-6.782055, -43.021958 )); //AGESPISA
        onibus3.add( new LatLng(-6.777723, -43.031713 )); //PROCURADORIA FEDERAL
         onibus3.add( new LatLng(-6.777052, -43.033168 )); //rotatoria +
         onibus3.add( new LatLng(-6.777718, -43.033510 )); //drogaria +
         onibus3.add( new LatLng(-6.785455, -43.042095 )); //portao +
        onibus3.add( new LatLng(-6.785664, -43.041863 )); //CTF
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

        b3.add( new LatLng(-6.785664, -43.041863) );
        b3.add( new LatLng(-6.777723, -43.031713) );
        b3.add( new LatLng(-6.781160, -43.022939) );
        b3.add( new LatLng(-6.782055, -43.021958) );
        b3.add( new LatLng(-6.784738, -43.015697) );
        b3.add( new LatLng(-6.777710, -43.009763) );
        b3.add( new LatLng(-6.784655, -42.996132) );
        b3.add( new LatLng(-6.763639, -43.010490) );
        b3.add( new LatLng(-6.754303, -43.026132) );
        b3.add( new LatLng(-6.763639, -43.010490) );
        b3.add( new LatLng(-6.777710, -43.009763) );
        b3.add( new LatLng(-6.784738, -43.015697) );
        b3.add( new LatLng(-6.782055, -43.021958) );
        b3.add( new LatLng(-6.777723, -43.031713) );
        b3.add( new LatLng(-6.785664, -43.041863) );

        //Percorre cada LatLon e desenha uma linha entre elas
        for (int i = 0; i < onibus3.size(); i++)
        {
            linha.add( onibus3.get(i) );
        }

        //Adiciona marcadores com a descrição do ponto de parada
        for (int i = 0; i < b3.size(); i++)
        {
            mMap.addMarker( mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(b3.get(i)).title(textMarcadoresB3[i]));
        }

        //Adiciona o desenho do laço anterior ao mapa
        mMap.addPolyline( linha.color(Color.BLUE).width(4f));
    }


    /**
     * Adiciona Marcadores de Forma Manual
     * Não faz parte do Trabalho
     */
    private void setMapLongClick(final GoogleMap map)
    {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (listaPontos.size() == 2)
                {
                    listaPontos.clear();
                    mMap.clear();
                }
                listaPontos.add(latLng);
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(latLng);
                if(listaPontos.size() == 1)
                {
                    //primeiro marcador
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else
                {
                    //segundo marcador
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                map.addMarker(marcador);
                String info = String.format(Locale.getDefault(), "Lat: %1$.5f, Long: %1$.5f",
                        latLng.latitude, latLng.longitude);
                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                        .position(latLng).title("Marcador").snippet(info));

                if(listaPontos.size() == 2)
                {
                    String url = getRequestedUrl(listaPontos.get(0), listaPontos.get(1));
                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(url);
                }
            }
        });
    }

    //Solicita URL Para Marcar a Rota Entre os Pontos
    private String getRequestedUrl(LatLng orig, LatLng dest)
    {
        String origem = "origin="+orig.latitude+ ","+orig.longitude;  //LatLong ponto de origem
        String destino = "destination="+dest.latitude+ ","+dest.longitude; //LatLong ponto de destino
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String param = origem+ "&" +destino+ "&" +sensor+ "&" +mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" +output+ "?" +param;
        return url;
    }

    //Traça uma Rota Entre os Pontos Demarcados
    private String requestDirections(String reqUrl)
    {
        String respString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try
        {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //resultado do requerimento
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String linha = "";

            while( (linha = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(linha);
            }

            respString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        }catch(IOException e)
        {
            e.getStackTrace();
        }finally
        {
            if(inputStream != null)
                try
                {
                    inputStream.close();
                }catch(IOException e)
                {
                    e.getStackTrace();
                }

            httpURLConnection.disconnect();
        }
        return respString;
    }

    //Ativa a Localização Atual(GPS)
    private void enableMyLocation()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        } else
        {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }


    //CLASSES PARA TRAÇAR AS ROTAS(Utiliza Internet, estilo GoogleMaps)
    public class TaskRequestDirections extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            String responseString = "";
            responseString = requestDirections(strings[0]);

            return responseString;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            //passa o json
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> >
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings)
        {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try
            {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            }catch(JSONException e)
            {
                e.getStackTrace();
            }

            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists)
        {
            //mostra as rotas no mapa
            ArrayList pontos = null;
            PolylineOptions polylineOptions = null;

            for(List<HashMap<String, String>> caminho : lists)
            {
                pontos = new ArrayList();
                polylineOptions = new PolylineOptions();

                for(HashMap<String, String> ponto : caminho)
                {
                    double lat = Double.parseDouble(ponto.get("lat"));
                    double lon = Double.parseDouble(ponto.get("lon"));

                    pontos.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(pontos);
                polylineOptions.width(15);
                polylineOptions.color(Color.GREEN);
                polylineOptions.geodesic(true);
            }

            if(polylineOptions != null)
            {
                mMap.addPolyline(polylineOptions);
            } else
            {
                Toast.makeText(getApplicationContext(), "Rota não encontrada!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}