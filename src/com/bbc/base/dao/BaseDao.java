package com.bbc.base.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	long count();

	/**
	 * insert entity, return entity
	 * 
	 * @param t
	 * @return
	 */
	T save(T t);

	Map<String, Object> saveMap(T t);

	Iterable<T> save(Iterable<T> t);

	/**
	 * delete entity
	 * 
	 * @param t
	 */
	void delete(T t);

	/**
	 * delete by id
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * delete list
	 * 
	 * @param t
	 */
	void delete(Iterable<T> t);

	/**
	 * select one where id
	 * 
	 * @param id
	 * @return
	 */
	T findOne(Long id);

	/**
	 * select one where uuid
	 * 
	 * @param uuid
	 * @return
	 */
	T findOneByUuid(String uuid);

	/**
	 * select one where more parameter
	 * 
	 * @param map
	 * @return
	 */
	T findOneByMap(Map<String, Object> map);

	/**
	 * 
	 * @param map
	 * @return
	 */
	List<T> findList(Map<String, Object> map);

	/**
	 * 完善实体类型信息
	 * 
	 * @return
	 */
	T improveEntity(T t);

}
