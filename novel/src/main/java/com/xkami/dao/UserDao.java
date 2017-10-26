package com.xkami.dao;

import com.xkami.entity.User;

public interface UserDao {
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	/**
	 * 使用邮箱登录
	 * @param user
	 * @return
	 */
	User loginByEmail(User user);
	/**
	 * 使用用户名密码登录
	 * @param user
	 * @return
	 */
	User loginByUserName(User user);
	/**
	 * 检验email是否已经存在
	 * @param email
	 * @return
	 */
	int checkEmail(String email);
	/**
	 * 检查用户名是否已经存在
	 * @param userName
	 * @return
	 */
	int checkUserName(String userName);
	/**
	 * 检查电话号码是否已经存在
	 * @param phoneNum
	 * @return
	 */
	int checkPhoneNum(String phoneNum);
	/**
	 * 检查用户id是否可以激活
	 * @param userId
	 * @return
	 */
	int checkUserId(String userId);
	/**
	 * 根据用户名密码获取用户的id和email
	 * @param user
	 * @return
	 */
	String getEmail(User user);
	/**
	 * 更新用户状态
	 * @param userId
	 * @param status
	 * @return
	 */
	int updateUserStatus(User user);
	/**
	 * 根据用户id获取用户基本信息
	 * @param userId
	 * @return
	 */
	User getUser(String userId);
}
