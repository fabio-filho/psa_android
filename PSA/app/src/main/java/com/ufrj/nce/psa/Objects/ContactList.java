package com.ufrj.nce.psa.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filhofilha on 11/11/15.
 */

public class ContactList implements Serializable {


    private List<Contact> mList;


    public ContactList(){
        mList = new ArrayList<>();
    }


    public Contact get(int index){

        try{
            return mList.get(index);

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return null;
    }

    public Boolean add(Contact contact){

        if(!checkContactExist(contact)) {
            mList.add(contact);
            return true;
        }
        return false;

    }

    public Boolean checkContactExist(Contact contact){

        for(Contact mContact: mList)
            if(mContact.getId().equals(contact.getId()))
                return true;

        return false;
    }

    public List<Contact> getList(){
        return mList;
    }


    public String getListInStringFormat(){

        String result = "";

        for (Contact contact : mList)
            if(contact.isNameEmpty())
                result += contact.getNumber()+"; ";
            else
                result += contact.getName()+"; ";

        return result;
    }


    public void remove(int index){
        try {
            mList.remove(index);
        }catch (Exception o){
            Utilities.log(o.toString());
        }
    }


    public void removeLastItem(){

        try {
            if(mList.size() > 0)
                mList.remove(mList.size() - 1);
        }catch (Exception o){
            Utilities.log(o.toString());
        }
    }


}