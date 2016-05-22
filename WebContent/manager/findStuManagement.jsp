<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<style type="text/css">
<!--
.text2 {	width:700px;
	line-height:40px;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}
-->
</style>
</head>

<body>
<h3 class="subTitle">学生管理
  <div class="search">
        <form id="form1" method="post" action="sys_Find_findStuManagement.action?currentPage=0">
            <select name="selectk">
            	<s:if test='%{#session.selectk.equals("claName")}'>
	                <option selected="selected" value="claName">班级名称</option>
	                <option value="stuName">学生姓名</option>
	                <option value="stuId">学生学号</option>
	            </s:if>
	            <s:elseif test='%{#session.selectk.equals("stuName")}'>
	            	<option value="claName">班级名称</option>
	                <option selected="selected" value="stuName">学生姓名</option>
	                <option value="stuId">学生学号</option>
	            </s:elseif>
	            <s:else>
	            	<option value="claName">班级名称</option>
	                <option value="stuName">学生姓名</option>
	                <option selected="selected" value="stuId">学生学号</option>
	            </s:else>
            </select>
            <input type="text" name="stuname" value='<s:property value="#session.quesname" />' />
            <input type="submit" name="Submit" class="btn" value="搜索" />
        </form>
  </div>
  &nbsp;&nbsp;&nbsp;&nbsp;<a href="sys_Find_stuAdd.action">增加学生</a>
</h3>
<br />
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="125" height="51">学生学号</td>
    <td >学生姓名</td>
    <td >登录密码</td>
    <td >学生性别</td>
    <td >出生日期</td>
    <td >所在班级</td>
    <td >所属属性</td>
    <td >修改操作</td>
    <td >删除操作</td>
  </tr>
  <s:iterator value="#session.allStudent" var="stu">
	  <tr align="center">
	    <td ><s:property value="#stu.stu_id"/></td>
	    <td ><s:property value="#stu.stu_name"/></td>
	    <td><s:property value="#stu.stu_password"/></td>
	    <td ><s:property value="#stu.stu_sex"/></td>
	    <td ><s:date name="#stu.stu_born" format="yyyy-MM-dd"/></td>
	    <td  ><s:property value="#stu.cla_name"/></td>
	    <td  ><s:property value="#stu.dep_name"/></td>
	    <td  ><a href="sys_Update_stuModify.action?stu_id=<s:property value="#stu.stu_id"/>" target="_self">修改</a></td>
	    <td  ><a href="sys_Delete_stuManagement.action?stu_id=<s:property value="#stu.stu_id"/>" onclick="javascript: return confirm('确认要删除该学生吗？');">删除</a></td>
	  </tr>
  </s:iterator>
</table>
<br />
<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="word_darkGrey">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href="sys_Find_findStuManagement.action?currentPage=1">首页</a> | 
				<a href="sys_Find_findStuManagement.action?currentPage=${page.currentPage -1 }">上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href="sys_Find_findStuManagement.action?currentPage=${page.currentPage + 1 }">下一页</a> | 
							<a
					href="sys_Find_findStuManagement.action?currentPage=${page.totalPage }">尾页</a>
			</c:when>
			<c:otherwise>
						下一页 | 尾页
					</c:otherwise>
		</c:choose>
		当前为第${page.currentPage}页,共${page.totalPage}

页
	</div></td></tr>
	</table>
</body>
</html>