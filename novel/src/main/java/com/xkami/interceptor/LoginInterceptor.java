package com.xkami.interceptor;


import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xkami.entity.Record;
import com.xkami.entity.User;
import com.xkami.service.UserService;
import com.xkami.util.DesUtil;
import com.xkami.util.StaticUtil;

/**
 * @author Administrator
 *	登录拦截器
 *	如果session中没有用户则验证cookie中的登录凭证
 *  否则返回登录页
 */
public class LoginInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService;
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 准许通过拦截的页面：登录注册
	 */
	public String loginRegister;
	public String getLoginRegister() {
		return loginRegister;
	}

	public void setLoginRegister(String loginRegister) {
		this.loginRegister = loginRegister;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		// TODO Auto-generated method stub
		//获取session中的user对象
		ActionContext session = invo.getInvocationContext();
		User user = (User) session.getSession().get("user");
		//和允许通过的请求进行比较，允许则放行
		String[] actionName = loginRegister.split(",");
		String currActionName = session.getName();
		for(String requestActionName:actionName) {
			if(requestActionName.equals(currActionName)) {
				return invo.invoke();
			}
		}
		if(user==null) {
//			System.out.println("inter......null");
			String token = null;
			String recordId = null;
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			for (Cookie cookie : cookies) {
//				System.out.println(cookie.getName()+":"+cookie.getValue());
				if("token".equals(cookie.getName())) {
					token = cookie.getValue();
				}
				if("recordId".equals(cookie.getName())) {
					recordId = cookie.getValue();
				}
			}
			
			if(token!=null&&recordId!=null) {
				token = DesUtil.decrypt(token, StaticUtil.BaseKey);
				recordId = DesUtil.decrypt(recordId,StaticUtil.BaseKey);
				System.out.println(token+recordId);
				Record record = new Record();
				record.setRecordId(recordId);
				record.setToken(token);
				User usertmp = userService.loginByRecord(record);
				if(usertmp!=null) {
					session.getSession().put("user", usertmp);
					return invo.invoke();
				}
			}
			return "login";
		}else {
//			System.out.println("inter......not null");
			return invo.invoke();
		}
	}


}
