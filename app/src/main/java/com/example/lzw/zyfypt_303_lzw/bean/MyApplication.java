package com.example.lzw.zyfypt_303_lzw.bean;

import android.app.Application;

/**
 * Created by lzw on 2017/5/4.
 * 全局数据传递
 */

public class MyApplication extends Application {
    private String sessionid;
    private String username;
    private String rolename;
    private String mod;
    private String realname;
    private String sex;
    @Override
    public void onCreate() {
        super.onCreate();
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
