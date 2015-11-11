package com.ufrj.nce.psa.Objects;

import java.io.Serializable;

/**
 * Created by filhofilha on 11/11/15.
 */
public class Emergency implements Serializable{

    public static final String CODE_EMPTY = "-1";
    public final static String TAG_EMERGENCY_MESSAGE = "@%$#";

    private String mName, mMessage, mDoctorMessage;

    private Contact mDoctorContact;
    private boolean mIsDoctorPartActioned = false;

    private ContactList mListContact;


    public Emergency(){
        mListContact = new ContactList();
    }


    public ContactList getListContact() {
        return mListContact;
    }



    public Boolean sendAlertToContact(String message, Contact contact){

        try {
            SmsSender smsSender = new SmsSender(contact.getNumber(), message);
            smsSender.sendMessage();

        }catch (Exception o){
            Utilities.log(o.toString());
            return false;
        }

        return true;
    }


    public Boolean sendAlertToAllContacts(String message){

        try {

            for (Contact contact : mListContact.getList())
                sendAlertToContact(message, contact);

        }catch (Exception o){
            Utilities.log("sendAlertToAllContacts", o.toString());
        }

        return false;
    }

    public boolean isDoctorPartActioned() {
        return mIsDoctorPartActioned;
    }

    public void setDoctorPartActioned(boolean mIsDoctorPartActived) {
        this.mIsDoctorPartActioned = mIsDoctorPartActived;
    }

    public Contact getDoctorContact() {
        return mDoctorContact;
    }

    public void setDoctorContact(Contact mDoctorContact) {
        this.mDoctorContact = mDoctorContact;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getDoctorMessage() {
        return mDoctorMessage;
    }

    public void setDoctorMessage(String mDoctorMessage) {
        this.mDoctorMessage = mDoctorMessage;
    }

    /* Statics methods */
    public static boolean isValidMessage(String mMessage){

        if(mMessage.length() <= 3 ||
                mMessage.contains(TAG_EMERGENCY_MESSAGE) ||
                mMessage.contains(EmergencyReceived.TAG_SMS_IDENTIFICATION)||
                mMessage.contains(EmergencyReceived.TAG_SEPARATOR))

            return false;

        return true;
    }
}