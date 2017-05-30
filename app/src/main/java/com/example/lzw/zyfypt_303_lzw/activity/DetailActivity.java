package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webView;

    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        toolBarInit();
        webviewInit();

    }

    private void webviewInit() {
        webView = new WebView(this);
        application = (MyApplication) getApplication();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String mod = application.getMod();
        webView.loadUrl(BASEURL + mod + ".php/show/index/id/" + id);
        Log.d(TAG, "webviewInit: " + BASEURL + mod + ".php/show/index/id/" + id);
        setContentView(webView);
    }

    private void toolBarInit() {


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.detail);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
}
