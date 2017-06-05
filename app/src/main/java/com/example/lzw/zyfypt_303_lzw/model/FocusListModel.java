package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.bean.FocusResult;
import com.example.lzw.zyfypt_303_lzw.bean.UserBean;
import com.example.lzw.zyfypt_303_lzw.iface.FocusListiface;
import com.example.lzw.zyfypt_303_lzw.listener.FocusListListener;
import com.example.lzw.zyfypt_303_lzw.service.FocusListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/6/1.
 * 关注列表网络请求
 */

public class FocusListModel implements FocusListiface<UserBean> {

    private Retrofit retrofit;
    private String BASEURL
            = "http://amicool.neusoft.edu.cn/";

    public FocusListModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final FocusListListener<UserBean> listener) {
        FocusListService service = retrofit.create(FocusListService.class);
        Call<List<FocusResult<UserBean>>> call = service.getFocusUserList(mod, page, sessionID);
        call.enqueue(new Callback<List<FocusResult<UserBean>>>() {
            @Override
            public void onResponse(Call<List<FocusResult<UserBean>>> call, Response<List<FocusResult<UserBean>>> response) {
                if (response.isSuccessful()) {
                    listener.onResponse(response.body());
                } else listener.onFail("onresponse fail");

            }

            @Override
            public void onFailure(Call<List<FocusResult<UserBean>>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });

    }
}
