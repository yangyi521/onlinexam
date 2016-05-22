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
	试题详细信息
    <div class="search">
        <form id="form1" method="post" action="pastexam.html">
            <select name="selectk">
                <option selected="selected">java基础</option>
                <option>ssh</option>
                <option>物理基础</option>
            </select>
            <select name="selectb">
                <option selected="selected">开发9班</option>
                <option>开发好几班</option>
            </select>
            <input type="text" name="testname" />
            <input type="submit" name="Submit" class="btn" value="搜索" />
        </form>
    </div>
</h3>
<br />
<table width="90%" height="91" border="0" align="center" cellpadding="0" cellspacing="0" class="table3">
  <tr align="center">
    <td width="163">物理基础</td>
    <td width="708"><span class="text">已知1+1=2,2+2=4，求太阳系内行星公转轨道？</span></td>
    <td width="128" >答案：A</td>
  </tr>
  <tr align="center">
    <td colspan="3"><p>A.GM = (2π/T)^2·R^3 </p>
      <p>B.Rn =0.4+0.3*2的（n-2）次方 </p>
      <p>C.Rªn=（GM*AUª）的（n+1）次方÷10=0.1943×1.943的n次方<br />
      </p>
    <p>D.以上都不对</p></td>
  </tr>
  <tr align="center">
    <td colspan="3">包含知识点：宇宙星系中行星公转轨道分布定律发现及提丢斯-波得定则解析</td>
  </tr>
  <tr align="center">
    <td colspan="3"><a href="test.html">增加到试卷</a></td>
  </tr>
</table>
</body>
</html>