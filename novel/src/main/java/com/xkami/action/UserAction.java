package com.xkami.action;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookieProvider;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.xkami.entity.Json;
import com.xkami.entity.Record;
import com.xkami.entity.User;
import com.xkami.service.UserService;
import com.xkami.util.CookieUtil;
import com.xkami.util.DesUtil;
import com.xkami.util.EmailUtil;
import com.xkami.util.IdUtil;

/**
 * @author Administrator
 *	用户模块action
 */
public class UserAction extends ActionSupport implements SessionAware,CookiesAware,CookieProvider{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserService userService;
	Gson gson;
	User user;
	Map<String,Object> session;
	Map<String, String> cookies;
	Set<Cookie> cookieSet = new HashSet<Cookie>();
	String json;
	String token;
	String baseKey;
	
	
	
	/**
	 * 使用email进行注册
	 * @return
	 */
	public String registerByEmail() {
		boolean flag = userService.registerByEmail(user);
		if(flag) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			User tmpUser = userService.loginByUserName(user);
			try {
				String email = userService.getEmail(tmpUser);
				String url = basePath+"/user/loginAndRegisterAction!activateUserByEmail"+"?token="+DesUtil.encrypt(tmpUser.getUserId(), baseKey);
				EmailUtil.sendMessage(email, url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag?"login":"register";
	}
	/**
	 * 检查email是否存在，并返回json数据
	 * @return
	 */
	public String checkEmail() {
		boolean flag = userService.checkEmail(user.getEmail());
		if(flag) {
			json = gson.toJson(new Json(0,"邮箱可以使用"));
		}else {
			json = gson.toJson(new Json(1,"邮箱已存在或为空"));
		}
		return "json";
	}
	public String checkPhoneNum() {
		return "json";
	}
	/**
	 * 检查用户名是否存在，返回json
	 * @return
	 */
	public String checkUserName() {
		boolean flag = userService.checkUserName(user.getUserName());
		if(flag) {
			json = gson.toJson(new Json(0,"用户名可以使用"));
		}else {
			json = gson.toJson(new Json(1,"用户名已存在或为空"));
		}
		return "json";
	}
	/**
	 * 使用邮箱和密码进行登录
	 * @return
	 */
	public String loginByEmail() {
		System.out.println("-----loginByEmail-----");
		user = userService.loginByEmail(user);
		if(user==null) {
			return "login";
		}else {
			session.put("user", user);
			setRecord();
			return "index";
		}
	}
	/**
	 * 使用用户名和密码进行登录
	 * @return
	 */
	public String loginByUserName() {
		user = userService.loginByUserName(user);
		if(user==null) {
			return "login";
		}else {
			session.put("user", user);
			setRecord();
			return "index";
		}
	}
	/**
	 * 点击激活邮件中的链接激活账户
	 * @return
	 */
	public String activateUserByEmail() {
		try {
			String userId = DesUtil.decrypt(token, baseKey);
			if(userService.checkUserId(userId)) {
				if(userService.activateUser(userId)) {
					return "success";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	
	private void setRecord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = IdUtil.getUuid();
		Record record = userService.getRecordByUserId(user.getUserId());
		record.setToken(token);
		record.setLoginTime(new Date());
		record.setIp(request.getRemoteAddr());
		try {
			cookieSet.add(CookieUtil.setCookie("recordId", DesUtil.encrypt(record.getRecordId(), baseKey),7));
			cookieSet.add(CookieUtil.setCookie( "token",  DesUtil.encrypt(token, baseKey), 7));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userService.updateRecord(record);
	}
	public void getRecord() {
		String token = cookies.get("token");
		String recordId = cookies.get("recordId");
		
		try {
			System.out.println("token:"+DesUtil.decrypt(token, baseKey)+
					"recordId:"+DesUtil.decrypt(recordId, baseKey));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBaseKey() {
		return baseKey;
	}
	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}
	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		// TODO Auto-generated method stub
		this.cookies = cookies;
	}
	@Override
	public Set<Cookie> getCookies() {
		// TODO Auto-generated method stub
		return cookieSet;
	}
}
