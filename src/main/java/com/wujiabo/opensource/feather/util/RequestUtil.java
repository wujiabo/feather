package com.wujiabo.opensource.feather.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getString(HttpServletRequest request, String key, String defualtValue) {
		return request.getAttribute(key) == null ? defualtValue : request.getAttribute(key).toString();
	}

}
