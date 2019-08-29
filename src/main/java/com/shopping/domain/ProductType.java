package com.shopping.domain;

import java.io.Serializable;

public class ProductType implements Serializable {

    private static final long serialVersionUID = 2980517548391346746L;

    private Integer product_type_id;
    private String product_type_name;
    private String icon_name;

    public Integer getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(Integer product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }

    public void setProduct_type_name(String product_type_name) {
        this.product_type_name = product_type_name;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    @Override
    public String toString() {
        return "product_type:{" +
                "product_type_id="+product_type_id+'\''+
                ",product_type_name="+product_type_name+'\''+
                ",icon_name="+icon_name+'\''+
                "}";
    }
}
