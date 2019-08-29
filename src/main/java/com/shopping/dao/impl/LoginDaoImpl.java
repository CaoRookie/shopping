package com.shopping.dao.impl;

import com.opensymphony.xwork2.ActionContext;
import com.shopping.dao.LoginDao;
import com.shopping.dao.rowmapper.UserRowMapper;
import com.shopping.domain.User;
import com.shopping.util.AccountVlidateUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 16:32 2018/3/7
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);

    public  User validateUser(String username, String password) {
        User user = null;
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM  user where ");
        sql.append("user_password=?");
        if(AccountVlidateUtil.isEmail(username)){
            sql.append(" and user_email=?");
        }
        if(AccountVlidateUtil.isName(username)){
            sql.append(" and user_name=?");
        }
        if(AccountVlidateUtil.isPhone(username)){
            sql.append(" and user_phone=?");
        }
        try{
             user = jdbcTemplate.queryForObject(sql.toString(),new Object[]{password,username},new UserRowMapper());
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("验证数据库结果："+user);
        return user;
    }

    public int register(Map<String, Object> map) {
        String sql="INSERT INTO `user` (user_name,user_phone,user_email,user_password) VALUE (?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{map.get("username"),map.get("phone"),map.get("myemail"),map.get("sPassword"),});
    }
}
