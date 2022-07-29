package com.wv.android

//import androidx.core.content.ContextCompat

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        val PERMISSION_ALL = 1
        val PERMISSIONS = arrayOf<String>(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS,
            Manifest.permission.CAMERA
        )

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
        }
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,R.color.splash)))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        webView.settings.setJavaScriptEnabled(true)
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        getSupportActionBar()!!.hide();

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                return if (url == "http://exitme") {
                    finish()
                    true
                } else {
                    view?.loadUrl(url)
                    true
                }
            }
        }
        webView.loadUrl("https://www.google.com")

    }

    fun hasPermissions(context: Context?, permissions: Array<String> ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    override fun onBackPressed() {
        // super.onBackPressed();
    }



}