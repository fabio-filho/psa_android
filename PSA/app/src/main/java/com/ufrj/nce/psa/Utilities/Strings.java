package com.ufrj.nce.psa.Utilities;

/**
 * Created by fabiofilho on 23/10/14.
 */
public class Strings {

    public final static String


            //Caption Default for Message Box.
            CAPTION_DEFAULT = "Atenção";



    public static String setFirtLetterUppercase(String text){

        return text.substring(0, 1).toUpperCase() + text.substring(1, text.length());
    }

}
