package com.example.lzw.zyfypt_303_lzw.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lzw.zyfypt_303_lzw.R;
import com.example.lzw.zyfypt_303_lzw.bean.LoginBean;
import com.example.lzw.zyfypt_303_lzw.bean.MyApplication;
import com.example.lzw.zyfypt_303_lzw.listener.LoginListener;
import com.example.lzw.zyfypt_303_lzw.model.LoginModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.login_username_edt)
    EditText mEditTextName;
    @BindView(R.id.login_password_edt)
    EditText mEditTextPassword;
    @BindView(R.id.textInputLayoutName)
    TextInputLayout mTextInputLayoutName;
    @BindView(R.id.textInputLayoutPassword)
    TextInputLayout mTextInputLayoutPswd;
    @BindView(R.id.switch1)
    Switch mRemember;

    private String username,password;
    private SharedPreferences login;

    private MyApplication application;
    private LoginListener listener = new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {

            if (!"".equals(loginBean.getError())) {
                Log.d(TAG, "onResponse: " + loginBean.getError());
                Toast.makeText(LoginActivity.this, loginBean.getError(), Toast.LENGTH_SHORT).show();
            } else {
                //如果验证成功跳转到主页
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                saveloginInfo();
                application = (MyApplication) getApplication();
                application.setSessionid(loginBean.getSessionid());
                application.setUsername(loginBean.getUsername());
                application.setRolename(loginBean.getRolename());
                Log.d(TAG, "onResponse: " + loginBean.getSessionid());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }

        @Override
        public void onFile(String msg) {
            System.out.println(msg);//TODO
            //提示错误信息
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private void saveloginInfo() {
        SharedPreferences.Editor editor = login.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putBoolean("remember",mRemember.isChecked());
        editor.apply();//提交保存
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定数据
        ButterKnife.bind(this);
        //工具栏初始化
        toolBarInit();
        //文字变化监测
        TextChangeListener();
        login = getSharedPreferences("login", MODE_PRIVATE);
        readloginInfo();
    }

    private void readloginInfo() {
        String name = login.getString("username",null);
        String pass = login.getString("password",null);
        Boolean isRemember = login.getBoolean("remember",false);
        if (isRemember){
            mEditTextName.setText(name);
            mEditTextPassword.setText(pass);
        }
    }

    //文字改变监听
    private void TextChangeListener() {
        mEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkName(s.toString(), false);
            }
        });

        mEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPswd(s.toString(), false);
            }
        });
    }

    private void toolBarInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.user_login);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClicked() {
        username = mEditTextName.getText().toString();
        password = mEditTextPassword.getText().toString();
        hideKeyBoard();
        if (!checkName(username, true))
            return;
        if (!checkPswd(password, true))
            return;
        LoginModel loginModel = new LoginModel();
        loginModel.getResultList(username, password, listener);
    }

    @OnClick(R.id.login_goToReg_btn)
    public void onGoToRegBtnClicked() {
        Intent intent = new Intent(LoginActivity.this, RegtisterActivity.class);
        startActivity(intent);
    }

    private boolean checkName(CharSequence name, boolean isLogin) {
        if (TextUtils.isEmpty(name)) {
            if (isLogin) {
                mTextInputLayoutName.setError(getString(R.string.error_login_empty));
                return false;
            }
        } else {
            mTextInputLayoutName.setError(null);
        }
        return true;
    }

    private boolean checkPswd(CharSequence pswd, boolean isLogin) {
        if (TextUtils.isEmpty(pswd)) {
            if (isLogin) {
                mTextInputLayoutPswd.setError(getString(R.string.error_pswd_empty));
                return false;
            }
        } else {
            mTextInputLayoutPswd.setError(null);
        }
        return true;
    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
