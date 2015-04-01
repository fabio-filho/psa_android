package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ufrj.nce.psa.Activities.MainActivity;
import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyAdapter;
import com.ufrj.nce.psa.Objects.ContactList;
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
    protected View.OnClickListener onClickListener;


    public void refreshListViewEmergency(){

        Functions.Log("refreshListViewEmergency", "Refreshed LisView Emergency");
    }

    protected void loadListViewEmergency(int layout){

        mListView = (ListView) rootView.findViewById(layout);

        checkListEmergencyIsEmpty();

        defineOnClickButtonItemListView();
    }


    protected void loadAdapterEmergencyDefault(){

        mAdapterEmergency = new EmergencyAdapter(getActivity().getApplicationContext(), mListEmergency, onClickListener);

        mListView.setAdapter(mAdapterEmergency);
    }


    private void checkListEmergencyIsEmpty(){

        if(mListEmergency.size() == 0)
            mListEmergency.add(new Emergency( Emergency.CODE_EMPTY, getResources().getString(R.string.fragment_home_no_emergencies)));
    }


    public void loadFromDBEmergencyItems(){

        SQLiteDatabase db = new EmergencyTable(getActivity().getApplicationContext()).getWritableDatabase();
        mListEmergency = SQLite.getEmergencies(db);

        loadEmergencyContactList();

        for(Emergency emergency: mListEmergency)
            Functions.Log("loadListEmergency", emergency.getName());
    }




    public void refreshHistoryEmergencyItems(){
        Toast.makeText(getActivity().getApplicationContext(), "Refresh History Emergency Items", Toast.LENGTH_SHORT).show();
    }


    private void defineOnClickButtonItemListView(){

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListView(view);
            }
        };
    }


    protected void onClickItemListView(View view){

        if (mAdapterEmergency.getItem(mListView.getPositionForView(view)).getCode().equals(Emergency.CODE_EMPTY)){
            openViewEmergency();
            refreshListViewEmergency();
            return;
        }

    }


    private void loadEmergencyContactList(){

        SQLiteDatabase db = new ContactTable(getActivity().getApplicationContext()).getWritableDatabase();

        for (int index=0; index < mListEmergency.size(); index++) {

            ContactList contactList = SQLite.getEmergencyContactList(db, mListEmergency.get(index).getCode());
            mListEmergency.get(index).setListContact(contactList);
        }

        db.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MainActivity.REQUEST_ACTIVITY_CODE)
            refreshListViewEmergency();
    }

    protected void openViewEmergency(){

        startActivityForResult(new Intent("android.intent.action.EMERGENCY_VIEW"), MainActivity.REQUEST_ACTIVITY_CODE);
    }


}
