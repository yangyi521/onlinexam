<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<h3 class="subTitle">班级管理
  <div class="search">
        <form id="form1" method="post" action="sys_Find_findClassesManagement.action?currentPage=0">
            <select name="selectk">
                <option selected="selected" value="2">开发方向</option>
                <option value="1">测试方向</option>
            </select>
            
            <input type="submit" name="Submit" class="btn_grey" value="搜索" />
        </form>
    </div>
    &nbsp;&nbsp;&nbsp;&nbsp;<a href="classesAdd.jsp">增加班级</a>
</h3>
<br />
<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="138" height="51">班级编号</td>
    <td width="162">班级名称</td>
    <td width="90">所属属性</td>
    <td width="94" >修改操作</td>
    <td width="116" >删除操作</td>
  </tr>
  <s:iterator value="#session.allClasses" var="cla">
	  <tr align="center">
	    <td width="138" height="51"><s:property value="#cla.cla_id"/></td>
	    <td width="162"><s:property value="#cla.cla_name"/></td>
	    <td width="90"><s:property value="#cla.dep_name"/></td>
	    <td width="94" ><a href="sys_Update_classesModify.action?cla_id=<s:property value="#cla.cla_id"/>" target="_self">修改</a></td>
	    <td width="116" ><a href="sys_Delete_classesManagement?cla_id=<s:property value="#cla.cla_id"/>" onclick="javascript: return confirm('确认要删除该班级吗？');">删除</a></td>
	  </tr>
  </s:iterator>
</table>
<br />
<table class="word_darkGrey" width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="table3">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href="sys_Find_classesManagement.action?currentPage=1">首页</a> | 
				<a href="sys_Find_classesManagement.action?currentPage=${page.currentPage -1 }">上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href="sys_Find_classesManagement.action?currentPage=${page.currentPage + 1 }">下一页</a> | 
							<a
					href="sys_Find_classesManagement.action?currentPage=${page.totalPage }">尾页</a>
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