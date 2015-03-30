package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

import java.util.List;

/**
 * Created by fabiofilho on 20/03/15.
 */
public class EmergencyAdapter extends BaseAdapter {

    private Context context;
    private List<Emergency> mList;
    private LayoutInflater mLayoutInflater;


    public EmergencyAdapter(Context context, List<Emergency> mList){

        this.mList = mList;
        this.context = context;

        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(mList.get(position).getCode());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = mLayoutInflater.inflate(R.layout.list_emergency, null);

        Button btnEmergency = (Button) view.findViewById(R.id.btnEmergency);
        btnEmergency.setText(mList.get(position).getName());

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                Log.i("onClick", "clicked");
            }
        });

        return view;
    }
}
