package com.ufrj.nce.psa.Connections;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyHistoryTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Connections.Tables.SettingsTable;
import com.ufrj.nce.psa.Objects.Contact;
import com.ufrj.nce.psa.Objects.ContactList;
import com.ufrj.nce.psa.Objects.DateTime;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.Objects.EmergencyHistory;
import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.Utilities.Functions;
import com.ufrj.nce.psa.Utilities.Values;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiofilho on 11/11/14.
 */
public class SQLite {

    public final static String DATABASE_NAME = "psa-database";
    public final static Integer DATABASE_VERSION = 1;


    /**
     * Get a string result by query that will be execute in SQLite database.
     * @param sql_command
     * @return String from the query sent.
     */
    public static String getOneStringQuery(SQLiteDatabase db, String sql_command)
    {
        try{
            Cursor cursor = db.rawQuery(sql_command,null);
            if (cursor == null || cursor.getCount() <= 0)
                return Values.NULL_RESULT.toString();
            else{
                cursor.moveToFirst();
                return cursor.getString(0);
            }

        }
        catch(Exception o)
        {
            Functions.Log("getOneStringQuery",o.toString());
            return Values.ERROR_RESULT.toString();
        }
        finally {
            db.close();
        }
    }


    public static void insertEmergency(SQLiteDatabase db, Emergency emergency){

        try {
            Functions.Log("insertEmergency", emergency.getName());
            db.execSQL("insert into " + EmergencyTable.TABLE_NAME + " VALUES(null, '" + emergency.getName() + "') ");

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }
        finally {
            db.close();
        }

    }


    public static void deleteEmergencyWithContacts(SQLiteDatabase db, Emergency emergency){

        try{
            db.execSQL("delete from " + EmergencyTable.TABLE_NAME + " where " + EmergencyTable.FIELD_CODE + " = '" + emergency.getCode() + "'");

            db.execSQL("delete from " + ContactTable.TABLE_NAME + " where " + ContactTable.FIELD_EMERGENCY + " = '" + emergency.getCode() + "'");

        }catch(Exception o){
            Functions.Log("deleteEmergencyWithContacts", o.toString());
        }
        finally {
            db.close();
        }


    }


    public static void insertEmergencyHistory(SQLiteDatabase db, EmergencySMS emergencySMS){

        try {

            db.execSQL("insert into " + EmergencyHistoryTable.TABLE_NAME + " VALUES(null, '"
                    + emergencySMS.getDateTime().toString() + "', '"
                    + emergencySMS.getContact().getNumber() + "', '"
                    + emergencySMS.getMessage() + "', '"
                    + emergencySMS.getContact().getLatitude() + "', '"
                    + emergencySMS.getContact().getLongitude() + "') ");


        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }
        finally {
            db.close();
        }

    }


    public static void insertContact(SQLiteDatabase db, Emergency emergency){

        try {

            for(Contact contact: emergency.getListContact().getListContacts()) {
                db.execSQL("insert into " + ContactTable.TABLE_NAME + " VALUES(null, '"
                        + emergency.getCode() + "', '"
                        + contact.getName() + "', '"
                        + contact.getNumber() + "') ");

                Functions.Log("insertContact", " Code: "+emergency.getCode()+" - Name: "+ contact.getName());
            }

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }
        finally {
            db.close();
        }

    }



    public static List<Emergency> getEmergencies(SQLiteDatabase db){

        List<Emergency> mListEmergency = new ArrayList<Emergency>();

        try{
            Cursor cursor = db.rawQuery("select * from "+EmergencyTable.TABLE_NAME, null);

            if(cursor == null) return mListEmergency;

            Functions.Log("getEmergencies", "Count: "+cursor.getCount()+"");

            cursor.moveToFirst();

            for (int index=0; index < cursor.getCount(); index++){

                Emergency emergency = new Emergency();

                emergency.setCode(cursor.getString(0));
                emergency.setName(cursor.getString(1));

                Functions.Log("getEmergencies", cursor.getString(1));

                mListEmergency.add(emergency);
                cursor.moveToNext();
            }

        }catch (Exception o){
            Functions.Log("getEmergencies", o.toString());
        }
        finally {
            db.close();
        }

        return mListEmergency;
    }


