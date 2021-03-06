package com.example.lzw.zyfypt_303_lzw.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.listener.CollectListener;
import com.example.lzw.zyfypt_303_lzw.listener.FocusListener;
import com.example.lzw.zyfypt_303_lzw.model.CollectModel;
import com.example.lzw.zyfypt_303_lzw.model.FocusModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewVideoActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Context context;
    private VideoView videoView;
    private String path = "";
    private String BASEURL = "http://amicool.neusoft.edu.cn/";
    private ProgressDialog dialog;
    private int resid;//资源id
    private int userid;//资源用户id
    private String mod;
    private String name;
    private Boolean flagcollect = false;//收藏标志
    CollectListener listener = new CollectListener() {
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.menucollect);
            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
                case "2":
                    System.out.println("----收藏成功");
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    flagcollect = true;
                    item.setTitle("取消收藏");
                    break;
                case "1":
                    System.out.println("----收藏失败");
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                    break;
                case "5":
                    System.out.println("----取消收藏成功");
                    Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    flagcollect = false;
                    item.setTitle("收藏");
                    break;
                case "4":
                    System.out.println("----取消收藏失败");
                    Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                    break;
                case "7":
                    System.out.println("----已收藏");
                    flagcollect = true;
                    item.setTitle("取消收藏");
                    break;
                case "8":
                    System.out.println("----未收藏");
                    flagcollect = false;
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
    private Boolean flagfocus = false;//关注标志
    FocusListener focusListener = new FocusListener() {
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.menufocus);
            //根据mode中response返回的字符串区分返回结果
            switch (msg) {
                case "2":
                    System.out.println("----关注成功");
                    Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "1":
                    System.out.println("----关注失败");
                    Toast.makeText(context, "关注失败", Toast.LENGTH_SHORT).show();
                    break;
                case "5":
                    System.out.println("----取消关注成功");
                    Toast.makeText(context, "取消关注成功", Toast.LENGTH_SHORT).show();
                    flagfocus = false;
                    item.setTitle("关注");
                    break;
                case "4":
                    System.out.println("----取消关注失败");
                    Toast.makeText(context, "取消关注失败", Toast.LENGTH_SHORT).show();
                    break;
                case "7":
                    System.out.println("----已关注");
                    flagfocus = true;
                    item.setTitle("取消关注");
                    break;
                case "8":
                    System.out.println("----未关注");
                    flagfocus = false;
                    item.setTitle("关注");
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
    private CollectModel collectmodel;//收藏model
    private FocusModel focusModel;//关注model
    private String sessionID = "";  //sessionid
    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        name = getIntent().getStringExtra("name");
        toolBarInit();
        context = ViewVideoActivity.this;
        application = (MyApplication) getApplication();
        sessionID = application.getSessionid();
        mod = application.getMod();
        resid = getIntent().getIntExtra("resid", 1);//获取传递的资源id
        userid = getIntent().getIntExtra("userid", 7);//获取传递的资源用户id
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置屏幕方向为横向
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//透明
        videoView = (VideoView) findViewById(R.id.videoView);

        path = BASEURL + "Uploads/video/video/" + getIntent().getStringExtra("videopath");//获取视频全路径
        dialog = ProgressDialog.show(ViewVideoActivity.this, "视频加载中...", "请您稍候");//进度条

        Uri uri = Uri.parse(path);
        videoView.setMediaController(new MediaController(this));//媒体播放控制工具
        videoView.setVideoURI(uri);//设置视频路径
        videoView.setOnPreparedListener(new MyPlayerOnPreparedListener());//播放开始回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());//播放完成回调
        videoView.requestFocus();// 让VideoView获取焦点
        videoView.start();//开始播放
    }

    private void toolBarInit() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);//加载菜单布局
        collectmodel = new CollectModel();//实例化对象
        focusModel = new FocusModel();//实例化对象
        collectmodel.exist(mod, resid, sessionID, listener);//判断是否收藏
        focusModel.exist("userfocus", userid, sessionID, focusListener);//判断是否关注
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menucollect:
                //Toast.makeText(this, "文章收藏", Toast.LENGTH_SHORT).show();
                if (flagcollect)//如果已收藏，则调用取消收藏
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect(mod, resid, sessionID, listener);
                } else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect(mod, resid, sessionID, listener);
                }
                break;
            case R.id.menufocus:
                //Toast.makeText(this, "文章关注", Toast.LENGTH_SHORT).show();
                if (flagfocus)//如果已关注，则调用取消关注
                {
                    System.out.println("----准备关注");
                    focusModel.unfocus("userfocus", userid, sessionID, focusListener);
                } else//如果未关注，则调用关注
                {
                    System.out.println("----准备取消关注");
                    focusModel.focus("userfocus", userid, sessionID, focusListener);
                }
                break;
            case android.R.id.home:
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //自定义子类，监听视频准备好，消除加载对话框
    class MyPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.setBackgroundColor(Color.argb(0, 0, 255, 0));
            dialog.dismiss();
        }
    }

    //自定义子类，监听播放完成，显示完成
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(ViewVideoActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().show();
        }
    }

}
