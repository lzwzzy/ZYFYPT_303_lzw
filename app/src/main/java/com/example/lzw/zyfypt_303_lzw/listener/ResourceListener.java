package com.example.lzw.zyfypt_303_lzw.listener;

import com.example.lzw.zyfypt_303_lzw.bean.ResourceBean;

import java.util.List;

/**
 * Created by lzw on 2017/5/4.
 */

public interface ResourceListener {
    //成功返回数据列表
    void onResponse(List<ResourceBean> list);

    //失败返回错误字符串
    void onFile(String msg);
}
