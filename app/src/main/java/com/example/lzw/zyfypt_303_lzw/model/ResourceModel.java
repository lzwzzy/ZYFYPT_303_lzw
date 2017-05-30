package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.example.lzw.zyfypt_303_lzw.iface.Resourceiface;
import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;
import com.example.lzw.zyfypt_303_lzw.service.ResourceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/5/4.
 */

public class ResourceModel implements Resourceiface {
    private Retrofit retrofit;
    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    //构造方法
    public ResourceModel() {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final ResourceListener resourceListener) {
        ResourceService resourceService = retrofit.create(ResourceService.class);
        Call<List<ResourceBean>> call = resourceService.getResult(mod, page, sessionID);
        call.enqueue(new Callback<List<ResourceBean>>() {
            @Override
            public void onResponse(Call<List<ResourceBean>> call, Response<List<ResourceBean>> response) {
                if (response.isSuccessful() && response != null) {
                    resourceListener.onResponse(response.body());
                } else {
                    resourceListener.onFile("onFile");
                }
            }

            @Override
            public void onFailure(Call<List<ResourceBean>> call, Throwable t) {
                resourceListener.onFile(t.toString());
            }
        });
    }
}
