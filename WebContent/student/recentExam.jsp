<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hp.bean.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style>
.text2{
	width:700px;
	line-height:40px;
	font-size: 9pt;
	color: #4E4E4E;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}
</style>
</head>

<body>

	<img src="../img/logo.jpg" align="top" />
<h3 class="subTitle">
	考试结果
</h3>
<div class="text2">
<s:property value="#session.student.stu_name" />同学：<br />
您的<s:property value="#session.stuTest.cou_name" />考试分数为：<font color="#FF0000"><s:property value="#session.stuTest.stu_test_score" /></font>分 <br />
考试评估：<br />
<% 
	StuTest stuTest=(StuTest)session.getAttribute("stuTest");
	
%>
<%=stuTest.getStu_test_evaluate() %>
<%-- <% String message = stuTest.getStu_test_evaluate();
  String msg = new String(message.getBytes("utf-8"));
%>
<%=msg%> --%>
<br />

</div>
<div class="text2">
<a href="backIndex.action">&lt;&lt; 返回首页</a>
</div>
</body>
</html>