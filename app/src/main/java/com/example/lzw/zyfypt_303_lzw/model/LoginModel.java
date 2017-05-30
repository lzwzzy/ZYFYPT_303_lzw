package com.example.lzw.zyfypt_303_lzw.model;


import com.example.lzw.zyfypt_303_lzw.bean.LoginBean;
import com.example.lzw.zyfypt_303_lzw.iface.Loginiface;
import com.example.lzw.zyfypt_303_lzw.listener.LoginListener;
import com.example.lzw.zyfypt_303_lzw.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/5/2.
 * 登陆逻辑
 */

public class LoginModel implements Loginiface {
    private Retrofit retrofit;
    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    public LoginModel() {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String username, String password, final LoginListener loginListener) {
        LoginService loginService = retrofit.create(LoginService.class);

        Call<LoginBean> call = loginService.login(username, password);

        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.isSuccessful() && response != null) {
                    loginListener.onResponse(response.body());
                } else {
                    loginListener.onFile("onFail");
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                loginListener.onFile(t.toString());
            }
        });
    }
}
