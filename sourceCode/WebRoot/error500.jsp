<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error500.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/en/edit.css" >
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/en/list.css" >
  </head>
  
  <body>
    <div class="errors" style="height: 550px;">
		
		<div class="errorbox">
			<img alt="" src="images/errora.png">
			<p>Error Code 500</p>
			<span>More Error Messenge infomation...</span>
			<input name="Button1" type="button" value="Tell Us">
		</div>
	</div>
  </body>
</html>
