package com.bbc.node.service;

import java.util.List;

import com.bbc.node.entity.NodeTypeEntity;

public interface NodeTypeService {

	/**
	 * ��ȡȫ����nodetype
	 * 
	 * @return
	 */
	List<NodeTypeEntity> findAllList();

}
