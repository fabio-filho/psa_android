package com.ufrj.nce.psa.Objects.Dialogs;

import android.app.FragmentManager;
import android.content.DialogInterface;

/**
 * Created by filhofilha on 11/10/15.
 */
public class Dialogs {

    public static void showChooseSingleItemFromList(FragmentManager mFragmentManager, String mTitle, String mButtonLabelOk, String mButtonLabelCancel,
                                                    int mArrayList, int mArrayListIndex, DialogInterface.OnClickListener mDialogOnClickListenerChosenItem,
                                                    DialogInterface.OnClickListener mDialogOnClickListenerOk,
                                                    DialogInterface.OnClickListener mDialogOnClickListenerCancel) {


        DialogSingleListChoice mDialogFragment = new DialogSingleListChoice();

        mDialogFragment.setAttributes(mTitle, mButtonLabelOk, mButtonLabelCancel, mArrayList, mArrayListIndex,
                mDialogOnClickListenerChosenItem, mDialogOnClickListenerOk, mDialogOnClickListenerCancel);

        mDialogFragment.show(mFragmentManager, DialogSingleListChoice.class.getName());
    }


    public static void showChooseOptionItemFromList(FragmentManager mFragmentManager, String mTitle, String mButtonLabelCancel,
                                                    int mArrayList, DialogInterface.OnClickListener mDialogOnClickListenerChosenItem,

                                                    DialogInterface.OnClickListener mDialogOnClickListenerCancel) {


        DialogListItemChoice mDialogFragment = new DialogListItemChoice();

        mDialogFragment.setAttributes(mTitle, mButtonLabelCancel, mArrayList,
                mDialogOnClickListenerChosenItem, mDialogOnClickListenerCancel);

        mDialogFragment.show(mFragmentManager, DialogSingleListChoice.class.getName());
    }


    public static void showEditText(FragmentManager mFragmentManager, String mTitle, String mEditTextHint, String mButtonLabelOk, String mButtonLabelCancel,
                                                    DialogInterface.OnClickListener mDialogOnClickListenerOk,
                                                    DialogInterface.OnClickListener mDialogOnClickListenerCancel) {


        DialogEditText mDialogFragment = new DialogEditText();

        mDialogFragment.setAttributes(mTitle, mEditTextHint, mButtonLabelOk, mButtonLabelCancel,
                mDialogOnClickListenerOk, mDialogOnClickListenerCancel);

        mDialogFragment.show(mFragmentManager, DialogEditText.class.getName());
    }



}
