package com.xkami.service.impl;


import com.xkami.dao.RecordDao;
import com.xkami.dao.UserDao;
import com.xkami.entity.Record;
import com.xkami.entity.User;
import com.xkami.service.UserService;
import com.xkami.util.DesUtil;
import com.xkami.util.IdUtil;
import com.xkami.util.Md5Util;
import com.xkami.util.RegexUtil;

public class UserServiceImpl implements UserService {
	String baseKey;
  	UserDao userDao;
  	RecordDao recordDao;
	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public String getBaseKey() {
		return baseKey;
	}

	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		user.setUserId(IdUtil.getUuid());
		user.setStatus("0");
		try {
			if(user.getEmail()!=null) {
				user.setEmail(DesUtil.encrypt(user.getEmail(), baseKey));
			}
			if(user.getPhoneNum()!=null) {
				user.setPhoneNum(DesUtil.encrypt(user.getPhoneNum(), baseKey));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		user.setPassword(Md5Util.MD5(user.getPassword()));
		int result = userDao.saveUser(user);
		if(result>0) {
			Record record = new Record();
			record.setRecordId(IdUtil.getUuid());
			record.setUserId(user.getUserId());
			recordDao.saveRecord(record);
		}
		return result>0;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		if("".equals(email)) {
			return false;
		}else {
			try {
				email = DesUtil.encrypt(email, baseKey);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userDao.checkEmail(email)==0;
		}
	}

	@Override
	public boolean checkUserName(String userName) {
		// TODO Auto-generated method stub
		if("".equals(userName)) {
			return false;
		}else {
			return userDao.checkUserName(userName)==0;
		}
	}

	@Override
	public boolean registerByEmail(User user) {
		// TODO Auto-generated method stub
		if(user!=null&&checkEmail(user.getEmail())&&checkUserName(user.getUserName())&&RegexUtil.checkEmail(user.getEmail())&&!("".equals(user.getPassword()))) {
			User tmpUser = new User();
			tmpUser.setEmail(user.getEmail());
			tmpUser.setPassword(user.getPassword());
			tmpUser.setUserName(user.getUserName());
			if(register(tmpUser)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User loginByEmail(User user) {
		// TODO Auto-generated method stub
		if("".equals(user.getEmail())||"".equals(user.getPassword())){
			return null;
		}
		user.setPassword(Md5Util.MD5(user.getPassword()));
		return userDao.loginByEmail(user);
	}

	@Override
	public User loginByUserName(User user) {
		// TODO Auto-generated method stub
		if("".equals(user.getUserName())||"".equals(user.getPassword())) {
			return null;
		}
		user.setPassword(Md5Util.MD5(user.getPassword()));
		return userDao.loginByUserName(user);
	}

	@Override
	public boolean checkPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		if("".equals(phoneNum)) {
			return false;
		}else {
			try {
				phoneNum = DesUtil.encrypt(phoneNum, baseKey);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userDao.checkPhoneNum(phoneNum)==0;
		}
	}

	@Override
	public String getEmail(User user) {
		// TODO Auto-generated method stub
		try {
			return DesUtil.decrypt(userDao.getEmail(user), baseKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkUserId(String userId) {
		// TODO Auto-generated method stub
		if("".equals(userId)) {
			return false;
		}else {
			return userDao.checkUserId(userId)>0;
		}
	}

	@Override
	public boolean activateUser(String userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setStatus("1");
		user.setUserId(userId);
		return userDao.updateUserStatus(user)>0;
	}

	@Override
	public Record getRecordByUserId(String userId) {
		// TODO Auto-generated method stub
		return recordDao.getRecordByUserId(userId);
	}

	@Override
	public boolean updateRecord(Record record) {
		// TODO Auto-generated method stub
		return recordDao.updateRecord(record)>0;
	}

	@Override
	public User loginByRecord(Record record) {
		// TODO Auto-generated method stub
		Record recordTmp = recordDao.getRecord(record.getRecordId());
		if(recordTmp!=null) {
			if(recordTmp.getToken().equals(record.getToken())) {
				User user = userDao.getUser(recordTmp.getUserId());
				return user;
			}
		}
		return null;
	}

}
