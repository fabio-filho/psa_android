package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import com.ufrj.nce.psa.Objects.Emergency;

import java.util.List;

/**
 * Created by filhofilha on 11/11/15.
 */
public abstract class EmergencyAdapter extends BaseAdapter {

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


    public void removeItem(int position){
        mList.remove(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Emergency getItem(int position) {
        return mList.get(position);
    }

    public List<Emergency> getList(){
        return mList;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}