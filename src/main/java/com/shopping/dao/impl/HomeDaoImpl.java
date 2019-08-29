package com.shopping.dao.impl;

import com.shopping.dao.HomeDao;
import com.shopping.dao.rowmapper.ProductRowMapper;
import com.shopping.domain.Announcement;
import com.shopping.domain.News;
import com.shopping.domain.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:51 2018/2/27
 */
@Repository
public class HomeDaoImpl implements HomeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private static final Logger logger= LoggerFactory.getLogger(HomeDaoImpl.class);

    public List<Product> getClickRateHight() {
        /*String sql="SELECT * FROM product ORDER BY product.click_rate DESC LIMIT 4";
        //List<?>list=jdbcTemplate.queryForList(sql);
        List<Product> list = jdbcTemplate.query(sql,new ProductRowMapper());
        return list;*/
        final String hql="FROM Product p order by p.click_rate desc";
            return hibernateTemplate.execute(new HibernateCallback<List<Product>>() {
            public List<Product> doInHibernate(Session session) throws HibernateException {
                return session.createQuery(hql).setMaxResults(4).list();
            }
        });
    }

    public List<Product> getSaleProductQuantity() {
//        String sql="SELECT * FROM product ORDER BY product.sale_quantity DESC LIMIT 4";
//        List<?>list=jdbcTemplate.queryForList(sql);
//        return (List<Product>)list;
        final String hql="FROM Product p order by p.sale_quantity desc";
        return hibernateTemplate.execute(new HibernateCallback<List<Product>>() {
            public List<Product> doInHibernate(Session session) throws HibernateException {
                return session.createQuery(hql).setMaxResults(4).list();
            }
        });
    }

    public List<Product> getNewProduct() {
//        String sql="SELECT * FROM product ORDER BY product.lave_quantity DESC LIMIT 4";
//        List<?>list=jdbcTemplate.queryForList(sql);
//        return (List<Product>)list;
        final String hql="FROM Product p order by p.date asc";
        return hibernateTemplate.execute(new HibernateCallback<List<Product>>() {
            public List<Product> doInHibernate(Session session) throws HibernateException {
                return session.createQuery(hql).setMaxResults(4).list();
            }
        });
    }

    public List<Product> getPromotionsProduct() {
//        String sql="SELECT * FROM product ORDER BY product.promotions_price LIMIT 4";
//        List<?>list=jdbcTemplate.queryForList(sql);
//        return (List<Product>)list;
        final String hql="FROM Product p order by p.promotions_price desc";
        return hibernateTemplate.execute(new HibernateCallback<List<Product>>() {
            public List<Product> doInHibernate(Session session) throws HibernateException {
                return session.createQuery(hql).setMaxResults(4).list();
            }
        });
    }

    public int addProductToCar(int user_id, int pid) {
        String sql="SELECT * FROM car WHERE pid=? AND user_id=?";
        Object [] params=new Object[]{pid,user_id};
        List<Map<String,Object>>list=jdbcTemplate.queryForList(sql,params);
        if(list != null && list.size() > 0){
            sql="UPDATE car set pnum=pnum+1 WHERE pid=? AND user_id=?";
            return jdbcTemplate.update(sql,params);
        }
        sql="INSERT INTO car(pid,user_id) VALUES (?,?)";
         return jdbcTemplate.update(sql,params);
    }

    public List<Announcement> getAnnouncement() {
        try{
            List<Announcement> announcements=hibernateTemplate.loadAll(Announcement.class);
            if (!announcements.isEmpty())
                return  announcements;
            return null;
        }catch (DataAccessException e){
            return null;
        }
    }

    public List<News> getNews() {
        try{
            String hql="from News n order by n.infotime asc ";
//            List<News> newsList=(List<News>)hibernateTemplate.find(hql);
            List<News> newsList=hibernateTemplate.loadAll(News.class);
            if (!newsList.isEmpty())
                return newsList;
            return null;
        }catch (DataAccessException e){
            logger.info("系统错误！获取新闻信息失败！");
            return null;
        }
    }
}
