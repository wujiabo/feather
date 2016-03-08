package com.wujiabo.opensource.feather.util;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RequestUtil {

	public static String getAttrString(ServletRequest request, String key, String defualtValue) {
		return request.getAttribute(key) == null ? defualtValue : request.getAttribute(key).toString();
	}

	public static String getParamString(ServletRequest request, String key, String defualtValue) {
		return StringUtils.isBlank(request.getParameter(key)) ? defualtValue : request.getParameter(key).toString();
	}
}
