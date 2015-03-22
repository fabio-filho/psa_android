package com.ufrj.nce.psa.Objects;

import com.ufrj.nce.psa.Utilities.Functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiofilho on 3/22/15.
 */
public class ContactList {


    private List<Contact> mListContacts;


    public ContactList(){
        mListContacts = new ArrayList<Contact>();
    }


    public Contact getContact(int index){

        try{
            return mListContacts.get(index);

        }catch (Exception o){
            Functions.Log("getContact", o.toString());
        }

        return null;
    }

    public Boolean addContact(Contact contact){

        if(!checkContactExist(contact)) {
            mListContacts.add(contact);
            return true;
        }
        return false;

    }

    public Boolean checkContactExist(Contact contact){

        for(Contact mContact: mListContacts)
            if(mContact.getId().equals(contact.getId()))
                return true;

        return false;
    }

    public List<Contact> getListContacts(){
        return mListContacts;
    }


    public String getListContactsShow(){

        String result = "";

        for (Contact contact : mListContacts)
            if(contact.isNameEmpty())
                result += contact.getNumber()+"; ";
            else
                result += contact.getName()+"; ";

        return result;
    }


    public void removeContact(int index){
        try {
            mListContacts.remove(index);
        }catch (Exception o){
            Functions.Log("removeContact", o.toString());
        }
    }


    public void removeLastContact(){

        try {
            mListContacts.remove(mListContacts.size() - 1);
        }catch (Exception o){
            Functions.Log("removeLastContact", o.toString());
        }
    }

    /*
    private void loadListNumbers(){

        mListContacts = new ArrayList<String>();

        String number="";

        for (int index=0; index < getNumber().length(); index++){

            if(String.copyValueOf(getNumber().toCharArray(), index, 1).equals(";"))
                mListContacts.add(number);
            else
                number += String.copyValueOf(getNumber().toCharArray(), index, 1);

        }

    }
    */

}
