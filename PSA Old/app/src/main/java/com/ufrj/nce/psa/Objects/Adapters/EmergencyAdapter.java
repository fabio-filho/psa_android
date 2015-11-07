package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

import java.util.List;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class EmergencyAdapter extends BaseAdapter {

    protected Context context;
    protected List<Emergency> mList;
    protected LayoutInflater mLayoutInflater;
    protected View.OnClickListener onClickListener;


    public EmergencyAdapter(Context context, List<Emergency> mList, View.OnClickListener onClickListener){

        this.mList = mList;
        this.context = context;
        this.onClickListener = onClickListener;

        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Emergency getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(mList.get(position).getCode());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = mLayoutInflater.inflate(R.layout.item_emergency, null);

        Button btnEmergency = (Button) view.findViewById(R.id.btnEmergency);
        btnEmergency.setText(mList.get(position).getName());

        btnEmergency.setOnClickListener(onClickListener);

        return view;
    }
}
