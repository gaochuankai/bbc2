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
	 * 单个保存NodeTypeEntity对象
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
	 * 保存一个NodeTypeEntity对象，并返回map
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
	 * 保存集合
	 */
	public Iterable<NodeTypeEntity> save(Iterable<NodeTypeEntity> t) {
		// TODO: 不会写，以后学习
		return null;
	}

	/**
	 * 依据实体删除
	 */
	public void delete(NodeTypeEntity t) {
		delete(t.getId());
	}

	/**
	 * 依据id删除
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
	 * 删除集合
	 */
	public void delete(Iterable<NodeTypeEntity> t) {
		// TODO: 还不会，慢慢学习

	}

	/**
	 * 查找一个实体
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
	 * 通过uuid查找实体
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
	 * 通过map查找实体
	 */
	public NodeTypeEntity findOneByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过map查找list---------先默认查找全部
	 */
	public List<NodeTypeEntity> findList(Map<String, Object> map) {
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		// TODO 处理map中的键值对
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
		// 判断createid是否有值
		if (t.getCreateId() != null) {
			// 有值添加UserEntity实体对象
			t.setCreateUserEntity(userDao.findOne(t.getCreateId()));
		}
		// 判断updateid是否有值
		if (t.getUpdateId() != null) {
			// 有值添加UserEntity实体对象
			t.setUpdateUserEntity(userDao.findOne(t.getUpdateId()));
		}
		return t;
	}

}
