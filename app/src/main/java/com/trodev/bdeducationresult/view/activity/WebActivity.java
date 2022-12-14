package com.trodev.bdeducationresult.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.trodev.bdeducationresult.R;

public class WebActivity extends AppCompatActivity {

    private WebView web;
    String webUrl = "https://docs.google.com/forms/d/e/1FAIpQLScx5P8esolvY1R_wAk-Qd03831PT3d_7yiGqA1zjXzYC1dzng/viewform?usp=sf_link";
    private ProgressDialog pd;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // back activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("মতামত লিখুন");

        web = findViewById(R.id.webComment);
        web.loadUrl(webUrl);

        pd = new ProgressDialog(this);
        pd.setMessage("অনুগ্রহ করে অপেক্ষা করুন");

        WebSettings mywebsetting = web.getSettings();
        mywebsetting.setJavaScriptEnabled(true);

        mywebsetting.setPluginState(WebSettings.PluginState.ON);
        web.getSettings().setLoadWithOverviewMode(true);

        //এইখানে আমাদের ওয়েবসাইট গুলো সাইজ দেওয়া হয়েছে।
       // web.setInitialScale(100);
        // Zoom Website
        web.getSettings().setBuiltInZoomControls(true);
        web.setWebViewClient(new WebViewClient());

        web.setWebViewClient(new WebActivity.pdweb(pd));
        pd.setCanceledOnTouchOutside(false);

        //
/*        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.getSettings().setAppCacheEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mywebsetting.setDomStorageEnabled(true);
        mywebsetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mywebsetting.setUseWideViewPort(true);
        mywebsetting.setSavePassword(true);
        mywebsetting.setSaveFormData(true);
        mywebsetting.setEnableSmoothTransition(true);*/



        web.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

                DownloadManager.Request request = new DownloadManager.Request ( Uri.parse( url ) );

                request.setMimeType( mimeType );
                String cookies = CookieManager.getInstance ().getCookie( url );
                request.addRequestHeader("cookic", cookies );

                request.addRequestHeader("User-Agent",userAgent );
                request.setDescription( "Download File..." );

                request.setTitle(URLUtil.guessFileName ( url, contentDisposition, mimeType ) );
                request.allowScanningByMediaScanner ();
                request.setNotificationVisibility ( DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED );
                request.setDestinationInExternalPublicDir ( Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName( url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService( DOWNLOAD_SERVICE );
                dm.enqueue( request );
                Toast.makeText ( WebActivity.this, "Downloading File", Toast.LENGTH_SHORT ).show();
            }
        });


    }

    public class pdweb extends WebViewClient{
        ProgressDialog pd;
        public pdweb (ProgressDialog pd){
            this.pd = pd;
            pd.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            view.loadUrl(webUrl);
            return super.shouldOverrideUrlLoading(view, webUrl);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(pd.isShowing()){
                pd.dismiss();
            }
        }


    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack())
        {
            web.goBack();
        }
        else
        {
            super.onBackPressed();
        }

    }
}