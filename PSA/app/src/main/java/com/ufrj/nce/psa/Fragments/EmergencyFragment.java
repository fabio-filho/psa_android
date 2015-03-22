package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyAdapter;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;

import java.util.List;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class EmergencyFragment extends Fragment {

    protected List<Emergency> mListEmergency;

    protected ListView mListView;
    protected EmergencyAdapter mAdapterEmergency;
    protected View rootView;


    protected void loadListView(int layout){

        mListView = (ListView) rootView.findViewById(layout);

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

        if(mListEmergency.size() == 0)
            mListEmergency.add(new Emergency( "-1", getResources().getString(R.string.fragment_home_no_emergencies)));

    }


    protected void onClickEmergency(){
        Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
    }

    public void removeEmergency(int code){
        Toast.makeText(getActivity().getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
    }

    public void addEmergency(){
        Toast.makeText(getActivity().getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
    }

    public void refreshEmergencyItems(){

        SQLiteDatabase db = new EmergencyTable(getActivity().getApplicationContext()).getWritableDatabase();
        mListEmergency = SQLite.getEmergencies(db);

        for(Emergency emergency: mListEmergency)
            Functions.Log("loadListEmergency", emergency.getName());
    }

    public void refreshHistoryEmergencyItems(){
        Toast.makeText(getActivity().getApplicationContext(), "Refresh History Emergency Items", Toast.LENGTH_SHORT).show();
    }


    private void insertEmergencyInDB(List<Emergency> mList){

    }


}
