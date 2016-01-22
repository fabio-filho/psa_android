package com.ufrj.nce.psa.Fragments;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by filhofilha on 11/11/15.
 */
public abstract class MyFragment extends Fragment{

    public abstract boolean isShowFloatingButton();

    public abstract View.OnClickListener getFloatingButtonOnClickListener();

    public abstract int getFloatingButtonColor();

    public abstract int getFloatingButtonIcon();

    private View.OnClickListener mChangeFragmentOnClickListener;

    public View.OnClickListener getChangeFragmentOnClickListener() {
        return mChangeFragmentOnClickListener;
    }

    public void setChangeFragmentOnClickListener(View.OnClickListener mChangeFragmentOnClickListener) {
        this.mChangeFragmentOnClickListener = mChangeFragmentOnClickListener;
    }

    protected void showSnackBar(View mView, String mContent, boolean mIsLongDuration){

        if(mIsLongDuration)
            Snackbar.make(mView, mContent, Snackbar.LENGTH_LONG).show();
        else
            Snackbar.make(mView, mContent, Snackbar.LENGTH_SHORT).show();
    }


    protected void showSnackBar(View mView, String mContent){

        Snackbar.make(mView, mContent, Snackbar.LENGTH_LONG).show();
    }

}

