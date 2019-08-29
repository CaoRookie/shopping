package com.shopping.dao;

import com.shopping.domain.Car;

import java.util.List;
import java.util.Map;

public interface ShopCarDao {
    public List<Map<String,Object>> getMyProduct(int user_id);

    public int removeCarReportData(int user_id,int pid);

    public int addCarReportData(int user_id, int pid);
    public int reduceCarReportData(int user_id, int pid);
}
