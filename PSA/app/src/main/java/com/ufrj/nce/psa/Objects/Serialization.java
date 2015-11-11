package com.ufrj.nce.psa.Objects;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by filhofilha on 11/8/15.
 */
public abstract class Serialization implements Serializable {

    private String mFileName = "myData.ser";


    protected Serialization(String mFileName){
        this.mFileName = mFileName;
    }


    protected final void saveInstance(Context mContext, Object mObject){

        try {
            FileOutputStream mFileOutputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);

            ObjectOutputStream mObjectOutputStream = new ObjectOutputStream(mFileOutputStream);
            mObjectOutputStream.writeObject(mObject);

            mObjectOutputStream.close();
            mFileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected final static Object loadInstance(Context mContext, String mFileName){

        Object mObject = null;
        try {
            FileInputStream mFileInputStream = mContext.openFileInput(mFileName);

            ObjectInputStream mObjectInputStream = new ObjectInputStream(mFileInputStream);
            mObject = mObjectInputStream.readObject();

            mObjectInputStream .close();
            mFileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mObject;
    }


    public abstract void saveData(Context mContext);

    public abstract Object loadData(Context mContext);
}