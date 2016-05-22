<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网络在线考试</title>
<html:base />
<link href="../css/style.css" rel="stylesheet">
</head>

<body>
<body>

<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="131" background="Images/top_bg.jpg">&nbsp;</td>
  </tr>
</table>
<table width="778" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"></td>
        </tr>
      <tr>
        <td align="center" valign="top">
<form action='stu_Find_exam.action?test_id=<s:property value="#near.test_id" />' method="post">
 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="51" colspan="3" align="center" class="word_orange1">考试规则</td>
</tr>
  <tr class="txt_grey">
    <td width="12%">&nbsp;</td>
    <td width="77%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;网络在线考试系统不允许对网页进行刷新、后退等操作，否则后果自负。如果在规定的考试时间内没有交卷，系统将自动提交试卷。每位考生同一个课程只能考一次；系统会及时通知考试的具体时间；<br>
&nbsp;&nbsp;&nbsp;&nbsp;只有同意以上规则才可以进行考试。如果出现问题或者未找到相关的考试课程，请与管理员联系。	</td>
    <td width="11%">&nbsp;</td>
  </tr>
  <tr height="40">
    <td>&nbsp;</td>
    <td align="center"  valign="middle"><input type="submit" value="同意" class="btn_grey" /></td>
    <td>&nbsp;</td>
  </tr>
</table> 
</form>
        </td>
      </tr>
    </table>
</td>
  </tr>
</table>
</body>
</body>
</html>