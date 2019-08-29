package com.shopping.service;

import com.shopping.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMessageService {
    public List<Map<String,Object>> getMessage(int userId,String type);

    public List<Map<String,Object>> getOrderInfo(String number);
    public Map<String,Object> getBottomMap(String number);

    public int delOrder(String orderNumber);
    public int cancelOrder(String orderNumber);
    public int deleteMyAddress(int userId,String addrId );
    public int setOrAndDeAddr(int userId,String addrId );

    public List<Map<String,Object>> getProvinceInfo( String id);
    public int saveReceiptAddr(Map<String,Object> map);

    public int update(User user);
}
