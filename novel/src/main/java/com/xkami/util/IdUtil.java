package com.xkami.util;

import java.util.UUID;

/**
 * @author Administrator
 *	id生成工具类
 */
public abstract class IdUtil {
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
