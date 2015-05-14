package com.ufrj.nce.psa.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.SettingsTable;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class SettingsFragment extends EmergencyFragment {

    private Boolean mSaveSettings = false;
    private ArrayAdapter<CharSequence> mAdapterSpinner;
    private Spinner mSpinner;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        this.rootView = rootView;

        if (Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_action_settings_holo);

        loadComponents();

        return rootView;
    }


    private void loadComponents() {


        try {
            mSpinner = (Spinner) rootView.findViewById(R.id.spinnerSettingsAlarmTime);

            mAdapterSpinner = ArrayAdapter.createFromResource(rootView.getContext(),
                    R.array.fragment_settings_array_time_alarm, android.R.layout.simple_spinner_item);

            mAdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinner.setAdapter(mAdapterSpinner);

            mSaveSettings = false;

            loadDefaultValue();

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    final int mPosition = position;

                    new Thread() {
                        @Override
                        public void run() {

                            saveAlertTime(mPosition);
                        }
                    }.start();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception o) {
            Functions.Log("loadComponents", o.toString());
        }


    }


    private void loadDefaultValue() {

        SQLiteDatabase db = new SettingsTable(rootView.getContext()).getWritableDatabase();
        int result = SQLite.getAlertTime(db);

        int spinnerPosition = mAdapterSpinner.getPosition(String.valueOf(result));
        mSpinner.setSelection(spinnerPosition);

        if (result != 0) return;

        db = new SettingsTable(rootView.getContext()).getWritableDatabase();
        SQLite.insertAlertTime(db, Integer.parseInt(mAdapterSpinner.getItem(0).toString()));


    }


    private void saveAlertTime(int position) {

        SQLiteDatabase db = new SettingsTable(rootView.getContext()).getWritableDatabase();
        SQLite.updateAlertTime(db, Integer.parseInt(mAdapterSpinner.getItem(position).toString()));
    }


}
