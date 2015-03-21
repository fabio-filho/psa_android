package com.ufrj.nce.psa.Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by fabiofilho on 3/13/15.
 */
public class TextFile {



    public static void writeTextFile(File file, String text, Boolean addMode)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, addMode));
            writer.write(text);
            writer.close();
        }catch (Exception o){
            android.util.Log.i("writeTextFile", o.toString());
        }
    }


    public static String readTextFile(File file)
    {
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(file));
            StringBuilder text = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null)
            {
                text.append(line);
                text.append("\n");
            }
            reader.close();

            return text.toString();
        }
        catch(Exception o){
            android.util.Log.i("readTextFile", o.toString());
            return "";
        }
    }

}
