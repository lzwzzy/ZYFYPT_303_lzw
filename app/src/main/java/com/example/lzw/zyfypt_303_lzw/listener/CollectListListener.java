package com.example.lzw.zyfypt_303_lzw.listener;

import com.example.lzw.zyfypt_303_lzw.bean.CollectBean;

import java.util.List;

/**
 * Created by lzw on 2017/6/1.
 */

public interface CollectListListener<T> {
    void onResponse(List<CollectBean<T>> beanlist);

    void onFail(String msg);
}
