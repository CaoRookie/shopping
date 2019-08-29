package com.shopping.domain;

import java.io.Serializable;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 用户实体类
 * @Date: 9:26 2018/3/5
 */
public class User implements Serializable{
    private static final long serialVersionUID = 2980517548391346744L;

    private Integer user_id;
    private String user_image;
    private String user_name;
    private String user_phone;
    private String user_email;
    private String user_password;
    private Integer user_access;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getUser_access() {
        return user_access;
    }

    public void setUser_access(Integer user_access) {
        this.user_access = user_access;
    }

    @Override
    public String toString() {
        return "User:{" +
                "user_id="+user_id+
                ",user_image= '"+user_image+'\''+
                ",user_name= '"+user_name+'\''+
                ",user_password= '"+user_password+'\''+
                ",user_phone= '"+user_phone+'\''+
                ",user_email= '"+user_email+'\''+
                ",user_access= "+user_access+"}";
    }
}
