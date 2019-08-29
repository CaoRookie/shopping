package com.shopping.domain;

import java.io.Serializable;

public class Announcement implements Serializable {

    private static final long serialVersionUID = 2980517548391346742L;

    private Integer id;
    private String name;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Announcement:{" +
                "id="+id+
                ",name="+name+"'"+
                ",content="+content+"'"+
                "}";
    }
}
