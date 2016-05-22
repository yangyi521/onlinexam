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
<style type="text/css">
<!--
.text2 {	width:700px;
	line-height:40px;
	font-size:16px;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}
-->
</style>
</head>

<body>
<h3 class="subTitle">
	查询个人成绩
      <div class="search">
        <form id="form1" method="post" action="tea_Find_queryResult.action?method=findBy">
            <select name="findType" id="findType">
                <option selected="selected" value="claName">班级名称</option>
                <option value="couName">课程名称</option>
                <option value="stuName">学生名称</option>
            </select>
            <input type="text" name="name" id="name" />
            <input type="submit" name="Submit" class="btn" value="搜索" />
        </form>
    </div>
</h3>
<br />
<s:iterator value="#session.stuTestById" var="stut">
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="171"><s:property value="#stut.cla_name"/></td>
    <td width="131"><s:property value="#stut.cou_name"/></td>
    <td><s:property value="#stut.stu_id" /></td>
    <td width="127"><s:property value="#stut.stu_name" /></td>
    <td width="127" ><s:property value="#stut.stu_test_score" /></td>
  </tr>
  <tr align="center">
    <td colspan="5"><s:property value="#stut.stu_test_evaluate" escape="false"/></td>
  </tr>
</table>
<br />
</s:iterator>
</body>
</html>