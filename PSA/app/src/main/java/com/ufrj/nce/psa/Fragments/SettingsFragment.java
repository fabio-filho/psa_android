package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ufrj.nce.psa.Application.MySettings;
import com.ufrj.nce.psa.Objects.Dialogs.Dialogs;
import com.ufrj.nce.psa.Objects.Utilities;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class SettingsFragment extends Fragment {

    private View mRootView;

    private TextView mTimeIntervalTextView, mTimeIntervalExplanationTextView;
    private String mChosenItemLabel;
    private int mChosenItemIndex;
    private MySettings mMySettings;
    private Switch mGPSSSwitch;

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_settings, mContainer, false);

        //Loading settings.
        mMySettings = new MySettings().loadData(mRootView.getContext());

        definingUIObjects();

        return mRootView;

    }



    private void definingUIObjects(){

        mTimeIntervalTextView            = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTimeInterval);
        mTimeIntervalExplanationTextView = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTimeIntervalExplanation);
        mGPSSSwitch                      = (Switch)   mRootView.findViewById(R.id.mSwitchSettingsFragmentUseGPS);

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
    }



    private void showAlarmIntervalDialog(){

        Utilities.log("mMySettings: "+mMySettings);

        Dialogs.showChooseSingleItemFromList(getFragmentManager(),
                getActivity().getString(R.string.fragment_settings_dialog_alert_time_label_button_title),
                getActivity().getString(R.string.fragment_settings_dialog_alert_time_label_button_ok),
                getActivity().getString(R.string.fragment_settings_dialog_alert_time_label_button_cancel),
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
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                }
        );
    }
}
