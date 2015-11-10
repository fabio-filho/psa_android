package com.ufrj.nce.psa.Application;

import android.content.Context;

import com.ufrj.nce.psa.Objects.Serialization;
import com.ufrj.nce.psa.Objects.Utilities;

/**
 * Created by filhofilha on 11/8/15.
 */
public class MySettings extends Serialization{


    public final static int INTERVAL_ALARM_SHORT = 10 * 1000;
    public final static int INTERVAL_ALARM_MEDIUM = 20 * 1000;
    public final static int INTERVAL_ALARM_LONG = 30 * 1000;


    private int mIndexAlarmIntervalMinutesArray = 1;
    private boolean mUseGPS = true;


    public MySettings() {
        super(MySettings.class.getName());
    }

    @Override
    public void saveData(Context mContext) {
        saveInstance(mContext, this);
    }

    @Override
    public MySettings loadData(Context mContext) {

        Object mObject = loadInstance(mContext, MySettings.class.getName());

        try {
            if(mObject !=null)
                return (MySettings) mObject;

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return new MySettings();
    }


    public int getIndexAlarmIntervalMinutesArray() {
        return mIndexAlarmIntervalMinutesArray;
    }

    public void setIndexAlarmIntervalMinutesArray(int mIndexAlarmIntervalMinutesArray) {
        this.mIndexAlarmIntervalMinutesArray = mIndexAlarmIntervalMinutesArray;
    }

    public boolean getUseGPS() {
        return mUseGPS;
    }

    public void setUseGPS(boolean mUseGPS) {
        this.mUseGPS = mUseGPS;
    }
}
