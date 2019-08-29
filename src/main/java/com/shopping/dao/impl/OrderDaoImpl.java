package com.shopping.dao.impl;

import com.shopping.constant.SysConstant;
import com.shopping.dao.OrderDao;
import com.shopping.domain.Car;
import com.shopping.domain.Order;
import com.shopping.domain.UserAddress;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger= LoggerFactory.getLogger(OrderDaoImpl.class);

    public List<UserAddress> getAddressByUserId(int userId) {
        String hql="from UserAddress u where u.user_id=? order by u.isDefaultAddr asc ";
        List<UserAddress> addressList=(List<UserAddress>)hibernateTemplate.find(hql,userId);
        return addressList;
    }

    public Map<String,Object> getSubmitProductByCarId(int carId) {
        String sql="SELECT * FROM car INNER JOIN product ON car.pid=product.id WHERE car.pid=? AND car.user_id=?";
        try{
            Car car=hibernateTemplate.get(Car.class,carId);
            return jdbcTemplate.queryForMap(sql,new Object[]{car.getPid(),car.getUser_id()});
        }catch (DataAccessException e){
            return null;
        }
    }

    public Map<String, Object> getProductById(int pid) {
        String sql="SELECT * FROM product WHERE id=?";
        try{
            return jdbcTemplate.queryForMap(sql,pid);
        }catch (DataAccessException e){
            return null;
        }
    }

    public int setDefaultAddr(int addrId) {
        try{
            List<UserAddress>list=hibernateTemplate.loadAll(UserAddress.class);
             for(UserAddress u:list){
                 u.setIsDefaultAddr(1);
                 hibernateTemplate.update(u);
             }
            UserAddress userAddress=hibernateTemplate.load(UserAddress.class,addrId);
            userAddress.setIsDefaultAddr(0);
            hibernateTemplate.update(userAddress);
            return 1;
        }catch (DataAccessException e){
            return 0;
        }
    }

    public UserAddress getEditAddrd(int addrId) {
        return hibernateTemplate.get(UserAddress.class,addrId);
    }

    public int updateAddress(Map<String, Object> map) {
        try{
            UserAddress userAddress=hibernateTemplate.get(UserAddress.class,Integer.valueOf(map.get("addrId").toString()));
            userAddress.setAddress(map.get("getProaddr").toString());
            userAddress.setReceiptUserName(map.get("getProname").toString());
            userAddress.setReceiptPhone(map.get("getProphone").toString());
            logger.info("修改后的地址为:{}",userAddress.toString());
            hibernateTemplate.update(userAddress);
            return 1;
        }catch (DataAccessException e){
            logger.info("修改地址失败!");
            return 0;
        }
    }

    /*public int addOrderToDB(Map<String, Object> map) {
         final String sql="insert into `order` (user_id,orderNumber,post,pay.address.state,totalPrice,date)" +
                    " values(?,?,?,?,?,?,?,?)";
            //order.setId(null);
            hibernateTemplate.execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    SQLQuery query=session.createSQLQuery(sql);
                    query.setInteger(0,order.getUser_id());
                    query.setString(1,order.getOrderNumber());
                    query.setString(2,order.getPost());
                    query.setString(3,order.getPay());
                    query.setString(4,order.getAddress());
                    query.setInteger(5,order.getState());
                    query.setBigDecimal(6,order.getTotalPrice());
                    query.setDate(7,order.getDate());
                    return query.list();
                }
            });
    }*/

    public int addOrderToDB(final Order order, String pIds) {
        try{
            hibernateTemplate.save(order);
            addProductToOrderDetail(order,pIds);
            return 1;
        }catch (DataAccessException e){
            logger.info("保存订单失败！错误信息：",e.getCause());
            return 0;
        }
    }

    private void addProductToOrderDetail(Order order,String pIds) {
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            String pidList[]=pIds.split(",");
            String sql="insert into orderdetail(orderId,pId,pnum,price)" +
                    " values(?,?" +
                    ",(select pnum from car where user_id=? and pid=?)" +
                    ",(select price from product where id=?))";
            conn= SessionFactoryUtils.getDataSource(hibernateTemplate.getSessionFactory()).getConnection();
            ps=conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for(int i=0;i<pidList.length;i++){
                ps.setString(1,order.getOrderNumber());
                ps.setString(2,pidList[i]);
                ps.setInt(3,order.getUser_id());
                ps.setString(4,pidList[i]);
                ps.setString(5,pidList[i]);
                ps.addBatch();
                if(i != 0 && i % SysConstant.BATCH_MAX_ROW == 0){
                    ps.executeBatch();
                    conn.commit();
                }
            }
            ps.executeBatch();
            conn.commit();
            ps.close();
            conn.close();
        }catch (SQLException e){
            hibernateTemplate.delete(order);
            logger.info("将订单商品提交到订单详情失败！错误信息：",e.getCause());
        }finally {
            try{
                if (ps != null)
                    ps.close();
            }catch (SQLException e){

            }
            try{
                if (conn != null)
                    conn.close();
            }catch (SQLException e){

            }
        }
    }
}