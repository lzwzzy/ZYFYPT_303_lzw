package com.example.lzw.zyfypt_303_lzw.listener;

import com.example.lzw.zyfypt_303_lzw.bean.FocusResult;

import java.util.List;

/**
 * Created by lzw on 2017/6/1.
 */

public interface FocusListListener<T> {
    void onResponse(List<FocusResult<T>> beanlist);

    void onFail(String msg);
}
