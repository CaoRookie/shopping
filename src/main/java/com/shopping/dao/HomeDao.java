package com.shopping.dao;

import com.shopping.domain.Announcement;
import com.shopping.domain.News;
import com.shopping.domain.Product;

import java.util.List;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:49 2018/2/27
 */
public interface HomeDao {

    //获取点击率排行
    public List<Product> getClickRateHight();

    //获取销量
    public List<Product> getSaleProductQuantity();

    //获取新商品
    public List<Product> getNewProduct();

    //获取促销商品
    public List<Product> getPromotionsProduct();

    //点击加入购物车
    public int addProductToCar(int user_id,int pid);

    public List<Announcement> getAnnouncement();

    public List<News> getNews();

}
