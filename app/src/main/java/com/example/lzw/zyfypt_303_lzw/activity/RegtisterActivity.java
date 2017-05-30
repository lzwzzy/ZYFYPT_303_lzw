package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.listener.RegisterListener;
import com.example.lzw.zyfypt_303_lzw.model.RegisterModel;

import static android.content.ContentValues.TAG;

public class RegtisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username_edt, tel_edt, password_edt, repassword_edt;
    private Button reg_btn, reg_backlogin_btn;

    private RegisterListener listener = new RegisterListener() {
        @Override
        public void onResponse(String isReg) {
            Log.d(TAG, "onResponse: " + isReg);
            if (isReg.equals("1")) {
                Toast.makeText(RegtisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegtisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegtisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFile(String msg) {
            Toast.makeText(RegtisterActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtister);
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.register);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void init() {
        //获取控件
        username_edt = (EditText) findViewById(R.id.reg_username_edt);
        tel_edt = (EditText) findViewById(R.id.reg_phone_edt);
        password_edt = (EditText) findViewById(R.id.reg_password_edt);
        repassword_edt = (EditText) findViewById(R.id.reg_repassword_edt);

        reg_btn = (Button) findViewById(R.id.reg_btn);
        reg_backlogin_btn = (Button) findViewById(R.id.reg_backLogin_btn);
        //注册监听
        reg_btn.setOnClickListener(this);
        reg_backlogin_btn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String username = username_edt.getText().toString();
        String password = password_edt.getText().toString();
        String repassword = repassword_edt.getText().toString();
        String tel = tel_edt.getText().toString();
        switch (v.getId()) {
            case R.id.reg_btn:
                if (username == null || username.equals("")) {
                    Toast.makeText(RegtisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (password == null || password.equals("")) {
                    Toast.makeText(RegtisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (tel == null || tel.equals("")) {
                    Toast.makeText(RegtisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (repassword.equals(password)) {
                        RegisterModel registerModel = new RegisterModel();
                        registerModel.getResultList(username, password, tel, listener);
                    } else {
                        Toast.makeText(RegtisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.reg_backLogin_btn:
                Intent intent = new Intent(RegtisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
