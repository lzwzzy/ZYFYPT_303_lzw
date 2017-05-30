package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.service.LogoutService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2017/5/6.
 */

public class LogoutModel {
    Boolean flag = true;
    private Retrofit retrofit;
    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    public LogoutModel() {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public boolean logout(String SessionId) {
        LogoutService logoutService = retrofit.create(LogoutService.class);
        Call<String> call = logoutService.logout(SessionId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    flag = true;
                } else {
                    flag = false;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                flag = false;
            }
        });
        return flag;
    }
}
