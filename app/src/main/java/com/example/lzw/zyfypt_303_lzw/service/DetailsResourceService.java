package com.example.lzw.zyfypt_303_lzw.service;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/5/4.
 */

public interface DetailsResourceService {
    @GET("api.php/get/mod/{mod}")
    Call<ResourceBean> getArticle(@Path("mod") String mod, @Query("id") int id);

}
