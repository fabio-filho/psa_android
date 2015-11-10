package com.ufrj.nce.psa.Fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufrj.nce.psa.Objects.Dialogs.DialogListChoice;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class SettingsFragment extends Fragment {

    private View mRootView;

    private TextView mTimeIntervalTextView;

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_settings, mContainer, false);

        definingUIObjects();

        return mRootView;

    }



    private void definingUIObjects(){

        mTimeIntervalTextView = (TextView) mRootView.findViewById(R.id.mTextViewSettingsFragmentTimeInterval);
        mTimeIntervalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DialogListChoice();
                newFragment.show(getActivity().getFragmentManager(), "missiles");
            }
        });
    }

    
}
