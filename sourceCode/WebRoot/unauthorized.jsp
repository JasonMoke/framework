<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>unauthorized</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
  </head>
  
  <body>
   <div class="container">
	<div class="jumbotron">
	<div class="row text-center">
	<br>
	<br>
	<img alt="" src="images/errorc.png">
	<br>
	<br>
	<h2>权限不足&nbsp;&nbsp;&nbsp;<small>请联系管理员</small></h2>
	<br>
	<br>
	<button type="button" class="btn btn-primary btn-lg btn-block" onclick="history.go(-1)" style="height:40px">返回上一步</button>
	<br>
	<br>
	<br>
	</div>
	</div>
	</div>
  </body>
</html>
