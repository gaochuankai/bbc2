package com.bbc.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.bbc.system.entity.UserEntity;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1279451197585077299L;

	/**
	 * id
	 */
	protected Long id;

	/**
	 * uuid
	 */
	protected String uuid = UUID.randomUUID().toString();;

	/**
	 * 创建时间
	 */
	protected Date createDate;

	/**
	 * 创建人
	 */
	protected Long createId;

	/**
	 * 修改时间
	 */
	protected Date updateDate;

	/**
	 * 修改人
	 */
	protected Long updateId;

	/**
	 * 创建人信息
	 */
	private UserEntity createUserEntity;

	/**
	 * 修改人信息
	 */
	private UserEntity updateUserEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public UserEntity getCreateUserEntity() {
		return createUserEntity;
	}

	public void setCreateUserEntity(UserEntity createUserEntity) {
		this.createUserEntity = createUserEntity;
	}

	public UserEntity getUpdateUserEntity() {
		return updateUserEntity;
	}

	public void setUpdateUserEntity(UserEntity updateUserEntity) {
		this.updateUserEntity = updateUserEntity;
	}

}
