package com.bbc.base.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

public final class LoginContext implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5440801025747214253L;
	/**
	 * �û�id
	 */
	private Long userId;
	/**
	 * �û���
	 */
	private String loginName;
	/**
	 * 
	 */
	private String name;
	/**
	 * ����IP��ַ
	 */
	private String ip;
	/**
	 * ����������Ϣ
	 */
	private Properties properties = new Properties();
	/**
	 * �ͻ��˷�������
	 */
	private String language;
	/**
	 * �ܴa
	 */
	private String password;
	/**
	 * �����ܴa
	 */
	private String reversiblePassword;
	private Date createTime;
	private Date lastAccessedTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReversiblePassword() {
		return reversiblePassword;
	}

	public void setReversiblePassword(String reversiblePassword) {
		this.reversiblePassword = reversiblePassword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(Date lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

}
