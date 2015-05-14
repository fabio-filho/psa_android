package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.ufrj.nce.psa.Utilities.Functions;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * Created by fabiofilho on 5/13/15.
 */
public class GoogleMaps {

    public static Bitmap getStaticMap(double latitude, double longitude, int zoom, int width, int height){


        String URL = "http://maps.google.com/maps/api/staticmap?center=" +latitude +
                "," + longitude + "&zoom="+zoom+"&size="+width+"x"+height
                +"&sensor=false&markers=color:red%7Clabel:R%7C"+latitude+","+longitude;


        Bitmap bmp = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);

        InputStream in = null;
        try {
            in = httpclient.execute(request).getEntity().getContent();
            bmp = BitmapFactory.decodeStream(in);
            in.close();

        } catch (Exception o) {
            Functions.Log("getStaticMap", o.toString());
        }

        return bmp;
    }

    public static Bitmap getStaticMap(double latitute, double longitude){

        int zoom = 17;
        int width = 600;
        int height = 600;

        return getStaticMap(latitute, longitude, zoom, width, height);
    }


    public static void openApp(Context context, String latitude, String longitude){

        String url = "https://www.google.com.br/maps/place//@"+latitude+","+longitude;

        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
