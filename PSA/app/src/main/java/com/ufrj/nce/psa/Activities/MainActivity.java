package com.ufrj.nce.psa.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ufrj.nce.psa.Fragments.EmergenciesFragment;
import com.ufrj.nce.psa.Fragments.EmergencyAddFragment;
import com.ufrj.nce.psa.Fragments.EmergencyEditFragment;
import com.ufrj.nce.psa.Fragments.EmergencyManagementFragment;
import com.ufrj.nce.psa.Fragments.HistoryFragment;
import com.ufrj.nce.psa.Fragments.InformationFragment;
import com.ufrj.nce.psa.Fragments.SettingsFragment;
import com.ufrj.nce.psa.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FloatingActionButton mFloatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFloatingButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(EmergencyAddFragment.SHOW_FLOATING_BUTTON)
                    mFloatingButton.setVisibility(View.VISIBLE);
                else
                    mFloatingButton.setVisibility(View.INVISIBLE);

                Fragment mFragment = new EmergencyAddFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.mFrameContainerMainActivity, mFragment).commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mDrawerLayoutMainActivity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

        Fragment mFragment = null;


        if (mId == R.id.mNavEmergencyFragment) {
            mFragment = new EmergenciesFragment();

            if(EmergenciesFragment.SHOW_FLOATING_BUTTON)
                mFloatingButton.setVisibility(View.VISIBLE);
            else
                mFloatingButton.setVisibility(View.INVISIBLE);

        } else if (mId == R.id.mNavHistoryFragment) {
            mFragment = new HistoryFragment();

            if(EmergenciesFragment.SHOW_FLOATING_BUTTON)
                mFloatingButton.setVisibility(View.VISIBLE);
            else
                mFloatingButton.setVisibility(View.INVISIBLE);

        } else if (mId == R.id.mNavInformationFragment) {
            mFragment = new InformationFragment();

            if(EmergenciesFragment.SHOW_FLOATING_BUTTON)
                mFloatingButton.setVisibility(View.VISIBLE);
            else
                mFloatingButton.setVisibility(View.INVISIBLE);

        } else if (mId == R.id.mNavEmergencyManagementFragment) {
            mFragment = new EmergencyManagementFragment();

            if(EmergenciesFragment.SHOW_FLOATING_BUTTON)
                mFloatingButton.setVisibility(View.VISIBLE);
            else
                mFloatingButton.setVisibility(View.INVISIBLE);

        } else if (mId == R.id.mNavSettingsFragment) {
            mFragment = new SettingsFragment();

            if(EmergenciesFragment.SHOW_FLOATING_BUTTON)
                mFloatingButton.setVisibility(View.VISIBLE);
            else
                mFloatingButton.setVisibility(View.INVISIBLE);
        }

        if(mFragment!=null)
            if (isValidFragment(mFragment.getClass().getName())) {
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.mFrameContainerMainActivity, mFragment).commit();
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mDrawerLayoutMainActivity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

}
