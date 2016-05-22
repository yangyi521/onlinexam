<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网络在线考试</title>
<link href="<%=basePath %>/css/style.css" rel="stylesheet" />
</head>

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
<s:if test="%{#session.student!=null}">
<form action="backIndex.action" method="post" target="_parent">
 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="51" colspan="3" align="center" class="word_orange1">错误页面</td>
</tr>
  <tr class="txt_grey">
    <td width="12%">&nbsp;</td>
    <td width="77%" align="center">您的操作有错误，请返回首页。<br></td>
    <td width="11%">&nbsp;</td>
  </tr>
  <tr height="40">
    <td>&nbsp;</td>
    <td align="center"  valign="middle"><input type="submit" value="返回首页" class="btn_grey" /></td>
    <td>&nbsp;</td>
  </tr>
</table> 
</form>
</s:if>
<s:elseif test="%{#session.manager!=null}">
<form action="backIndex.action?method=sys" method="post" target="_parent">
 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="51" colspan="3" align="center" class="word_orange1">错误页面</td>
</tr>
  <tr class="txt_grey">
    <td width="12%">&nbsp;</td>
    <td width="77%" align="center">您的操作有错误，请返回首页。<br></td>
    <td width="11%">&nbsp;</td>
  </tr>
  <tr height="40">
    <td>&nbsp;</td>
    <td align="center"  valign="middle"><input type="submit" value="返回首页" class="btn_grey" /></td>
    <td>&nbsp;</td>
  </tr>
</table> 
</form>
</s:elseif>
<s:else>
<form action="backIndex.action?mathod=tea" method="post" target="_parent">
 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="51" colspan="3" align="center" class="word_orange1">错误页面</td>
</tr>
  <tr class="txt_grey">
    <td width="12%">&nbsp;</td>
    <td width="77%" align="center">您的操作有错误，请返回首页。<br></td>
    <td width="11%">&nbsp;</td>
  </tr>
  <tr height="40">
    <td>&nbsp;</td>
    <td align="center"  valign="middle"><input type="submit" value="返回首页" class="btn_grey" /></td>
    <td>&nbsp;</td>
  </tr>
</table> 
</form>
</s:else>
        </td>
      </tr>
    </table>
</body>
</html>