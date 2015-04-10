package com.ufrj.nce.psa.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyHistoryAdapter;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.Objects.EmergencyHistory;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;

import java.util.List;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class HistoryEmergencyFragment extends EmergencyFragment {

    private ListView mListView;
    private EmergencyHistoryAdapter mAdapterEmergencyHistory;
    private List<EmergencyHistory> mListEmergencyHistory;


    public HistoryEmergencyFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_emergency_history, container, false);

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_history_emergency_holo);

        loadListViewEmergencyHistory(R.layout.item_emergency_history);
        loadAdapterEmergencyHistory();
        refreshHistoryEmergencyItems();

        return rootView;
    }


    public void refreshHistoryEmergencyItems(){


    }

    public void loadFromDBEmergencyItems(){

        SQLiteDatabase db = new EmergencyTable(getActivity().getApplicationContext()).getWritableDatabase();
        mListEmergency = SQLite.getEmergencies(db);

        for(Emergency emergency: mListEmergency)
            Functions.Log("loadListEmergency", emergency.getName());
    }



    protected void onClickItemListView(View view){

        //TODO
    }

    private void loadListViewEmergencyHistory(int layout){

        mListView = (ListView) rootView.findViewById(layout);

        defineOnClickButtonItemListView();
    }


    private void loadAdapterEmergencyHistory(){

        mAdapterEmergencyHistory = new EmergencyHistoryAdapter(getActivity().getApplicationContext(),
                mListEmergencyHistory, onClickListener);

        mListView.setAdapter(mAdapterEmergencyHistory);
    }



}
