<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hp.bean.Questions" %>
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
<title><s:property value="#session.stuDetailedTest.test_name" /></title>
<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
<style>
p {
	text-indent: 2em;
}

.text2 {
	width: 700px;
	line-height: 40px;
	font-size: 9pt;
	color: #4E4E4E;
	font-family: Arial, "宋体";
	margin: 0 auto;
	margin-top: 20px;
}
</style>
</head>

<body>

		<img src="<%=path %>/img/logo.jpg" width="1345" align="top" />

	<div id="main_content">
		<h1>
			<center><s:property value="#session.stuDetailedTest.test_name" /></center>
		</h1>
		<div id="time">考试用时<s:property value="#session.stuDetailedTest.stu_test_time" /></div>
		<center>
			<p><s:property value="#session.stuDetailedTest.cou_name" />&nbsp;&nbsp;&nbsp;<s:property value="#session.stuDetailedTest.cla_name" />&nbsp;&nbsp;&nbsp;<s:property value="#session.stuDetailedTest.stu_name" /></p>
		</center>
		<% 
			
			Questions[] sin_ques = (Questions[])session.getAttribute("sin_ques");//单选
			Questions[] mult_ques = (Questions[])session.getAttribute("mult_ques");//多选
			Questions[] judg_ques = (Questions[])session.getAttribute("judg_ques");//判断
			int[] stu_wrong_ques =(int[])session.getAttribute("stu_wrong_ques");
			String[] stu_wrong_ans =(String[])session.getAttribute("stu_wrong_ans");
			int k=1;
			int flag=0;
		%>
			<div class="word_darkGrey">
			<p>
				<b>一、单项选择题（30分）</b>
			</p>
			<%
				for(int i=0; i<sin_ques.length; i++){
				flag=0;
			%>
					
					<p></p>
					<p class="text"><%=k %>.<%=sin_ques[i].getQ_title() %></p>
					<p>A.<%=sin_ques[i].getQ_itemA() %></p>
					<p>B.<%=sin_ques[i].getQ_itemB() %></p>
					<p>C.<%=sin_ques[i].getQ_itemC() %></p>
					<p>D.<%=sin_ques[i].getQ_itemD() %></p>
					<br />
					<% 
					k++;
						for(int j=0; j<stu_wrong_ques.length; j++){
							//如果有错题（错题编号和题号一致）
							if(sin_ques[i].getQ_id()==stu_wrong_ques[j]){
								%>
								<p>您的答案：<font color="red"><%=stu_wrong_ans[j] %> </font>&nbsp;&nbsp;&nbsp; 正确答案：<%=sin_ques[i].getQ_ans() %></p>
								<%
								flag++;
							}
						}
						//没有flag就证明没有错题
						if(flag==0){
							%>
							<p>您的答案：<%=sin_ques[i].getQ_ans() %> &nbsp;&nbsp;&nbsp; 正确答案：<%=sin_ques[i].getQ_ans() %></p>
						<%
						}
					%>
					<br />
			<%
				}
			%>
			<br />
			<p>
				<b>二、多项选择题（40分）</b>
			</p>
			<p></p>
			
			<% 
				for(int i=0; i<mult_ques.length; i++){
			%>
					<p class="text"><%=k %>.<%=mult_ques[i].getQ_title() %></p>
					<p>A.<%=mult_ques[i].getQ_itemA() %></p>
					<p>B.<%=mult_ques[i].getQ_itemB() %></p>
					<p>C.<%=mult_ques[i].getQ_itemC() %></p>
					<p>D.<%=mult_ques[i].getQ_itemD() %></p>
					<br />
					<% 
					k++;
					flag=0;//重置flag=0
					for(int j=0; j<stu_wrong_ques.length; j++){
						//如果有错题（错题编号和题号一致）
						if(mult_ques[i].getQ_id()==stu_wrong_ques[j]){
							%>
							<p>您的答案：<font color="red"><%=stu_wrong_ans[j] %> </font>&nbsp;&nbsp;&nbsp; 正确答案：<%=mult_ques[i].getQ_ans() %></p>
							<%
							flag++;
						}
					}
					//flag==0证明没有错题
					if(flag==0){
						%>
							<p>您的答案：<%=mult_ques[i].getQ_ans() %> &nbsp;&nbsp;&nbsp; 正确答案：<%=mult_ques[i].getQ_ans() %></p>
							<%
					}
					%>
					<br />
			<%
				}
			%>
			<br />
			<p>
				<b>三、判断是非题（30分）</b>
			</p>
			<p></p>
			<%
				for(int i=0; i<judg_ques.length; i++){
				
			%>
					<p class="text"><%=k %>.<%=judg_ques[i].getQ_title() %></p>
					<p>A.<%=judg_ques[i].getQ_itemA() %></p>
					<p>B.<%=judg_ques[i].getQ_itemB() %></p>
					<br />
					<% 
					k++;
					flag=0;//重置flag=0
					for(int j=0; j<stu_wrong_ques.length; j++){
						//如果有错题（错题编号和题号一致）
						if(judg_ques[i].getQ_id()==stu_wrong_ques[j]){
							%>
							<p>您的答案：<font color="red"><%=stu_wrong_ans[j] %> </font>&nbsp;&nbsp;&nbsp; 正确答案：<%=judg_ques[i].getQ_ans() %></p>
							<%
							flag++;
						}
					}
					//flag==0证明没有错题
					if(flag==0){
							%>
							<p>您的答案：<%=judg_ques[i].getQ_ans() %> &nbsp;&nbsp;&nbsp; 正确答案：<%=judg_ques[i].getQ_ans() %></p>
							<%
						}
					%>
					<br />
					<%
				}
			%>
					</div>
			
					<h3 class="subTitle">考试结果</h3>
					<div class="text2">
						<s:property value="#session.student.stu_name" />同学：<br /> 您的<s:property value="#session.stuDetailedTest.cou_name" />考试分数为：<font color="#FF0000"><s:property value="#session.stuDetailedTest.stu_test_score" /></font>分 <br />
						考试评估：<br /><s:property value="#session.stuDetailedTest.stu_test_evaluate" escape="false" /><br />
					</div>
					<!--  <div class="text2">
						<a href="student/index.html">&lt;&lt; 返回首页</a>
					</div>  -->
	</div>
</body>
</html>

