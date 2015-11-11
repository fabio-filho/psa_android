package com.ufrj.nce.psa.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ufrj.nce.psa.Application.Data.Settings;
import com.ufrj.nce.psa.Fragments.EmergenciesFragment;
import com.ufrj.nce.psa.Fragments.EmergencyAddFragment;
import com.ufrj.nce.psa.Fragments.EmergencyEditFragment;
import com.ufrj.nce.psa.Fragments.EmergencyManagementFragment;
import com.ufrj.nce.psa.Fragments.HistoryFragment;
import com.ufrj.nce.psa.Fragments.InformationFragment;
import com.ufrj.nce.psa.Fragments.MyFragment;
import com.ufrj.nce.psa.Fragments.SettingsFragment;
import com.ufrj.nce.psa.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FloatingActionButton mFloatingButton;
    private TextView mNavBarNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFloatingButton = (FloatingActionButton) findViewById(R.id.mFabMainActivity);

        DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.mDrawerLayoutMainActivity);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(mToggle);
        mToggle.syncState();


        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        /* Layout inflater from nav_header_main.xml */
        View mHeader = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        /* Adding manually the nav_header_main into Navigation View */
        mNavigationView.addHeaderView(mHeader);

        mNavBarNameTextView = (TextView) mHeader.findViewById(R.id.mTextViewNavBarMainActivityName);

        mNavBarNameTextView.setText( new Settings().loadData(getApplicationContext()).getName());

        /* Set Default Fragment into Activity */
        setFragmentIntoActivity(new EmergenciesFragment());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mDrawerLayoutMainActivity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem mItem) {
        // Handle navigation view item clicks here.
        int mId = mItem.getItemId();

        MyFragment mFragment = null;

        if (mId == R.id.mNavEmergencyFragment) {
            mFragment = new EmergenciesFragment();

        } else if (mId == R.id.mNavHistoryFragment) {
            mFragment = new HistoryFragment();

        } else if (mId == R.id.mNavInformationFragment) {
            mFragment = new InformationFragment();

        } else if (mId == R.id.mNavEmergencyManagementFragment) {
            mFragment = new EmergencyManagementFragment();

        } else if (mId == R.id.mNavSettingsFragment) {
            mFragment = new SettingsFragment();
        }

        if(mFragment!=null)
            if (isValidFragment(mFragment.getClass().getName()))
                setFragmentIntoActivity(mFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mDrawerLayoutMainActivity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void addOnClickListenersToFragments(MyFragment mFragment){

        if(mFragment instanceof EmergencyAddFragment) {
            mFragment.setChangeFragmentOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFragmentIntoActivity(new EmergenciesFragment());
                }
            });
        }


        if(mFragment instanceof SettingsFragment)
            ((SettingsFragment) mFragment).setChangeNameOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNavBarNameTextView.setText( new Settings().loadData(getApplicationContext()).getName() );
                }
            });

    }

    private void setFragmentIntoActivity(MyFragment mFragment){

        addOnClickListenersToFragments(mFragment);

        if(mFragment.isShowFloatingButton())
            mFloatingButton.setVisibility(View.VISIBLE);
        else
            mFloatingButton.setVisibility(View.INVISIBLE);

        if(mFragment.getFloatingButtonIcon() == 0)
            mFloatingButton.setImageResource(android.R.drawable.ic_input_add);
        else
            mFloatingButton.setImageResource(mFragment.getFloatingButtonIcon());

        if(mFragment.getFloatingButtonColor() == 0)
            mFloatingButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.holo_red_light));
        else
            mFloatingButton.setBackgroundTintList(getResources().getColorStateList(mFragment.getFloatingButtonColor()));

        if(mFragment.getFloatingButtonOnClickListener()!=null)
            mFloatingButton.setOnClickListener(mFragment.getFloatingButtonOnClickListener());
        else
            mFloatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFragmentIntoActivity(new EmergencyAddFragment());
                }
            });

        FragmentManager mFragmentManager = getFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.mFrameContainerMainActivity, mFragment).commit();
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String mFragmentName) {

        return EmergenciesFragment.class.getName().equals(mFragmentName)
                || EmergencyAddFragment.class.getName().equals(mFragmentName)
                || EmergencyEditFragment.class.getName().equals(mFragmentName)
                || EmergencyManagementFragment.class.getName().equals(mFragmentName)
                || HistoryFragment.class.getName().equals(mFragmentName)
                || SettingsFragment.class.getName().equals(mFragmentName)
                || InformationFragment.class.getName().equals(mFragmentName);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
