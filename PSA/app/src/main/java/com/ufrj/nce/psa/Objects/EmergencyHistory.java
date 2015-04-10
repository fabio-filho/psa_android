package com.ufrj.nce.psa.Objects;

import android.content.Context;

/**
 * Created by filhoefilha on 10/04/15.
 */
public class EmergencyHistory {


    private String message, code, latitude, longitude;
    private DateTime datetime;
    private Contact contact;


    public EmergencyHistory(Context mContext, String code, String message, String number,
                            DateTime datetime, String latitude, String longitude) {

        this.message = message;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.datetime = datetime;
        this.contact = new Contact(mContext, number);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


}
