package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencyReceiverView extends Activity {

    private EmergencySMS emergencySMS;
    private TextView textViewLabelContact,textViewLabelMessage, textViewLabelDatetime,
            textViewContact,textViewMessage, textViewDatetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_receiver_view);

        emergencySMS = new EmergencySMS(getApplicationContext(), EmergencySMS.MESSAGE);

        textViewLabelContact = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverLabelContact);
        textViewLabelMessage = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverLabelMessageReceived);
        textViewLabelDatetime = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverLabelDatetime);

        textViewContact = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverLabelContact);
        textViewMessage = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverMessageReceived);
        textViewDatetime = (TextView) findViewById(R.id.txtViewMessageEmergencyReceiverDatetime);

        textViewContact.setText(emergencySMS.getContact().getName());
        textViewMessage.setText(emergencySMS.getMessage());
        textViewDatetime.setText(emergencySMS.getDateTime().toString());

    }



    public void onClickButtonCall(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + emergencySMS.getContact().getNumber()));
        startActivity(callIntent);
    }





}
