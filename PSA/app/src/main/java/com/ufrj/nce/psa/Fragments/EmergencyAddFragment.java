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
    public final static boolean SHOW_FLOATING_BUTTON = false;


    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_emergency_add, mContainer, false);

        return mRootView;
    }


    @Override
    public void onPause() {
        super.onPause();

    }
}
