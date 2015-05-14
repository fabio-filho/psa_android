package com.ufrj.nce.psa.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 5/13/15.
 */
public class NotificationDismissedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //int notificationId = intent.getExtras().getInt(".Broadcasts.NotificationDismissedReceiver");

        if (SMSReceiver.threadAlertEmergency != null)
            SMSReceiver.threadAlertEmergency.stopProcess();

        Functions.Log("onReceive", "Stopped Alert.");

    }
}
