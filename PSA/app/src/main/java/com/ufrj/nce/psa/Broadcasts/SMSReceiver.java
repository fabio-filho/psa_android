package com.ufrj.nce.psa.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.Objects.PushNotification;
import com.ufrj.nce.psa.Objects.SoundStream;
import com.ufrj.nce.psa.Utilities.Functions;
import com.ufrj.nce.psa.Utilities.Values;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class SMSReceiver extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String message = currentMessage.getDisplayMessageBody();

                    if(!message.contains(EmergencySMS.TAG_SMS_IDENTIFICATION)) return;

                    saveEmergencyOnDB(context, message, phoneNumber);
                    alertEmergency(context);
                    showEmergencyNotification(context, message);
                }
            }

        } catch (Exception e) {
            Functions.Log("onReceive", "Exception smsReceiver" +e);
        }

        this.clearAbortBroadcast();
    }

    private void saveEmergencyOnDB(Context context, String message, String phoneNumber){

        try{

            EmergencySMS.MESSAGE = message;
            EmergencySMS.NUMBER = phoneNumber;

            EmergencySMS.saveEmergencyOnDB(context, new EmergencySMS(context, message));

        }catch(Exception o){
            Functions.Log("saveEmergencyOnDB", o.toString());
        }
    }

    private void alertEmergency(Context context){

        try{

            new SoundStream(context, context.getAssets().openFd(Values.ASSETS_SOUND)).play(10);

        }catch (Exception o){
            Functions.Log("alertEmergency", o.toString());
        }
    }

    private void showEmergencyNotification(Context context, String message){

        try{
            PushNotification.createNotification(context, new EmergencySMS(context, message).getMessage());

        }catch (Exception o){
            Functions.Log("showEmergencyNotification", o.toString());
        }
    }

}