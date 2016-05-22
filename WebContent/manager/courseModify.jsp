<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<style>
p{
	text-indent:2em;}
textarea{
　　resize:none;
}
.text2{
	width:700px;
	line-height:40px;
	font-size:16px;
	font-family: Arial, "宋体";
	margin:0 auto;
	margin-top:20px;}

</style>
<script>
function number(eeId){
	var a=document.getElementById("coursenum").value;
	var re = /^[0-9]*$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "课程学分只能为数字！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}

function coursename(eeId){
	var a=document.getElementById("coursename").value;
	var re = /^[\u4e00-\u9fa5]|[a-zA-Z]$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "课程名称只能为中文或大小写英文！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}


function showErrorMsg(msg,eeId){
	document.getElementById(eeId).style.display = "";
	document.getElementById(eeId).innerHTML = msg;
}
//当获得焦点 错误信息就不显示
function clearErrorMsg(eId){
	document.getElementById(eId).style.display = "none";	
}

function verInput(){
	if(coursename('name_hint') && number('num_hint')){
		return true;
	}else{
		alert("修改失败，请按照提示信息修改");
		return false;
	}
}

</script>
</head>

<body>
<div class="text2">
  <form id="form1" name="form1" method="post" action='sys_Update_courseManagement.action?cou_id=<s:property value="#session.coss.cou_id" />&tea_cou_id=<s:property value="#session.coss.tea_cou_id" />&currentPage=0' onsubmit="return verInput();">
    <table class="word_darkGrey" width="442" border="0" align="center">
      <tr>
        <td>课程名称：</td>
        <td><label>
          <input type="text" name="coursename" id="coursename" onfocus="clearErrorMsg('name_hint')" onblur="coursename('name_hint')" value='<s:property value="#session.coss.cou_name" />' />
        </label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="name_hint"></div></font></td>
      </tr>
      <tr>
        <td width="103">课程学分：</td>
        <td width="150"><label>
          <input type="text" size="5" name="coursenum" id="coursenum" onfocus="clearErrorMsg('num_hint')" onblur="number('num_hint')" value='<s:property value="#session.coss.cou_credit" />' />
        分</label></td>
        <td width="305" align="center"><font color="#FF0000" size="-1"><div id="num_hint"></div></font></td>
      </tr>
      <tr>
        <td>所属系别：</td>
        <td><label>
          <select name="dep" id="dep">
            <s:iterator value="#session.depdep" var="dep">
         <option value='<s:property value="#dep.dep_id" />'><s:property value="#dep.dep_name" /></option>
        </s:iterator>
          </select>
        </label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="stu_name_error"></div></font></td>
      </tr>
      <tr>
        <td>任课教师：</td>
        <td><label>
          <select name="teacher" id="teacher">
            <s:iterator value="#session.teatea" var="tea">
         <option value='<s:property value="#tea.tea_id" />'><s:property value="#tea.tea_name" /></option>
        </s:iterator>
          </select>
        </label></td>
        <td><font color="#FF0000" size="-1"><div id="ans_hint"></div></font></td>
      </tr>
      <tr>
        <td>所教班级：</td>
        <td><label>
          <select name="classes" id="classes">
            <s:iterator value="#session.clacla" var="cla">
         <option value='<s:property value="#cla.cla_id" />'><s:property value="#cla.cla_name" /></option>
        </s:iterator>
          </select>
        </label></td>
        <td><font color="#FF0000" size="-1"><div id="ans_hint"></div></font></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><label>
          <input type="submit" name="button" id="button" class="btn_grey" value="修改课程" />
        </label></td>
        <td align="center">&nbsp;</td>
      </tr>
    </table>
  </form>
</div>

</body>
</html>