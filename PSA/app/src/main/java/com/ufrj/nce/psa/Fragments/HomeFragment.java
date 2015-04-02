package com.ufrj.nce.psa.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.Objects.EmergencySMS;
import com.ufrj.nce.psa.R;

public class HomeFragment extends EmergencyFragment {

    public HomeFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.rootView = rootView;

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_home);

        refreshListViewEmergency();

        return rootView;
    }


    @Override
    public void refreshListViewEmergency() {
        super.refreshListViewEmergency();

        loadFromDBEmergencyItems();

        loadListViewEmergency(R.id.listViewHomeFragment);

        loadAdapterEmergencyDefault();
    }

    @Override
    protected void onClickItemListView(View view) {

        if (mAdapterEmergency.getItem(mListView.getPositionForView(view)).getCode().equals(Emergency.CODE_EMPTY)){
            openViewEmergency();
            refreshListViewEmergency();
            return;
        }


        EmergencySMS emergencySMS = new EmergencySMS(rootView.getContext(), mAdapterEmergency.getItem(mListView.getPositionForView(view)).getName());

        Toast.makeText(getActivity().getApplicationContext(), "Enviando Emergencia", Toast.LENGTH_SHORT).show();

        mAdapterEmergency.getItem(mListView.getPositionForView(view)).sendAlertToAllContacts(emergencySMS.getMessageToSend());

        Toast.makeText(getActivity().getApplicationContext(), "Emergencia Enviada", Toast.LENGTH_LONG).show();

    }



}