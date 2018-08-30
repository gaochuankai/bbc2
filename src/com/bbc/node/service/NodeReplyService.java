package com.bbc.node.service;

import java.util.List;

import com.bbc.base.service.BaseService;
import com.bbc.node.dao.NodeReplyDao;
import com.bbc.node.entity.NodeReplyEntity;

public interface NodeReplyService
		extends BaseService<NodeReplyEntity, NodeReplyDao> {

	/**
	 * ͨ��nodeInfoId��ѯȫ���ظ�
	 * 
	 * @param nodeInfoId
	 * @return
	 */
	List<NodeReplyEntity> findListByNodeInfoId(Long nodeInfoId);

}
