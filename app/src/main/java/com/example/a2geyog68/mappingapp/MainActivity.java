package com.example.a2geyog68.mappingapp;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MapView mv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load
                (this,PreferenceManager.getDefaultSharedPreferences (this));


        setContentView(R.layout.activity_main);

        mv = (MapView) findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);

        mv.getController().setCenter(new GeoPoint(51.05, -0.72));


    }
}
