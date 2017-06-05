package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.FocusListListener;

/**
 * Created by lzw on 2017/6/1.
 */

public interface FocusListiface<T> {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       FocusListListener<T> listener
    );

}
