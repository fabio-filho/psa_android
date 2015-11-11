package com.ufrj.nce.psa.Objects;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by filhofilha on 11/11/15.
 */
public class AgendaContact implements Serializable {


    private Uri mUriData = null;
    private transient Context mContext;
    private String mId;
    private String mName, mNumber;

    public AgendaContact(Context mContext, Uri mUriData){

        this.mUriData = mUriData;
        this.mContext = mContext;
        setId();
        setName();
        setNumber();
    }

    private void setId(){

        Cursor mCursorID = mContext.getContentResolver().query(mUriData,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (mCursorID.moveToFirst())
            mId = mCursorID.getString(mCursorID.getColumnIndex(ContactsContract.Contacts._ID));

        mCursorID.close();
    }




    private void setNumber() {

        // Using the contact ID now we will get contact phone mNumber
        Cursor mCursorPhone = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{getId()},
                null);

        if (mCursorPhone.moveToFirst())
            mNumber = mCursorPhone.getString(mCursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        mCursorPhone.close();

    }


    private void setName() {

        // querying contact data store
        Cursor mCursor = mContext.getContentResolver().query(mUriData, null, null, null, null);

        if (mCursor.moveToFirst())
            mName = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

        mCursor.close();
    }


    public String getId(){
        return mId;
    }


    public String getName() {

        return mName;
    }


    public String getNumber() {
        return mNumber;
    }


    private Bitmap getImage() {

        Bitmap photo = null;

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(mContext.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(getId())));

            if (inputStream != null)
                photo = BitmapFactory.decodeStream(inputStream);

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return photo;
    }

    public boolean isNameEmpty(){

        if(mName.equals(""))
            return true;
        else
            return false;
    }


    public Contact getContact(){

        return new Contact(mId, mName, mNumber, null);
    }


}
