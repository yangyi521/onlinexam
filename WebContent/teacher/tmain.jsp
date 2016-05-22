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
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<title>无标题文档</title>
<style>

</style>
</head>

<body>
<h3 class="subTitle">
	近期考试
</h3>
<br />
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td><b>考试科目</b></td>
    <td><b>考试名称</b></td>
    <td><b>考试截止时间</b></td>
    <td width="81" ><b>考试状态</b></td>
  </tr>
  <s:iterator value="#session.near_list" var="near">
  <tr align="center">
    <td><s:property value="#near.cou_name" /></td>
    <td><s:property value="#near.test_name" /></td>
    <td><s:date name="#near.test_time" format="yyyy-MM-dd" /></td>
    <td width="81" >正在考试</td>
  </tr>
  </s:iterator>
</table>
</body>
</html>