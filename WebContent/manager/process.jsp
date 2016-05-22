<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ page import="java.util.*"%> 
<%@ page import="javax.servlet.*"%> 
<%@ page import="jxl.*"%> 
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>
<body>
 <%
       
        Connection conn = null;
        Statement stmt = null; 
        StringBuffer sb=new StringBuffer();
        String rs=null;
        String sql = null;
        try {
            ServletInputStream is = request.getInputStream();
            
            byte[] junk = new byte[1024];
            int bytesRead = 0;
            bytesRead = is.readLine(junk, 0, junk.length);
            bytesRead = is.readLine(junk, 0, junk.length);
            bytesRead = is.readLine(junk, 0, junk.length);
            bytesRead = is.readLine(junk, 0, junk.length);
            Workbook workbook = Workbook.getWorkbook(is);
            //System.out.println(workbook.getSheets().length);
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/hy";
            conn = DriverManager.getConnection(url, "xpqam", "xpqam");
            stmt = conn.createStatement();
            sql = "delete from hy_xpqam";
            stmt.execute(sql);
            for (int i = 0; i < workbook.getSheets().length; i++) {
                Sheet sheet = workbook.getSheet(i);
                String roomname = sheet.getName();
                for (int j = 2; j < sheet.getRows(); j++) {
                  /*
                   Cell ip=cell[0];
                   Cell frename=cell[1];
                   Cell frequency=cell[2];
                   Cell groupname=cell[3];
                
                   Cell ip=cell[1];
                   Cell frename=cell[2];
                   Cell frequency=cell[3];
                   Cell groupname=cell[0];
                   */
                    Cell[] cell = sheet.getRow(j);
                    String ip = cell[0].getContents();
                    String frename = cell[1].getContents();
                    String frequency = cell[2].getContents();
                    String groupname = cell[3].getContents();
                    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");

                    if (ip  != null && frename  != null && frequency  != null && groupname  != null 
                         &&!"".equals(ip) && !"".equals(frename) && !"".equals(frequency)
                         && !"".equals(groupname) && pattern.matcher(groupname).matches()) {

                        String newfrequency = frequency.replace("，", ",");

                        sql = "insert into hy_xpqam(roomname,groupname,ip,frename,frequency)values('"
                                + roomname + "', " + groupname.trim()
                                + " ,'" + ip.trim() + "','" + frename.trim()
                                + "','" + newfrequency.trim() + "')";
                    //   out.println("<p>"+j + 1 + "<<---" + sql+"</p>");
                        stmt.execute(sql);
                        
                    } else {
                         sql = "insert into hy_xpqam(roomname,groupname,ip,frename,frequency)values('"
                                + roomname + "', " + groupname  + " ,'" + ip 
                                + "','" + frename  + "','" + frequency  + "')";
                     //    System.out.println(j + 1 + "---" + sql);
                         sb.append("工作表["+roomname+"]第["+(j + 1) + "]行错误：" + sql+"<br>");
                    }
                }
            }
            
            CallableStatement proc =conn.prepareCall("{call PROC_IPQAMUPDATE}");
            proc.execute();
         request.setAttribute("sb", sb.toString());
         request.setAttribute("rs", "<p>数据导入成功!</p>");
         request.getRequestDispatcher("upload.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("rs", "<p>数据导入失败!</p>");
        }finally{
          
          
            try{
                if(stmt!=null){
                    stmt.close();
                    stmt=null;
                }
                if(conn!=null){
                    conn.close();
                    conn=null;
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
             
        }
    %>

</body>
</html>