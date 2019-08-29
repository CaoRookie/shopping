package com.shopping.service.impl;

import com.shopping.dao.OrderDao;
import com.shopping.domain.Car;
import com.shopping.domain.Order;
import com.shopping.domain.UserAddress;
import com.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<UserAddress> getAddressByUserId(int userId) {
        return orderDao.getAddressByUserId(userId);
    }

    public Map<String,Object> getSubmitProductByCarId(int carId) {
        return orderDao.getSubmitProductByCarId(carId);
    }

    public Map<String, Object> getProductById(int pid) {
        return orderDao.getProductById(pid);
    }

    public int setDefaultAddr(int addrId) {
        return orderDao.setDefaultAddr(addrId);
    }

    public UserAddress getEditAddrd(int addrId) {
        return orderDao.getEditAddrd(addrId);
    }

    public int updateAddress(Map<String, Object> map) {
        return orderDao.updateAddress(map);
    }

    public int addOrderToDB(Order order, String pIds) {
        return orderDao.addOrderToDB(order,pIds);
    }
}
