package com.bbc.node.dao;

import java.sql.Connection;

import com.bbc.base.dao.BaseDao;
import com.bbc.node.entity.NodeInfoEntity;

public interface NodeInfoDao extends BaseDao<NodeInfoEntity> {

	/**
	 * ����replyCount����ֵ
	 * 
	 * @param num
	 * @param id
	 */
	void updateReplyCount(Integer num, Long id);

	void updateReplyCount(Integer i, Long nodeId, Connection connection);

}
