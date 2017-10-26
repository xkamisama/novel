package com.xkami.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xkami.entity.User;

public class LoginInterceptor implements Interceptor {
	public String loginRegister;
	public String getLoginRegister() {
		return loginRegister;
	}

	public void setLoginRegister(String loginRegister) {
		this.loginRegister = loginRegister;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invo) throws Exception {
		// TODO Auto-generated method stub
		ActionContext session = invo.getInvocationContext();
		User user = (User) session.getSession().get("user");
		String[] actionName = loginRegister.split(",");
		String currActionName = session.getName();
		for(String requestActionName:actionName) {
			if(requestActionName.equals(currActionName)) {
				return invo.invoke();
			}
		}
		if(user==null) {
			System.out.println("inter......null");
			return "login";
		}else {
			System.out.println("inter......not null");
			return invo.invoke();
		}
	}

}
