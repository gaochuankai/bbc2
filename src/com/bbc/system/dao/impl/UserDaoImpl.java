package com.bbc.system.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bbc.base.utils.BaseUtils;
import com.bbc.base.utils.JDBC_CPUtiles;
import com.bbc.system.dao.UserDao;
import com.bbc.system.entity.UserEntity;

public class UserDaoImpl implements UserDao {

	private QueryRunner queryRunner = JDBC_CPUtiles.getQueryRunner();

	private String insertSql = "insert into system_user "
			+ "(id,uuid,name,password,wechat,email,tellphone,head_img,brithday,login_name,create_date,create_id,update_date,update_id)"
			+ " value (null,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public UserEntity save(UserEntity t) {
		// TODO:��ΰѸող�������ݷ��س�����
		try {
			int count = queryRunner.update(insertSql, t.getUuid(),
					t.getPassword(), t.getWechat(), t.getEmail(),
					t.getTellphone(), t.getHeadImg(), t.getBrithday(),
					t.getLoginName(), t.getCreateDate(), t.getCreateId(),
					t.getUpdateDate(), t.getUpdateId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * ����map
	 */
	public Map<String, Object> saveMap(UserEntity t) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		try {
			int count = queryRunner.update(insertSql, t.getUuid(),
					t.getPassword(), t.getWechat(), t.getEmail(),
					t.getTellphone(), t.getHeadImg(), t.getBrithday(),
					t.getLoginName(), t.getCreateDate(), t.getCreateId(),
					t.getUpdateDate(), t.getUpdateId());
			if (count == 1) {
				result.put("success", true);
			}
		} catch (SQLException e) {
			result.put("errorMsg", "����ʱ����");
			return result;
		}
		return result;
	}

	public Iterable<UserEntity> save(Iterable<UserEntity> t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(UserEntity t) {
		// TODO Auto-generated method stub

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<UserEntity> t) {
		// TODO Auto-generated method stub

	}

	public UserEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity findOneByUuid(String uuid) {
		return null;
	}

	public UserEntity findOneByMap(Map<String, Object> map) {
		UserEntity userEntity = new UserEntity();
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		String sql = "select * from system_user where login_name = ?";
		return userEntity;
	}

	/**
	 * ͨ��map����һ�в�����list
	 */
	public List<UserEntity> findList(Map<String, Object> map) {
		// TODO ����map�еļ�ֵ��
		List<UserEntity> result = new ArrayList<UserEntity>();
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		String sql = "select * from system_user";
		try {
			result = queryRunner.query(sql,
					new BeanListHandler<UserEntity>(UserEntity.class));
		} catch (SQLException e) {
			return null;
		}
		return result;
	}

	/**
	 * ͨ��loginName����һ��entity������entity
	 */
	public UserEntity findOneByLoginName(String loginName) {
		UserEntity userEntity = new UserEntity();
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		String sql = "select * from system_user where login_name = ?";
		try {
			userEntity = queryRunner.query(sql,
					new BeanHandler<UserEntity>(UserEntity.class));
		} catch (SQLException e) {
			throw new RuntimeException("ͨ��loginName����UserEntityʱ����");
		}
		return userEntity;
	}

	/**
	 * ͨ��loginName����һ��entity������map
	 */
	public Map<String, Object> findOneMapByLoginName(String loginName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		UserEntity userEntity = new UserEntity();
		String sql = "select * from system where login_name = ?";
		try {
			userEntity = queryRunner.query(sql,
					new BeanHandler<UserEntity>(UserEntity.class));
			result.put("success", true);
			result.put("entity", userEntity);
		} catch (SQLException e) {
			result.put("errorMsg", "����loginName��ѯUserEntityʱ����");
			return result;
		}
		return result;
	}

	/**
	 * ���ݵ�¼���������ѯ�û�
	 */
	public Map<String, Object> findOneByLoginNameAndPassword(
			UserEntity userEntity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);
		String sql = "select * from system_user where login_name = ? and password = ?";
		try {
			userEntity = queryRunner.query(sql,
					new BeanHandler<UserEntity>(UserEntity.class),
					userEntity.getLoginName(), userEntity.getPassword());
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, true);
			result.put(BaseUtils.SYSTEM_MAP_ENTITY, userEntity);
		} catch (SQLException e) {
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG,
					"����loginname��password��ѯ����");
		}
		return result;
	}

	/**
	 * userentity���Բ��˷���
	 */
	public UserEntity improveEntity(UserEntity t) {
		return null;
	}

}
