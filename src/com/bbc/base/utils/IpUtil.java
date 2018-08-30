package com.bbc.base.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(IpUtil.class);
	private static final String[] HEADERS = new String[] { "x-forwarded-for",
			"HEADER_X_FORWARDED_FOR", "Proxy-Client-IP", "WL-Proxy-Client-IP" };
	private static final Pattern pattern = Pattern
			.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");

	public static boolean isValidIpByCas(String ip) {
		return pattern.matcher(ip).matches();
	}
	
	public static String getIpByDomainName(String domainName) {
		try {
			return InetAddress.getByName(domainName).getHostAddress();
		} catch (UnknownHostException e) {
			LOG.error("unknowDomainName---" + domainName, e);
		}
		return null;
	}
	
	/**
	 * ��ȡrequest��ԭʼIP,��ͨ��f5,nginx��
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		for (String header : HEADERS) {
			String ip = request.getHeader(header);
			if (isValidIp(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
	
	private static boolean isValidIp(String ip){
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			return false;
		}
		return true;
   }
}
