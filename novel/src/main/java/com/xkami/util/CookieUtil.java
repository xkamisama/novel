package com.xkami.util;

import javax.servlet.http.Cookie;

/**
 * @author Administrator
 *	cookie工具类
 */
public class CookieUtil {
	private static final String path = "/";
	/**
	 * 设置cookie的参数并返回Cookie对象
	 * @param key
	 * @param value
	 * @param days
	 * @return
	 */
	public static Cookie setCookie(String key,String value,int days) {
		Cookie cookie =new Cookie(key,value);
		cookie.setMaxAge(60*60*24*days);
		cookie.setPath(path);
		return cookie;
	}
    
}

