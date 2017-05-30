package com.example.lzw.zyfypt_303_lzw.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/5/2.
 */

public class BaseModel {

    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    public BaseModel(Retrofit retrofit) {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
