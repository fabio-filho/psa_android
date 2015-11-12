package com.ufrj.nce.psa.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ufrj.nce.psa.Application.Data.Emergencies;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyAdapter;
import com.ufrj.nce.psa.Objects.Adapters.EmergencyManagementFragmentAdapter;
import com.ufrj.nce.psa.Objects.Dialogs.Dialogs;
import com.ufrj.nce.psa.Objects.Dialogs.MessageBox;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergencyManagementFragment extends MyFragment {

    private View mRootView;
    private ListView mListView;
    private EmergencyAdapter mEmergencyAdapter;
    private Emergencies mEmergencies;
    private TextView mSubTitleTextView;
    private int mChosenItemIndex;


    @Override
    public boolean isShowFloatingButton() {
        return true;
    }


    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return null;
    }

    @Override
    public int getFloatingButtonColor() {
        return android.R.color.holo_red_light;
    }

    @Override
    public int getFloatingButtonIcon() {
        return R.mipmap.ic_local_hospital_black_24dp;
    }


    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_emergency_management, mContainer, false);

        mEmergencies = new Emergencies().loadData(mRootView.getContext());
        defineUIObjects();
        loadListView();

        return mRootView;

    }


    private void defineUIObjects(){

        mSubTitleTextView = (TextView) mRootView.findViewById(R.id.mTextViewEmergencyManagementFragmentTitle);
        mListView = (ListView) mRootView.findViewById(R.id.mListViewEmergencyManagementFragment);
    }


    private void loadListView(){

        mEmergencyAdapter = new EmergencyManagementFragmentAdapter(mRootView.getContext(),
                mEmergencies.getList(), new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                onClickEmergencyButton(mView);
            }
        });

        mListView.setAdapter(mEmergencyAdapter);

        refreshSubTitleTextView();
    }


    private void refreshSubTitleTextView(){

        if(mEmergencyAdapter.getList().size() == 0)
            mSubTitleTextView.setText(mRootView.getResources().getString(R.string.fragment_emergencies_subtitle_no_emergencies));
        else
            mSubTitleTextView.setText(mRootView.getResources().getString(R.string.fragment_emergency_management_title));
    }

    private void onClickEmergencyButton(final View mView){

        final Emergency mEmergency = mEmergencyAdapter.getItem(mListView.getPositionForView(mView));

        Dialogs.showChooseOptionItemFromList(getFragmentManager(),
                getActivity().getString(R.string.fragment_emergency_management_dialog_options_title),
                getActivity().getString(R.string.fragment_settings_dialog_label_button_cancel),
                R.array.fragment_emergency_management_array_item_options,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(which == 0)
                            MessageBox.showOkCancel(mRootView.getContext(),
                                    getActivity().getString(R.string.fragment_emergency_management_remove_item) + " " + mEmergency.getName()+ " ?",
                                    getActivity().getString(R.string.fragment_emergency_management_remove_item_title),
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    mEmergencyAdapter.removeItem(mListView.getPositionForView(mView));

                                                    mEmergencies.remove(mListView.getPositionForView(mView));
                                                    mEmergencies.saveData(mRootView.getContext());

                                                    mEmergencyAdapter.notifyDataSetChanged();
                                                    refreshSubTitleTextView();

                                                    showSnackBar(mRootView, mEmergency.getName()+" removido.", false);
                                                }
                                            });
                                        }
                                    });

                        if(which == 1)
                            showSnackBar(mRootView, "Em breve ...", false);


                    }
                },
                null
        );
    }

}