    public static ContactList getEmergencyContactList (SQLiteDatabase db, String codeEmergency){

        ContactList mContactList = new ContactList();

        try{
            Cursor cursor = db.rawQuery("select * from "+ContactTable.TABLE_NAME+ " where "+ContactTable.FIELD_EMERGENCY+ " = '"+codeEmergency+"'" , null);

            if(cursor == null) return mContactList;

            Functions.Log("getEmergencyContactList", "Count: "+cursor.getCount()+" - Code Emergency: "+ codeEmergency+ " SQL: select * from "+ContactTable.TABLE_NAME+ " where "+ContactTable.FIELD_EMERGENCY+ " = '"+codeEmergency+"'");

            cursor.moveToFirst();

            for (int index=0; index < cursor.getCount(); index++){

                Contact contact = new Contact(cursor.getString(0), cursor.getString(2), cursor.getString(3));

                mContactList.addContactWithoutChecker(contact);

                cursor.moveToNext();
            }

        }catch (Exception o){
            Functions.Log("getEmergencyContactList", o.toString());
        }
        finally {
            db.close();
        }

        return mContactList;
    }


    public static List<EmergencyHistory> getEmergencyHistories(Context context, SQLiteDatabase db){

        List<EmergencyHistory> mListEmergencyHistory = new ArrayList<EmergencyHistory>();

        try{
            Cursor cursor = db.rawQuery("select * from "+EmergencyHistoryTable.TABLE_NAME
                    +" order by "+EmergencyHistoryTable.FIELD_DATETIME+" desc", null);

            if(cursor == null) return mListEmergencyHistory;

            Functions.Log("getEmergencyHistories", "Count: "+cursor.getCount()+"");

            cursor.moveToFirst();

            for (int index=0; index < cursor.getCount(); index++){

                EmergencyHistory emergency = new EmergencyHistory(context);

                emergency.setCode(cursor.getString(0));
                emergency.setDatetime(new DateTime(cursor.getString(1)));
                emergency.setContact(cursor.getString(2));
                emergency.setMessage(cursor.getString(3));
                emergency.setLatitude(cursor.getString(4));
                emergency.setLongitude(cursor.getString(5));

                Functions.Log("getEmergencyHistories", cursor.getString(1));

                mListEmergencyHistory.add(emergency);
                cursor.moveToNext();
            }

        }catch (Exception o){
            Functions.Log("getEmergencyHistories", o.toString());
        }
        finally {
            db.close();
        }

        return mListEmergencyHistory;
    }



    public static int getAlertTime(SQLiteDatabase db){

        try{

            Cursor cursor = db.rawQuery("select "+ SettingsTable.FIELD_ALARM_TIME+ "  from  "+SettingsTable.TABLE_NAME, null);

            if (cursor==null || cursor.getCount() == 0) return 0;

            cursor.moveToFirst();

            cursor.moveToFirst();

            return Integer.parseInt(cursor.getString(0));

        }catch (Exception o){
            Functions.Log("getAlertTime", o.toString());
        }
        finally {
            db.close();
        }


        return 0;
    }


    public static Boolean updateAlertTime(SQLiteDatabase db, int time){

        try{

            db.execSQL("update " + SettingsTable.TABLE_NAME + " set " + SettingsTable.FIELD_ALARM_TIME + " = " + time);

        }catch (Exception o){
            Functions.Log("updateAlertTime", o.toString());
            return false;
        }
        finally {
            db.close();
        }

        return true;

    }

    public static Boolean insertAlertTime(SQLiteDatabase db, int time){

        try{

            db.execSQL("insert into " + SettingsTable.TABLE_NAME + " values (null, "+ time+" )");

        }catch (Exception o){
            Functions.Log("insertAlertTime", o.toString());
            return false;
        }
        finally {
            db.close();
        }

        return true;

    }



}
