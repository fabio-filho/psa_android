package com.ufrj.nce.psa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ufrj.nce.psa.Application.Data.Emergencies;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyAdapter;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergenciesFragment extends MyFragment  {

    private View mRootView;
    private ListView mListView;
    private EmergencyAdapter mEmergencyAdapter;
    private Emergencies mEmergencies;


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

        mEmergencies = new Emergencies().loadData(mRootView.getContext());
        defineUIObjects();
        loadListView();

        return mRootView;

    }


    private void defineUIObjects(){

        mListView = (ListView) mRootView.findViewById(R.id.mListViewEmergenciesFragment);


    }


    private void loadListView(){

        mEmergencyAdapter = new EmergencyAdapter(mRootView.getContext(), mEmergencies.getList(), new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                onClickEmergencyButton(mView);
            }
        });

        mListView.setAdapter(mEmergencyAdapter);

    }


    private void onClickEmergencyButton(View mView){

        Emergency mEmergency = mEmergencyAdapter.getItem(mListView.getPositionForView(mView));
        showSnackBar(mRootView.findFocus(), "Enviando "+mEmergency.getName(), false);
    }

}
