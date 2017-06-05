package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;
import com.example.lzw.zyfypt_303_lzw.iface.FocusUserResListiface;
import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;
import com.example.lzw.zyfypt_303_lzw.service.FocusUserResListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2017/6/3.
 */

public class FocusUserResListModel implements FocusUserResListiface {

    private Retrofit retrofit;
    private String BASEURL = "http://amicool.neusoft.edu.cn/";

    //构造方法
    public FocusUserResListModel() {
        //实例化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, int userid, final ResourceListener listener) {
        FocusUserResListService service = retrofit.create(FocusUserResListService.class);
        Call<List<ResourceBean>> call = service.getResourcesList(mod, page, sessionID, userid);
        call.enqueue(new Callback<List<ResourceBean>>() {
            @Override
            public void onResponse(Call<List<ResourceBean>> call, Response<List<ResourceBean>> response) {
                if (response.isSuccessful()) {
                    listener.onResponse(response.body());
                } else {
                    listener.onFile("onFile");
                }
            }

            @Override
            public void onFailure(Call<List<ResourceBean>> call, Throwable t) {
                listener.onFile(t.toString());
            }
        });
    }
}
