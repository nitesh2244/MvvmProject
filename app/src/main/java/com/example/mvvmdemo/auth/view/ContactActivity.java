package com.example.mvvmdemo.auth.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.adapter.ContactAdapter;
import com.example.mvvmdemo.adapter.UserAdapter;
import com.example.mvvmdemo.auth.model.MContact;
import com.example.mvvmdemo.databinding.ActivityContactBinding;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    ActivityContactBinding binding;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contact);
        showContacts();
    }

    private void initRecyclerView() {
        ArrayList<MContact> arrayList = getContactNames();
        binding.rvContact.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvContact.setHasFixedSize(true);
        binding.rvContact.setItemAnimator(new DefaultItemAnimator());
        ContactAdapter contactAdapter = new ContactAdapter(this,arrayList);
        binding.rvContact.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }
    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
           initRecyclerView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private ArrayList<MContact> getContactNames() {
        ArrayList<MContact> mContacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
       // Cursor phoneCr = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        Cursor phoneCr = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID , null, null);
        if(phoneCr.getCount() >0){
            if (cursor.moveToFirst() && phoneCr.moveToNext()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String  phone = phoneCr.getString(phoneCr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int type = phoneCr.getInt(phoneCr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                    switch (type) {
                        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:

                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:

                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:

                            break;
                    }


                    mContacts.add(new MContact(name,phone));
                } while (cursor.moveToNext());

            }
        }
        phoneCr.close();
        cursor.close();

        return mContacts;
    }
}