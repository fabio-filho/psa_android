package com.ufrj.nce.psa.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class HistoryEmergencyFragment extends EmergencyFragment {

    public HistoryEmergencyFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history_emergency, container, false);

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_history_emergency_holo);


        refreshHistoryEmergencyItems();

        return rootView;
    }


}
