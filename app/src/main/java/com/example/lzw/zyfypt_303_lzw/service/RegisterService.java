package com.example.lzw.zyfypt_303_lzw.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/5/3.
 */

public interface RegisterService {
    @GET("api.php/reg")
    Call<String> reg(@Query("username") String username,
                     @Query("password") String password,
                     @Query("tel") String tel);

}
