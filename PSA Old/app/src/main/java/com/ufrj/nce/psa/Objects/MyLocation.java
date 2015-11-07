package com.ufrj.nce.psa.Objects;

/**
 * Created by fabiofilho on 2/25/15.
 */

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import com.ufrj.nce.psa.Utilities.Functions;


public class MyLocation {


    Location currentLocation;

    private String longitude, latitude;

    public MyLocation(){
    }

    public MyLocation(String latitude, String longitude){

        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getLatitude(){ return latitude;}

    public String getLongitude(){ return longitude;}

    public void turnGPSOn(Context context) {

        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public Location getLocation(){
        return currentLocation;
    }

    public void loadLocation(Context context){

        try {
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            currentLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }catch(Exception o){
            Functions.Log("refreshDeviceLocation", o.toString());
        }

    }
}