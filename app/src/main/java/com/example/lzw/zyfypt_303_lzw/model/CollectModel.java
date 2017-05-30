package com.example.lzw.zyfypt_303_lzw.model;

import com.example.lzw.zyfypt_303_lzw.iface.Collectiface;
import com.example.lzw.zyfypt_303_lzw.listener.CollectListener;
import com.example.lzw.zyfypt_303_lzw.service.CollectService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2017/5/30.
 */

public class CollectModel implements Collectiface {
    private Retrofit retrofit;
    private String BASEURL
            ="http://amicool.neusoft.edu.cn/";
    //构造函数
    public CollectModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    //收藏
    @Override
    public void collect(String mod, int id, String sessionid, final CollectListener listener) {
        //使用Retrofit----2
        CollectService  collectService=retrofit.create(CollectService.class);
        Call<String> call=collectService.collect(mod,id,sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))
                    listener.onResponse("1");//收藏失败
                else if(!response.body().trim().toString().contains("error"))
                    listener.onResponse("2");//收藏成功
                else
                    listener.onResponse("收藏："+response.body());


                }
                else   listener.onFail("collect onresponse fail");//
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("收藏失败："+t.toString());
            }
        });
    }
    //取消收藏
    @Override
    public void uncollect(String mod, int id, String sessionid, final CollectListener listener) {
        //使用Retrofit----2
        CollectService  collectService=retrofit.create(CollectService.class);
        Call<String> call=collectService.uncollect(mod,id,sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))
                    listener.onResponse("4");//取消收藏失败
                else if (response.body().trim().toString().equals("1"))
                    listener.onResponse("5");//取消收藏成功
                else
                    listener.onResponse("取消收藏："+response.body());

                }
                else   listener.onFail("uncollect onresponse fail");//uncollect onresponse fail
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("取消收藏失败："+t.toString());
            }
        });
    }
    //判断是否收藏
    @Override
    public void exist(String mod, int id, String sessionid, final CollectListener listener) {
        //使用Retrofit----2
        final CollectService collectService=retrofit.create(CollectService.class);
        Call<String> call=collectService.exists(mod,id,sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response!=null)
                {  if(response.body().trim().toString().equals("0"))//已收藏
                    listener.onResponse("7");
                else if (response.body().trim().toString().equals("1"))//未收藏
                    listener.onResponse("8");
                else
                    listener.onResponse("判断收藏："+response.body());

                }
                else   listener.onFail("collect exist onresponse fail");//
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("判断收藏失败："+t.toString());
            }
        });
    }

}
