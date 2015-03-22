package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.widget.Toast;

import com.ufrj.nce.psa.Objects.Emergency;

import java.util.List;

/**
 * Created by fabiofilho on 3/21/15.
 */
public class EmergencyFragment extends Fragment {


    public void removeEmergency(int code){
        Toast.makeText(getActivity().getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
    }

    public void addEmergency(){
        Toast.makeText(getActivity().getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
    }

    public void refreshEmergencyItems(){
        Toast.makeText(getActivity().getApplicationContext(), "Refresh Emergency Items", Toast.LENGTH_SHORT).show();
    }

    public void refreshHistoryEmergencyItems(){
        Toast.makeText(getActivity().getApplicationContext(), "Refresh History Emergency Items", Toast.LENGTH_SHORT).show();
    }


    private void insertEmergencyInDB(List<Emergency> mList){

    }


}
