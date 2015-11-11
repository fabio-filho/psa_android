package com.ufrj.nce.psa.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ufrj.nce.psa.Application.Data.Emergencies;
import com.ufrj.nce.psa.Objects.AgendaContact;
import com.ufrj.nce.psa.Objects.Contact;
import com.ufrj.nce.psa.Objects.Dialogs.MessageBox;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.Objects.Utilities;
import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class EmergencyAddFragment extends MyFragment{

    private View mRootView;
    final int PICK_CONTACT = 1;
    private Emergency mEmergency = new Emergency();
    private EditText mEmergencyNameEditText, mContactsEditText;
    private Button mSaveButton, mCancelButton, mRemoveAgendaContactButton;
    private Emergencies mEmergencies;


    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openContacts();
            }
        };
    }

    @Override
    public int getFloatingButtonColor() {
        return android.R.color.holo_blue_light;
    }

    @Override
    public boolean isShowFloatingButton() {
        return true;
    }

    @Override
    public int getFloatingButtonIcon() {
        return R.mipmap.ic_person_add_black_24dp;
    }



    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_emergency_add, mContainer, false);


        mEmergencies = new Emergencies().loadData(mRootView.getContext());
        Utilities.log(""+mEmergencies.getList().size());

        definingUIObjects();

        return mRootView;
    }


    private void definingUIObjects(){

        mEmergencyNameEditText  = (EditText) mRootView.findViewById(R.id.mEditTextEmergencyFragmentAddEmergencyName);
        mContactsEditText = (EditText) mRootView.findViewById(R.id.mEditTextEmergencyFragmentAddContacts);

        mSaveButton                = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddSave);
        mCancelButton              = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddCancel);
        mRemoveAgendaContactButton = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddRemoveContact);


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmergency();
            }
        });

        mCancelButton.setOnClickListener(getChangeFragmentOnClickListener());

        mRemoveAgendaContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmergency.getListContact().removeLastItem();
                mContactsEditText.setText(mEmergency.getListContact().getListInStringFormat());
            }
        });

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent mData) {
        super.onActivityResult(requestCode, resultCode, mData);

        if (requestCode == PICK_CONTACT && resultCode == getActivity().RESULT_OK)
            setContactOnUI(new AgendaContact(mRootView.getContext(), mData.getData()).getContact());

    }


    private void saveEmergency(){

        try{

            if (mEmergencyNameEditText.getText().length() == 0) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_name));
                    }
                });

                return;
            }

            if(mContactsEditText.getText().length() <= 3){

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_number_list));
                    }
                });
                return;
            }

            mEmergencies.add(mEmergency);
            mEmergencies.saveData(mRootView.getContext());

            showSnackBar(mRootView.findFocus(), "Emergência " + mEmergencyNameEditText.getText() + " Adicionada ", false);

            mCancelButton.performClick();

        }catch (Exception o){
            Utilities.log(o.toString());
        }
    }

    private void setContactOnUI(Contact mContact){

        if(!mEmergency.getListContact().add(mContact))
            showSnackBar(mRootView.findFocus(), getResources().getString(R.string.activity_emergency_view_message_item_already_exist), true);


        mContactsEditText.setText(mEmergency.getListContact().getListInStringFormat());
    }



    private void openContacts(){

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, PICK_CONTACT);

    }

}
