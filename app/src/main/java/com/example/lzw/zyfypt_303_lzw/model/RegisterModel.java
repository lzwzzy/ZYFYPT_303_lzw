package com.example.lzw.zyfypt_303_lzw.model;


import com.example.lzw.zyfypt_303_lzw.iface.Registeriface;
import com.example.lzw.zyfypt_303_lzw.listener.RegisterListener;
import com.example.lzw.zyfypt_303_lzw.service.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2017/5/2.
 * 登陆逻辑
 */

public class RegisterModel implements Registeriface {
    private Retrofit retrofit;
    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    public RegisterModel() {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String username, String password, String tel, final RegisterListener registerListener) {
        RegisterService registerService = retrofit.create(RegisterService.class);
        Call<String> call = registerService.reg(username, password, tel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response != null) {
                    registerListener.onResponse(response.body());
                } else {
                    registerListener.onFile("onFail");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                registerListener.onFile(t.toString());
            }
        });
    }
}
