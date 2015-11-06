package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufrj.nce.psa.Broadcasts.SMSReceiver;
import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.Objects.GoogleMaps;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencyReceivedView extends Activity {


    public static Boolean EMERGENCY_RECEIVED = false;
    public static EmergencySMS temp_emergencySMS = null;

    private EmergencySMS emergencySMS;
    private TextView textViewLabelContact,textViewLabelMessage, textViewLabelDatetime,
            textViewContact,textViewMessage, textViewDatetime, textStaticMap;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_received_view);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (EMERGENCY_RECEIVED)
            emergencySMS = new EmergencySMS(getApplicationContext(), EmergencySMS.MESSAGE);
        else
            emergencySMS = temp_emergencySMS;


        loadEmergencyView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {
            if(EMERGENCY_RECEIVED)
                startActivity(new Intent("android.intent.action.PSA_MENU"));
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    public void loadEmergencyView(){


        textStaticMap = (TextView) findViewById(R.id.txtEmergencyReceiverViewLabelStaticMap);
        textViewLabelContact = (TextView) findViewById(R.id.txtEmergencyReceiverViewLabelContact);
        textViewLabelMessage = (TextView) findViewById(R.id.txtEmergencyReceiverViewLabelMessageReceived);
        textViewLabelDatetime = (TextView) findViewById(R.id.txtEmergencyReceiverViewLabelDatetime);

        textViewContact = (TextView) findViewById(R.id.txtEmergencyReceiverViewContact);
        textViewMessage = (TextView) findViewById(R.id.txtEmergencyReceiverViewMessageReceived);
        textViewDatetime = (TextView) findViewById(R.id.txtEmergencyReceiverViewDatetime);

        textViewContact.setText(emergencySMS.getContact().getName());
        textViewMessage.setText(emergencySMS.getMessage());
        textViewDatetime.setText(emergencySMS.getDateTime().toString());

        imageView = (ImageView) findViewById(R.id.imgEmergencyReceiverViewStaticMap);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleMaps.openApp(getApplicationContext(),
                        emergencySMS.getContact().getLatitude(), emergencySMS.getContact().getLongitude());
            }
        });

        loadStaticMap(emergencySMS.getContact().getLatitude_double(), emergencySMS.getContact().getLongitude_double());
    }

    public void loadStaticMap(final double latitude, final double longitude){

        Functions.Log("loadStaticMap", latitude+" --- "+longitude);

        if ((latitude == 0.0) || (longitude == 0.0)) return;

        new Thread(){

            @Override
            public void run() {

               try {

                   final Bitmap bmp = GoogleMaps.getStaticMap(latitude, longitude);

                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {

                           imageView.setImageBitmap(bmp);
                           textStaticMap.setText(getResources().getString(R.string.activity_emergency_receiver_static_map_received));

                       }
                   });

               }catch (Exception o){
                   Functions.Log("loadStaticMap", o.toString());
               }

            }
        }.start();


    }


    public void onClickButtonCall(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + emergencySMS.getContact().getNumber()));
        startActivity(callIntent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (SMSReceiver.threadAlertEmergency != null)
            SMSReceiver.threadAlertEmergency.stopProcess();
    }
}
