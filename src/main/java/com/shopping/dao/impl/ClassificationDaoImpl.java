package com.shopping.dao.impl;

import com.shopping.dao.ClassificationDao;
import com.shopping.domain.Product;
import com.shopping.domain.ProductType;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClassificationDaoImpl implements ClassificationDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductType> getProTypeList() {
        List<ProductType>list=hibernateTemplate.loadAll(ProductType.class);
        if(!list.isEmpty())
            return list;
        return null;
    }

    public List<Product> getProductByType(int productType) {
        String hql="from Product p where p.product_type=?";
        List<Product> productList=( List<Product>)hibernateTemplate.find(hql,productType);
        return productList;
    }

    public List<Map<String, Object>> getProductBySreach(String sreach) {
        final StringBuilder sb=new StringBuilder();
        sb.append("SELECT DISTINCT p.* FROM product p LEFT JOIN product_type pt on p.product_type=pt.product_type_id WHERE pt.product_type_name LIKE '%"+sreach+"%'");
        sb.append(" UNION");
        sb.append(" SELECT  p.* FROM product p WHERE p.`name` LIKE '%"+sreach+"%'");
        /*return hibernateTemplate.execute(new HibernateCallback<List<Map<String, Object>>>() {
            public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
                SQLQuery query=session.createSQLQuery(sb.toString());
                return query.list();
            }
        });*/
        return jdbcTemplate.queryForList(sb.toString());
    }
}