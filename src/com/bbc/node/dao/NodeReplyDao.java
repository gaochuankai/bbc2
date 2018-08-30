package com.bbc.node.dao;

import java.sql.Connection;
import java.util.List;

import com.bbc.base.dao.BaseDao;
import com.bbc.node.entity.NodeReplyEntity;

public interface NodeReplyDao extends BaseDao<NodeReplyEntity> {

	/**
	 * 
	 * @param nodeInfoId
	 * @return
	 */
	List<NodeReplyEntity> findListByNodeInfoId(Long nodeInfoId);

	/**
	 * ��������ı��淽��
	 * 
	 * @param t
	 * @param connection
	 * @return
	 */
	NodeReplyEntity save(NodeReplyEntity t, Connection connection);

}
