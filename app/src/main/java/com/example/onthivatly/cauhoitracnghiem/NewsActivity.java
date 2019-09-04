package com.example.onthivatly.cauhoitracnghiem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.onthivatly.MainActivity;
import com.example.onthivatly.R;

public class NewsActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTintuc");
      //  Toast.makeText(this,link,Toast.LENGTH_LONG).show();

        webView= (WebView)findViewById(R.id.webviewTinTuc);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
    }
}
