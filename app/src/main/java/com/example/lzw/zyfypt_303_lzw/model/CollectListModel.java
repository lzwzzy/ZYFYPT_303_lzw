package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.bean.CollectBean;
import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.example.lzw.zyfypt_303_lzw.iface.CollectListiface;
import com.example.lzw.zyfypt_303_lzw.listener.CollectListListener;
import com.example.lzw.zyfypt_303_lzw.service.CollectListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/6/1.
 * 收藏列表网络请求
 */

public class CollectListModel implements CollectListiface<ResourceBean> {

    private Retrofit retrofit;
    private String BASEURL
            = "http://amicool.neusoft.edu.cn/";

    public CollectListModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final CollectListListener<ResourceBean> listener) {
        CollectListService service = retrofit.create(CollectListService.class);
        Call<List<CollectBean<ResourceBean>>> call = service.getCollectList(mod, page, sessionID);
        call.enqueue(new Callback<List<CollectBean<ResourceBean>>>() {
            @Override
            public void onResponse(Call<List<CollectBean<ResourceBean>>> call, Response<List<CollectBean<ResourceBean>>> response) {
                if (response.isSuccessful() && response != null) {
                    listener.onResponse(response.body());
                } else listener.onFail("onresponse fail");

            }

            @Override
            public void onFailure(Call<List<CollectBean<ResourceBean>>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });

    }
}
