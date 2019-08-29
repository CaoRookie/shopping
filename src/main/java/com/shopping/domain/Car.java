package com.shopping.domain;

import java.io.Serializable;

/**
 * 购物车实体类
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 2980517548391346745L;

    private Integer id;
    private Integer user_id;
    private Integer pnum;
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Car:{" +
                "id="+id+
                ",user_id="+user_id+
                ",pnum="+pnum+
                ",pid="+pid+
                "}";
    }
}
