package com.xkami.service;

import com.xkami.entity.Record;
import com.xkami.entity.User;

public interface UserService {
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	boolean register(User user);
	/**
	 * 使用邮箱注册用户
	 * @param user
	 * @return
	 */
	boolean registerByEmail(User user);
	/**
	 * 检查email是否已经存在
	 * @param user
	 * @return
	 */
	boolean checkEmail(String email);
	/**
	 * 检查电话号码是否已经存在
	 * @param phoneNum
	 * @return
	 */
	boolean checkPhoneNum(String phoneNum);
	/**
	 * 检查用户名是否已经存在
	 * @param userName
	 * @return
	 */
	boolean checkUserName(String userName);
	/**
	 * 检查用户id是否已经存在
	 * @param userId
	 * @return
	 */
	boolean checkUserId(String userId);
	/**
	 * 使用邮箱和密码进行登录
	 * @param user
	 * @return
	 */
	User loginByEmail(User user);
	/**
	 * 使用用户名和密码进行登录
	 * @param user
	 * @return
	 */
	User loginByUserName(User user);
	/**
	 * 根据用户名密码email
	 * @param user
	 * @return
	 */
	String getEmail(User user);
	/**
	 * 激活用户
	 * @param userId
	 * @return
	 */
	boolean activateUser(String userId);
	/**
	 * 根据用户id返回登录记录
	 * @param userId
	 * @return
	 */
	Record getRecordByUserId(String userId);
	/**
	 * 更新用户登录信息
	 * @param record
	 * @return
	 */
	boolean updateRecord(Record record);
	/**
	 * 根据登录记录cookie来判断是否可以登录
	 * @param record
	 * @return
	 */
	User loginByRecord(Record record);
}
