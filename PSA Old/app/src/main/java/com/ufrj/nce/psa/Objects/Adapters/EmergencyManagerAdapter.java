package com.ufrj.nce.psa.Objects.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

import java.util.List;

/**
 * Created by fabiofilho on 3/31/15.
 */
public class EmergencyManagerAdapter  extends EmergencyAdapter {

    private View.OnClickListener onClickDeleteItem;
    private TextView textViewContacts;

    public EmergencyManagerAdapter(Context context, List<Emergency> mList, View.OnClickListener onClickListener, View.OnClickListener onClickDeleteItem){

        super(context,  mList, onClickListener);

        this.mList = mList;
        this.context = context;
        this.onClickListener = onClickListener;
        this.onClickDeleteItem = onClickDeleteItem;

        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = mLayoutInflater.inflate(R.layout.item_manager_emergency, null);


        Button btnEmergencyEdit = (Button) view.findViewById(R.id.btnEmergencyEdit);
        btnEmergencyEdit.setText(mList.get(position).getName());

        btnEmergencyEdit.setOnClickListener(onClickListener);


        ImageButton btnEmergencyDelete = (ImageButton) view.findViewById(R.id.btnEmergencyDelete);
        btnEmergencyDelete.setOnClickListener(onClickDeleteItem);


        textViewContacts = (TextView)  view.findViewById(R.id.textViewEmergencyManagerViewContacts);

        if(mList.get(position).getCode().equals(Emergency.CODE_EMPTY)){
            btnEmergencyDelete.setVisibility(View.INVISIBLE);
            textViewContacts.setText("");
            return view;
        }

        textViewContacts.setText(mList.get(position).getListContact().getListContactsShow());

        return view;
    }
}
