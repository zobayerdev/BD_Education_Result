package com.trodev.bdeducationresult.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;

import com.trodev.bdeducationresult.R;

import java.util.Objects;


public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Admin Contact");

        Button callBTN = findViewById(R.id.call_btn);

        callBTN.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + "+8801615257555"));
            startActivity(dialIntent);
        });



    }
}