package com.ggf.qcpp.h_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ggf.qcpp.R;

public class h_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_dashboard);

//        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        WebView webView = findViewById(R.id.webviewdashboard);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        webView.loadUrl("https://osiqcpp.ggfsystem.com/");
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        // Tambahkan kode lain yang Anda inginkan sebelum menutup aktivitas (jika perlu).
        finish(); // Menutup aktivitas saat tombol "Back" ditekan.
    }

}