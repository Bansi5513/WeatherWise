package com.example.weatherwise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout Home;
    private ProgressBar PBLoading;
    private TextView CityName, Temperature, Condition;
    private TextInputEditText EditCity;
    private ImageView ImageViewBack, Icon, Search;
    private RecyclerView  RecyclerView;
    private ArrayList<RecyclerViewModal> RVModalArrayList;
    private RecyclerViewAdapter RVAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        Home = findViewById(R.id.Home);
        PBLoading = findViewById(R.id.ProgressBarLoading);
        CityName = findViewById(R.id.CityName);
        Temperature = findViewById(R.id.Temperature);
        Condition = findViewById(R.id.Condition);
        EditCity = findViewById(R.id.EditCity);
        ImageViewBack = findViewById(R.id.ImageViewBack);
        Icon = findViewById(R.id.Icon);
        Search = findViewById(R.id.Search);
        RecyclerView = findViewById(R.id.RecyclerView);
        RVModalArrayList = new ArrayList<>();
        RVAdapter = new RecyclerViewAdapter(this, RVModalArrayList);
        RecyclerView.setAdapter(RVAdapter);

        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

    }

    private void getInformation(String CityName){
        String url = "http://api.weatherapi.com/v1/forecast.json?key=ab78abe527e9472083291014230110&q=" + CityName + "&days=1&aqi=no&alerts=no";
    }
}