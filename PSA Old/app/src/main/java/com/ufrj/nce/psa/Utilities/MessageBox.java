package com.ufrj.nce.psa.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by fabiofilho on 06/11/14.
 */
public class MessageBox {

    private static final String CAPTION_DEFAULT = "Atenção";

    public static int ID_YES = 1, ID_NO = 0, ID_CANCEL = 2;

    private static int TEMP_RESULT;

    public static int getTEMP_RESULT() {
        return TEMP_RESULT;
    }

    public static void setTEMP_RESULT(int tEMP_RESULT) {
        TEMP_RESULT = tEMP_RESULT;
    }

    public static void showOkCancel(Context context, String message, String caption, final View.OnClickListener onClickListener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(caption);
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Do do my action here
                //setTEMP_RESULT(ID_YES);
                onClickListener.onClick(null);
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // I do not need any action here you might
                //setTEMP_RESULT(ID_CANCEL);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        System.out.println(TEMP_RESULT);

    }

    private static void show(Context context, String message, String caption)
    {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(caption);
            builder.setMessage(message);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do do my action here
                    dialog.dismiss();
                }

            });

            AlertDialog alert = builder.create();
            alert.show();

        }catch (Exception o){o.printStackTrace();}

    }

    public static void showOk(Context context, String message)
    {
        show(context, message, CAPTION_DEFAULT);
    }

    public static void showOk(Context context, String message, String caption)
    {
        show(context,message,caption);
    }




}
