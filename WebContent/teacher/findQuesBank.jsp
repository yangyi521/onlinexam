<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.text2 {
	width: 700px;
	line-height: 40px;
	font-size: 16px;
	font-family: Arial, "宋体";
	margin: 0 auto;
	margin-top: 20px;
}
.paging{
	font-family: Arial, "宋体";
	font-size: 12px;
}
-->
</style>
</head>

<body>
<form id="form1" method="post" action="tea_Find_findQuesBank.action?currentPage=0">
	<h3 class="subTitle">
		题库管理
		<div class="search">
				<select name="selectk" id="selectk">
					<s:if test='%{#session.selectk.equals("couName")}'>
						<option selected="selected" value="couName">课程科目</option>
					<option value="queTitle">试题题目</option>
					</s:if>
					<s:else>
					<option value="couName">课程科目</option>
					<option value="queTitle" selected="selected">试题题目</option>
					</s:else>
				</select> <input type="text" name="quesname" id="quesname" value='<s:property value="#session.quesname" />' /> <input type="submit"
					name="Submit" class="btn_grey" value="搜索" />
		
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;<a href='tea_Add_quesAdd.action?dep_id=<s:property value="#session.teacher.dep_id" />'>增加试题</a>
	</h3>
	<br />
	<s:if test="%{#session.queList!=null}">
	<s:iterator value="#session.queList" var="ques">
		<s:if test="#ques.q_type==3">
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="table3">
				<tr align="center">
					<td width="45"><s:property value="#ques.q_id" /></td>
					<td width="120"><s:property value="#ques.cou_name" /></td>
					<td><span class="text"><s:property
								value="#ques.q_title" /></span></td>
					<td width="85">判断题</td>
					<td width="125">答案：<s:property value="#ques.q_ans" /></td>
				</tr>
				<tr align="center">
					<td colspan="5"><p>
							A.
							<s:property value="#ques.q_itemA" />
						</p>
						<p>
							B.
							<s:property value="#ques.q_itemB" />
						</p></td>
				</tr>
				<tr align="center">
					<td colspan="5"><s:property value="#ques.poi_title" /></td>
				</tr>
				<tr align="center">
					<td colspan="5"><a href='tea_Update_quesModify.action?q_id=<s:property value="#ques.q_id"/>&dep_id=<s:property value="#session.teacher.dep_id" />'>修改试题</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href='tea_Delete_quesBank.action?q_id=<s:property value="#ques.q_id"/>&currentPage=0'
						onclick="javascript: return confirm('确认删除试题吗？');">删除试题</a></td>
				</tr>
			</table>
			<br />
		</s:if>
		<s:else>
			<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="table3">
				<tr align="center">
					<td width="45"><s:property value="#ques.q_id" /></td>
					<td width="120"><s:property value="#ques.cou_name" /></td>
					<td><span class="text"><s:property
								value="#ques.q_title" /></span></td>
					<s:if test='%{#ques.q_type==1}'>
						<td width="85">单选题</td>
					</s:if>
					<s:else>
						<td width="85">多选题</td>
					</s:else>
					<td width="125">答案：<s:property value="#ques.q_ans" /></td>
				</tr>
				<tr align="center">
					<td colspan="5"><p>
							A.
							<s:property value="#ques.q_itemA" />
							&nbsp;&nbsp;&nbsp;&nbsp; B.
							<s:property value="#ques.q_itemB" />
						</p>
						<p>
							C.
							<s:property value="#ques.q_itemC" />
							&nbsp;&nbsp;&nbsp;&nbsp; D.
							<s:property value="#ques.q_itemD" />
						</p></td>
				</tr>
				<tr align="center">
					<td colspan="5"><s:property value="#ques.poi_title" /></td>
				</tr>
				<tr align="center">
					<td colspan="5"><a href='tea_Update_quesModify.action?q_id=<s:property value="#ques.q_id"/>&dep_id=<s:property value="#session.teacher.dep_id" />'>修改试题</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						href='tea_Delete_quesBank.action?q_id=<s:property value="#ques.q_id"/>&currentPage=0' onclick="javascript: return confirm('确认删除试题吗？');">删除试题</a></td>
				</tr>
			</table>
			<br />
		</s:else>
	</s:iterator>
	</s:if>
	<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="word_darkGrey">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href="tea_Find_findQuesBank.action?currentPage=1">首页</a> | 
				<a href="tea_Find_findQuesBank.action?currentPage=${page.currentPage -1 }">上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href="tea_Find_findQuesBank.action?currentPage=${page.currentPage + 1 }">下一页</a> | 
							<a
					href="tea_Find_findQuesBank.action?currentPage=${page.totalPage }">尾页</a>
			</c:when>
			<c:otherwise>
						下一页 | 尾页
					</c:otherwise>
		</c:choose>
		当前为第${page.currentPage}页,共${page.totalPage}页
	</div></td></tr>
	</table>
</form>
</body>
</html>