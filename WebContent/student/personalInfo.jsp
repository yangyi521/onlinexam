<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hp.bean.*,java.text.SimpleDateFormat"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style>
p {
	text-indent: 2em;
}

.text2 {
	width: 300px;
	line-height: 30px;
	font-size: 9pt;
	color: #4E4E4E;
	font-family: Arial, "宋体";
	margin: 0 auto;
	margin-top: 20px;
}
</style>

</head>

<body>
<h3 class="subTitle">
	个人信息
</h3>
	<%
		StudentView stuv = (StudentView) session.getAttribute("student");
		//System.out.println(stuv.getStu_born());

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String birth = f.format(stuv.getStu_born());
		birth = birth.substring(0, 4);
		//System.out.println(birth);

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int year = c.get(Calendar.YEAR);
		//System.out.println(year);
		int age = year - Integer.valueOf(birth);
	%>
	<div class="text2">
		<p>
			学号：
			<s:property value="#session.student.stu_id" />
		</p>
		<p>
			姓名：
			<s:property value="#session.student.stu_name" />
		</p>
		<p>
			性别：
			<s:property value="#session.student.stu_sex" />
		</p>
		<p>
			年龄：<%=age%>岁
		</p>
		<p>
			班级：
			<s:property value="#session.student.cla_name" />
		</p>
		<p>
			方向：
			<s:property value="#session.student.dep_name" />
		</p>
		<p>
			<a href="changePwd.jsp">修改密码</a>
		</p>
	</div>
</body>
</html>
