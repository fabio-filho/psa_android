package com.ufrj.nce.psa.Utilities;

import android.content.Context;

/**
 * Created by fabiofilho on 3/3/15.
 */
public class WifiManager {

    public static void setState(Context context, Boolean state) {

        try {
            android.net.wifi.WifiManager wifiManager = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            if (state)
                if (wifiManager.getWifiState() != android.net.wifi.WifiManager.WIFI_STATE_ENABLING)
                    wifiManager.setWifiEnabled(state);
                else if (wifiManager.getWifiState() != android.net.wifi.WifiManager.WIFI_STATE_DISABLING)
                    wifiManager.setWifiEnabled(state);

            Functions.Log("turnWifi", "Wifi Inputted: " + state);

        } catch (Exception o) {
            Functions.Log("turnWifi", o.toString());
        }
    }


    public static void reconnect(Context context){

        try {
            android.net.wifi.WifiManager wifiManager = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            wifiManager.reconnect();
            Functions.Log("turnWifi", "Wifi Reconnect.");

        }catch(Exception o){
            Functions.Log("turnWifi", o.toString());
        }

    }



    public static int getState(Context context){

        try {
            android.net.wifi.WifiManager wifiManager = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            Functions.Log("getState", "Wifi State: "+ wifiManager.getWifiState());
            return  wifiManager.getWifiState();

        } catch (Exception o) {
            Functions.Log("turnWifi", o.toString());
            return  android.net.wifi.WifiManager.WIFI_STATE_UNKNOWN;
        }


    }


    public static Boolean isDisabled (Context context) {

        try {
            if(getState(context) == android.net.wifi.WifiManager.WIFI_STATE_DISABLING ||
                    getState(context) == android.net.wifi.WifiManager.WIFI_STATE_DISABLED  )
                return true;

        } catch (Exception o) {
            Functions.Log("turnWifi", o.toString());

        }

        return  false;

    }


}
