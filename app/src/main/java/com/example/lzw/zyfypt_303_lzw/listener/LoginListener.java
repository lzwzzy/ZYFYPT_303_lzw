package com.example.lzw.zyfypt_303_lzw.listener;


import com.example.lzw.zyfypt_303_lzw.bean.LoginBean;

/**
 * Created by lzw on 2017/5/2.
 */

public interface LoginListener {
    //成功返回列表
    void onResponse(LoginBean loginBean);

    //失败返回错误字符串
    void onFile(String msg);
}
