package com.ufrj.nce.psa.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        loadFromDBEmergencyItems();

        loadListViewEmergency(R.id.listViewHomeFragment);

        initializeListeners();

        return rootView;
    }


    private void initializeListeners(){

        Button btn = (Button) rootView.findViewById(R.id.btnEmergency);


    }


}