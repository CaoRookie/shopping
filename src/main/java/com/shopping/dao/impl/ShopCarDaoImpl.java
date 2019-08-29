package com.shopping.dao.impl;

import com.shopping.dao.ShopCarDao;
import com.shopping.domain.Car;
import com.shopping.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShopCarDaoImpl implements ShopCarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    private static final Logger logger= LoggerFactory.getLogger(ShopCarDaoImpl.class);

    public List<Map<String, Object>> getMyProduct(int user_id) {

//        String sql="SELECT * FROM car LEFT JOIN product ON car.pid=product.id WHERE car.user_id=?";
        String sql="SELECT\n" +
                "\tproduct.image_path PIMAGE,\n" +
                "\tproduct.`name` PNAME,\n" +
                "\tproduct.price PRICE,\n" +
                "\tcar.pnum PNUM,\n" +
                "\tcar.pid PID,\n" +
                "\tcar.id carId\n" +
                "FROM\n" +
                "\tcar\n" +
                "LEFT JOIN product ON car.pid = product.id\n" +
                "WHERE\n" +
                "\tcar.user_id = ?";
        return jdbcTemplate.queryForList(sql,new Object[]{user_id});
    }

    public int removeCarReportData(int user_id, int pid) {
        /*String sql="DELETE FROM car WHERE car.user_id = ? AND car.pid = ?";
        return jdbcTemplate.update(sql,new Object[]{user_id,pid});*/
        try{
            String hql="from Car c where c.user_id=? and c.pid=?";
            List<Car> productList=(List<Car>)hibernateTemplate.find(hql,new Object[]{user_id,pid});
            hibernateTemplate.delete(productList.get(0));
            return 1;
        }catch (DataAccessException e){
            return 0;
        }catch (IndexOutOfBoundsException e){
            return 0;
        }
    }

    public int addCarReportData(int user_id, int pid) {
        String hql="from Car c where c.user_id=? and c.pid=?";
        try {
            List<Car> productList=(List<Car>)hibernateTemplate.find(hql,new Object[]{user_id,pid});
            Car car=productList.get(0);
            car.setPnum(car.getPnum()+1);
            hibernateTemplate.update(car);
            return 1;
        }catch (DataAccessException e){
            logger.info("购物车数量增加更新失败HQL:{}",hql);
            return 0;
        }
    }
    public int reduceCarReportData(int user_id, int pid) {
        String hql="from Car c where c.user_id=? and c.pid=?";
        try {
            List<Car> productList=(List<Car>)hibernateTemplate.find(hql,new Object[]{user_id,pid});
            Car car=productList.get(0);
            car.setPnum(car.getPnum()-1);
            hibernateTemplate.update(car);
            return 1;
        }catch (DataAccessException e){
            logger.info("购物车数量减少更新失败HQL:{}",hql);
            return 0;
        }
    }
}
