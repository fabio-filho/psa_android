package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergencyAddFragment extends Fragment {

    private View mRootView;


    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_emergencies, mContainer, false);

        return mRootView;
    }


}
