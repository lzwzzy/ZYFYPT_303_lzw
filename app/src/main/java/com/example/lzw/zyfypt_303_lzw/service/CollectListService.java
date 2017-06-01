package com.example.lzw.zyfypt_303_lzw.service;

import com.example.lzw.zyfypt_303_lzw.bean.CollectBean;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/6/1.
 */

public interface CollectListService {
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<ResourceBean>>> getCollectList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
}
