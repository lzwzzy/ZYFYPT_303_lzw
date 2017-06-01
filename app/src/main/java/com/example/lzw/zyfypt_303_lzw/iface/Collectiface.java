package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.CollectListener;

/**
 * Created by lzw on 2017/5/30.
 */

public interface Collectiface {
    void collect(String mod, int id, String sessionid, CollectListener listener);

    void uncollect(String mod, int id, String sessionid, CollectListener listener);

    void exist(String mod, int id, String sessionid, CollectListener listener);

}
