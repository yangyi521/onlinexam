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
function anscheck(eeId){
	var a=document.getElementById("ans").value;
	var re = /^[A-Z]{1,4}$/;
	if(re.test(a)){
		return true;
	}else{
		var msg = "正确答案只能为大写英文！";
		showErrorMsg(msg,eeId);
		return false;	
	}
}
function isNotNull(uuid){
	var value=document.getElementById(uuid).value;
	if(value == null || value==""){
		return false;
	}else{
		return true;
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
var obj=document.getElementById("type");
	var value=obj.selectedIndex;
	if(value==2){
		if(anscheck('ans_hint') &&isNotNull('ques')&&isNotNull('choice_A')&&isNotNull('choice_B')){
		return true;
	}else{
		alert("添加失败，请将信息填写完整、正确！");
		return false;
	}
	}else{
	if(anscheck('ans_hint') &&isNotNull('ques')&&isNotNull('choice_A')&&isNotNull('choice_B')&&isNotNull('choice_C')&&isNotNull('choice_D')){
		return true;
	}else{
		alert("添加失败，请将信息填写完整、正确！");
		return false;
	}
	}
}
function selectChoice(){
	var obj=document.getElementById("type");
	var value=obj.selectedIndex;
	if(value==2){
		document.getElementById("choice_C").style.display="none";
		document.getElementById("choice_D").style.display="none";
	}
	if(value==0 || value==1){
		document.getElementById("choice_A").style.display="block";
		document.getElementById("choice_B").style.display="block";
		document.getElementById("choice_C").style.display="block";
		document.getElementById("choice_D").style.display="block";
	}
}
</script>
</head>

<body>
<div class="text2">
  <form id="form1" name="form1" method="post" action="tea_Add_quesBank.action?currentPage=0" onsubmit="return verInput();">
    <table border="0" align="center" class="word_darkGrey">
      <tr>
        <td width="100">科目：</td>
        <td><select name="object" id="object">
        <s:iterator value="#session.courses" var="cou">
         <option value='<s:property value="#cou.cou_id" />'><s:property value="#cou.cou_name" /></option>
        </s:iterator>
        </select></td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td>题目类型：</td>
        <td><select name="type" id="type" onclick="selectChoice();">
          <option value="1" selected="selected">单项选择</option>
          <option value="2">多项选择</option>
          <option value="3">判断对错</option>
        </select></td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td >题目：</td>
        <td><label>
          <textarea name="ques" id="ques" style="resize:none;" onblur="isNotNull('ques');"></textarea>
        </label></td>
        <td width="182" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td>选项A：</td>
        <td><label>
          <input type="text" name="choice_A" id="choice_A" onblur="isNotNull('choice_A');"/>
        </label></td>
        <td align="center"></td>
      </tr>
      <tr>
        <td>选项B：</td>
        <td><label>
          <input type="text" name="choice_B" id="choice_B" onblur="isNotNull('choice_B');"/>
        </label></td>
        <td align="center"></td>
      </tr>
      <tr>
        <td>选项C：</td>
        <td><label>
          <input type="text" name="choice_C" id="choice_C" onblur="isNotNull('choice_C');"/>
        </label></td>
        <td align="center">判断题无此项</td>
      </tr>
      <tr>
        <td>选项D：</td>
        <td><label>
          <input type="text" name="choice_D" id="choice_D" onblur="isNotNull('choice_D');" />
        </label></td>
        <td align="center">判断题无此项</td>
      </tr>
      <tr>
        <td>知识点：</td>
        <td><label>
          <select name="point" id="point" >
            <s:iterator value="#session.points" var="poi">
         <option value='<s:property value="#poi.poi_id" />'><s:property value="#poi.poi_title" /></option>
        </s:iterator>
          </select>
        </label></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>正确答案：</td>
        <td><label>
          <input type="text" name="ans" id="ans" onfocus="clearErrorMsg('ans_hint')" onblur="anscheck('ans_hint')" />
        </label></td>
        <td><font color="#FF0000" size="-1"><div id="ans_hint"></div></font></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><label>
          <input type="submit" name="button" class="btn_grey" id="button" value="增加试题" />
        </label></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>