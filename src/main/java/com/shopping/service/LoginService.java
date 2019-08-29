package com.shopping.service;

import com.shopping.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:26 2018/3/7
 */
public interface LoginService {

    public User validateUser(String username, String password);
    public int register(Map<String,Object> map);
}
