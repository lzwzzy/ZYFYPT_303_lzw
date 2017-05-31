package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.listener.CollectListener;
import com.example.lzw.zyfypt_303_lzw.model.CollectModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class DetailActivity extends AppCompatActivity {


    private CoordinatorLayout layout;
    private Toolbar toolbar;

    private WebView webView;

    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    private MyApplication application;

    private String mod;

    private int resid;//资源id
    private int userid;//资源用户id
    private String name;
    Context context;

    private Boolean flagcollect=false;//收藏标志
    private Boolean flagfocus=false;//关注标志

    private CollectModel collectmodel;//收藏model
    private String sessionID="";  //sessionid

    CollectListener listener=new CollectListener() {
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item=(ActionMenuItemView)findViewById(R.id.menucollect);
            //根据mode中response返回的字符串区分返回结果
            switch (msg)
            {
                case "2": System.out.println("----收藏成功");
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    flagcollect=true;
                    item.setTitle("取消收藏");
                    break;
                case "1":System.out.println("----收藏失败");
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    break;
                case "5":System.out.println("----取消收藏成功");
                    Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                case "4":System.out.println("----取消收藏失败");
                    Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                    break;
                case "7":System.out.println("----已收藏");
                    flagcollect=true;
                    item.setTitle("取消收藏");
                    break;
                case "8":System.out.println("----未收藏");
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                default:
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name=getIntent().getStringExtra("name");
        context=DetailActivity.this;
        application = (MyApplication) getApplication();
        sessionID = application.getSessionid();
        mod = application.getMod();
        resid  = getIntent().getIntExtra("resid",1);//获取传递的资源id
        userid = getIntent().getIntExtra("userid",7);//获取传递的资源用户id
        toolBarInit();
        webviewInit();


    }

    private void webviewInit() {

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置WebView属性,运行执行js脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(BASEURL + mod + ".php/show/index/id/" + resid);
        Log.d(TAG, "webviewInit: " + BASEURL + mod + ".php/show/index/id/" + resid);
    }

    private void toolBarInit() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);//加载菜单布局
        collectmodel=new CollectModel();//实例化对象
        collectmodel.exist(mod,resid,sessionID,listener);//判断是否收藏
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menucollect:
                //Toast.makeText(this, "文章收藏", Toast.LENGTH_SHORT).show();
                if(flagcollect)//如果已收藏，则调用取消收藏
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect(mod,resid,sessionID,listener);
                }
                else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect(mod,resid,sessionID,listener);
                }
                break;
            case R.id.menufocus:
                //Toast.makeText(this, "文章关注", Toast.LENGTH_SHORT).show();
                if(flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备关注");
                }
                else//如果未关注，则调用关注
                {
                    System.out.println("----准备取消关注");
                }
                break;
            case android.R.id.home:
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
