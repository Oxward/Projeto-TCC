package com.example.aluno.googlemap.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.aluno.googlemap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final String[] textMarcadoresB1 =
            new String[]{"CTF", "Procuradoria Federal", "Agespisa", "Posto R Sá", "Posto Fiscal dos Pontões",
                    "Posto Fiscal do Barão", "Posto de Combustível", "Hospital do Barão", "Posto de Combustível",
                    "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa", "Procuradoria Federal", "CTF"};
    private final String[] textMarcadoresB2 =
            new String[]{"CTF", "Procuradoria Federal", "Fartote Freitas", "Educandário", "Paraíba", "Antiga Yamaha",
                    "Hotel Rio Parnaíba", "Rodoviária Nova", "Posto R Sá", "Rádio FM", "Agespisa",
                    "Procuradoria Federal", "CTF"};
    private final String[] textMarcadoresB3 =
            new String[]{"CTF", "Procuradoria Federal", "Fartote Freitas", "Agespisa", "Rádio FM", "Posto R de Sá", "Rodoviária Nova",
                    "Posto Fiscal dos Pontões", "Barão", "Posto Fiscal dos Pontões", "Posto R de Sá", "Rádio FM", "Agespisa",
                    "Procuradoria Federal", "CTF"};
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
    private ArrayList<LatLng> onibus3 = new ArrayList<>();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        enableMyLocation();
        // Adiciona um marcador no CTF e move a câmera
        LatLng ctf = new LatLng(-6.785664, -43.041863);
        mMap.addMarker(new MarkerOptions().position(ctf).title("Marcador no CTF"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, 15f));
        //GroundOverlayOptions homeOverlay = new GroundOverlayOptions()
        //                .image(BitmapDescriptorFactory.fromResource(R.drawable.android)).position(ctf, 100);
        //        //mMap.addGroundOverlay(homeOverlay);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_routes:
                try {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, "" + e.getMessage());
                }
                break;

            case R.id.nav_schedule:
                try {
                    Intent intent = new Intent(MainActivity.this, IntinerarioActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, "" + e.getMessage());
                }
                break;

            case R.id.nav_manage:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Ativa a Localização Atual(GPS)
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }
}
