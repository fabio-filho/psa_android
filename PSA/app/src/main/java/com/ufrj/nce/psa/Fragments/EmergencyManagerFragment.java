package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class EmergencyManagerFragment  extends Fragment {

    public EmergencyManagerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_emergency_manager, container, false);

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_emergency);

        return rootView;
    }
}
