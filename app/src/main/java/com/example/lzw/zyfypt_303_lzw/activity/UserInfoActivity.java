package com.example.lzw.zyfypt_303_lzw.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.rolename)
    TextView rolename;
    @BindView(R.id.realname)
    TextView realname;
    @BindView(R.id.sex)
    TextView sex;

    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        init();
        tooolbarInit();
    }

    private void tooolbarInit() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("个人信息");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void init() {
        app = (MyApplication) getApplication();
        username.setText(app.getUsername());
        rolename.setText(app.getRolename());
        realname.setText(app.getRealname());
        sex.setText(app.getSex());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
