package com.ufrj.nce.psa.Application;

import android.content.Context;

import com.ufrj.nce.psa.Objects.Serialization;

/**
 * Created by filhofilha on 11/8/15.
 */
public class MySettings extends Serialization{

    private transient Context mContext;
    private int mMinutesIntervalEmergencyReceivedAlarm = 30 * 1000;


    protected MySettings(Context mContext) {

        super(MySettings.class.getName());

        this.mContext = mContext;
    }

    @Override
    public void saveData() {
        saveInstance(mContext, this);
    }

    @Override
    public MySettings loadData() {
        return (MySettings)loadInstance(mContext, MySettings.class.getName());
    }


    public int getMinutesIntervalEmergencyReceivedAlarm() {
        return mMinutesIntervalEmergencyReceivedAlarm;
    }

    public void setMinutesIntervalEmergencyReceivedAlarm(int mMinutesIntervalEmergencyReceivedAlarm) {
        this.mMinutesIntervalEmergencyReceivedAlarm = mMinutesIntervalEmergencyReceivedAlarm;
    }

}
