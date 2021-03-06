package com.ufrj.nce.psa.Objects;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

import com.ufrj.nce.psa.Utilities.Functions;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class Contact {

    public static final String NO_NAME = "Nenhum nome, da sua lista de contatos, foi encontrado para este número.";

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

        String resultName = getNameByNumber(context, number);

        if(resultName.equals(NO_NAME))
            name = number;
        else
            name = resultName;
    }

    private String getNameFromNumber(){

        return "";
    }

    public String getLatitude() {
        return location.getLatitude();
    }

    public Double getLatitude_double() {

        try{
            return Double.parseDouble(location.getLatitude());
        }catch (Exception o){
            Functions.Log("getLatitude_double", o.toString());
            return 0.0;
        }

    }

    public String getLongitude() {
        return location.getLongitude();
    }

    public Double getLongitude_double() {

        try{
            return Double.parseDouble(location.getLongitude());
        }catch (Exception o){
            Functions.Log("getLongitude_double", o.toString());
            return 0.0;
        }
    }


    public String getCode(){
        return code;
    }

    public String getName() {

        return name;
    }

    public void setLocation(String latitude, String longitude){

        location = new MyLocation(latitude, longitude);
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
                Functions.Log("getNameByNumber", o.toString());
            } finally {
                if (contactLookup != null) {
                    contactLookup.close();
                }
            }
        }catch(Exception o)
        {Functions.Log("getNameByNumber", o.toString());}

        return name;
    }


}
