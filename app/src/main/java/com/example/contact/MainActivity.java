package com.example.contact;
import com.example.contact.Dialog;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RadioButton nom, num;
    EditText editnumero, editname;
    private CountryCodePicker ccp;
    private Button button;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editnumero = findViewById(R.id.editnum);
        editname = findViewById(R.id.editname);
        editnumero.setVisibility(View.INVISIBLE);
        editname.setVisibility(View.INVISIBLE);
        nom = findViewById(R.id.nom);
        num = findViewById(R.id.Numero);
        ccp = findViewById(R.id.ccp);
        button = findViewById(R.id.ajt_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nom.isChecked()){
                    Contact contact = findByName(editname.getText()+"");
                    Dialog dialog = new Dialog(contact.getNom(), contact.getPhone());
                    dialog.show(getSupportFragmentManager(),"");
                }else if (num.isChecked()){
                    Contact contact = findByPhone(editnumero.getText()+"");
                    Dialog dialog = new Dialog(contact.getNom(), contact.getPhone());
                    dialog.show(getSupportFragmentManager(),"");
                }

            }
        });

        //Contact contact = findByPhone("+212613342690");
        //Toast.makeText(this, contact.getNom()+"", Toast.LENGTH_SHORT).show();




        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (nom.isChecked()){
                    editnumero.setVisibility(View.INVISIBLE);
                    editname.setVisibility(View.VISIBLE);
                    ccp.setVisibility(View.INVISIBLE);

                }
                if (num.isChecked()){
                    editnumero.setVisibility(View.VISIBLE);
                    ccp.setVisibility(View.VISIBLE);
                    editname.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Localisation
        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           // Permissions.Request_FINE_LOCATION(this, 20);

            //return;
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, new LocationListener() {
          //  @Override
            //public void onLocationChanged(@NonNull Location location) {
                //Toast.makeText(MainActivity.this, location.getLatitude()+" : "+location.getLongitude(), Toast.LENGTH_SHORT).show();
            //}
        //});
    //}

    @Override
    public void onClick(View v) {


    }

    public List<Contact> loadContact (ContentResolver cr){
        List<Contact> contacts = new ArrayList<>();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            @SuppressLint("Range") String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add (new Contact(name, phoneNumber));
        }
        phones.close();
        return contacts;
    }

    public Contact findByName(String name){
        List<Contact> contacts = loadContact(getApplicationContext().getContentResolver());

        for(Contact contact:contacts){
            if (contact.getNom().contains(name)) return contact;
        }

        return null;
    }

    public Contact findByPhone(String phone){
        List<Contact> contacts = loadContact(getApplicationContext().getContentResolver());

        for(Contact contact:contacts){
            if (contact.getPhone().contains(phone)) return contact;
        }

        return null;
    }


}