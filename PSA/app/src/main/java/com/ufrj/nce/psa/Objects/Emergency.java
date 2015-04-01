package com.ufrj.nce.psa.Objects;

import android.view.View;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class Emergency {

    public static final String CODE_EMPTY = "-1";


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




}
