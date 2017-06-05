package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.ResourceListener;

/**
 * Created by lzw on 2017/6/3.
 */

public interface FocusUserResListiface {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       int userid,//被关注用户id
                       ResourceListener resourceListener);
}
