<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXXX</title>

</head>
<body>
	<div class="pluginparentdiv">
		<TABLE class="plugin" border="0" cellSpacing="0" cellPadding="0"
			align="center">
			<TBODY>
				<TR>
					<TD class="w_main_1" colSpan="3"></TD>
				</TR>
				<TR>
					<TD class="w_main_li"></TD>
					<TD bgColor="#516276" align="center">
						<TABLE border="0" cellSpacing="0" cellPadding="0" width="785">
							<TR style="CURSOR: pointer" id="_htroleuser" class="noselect">
								<TD class="w_main_icon_open" width="39"></TD>
								<TD class="cm_c"><B id="titleServerDiskTend">从EXCEL文件导入IPQAM数据</B>
								</TD>
							</TR>
							<TR>
								<TD colSpan="2">
									<div id="stDNSAll" class="hidden" style="display: block;">
										<TABLE class="w_txt" border="0" cellSpacing="0"
											cellPadding="0" width="100%">
											<TBODY>
												<TR>
													<TD class=w_px1></TD>
													<TD bgColor=#254260></TD>
													<TD class=w_px2></TD>
												</TR>
												<TR>
													<TD bgColor=#254260></TD>
													<TD class=w_ttt bgColor=#254260>
														<div style="border: 0;" frameborder="no" border="0"
															marginwidth="0" marginheight="0" width="100%"
															scrolling="no" noresize="noresize">
															<form action="process.jsp" method="post"
																enctype="multipart/form-data">
																<br> 请选择要上传的文件:<input type="file" name="file"
																	size=50 accept="application/vnd.ms-excel"> <input
																	type="submit" value="提交">
															</form>
															<%
																Object rs = request.getAttribute("rs");
																Object sb = request.getAttribute("sb");
																if (rs != null) {
																	out.println(rs.toString());
																}
																if (sb != null) {
																	out.println(sb.toString());
																}
															%>

														</div>
													</TD>
													<TD bgColor=#254260></TD>
												</TR>
												<TR bgColor=#254260>
													<TD class=w_px3></TD>
													<TD bgColor=#254260></TD>
													<TD class=w_px4></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</TD>
							</TR>
						</TABLE>
					</TD>
					<TD class=w_main_li2>&nbsp;</TD>
				</TR>
				<TR>
					<TD class=w_main_2 colSpan=3></TD>
				</TR>
				<TR>
					<TD class=w_main_3 colSpan=3></TD>
				</TR>
			</TBODY>
		</TABLE>
	</div>
</body>



</body>
</html>