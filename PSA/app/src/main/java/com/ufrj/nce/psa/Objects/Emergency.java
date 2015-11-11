package com.ufrj.nce.psa.Objects;

import android.view.View;

import java.io.Serializable;

/**
 * Created by filhofilha on 11/11/15.
 */
public class Emergency implements Serializable{

    public static final String CODE_EMPTY = "-1";
    public final static String TAG_EMERGENCY_MESSAGE = "@%$#";

    private String mName;
    private String mId;
    private View.OnClickListener mOnClickListener;

    private AgendaContactList mListContact;


    public Emergency(String mId, String mName, View.OnClickListener onClickListener){

        this.mName = mName;
        this.mId = mId;
        this.mOnClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }



    public Emergency(){
        mListContact = new AgendaContactList();
    }


    public AgendaContactList getListContact() {
        return mListContact;
    }



    public Boolean sendAlertToContact(String message, AgendaContact contact){

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

            for (AgendaContact contact : mListContact.getList())
                sendAlertToContact(message, contact);

        }catch (Exception o){
            Utilities.log("sendAlertToAllContacts", o.toString());
        }

        return false;
    }


}