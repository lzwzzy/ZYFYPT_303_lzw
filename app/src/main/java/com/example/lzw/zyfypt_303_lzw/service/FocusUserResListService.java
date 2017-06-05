package com.example.lzw.zyfypt_303_lzw.service;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/6/3.
 */

public interface FocusUserResListService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<ResourceBean>> getResourcesList(@Path("mod") String mod,
                                              @Query("page") int page,
                                              @Header("SessionID") String sessionID,
                                              @Query("userid") int userid);

}
