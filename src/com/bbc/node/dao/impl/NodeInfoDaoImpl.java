package com.bbc.node.dao.impl;

import java.sql.Connection;
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
import com.bbc.node.dao.NodeInfoDao;
import com.bbc.node.dao.NodeTypeDao;
import com.bbc.node.entity.NodeInfoEntity;
import com.bbc.system.dao.UserDao;
import com.bbc.system.dao.impl.UserDaoImpl;

public class NodeInfoDaoImpl implements NodeInfoDao {

	private UserDao userDao = new UserDaoImpl();
	private NodeTypeDao nodeTypeDao = new NodeTypeDaoImpl();
	private QueryRunner queryRunner = JDBC_CPUtiles.getQueryRunner();
	private QueryRunner queryRunnerTransaction = new QueryRunner();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long count() {
		String sql = "select * from node_info";
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
	public NodeInfoEntity save(NodeInfoEntity t) {
		int con = 0;
		try {
			String sql = "";
			if (t.getId() != null) {
				sql = "update node_info set title = ?, context = ?, node_type_id = ?,"
						+ "node_staus = ?, reply_count = ?, delete_status = ?,"
						+ " update_id = ?, update_date = ? where id = ?";
				con = queryRunner.update(sql,
						new BeanHandler<NodeInfoEntity>(NodeInfoEntity.class),
						t.getTitle(), t.getContext(), t.getNodeTypeId(),
						t.getNodeStaus(), t.getReplyCount(),
						t.getDeleteStatus(), t.getUpdateDate(), t.getId());
			} else {
				sql = "insert into node_info (uuid, title, context, node_type_id, "
						+ "node_staus, reply_count, delete_status, create_id, "
						+ "create_date) value (?,?,?,?,?,?,?,?,?) ";
				if (t.getUuid() == null) {
					t.setUuid(UUID.randomUUID().toString());
				}
				con = queryRunner.update(sql,
						new BeanHandler<NodeInfoEntity>(NodeInfoEntity.class),
						t.getUuid(), t.getTitle(), t.getContext(),
						t.getNodeTypeId(), t.getNodeStaus(), t.getReplyCount(),
						t.getDeleteStatus(), t.getCreateId(),
						t.getCreateDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SAVE_ERROR);
		}
		NodeInfoEntity entity = null;
		if (con == 1) {
			entity = findOneByUuid(t.getUuid());
		}
		return entity;
	}

	/**
	 * 保存一个NodeTypeEntity对象，并返回map
	 */
	public Map<String, Object> saveMap(NodeInfoEntity t) {
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
	public Iterable<NodeInfoEntity> save(Iterable<NodeInfoEntity> t) {
		// TODO: 不会写，以后学习
		return null;
	}

	/**
	 * 依据实体删除
	 */
	public void delete(NodeInfoEntity t) {
		delete(t.getId());
	}

	/**
	 * 依据id删除
	 */
	public void delete(Long id) {
		String sql = "delete from node_info where id = ?";
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
	public void delete(Iterable<NodeInfoEntity> t) {
		// TODO: 还不会，慢慢学习

	}

	/**
	 * 查找一个实体
	 */
	public NodeInfoEntity findOne(Long id) {
		String sql = "select * from node_info where id = ?";
		try {
			NodeInfoEntity entity = queryRunner.query(sql,
					new BeanHandler<NodeInfoEntity>(NodeInfoEntity.class), id);
			return improveEntity(entity);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SELECT_ERROR);
		}
	}

	/**
	 * 通过uuid查找实体
	 */
	public NodeInfoEntity findOneByUuid(String uuid) {
		String sql = "select * from node_info where uuid = ?";
		try {
			NodeInfoEntity entity = queryRunner.query(sql,
					new BeanHandler<NodeInfoEntity>(NodeInfoEntity.class),
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
	public NodeInfoEntity findOneByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过map查找list---------先默认查找全部
	 */
	public List<NodeInfoEntity> findList(Map<String, Object> map) {
		QueryRunner queryRunner = new QueryRunner(
				JDBC_CPUtiles.getComboPooledDataSource());
		// TODO 处理map中的键值对
		Map<String, SearchFilter> filters = SearchFilter.parse(map);
		filters.isEmpty();
		String sql = "select * from node_info";
		try {
			List<NodeInfoEntity> list = queryRunner.query(sql,
					new BeanListHandler<NodeInfoEntity>(NodeInfoEntity.class));
			for (NodeInfoEntity entity : list) {
				improveEntity(entity);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(BaseUtils.DAO_SELECT_ERROR);
		}
	}

	/**
	 * 完善信息，添加UserEntity、NodeTypeEntity
	 */
	public NodeInfoEntity improveEntity(NodeInfoEntity t) {
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
		// 判断typeid是否有值
		if (t.getNodeTypeId() != null) {
			// 有值添加NodeTypeEntity实体对象
			t.setNodeTypeEntity(nodeTypeDao.findOne(t.getNodeTypeId()));
		}
		return t;
	}

	/**
	 * 更新ReplyCount
	 */
	public void updateReplyCount(Integer num, Long id) {
		String sql = "update node_info set reply_count = reply_count + ? where id = ?";
		try {
			queryRunner.update(sql,
					new BeanHandler<NodeInfoEntity>(NodeInfoEntity.class), num,
					id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新ReplyCount
	 */
	public void updateReplyCount(Integer num, Long id, Connection connection) {
		String sql = "update node_info set reply_count = reply_count + ? where id = ?";
		try {
			queryRunnerTransaction.update(connection, sql, num, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
