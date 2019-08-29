package com.shopping.dao;

import com.shopping.domain.Car;
import com.shopping.domain.Order;
import com.shopping.domain.UserAddress;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public List<UserAddress> getAddressByUserId(int userId);

    public Map<String,Object> getSubmitProductByCarId(int carId);

    public Map<String,Object> getProductById(int pid);

    public int setDefaultAddr(int addrId);

    public UserAddress getEditAddrd(int addrId);

    public int updateAddress(Map<String,Object> map);

    public int addOrderToDB(Order order,String pIds);

}
