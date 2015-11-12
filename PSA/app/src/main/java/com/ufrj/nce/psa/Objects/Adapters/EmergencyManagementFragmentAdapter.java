package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

import java.util.List;

/**
 * Created by filhofilha on 11/12/15.
 */
public class EmergencyManagementFragmentAdapter extends  EmergencyAdapter{


    public EmergencyManagementFragmentAdapter(Context context, List<Emergency> mList, View.OnClickListener onClickListener) {
        super(context, mList, onClickListener);
    }

    @Override
    public View getView(int position, View mView, ViewGroup parent) {

        mView = mLayoutInflater.inflate(R.layout.list_view_emergency_management_fragment_item, null);

        Button mButtonEmergency = (Button) mView.findViewById(R.id.mButtonEmergencyManagementFragmentListView);
        mButtonEmergency.setText(mList.get(position).getName());

        mButtonEmergency.setOnClickListener(onClickListener);

        return mView;
    }
}
