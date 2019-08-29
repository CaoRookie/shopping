package com.shopping.dao;

import com.shopping.domain.Order;
import com.shopping.domain.Product;
import com.shopping.domain.ProductType;
import com.shopping.domain.User;

import java.util.List;
import java.util.Map;

public interface SystemDao {
    public List<Map<String,Object>>getReport(String type);
    public <T>int addItem(T t);
    public int delete(String pId,String type);
    public <T> int update(T t);

    public User getUserById(int id);

    public <T>void writeToTable(List<T> list);

    public void writeProductToJdbc(List<Product> list);
}
