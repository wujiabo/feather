package com.wujiabo.opensource.feather.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getString(HttpServletRequest request, String key) {
		// TODO Auto-generated method stub
		return request.getAttribute(key) == null ? null : request.getAttribute(key).toString();
	}

}
