package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencyReceiverView extends Activity {

    public static String MESSAGE="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_receiver_view);


        TextView textView = (TextView) findViewById(R.id.textViewEmergencyManagerViewContacts);

        textView.setText(MESSAGE);
    }
}
