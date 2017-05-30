package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;

/**
 * Created by lzw on 2017/5/2.
 */

public interface Resourceiface {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       ResourceListener resourceListener);
}
