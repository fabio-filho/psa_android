package com.ufrj.nce.psa.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyAdapter;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;

import java.util.List;

public class HomeFragment extends EmergencyFragment {

    private ListView mListView;
    private EmergencyAdapter mAdapterEmergency;
    private List<Emergency> mListEmergency;
    private View rootView;

    public HomeFragment(){



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.rootView = rootView;

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_home);

        refreshEmergencyItems();

        loadListEmergency();

        loadListView();

        return rootView;
    }


    private void loadListEmergency(){

        SQLiteDatabase db = new EmergencyTable(getActivity().getApplicationContext()).getWritableDatabase();
        mListEmergency = SQLite.getEmergencies(db);

        for(Emergency emergency: mListEmergency)
            Functions.Log("loadListEmergency", emergency.getName() );
    }


    private void loadListView(){

        mListView = (ListView) rootView.findViewById(R.id.listViewHomeFragment);

        checkListEmergencyIsEmpty();

        mAdapterEmergency = new EmergencyAdapter(getActivity().getApplicationContext(), mListEmergency);

        mListView.setAdapter(mAdapterEmergency);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void checkListEmergencyIsEmpty(){

        Functions.Log("loadListView", mListEmergency+"");
        if(mListEmergency.size() == 0)
            mListEmergency.add(new Emergency( "0", "Sem Emergências - Clique para Adicionar"));

        Functions.Log("loadListView", mListEmergency.size()+"");

        Functions.Log("loadListView", getActivity().getApplicationContext()+"");

    }




}