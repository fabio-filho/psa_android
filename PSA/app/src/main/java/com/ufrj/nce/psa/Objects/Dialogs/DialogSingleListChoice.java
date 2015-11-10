package com.ufrj.nce.psa.Objects.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by filhofilha on 10/11/15.
 */
public class DialogSingleListChoice extends MyDialogFragment {


    private int mArrayList, mArrayListIndex = 0;


    public void setAttributes(String mTitle, String mButtonLabelOk, String mButtonLabelCancel,
                              int mArrayList, int mArrayListIndex, DialogInterface.OnClickListener mDialogOnClickListenerChosenItem,
                              DialogInterface.OnClickListener mDialogOnClickListenerOk,
                              DialogInterface.OnClickListener mDialogOnClickListenerCancel) {

        this.mArrayList = mArrayList;
        this.mArrayListIndex = mArrayListIndex;

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

    @Override
    public Dialog onCreateDialog(Bundle mSavedInstanceState) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

        mBuilder.setTitle(mTitle)
                .setSingleChoiceItems(mArrayList, mArrayListIndex, mDialogOnClickListenerChosenItem)
                .setPositiveButton(mButtonLabelOk, mDialogOnClickListenerOk)
                .setNegativeButton(mButtonLabelCancel, mDialogOnClickListenerCancel);

        return mBuilder.create();
    }

}