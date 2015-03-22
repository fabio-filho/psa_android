package com.ufrj.nce.psa.Connections.Tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ufrj.nce.psa.Connections.SQLite;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class EmergencyTable extends SQLiteOpenHelper {

    public static final String TABLE_NAME              = "table_emergency";
    public static final String FIELD_CODE              = "code";
    public static final String FIELD_NAME              = "name";



    // creation SQLite statement
    public static final String TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
            "    "+FIELD_CODE+"    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "    "+FIELD_NAME+"    VARCHAR( 1, 100 )  NOT NULL" +
            ");\n";


    public EmergencyTable(Context context) {
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