package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;

import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyHistoryTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Connections.Tables.SettingsTable;
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

                startActivity(new Intent("android.intent.action.PSA_MENU"));
                finish();

            }
        }.start();

        int result = 0;

        Functions.Log("onCreate", ((result == 0) ?  "Ok" : "Not"));
        //createShortCut();
    }



    public void createShortCut(){

        // a Intent to create a shortCut
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //repeat to create is forbidden
        shortcutintent.putExtra("duplicate", true);
        //set the name of shortCut
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Call Emergency");
        //set icon
        Parcelable icon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_emergency);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //set the application to lunch when you click the icon
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getApplicationContext(), PSAMainActivity.class));
        //sendBroadcast,done
        sendBroadcast(shortcutintent);
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

        db = new EmergencyHistoryTable(this).getWritableDatabase() ;
        db.execSQL(EmergencyHistoryTable.TABLE_CREATE);
        db.close();

        db = new SettingsTable(this).getWritableDatabase() ;
        db.execSQL(SettingsTable.TABLE_CREATE);
        db.close();


    }
}
