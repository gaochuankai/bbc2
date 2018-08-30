package com.bbc.system.entity;

import java.util.Date;

import com.bbc.base.entity.BaseEntity;

public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836293400262374099L;

	/**
	 * ��¼��
	 */
	private String loginName;

	/**
	 * �ǳ�
	 */
	private String name;

	/**
	 * ����
	 */
	private String password;

	/**
	 * ΢��
	 */
	private String wechat;

	/**
	 * ����
	 */
	private String email;

	/**
	 * �绰
	 */
	private String tellphone;

	/**
	 * ͷ��
	 */
	private String headImg;

	/**
	 * ����
	 */
	private Date brithday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTellphone() {
		return tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	@Override
//	public String toString() {
//		return "UserEntity [id=" + id + ", uuid=" + uuid + ", loginName="
//				+ loginName + ", name=" + name + ", password=" + password
//				+ ", wechat=" + wechat + ", email=" + email + ", tellphone="
//				+ tellphone + ", headImg=" + headImg + ", brithday=" + brithday
//				+ "]";
//	}

}
