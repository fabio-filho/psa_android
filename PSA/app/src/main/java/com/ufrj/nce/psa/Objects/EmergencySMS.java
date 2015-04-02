package com.ufrj.nce.psa.Objects;

import android.content.Context;

import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class EmergencySMS {


    private String message = "", messageReceived = "", datetime = "";
    private MyLocation location;
    private final String TAG_SEPARATOR = "$.$";
    public static final String TAG_SMS_IDENTIFICATION = "#@#";



    public EmergencySMS(Context context, String message) {

        this.message = message;

        location = new MyLocation();
        location.loadLocation(context);
    }

    public EmergencySMS(String messageReceived) {

        this.messageReceived = messageReceived;

        loadValuesFromMessageReceived();
    }


    public String getMessageToSend(){

        return TAG_SMS_IDENTIFICATION+
                    TAG_SEPARATOR+
              new DateTime().toString()+
                    TAG_SEPARATOR+
              location.getLocation().getLatitude()+
                    TAG_SEPARATOR+
              location.getLocation().getLongitude()+
                    TAG_SEPARATOR+
              message;
    }


    private void loadValuesFromMessageReceived(){

        int countTagsSeparator = 0;
        String longitude ="", latitude="";

        for (int index = 0; index < messageReceived.length(); index++){

            Functions.Log("loadValuesFromMessageReceived", "Index: "+ index+" - CountSeparator: "+countTagsSeparator);
            if (countTagsSeparator < 4) {
                Functions.Log("loadValuesFromMessageReceived", "Entrou");
                if (String.copyValueOf(messageReceived.toCharArray(), index, TAG_SEPARATOR.length()).equals(TAG_SEPARATOR)) {
                    countTagsSeparator++;
                    index += 3;
                }
            }

            switch (countTagsSeparator){

                case 1:{
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

        location = new MyLocation(latitude, longitude);

    }

    public MyLocation getLocation(){
        return location;
    }

    public String getMessage(){
        return message;
    }

    public DateTime getDateTime(){

        return new DateTime(datetime);
    }




}
