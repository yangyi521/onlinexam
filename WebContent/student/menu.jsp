<%@page import="com.hp.bean.Classes"%>
<%@ page contentType="text/html; charset=Utf-8" import="java.util.*,java.sql.*" %>   
<%
   	String sort=new String(request.getParameter("sort").getBytes("iso-8859-1"),"UTF-8");   
       System.out.print("sort="+sort);   
       String dbDriver = "com.mysql.jdbc.Driver"; //连接sql数据库的方法   
       String url = "jdbc:mysql://localhost:3306/onlinexam?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";   
       String userName = "root";
       String password = "123456";
       Connection conn = null;
       Statement st = null;
       ResultSet rs = null;
       List<Classes> lists = new ArrayList<Classes>();   
     
       try{   
           Class.forName(dbDriver);    
       } catch(java.lang.ClassNotFoundException e) {   
           e.printStackTrace();   
       }   
     
       try {   
           conn = DriverManager.getConnection(url);    
           st=conn.createStatement();   
           rs=st.executeQuery("SELECT * FROM t_class WHERE dep_id='"+sort+"'");   
           while (rs.next())   
           {   
           	Classes cla = new Classes();
           	cla.setCla_id(rs.getInt(1));
           	cla.setCla_name(rs.getString(2));
           	cla.setDep_id(rs.getInt(3));
           	System.out.print("cla.cla_name="+cla.getCla_name());
               lists.add(cla);
           }   
           rs.close();   
           st.close();   
           conn.close();   
       }  catch (Exception e)  {      
           e.printStackTrace();   
       }   
       response.setContentType("text/xml; charset=utf-8");   
       response.setHeader("Cache-Control", "no-cache");   
       //必须要写下面这一行，不然取出的XML长度为0   
       response.getWriter().write("<?xml   version=\"1.0\"   encoding=\"utf-8\"?>");       
       out.println("<response>");   
       for(int i=0;i<lists.size();i++)   
       {   
           //out.println("<res>" + lists.get(i).getCla_id() + "</res>");
           out.println("<res>" + lists.get(i).getCla_name() + "</res>");   
       }   
       out.println("</response>");
   %>