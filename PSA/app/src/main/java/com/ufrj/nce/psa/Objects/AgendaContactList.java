package com.ufrj.nce.psa.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filhofilha on 11/11/15.
 */

public class AgendaContactList implements Serializable {


    private List<AgendaContact> mList;


    public AgendaContactList(){
        mList = new ArrayList<>();
    }


    public AgendaContact get(int index){

        try{
            return mList.get(index);

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return null;
    }

    public Boolean add(AgendaContact contact){

        if(!checkContactExist(contact)) {
            mList.add(contact);
            return true;
        }
        return false;

    }

    public Boolean checkContactExist(AgendaContact contact){

        for(AgendaContact mContact: mList)
            if(mContact.getId().equals(contact.getId()))
                return true;

        return false;
    }

    public List<AgendaContact> getList(){
        return mList;
    }


    public String getListInStringFormat(){

        String result = "";

        for (AgendaContact contact : mList)
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