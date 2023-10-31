package com.trodev.bdeducationresult.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.trodev.bdeducationresult.BuildConfig;
import com.trodev.bdeducationresult.R;
import com.trodev.bdeducationresult.utils.AppConstant;
import com.trodev.bdeducationresult.view.admin.AdminDashboard;

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
                    openWebViewForResult(AppConstant.PSC_TITLE,AppConstant.PSC_RESULT);

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
                    openWebViewForResult(AppConstant.JSC_TITLE,AppConstant.JSC_RESULT);

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
                    openWebViewForResult(AppConstant.SSC_TITLE,AppConstant.SSC_RESULT);

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
                    openWebViewForResult(AppConstant.HSC_TITLE,AppConstant.HSC_RESULT);

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
                    openWebViewForResult(AppConstant.NU_TITLE,AppConstant.NU_RESULT);

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
                    openWebViewForResult(AppConstant.MBBS_TITLE,AppConstant.MBBS_RESULT);

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

    private void openWebViewForResult(String title, String resultSite) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(AppConstant.TITLE,title);
        intent.putExtra(AppConstant.WEBSITE,resultSite);
        startActivity(intent);
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

    @SuppressLint("NonConstantResourceId")
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
                startActivity(new Intent(MainActivity.this, AdminDashboard.class));
                break;

            case R.id.nav_share:
                try {
                  Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "BD Education Resul App");
                    String shareMessage = "\nBD Education Result App অ্যাপটি ডাউনলোড করুন\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    Log.e("ERROR",e.toString());
                }

                break;

            case R.id.nav_comment:
                Toast.makeText(this, "শীগ্রহী আসবে...", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_privacy:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://theholyquranislamicapp.trodev.com/")));

                break;

            case R.id.nav_rate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));

                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}