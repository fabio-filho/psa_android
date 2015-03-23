package com.ufrj.nce.psa.Connections;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Connections.Tables.HistoryEmergencyTable;
import com.ufrj.nce.psa.Objects.Contact;
import com.ufrj.nce.psa.Objects.Emergency;
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
            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }

    }


    public static void deleteEmergencyWithContacts(SQLiteDatabase db, Emergency emergency){

        try{
            db.execSQL("delete from "+EmergencyTable.TABLE_NAME+ " where "+EmergencyTable.FIELD_CODE+ " = '"+emergency.getCode()+"'");

            db.execSQL("delete from "+ContactTable.TABLE_NAME+ " where "+ContactTable.FIELD_EMERGENCY+ " = '"+emergency.getCode()+"'");

        }catch(Exception o){
            Functions.Log("deleteEmergency", o.toString());
        }


    }


    public static void insertHistoryEmergency(SQLiteDatabase db, Emergency emergency, Contact contact, Boolean isReceived){

        try {

            db.execSQL("insert into " + HistoryEmergencyTable.TABLE_NAME + " VALUES(null, NOW(), '"
                    + contact.getNumber()+"', '"
                    + emergency.getName()+"', '"
                    + isReceived.toString()+"', '"
                    + contact.getLatitude()+"', '"
                    + contact.getLongitude()+"') ");

            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }

    }


    public static void insertContact(SQLiteDatabase db, Emergency emergency){

        try {

            for(Contact contact: emergency.getListContact().getListContacts())
                db.execSQL("insert into " + ContactTable.TABLE_NAME + " VALUES(null, '"
                        + emergency.getCode() + "', '"
                        + contact.getName() + "', '"
                        + contact.getNumber() + "') ");

            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
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

        return mListEmergency;
    }






}
