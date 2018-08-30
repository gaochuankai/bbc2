package com.bbc.node.entity;

import com.bbc.base.entity.BaseEntity;

/**
 * 帖子类型信息
 * 
 * @author gck
 *
 */
public class NodeTypeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5512153079354168368L;

	/**
	 * 类型名称
	 */
	private String typeName;

	/**
	 * 备注
	 */
	private String remark;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
