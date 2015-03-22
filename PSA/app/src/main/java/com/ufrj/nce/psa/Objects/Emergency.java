package com.ufrj.nce.psa.Objects;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class Emergency {

    private String name;
    private String code;

    private ContactList mListContact;

    public Emergency(String code, String name){
        this.name = name;
        this.code = code;
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
