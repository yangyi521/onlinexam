<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
div{
	width:200px;
	margin:0 auto;}
}

</style>
</head>

<body>
<div>
<form action="sys_Add_bulkStuAdd.action" method="post" enctype="multipart/form-data" name="form1" id="form1">
  <table width="300" border="0">
  <tr>
  	<td height="20"></td>
  </tr>
    <tr>
      <td height="70">
        <p class="word_darkGrey">请将按照格式编写好的excel文档放在<font color="red">D:\onlinexam</font>目录下。并取名为<font color="red">Student.xls</font>。然后点击批量增加按钮。</p>
      </td>
    </tr>
    <tr>
    	<td><p class="word_darkGrey">注：如需删除Excel中的记录，请右键该记录选择删除。如若只清空而不删除可能会造成批量添加失败。</p></td>
    </tr>
    
    <tr>
      <td align="center" height="50">
      <p></p>
      <label>
        <input type="submit" name="button" id="button" class="btn_grey" value="批量添加学生" />
      </label></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</div>
<div>${msg }
</div>
</body>
</html>