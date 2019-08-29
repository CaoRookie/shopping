package com.shopping.dao.impl;

import com.shopping.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public int save(T o) {
		try{
		    //save返回对应的主键值。
			hibernateTemplate.save(o);
			return 1;
		}catch (DataAccessException e){
			return 0;
		}
	}

	public void saveList(List<T> l) {

	}

	public int delete(T o) {
        try{
            hibernateTemplate.delete(o);
            return 1;
        }catch (DataAccessException e){
            return 0;
        }
	}

	public int update(T o) {
        try{
            hibernateTemplate.update(o);
            return 1;
        }catch (DataAccessException e){
            return 0;
        }
	}

	public int saveOrUpdate(T o) {
        try{
            hibernateTemplate.saveOrUpdate(o);
            return 1;
        }catch (DataAccessException e){
            return 0;
        }
	}

	public T getById(Class<T> c,Serializable id) {
		return hibernateTemplate.get(c,id);
	}

	public T getByHql(String hql) {
		return null;
	}

	public T getByHql(String hql, Map<String, Object> params) {
		return null;
	}

	public List<T> find() {
		return null;
	}

	public List<T> find(String hql) {
		return null;
	}

	public List<T> find(String hql, Map<String, Object> params) {
		return null;
	}

	public List<T> find(String hql, int page, int rows) {
		return null;
	}

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		return null;
	}

	public List<T> find(int page, int rows) {
		return null;
	}

	public Long count(String hql) {
		return null;
	}

	public Long count(String hql, Map<String, Object> params) {
		return null;
	}

	public Long count() {
		return null;
	}

	public int executeHql(String hql) {
		return 0;
	}

	public int executeHql(String hql, Map<String, Object> params) {
		return 0;
	}

	public List findColumnBySql(String sql) {
		return null;
	}

	public List findBySql(String sql) {
		return null;
	}

	public List findBySql(String sql, int page, int rows) {
		return null;
	}

	public List findBySql(String sql, Map<String, Object> params) {
		return null;
	}

	public List findBySql(String sql, Map<String, Object> params, int page, int rows) {
		return null;
	}

	public int executeSql(String sql) {
		return 0;
	}

	public int executeSql(String sql, Map<String, Object> params) {
		return 0;
	}

	public Long countBySql(String sql) {
		return null;
	}

	public Long countBySql(String sql, Map<String, Object> params) {
		return null;
	}

	public String getBySql(String sql) {
		return null;
	}
}
