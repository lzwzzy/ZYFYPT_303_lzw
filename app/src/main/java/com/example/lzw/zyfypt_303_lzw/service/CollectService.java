package com.example.lzw.zyfypt_303_lzw.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/5/30.
 */

public interface CollectService {
    //收藏
    @GET("api.php/create/mod/collect{mod}")
    Call<String> collect(@Path("mod") String mod,
                         @Query("resid") int resid,
                         @Header("SessionID") String sessionID);

    //是否已收藏
    @GET("api.php/exists/mod/collect{mod}")
    Call<String> exists(@Path("mod") String mod,
                        @Query("resid") int resid,
                        @Header("SessionID") String sessionID);

    //取消收藏
    @GET("api.php/delete/mod/collect{mod}")
    Call<String> uncollect(@Path("mod") String mod,
                           @Query("resid") int resid,
                           @Header("SessionID") String sessionID);


}
