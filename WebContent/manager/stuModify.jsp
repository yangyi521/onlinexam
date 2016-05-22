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
	if(number('num_hint') && username('name_hint') && password('pwd_hint') && getGen('sex_hint') && getBorn()){
		return true;
	}else{
		alert("增加失败，请按照提示信息修改");
		return false;
	}
}

function getBorn(){
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;
	//alert(year.length==0);
	if(year.length==0 || month.length==0 || day.length==0 ){
		var msg = "请选择生日！";
		showErrorMsg(msg,"tea_age_error");
		return false;
	}else{
		return true;
	}
}

//出生年月日级联菜单 
function YearMonthDay(){
   fo=document.form1;
   foday=fo.day;
   MonHead=[31,28,31,30,31,30,31,31,30,31,30,31];
 
   //设置年
   y=new Date().getFullYear();
   for(var i=(y-90);i<=y;i++)
   fo.year.options.add(new Option(i,i));
   fo.year.options.value=y;//current year
 
    //设置月
   m=new Date().getMonth();
   for(i=1;i<=12;i++)
   fo.month.options.add(new Option(i,i));
   fo.month.options.value=m+1;//current month
 
   //设置日
   d=new Date().getDay();
   n=MonHead[m];
   if(m==1&&IsRunYear(yearValue))
   n++;
   day(n);
   fo.day.options.value=d+1;//curren day
}
   //onchange of year
function yy(str){
   monthValue=fo.month.options[fo.month.selectedIndex].value;
   if(monthValue==""){
      var foday=document.form1.day;
   optionClear(foday);
   return;
    }
   var n=MonHead[monthValue-1];
   if(monthValue==2&&IsRunYear(str)) n++;
   day(n);
}
   //onchange of month
function mm(ab){
  yearValue=fo.year.options[fo.year.selectedIndex].value;
 if(yearValue==""){
  optionClear(foday);
  return;
 }
 var n=MonHead[ab-1];
 if(ab==2&&IsRunYear(yearValue)) n++;
 day(n);
}
 
function day(ab){
   optionClear(foday);
   for(var i=1;i<=ab;i++)
   foday.options.add(new Option(i,i));
 }
 
function optionClear(ab){
   for(var i=ab.options.length;i>0;i--)
   ab.remove(i);
}
function  IsRunYear(year){
   return(0==year%400&&(year%100!=0 || year%4==0));
}
</script>
</head>

<body onload="YearMonthDay()">
<script type="text/javascript" src="../js/Calendar3.js"></script>
<div class="text2">
  <form action="sys_Update_stuManagement.action" method="post" enctype="multipart/form-data" name="form1" id="form1" onsubmit="return verInput();">
    <table width="662" border="0" align="center" class="word_darkGrey">
      <tr>
        <td>学号：</td>
        <td><s:property value="#session.stu.stu_id"/></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="num_hint">学号不能修改</div></font></td>
      </tr>
      <tr>
        <td width="132">姓名：</td>
        <td width="195"><label>
          <input type="text" name="username" id="username" onfocus="clearErrorMsg('name_hint')" onblur="username('name_hint')" value='<s:property value="#session.stu.stu_name"/>' />
        </label></td>
        <td width="321" align="center"><font color="#FF0000" size="-1"><div id="name_hint"></div></font></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><label>
          <input type="text" name="password" id="password" onfocus="clearErrorMsg('pwd_hint')" onblur="password('pwd_hint')" value='<s:property value="#session.stu.stu_password"/>' />
        </label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="pwd_hint"></div></font></td>
      </tr>
      <tr>
        <td>性别：</td>
        <td><label> <s:if
								test='%{#session.stu.stu_sex=="男"}'>
								<input type="radio" name="stu_sex" id="stu_sex" value="男"
									checked="checked" />男
              <input type="radio" name="stu_sex" id="stu_sex" value="女" />女
            </s:if> <s:else>
								<input type="radio" name="stu_sex" id="stu_sex" value="男" />男
              <input type="radio" name="stu_sex" id="stu_sex" value="女"
									checked="checked" />女
            </s:else>
					</label></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="sex_hint"></div></font></td>
      </tr>
      <tr>
        <td>出生日期：</td>
        <td width="250"><input name="birthday" type="text" id="control_date" size="20"
      maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" 
      value='<s:date name="#session.stu.stu_born" format="yyyy-MM-dd"/>'/></td>
        <td align="center"><font color="#FF0000" size="-1"><div id="age_hint"></div></font></td>
      </tr>
      <tr>
        <td>所在班级：</td>
        <td><select name="classes" id="classes">
        	<s:iterator value="#session.cls" var="cla">
          <option value='<s:property value="#cla.cla_id" />'><s:property value="#cla.cla_name" /></option>
          </s:iterator>
        </select></td>
        <td><font color="#FF0000" size="-1"><div id="obj_hint"></div></font></td>
      </tr>
      <tr>
        <td>所属属性：</td>
        <td><select name="dep" id="dep">
        	<s:iterator value="#session.des" var="dep">
          <option value='<s:property value="#dep.dep_id" />'><s:property value="#dep.dep_name" /></option>
          </s:iterator>
        </select></td>
        <td><font color="#FF0000" size="-1"><div id="obj_hint"></div></font></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><label>
          <input type="submit" name="button" id="button" class="btn_grey" value="修改学生" />
        </label></td>
        <td align="center">&nbsp;</td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>