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
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style>
p{
	text-indent:2em;}
.text2{
	width:400px;
	line-height:40px;
	font-size: 9pt;
	color: #4E4E4E;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}
</style>
</head>

<body>
<div class="text2">
<p>工号：<s:property value="#session.teacher.tea_id"/></p>
<p>姓名：<s:property value="#session.teacher.tea_name"/></p>
<p>性别：<s:property value="#session.teacher.tea_sex"/></p>
<p>年龄：<%=session.getAttribute("tage") %></p>
<s:if test="%{#session.teaTeach==null}">
<p>所教班级：无	对应课程：无</p>
</s:if>
<s:else>
<s:iterator value="#session.teaTeach" var="teach">
<p>所教班级：<s:property value="#teach.cla_name"/>	对应课程：<s:property value="#teach.cou_name"/></p>
</s:iterator>
</s:else>
<p><a href="tchangePwd.jsp">修改密码</a></p>
</div>
</body>
</html>