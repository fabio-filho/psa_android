package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class InformationFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_information, container, false);

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_action_help_holo);

        return rootView;
    }
}
