package com.ufrj.nce.psa.Objects;

import android.telephony.gsm.SmsManager;

import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class SmsSender {

    private String number, message;

    public SmsSender(String number, String message) {
        this.number = number;
        this.message = message;
    }

    public String getNumber() {
        return number;
    }


    public String getMessage() {
        return message;
    }


    public Boolean sendMessage(){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(getNumber(), null, getMessage(), null, null);

        }catch (Exception o){

            Functions.Log("sendMessage", o.toString());
            return false;
        }
        return true;
    }


}
