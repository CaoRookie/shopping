package com.shopping.service;

import com.shopping.domain.Car;
import com.shopping.domain.Order;
import com.shopping.domain.UserAddress;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 获取用户地址
     * @param userId
     * @return
     */
    public List<UserAddress> getAddressByUserId(int userId);

    /**
     * 根据购物车选中的id，查询购物车相应的信息
     */
    public Map<String,Object> getSubmitProductByCarId(int carId);

    /**
     * 一键购买，根据传入产品Id获取对应商品记录
     */
    public Map<String,Object> getProductById(int pid);

    /**
     * 设置默认地址
     * @param addrId
     * @return
     */
    public int setDefaultAddr(int addrId);

    /**
     * 修改地址，回显数据
     * @param addrId
     * @return
     */
    public UserAddress getEditAddrd(int addrId);

    /**
     * 提交对地址的修改
     * @param map 修改后的地址的参数封装
     * @return
     */
    public int updateAddress(Map<String,Object> map);

    /**
     * 用户提交订单
     * @return
     */
    public int addOrderToDB(Order order,String pIds);
}
