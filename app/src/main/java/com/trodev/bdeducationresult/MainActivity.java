package com.trodev.bdeducationresult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private CardView psc, ssc, jsc, hsc, nu, mbbs, cgpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // material cardView ID
        psc = findViewById(R.id.psc);
        ssc = findViewById(R.id.ssc);
        jsc = findViewById(R.id.jsc);
        ssc = findViewById(R.id.ssc);
        hsc = findViewById(R.id.hsc);
        nu = findViewById(R.id.nu);
        mbbs = findViewById(R.id.mbbs);
        cgpa = findViewById(R.id.cgpa);

        psc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, PscActivity.class));
                    Toast.makeText(MainActivity.this, "PSC এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }
            }
        });

        jsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, JscActivity.class));
                    Toast.makeText(MainActivity.this, "JSC এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }
            }
        });

        ssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, SscActivity.class));
                    Toast.makeText(MainActivity.this, "SSC এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }

            }
        });

        hsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, HscActivity.class));
                    Toast.makeText(MainActivity.this, "HSC এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }

            }
        });

        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, NuActivity.class));
                    Toast.makeText(MainActivity.this, "NU এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }
            }
        });

        mbbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    startActivity(new Intent(MainActivity.this, MbbsActivity.class));
                    Toast.makeText(MainActivity.this, "MBBS এর ফলাফল দেখুন", Toast.LENGTH_SHORT).show();
                } else {
                    showPopupMessage();
                }


            }
        });


        cgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, CgpaActivity.class));
                    Toast.makeText(MainActivity.this, "CGPA দেখুন", Toast.LENGTH_SHORT).show();
            }
        });


        // Navigation Drawer Coding
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // eikhane eituku hocche amader navigation layout er kaj korar jonno.
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        //External Storage permission of saving file
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "denied to Write_EXTERNAL_STORAGE - requesting it");
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 1);
            }
        }
    }

    private void showPopupMessage() {
        new AlertDialog.Builder(this)
                .setTitle("No Internet..")
                .setMessage("Please check your internet connection...!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", null)
                .setIcon(R.drawable.ic_nonet)
                .show();
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
/*           case R.id.nav_psc:
               Toast.makeText(this, "নোটিশ দেখুন", Toast.LENGTH_SHORT).show();
              break;


            case R.id.nav_jsc:
                // startActivity(new Intent(MainActivity.this, AdminNotification.class));
                Toast.makeText(this, "নোটিশ দেখুন", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_ssc:
                //  startActivity(new Intent(MainActivity.this, RequestDeveloperActivity.class));
                Toast.makeText(this, "নোটিশ দেখুন", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_hsc:
                Toast.makeText(this, "নোটিশ দেখুন", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_nu_notice:
                Toast.makeText(this, "নোটিশ দেখুন", Toast.LENGTH_SHORT).show();
                break;*/


            case R.id.nav_admin:
                Toast.makeText(this, "স্বাগতম, এডমিন এর সাথে যোগাযোগ করুন", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AdminDashboard.class));
                break;

            case R.id.nav_share:
                try {
/*                  Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "The Holy Quran -Islamic App");
                    String shareMessage = "\nThe Holy Quran -Islamic App অ্যাপটি ডাউনলোড করুন\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                    Toast.makeText(this, "শেয়ার করুন!", Toast.LENGTH_SHORT).show();*/
                } catch (Exception e) {
                    //e.toString();
                }

                break;

            case R.id.nav_comment:
                Toast.makeText(this, "শীগ্রহী আসবে...", Toast.LENGTH_SHORT).show();
              //  startActivity(new Intent(MainActivity.this,WebActivity.class));
                break;

            case R.id.nav_privacy:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://theholyquranislamicapp.trodev.com/")));
                Toast.makeText(this, "প্রাইভেসি পলিসি!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                    Toast.makeText(this, "রেটিং দিন!", Toast.LENGTH_SHORT).show();
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}