package com.ufrj.nce.psa.Connections.Tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ufrj.nce.psa.Connections.SQLite;

/**
 * Created by fabiofilho on 5/14/15.
 */
public class SettingsTable extends SQLiteOpenHelper {

    public static final String TABLE_NAME              = "table_settings";
    public static final String FIELD_CODE              = "code";
    public static final String FIELD_ALARM_TIME        = "alarm_time";



    // creation SQLite statement
    public static final String TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
            "    "+FIELD_CODE+"          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "    "+FIELD_ALARM_TIME+"    INTEGER  NOT NULL" +
            ");\n";


    public SettingsTable(Context context) {
        super(context, SQLite.DATABASE_NAME, null, SQLite.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

}