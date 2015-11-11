package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import java.io.Serializable;

/**
 * Created by filhofilha on 11/11/15.
 */


public class MyLocation implements Serializable {

    Location mCurrentLocation = null;

    private final String NULL_VALUE = "NULL";
    private String mLongitude = NULL_VALUE, mLatitude = NULL_VALUE;

    public MyLocation(){
    }

    public MyLocation(String latitude, String longitude){

        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public boolean isNullLocation(){

        if(mLatitude.equals(NULL_VALUE) || mLongitude.equals(NULL_VALUE))
            return true;

        return false;
    }

    public String getLatitude(){

        if(mCurrentLocation == null)
            return mLatitude;
        else
            return String.valueOf(mCurrentLocation.getLatitude());
    }

    public String getLongitude(){

        if(mCurrentLocation == null)
            return mLongitude;
        else
            return String.valueOf(mCurrentLocation.getLongitude());
    }



    public void loadLocation(Context context){

        try {
            LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            mCurrentLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }catch(Exception o){
            Utilities.log("refreshDeviceLocation", o.toString());
        }

    }
}