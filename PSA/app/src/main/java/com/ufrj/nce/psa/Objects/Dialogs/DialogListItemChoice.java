package com.ufrj.nce.psa.Objects.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by filhofilha on 11/12/15.
 */
public class DialogListItemChoice extends MyDialogFragment {

    private int mArrayList;


    public void setAttributes(String mTitle, String mButtonLabelCancel,
                              int mArrayList, DialogInterface.OnClickListener mDialogOnClickListenerChosenItem,
                              DialogInterface.OnClickListener mDialogOnClickListenerCancel) {

        this.mArrayList = mArrayList;

        this.mTitle = mTitle;

        if (mButtonLabelCancel != null)
            this.mButtonLabelCancel = mButtonLabelCancel;

        if (mDialogOnClickListenerChosenItem != null)
            this.mDialogOnClickListenerChosenItem = mDialogOnClickListenerChosenItem;
        else
            this.mDialogOnClickListenerChosenItem = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };


        if (mDialogOnClickListenerCancel != null)
            this.mDialogOnClickListenerCancel = mDialogOnClickListenerCancel;
        else
            this.mDialogOnClickListenerCancel = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };

    }

    @Override
    public Dialog onCreateDialog(Bundle mSavedInstanceState) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

        mBuilder.setTitle(mTitle)
                .setItems(mArrayList, mDialogOnClickListenerChosenItem)
                .setNegativeButton(mButtonLabelCancel, mDialogOnClickListenerCancel);

        return mBuilder.create();
    }

}