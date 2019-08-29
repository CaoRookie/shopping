package com.shopping.service.impl;

import com.shopping.dao.SystemDao;
import com.shopping.dao.UserMessageDao;
import com.shopping.domain.User;
import com.shopping.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageDao userMessageDao;
    @Autowired
    private SystemDao systemDao;

    public List<Map<String, Object>> getMessage(int userId,String type) {
        return userMessageDao.getMessage(userId,type);
    }

    public List<Map<String, Object>> getOrderInfo(String number) {
        return userMessageDao.getOrderInfo(number);
    }

    public Map<String, Object> getBottomMap(String number) {
        return userMessageDao.getBottomMap(number);
    }

    public int delOrder(String orderNumber) {
        return userMessageDao.delOrder(orderNumber);
    }

    public int cancelOrder(String orderNumber) {
        return userMessageDao.cancelOrder(orderNumber);
    }

    public int deleteMyAddress(int userId, String addrId) {
        return userMessageDao.deleteMyAddress(userId,addrId);
    }

    public int setOrAndDeAddr(int userId, String addrId) {
        return userMessageDao.setOrAndDeAddr(userId,addrId);
    }

    public List<Map<String, Object>> getProvinceInfo( String id) {
        return userMessageDao.getProvinceInfo(id);
    }

    public int saveReceiptAddr(Map<String,Object> map) {
        return userMessageDao.saveReceiptAddr(map);
    }

    public int update(User user) {
        return systemDao.update(user);
    }
}
