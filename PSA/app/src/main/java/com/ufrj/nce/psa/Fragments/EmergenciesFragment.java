package com.ufrj.nce.psa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergenciesFragment extends MyFragment  {

    private View mRootView;
    public final static boolean SHOW_FLOATING_BUTTON = true;


    @Override
    public boolean isShowFloatingButton() {
        return true;
    }


    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return null;
    }

    @Override
    public int getFloatingButtonColor() {
        return android.R.color.holo_red_light;
    }

    @Override
    public int getFloatingButtonIcon() {
        return R.mipmap.ic_local_hospital_black_24dp;
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_emergencies, mContainer, false);

        return mRootView;

    }
}
