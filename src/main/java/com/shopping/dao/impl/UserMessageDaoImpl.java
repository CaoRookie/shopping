package com.shopping.dao.impl;

import com.shopping.constant.OrderConstant;
import com.shopping.constant.SysConstant;
import com.shopping.dao.UserMessageDao;
import com.shopping.domain.Order;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Repository
public class UserMessageDaoImpl implements UserMessageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private static final Logger logger= LoggerFactory.getLogger(UserMessageDaoImpl.class);

    public List<Map<String, Object>> getMessage(int userId,String type) {
        String sql="";
        String hql="";
        /*if(SysConstant.USER.equals(type))
            sql="SELECT a.user_name `name`,a.user_phone phone,a.user_email email  FROM `user` a WHERE a.user_id=?";
        if(SysConstant.ADDRESS.equals(type))
            sql="SELECT * FROM user_address WHERE user_id=?";
        if(SysConstant.ORDER.equals(type))
            sql="SELECT * FROM orders WHERE user_id=?";
        return jdbcTemplate.queryForList(sql,new Object[]{userId});*/
        if(SysConstant.USER.equals(type))
            hql="from User a where a.user_id=?";
        if(SysConstant.ADDRESS.equals(type))
            hql="FROM UserAddress a where a.user_id=?";
        if(OrderConstant.ORDER.equals(type))
            hql="FROM Order as a where a.user_id=?";
        return (List<Map<String, Object>>)hibernateTemplate.find(hql,new Object[]{userId});
    }

    public List<Map<String, Object>> getOrderInfo(String number) {
        String sql="select p.image_path,p.`name`,p.detail,p.price,o.pnum FROM product p " +
                "LEFT JOIN orderdetail o on p.id=o.pId WHERE o.orderId=?";
        return jdbcTemplate.queryForList(sql,new Object[]{number});
    }

    public Map<String, Object> getBottomMap(String number) {
        String sql="SELECT\n" +
                "\tos.pay,\n" +
                "\tos.post,\n" +
                "\tos.totalPrice,\n" +
                "\tua.receiptUserName,\n" +
                "\tua.receiptPhone,\n" +
                "\tua.address\n" +
                "FROM\n" +
                "\torders os\n" +
                "LEFT JOIN user_address ua ON os.address = ua.addrId\n" +
                "WHERE\n" +
                "\tos.orderNumber =?";
        return jdbcTemplate.queryForMap(sql,new Object[]{number});
    }

    public int deleteMyAddress(int userId, String addrId) {
        String sql="DELETE FROM user_address WHERE user_id=? AND addrId=?";
        return jdbcTemplate.update(sql,new Object[]{userId,addrId});
    }

    public int setOrAndDeAddr(int userId, String addrId) {
        String sql="SELECT * FROM user_address WHERE user_id=? AND addrId=?";
        Map<String,Object>map=jdbcTemplate.queryForMap(sql,new Object[]{userId,addrId});
        logger.info("地址类型的：[{}]",map.get("isDefaultAddr"));
        if(map.get("isDefaultAddr").equals(0)){
            sql="UPDATE user_address SET isDefaultAddr=1 WHERE user_id=? AND addrId=?";
        }else{
            sql="UPDATE user_address SET isDefaultAddr=0 WHERE user_id=? AND addrId=?";
        }
        logger.info("修改地址类型的sql：[{}]",sql);
        return jdbcTemplate.update(sql,new Object[]{userId,addrId});
    }

    public List<Map<String, Object>> getProvinceInfo(String id) {
        if (StringUtils.isBlank(id)){
           id = "0";
        }
        String sql = "SELECT id,`name` text FROM area where parentId=? ORDER BY id ASC";
        return jdbcTemplate.queryForList(sql,id);
    }

    public int saveReceiptAddr(Map<String,Object> map) {
        String sql="INSERT INTO user_address (user_id,receiptUserName,address,receiptPhone) VALUE (?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{map.get("userId"),map.get("receiptUser"),map.get("receiptAddr"),map.get("receiptPhone")});
    }

    public int delOrder(String orderNumber) {
        String sql="DELETE FROM orders WHERE orderNumber=?";
        return jdbcTemplate.update(sql,orderNumber);
    }

    public int cancelOrder(String orderNumber) {
        String sql="UPDATE orders set state=? WHERE orderNumber=?";
        return jdbcTemplate.update(sql,new Object[]{OrderConstant.CANCEL,orderNumber});
    }
}
