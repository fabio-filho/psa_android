package com.ufrj.nce.psa.Utilities;


import android.util.Log;

import com.ufrj.nce.psa.Objects.DateTime;
import com.ufrj.nce.psa.Objects.TextFile;

import java.io.File;
import java.util.Calendar;

/**
 * Created by fabiofilho on 11/10/14.
 */

public class Functions {

    public static void sleep(int time){

        try{
            Thread.sleep(time);
        }catch(Exception o){

        }
    }


    public static void Log(String title, String text){

        try{

            Log.i(title, text);

            Calendar calendar = Calendar.getInstance();
            String nameFile = new DateTime().getDay()
                    +new DateTime().getMonth()
                    +new DateTime().getYear()+".txt";

            String time = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
            time += ":"+String.valueOf(calendar.get(Calendar.MINUTE));

            //Create Folder.
            new File(Values.PATH_SDCARD_LOG).mkdir();

            String msg = "";

            msg += "\n"+
                    time + " || "+ title+ " : "+ text;

            TextFile.writeTextFile(new File(Values.PATH_SDCARD_LOG + nameFile), msg, true);

        }
        catch(Exception o){
            Functions.Log(title,o.toString());
        }
    }






}
