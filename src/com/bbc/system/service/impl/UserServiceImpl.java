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
	 * ����map�е���Ϣ���в�ѯ
	 */
	public List<UserEntity> findByParamsList(Map<String, Object> searchParams) {
		return userDao.findList(searchParams);
	}

	public Long count(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ע�᷽��
	 */
	public Map<String, Object> register(UserEntity userEntity) {
		Map<String, Object> result = userDao
				.findOneMapByLoginName(userEntity.getLoginName());
		// �����õ�userentity���ݣ����ʾ���û��Ѿ����ڣ�
		if ((Boolean) result.get("success")) {
			result.put("errorMsg", "���û����Ѵ��ڣ�");
		} else {
			result = userDao.saveMap(userEntity);
		}
		return result;
	}

	/**
	 * ��¼����
	 */
	public Map<String, Object> login(UserEntity userEntity) {
		Map<String, Object> result = userDao
				.findOneByLoginNameAndPassword(userEntity);
		userEntity = (UserEntity) result.get(BaseUtils.SYSTEM_MAP_ENTITY);
		if (userEntity == null) {
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG, "δ��ѯ���û���Ϣ��");
		} else {
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, true);
		}
		// �����û���¼����Ϣ
//		if() {
//			
//		}
		return result;
	}

	/**
	 * �û��ǳ�ϵͳ
	 */
	public void logout(UserEntity userEntity) {
		
	}

}
