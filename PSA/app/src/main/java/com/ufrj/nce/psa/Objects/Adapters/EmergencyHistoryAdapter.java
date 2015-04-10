package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ufrj.nce.psa.Objects.EmergencyHistory;
import com.ufrj.nce.psa.R;

import java.util.List;

/**
 * Created by filhoefilha on 10/04/15.
 */
public class EmergencyHistoryAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<EmergencyHistory> mList;
    private View.OnClickListener mOnClickListener;
    private TextView txtMessage, txtContact, txtDatetime;

    public EmergencyHistoryAdapter(Context mContext, List<EmergencyHistory> mList,
                                   View.OnClickListener mOnClickListener){

        this.mList = mList;
        this.mContext = mContext;
        this.mOnClickListener = mOnClickListener;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public EmergencyHistory getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(mList.get(position).getCode());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = mLayoutInflater.inflate(R.layout.item_emergency_history, null);

        //Button btnEmergency = (Button) view.findViewById(R.id.btnEmergency);
        //btnEmergency.setText(mList.get(position).getName());
        //btnEmergency.setOnClickListener(mOnClickListener);
        txtMessage  = (TextView) view.findViewById(R.id.txtEmergencyHistoryMessage);
        txtContact  = (TextView) view.findViewById(R.id.txtEmergencyHistorySender);
        txtDatetime = (TextView) view.findViewById(R.id.txtEmergencyHistoryDatetimeReceived);

        txtMessage.setText(mList.get(position).getMessage());
        txtContact.setText(mList.get(position).getContact().getName());
        txtDatetime.setText(mList.get(position).getDatetime().toString());

        return view;
    }
}

