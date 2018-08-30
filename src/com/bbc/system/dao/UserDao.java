package com.bbc.system.dao;

import java.util.Map;

import com.bbc.base.dao.BaseDao;
import com.bbc.system.entity.UserEntity;

public interface UserDao extends BaseDao<UserEntity> {

	/**
	 * 依据loginname查询一个实体，返回实体
	 * 
	 * @param loginName
	 * @return
	 */
	UserEntity findOneByLoginName(String loginName);

	/**
	 * 依据loginname查询一个实体，返回map
	 * 
	 * @param loginName
	 * @return
	 */
	Map<String, Object> findOneMapByLoginName(String loginName);

	/**
	 * 依据loginname和password查询一个实体，返回map
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> findOneByLoginNameAndPassword(UserEntity userEntity);

}
