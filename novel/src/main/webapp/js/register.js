$(function(){
	$("#registerByEmail").click(function(){
		var email = $("#email").val();
		var userName = $("#userName").val();
		if($("#emailCheck").html()==""&&$("#userNameCheck").html()==""){
			$("#registerForm").submit();
		}
	})
	$("#registerByPhoneNum").click(function(){
		var userName = $("#userName").val();
		var phoneNum = $("#phoneNum").val();
		if($("#phoneNumCheck").html()==""&&$("#userNameCheck").html()==""){
			$("#registerForm").submit();
		}
	})
	$("#email").blur(function(){
		checkEmail($("#email").val());
	});
	$("#userName").blur(function(){
		checkUserName($("#userName").val());
	});
	$("#phoneNum").blur(function(){
		checkPhoneNum($("#phoneNum").val())
	});
})
function checkEmail(email){
	$.ajax({
		type:"get",
		url:"user/loginAndRegisterAction!checkEmail",
		dataType:"json",
		data:{
			"user.email":email
		},
		success:function(data){
			var json = eval("("+data+")");
			if(json.result==1){
				$("#emailCheck").html(json.message);
			}else{
				$("#emailCheck").html("");
			}
		}
	});
}
function checkUserName(userName){
	$.ajax({
		type:"get",
		url:"user/loginAndRegisterAction!checkUserName",
		dataType:"json",
		data:{
			"user.userName":userName
		},
		success:function(data){
			var json = eval("("+data+")");
			if(json.result==1){
				$("#userNameCheck").html(json.message);
			}else{
				$("#userNameCheck").html("");
			}
		}
	});
}
function checkPhoneNum(phoneNum){
	$.ajax({
		type:"get",
		url:"user/loginAndRegisterAction!checkPhoneNum",
		dataType:"json",
		data:{
			"user.phoneNum":phoneNum
		},
		success:function(data){
			var json = eval("("+data+")");
			if(json.result==1){
				$("#phoneNumCheck").html(json.message);
			}else{
				$("#phoneNumCheck").html("");
			}
		}
	});
}

