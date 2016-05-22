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
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<style>
p{
	text-indent:2em;}
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
	var a=document.getElementById("num").value;
	var re = /^[0-9]*$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "编号只能为数字！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}

function username(eeId){
	var a=document.getElementById("username").value;
	var re = /^[\u4e00-\u9fa5]+$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "姓名只能为中文！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}

function password(eeId){
	var a=document.getElementById("password").value;
	var re = /^[0-9A-Za-z]{6,}$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "密码必须6位以上且只能为大小写英文或数字！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}

function age(eeId){
	var a=document.getElementById("age").value;
	var re = /^[0-9]{1,2}$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "年龄只能为数字！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}

function getGen(eeId){
	var genResult = "";
	if(document.getElementById("sex_0").checked || document.getElementById("sex_1").checked){
		genResult = "xingbie";
		return true;
	}
	
	if(genResult == ""){
		var msg = "请选择性别！";
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
	if(number('num_hint') && username('name_hint') && password('pwd_hint') && getGen('sex_hint')&& isNotNull('control_date')){
		return true;
	}else{
		alert("增加失败，请按照提示信息修改");
		return false;
	}
}
function isNotNull(uuid){
	var value=document.getElementById(uuid).value;
	if(value == null || value==""){
		alert("请选择出生日期！");
		return false;
	}else{
		return true;
	}
}

</script>
</head>

<body onload="YearMonthDay()">
<script type="text/javascript" src="../js/Calendar3.js"></script>
<div class="text2">
  <form action="sys_Add_teaManagement.action" method="post" name="form1" id="form1" onsubmit="return verInput();">
    <table width="662" border="0" align="center" class="word_darkGrey">
      <tr>
        <td>工号：</td>
        <td><label>
          <input type="text" name="num" id="num" onfocus="clearErrorMsg('num_hint')" onblur="number('num_hint')" />
        </label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="num_hint"></div></font></td>
      </tr>
      <tr>
        <td width="132">姓名：</td>
        <td width="195"><label>
          <input type="text" name="username" id="username" onfocus="clearErrorMsg('name_hint')" onblur="username('name_hint')" />
        </label></td>
        <td width="321" align="center"><font color="#FF0000" size="-1"><div id="name_hint"></div></font></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><label>
          <input type="password" name="password" id="password" onfocus="clearErrorMsg('pwd_hint')" onblur="password('pwd_hint')"  />
        </label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="pwd_hint"></div></font></td>
      </tr>
      <tr>
        <td>性别：</td>
        <td><p>
          <label>
            <input type="radio" name="sex" value="男" id="sex_0" onclick="getGen('sex_hint')" onblur="clearErrorMsg('sex_hint')" />
            男</label>
          <label>
            <input type="radio" name="sex" value="女" id="sex_1" onclick="getGen('sex_hint')" onblur="clearErrorMsg('sex_hint')" />
            女</label>
        </p></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="sex_hint"></div></font></td>
      </tr>
      <tr>
        <td>出生日期：</td>
        <td width="250"><input name="birthday" type="text" id="control_date" size="20"
      maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" 
      value='<s:date name="todayDate" format="yyyy-MM-dd"/>'/></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="age_hint"></div></font></td>
      </tr>
      <tr>
        <td>所属属性：</td>
        <td><select name="dep" id="dep">
        <s:iterator value="#session.departs" var="dep">
          <option value='<s:property value="#dep.dep_id" />'><s:property value="#dep.dep_name" /></option>
          </s:iterator>
        </select></td>
        <td><font color="#FF0000" size="-1"><div id="obj_hint"></div></font></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><label>
          <input type="submit" name="button" id="button" class="btn_grey" value="增加教师" />
        </label>&nbsp;&nbsp;&nbsp;<a href="bulkTeaAdd.jsp">批量导入教师</a></td>
        <td align="center">&nbsp;</td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>