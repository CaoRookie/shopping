package com.shopping.domain;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable{

    private static final long serialVersionUID = 2980517548391346749L;

    private Integer id;
    private String title;//标题
    private String content;//内容
    private String user;//发布人
    private Date infotime;//发布时间
    private Integer hits;//点击率

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getInfotime() {
        return infotime;
    }

    public void setInfotime(Date infotime) {
        this.infotime = infotime;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "News:{" +
                "id="+id+
                ",title='"+title+"'"+
                ",content='"+content+"'"+
                ",user='"+user+"'"+
                ",infotim='"+infotime+
                ",hits"+hits+
                "}";
    }
}
