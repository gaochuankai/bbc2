package com.bbc.node.service.impl;

import java.util.HashMap;
import java.util.List;

import com.bbc.node.dao.NodeTypeDao;
import com.bbc.node.dao.impl.NodeTypeDaoImpl;
import com.bbc.node.entity.NodeTypeEntity;
import com.bbc.node.service.NodeTypeService;

public class NodeTypeServiceImpl implements NodeTypeService {

	NodeTypeDao nodeTypeDao = new NodeTypeDaoImpl();

	/**
	 * 获取全部的list
	 */
	public List<NodeTypeEntity> findAllList() {
		return nodeTypeDao.findList(new HashMap<String, Object>());
	}

}
