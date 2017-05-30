package com.example.lzw.zyfypt_303_lzw.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by lzw on 2017/5/6.
 */

public interface LogoutService {
    @GET("api.php/logout")
    Call<String> logout(@Header("SessionID") String sessionID);
}
