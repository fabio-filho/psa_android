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

            db.execSQL("insert into " + EmergencyTable.TABLE_NAME + " VALUES(null, '" + emergency.getName() + "') ");
            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }

    }


    public static void insertHistoryEmergency(SQLiteDatabase db, Emergency emergency, Contact contact, Boolean isReceived){

        try {

            db.execSQL("insert into " + HistoryEmergencyTable.TABLE_NAME + " VALUES(null, NOW(), '"
                    + contact.getNumber()+"', '"
                    + emergency.getName()+"', '"
                    + isReceived.toString()+"') ");
            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }

    }



    public static void insertContact(SQLiteDatabase db, Emergency emergency, Contact contact){

        try {

            db.execSQL("insert into " + ContactTable.TABLE_NAME + " VALUES(null, '"
                    + emergency.getCode() + "', '"
                    + contact.getName()+"', '"
                    + contact.getNumber()+"') ");

            db.close();

        }catch(Exception o){
            Functions.Log("insertEmergency", o.toString());
        }

    }









}
