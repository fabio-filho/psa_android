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

/**
 * Created by fabiofilho on 3/21/15.
 */
public class Contact {

    private String name="", number="", code="";
    private String id;
    private Uri uriData;
    private Bitmap image=null;
    private Context context;
    private MyLocation location;


    public Contact(String code, String name, String number) {

        this.code = code;
        this.name = name;
        this.number = number;
    }


    public Contact(Context context, Uri uriData){

        this.uriData = uriData;
        this.context = context;
        setID();
        setName();
        setNumber();
    }

    public Contact(Context context, String number){

        this.context = context;
        this.number = number;

        location = new MyLocation();
        location.loadLocation(context);
    }

    private String getNameFromNumber(){

        return "";
    }

    public String getLatitude() {
        return location.getLatitude();
    }

    public String getLongitude() {
        return location.getLongitude();
    }

    public void setLocation(MyLocation location) {
        this.location = location;
    }

    public String getCode(){
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean isNameEmpty(){
        return getName().equals("");
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = context.getContentResolver().query(uriData, null, null, null, null);

        if (cursor.moveToFirst())
            setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

        cursor.close();
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    private void setNumber() {

        String contactNumber = null;

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{getId()},
                null);

        if (cursorPhone.moveToFirst())
            setNumber(cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

        cursorPhone.close();

    }


    public String getId(){
        return id;
    }


    private void setId(String id){
        this.id = id;
    }


    private void setID(){

        Cursor cursorID = context.getContentResolver().query(uriData,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst())
            setId(cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID)));

        cursorID.close();
    }


    private Bitmap getImage() {

        Bitmap photo = null;

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
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





}
