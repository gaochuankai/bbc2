package com.bbc.node.entity;

import com.bbc.base.entity.BaseEntity;

/**
 * 帖子回复信息
 * 
 * @author gck
 *
 */
public class NodeReplyEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1655775316345826502L;

	/**
	 * 主表node_info_id
	 */
	private Long nodeId;

	/**
	 * 内容
	 */
	private String context;

	/**
	 * 楼层数
	 */
	private Integer flootNumber;

	/**
	 * 父节点
	 */
	private Long parentId;

	/**
	 * 状态
	 */
	private Integer deleteStatus;

	/**
	 * 帖子表信息
	 */
	private NodeInfoEntity nodeInfoEntity;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getFlootNumber() {
		return flootNumber;
	}

	public void setFlootNumber(Integer flootNumber) {
		this.flootNumber = flootNumber;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public NodeInfoEntity getNodeInfoEntity() {
		return nodeInfoEntity;
	}

	public void setNodeInfoEntity(NodeInfoEntity nodeInfoEntity) {
		this.nodeInfoEntity = nodeInfoEntity;
	}

}
