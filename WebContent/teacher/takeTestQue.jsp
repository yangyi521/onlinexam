<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
<title></title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<style>
p {
	text-indent: 2em;
}

.text2 {
	width: 450px;
	line-height: 40px;
	font-size: 16px;
	font-family: Arial, "宋体";
	margin: 0 auto;
	margin-top: 20px;
}
</style>
<script>
function InputM(){
	if(isNotNull("test_name")==true &&isNotNull("sincho")==true && isNotNull("multcho")==true && isNotNull("judg")==true &&isNotNull("control_date")==true &&testTime() && maxQueCount()){
		return true;
	}else{
		var msg="请将信息填写完整、正确！";
		alert(msg);
		return false;
	}
}

function diffDate(end) {
	var evalue = document.getElementById(end).value;
	
	var dB = new Date(evalue.replace(/-/g, "/"));
	if (new Date() > Date.parse(dB)) {
	       return false;
	} 
	return true;
}

function testTime(){
	var ret = diffDate("control_date");
	
	if(ret == false){
		alert("考试截止时间选择不能小于当前时间！");
		return false;
	}
	
	return true;
}

function showErrorMsg(msg,eeId){
	document.getElementById(eeId).style.display = "";
	document.getElementById(eeId).innerHTML = msg;
}

function isNotNull(uuid){
	var value=document.getElementById(uuid).value;
	if(value == null || value==""){
		return false;
	}else{
		return true;
	}
}
function maxQueCount(){
	var sin =document.getElementById("sincho").value;
	var mult=document.getElementById("multcho").value;
	var judg=document.getElementById("judg").value;
	
	if(sin>5){
		alert("题库数量不够，单选题所出题目请不要超过5个题！");
		return false;
	}
	if(mult>2){
		alert("题库数量不够，单选题所出题目请不要超过2个题！");
		return false;
	}
	if(judg>2){
		alert("题库数量不够，单选题所出题目请不要超过2个题！");
		return false;
	}
	return true;
}
</script>
</head>

<body onload="YearMonthDay()">
<script type="text/javascript" src="../js/Calendar3.js"></script>
	<form id="form1" name="form1" class="word_darkGrey" method="post" onsubmit="return InputM();" action='tea_Add_test.action?method=randomTest&tea_id=<s:property value="#session.teacher.tea_id" />' >
		<div class="text2">
			<p>
				<label>课程科目为： <select name="cous" id="cous">
					<s:iterator value="#session.teaAllTeach" var="tea">
						<option value='<s:property value="#tea.cou_id" />'><s:property value="#tea.cou_name" /></option>
					</s:iterator>
				</select>
				</label>
			</p>
			<p>
				<label>所属属性为： <select name="deps" id="deps">
					<s:iterator value="#session.ndep" var="dep">
						<option value='<s:property value="#dep.dep_id" />'><s:property value="#dep.dep_name" /></option>
					</s:iterator>
				</select>
				</label>
			</p>
			<p>
				试卷名称为： <label> <input type="text" name="test_name" id="test_name" onblur="isNotNull('test_name');" />
				</label> <br />
			</p>
			<% 
			Date date = new Date();
			%>
			<p>
				考试截止时间为： <label><input name="birthday" type="text" id="control_date" size="20"
      maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" 
      value='<s:date name="todayDate" format="yyyy-MM-dd"/>'/></label><br />
			</p>
			<p>
				一、单项选择题（30分） &nbsp;&nbsp; <label><input name="sincho" type="text" id="sincho" size="5" onblur="isNotNull('sincho');" /></label> 道小题 。 <br />
			</p>
			<p>
				二、多项选择题 （40分）&nbsp;&nbsp; <label><input name="multcho" type="text" id="multcho" size="5" onblur="isNotNull('multcho');" /></label> 道小题。 <br />
			</p>
			<p>
				三、判断是非题（30分） &nbsp;&nbsp; <label><input name="judg" type="text" id="judg" size="5" onblur="isNotNull('judg')" /></label> 道小题。 
			</p>
			
			<p>
			面向班级：<s:iterator value="#session.teaAllCla" var="tecl">
						<label>
      <input type="checkbox" name="teachCla" value='<s:property value="#tecl.cla_id"/>' id='teachCla_<s:property value="#tecl.tea_cou_id"/>' checked="checked"/><s:property value="#tecl.cla_name"/></label><p>&emsp;&emsp;&emsp;&emsp;&emsp;
					</s:iterator>
			</p>
			<p align="center">
			<input type="submit" class="btn_grey" value="自动出题"/>&nbsp;&nbsp;&nbsp;&nbsp;<br />
			</p>
		</div>
	</form>
</body>
</html>