package com.ufrj.nce.psa.Objects;

import android.telephony.SmsManager;

/**
 * Created by filhofilha on 11/11/15.
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

            Utilities.log(o.toString());
            return false;
        }
        return true;
    }


}