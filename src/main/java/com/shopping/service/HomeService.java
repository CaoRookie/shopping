package com.shopping.service;

import com.shopping.domain.Announcement;
import com.shopping.domain.News;
import com.shopping.domain.Product;

import java.util.List;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:16 2018/2/27
 */
public interface HomeService {

    //获取点击率排行
    public List<Product> getClickRateHight();

    //获取销量最高
    public List<Product> getSaleProductQuantity();

    //获取新商品
    public List<Product> getNewProduct();

    //获取促销商品
    public List<Product> getPromotionsProduct();

    //点击加入购物车
    public int addProductToCar(Integer user_id,Integer pid);

    //获取公告信息
    public List<Announcement> getAnnouncement();

    //获取新闻信息
    public List<News> getNews();
}
