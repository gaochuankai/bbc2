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
	 * ��������NodeTypeEntity����
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
	 * ��������NodeTypeEntity����ʹ��������
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
	 * ����һ��NodeTypeEntity���󣬲�����map
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
	 * ���漯��
	 */
	public Iterable<NodeReplyEntity> save(Iterable<NodeReplyEntity> t) {
		// TODO: ����д���Ժ�ѧϰ
		return null;
	}

	/**
	 * ����ʵ��ɾ��
	 */
	public void delete(NodeReplyEntity t) {
		delete(t.getId());
	}

	/**
	 * ����idɾ��
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
	 * ɾ������
	 */
	public void delete(Iterable<NodeReplyEntity> t) {
		// TODO: �����ᣬ����ѧϰ

	}

	/**
	 * ����һ��ʵ��
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
	 * ͨ��uuid����ʵ��
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
	 * ͨ��map����ʵ��
	 */
	public NodeReplyEntity findOneByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ͨ��map����list---------��Ĭ�ϲ���ȫ��
	 */
	public List<NodeReplyEntity> findList(Map<String, Object> map) {
		List<NodeReplyEntity> list = new ArrayList<NodeReplyEntity>();
		queryRunner = new QueryRunner(JDBC_CPUtiles.getComboPooledDataSource());
		// TODO ����map�еļ�ֵ��
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
		// �ж�nodeid�Ƿ���ֵ
		if (t.getNodeId() != null) {
			// ��ֵ���NodeInfoEntityʵ�����
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
