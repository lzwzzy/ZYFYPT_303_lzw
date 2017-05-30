package com.example.lzw.zyfypt_303_lzw.listener;


/**
 * Created by lzw on 2017/5/2.
 */

public interface RegisterListener {
    //成功返回1
    void onResponse(String isReg);

    //失败返回错误字符串
    void onFile(String msg);
}
