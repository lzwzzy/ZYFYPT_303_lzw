package com.example.lzw.zyfypt_303_lzw.service;


import com.example.lzw.zyfypt_303_lzw.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/5/2.
 * 登陆网络接口访问
 */

public interface LoginService {
    @GET("api.php/login")
    Call<UserBean> login(@Query("username") String username,
                         @Query("password") String password);

}
