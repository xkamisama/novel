<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<script src="js/register.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1">
				<form class="form-horizontal" role="form" method="post" action="user/loginAndRegisterAction!registerByEmail" id="registerForm">
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userName" placeholder="请输入用户名" name="user.userName">
						</div>
					</div>
					<p id="userNameCheck"></p>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" placeholder="请输入邮箱" name="user.email">
						</div>
					</div>
					<p id="emailCheck"></p>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" placeholder="请输入密码" name="user.password">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-default" id="registerByEmail">注册</button>
							<a href="login.jsp">已注册</a>
						</div>
					</div>
				</form>
			</div>
		</div>

	</body>

</html>