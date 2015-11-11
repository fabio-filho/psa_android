package com.ufrj.nce.psa.Objects.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by filhofilha on 11/11/15.
 */
public class DialogEditText extends MyDialogFragment {


    private String mNameEditTextHint;
    private EditText mNameEditText;
    private static String mNameEditTextValue;

    public void setAttributes(String mTitle, String mNameEditTextHint, String mButtonLabelOk, String mButtonLabelCancel,
                              DialogInterface.OnClickListener mDialogOnClickListenerOk,
                              DialogInterface.OnClickListener mDialogOnClickListenerCancel) {


        this.mNameEditTextHint = mNameEditTextHint;
        this.mTitle = mTitle;
        if(mButtonLabelOk != null)
            this.mButtonLabelOk = mButtonLabelOk;
        if(mButtonLabelCancel != null)
            this.mButtonLabelCancel = mButtonLabelCancel;


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
                    dialog.cancel();
                }
            };

    }


    @Override
    public Dialog onCreateDialog(Bundle mSavedInstanceState) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

        mNameEditText = new EditText(getActivity());
        mNameEditText.setText(mNameEditTextHint);
        mNameEditTextValue = mNameEditTextHint;

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNameEditTextValue = mNameEditText.getText().toString();
            }
        });

        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        mNameEditText.setPadding(20,20,20,20);
        mNameEditText.setLayoutParams(mLayoutParams);


        mNameEditText.setSingleLine();
        mBuilder.setView(mNameEditText);

        mBuilder.setTitle(mTitle)
                .setPositiveButton(mButtonLabelOk, mDialogOnClickListenerOk)
                .setNegativeButton(mButtonLabelCancel, mDialogOnClickListenerCancel);

        return mBuilder.create();
    }


    public static String getNameEditTextValue(){
        return mNameEditTextValue;
    }
}
