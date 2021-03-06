package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ufrj.nce.psa.Broadcasts.SMSReceiver;
import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Fragments.EmergencyFragment;
import com.ufrj.nce.psa.Fragments.EmergencyHistoryFragment;
import com.ufrj.nce.psa.Fragments.EmergencyManagerFragment;
import com.ufrj.nce.psa.Fragments.HomeFragment;
import com.ufrj.nce.psa.Fragments.InformationFragment;
import com.ufrj.nce.psa.Fragments.SettingsFragment;
import com.ufrj.nce.psa.Objects.Adapters.NavDrawerListAdapter;
import com.ufrj.nce.psa.Objects.NavDrawerItem;
import com.ufrj.nce.psa.R;

import java.util.ArrayList;


public class PSAMainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
    //Items action bar
    private Menu menu;
    //Fragment atributte
    private EmergencyFragment fragment;
    private static int FRAGMENT_CURRENT = 0 ;

    public static final int REQUEST_ACTIVITY_CODE = 155;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Initialize Side Menu
        initializeSideMenu();

		// enabling action bar app icon and behaving it as toggle button
        settingChangesOnActionsBar(savedInstanceState);
    }


    private void initializeSideMenu(){

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<>();

        // adding nav drawer items to array
        addingItemsOnNavDrawer();

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
    }


    private void settingChangesOnActionsBar(Bundle savedInstanceState){

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if(Build.VERSION.SDK_INT >= 14)
            getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(FRAGMENT_CURRENT);
        }
    }


    private String getCountEmergency(){

        SQLiteDatabase db = new EmergencyTable(getApplicationContext()).getWritableDatabase();
        return SQLite.getOneStringQuery(db, "select COUNT(*) from " + EmergencyTable.TABLE_NAME);
    }

    private void addingItemsOnNavDrawer(){

        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1), true, getCountEmergency()));
        // Emergency Manager
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // History
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "0"));
        // Settings
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Informatiom
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
    }


    /**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

        if(item.getItemId() == R.id.action_add_emergency) {
            openAdderEmergency();
            return true;
        }

		return super.onOptionsItemSelected(item);

	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_add_emergency).setVisible(false);

        if(!drawerOpen)
           if(FRAGMENT_CURRENT == 0 || FRAGMENT_CURRENT == 1)
               menu.findItem(R.id.action_add_emergency).setVisible(true);

		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Displaying fragment view for selected nav drawer list item
	 * */
	private void displayView(final int position) {

        new Thread() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //set current fragment value
                        FRAGMENT_CURRENT=position;
                        // update the main content by replacing fragments
                        fragment=null;
                        switch(position)

                        {
                            case 0:
                                fragment = new HomeFragment();
                                break;
                            case 1:
                                fragment = new EmergencyManagerFragment();
                                break;
                            case 2:
                                fragment = new EmergencyHistoryFragment();
                                break;
                            case 3:
                                fragment = new SettingsFragment();
                                break;
                            case 4:
                                fragment = new InformationFragment();
                                break;

                            default:
                                break;
                        }

                        if(fragment!=null)

                        {
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.frame_container, fragment).commit();

                            // update selected item and title, then close the drawer
                            mDrawerList.setItemChecked(position, true);
                            mDrawerList.setSelection(position);
                            setTitle(navMenuTitles[position]);
                            mDrawerLayout.closeDrawer(mDrawerList);

                        }

                        else

                        {
                            // error in creating fragment
                            Log.e("displayView", "Error in creating fragment");
                        }
                    }
                });
            }


        }.start();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


    private void openAdderEmergency(){

        startActivityForResult(new Intent("android.intent.action.EMERGENCY_VIEW"), REQUEST_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ACTIVITY_CODE) {
            fragment.refreshListViewEmergency();
            navDrawerItems.get(0).setCount(getCountEmergency());
        }

    }


    /*
    public void onClickListView(View view){

        if(fragment != null)
            fragment.onClickListViewEmergency(view);
    }
*/


    @Override
    protected void onResume() {
        super.onResume();

        if (SMSReceiver.threadAlertEmergency != null)
            SMSReceiver.threadAlertEmergency.stopProcess();
    }
}
