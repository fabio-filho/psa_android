package com.ufrj.nce.psa.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class EmergencyManagerFragment  extends EmergencyFragment {

    public EmergencyManagerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_emergency_manager, container, false);
        this.rootView = rootView;

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_emergency);


        refreshEmergencyItems();

        loadListView(R.id.listViewEmergencyManagerFragment);

        loadBtnEmergencyListView();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(), "myPos " + i, Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }


}
