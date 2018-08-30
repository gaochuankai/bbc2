package com.bbc.base.utils;

public final class BaseUtils {

	/**
	 * response中乱码
	 */
	public static final String RESPONSE_CONTENTTYPE = "text/html;charset=utf-8";

	/**
	 * 系统中Map返回值Key
	 */
	public static final String SYSTEM_MAP_SUCCESS = "success";
	public static final String SYSTEM_MAP_ENTITY = "entity";
	public static final String SYSTEM_MAP_ERROR_MSG = "errorMsg";

	/**
	 * dao数据处理报错
	 */
	public static final String DAO_COUNT_ERROR = "COUNT-ERROR";
	public static final String DAO_SAVE_ERROR = "SAVE-ERROR";
	public static final String DAO_DELETE_ERROR = "DELETE-ERROR";
	public static final String DAO_SELECT_ERROR = "SELECT-ERROR";

	/**
	 * 系统中及数据库中常用数字代表的值：0：草稿
	 */
	public static final Integer SYSTEM_DATA_0 = 0;

	/**
	 * 系统中及数据库中常用数字代表的值：1：发布
	 */
	public static final Integer SYSTEM_DATA_1 = 1;

	/**
	 * 系统中及数据库中常用数字代表的值：逻辑删除状态2：未删除
	 */
	public static final Integer SYSTEM_DATA_2 = 2;

	/**
	 * 系统中及数据库中常用数字代表的值：逻辑删除状态3：已删除
	 */
	public static final Integer SYSTEM_DATA_3 = 3;

	/**
	 * 系统中及数据库中常用数字代表的值：4禁止回帖
	 */
	public static final Integer SYSTEM_DATA_4 = 4;

}
