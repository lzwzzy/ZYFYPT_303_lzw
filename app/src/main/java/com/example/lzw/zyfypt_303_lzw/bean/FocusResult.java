package com.example.lzw.zyfypt_303_lzw.bean;

/**
 * Created by lzw on 2017/6/2.
 */

public class FocusResult<T> {
    private int id;
    private int idolid;
    private String fwtime;
    private String listorder;
    private int userid;
    private int vstate;
    private T bean;

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public String getFwtime() {
        return fwtime;
    }

    public void setFwtime(String fwtime) {
        this.fwtime = fwtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdolid() {
        return idolid;
    }

    public void setIdolid(int idolid) {
        this.idolid = idolid;
    }

    public String getListorder() {
        return listorder;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVstate() {
        return vstate;
    }

    public void setVstate(int vstate) {
        this.vstate = vstate;
    }
}
