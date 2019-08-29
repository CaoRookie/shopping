package com.shopping.service.impl;

import com.shopping.dao.HomeDao;
import com.shopping.domain.Announcement;
import com.shopping.domain.News;
import com.shopping.domain.Product;
import com.shopping.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 首页逻辑层
 * @Date: 17:16 2018/2/27
 */
@Service
public class HomeServiceImpl implements HomeService {

    //注入dao
    @Autowired
    private HomeDao homeDao;

    public List<Product> getClickRateHight() {
        return homeDao.getClickRateHight();
    }

    public List<Product> getSaleProductQuantity() {
        return homeDao.getSaleProductQuantity();
    }

    public List<Product> getNewProduct() {
        return homeDao.getNewProduct();
    }

    public List<Product> getPromotionsProduct() {
        return homeDao.getPromotionsProduct();
    }

    public int addProductToCar(Integer user_id, Integer pid) {
        return homeDao.addProductToCar(user_id,pid);
    }

    public List<Announcement> getAnnouncement() {
        return homeDao.getAnnouncement();
    }

    public List<News> getNews() {
        return homeDao.getNews();
    }
}
