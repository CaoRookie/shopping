package com.shopping.dao.rowmapper;

import com.shopping.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDetail(resultSet.getString("detail"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setVip_price(resultSet.getBigDecimal("vip_price"));
        product.setClick_rate(resultSet.getInt("click_rate"));
        product.setLave_quantity(resultSet.getInt("lave_quantity"));
        product.setProduct_type(resultSet.getInt("product_type"));
        product.setPromotions_price(resultSet.getBigDecimal("promotions_price"));
        product.setImage_path(resultSet.getString("image_path"));
        product.setSale_quantity(resultSet.getInt("sale_quantity"));
        return product;
    }
}