package com.shopping.dao;

import com.shopping.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 16:30 2018/3/7
 */
public interface LoginDao {

    //验证用户登录
    public User validateUser(String username, String password);
    public int register(Map<String,Object> map);
}
