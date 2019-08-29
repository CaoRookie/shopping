package com.shopping.service.impl;

import com.shopping.dao.LoginDao;
import com.shopping.domain.User;
import com.shopping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:28 2018/3/7
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public User validateUser(String username, String password) {
        return loginDao.validateUser(username,password);
    }

    public int register(Map<String, Object> map) {
        return loginDao.register(map);
    }
}
