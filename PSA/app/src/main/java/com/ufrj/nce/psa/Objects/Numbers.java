package com.ufrj.nce.psa.Objects;

/**
 * Created by fabiofilho on 3/9/15.
 */
public class Numbers {

    public static Boolean toBoolean(String string){

        if (string.equals("false"))
            return false;
        if (string.equals("true"))
            return true;

        if (string.equals("0"))
            return false;
        else
            return true;

    }

    public static int toInt(String string){

        try{
            return Integer.parseInt(string);
        }catch(Exception o){
            return 0;
        }


    }


    public static int toInt(Boolean bool){

        try{
            if(bool) return 1;
            else return 0;

        }catch(Exception o){
            return 0;
        }


    }


}
