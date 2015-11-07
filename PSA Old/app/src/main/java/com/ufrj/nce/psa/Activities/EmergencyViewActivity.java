package com.ufrj.nce.psa.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ufrj.nce.psa.Connections.SQLite;
import com.ufrj.nce.psa.Connections.Tables.ContactTable;
import com.ufrj.nce.psa.Connections.Tables.EmergencyTable;
import com.ufrj.nce.psa.Objects.Contact;
import com.ufrj.nce.psa.Objects.Emergency;
import com.ufrj.nce.psa.R;
import com.ufrj.nce.psa.Utilities.Functions;
import com.ufrj.nce.psa.Utilities.MessageBox;
import com.ufrj.nce.psa.Utilities.Strings;

/**
 * Created by fabiofilho on 3/22/15.
 */
public class EmergencyViewActivity extends Activity {

    final int PICK_CONTACT = 1;

    private Menu menu;

    private EditText editTextName, editTextListContacts;

    private final Context thisContext = this;

    private Emergency mEmergency = new Emergency();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_add_edit);

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

        //Check if can save.

        if (editTextName.getText().length() == 0) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MessageBox.showOk(thisContext, getResources().getString(R.string.activity_emergency_view_message_empty_name));
                }
            });

            return;
        }

        if(editTextListContacts.getText().length() <= 3){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MessageBox.showOk(thisContext, getResources().getString(R.string.activity_emergency_view_message_empty_number_list));
                }
            });
            return;
        }

        if(insertEmergencyOnDB()) {
            Toast.makeText(getApplicationContext(), "Emergência " + editTextName.getText() + " Adicionada ", Toast.LENGTH_SHORT).show();
            finish();
        }else
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.activity_emergency_view_message_error_save), Toast.LENGTH_SHORT).show();

    }


    public void onDeleteContacts(View view){

        mEmergency.getListContact().removeLastContact();
        editTextListContacts.setText(mEmergency.getListContact().getListContactsShow());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT && resultCode == RESULT_OK)
            setContactInfoOnActivityLayout( new Contact(getApplicationContext(), data.getData()));

    }

    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================



    private void setContactInfoOnActivityLayout(Contact contact){

        if(!mEmergency.getListContact().addContact(contact))
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.activity_emergency_view_message_item_already_exist), Toast.LENGTH_LONG).show();

        editTextListContacts.setText(mEmergency.getListContact().getListContactsShow());
    }


    private Boolean insertEmergencyOnDB(){

        try{
            mEmergency.setName(Strings.setFirtLetterUppercase(editTextName.getText().toString()));

            SQLiteDatabase db = new EmergencyTable(getApplicationContext()).getWritableDatabase();
            SQLite.insertEmergency(db, mEmergency);

            db = new EmergencyTable(getApplicationContext()).getWritableDatabase();
            mEmergency.setCode(SQLite.getOneStringQuery(db, "select MAX(code) from "+EmergencyTable.TABLE_NAME));

            db = new ContactTable(getApplicationContext()).getWritableDatabase();
            SQLite.insertContact(db, mEmergency);

            return true;

        }catch (Exception o){
            Functions.Log("insertEmergencyOnDB", o.toString());
        }

        return false;
    }

    private void openContacts(){

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, PICK_CONTACT);

    }


    @Override
    public void finish() {

        setResult(Activity.RESULT_OK, new Intent());

        super.finish();
    }
}
