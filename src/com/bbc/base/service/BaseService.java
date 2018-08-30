package com.bbc.base.service;

import java.util.List;
import java.util.Map;

import com.bbc.base.dao.BaseDao;
import com.bbc.base.entity.BaseEntity;

public interface BaseService<T extends BaseEntity, DAO extends BaseDao<T>> {

	/**
	 * save ,and save log
	 * 
	 * @param t
	 * @return
	 */
	T logSave(T t);

	Map<Object, Object> logSaveMap(T t);

	/**
	 * 
	 * @param t
	 * @return
	 */
	T noLogSave(T t);

	Map<String, Object> noLogSaveMap(T t);

	List<T> nologSaveList(List<T> t);

	/**
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 
	 * @param t
	 */
	void delete(T t);

	/**
	 * 
	 * @param List <T>
	 */
	void delete(List<T> t);

	/**
	 * 
	 * @param id
	 * @return T
	 */
	T findOne(Long id);

	/**
	 * 
	 * @param id
	 * @return T
	 */
	Map<String, Object> findOneMap(Long id);

	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	T findOneByParams(Map<String, Object> searchParams);

	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	List<T> findByParamsList(Map<String, Object> searchParams);

	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	Long count(Map<String, Object> searchParams);

}
