<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面</title>
<link href="<%=path %>css/style.css" rel="stylesheet" type="text/css" />
<style>
#wrapper {
	background-image:url(img/login.jpg);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	height: 348px;
	width: 684px;
	margin-top: 150px;
	margin-right: auto;
	margin-left: auto;
}
.login {
	height: 140px;
	width: 222px;
	float: right;
	margin-top: 93px;
	margin-right: 101px;
	padding-top: 50px;
	padding-left: 55px;
	display: inline;
}
.login div {
	margin-top: 15px;
	margin-bottom: 15px;
	height: 22px;
}
.login #type {
	font-family: Arial, "宋体";
	font-size: 12px;
}
.login #username {
	border: 1px solid #0F509F;
	width: 160px;
	height: 16px;
}
.login #password {
	border: 1px solid #0F509F;
	width: 160px;
	height: 16px;
}
.login .button {
	padding-left: 0px;
}
.login .button input {
	background-color:#999;
	height: 20px;
	width: 60px;
	font-family: Arial, "宋体";
	font-size: 12px;
	font-weight: bold;
	color: #FFFFFF;
	background-repeat: repeat-x;
	background-position: 0px 0px;
	border: 1px solid #999;
	line-height: 18px;
}
</style>
<script>
function checkNull(){
	var name = document.getElementById("username").value;
	var pwd = document.getElementById("password").value;
	
	if(name.length == 0 ){
		alert("用户名不能为空！");
		return false;
	}
	if(pwd.length == 0 ){
		alert("密码不能为空！");
		return false;
	}
	return true;
}


</script>
</head>

<body>
<div id="wrapper">
<form id="form1" name="form1" method="post" action="loginAction.action?method=login" onsubmit="return checkNull()">
		<div class="login">
			<div>
			<input type="text" name="username" id="username" />
			</div>
			<div>
			  <input type="password" name="password" id="password" />
		  </div>
          <div>
            <label>
              <select name="choice" id="choice">
                <option value="student">学生</option>
                <option value="teacher">教师</option>
                <option value="admin">管理员</option>
              </select>
            </label>
          </div>
			<div class="button">
			  <input type="submit" name="Submit" value="登录" />
			  　
				<input type="reset" name="Submit" value="重置" />
		  </div>
		</div>
	</form>
</div>
<div>${msg }
</div>
</body>
</html>
