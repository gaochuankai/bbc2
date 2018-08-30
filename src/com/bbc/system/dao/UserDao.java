package com.bbc.system.dao;

import java.util.Map;

import com.bbc.base.dao.BaseDao;
import com.bbc.system.entity.UserEntity;

public interface UserDao extends BaseDao<UserEntity> {

	/**
	 * ����loginname��ѯһ��ʵ�壬����ʵ��
	 * 
	 * @param loginName
	 * @return
	 */
	UserEntity findOneByLoginName(String loginName);

	/**
	 * ����loginname��ѯһ��ʵ�壬����map
	 * 
	 * @param loginName
	 * @return
	 */
	Map<String, Object> findOneMapByLoginName(String loginName);

	/**
	 * ����loginname��password��ѯһ��ʵ�壬����map
	 * 
	 * @param userEntity
	 * @return
	 */
	Map<String, Object> findOneByLoginNameAndPassword(UserEntity userEntity);

}
