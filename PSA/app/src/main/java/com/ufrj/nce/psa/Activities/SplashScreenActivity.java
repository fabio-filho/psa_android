package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Connections.Tables.HistoryEmergencyTable;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;
import com.ufrj.nce.psa.Utilities.Values;

import java.io.File;

/**
 * Created by fabiofilho on 22/03/15.
 */
public class SplashScreenActivity extends Activity{

    private final int TIME_SPLASH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        createDirs();
        createTables();

        new Thread(){
            @Override
            public void run() {

                Functions.sleep(TIME_SPLASH);

                startActivity(new Intent("android.intent.action.MENU"));
                finish();
            }
        }.start();
    }

    private void createDirs(){

       // new File(Values.PATH_SDCARD).mkdirs();
        new File(Values.PATH_SDCARD_LOG).mkdirs();
    }

    private void createTables(){

        SQLiteDatabase db;
        db = new EmergencyTable(this).getWritableDatabase() ;
        db.execSQL(EmergencyTable.TABLE_CREATE);
        db.close();

        db = new ContactTable(this).getWritableDatabase() ;
        db.execSQL(ContactTable.TABLE_CREATE);
        db.close();

        db = new HistoryEmergencyTable(this).getWritableDatabase() ;
        db.execSQL(HistoryEmergencyTable.TABLE_CREATE);
        db.close();


    }
}
