package com.xkami.dao;

import com.xkami.entity.Record;

public interface RecordDao {
	/**
	 * 每当用户注册成功时添加一条用户登录信息记录
	 * @param record
	 * @return
	 */
	int saveRecord(Record record);
	/**
	 * 根据用户id获取该用户的登录信息
	 * @param userId
	 * @return
	 */
	Record getRecordByUserId(String userId);
	/**
	 * 根据记录id获取整条信息
	 * @param recordId
	 * @return
	 */
	Record getRecord(String recordId);
	/**
	 * 根据用户id找到并更新记录
	 * @param record
	 * @return
	 */
	int updateRecord(Record record);
}
