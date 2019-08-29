package com.shopping.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 商品实体
 * @Date: 17:24 2018/2/27
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 2980517548391346743L;
    private Integer id;
    private String name;
    private String detail;
    private BigDecimal price;
    private BigDecimal vip_price;
    private Integer lave_quantity;
    private Integer sale_quantity;
    private Integer click_rate;
    private BigDecimal promotions_price;
    private String image_path;
    private Integer product_type;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVip_price() {
        return vip_price;
    }

    public void setVip_price(BigDecimal vip_price) {
        this.vip_price = vip_price;
    }

    public Integer getLave_quantity() {
        return lave_quantity;
    }

    public void setLave_quantity(Integer lave_quantity) {
        this.lave_quantity = lave_quantity;
    }

    public Integer getSale_quantity() {
        return sale_quantity;
    }

    public void setSale_quantity(Integer sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public Integer getClick_rate() {
        return click_rate;
    }

    public void setClick_rate(Integer click_rate) {
        this.click_rate = click_rate;
    }

    public BigDecimal getPromotions_price() {
        return promotions_price;
    }

    public void setPromotions_price(BigDecimal promotions_price) {
        this.promotions_price = promotions_price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "product:{id="+id
                +",name='"+name+'\''
                +",detail='"+detail+'\''
                +",price="+price
                +",vip_price="+vip_price
                +",lave_quantity="+lave_quantity
                +",sale_quantity="+sale_quantity
                +",click_rate="+click_rate
                +",promotions_price="+promotions_price
                +",image_path='"+image_path+'\''
                +",product_type='"+product_type+'\''
                +",date='"+date+'\''+"}";
    }
}
