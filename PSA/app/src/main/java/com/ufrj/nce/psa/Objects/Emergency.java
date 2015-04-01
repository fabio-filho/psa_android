package com.ufrj.nce.psa.Objects;

import android.view.View;

import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class Emergency {

    public static final String CODE_EMPTY = "-1";
    public final static String TAG_EMERGENCY_MESSAGE = "@%$#";

    private String name;
    private String code;
    private View.OnClickListener onClickListener;

    private ContactList mListContact;


    public Emergency(String code, String name){
        this.name = name;
        this.code = code;
    }


    public Emergency(String code, String name, View.OnClickListener onClickListener){

        this.name = name;
        this.code = code;
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public Emergency(){
        mListContact = new ContactList();
    }


    public ContactList getListContact() {
        return mListContact;
    }


    public void setListContact(ContactList mListContact) {
        this.mListContact = mListContact;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public Boolean sendAlertToContact(String message, Contact contact){

        try {

             SmsSender smsSender = new SmsSender(contact.getNumber(), message);
             smsSender.sendMessage();

        }catch (Exception o){
            Functions.Log("sendAlertToContact", o.toString());
            return false;
        }

        return true;
    }


    public Boolean sendAlertToAllContacts(String message){

        try {

            for (Contact contact : mListContact.getListContacts())
                sendAlertToContact(message, contact);

        }catch (Exception o){
            Functions.Log("sendAlertToAllContacts", o.toString());
        }

        return false;
    }


}
