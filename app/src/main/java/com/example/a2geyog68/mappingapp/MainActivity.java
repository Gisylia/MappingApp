package com.example.a2geyog68.mappingapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.widget.Toast;



import java.nio.BufferUnderflowException;

public class MainActivity extends AppCompatActivity implements LocationListener {

    MapView mv;


    @Override


    /**this is called when the main activity is first created*/
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**this is for the location manager */
        LocationManager mgr= (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
// if you want to test indoors change gps_provider to network_provider (everything in caps)

        /**this is for the preference menu*/
        Configuration.getInstance().load
                (this,PreferenceManager.getDefaultSharedPreferences (this));

        /**this is for the map view*/
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


    /** choosing the different map views*/
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

/** when the location changes */
    public void onLocationChanged(Location newLoc) // new lock represents your current gps location
    {
        Toast.makeText
                (this, "Location= " +
                        newLoc.getLatitude()+ "" +
                        newLoc.getLongitude(), Toast.LENGTH_LONG).show();

        mv.getController().setCenter(new GeoPoint( newLoc.getLatitude() ,newLoc.getLongitude() ));

    }

    /** when the location is disabled*/
    public void onProviderDisabled(String provider)
   {
        Toast.makeText(this, "Provider" +
                        provider + "disabled",
                        Toast.LENGTH_LONG).show();
   }


    /**when the location is enabled */
    public void onProviderEnabled (String provider)
    {
        Toast.makeText(this, "Provider" +
                        provider + "enabled",
                Toast.LENGTH_LONG).show();
    }

    /**when the location changes a status update*/
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        Toast.makeText(this, "Status changed:" +
                        status,
                Toast.LENGTH_LONG).show();




    }

}
