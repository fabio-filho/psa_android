package com.ufrj.nce.psa.Application.Data;

import android.content.Context;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.Objects.Serialization;
import com.ufrj.nce.psa.Objects.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filhofilha on 11/11/15.
 */
public class Emergencies extends Serialization {


    private List<Emergency> mList;


    public Emergencies() {

        super(Emergencies.class.getName());

        mList = new ArrayList<>();
    }

    @Override
    public void saveData(Context mContext) {
        saveInstance(mContext, this);
    }

    @Override
    public Emergencies loadData(Context mContext) {

        Object mObject = loadInstance(mContext, Emergencies.class.getName());

        try {
            if(mObject !=null)
                return (Emergencies) mObject;

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return new Emergencies();
    }



    public void add(Emergency mEmergency){

        mList.add(mEmergency);
    }

    public Emergency get(int mIndex){

        try{
            return mList.get(mIndex);

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return null;
    }


    public void remove(int mIndex){

        try{
            mList.remove(mIndex);

        }catch (Exception o){
            Utilities.log(o.toString());
        }

    }

    public List<Emergency> getList(){
        return mList;
    }

}
