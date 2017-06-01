package com.example.lzw.zyfypt_303_lzw.bean;

/**
 * Created by lzw on 2017/6/1.
 * 收藏实体
 */

public class CollectBean<T> {
    private int id;
    private int resid;
    private int userid;
    private String cctime;
    private String listorder;
    private String vstat;
    private T bean;

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public String getCctime() {
        return cctime;
    }

    public void setCctime(String cctime) {
        this.cctime = cctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListorder() {
        return listorder;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getVstat() {
        return vstat;
    }

    public void setVstat(String vstat) {
        this.vstat = vstat;
    }
}
