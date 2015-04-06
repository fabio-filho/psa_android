package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyHistoryTable;
import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencySMS {

    public static String NUMBER = "", MESSAGE = "";

    private String message = "", messageReceived = "", datetime = "";
    private final String TAG_SEPARATOR = "$.$";
    public static final String TAG_SMS_IDENTIFICATION = "#@#";
    private Contact contact;


    public EmergencySMS(Context context, String message) {

        this.message = message;

        contact = new Contact(context, NUMBER);
    }

    public EmergencySMS(String messageReceived) {

        this.messageReceived = messageReceived;

        loadValuesFromMessageReceived();
    }


    public Contact getContact(){

        return contact;
    }

    public String getMessageToSend(){

        return TAG_SMS_IDENTIFICATION+
                    TAG_SEPARATOR+
              new DateTime().toString()+
                    TAG_SEPARATOR+
              contact.getLatitude()+
                    TAG_SEPARATOR+
              contact.getLongitude()+
                    TAG_SEPARATOR+
              message;
    }


    private void loadValuesFromMessageReceived(){

        try {
            int countTagsSeparator = 0;
            String longitude = "", latitude = "";

            for (int index = 0; index < messageReceived.length(); index++) {

                if (countTagsSeparator < 4) {

                    if (String.copyValueOf(messageReceived.toCharArray(), index, TAG_SEPARATOR.length()).equals(TAG_SEPARATOR)) {
                        countTagsSeparator++;
                        index += 3;
                    }
                }

                switch (countTagsSeparator) {

                    case 1: {
                        datetime += String.copyValueOf(messageReceived.toCharArray(), index, 1);
                        break;
                    }
                    case 2: {
                        latitude += String.copyValueOf(messageReceived.toCharArray(), index, 1);
                        break;
                    }
                    case 3: {
                        longitude += String.copyValueOf(messageReceived.toCharArray(), index, 1);
                        break;
                    }
                    case 4: {
                        message += String.copyValueOf(messageReceived.toCharArray(), index, 1);
                        break;
                    }

                }

            }

        }catch(Exception o){
            Functions.Log("loadValuesFromMessageReceived", o.toString());
        }

    }

    public String getMessage(){
        return message;
    }

    public DateTime getDateTime(){

        return new DateTime(datetime);
    }


    public static Boolean saveEmergencyOnDB(Context context, EmergencySMS emergencySMS){

        try{
            SQLiteDatabase db = new EmergencyHistoryTable(context).getWritableDatabase();
            SQLite.insertEmergencyHistory(db, emergencySMS);
            db.close();

        }catch(Exception o){
            Functions.Log("saveEmergencyOnDB", o.toString());
            return false;
        }
        return true;
    }


}
