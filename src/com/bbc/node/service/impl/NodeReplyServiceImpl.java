package com.bbc.node.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbc.base.utils.BaseUtils;
import com.bbc.base.utils.JDBC_CPUtiles;
import com.bbc.node.dao.NodeInfoDao;
import com.bbc.node.dao.NodeReplyDao;
import com.bbc.node.dao.impl.NodeInfoDaoImpl;
import com.bbc.node.dao.impl.NodeReplyDaoImpl;
import com.bbc.node.entity.NodeReplyEntity;
import com.bbc.node.service.NodeReplyService;

public class NodeReplyServiceImpl implements NodeReplyService {

	private NodeReplyDao nodeReplyDao = new NodeReplyDaoImpl();
	private NodeInfoDao nodeInfoDao = new NodeInfoDaoImpl();

	public NodeReplyEntity logSave(NodeReplyEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Object, Object> logSaveMap(NodeReplyEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeReplyEntity noLogSave(NodeReplyEntity t) {
		Connection connection = null;
		try {
			connection = JDBC_CPUtiles.getConnection();
			connection.setAutoCommit(false);
			t = nodeReplyDao.save(t, connection);
			nodeInfoDao.updateReplyCount(1, t.getNodeId(), connection);
			connection.commit();
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	public Map<String, Object> noLogSaveMap(NodeReplyEntity t) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);
		Connection connection = null;
		try {
			connection = JDBC_CPUtiles.getConnection();
			connection.setAutoCommit(false);
			t = nodeReplyDao.save(t);
			nodeInfoDao.updateReplyCount(1, t.getNodeId());
			connection.commit();
			result.put(BaseUtils.SYSTEM_MAP_SUCCESS, true);
			result.put(BaseUtils.SYSTEM_MAP_ENTITY, t);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public List<NodeReplyEntity> nologSaveList(List<NodeReplyEntity> t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public void delete(NodeReplyEntity t) {
		// TODO Auto-generated method stub

	}

	public void delete(List<NodeReplyEntity> t) {
		// TODO Auto-generated method stub

	}

	public NodeReplyEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> findOneMap(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeReplyEntity findOneByParams(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NodeReplyEntity> findByParamsList(
			Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long count(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public List<NodeReplyEntity> findListByNodeInfoId(Long nodeInfoId) {
		return nodeReplyDao.findListByNodeInfoId(nodeInfoId);
	}

}
