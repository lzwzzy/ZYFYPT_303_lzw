package com.example.lzw.zyfypt_303_lzw.bean;

/**
 * Created by lzw on 2017/5/4.
 * 资源实体类
 */

public class ResourceBean {

    private int id;//id
    private int userid;//资源用户id
    private String name;//标题
    private String thumb;//图片id，本例使用静态图片
    private String update_time;//时间
    private String description;//描述
    private String technoname;//技术类别
    private String pdfattach;//pdf地址
    private String videopath;//视频路径
    private String mod;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getPdfattach() {
        return pdfattach;
    }

    public void setPdfattach(String pdfattach) {
        this.pdfattach = pdfattach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getTechnoname() {
        return technoname;
    }

    public void setTechnoname(String technoname) {
        this.technoname = technoname;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
