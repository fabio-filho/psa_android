package com.ufrj.nce.psa.Connections.Tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ufrj.nce.psa.Connections.SQLite;

/**
 * Created by fabiofilho on 03/02/15.
 */
public class PlayListScheduleTable extends SQLiteOpenHelper {

    public static final String TABLE_NAME              = "table_playlist_schedule";
    public static final String FIELD_CODE              = "code";
    public static final String FIELD_CODE_FILE         = "code_file";
    public static final String FIELD_CODE_PLAYLIST     = "code_playlist";
    public static final String FIELD_DATE_TIME_MODE    = "date_time_mode";
    public static final String FIELD_ONLY_TIME         = "only_time";
    public static final String FIELD_ONLY_DATE         = "only_date";
    public static final String FIELD_WEEK_DAYS         = "week_days";
    public static final String FIELD_TIME_START        = "time_start";
    public static final String FIELD_TIME_END          = "time_end";
    public static final String FIELD_TIME_FIXED        = "time_fixed";
    public static final String FIELD_EXPIRATION_START  = "expiration_start";
    public static final String FIELD_EXPIRATION_END    = "expiration_end";




    // creation SQLite statement
    public static final String TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
            "    "+FIELD_CODE+"               VARCHAR( 1, 45 ) PRIMARY KEY," +
            "    "+FIELD_CODE_PLAYLIST+"      VARCHAR( 1, 45 )  NOT NULL," +
            "    "+FIELD_CODE_FILE+"          VARCHAR( 1, 45 )  NOT NULL," +
            "    "+FIELD_DATE_TIME_MODE+"     BOOLEAN, " +
            "    "+FIELD_ONLY_TIME+"          BOOLEAN, " +
            "    "+FIELD_ONLY_DATE+"          BOOLEAN, " +
            "    "+FIELD_WEEK_DAYS+"          VARCHAR( 1, 45 ), " +
            "    "+FIELD_TIME_START+"         DATETIME, " +
            "    "+FIELD_TIME_END+"           DATETIME, " +
            "    "+FIELD_TIME_FIXED+"         BOOLEAN, " +
            "    "+FIELD_EXPIRATION_START+"   DATETIME, " +
            "    "+FIELD_EXPIRATION_END+"     DATETIME" +
            ");\n";

    public PlayListScheduleTable(Context context) {
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