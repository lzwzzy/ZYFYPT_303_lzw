package com.example.lzw.zyfypt_303_lzw.iface;

import com.example.lzw.zyfypt_303_lzw.listener.FocusListener;

/**
 * Created by lzw on 2017/5/30.
 */

public interface Focusiface {


    void focus(String mod, int userid, String sessionid, FocusListener listener);

    void unfocus(String mod, int userid, String sessionid, FocusListener listener);

    void exist(String mod, int userid, String sessionid, FocusListener listener);

}
