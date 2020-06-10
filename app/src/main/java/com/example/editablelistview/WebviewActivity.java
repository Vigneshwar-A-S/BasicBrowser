package com.example.editablelistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton searchpage_btn;
    private EditText SuperInput;
    private WebView searchWebAddress;
    ProgressBar progressBar;
    String url;
    String text;
    String Url_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        searchpage_btn = (ImageButton) findViewById(R.id.search_page);
        SuperInput =(EditText) findViewById (R.id.super_view);
        searchWebAddress = ( WebView ) findViewById(R.id.SearchWebsite);
        progressBar = ( ProgressBar ) findViewById(R.id.progressbar);


        url = getIntent().getExtras().get("url_address").toString();
        SuperInput.setText(url);



        WebSettings webSettings = searchWebAddress.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDisplayZoomControls(false);
        webSettings.supportZoom();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);

        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);


        searchWebAddress.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//Remove ScrollBars
        searchWebAddress.getSettings().setDefaultFontSize(12);//Set Font Size
        searchWebAddress.getSettings().setLoadsImagesAutomatically(true);//Enable Image Loading
        searchWebAddress.getSettings().setPluginState(WebSettings.PluginState.ON);//Enable Flash
        searchWebAddress.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH); //improves Feedback     on touch
        // searchWebAddress.setBackgroundColor(0x00000000);//Transparent Screen When Loading
        //mWebView.getSettings().setBuiltInZoomControls(true);//Set Zoom Controls
        //mWebView.getSettings().setDisplayZoomControls(false);//Requires Api 11
        searchWebAddress.getSettings().setAppCacheMaxSize(1024*1024*8);//Set Cache (8mb)
        String appCachePath =     getApplicationContext().getCacheDir().getAbsolutePath();//Set Cache (8mb)
        searchWebAddress.getSettings().setAppCachePath(appCachePath);//Set Cache (8mb)
        searchWebAddress.getSettings().setAllowFileAccess(true);//Set Cache (8mb)
        searchWebAddress.getSettings().setAppCacheEnabled(true);//Set Cache (8mb)
        searchWebAddress.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);//Set Cache (8mb)


        searchWebAddress.loadUrl("http://www.google.com.bd/search?q=" + Url_Address);
        text = Url_Address;
        SuperInput.setText(text);
        SuperInput.requestFocus();

        // searchWebAddress.loadUrl.toString();

        String url_without_https = Url_Address.replaceAll("https://www.", "");
        String https = "https://";
        String www = "www.";

        // searchWebAddress.clearCache(true);
        //  searchWebAddress.clearHistory();



        searchWebAddress.loadUrl(url);
        searchWebAddress.setWebViewClient(new WebViewClient() {
                                              @Override
                                              public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                                  progressBar.setVisibility(View.VISIBLE);


                                                  final String Urls = url;

                                                  if (Urls.contains("mailto:") || Urls.contains("sms:") || Urls.contains("tel:")) {
                                                      searchWebAddress.stopLoading();
                                                      Intent i = new Intent();
                                                      i.setAction(Intent.ACTION_VIEW);
                                                      i.setData(Uri.parse(Urls));
                                                      startActivity(i);
                                                  }

                                                  super.onPageStarted(view, url, favicon);
                                              }

                                              @Override
                                              public void onPageFinished(WebView view, String url) {
                                                  progressBar.setVisibility(View.GONE);
                                                  super.onPageFinished(view, url);
                                              }
                                          }


        );








        searchpage_btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        if(v == searchpage_btn)
        {
            Intent searchpage = new Intent(WebviewActivity.this, MainActivity.class);
            startActivity(searchpage);
        }

    }


    @Override
    public void onBackPressed() {
        {
            if (searchWebAddress.canGoBack())
            {
                searchWebAddress.canGoBack();
            }
            else
            {
                super.onBackPressed();
            }
        }
    }



}
