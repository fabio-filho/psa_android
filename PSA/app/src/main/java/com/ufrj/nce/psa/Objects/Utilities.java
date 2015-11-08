package com.ufrj.nce.psa.Objects;


import android.util.Log;

import com.ufrj.nce.psa.Application.MyApplication;

/**
 * Created by fabiofilho on 10/21/15.
 */
public class Utilities {

    public static void sleep(int mTimer){

        try {
            Thread.sleep(mTimer);

        }catch (Exception o){
            Utilities.log(o.toString());
        }
    }


    public static void log(String mTitle, String mMessage){

        try {
            Log.i(mTitle, mMessage);

        }catch (Exception o){
            Log.i(MyApplication.APP_NAME, o.toString());
        }
    }

    public static void log(String mMessage){

        StackTraceElement mStackTraceElement = new Throwable().getStackTrace()[1];

        log(MyApplication.APP_NAME, "Class -> "+mStackTraceElement.getClassName()+" | Method -> "+ mStackTraceElement.getMethodName()+" | Message -> " + mMessage);
    }
}