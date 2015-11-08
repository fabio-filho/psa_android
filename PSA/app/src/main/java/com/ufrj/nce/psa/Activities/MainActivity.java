package com.ufrj.nce.psa.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.ufrj.nce.psa.Fragments.HistoryFragment;
import com.ufrj.nce.psa.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment mFragment = new EmergencyAddFragment();
                FragmentManager mFragmentManager = getFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.frameContainerMainActivity, mFragment).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment mFragment = null;


        if (id == R.id.nav_camara) {
            Snackbar.make(getCurrentFocus(), "Emergencies", Snackbar.LENGTH_SHORT).show();

            mFragment = new EmergenciesFragment();

        } else if (id == R.id.nav_gallery) {
            Snackbar.make(getCurrentFocus(), "History", Snackbar.LENGTH_SHORT).show();

            mFragment = new HistoryFragment();

        } else if (id == R.id.nav_slideshow) {
            Snackbar.make(getCurrentFocus(), "SlideShow", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Snackbar.make(getCurrentFocus(), "Manage", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Snackbar.make(getCurrentFocus(), "Share", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Snackbar.make(getCurrentFocus(), "Send", Snackbar.LENGTH_SHORT).show();
        }

        if(mFragment!=null){
            FragmentManager mFragmentManager = getFragmentManager();
            mFragmentManager.beginTransaction().replace(R.id.frameContainerMainActivity, mFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
