package com.example.lzw.zyfypt_303_lzw.service;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/5/4.
 */

public interface ResourceService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<ResourceBean>> getResult(@Path("mod") String mod,
                                       @Query("page") int page,
                                       @Header("SessionID") String SessionID);
}
