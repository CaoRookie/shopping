package com.shopping.service.impl;

import com.shopping.dao.BaseDao;
import com.shopping.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 基础业务逻辑
 * 
 * @author 
 * 
 * @param <T>
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	public int save(T o) {
		return baseDao.save(o);
	}

	public void saveList(List<T> l) {

	}

	public int delete(T o) {
		return baseDao.delete(o);
	}

	public int update(T o) {
		return baseDao.update(o);
	}

	public int saveOrUpdate(T o) {
		return baseDao.saveOrUpdate(o);
	}

	public T getById(Class<T> c,Serializable id) {
		return baseDao.getById(c,id);
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
