package com.shopping.dao.impl;

import com.shopping.constant.OrderConstant;
import com.shopping.constant.SysConstant;
import com.shopping.dao.SystemDao;
import com.shopping.domain.Order;
import com.shopping.domain.Product;
import com.shopping.domain.ProductType;
import com.shopping.domain.User;
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
public class SystemDaoImpl implements SystemDao {

    private static final Logger logger= LoggerFactory.getLogger(SystemDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;


    public List<Map<String, Object>> getReport(String type) {
        String sql="";
        if (SysConstant.PRODUCT_TYPE.equals(type))
            sql = "SELECT * FROM product ORDER BY id ASC";
        if(SysConstant.PRODUCTTYPE_TYPE.equals(type))
            sql = "SELECT * FROM product_type ORDER BY product_type_id ASC";
        if(SysConstant.USER.equals(type))
            sql = "SELECT * FROM user ORDER BY user_id ASC";
        if(OrderConstant.ORDER.equals(type))
            sql = "SELECT * FROM orders ORDER BY id ASC";
        if(OrderConstant.ORDERCL.equals(type)){
            sql="SELECT * FROM orders WHERE state=? ORDER BY id ASC";
            return jdbcTemplate.queryForList(sql,OrderConstant.RECEIVE);
        }
        if(OrderConstant.ORDERDCL.equals(type)){
            sql="SELECT * FROM orders WHERE state=? ORDER BY id ASC";
            return jdbcTemplate.queryForList(sql,OrderConstant.SHIP);
        }
        if(OrderConstant.ORDERCG.equals(type)){
            sql="SELECT * FROM orders WHERE state=? ORDER BY id ASC";
            return jdbcTemplate.queryForList(sql,OrderConstant.EVALUATION);
        }
        return jdbcTemplate.queryForList(sql);
    }
    //hibernate查询查出来是Object数组形式-----不友好
    public List<Map<String, Object>> getReportAsHibernate(String type) {
        final StringBuilder sb=new StringBuilder();
        if (SysConstant.PRODUCT_TYPE.equals(type)){
            sb.delete(0,sb.length());
            sb.append("SELECT * FROM product ORDER BY id ASC");
        }
        if(SysConstant.PRODUCTTYPE_TYPE.equals(type)){
            sb.delete(0,sb.length());
            sb.append("SELECT * FROM product_type ORDER BY product_type_id ASC");
        }
        if(SysConstant.USER.equals(type)){
            sb.delete(0,sb.length());
            sb.append("SELECT * FROM user ORDER BY user_id ASC");
        }
        if(OrderConstant.ORDER.equals(type)){
            sb.delete(0,sb.length());
            sb.append("SELECT * FROM orders ORDER BY id ASC");
        }
        //查出来是Object数组形式-----不友好
        return hibernateTemplate.execute(new HibernateCallback<List<Map<String,Object>>>() {
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
                SQLQuery sqlQuery=session.createSQLQuery(sb.toString());
                return sqlQuery.list();
            }
        });
    }

    public <T> int addItem(T t) {
        try{
            hibernateTemplate.save(t);
            return 1;
        }catch (DataAccessException e){
            logger.info("系统管理--->数据添加失败！错误：\n{}",e.getMessage());
            return 0;
        }
    }

    public int delete(String pId, String type) {
        try{
            if (SysConstant.PRODUCT_TYPE.equals(type)){
                Product p=hibernateTemplate.get(Product.class,new Integer(pId));
                hibernateTemplate.delete(p);
            }else if(SysConstant.PRODUCTTYPE_TYPE.equals(type)){
                ProductType p=hibernateTemplate.get(ProductType.class,new Integer(pId));
                hibernateTemplate.delete(p);
            }else if(SysConstant.USER.equals(type)){
                User p=hibernateTemplate.get(User.class,new Integer(pId));
                hibernateTemplate.delete(p);
                }else if(OrderConstant.ORDER.equals(type)){
                Order p=hibernateTemplate.get(Order.class,new Integer(pId));
                hibernateTemplate.delete(p);
            }
            return 1;
        }catch (DataAccessException e){
            return 0;
        }
    }
    public <T> int update(T t){
        try {
            hibernateTemplate.update(t);
            return 1;
        }catch (DataAccessException e){
            logger.info("修改数据出错:{}",e.getMessage());
            return 0;
        }
    }

    public User getUserById(int id) {
        return hibernateTemplate.get(User.class,id);
    }

    public <T>void writeToTable(final List<T> list) {

    }
    public void writeProductToJdbc(List<Product> list){
        Connection conn=null;
        PreparedStatement pStmt=null;
        try{
            String sql="INSERT INTO product (`name`,detail,price,vip_price," +
                    "lave_quantity,sale_quantity,click_rate,promotions_price," +
                    "image_path,product_type,`date`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            conn=SessionFactoryUtils.getDataSource(hibernateTemplate.getSessionFactory()).getConnection();
            pStmt=conn.prepareStatement(sql);
            conn.setAutoCommit(false);//设置手动提交事务
            for(int i=0;i<list.size();i++){
                pStmt.setString(1,list.get(i).getName());
                pStmt.setString(2,list.get(i).getDetail());
                pStmt.setBigDecimal(3,list.get(i).getPrice());
                pStmt.setBigDecimal(4,list.get(i).getVip_price());
                pStmt.setInt(5,list.get(i).getLave_quantity());
                pStmt.setInt(6,list.get(i).getSale_quantity());
                pStmt.setInt(7,list.get(i).getClick_rate());
                pStmt.setBigDecimal(8,list.get(i).getPromotions_price());
                pStmt.setString(9,list.get(i).getImage_path());
                pStmt.setInt(10,list.get(i).getProduct_type());
                pStmt.setString(11,list.get(i).getDate());
                pStmt.addBatch();
                if(i != 0 && i % SysConstant.BATCH_MAX_ROW == 0){
                    pStmt.executeBatch();
                    conn.commit();
                }
            }
            pStmt.executeBatch();
            conn.commit();
            pStmt.close();
            conn.close();
        }catch (SQLException e){
            logger.info("商品批处理失败！失败原因：{}",e.getMessage());
        }finally {
            try{
                if(pStmt!=null)
                    pStmt.close();
            }catch (SQLException e2){

            }
            try{
                if (conn!=null)
                    conn.close();
            }catch (SQLException e1){

            }
        }
    }
}
