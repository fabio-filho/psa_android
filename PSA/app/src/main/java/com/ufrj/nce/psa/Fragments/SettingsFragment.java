package com.ufrj.nce.psa.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ufrj.nce.psa.Application.Data.Settings;
import com.ufrj.nce.psa.Objects.Dialogs.DialogEditText;
import com.ufrj.nce.psa.Objects.Dialogs.Dialogs;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class SettingsFragment extends MyFragment {

    private View mRootView;

    private TextView mTimeIntervalTextView, mTimeIntervalExplanationTextView, mNameTextView, mTitleNameTextView;
    private String mChosenItemLabel;
    private int mChosenItemIndex;
    private Settings mMySettings;
    private Switch mGPSSSwitch;

    @Override
    public boolean isShowFloatingButton() {
        return false;
    }

    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return null;
    }

    @Override
    public int getFloatingButtonColor() {
        return 0;
    }

    @Override
    public int getFloatingButtonIcon() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_settings, mContainer, false);

        //Loading settings.
        mMySettings = new Settings().loadData(mRootView.getContext());

        definingUIObjects();

        return mRootView;

    }



    private void definingUIObjects(){

        mTimeIntervalTextView            = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTimeInterval);
        mTimeIntervalExplanationTextView = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTimeIntervalExplanation);
        mGPSSSwitch                      = (Switch)   mRootView.findViewById(R.id.mSwitchSettingsFragmentUseGPS);
        mNameTextView                    = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentName);
        mTitleNameTextView               = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTitleName);

        if(mMySettings.getName().equals(""))
            mMySettings.setName(mRootView.getResources().getString(R.string.fragment_settings_label_default_name));

        mNameTextView.setText(mMySettings.getName());
        mGPSSSwitch.setChecked(mMySettings.getUseGPS());

        mTimeIntervalExplanationTextView.setText(getActivity().getResources().
                getStringArray(R.array.fragment_settings_array_time_alarm)[mMySettings.getIndexAlarmIntervalMinutesArray()]);

        mTimeIntervalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarmIntervalDialog();
            }
        });

        mTimeIntervalExplanationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarmIntervalDialog();
            }
        });

        mGPSSSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mMySettings.setUseGPS(isChecked);
                mMySettings.saveData(mRootView.getContext());
            }
        });

        mTitleNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextNameDialog();
            }
        });

        mNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextNameDialog();
            }
        });
    }


    private void showEditTextNameDialog(){

        Dialogs.showEditText(getFragmentManager(),
                getActivity().getString(R.string.fragment_settings_dialog_change_name_title),
                mMySettings.getName(),
                getActivity().getString(R.string.fragment_settings_dialog_label_button_ok),
                getActivity().getString(R.string.fragment_settings_dialog_label_button_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mMySettings.setName(DialogEditText.getNameEditTextValue());
                        mMySettings.saveData(mRootView.getContext());

                        mNameTextView.setText(mMySettings.getName());
                    }
                },
                null
        );
    }


    private void showAlarmIntervalDialog(){

        Dialogs.showChooseSingleItemFromList(getFragmentManager(),
                getActivity().getString(R.string.fragment_settings_dialog_change_alert_time_interval_title),
                getActivity().getString(R.string.fragment_settings_dialog_label_button_ok),
                getActivity().getString(R.string.fragment_settings_dialog_label_button_cancel),
                R.array.fragment_settings_array_time_alarm, mMySettings.getIndexAlarmIntervalMinutesArray(),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mChosenItemLabel = getActivity().getResources().getStringArray(R.array.fragment_settings_array_time_alarm)[which];
                        mChosenItemIndex = which;
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mTimeIntervalExplanationTextView.setText(mChosenItemLabel);
                        mMySettings.setIndexAlarmIntervalMinutesArray(mChosenItemIndex);
                        mMySettings.saveData(mRootView.getContext());
                    }
                },
                null
        );
    }
}
