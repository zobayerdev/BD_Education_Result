package com.trodev.bdeducationresult.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.trodev.bdeducationresult.R;
import com.trodev.bdeducationresult.utils.AppConstant;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "AndroidRide";
    private WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String title = getIntent().getStringExtra(AppConstant.TITLE);
        String websiteLink = getIntent().getStringExtra(AppConstant.WEBSITE);

        if(title.isEmpty()){
            Objects.requireNonNull(getSupportActionBar()).setTitle("BD Education Result");
        }else{
            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // ###########################################################
        // locate your Web ID...
        webview = (WebView) findViewById(R.id.webView);


        if(!websiteLink.isEmpty()){
            webview.loadUrl(websiteLink);
        }



        //############################################################
        // if you set this size in your website, Fixed it or don't use this
        webview.setInitialScale(90);

        //#############################################################
        // Website Zoom control
        // webview.getSettings().setBuiltInZoomControls(true);


        //#############################################################
        // extra Code of webview
        webview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setAppCacheEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);


        // ############################## Download Code is Here ####################################


        webview.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            //Checking runtime permission for devices above Marshmallow.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG, "Permission is granted");
                    downloadDialog(url, userAgent, contentDisposition, mimetype);
                } else {
                    Log.v(TAG, "Permission is revoked");
                    //requesting permissions.
                    ActivityCompat.requestPermissions(WebViewActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            } else {
                //Code for devices below API 23 or Marshmallow
                Log.v(TAG, "Permission is granted");
                downloadDialog(url, userAgent, contentDisposition, mimetype);
            }
        });
    }

    public void downloadDialog(final String url, final String userAgent, String contentDisposition, String mimetype) {
        //getting filename from url.
        final String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
        //alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
        //title of alertdialog
        builder.setTitle("Download File");
        //message of alertdialog
        builder.setMessage("Do you want to download " + filename);
        //if Yes button clicks.
        builder.setPositiveButton("Yes", (dialog, which) -> {
            //DownloadManager.Request created with url.
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            //cookie
            String cookie = CookieManager.getInstance().getCookie(url);
            //Add cookie and User-Agent to request
            request.addRequestHeader("Cookie", cookie);
            request.addRequestHeader("User-Agent", userAgent);
            //file scanned by MediaScannar
            request.allowScanningByMediaScanner();
            //Download is visible and its progress, after completion too.
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            //DownloadManager created
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            //Saving files in Download folder
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            //download enqued
            downloadManager.enqueue(request);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            //cancel the dialog if Cancel clicks
            dialog.cancel();
        });
        //alertdialog shows.
        builder.create().show();
    }


    // One-BackPress Method is here....
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
            webview.clearCache(true);
        } else {
            super.onBackPressed();
        }
    }
}