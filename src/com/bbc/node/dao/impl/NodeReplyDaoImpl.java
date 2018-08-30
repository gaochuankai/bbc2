package com.bbc.node.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.bbc.node.dao.NodeReplyDao;
import com.bbc.node.entity.NodeReplyEntity;
import com.bbc.system.dao.UserDao;
import com.bbc.system.dao.impl.UserDaoImpl;

public class NodeReplyDaoImpl implements NodeReplyDao {

	private UserDao userDao = new UserDaoImpl();
	private NodeInfoDao nodeInfoDao = new NodeInfoDaoImpl();
	private QueryRunner queryRunner = JDBC_CPUtiles.getQueryRunner();
	private QueryRunner queryRunnerTransaction = new QueryRunner();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long count() {
		String sql = "select * from node_reply";
		try {
			return queryRunner.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 单个保存NodeTypeEntity对象
	 */
	public NodeReplyEntity save(NodeReplyEntity t) {
		int con = 0;
		try {
			String sql = "";
			if (t.getId() != null) {
				sql = "update node_reply set node_id = ?, context = ?, floot_number = ?,"
						+ "parent_id = ?, delete_status = ?, update_id = ?, update_date = ? where id = ?";
				con = queryRunner.update(sql,
						new BeanHandler<NodeReplyEntity>(NodeReplyEntity.class),
						t.getNodeId(), t.getContext(), t.getFlootNumber(),
						t.getParentId(), t.getDeleteStatus(), t.getUpdateId(),
						t.getUpdateDate(), t.getId());
			} else {
				sql = "insert into node_reply (uuid, node_id, context, floot_number, parent_id, "
						+ "delete_status, create_id, create_date) value (?,?,?,?,?,?,?,?) ";
				if (t.getUuid() == null) {
					t.setUuid(UUID.randomUUID().toString());
				}
				con = queryRunner.update(sql,
						new BeanHandler<NodeReplyEntity>(NodeReplyEntity.class),
						t.getUuid(), t.getNodeId(), t.getContext(),
						t.getFlootNumber(), t.getParentId(),
						t.getDeleteStatus(), t.getCreateId(),
						t.getCreateDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		NodeReplyEntity entity = null;
		if (con == 1) {
			entity = findOneByUuid(t.getUuid());
		}
		return entity;
	}

	/**
	 * 单个保存NodeTypeEntity对象，使用了事务
	 */
	public NodeReplyEntity save(NodeReplyEntity t, Connection connection) {
		int con = 0;
		try {
			String sql = "";
			if (t.getId() != null) {
				sql = "update node_reply set node_id = ?, context = ?, floot_number = ?,"
						+ "parent_id = ?, delete_status = ?, update_id = ?, update_date = ? where id = ?";
				con = queryRunnerTransaction.update(connection, sql,
						t.getNodeId(), t.getContext(), t.getFlootNumber(),
						t.getParentId(), t.getDeleteStatus(), t.getUpdateId(),
						t.getUpdateDate(), t.getId());
			} else {
				sql = "insert into node_reply (uuid, node_id, context, floot_number, parent_id, "
						+ "delete_status, create_id, create_date) value (?,?,?,?,?,?,?,?) ";
				if (t.getUuid() == null) {
					t.setUuid(UUID.randomUUID().toString());
				}
				con = queryRunnerTransaction.update(connection, sql,
						t.getUuid(), t.getNodeId(), t.getContext(),
						t.getFlootNumber(), t.getParentId(),
						t.getDeleteStatus(), t.getCreateId(),
						t.getCreateDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		NodeReplyEntity entity = null;
		if (con == 1) {
			entity = findOneByUuid(t.getUuid());
		}
		return entity;
	}

	/**
	 * 保存一个NodeTypeEntity对象，并返回map
	 */
	public Map<String, Object> saveMap(NodeReplyEntity t) {
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
	public Iterable<NodeReplyEntity> save(Iterable<NodeReplyEntity> t) {
		// TODO: 不会写，以后学习
		return null;
	}

	/**
	 * 依据实体删除
	 */
	public void delete(NodeReplyEntity t) {
		delete(t.getId());
	}

	/**
	 * 依据id删除
	 */
	public void delete(Long id) {
		String sql = "delete from node_reply where id = ?";
		try {
			queryRunner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除集合
	 */
	public void delete(Iterable<NodeReplyEntity> t) {
		// TODO: 还不会，慢慢学习

	}

	/**
	 * 查找一个实体
	 */
	public NodeReplyEntity findOne(Long id) {
		NodeReplyEntity entity = new NodeReplyEntity();
		String sql = "select * from node_reply where id = ?";
		try {
			entity = queryRunner.query(sql,
					new BeanHandler<NodeReplyEntity>(NodeReplyEntity.class),
					id);
			return improveEntity(entity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 通过uuid查找实体
	 */
	public NodeReplyEntity findOneByUuid(String uuid) {
		NodeReplyEntity entity = new NodeReplyEntity();
		String sql = "select * from node_reply where uuid = ?";
		try {
			entity = queryRunner.query(sql,
					new BeanHandler<NodeReplyEntity>(NodeReplyEntity.class),
					uuid);
			return improveEntity(entity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 通过map查找实体
	 */
	public NodeReplyEntity findOneByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过map查找list---------先默认查找全部
	 */
	public List<NodeReplyEntity> findList(Map<String, Object> map) {
		List<NodeReplyEntity> list = new ArrayList<NodeReplyEntity>();
		queryRunner = new QueryRunner(JDBC_CPUtiles.getComboPooledDataSource());
		// TODO 处理map中的键值对
		Map<String, SearchFilter> filters = SearchFilter.parse(map);
		filters.isEmpty();
		String sql = "select * from node_reply";
		try {
			list = queryRunner.query(sql, new BeanListHandler<NodeReplyEntity>(
					NodeReplyEntity.class));
			for (NodeReplyEntity nodeReplyEntity : list) {
				improveEntity(nodeReplyEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public NodeReplyEntity improveEntity(NodeReplyEntity t) {
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
		// 判断nodeid是否有值
		if (t.getNodeId() != null) {
			// 有值添加NodeInfoEntity实体对象
			t.setNodeInfoEntity(nodeInfoDao.findOne(t.getNodeId()));
		}
		return t;
	}

	public List<NodeReplyEntity> findListByNodeInfoId(Long nodeInfoId) {
		List<NodeReplyEntity> list = new ArrayList<NodeReplyEntity>();
		queryRunner = new QueryRunner(JDBC_CPUtiles.getComboPooledDataSource());
		String sql = "select * from node_reply where node_id = ?";
		try {
			list = queryRunner.query(sql,
					new BeanListHandler<NodeReplyEntity>(NodeReplyEntity.class),
					nodeInfoId);
			for (NodeReplyEntity nodeReplyEntity : list) {
				improveEntity(nodeReplyEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
