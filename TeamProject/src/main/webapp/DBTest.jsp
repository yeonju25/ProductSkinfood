<%@page import="java.sql.*" %>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext" %>
<%@page import="javax.naming.Context" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!

Connection conn=null;
Statement stmt=null;
ResultSet rs=null;

String url="jdbc:oracle:thin:@localhost:1521:XE";
String uid = "scott";
String pass="tiger";
String sql ="Select * from member";
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	try{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		System.out.println(conn);
		out.println("DBCP연동성공");
		
	}catch (Exception e){
		e.printStackTrace();
	} 

%>
</body>
</html>