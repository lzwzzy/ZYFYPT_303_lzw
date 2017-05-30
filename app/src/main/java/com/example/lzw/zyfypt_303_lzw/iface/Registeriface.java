package com.example.lzw.zyfypt_303_lzw.iface;


import com.example.lzw.zyfypt_303_lzw.listener.RegisterListener;

/**
 * Created by lzw on 2017/5/2.
 */

public interface Registeriface {
    void getResultList(String username,
                       String password,
                       String tel,
                       RegisterListener registerListener);
}
