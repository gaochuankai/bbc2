package com.bbc.system.service;

import java.util.Map;

import com.bbc.base.service.BaseService;
import com.bbc.system.dao.impl.UserDaoImpl;
import com.bbc.system.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, UserDaoImpl> {

	/**
	 * 注册方法
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> register(UserEntity userEntity);

	/**
	 * 登录方法
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> login(UserEntity userEntity);

	/**
	 * 登出方法
	 * 
	 * @param userEntity
	 */
	void logout(UserEntity userEntity);

}
