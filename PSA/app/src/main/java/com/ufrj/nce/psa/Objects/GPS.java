package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by filhofilha on 11/11/15.
 */
public class GPS {


    public static boolean isEnabled(Context mContext){

        try{

            LocationManager mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return false;
    }


    public static void openGPSSettings(Context mContext) {

        mContext.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }



}