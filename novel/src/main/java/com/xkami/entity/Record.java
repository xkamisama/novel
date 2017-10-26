package com.xkami.entity;

import java.util.Date;

/**
 * @author Administrator
 *	用户登录信息记录，可用于自动登录
 */
public class Record {
	/**
	 * 记录的id
	 */
	private String recordId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 过期时间
	 */
	private Date loginTime;
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * token
	 */
	private String token;
	/**
	 * 用户最近一次登录时的ip
	 */
	private String ip;
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
