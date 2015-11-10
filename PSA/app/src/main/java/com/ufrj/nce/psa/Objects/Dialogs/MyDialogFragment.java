package com.ufrj.nce.psa.Objects.Dialogs;

import android.app.DialogFragment;
import android.content.DialogInterface;

/**
 * Created by filhofilha on 11/10/15.
 */
public abstract class MyDialogFragment extends DialogFragment {



    protected String mTitle;
    protected String mButtonLabelOk = "Ok";
    protected String mButtonLabelCancel = "Cancel";
    protected DialogInterface.OnClickListener mDialogOnClickListenerChosenItem, mDialogOnClickListenerOk, mDialogOnClickListenerCancel;


    public void setAttributes(String mTitle, String mButtonLabelOk, String mButtonLabelCancel,
                              DialogInterface.OnClickListener mDialogOnClickListenerChosenItem,
                              DialogInterface.OnClickListener mDialogOnClickListenerOk,
                              DialogInterface.OnClickListener mDialogOnClickListenerCancel) {


        this.mTitle = mTitle;
        if(mButtonLabelOk != null)
            this.mButtonLabelOk = mButtonLabelOk;
        if(mButtonLabelCancel != null)
            this.mButtonLabelCancel = mButtonLabelCancel;


        if(mDialogOnClickListenerChosenItem != null)
            this.mDialogOnClickListenerChosenItem = mDialogOnClickListenerChosenItem;
        else
            this.mDialogOnClickListenerChosenItem = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };


        if(mDialogOnClickListenerOk != null)
            this.mDialogOnClickListenerOk = mDialogOnClickListenerOk;
        else
            this.mDialogOnClickListenerOk = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };


        if(mDialogOnClickListenerCancel != null)
            this.mDialogOnClickListenerCancel = mDialogOnClickListenerCancel;
        else
            this.mDialogOnClickListenerCancel = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };

    }
}
