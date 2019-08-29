package com.shopping.dao.impl;

import com.shopping.dao.CommonMethodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonMethodDaoImpl implements CommonMethodDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取List<Map<String, Object>>类型数据
    public List<Map<String, Object>> getData(String sql, Object[] args) {
        return jdbcTemplate.queryForList(sql,args);
    }

    //删除一条记录
    public int deleteSingleData(String sql, Object[] args) {
        return jdbcTemplate.update(sql,args);
    }
}
