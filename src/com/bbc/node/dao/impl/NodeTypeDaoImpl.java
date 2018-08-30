package com.bbc.node.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bbc.base.utils.BaseUtils;
import com.bbc.base.utils.JDBC_CPUtiles;
import com.bbc.base.utils.SearchFilter;
import com.bbc.node.dao.NodeTypeDao;
import com.bbc.node.entity.NodeTypeEntity;
import com.bbc.system.dao.UserDao;
import com.bbc.system.dao.impl.UserDaoImpl;

public class NodeTypeDaoImpl implements NodeTypeDao {

	private UserDao userDao = new UserDaoImpl();
	private QueryRunner queryRunner = JDBC_CPUtiles.getQueryRunner();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long count() {
		String sql = "select * from node_type";
		try {
			return queryRunner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_COUNT_ERROR);
		}
	}

	/**
	 * ��������NodeTypeEntity����
	 */
	public NodeTypeEntity save(NodeTypeEntity t) {
		int con = 0;
		try {
			String sql = "";
			if (t.getId() != null) {
				sql = "update node_type set type_name = ?, remark = ?, update_id = ?, update_date = ? where id = ?";
				con = queryRunner.update(sql,
						new BeanHandler<NodeTypeEntity>(NodeTypeEntity.class),
						t.getTypeName(), t.getRemark(), t.getUpdateId(),
						t.getUpdateDate(), t.getId());
			} else {
				sql = "insert into node_type (uuid, type_name, remark, create_id, create_date) value (?,?,?,?,?) ";
				if (t.getUuid() == null) {
					t.setUuid(UUID.randomUUID().toString());
				}
				con = queryRunner.update(sql,
						new BeanHandler<NodeTypeEntity>(NodeTypeEntity.class),
						t.getUuid(), t.getTypeName(), t.getRemark(),
						t.getCreateId(), t.getCreateDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SAVE_ERROR);
		}
		NodeTypeEntity entity = null;
		if (con == 1) {
			entity = findOneByUuid(t.getUuid());
		}
		return entity;
	}

	/**
	 * ����һ��NodeTypeEntity���󣬲�����map
	 */
	public Map<String, Object> saveMap(NodeTypeEntity t) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);
		t = save(t);
		if (t == null) {
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG,
					BaseUtils.DAO_SAVE_ERROR);
		} else {
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, true);
			result.put(BaseUtils.SYSTEM_MAP_ENTITY, t);
		}
		return result;
	}

	/**
	 * ���漯��
	 */
	public Iterable<NodeTypeEntity> save(Iterable<NodeTypeEntity> t) {
		// TODO: ����д���Ժ�ѧϰ
		return null;
	}

	/**
	 * ����ʵ��ɾ��
	 */
	public void delete(NodeTypeEntity t) {
		delete(t.getId());
	}

	/**
	 * ����idɾ��
	 */
	public void delete(Long id) {
		String sql = "delete from node_type where id = ?";
		try {
			queryRunner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_DELETE_ERROR);
		}
	}

	/**
	 * ɾ������
	 */
	public void delete(Iterable<NodeTypeEntity> t) {
		// TODO: �����ᣬ����ѧϰ

	}

	/**
	 * ����һ��ʵ��
	 */
	public NodeTypeEntity findOne(Long id) {
		String sql = "select * from node_type where id = ?";
		try {
			NodeTypeEntity entity = queryRunner.query(sql,
					new BeanHandler<NodeTypeEntity>(NodeTypeEntity.class), id);
			return improveEntity(entity);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SELECT_ERROR);
		}
	}

	/**
	 * ͨ��uuid����ʵ��
	 */
	public NodeTypeEntity findOneByUuid(String uuid) {
		String sql = "select * from node_type where uuid = ?";
		try {
			NodeTypeEntity entity = queryRunner.query(sql,
					new BeanHandler<NodeTypeEntity>(NodeTypeEntity.class),
					uuid);
			return improveEntity(entity);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SELECT_ERROR);
		}
	}

	/**
	 * ͨ��map����ʵ��
	 */
	public NodeTypeEntity findOneByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ͨ��map����list---------��Ĭ�ϲ���ȫ��
	 */
	public List<NodeTypeEntity> findList(Map<String, Object> map) {
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		// TODO ����map�еļ�ֵ��
		Map<String, SearchFilter> filters = SearchFilter.parse(map);
		filters.isEmpty();
		String sql = "select * from node_type";
		try {
			List<NodeTypeEntity> list = queryRunner.query(sql,
					new BeanListHandler<NodeTypeEntity>(NodeTypeEntity.class));
			for (NodeTypeEntity t : list) {
				improveEntity(t);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SELECT_ERROR);
		}
	}

	public NodeTypeEntity improveEntity(NodeTypeEntity t) {
		// �ж�createid�Ƿ���ֵ
		if (t.getCreateId() != null) {
			// ��ֵ���UserEntityʵ�����
			t.setCreateUserEntity(userDao.findOne(t.getCreateId()));
		}
		// �ж�updateid�Ƿ���ֵ
		if (t.getUpdateId() != null) {
			// ��ֵ���UserEntityʵ�����
			t.setUpdateUserEntity(userDao.findOne(t.getUpdateId()));
		}
		return t;
	}

}
