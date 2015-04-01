package com.ufrj.nce.psa.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyManagerAdapter;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.MessageBox;

/**
 * Created by fabiofilho on 3/20/15.
 */
public class EmergencyManagerFragment  extends EmergencyFragment {


    private View.OnClickListener onClickListenerDeleteItem;


    public EmergencyManagerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_emergency_manager, container, false);
        this.rootView = rootView;

        if(Build.VERSION.SDK_INT >= 14)
            getActivity().getActionBar().setIcon(R.mipmap.ic_emergency);

        refreshListViewEmergency();

        return rootView;
    }


    @Override
    public void refreshListViewEmergency() {

        super.refreshListViewEmergency();

        loadFromDBEmergencyItems();

        loadListViewEmergency(R.id.listViewEmergencyManagerFragment);

        if (mListEmergency.get(0).getCode().equals(Emergency.CODE_EMPTY)) {
            loadAdapterEmergencyDefault();
            return;
        }

        loadAdapterEmergencyManager();
    }

    private void loadAdapterEmergencyManager(){

        defineOnClickButtonDeleteItemListView();

        mAdapterEmergency = new EmergencyManagerAdapter(getActivity().getApplicationContext(), mListEmergency, onClickListener, onClickListenerDeleteItem);

        mListView.setAdapter(mAdapterEmergency);
    }


    private void defineOnClickButtonDeleteItemListView(){

        onClickListenerDeleteItem = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmationForDeleteItem(view);
            }
        };
    }


    private Boolean confirmationForDeleteItem(final View mView){

        MessageBox.showOkCancel(getActivity(),
                getResources().getString(R.string.fragment_emergency_manager_remove_item)+
                        " '"+mAdapterEmergency.getItem(mListView.getPositionForView(mView)).getName()+"' ?",
                getResources().getString(R.string.fragment_emergency_manager_remove_item_title), new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                removeEmergency(mView);

                refreshListViewEmergency();
            }
        });

        return false;
    }


    private void removeEmergency(View view){

        Emergency emergency = mAdapterEmergency.getItem(mListView.getPositionForView(view));

        SQLiteDatabase db = new EmergencyTable(getActivity().getApplicationContext()).getWritableDatabase();
        SQLite.deleteEmergencyWithContacts(db, emergency);

        Toast.makeText(getActivity().getApplicationContext(), emergency.getName()+" - Removido", Toast.LENGTH_SHORT).show();
    }



}
