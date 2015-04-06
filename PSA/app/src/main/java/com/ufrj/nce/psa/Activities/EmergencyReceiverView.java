package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencyReceiverView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_receiver_view);

        TextView textView = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiver);

        EmergencySMS emergencySMS = new EmergencySMS(EmergencySMS.MESSAGE);

        textView.setText(EmergencySMS.NUMBER+"\n"+emergencySMS.getMessage()+"\n"
                +emergencySMS.getContact().getLatitude()+"\n"
                +emergencySMS.getContact().getLongitude()+"\n"
                +emergencySMS.getDateTime() );
    }






}
