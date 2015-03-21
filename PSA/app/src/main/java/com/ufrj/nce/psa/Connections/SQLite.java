package com.ufrj.nce.psa.Connections;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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





}
