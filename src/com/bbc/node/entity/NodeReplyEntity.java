package com.bbc.node.entity;

import com.bbc.base.entity.BaseEntity;

/**
 * ���ӻظ���Ϣ
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
	 * ����node_info_id
	 */
	private Long nodeId;

	/**
	 * ����
	 */
	private String context;

	/**
	 * ¥����
	 */
	private Integer flootNumber;

	/**
	 * ���ڵ�
	 */
	private Long parentId;

	/**
	 * ״̬
	 */
	private Integer deleteStatus;

	/**
	 * ���ӱ���Ϣ
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
