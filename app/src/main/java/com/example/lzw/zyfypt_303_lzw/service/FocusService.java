package com.example.lzw.zyfypt_303_lzw.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/6/2.
 * 关注
 */

public interface FocusService {
    //判断是否已关注
    @GET("api.php/exists/mod/{mod}")
    Call<String> exists(@Path("mod") String mod,
                        @Query("idolid") int idolid,
                        @Header("SessionID") String sessionID);

    //关注
    @GET("api.php/create/mod/{mod}")
    Call<String> focus(@Query("mod") String mod,
                       @Query("idolid") int idolid,
                       @Header("SessionID") String SessionID);

    //取消关注
    @GET("api.php/delete/mod/{mod}")
    Call<String> unfocus(@Path("mod") String mod,
                         @Query("idolid") int idolid,
                         @Header("SessionID") String sessionID);

}
