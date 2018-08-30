package com.bbc.node.service.impl;

import java.util.List;
import java.util.Map;

import com.bbc.node.dao.NodeInfoDao;
import com.bbc.node.dao.NodeTypeDao;
import com.bbc.node.dao.impl.NodeInfoDaoImpl;
import com.bbc.node.dao.impl.NodeTypeDaoImpl;
import com.bbc.node.entity.NodeInfoEntity;
import com.bbc.node.service.NodeInfoService;
import com.bbc.system.dao.UserDao;
import com.bbc.system.dao.impl.UserDaoImpl;

public class NodeInfoServiceImpl implements NodeInfoService {

	public NodeInfoDao nodeInfoDao = new NodeInfoDaoImpl();
	public UserDao userDao = new UserDaoImpl();
	public NodeTypeDao nodeTypeDao = new NodeTypeDaoImpl();

	public NodeInfoEntity logSave(NodeInfoEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Object, Object> logSaveMap(NodeInfoEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeInfoEntity noLogSave(NodeInfoEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> noLogSaveMap(NodeInfoEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NodeInfoEntity> nologSaveList(List<NodeInfoEntity> t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	public void delete(NodeInfoEntity t) {
		// TODO Auto-generated method stub

	}

	public void delete(List<NodeInfoEntity> t) {
		// TODO Auto-generated method stub

	}

	public NodeInfoEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> findOneMap(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public NodeInfoEntity findOneByParams(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NodeInfoEntity> findByParamsList(
			Map<String, Object> searchParams) {
		return nodeInfoDao.findList(searchParams);
	}

	public Long count(Map<String, Object> searchParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
