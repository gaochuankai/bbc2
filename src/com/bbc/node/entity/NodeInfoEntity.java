package com.bbc.node.entity;

import com.bbc.base.entity.BaseEntity;

/***
 * ���ӵ���Ҫ��Ϣ
 * 
 * @author gck
 *
 */
public class NodeInfoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110432491975670559L;

	/**
	 * ����
	 */
	private String title;

	/**
	 * ����
	 */
	private String context;

	/**
	 * ����id
	 */
	private Long nodeTypeId;

	/**
	 * ����״̬���ݸ壬��������ɾ����ֹ�ظ�
	 */
	private Integer nodeStaus;

	/**
	 * 
	 */
	private Integer deleteStatus;

	/**
	 * �ظ���
	 */
	private Integer replyCount;

	/**
	 * ����������Ϣ
	 */
	private NodeTypeEntity nodeTypeEntity;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getNodeTypeId() {
		return nodeTypeId;
	}

	public void setNodeTypeId(Long nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	public Integer getNodeStaus() {
		return nodeStaus;
	}

	public void setNodeStaus(Integer nodeStaus) {
		this.nodeStaus = nodeStaus;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public NodeTypeEntity getNodeTypeEntity() {
		return nodeTypeEntity;
	}

	public void setNodeTypeEntity(NodeTypeEntity nodeTypeEntity) {
		this.nodeTypeEntity = nodeTypeEntity;
	}

}
