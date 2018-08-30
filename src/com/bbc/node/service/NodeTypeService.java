package com.bbc.node.service;

import java.util.List;

import com.bbc.node.entity.NodeTypeEntity;

public interface NodeTypeService {

	/**
	 * 获取全部的nodetype
	 * 
	 * @return
	 */
	List<NodeTypeEntity> findAllList();

}
