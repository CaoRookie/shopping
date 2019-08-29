package com.shopping.service.impl;

import com.shopping.dao.ShopCarDao;
import com.shopping.domain.Car;
import com.shopping.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarDao shopCarDao;

    public List<Map<String, Object>> getMyProduct(int user_id) {
        return shopCarDao.getMyProduct(user_id);
    }

    public int removeCarReportData(int user_id, int pid) {
        return shopCarDao.removeCarReportData(user_id,pid);
    }

    public int addCarReportData(int user_id, int pid) {
        return shopCarDao.addCarReportData(user_id,pid);
    }

    public int reduceCarReportData(int user_id, int pid) {
        return shopCarDao.reduceCarReportData(user_id,pid);
    }
}
