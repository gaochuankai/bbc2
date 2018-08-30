package com.bbc.base.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SearchFilter {
	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, NEQ, IN, NOTIN, ISNULL, ISNOTNULL
	}

	public String fieldName;
	public Object value;
	public Operator operator;
	public String conditionGroup;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * 
	 * @param fieldName      �ֶ�����
	 * @param value          �ֶ�ֵ
	 * @param operator       EQ|LIKE|GT|LT|GTE|LTE|NEQ|IN
	 * @param conditionGroup ��������KEY ���������������ѯ(A and B) or (A and C)
	 */
	public SearchFilter(String fieldName, Operator operator, Object value,
			String conditionGroup) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.conditionGroup = conditionGroup;
	}

	/**
	 * searchParams��key�ĸ�ʽΪOPERATOR_FIELDNAME
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, SearchFilter> parse(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new ConcurrentHashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// ���˵���ֵ
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank(ObjectUtils.toString(value))) {
				continue;
			}

			// ���operator��filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2 && names.length != 3) {
				throw new IllegalArgumentException(
						key + " is not a valid search filter name");
			}
			Operator operator = Operator.valueOf(names[0]);
			String filedName = names[1];
			String conditionGroup = null;
			if (names.length == 3)
				conditionGroup = names[2];
			// ����searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value,
					conditionGroup);
			filters.put(key, filter);
		}

		return filters;
	}
}
