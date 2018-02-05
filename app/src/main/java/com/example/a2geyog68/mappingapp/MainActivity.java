package com.example.a2geyog68.mappingapp;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.nio.BufferUnderflowException;

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




    // adding a new method to inflate the string XML


    public boolean onCreateOptionsMenu (Menu menu)

    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }


    public boolean onOptionsItemSelected (MenuItem item)


    {

        if (item.getItemId() == R.id.choosemap)

        {
            Intent intent = new Intent(this, MapChooseActivity.class);
                    startActivityForResult(intent, 0);
            return true;
        }

        return false;
    }

    public void onActivityResult (int requestCode, int resultCode, Intent returnIntent)

    {
        if (resultCode == RESULT_OK)

        {

            if (requestCode == 0) //mapChooseActivity returning it's intent

            {

                Bundle bundle = returnIntent.getExtras();
                boolean hikebike = bundle.getBoolean("com.example.gisylia_g.mappingapp.hikebike");

                if (hikebike == true)

                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }

                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);

                }

            }

        }

    }

}
