package com.example.lzw.zyfypt_303_lzw.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lzw.zyfypt_303_lzw.R;

public class ViewVideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private String path="";
    private String BASEURL="http://amicool.neusoft.edu.cn/";
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置屏幕方向为横向
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//透明
        videoView=(VideoView)findViewById(R.id.videoView);

        path=BASEURL+"Uploads/video/video/"+getIntent().getStringExtra("videopath");//获取视频全路径
        dialog=ProgressDialog.show(ViewVideoActivity.this, "视频加载中...", "请您稍候");//进度条

        Uri uri = Uri.parse(path);
        videoView.setMediaController(new MediaController(this));//媒体播放控制工具
        videoView.setVideoURI(uri);//设置视频路径
        videoView.setOnPreparedListener(new MyPlayerOnPreparedListener());//播放开始回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());//播放完成回调
        videoView.requestFocus();// 让VideoView获取焦点
        videoView.start();//开始播放
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
            Toast.makeText( ViewVideoActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().show();
        }
    }

}
