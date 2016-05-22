<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hp.bean.Questions"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style>
p {
	text-indent: 2em;
}
</style>
</head>

<body>
	<%
		Questions[] sin_ques = (Questions[]) session
				.getAttribute("testsin");//单选
		Questions[] mult_ques = (Questions[]) session
				.getAttribute("testmult");//多选
		Questions[] judg_ques = (Questions[]) session
				.getAttribute("testjudg");//判断

		int k = 1;
	%>
	<div id="main_content">
		<form id="form1" name="form1" method="post" action="backIndex?method=tea" target="_parent">
			<h1>
				<center>
					<s:property value="#session.newTest.test_name" />
				</center>
			</h1>
			<p>
				<b>一、单项选择题（30分）</b>
			</p>
			<%
			if(sin_ques.length!=0){
			
				double sin_score = 30.0 / sin_ques.length;
				session.setAttribute("sin_score", sin_score);
				for (int i = 0; i < sin_ques.length; i++) {
			%>
			<p></p>
			<p class="text">
				<%=k%>.<%=sin_ques[i].getQ_title()%>
			</p>
			<p></p>
			
			<p>
				A.<%=sin_ques[i].getQ_itemA()%></p>
			<p>
				B.<%=sin_ques[i].getQ_itemB()%></p>
			<p>
				C.<%=sin_ques[i].getQ_itemC()%></p>
			<p>
				D.<%=sin_ques[i].getQ_itemD()%></p>


			<p class="text">
				<label> <input type="radio"
					name="choice_<%=sin_ques[i].getQ_id()%>" value="A" id="choice_A" />
					A.
				</label> <label> <input type="radio"
					name="choice_<%=sin_ques[i].getQ_id()%>" value="B" id="choice_B" />
					B.
				</label> <label> <input type="radio"
					name="choice_<%=sin_ques[i].getQ_id()%>" value="C" id="choice_C" />
					C.
				</label> <label> <input type="radio"
					name="choice_<%=sin_ques[i].getQ_id()%>" value="D" id="choice_D" />
					D.
				</label>
			</p>
			<p>&nbsp;</p>
			<%
				k++;
				}
			}
			
			%>
			<p>&nbsp;</p>
			<p>
				<b>二、多项选择题（40分）</b>
			</p>
			<p></p>
			<%
			if(mult_ques.length!=0){
			
				double mult_score = 40.0 / mult_ques.length;
				session.setAttribute("mult_score", mult_score);
				for (int i = 0; i < mult_ques.length; i++) {
			%>
			<p class="text">
				<%=k%>.<%=mult_ques[i].getQ_title()%>
			</p>
			
			<p>
				A.<%=mult_ques[i].getQ_itemA()%></p>
			<p>
				B.<%=mult_ques[i].getQ_itemB()%></p>
			<p>
				C.<%=mult_ques[i].getQ_itemC()%></p>
			<p>
				D.<%=mult_ques[i].getQ_itemD()%></p>
			<br />

			<p class="text">
				<label> <input type="checkbox"
					name="checkbox_<%=mult_ques[i].getQ_id()%>" value="A"
					id="checkbox_A" /> A.
				</label> <label> <input type="checkbox"
					name="checkbox_<%=mult_ques[i].getQ_id()%>" value="B"
					id="checkbox_B" /> B.
				</label> <label> <input type="checkbox"
					name="checkbox_<%=mult_ques[i].getQ_id()%>" value="C"
					id="checkbox_C" /> C.
				</label> <label> <input type="checkbox"
					name="checkbox_<%=mult_ques[i].getQ_id()%>" value="D"
					id="checkbox_D" /> D.
				</label>
			</p>
			<p>&nbsp;</p>
			<%
				k++;
				}
			}
			%>
			<p>&nbsp;</p>
			<p>
				<b>三、判断是非题（30分）</b>
			</p>
			<%
			if(judg_ques.length!=0){
			
				double judg_score = 30.0 / judg_ques.length;
				session.setAttribute("judg_score", judg_score);
				for (int i = 0; i < judg_ques.length; i++) {
			%>
			<p></p>
			<p class="text">
				<%=k%>.<%=judg_ques[i].getQ_title()%>
			</p>

			<p>
				<label> <input type="radio"
					name="judge_<%=judg_ques[i].getQ_id()%>" value="A" id="judge_A" />
					正确
				</label> <label> <input type="radio"
					name="judge_<%=judg_ques[i].getQ_id()%>" value="B" id="judge_B" />
					错误
				</label>

			</p>
			<p>&nbsp;</p>
			<%
				k++;
				}
			}
			%>
			<p class="text"></p>
			<p>&nbsp;</p>
			<label>
				<center>
					<input type="submit" name="button" id="button" class="btn_grey" value="发布试卷" />
				</center>
			</label>
		</form>
	</div>
</body>
</html>