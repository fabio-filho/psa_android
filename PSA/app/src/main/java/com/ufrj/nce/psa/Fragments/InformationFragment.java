package com.ufrj.nce.psa.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufrj.nce.psa.R;

/**
 * Created by filhofilha on 11/8/15.
 */
public class InformationFragment extends MyFragment {

    private View mRootView;

    private TextView mLeaderTextView, mDeploymentTextView;


    @Override
    public boolean isShowFloatingButton() {
        return false;
    }

    @Override
    public View.OnClickListener getFloatingButtonOnClickListener() {
        return null;
    }

    @Override
    public int getFloatingButtonColor() {
        return 0;
    }
    @Override
    public int getFloatingButtonIcon() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup mContainer, Bundle mSavedInstanceState) {

        mRootView = mInflater.inflate(R.layout.fragment_information, mContainer, false);

        defineUIObjects();

        return mRootView;
    }



    private void defineUIObjects(){

        mLeaderTextView = (TextView) mRootView.findViewById(R.id.mTextViewInformationFragmentLeader);
        mDeploymentTextView = (TextView) mRootView.findViewById(R.id.mTextViewInformationFragmentDeployment);

        mLeaderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(new String[]{"mborges@nce.ufrj.br"});
            }
        });


        mDeploymentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(new String []{"fabiofilho@dcc.ufrj.br"});
            }
        });
    }



    private void openEmailIntent(String mEmail){

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mEmail));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "PSA - Personal Safety Assistant");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

        startActivity(Intent.createChooser(emailIntent, "Enviando e-mail ..."));
    }


    public void composeEmail(String[] addresses) {

        String subject = "PSA - Personal Safety Assistant";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
