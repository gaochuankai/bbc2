package com.bbc.system.service.impl;

import java.util.List;
import java.util.Map;

import com.bbc.base.utils.BaseUtils;
import com.bbc.system.dao.UserDao;
import com.bbc.system.dao.impl.UserDaoImpl;
import com.bbc.system.entity.UserEntity;
import com.bbc.system.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	public UserEntity logSave(UserEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Object, Object> logSaveMap(UserEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity noLogSave(UserEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> noLogSaveMap(UserEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserEntity> nologSaveList(List<UserEntity> t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public void delete(UserEntity t) {
		// TODO Auto-generated method stub

	}

	public void delete(List<UserEntity> t) {
		// TODO Auto-generated method stub

	}

	public UserEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> findOneMap(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity findOneByParams(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 依据map中的信息进行查询
	 */
	public List<UserEntity> findByParamsList(Map<String, Object> searchParams) {
		return userDao.findList(searchParams);
	}

	public Long count(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 注册方法
	 */
	public Map<String, Object> register(UserEntity userEntity) {
		Map<String, Object> result = userDao
				.findOneMapByLoginName(userEntity.getLoginName());
		// 如果查得到userentity数据，则表示该用户已经存在！
		if ((Boolean) result.get("success")) {
			result.put("errorMsg", "该用户名已存在！");
		} else {
			result = userDao.saveMap(userEntity);
		}
		return result;
	}

	/**
	 * 登录操作
	 */
	public Map<String, Object> login(UserEntity userEntity) {
		Map<String, Object> result = userDao
				.findOneByLoginNameAndPassword(userEntity);
		userEntity = (UserEntity) result.get(BaseUtils.SYSTEM_MAP_ENTITY);
		if (userEntity == null) {
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG, "未查询到用户信息！");
		} else {
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, true);
		}
		// 插入用户登录表信息
//		if() {
//			
//		}
		return result;
	}

	/**
	 * 用户登出系统
	 */
	public void logout(UserEntity userEntity) {
		
	}

}
