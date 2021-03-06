package com.ufrj.nce.psa.Application.Data;

import android.content.Context;

import com.ufrj.nce.psa.Objects.GPS;
import com.ufrj.nce.psa.Objects.Serialization;
import com.ufrj.nce.psa.Objects.Utilities;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class Settings extends Serialization{


    public final static int INTERVAL_ALARM_SHORT = 10 * 1000;
    public final static int INTERVAL_ALARM_MEDIUM = 20 * 1000;
    public final static int INTERVAL_ALARM_LONG = 30 * 1000;


    private int mIndexAlarmIntervalMinutesArray = 1;
    private boolean mUseGPS = true;
    private String mName = "";



    public Settings() {
        super(Settings.class.getName());
    }


    /* =================================================================  Serialization settings. */

    @Override
    public void saveData(Context mContext) {
        saveInstance(mContext, this);
    }

    @Override
    public Settings loadData(Context mContext) {

        Object mObject = loadInstance(mContext, Settings.class.getName());

        try {
            if(mObject !=null) {
                if(((Settings) mObject).getName().equals(""))
                    ((Settings) mObject).setName(mContext.getResources().getString(R.string.fragment_settings_label_default_name));

                return (Settings) mObject;
            }

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        Settings mSettings = new Settings();
        mSettings.setName(mContext.getResources().getString(R.string.fragment_settings_label_default_name));
        mSettings.setUseGPS(GPS.isEnabled(mContext));

        return mSettings;
    }




    /* ===================================================================   Settings proprieties */


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

    public String getName() {
        Utilities.log(mName);
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
