<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
//密码信息验证
function validateUpsw(ueId,eeId){
	var upsw = document.getElementById(ueId).value;
	if(upsw.length < 6 || upsw.length > 20){
		var msg = "密码长度在6~20位之间";
		showErrorMsg(msg,eeId);
		return false;
	}
	
	var pattern = /^[a-zA-Z0-9_]{6,20}$/;
	if(pattern.test(upsw)){
		return true;
	}else {
		var msg = "密码包含非法字符";
		showErrorMsg(msg,eeId);
		return false;
	}
}

//密码校验验证
function validatePsw(ueId1,ueId2,eeId){
	var psw = document.getElementById(ueId1).value;
	var pswre = document.getElementById(ueId2).value;
	if(psw != pswre){
		var msg = "两次输入的密码不一样！";
		showErrorMsg(msg,eeId);
		return false;
	}else return true;
}
function showErrorMsg(msg,eeId){
	document.getElementById(eeId).style.display = "";
	document.getElementById(eeId).innerHTML = msg;
}
function clearErrorMsg(eId){
	document.getElementById(eId).style.display = "none";	
}
function verInput(){
	if(validatePsw('password','chkpassword','pswre_hint') && validateUpsw('repassword','psw_hint') && validateUpsw('password','psw_hint')){
		
		return true;
	}else{
		alert("格式有错，请按照提示信息修改！");
		return false;
	}
}

</script>
</head>

<body>
<div class="text2">
<form id="form1" name="form1" method="post" action='stu_Update_personalInfo.action?method=changePwd&stu_id=<s:property value="#session.student.stu_id" />' onsubmit="return verInput()">
<table width="494" align="center" class="word_darkGrey">
    <tr>
        <td width="127">原密码：</td>
        <td width="124"><input type="password" name="repassword" id="repassword" onfocus="clearErrorMsg('psw_hint')" onblur="validateUpsw('repassword','psw_hint')" /></td>
        <td width="193"><font color="#FF0000" size="-1"><div id="psw_hint">&nbsp;</div></font></td>
    </tr>
    <tr>
        <td>新密码：</td>
        <td><input type="password" name="password" id="password" onfocus="clearErrorMsg('psw_hint2')" onblur="validateUpsw('password','psw_hint2')"  /></td>
        <td><font color="#FF0000" size="-1"><div id="psw_hint2">&nbsp;</div></font></td>
    </tr>
    <tr>
        <td>请再次输入密码：</td>
        <td><input type="password" name="chkpassword" id="chkpassword" onfocus="clearErrorMsg('pswre_hint')" onblur="validatePsw('password','chkpassword','pswre_hint')" /></td>
        <td><font color="#FF0000" size="-1"><div id="pswre_hint">&nbsp;</div></font></td>
    </tr>
    <tr>
    <td colspan="3" align="center"><label>
      <input type="submit" name="button" id="button" class="btn_grey" value="修改密码" />
    </label></td>
  </tr>
</table>
</form>
</div>
<div>${msg }</div>
</body>
</html>