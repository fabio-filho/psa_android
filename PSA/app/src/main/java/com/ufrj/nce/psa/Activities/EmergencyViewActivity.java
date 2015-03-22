package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ufrj.nce.psa.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiofilho on 3/22/15.
 */
public class EmergencyViewActivity extends Activity {

    final int PICK_CONTACT = 1;

    private Menu menu;

    private EditText editTextName, editTextListContacts;
    private List<String> mListContacts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_view);

        if (Build.VERSION.SDK_INT >= 14)
            getActionBar().setHomeButtonEnabled(true);

        initializeComponents();
    }

    private void initializeComponents() {

        editTextName = (EditText) findViewById(R.id.txtNameEmergency);

        editTextListContacts = (EditText) findViewById(R.id.txtListNumbers);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.btn_add_contact).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.btn_add_contact) {
            openContacts();
        }

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.emergency_view, menu);

        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }


    public void onCancel(View view) {

        finish();
    }


    public void onSaveEmergency(View view) {

        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
        finish();
    }

    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        //Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_SHORT).show();
        String contactID = "";

        if (reqCode == PICK_CONTACT) {
            Uri result = data.getData();

            String contactNumber = null;

            // getting contacts ID
            Cursor cursorID = getContentResolver().query(result,
                    new String[]{ContactsContract.Contacts._ID},
                    null, null, null);

            if (cursorID.moveToFirst()) {

                contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
            }

            cursorID.close();

            Log.i("onActivityResult", "Contact ID: " + contactID);

            // Using the contact ID now we will get contact phone number
            Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                            ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                    new String[]{contactID},
                    null);

            if (cursorPhone.moveToFirst()) {
                contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i("onActivityResult", contactNumber);
            }

            cursorPhone.close();

            Log.d("onActivityResult", "Contact Phone Number: " + contactNumber);
            Toast.makeText(getApplicationContext(), " \nPhone: " + contactNumber , Toast.LENGTH_SHORT).show();

        }
    }





    private void openContacts(){

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, PICK_CONTACT);
    }


}
