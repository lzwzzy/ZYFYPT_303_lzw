package com.example.lzw.zyfypt_303_lzw.service;

import com.example.lzw.zyfypt_303_lzw.bean.FocusResult;
import com.example.lzw.zyfypt_303_lzw.bean.UserBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lzw on 2017/6/2.
 */

public interface FocusListService {
    @GET("api.php/listmyfocus/mod/{mod}focus")
    Call<List<FocusResult<UserBean>>> getFocusUserList(@Path("mod") String mod,
                                                       @Query("page") int page,
                                                       @Header("SessionID") String sessionID);

}
