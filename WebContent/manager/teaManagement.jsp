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
	font-size:16px;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}
-->
</style>
</head>

<body>
<h3 class="subTitle">教师管理
  <div class="search">
        <form id="form1" method="post" action="sys_Find_findTeaManagement.action?currentPage=0">
            <select name="selectk">
                <option selected="selected" value="courseName">课程科目</option>
                <option value="teaId">教师编号</option>
                <option value="teaName">教师姓名</option>
            </select>
            <input type="text" name="name" />
            <input type="submit" name="Submit" class="btn_grey" value="搜索" />
        </form>
  </div>
  &nbsp;&nbsp;&nbsp;&nbsp;<a href="sys_Find_teaAdd.action">增加教师</a>
</h3>
<br />
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="187" height="51">教师工号</td>
    <td width="91">教师姓名</td>
    <td width="156">登录密码</td>
    <td width="105" >教师性别</td>
    <td width="139">出生日期</td>
    <td width="138" >所属属性</td>
    <td width="91">修改操作</td>
    <td width="92">删除操作</td>
  </tr>
  <s:iterator value="#session.allTeacher" var="tea">
	  <tr align="center">
	    <td ><s:property value="#tea.tea_id"/></td>
	    <td ><s:property value="#tea.tea_name"/></td>
	    <td><s:property value="#tea.tea_password"/></td>
	    <td ><s:property value="#tea.tea_sex"/></td>
	    <td ><s:date name="#tea.tea_born" format="yyyy-MM-dd"/></td>
	    <td  ><s:property value="#tea.dep_name"/></td>
	    <td  ><a href="sys_Update_teaModify.action?tea_id=<s:property value="#tea.tea_id"/>" target="_self">修改</a></td>
	    <td  ><a href="sys_Delete_teaManagement.action?tea_id=<s:property value="#tea.tea_id"/>" onclick="javascript: return confirm('确认要删除该教师吗？');">删除</a></td>
	  </tr>
  </s:iterator>
</table>
<br />
<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="word_darkGrey">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href="sys_Find_teaManagement.action?currentPage=1">首页</a> | 
				<a href="sys_Find_teaManagement.action?currentPage=${page.currentPage -1 }">上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href="sys_Find_teaManagement.action?currentPage=${page.currentPage + 1 }">下一页</a> | 
							<a
					href="sys_Find_teaManagement.action?currentPage=${page.totalPage }">尾页</a>
			</c:when>
			<c:otherwise>
						下一页 | 尾页
					</c:otherwise>
		</c:choose>
		当前为第${page.currentPage}页,共${page.totalPage}页
	</div></td></tr>
	</table>
</body>
</html>