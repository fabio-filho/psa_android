package com.ufrj.nce.psa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergencyEditFragment extends MyFragment {

    private View mRootView;


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

        mRootView = mInflater.inflate(R.layout.fragment_emergency_edit, mContainer, false);

        return mRootView;
    }
}
