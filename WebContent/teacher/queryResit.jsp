<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hp.bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.text2 {
	width:700px;
	line-height:40px;
	font-size:16px;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;
}
.paging{
	font-family: Arial, "宋体";
	font-size: 12px;
}
-->
</style>
</head>

<body>
<h3 class="subTitle">
	查询成绩
    <div class="search">
        <form id="form1" method="post" action='tea_Find_findQueryResult.action?tea_id=<s:property value="#session.teacher.tea_id" />&currentPage=0'>
            <select name="findType" id="findType">
                <option selected="selected" value="claName">班级名称</option>
                <option value="couName">课程名称</option>
                <option value="stuName">学生名称</option>
            </select>
            <input type="text" name="name" id="name" />
            <input type="submit" name="Submit" class="btn_grey" value="搜索" />
        </form>
    </div>
</h3>
<br />

<s:iterator value="#session.stuTestList" var="stuTest">
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="171"><s:property value="#stuTest.cla_name"/></td>
    <td width="131"><s:property value="#stuTest.cou_name"/></td>
    <td><s:property value="#stuTest.stu_id" /></td>
    <td width="127"><s:property value="#stuTest.stu_name" /></td>
    <td width="127" ><s:property value="#stuTest.stu_test_score" /></td>
  </tr>
  <tr align="center">
    <td colspan="5"><s:property value="#stuTest.stu_test_evaluate" escape="false"/></td>
  </tr>
</table>
<br />
</s:iterator>
<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="word_darkGrey">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href="tea_Find_queryResult.action?tea_id=<s:property value="#session.teacher.tea_id" />&currentPage=1">首页</a> | 
				<a href="tea_Find_queryResult.action?tea_id=<s:property value="#session.teacher.tea_id" />&currentPage=${page.currentPage -1 }">上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href="tea_Find_queryResult.action?tea_id=<s:property value="#session.teacher.tea_id" />&currentPage=${page.currentPage + 1 }">下一页</a> | 
							<a
					href="tea_Find_queryResult.action?tea_id=<s:property value="#session.teacher.tea_id" />&currentPage=${page.totalPage }">尾页</a>
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