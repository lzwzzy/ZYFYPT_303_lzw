package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.LoginListener;

/**
 * Created by lzw on 2017/5/2.
 */

public interface Loginiface {
    void getResultList(String username, String password, LoginListener loginListener);
}
