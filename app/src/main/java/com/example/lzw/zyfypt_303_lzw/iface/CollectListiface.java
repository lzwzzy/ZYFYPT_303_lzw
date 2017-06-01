package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.CollectListListener;

/**
 * Created by lzw on 2017/6/1.
 */

public interface CollectListiface<T> {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       CollectListListener<T> listener
    );

}
