package com.bbc.system.service;

import java.util.Map;

import com.bbc.base.service.BaseService;
import com.bbc.system.dao.impl.UserDaoImpl;
import com.bbc.system.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, UserDaoImpl> {

	/**
	 * ע�᷽��
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> register(UserEntity userEntity);

	/**
	 * ��¼����
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> login(UserEntity userEntity);

	/**
	 * �ǳ�����
	 * 
	 * @param userEntity
	 */
	void logout(UserEntity userEntity);

}
