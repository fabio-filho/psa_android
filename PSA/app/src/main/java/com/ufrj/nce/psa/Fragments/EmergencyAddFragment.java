package com.ufrj.nce.psa.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

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
    final int PICK_CONTACT = 1, PICK_DOCTOR_CONTACT = 2;
    private Emergency mEmergency = new Emergency();
    private EditText mEmergencyNameEditText, mEmergencyMessageEditText, mDoctorMessageEditText;
    private TextView mContactsEditText, mDoctorNumberTextView;
    private Button mSaveButton, mCancelButton, mRemoveAgendaContactButton;
    private Emergencies mEmergencies;

    private Switch mDoctorPartSwitch;
    private LinearLayout mDoctorPartLinearLayout;
    private ImageView mAddDoctorNumberImageView;


    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts(PICK_CONTACT);
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

        defineUIObjects();

        return mRootView;
    }


    private void defineUIObjects(){

        mDoctorNumberTextView = (TextView) mRootView.findViewById(R.id.mtTextViewEmergencyFragmentAddEmergencyDoctorNumber);
        mAddDoctorNumberImageView = (ImageView) mRootView.findViewById(R.id.mImageViewEmergencyFragmentAddEmergencyAddDoctorNumber);
        mDoctorPartSwitch = (Switch) mRootView.findViewById(R.id.mSwitchEmergencyFragmentAddEmergencyDoctorPart);
        mDoctorPartLinearLayout = (LinearLayout) mRootView.findViewById(R.id.mLinearLayoutEmergencyFragmentAddEmergencyDoctor);
        mDoctorMessageEditText = (EditText) mRootView.findViewById(R.id.mEditTextEmergencyFragmentAddEmergencyDoctorMessage);

        mEmergencyMessageEditText = (EditText) mRootView.findViewById(R.id.mEditTextEmergencyFragmentAddEmergencyMessage);
        mEmergencyNameEditText  = (EditText) mRootView.findViewById(R.id.mEditTextEmergencyFragmentAddEmergencyName);
        mContactsEditText = (TextView) mRootView.findViewById(R.id.mTextViewEmergencyFragmentAddContacts);

        mSaveButton                = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddSave);
        mCancelButton              = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddCancel);
        mRemoveAgendaContactButton = (Button) mRootView.findViewById(R.id.mButtonEmergencyFragmentAddRemoveContact);


        mAddDoctorNumberImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts(PICK_DOCTOR_CONTACT);
            }
        });

        mDoctorPartSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mEmergency.setDoctorPartActioned(isChecked);
                        ViewGroup.LayoutParams mLayoutParams = mDoctorPartLinearLayout.getLayoutParams();

                        if(isChecked)
                            mLayoutParams.height = ViewGroup.LayoutParams.FILL_PARENT;
                        else
                            mLayoutParams.height = 0;

                        mDoctorPartLinearLayout.setLayoutParams(mLayoutParams);
                    }
                });


            }
        });

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

        if (requestCode == PICK_DOCTOR_CONTACT && resultCode == getActivity().RESULT_OK)
            setDoctorContactOnUI(new AgendaContact(mRootView.getContext(), mData.getData()).getContact());

    }


    private boolean canSaveEmergency(){

        try{

            if (!Emergency.isValidMessage(mEmergencyNameEditText.getText().toString())) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_name));
                    }
                });

                return false;
            }

            if(!Emergency.isValidMessage(mEmergencyMessageEditText.getText().toString())){

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_message));
                    }
                });
                return false;
            }


            if(!Emergency.isValidMessage(mContactsEditText.getText().toString())){

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_number_list));
                    }
                });
                return false;
            }



            /* DOCTOR PART */
            if(mEmergency.isDoctorPartActioned()){

                if(!Emergency.isValidMessage(mDoctorMessageEditText.getText().toString())){

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_doctor_message));
                        }
                    });
                    return false;
                }


                if(!Emergency.isValidMessage(mDoctorNumberTextView.getText().toString())){

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MessageBox.showOk(mRootView.getContext(), getResources().getString(R.string.activity_emergency_view_message_empty_doctor_number));
                        }
                    });
                    return false;
                }
            }

        }catch (Exception o){
            Utilities.log(o.toString());
        }

        return true;
    }

    private void saveEmergency(){

        try{
            if(!canSaveEmergency()) return;

            mEmergency.setName(mEmergencyNameEditText.getText().toString());
            mEmergency.setMessage(mEmergencyMessageEditText.getText().toString());

            mEmergency.setDoctorMessage(mDoctorMessageEditText.getText().toString());

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

    private void setDoctorContactOnUI(Contact mContact){

        mEmergency.setDoctorContact(mContact);
        mDoctorNumberTextView.setText(mEmergency.getDoctorContact().getName());
    }



    private void openContacts(int mResultTag){

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, mResultTag);

    }

}
