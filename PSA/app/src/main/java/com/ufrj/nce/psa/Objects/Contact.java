package com.ufrj.nce.psa.Objects;

/**
 * Created by filhofilha on 11/11/15.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class Contact implements Serializable{

    public static final String NO_NAME = "Nenhum nome foi encontrado para este número.";

    private String mName, mNumber;
    private String mId;

    private Bitmap mImage;
    private MyLocation mMyLocation;


    public static String getNameByNumber(Context context, String number) {

        String name = NO_NAME;
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));


            ContentResolver contentResolver = context.getContentResolver();
            Cursor contactLookup = contentResolver.query(uri, new String[]{BaseColumns._ID,
                    ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);

            try {
                if (contactLookup != null && contactLookup.getCount() > 0) {
                    contactLookup.moveToFirst();
                    name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                }
            } catch (Exception o) {
                Utilities.log( o.toString());
            } finally {
                if (contactLookup != null) {
                    contactLookup.close();
                }
            }
        }catch(Exception o){
            Utilities.log( o.toString());
        }

        return name;
    }


    public Contact(String mId, String mName, String mNumber, MyLocation mMyLocation) {

        this.mId = mId;
        this.mName = mName;
        this.mNumber = mNumber;
        this.mMyLocation = mMyLocation;
    }


    public Bitmap getImage(){
        return mImage;
    }


    public MyLocation getLocation() {
        return mMyLocation;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getId() {
        return mId;
    }

    public boolean isNameEmpty(){

        if(mName.equals(""))
            return true;
        else
            return false;
    }
}

