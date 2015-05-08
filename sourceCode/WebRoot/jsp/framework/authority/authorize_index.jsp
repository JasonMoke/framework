<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<!--引用struts标签  -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>授权管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<script type="text/javascript">
	function __submit(){
		var UserId=window.frames["leftFrame"].document.getElementById("UserId").value;
		var RoleId=window.frames["rightFrame"].document.getElementById("RoleId").value;
		if(UserId==""||RoleId==""){
			alert("人员或角色未选择");
		}else{
			location.href="<%=path%>/user/selRoleByUsers.action?UserId="+UserId+"&RoleId="+RoleId;
		}
	}
</script>
</head>
<body>
<h2 class="page-header">授权管理</h2>
<div class="form-actions text-right">  
	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
</div>
<iframe align="top" width="40%" height="500px" src="<%=basePath%>/jsp/framework/organ/organ_user_tree.jsp" id="leftFrame" name="leftFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" ></iframe>
<iframe align="top" width="55%" height="500px" src="<%=request.getContextPath() %>/tree/roleGroupRoleTree.action" id="rightFrame" name="rightFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="no"></iframe>
</body>
<span class="_height"></span>
</html>
