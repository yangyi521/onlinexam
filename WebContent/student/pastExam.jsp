<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>

<body>
<h3 class="subTitle">
	往期考试
    <div class="search">
        <form id="form1" method="post" action="stu_Find_findPastExam.action?currentPage=0">
            <select name="select">
            <s:iterator value="#session.cousss" var="cou">
                <option value='<s:property value="#cou.cou_id" />'><s:property value="#cou.cou_name" /></option>
            </s:iterator>
            </select>
            <input type="submit" name="Submit" class="btn_grey" value="搜索" />
        </form>
    </div>
</h3>
<br />
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="113"><b>考试科目</b></td>
    <td width="260"><b>试卷名称</b></td>
    <td width="120"><b>考试时长</b></td>
    <td width="120"><b>是否完成</b></td>
    <td width="81" ><b>考试得分</b></td>
  </tr>
<s:iterator value="#session.stuTestSimpleView" var="stuTest">
  <tr align="center">
    <td><s:property value="#stuTest.cou_name" /></td>
    <td><a href="<%=path %>/stu_Find_detailedTest.action?stu_test_id=<s:property value="#stuTest.stu_test_id" />" target="_blank"><s:property value="#stuTest.test_name" /></a></td>
    <td><s:property value="#stuTest.stu_test_time" /></td>
    <td>已完成</td>
    <td width="81" ><s:property value="#stuTest.stu_test_score" /></td>
  </tr>
</s:iterator>
</table>
<br />
<table width="90%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="word_darkGrey">
		<tr><td><div align="center" class="paging">
		<c:choose>
			<c:when test="${page.hasPrePage}">
				<a href='stu_Find_pastExam.action?stu_id=<s:property value="#session.student.stu_id" />&currentPage=1'>首页</a> | 
				<a href='stu_Find_pastExam.action?stu_id=<s:property value="#session.student.stu_id" />&currentPage=${page.currentPage-1 }'>上一页</a>
			</c:when>
			<c:otherwise>
							首页 | 上一页
						</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage}">
				<a
					href='stu_Find_pastExam.action?stu_id=<s:property value="#session.student.stu_id" />&currentPage=${page.currentPage + 1 }'>下一页</a> | 
							<a
					href='stu_Find_pastExam.action?stu_id=<s:property value="#session.student.stu_id" />&currentPage=${page.totalPage }'>尾页</a>
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
