package com.shopping.dao;

import java.util.List;
import java.util.Map;

public interface CommonMethodDao {

    public List<Map<String,Object>> getData(String sql,Object[] args);

    public int deleteSingleData(String sql,Object[] args);
}
