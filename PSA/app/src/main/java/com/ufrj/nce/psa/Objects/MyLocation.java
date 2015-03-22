package com.ufrj.nce.psa.Objects;

/**
 * Created by fabiofilho on 2/25/15.
 */
import android.location.Location;
import android.os.Bundle;

import com.ufrj.nce.psa.Utilities.Functions;


public class MyLocation implements android.location.LocationListener {

    Location currentLocation;
    public void onLocationChanged(Location location) {
        // This is where I finally get the latest location information

        currentLocation= location;

        int Bearing = (int)currentLocation.getBearing();

        Functions.Log("onLocationChanged", "Result: "+ currentLocation.getLongitude() + " - " + currentLocation.getLatitude());


    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}