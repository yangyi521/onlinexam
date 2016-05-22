<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</style>
</head>

<body>
<h3 class="subTitle">
	查询题库
    <div class="search">
        <form id="form1" method="post" action="pastexam.html">
            <select name="selectk">
                <option selected="selected">java基础</option>
                <option>ssh</option>
                <option>物理基础</option>
            </select>
            <input type="text" name="testname" />
            <input type="submit" name="Submit" class="btn" value="搜索" />
        </form>
    </div>
</h3>
<br />
<table width="90%" height="40" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="150">物理基础</td>
    <td width="755"><span class="text"><a href="testdetailed.html" target="_parent">已知1+1=2,2+2=4，求太阳系内行星公转轨道？ </a></span></td>
    <td width="94"><a href="test.html">增加到试卷</a></td>
  </tr>
</table>
<br />
<table width="90%" height="33" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="150">物理基础</td>
    <td width="755"><span class="text"><a href="testdetailed.html" target="_parent">行星公转轨道分布周期，和已知相邻行星公转周期平均比值的3/2*（n+1）次方成正比。 </a></span></td>
    <td width="94"><a href="test.html">增加到试卷</a></td>
  </tr></table>
</body>
</html>