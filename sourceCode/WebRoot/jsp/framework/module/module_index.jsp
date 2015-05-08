<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/commons/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commons/getExplorerVersion.js"></script>
	<style type="text/css">._height{display:block;clear:both;}</style>
	<script type="text/javascript">
	var isLoadAuto = false;
	//调整iframe高度
	function iframeAuto() 
	{ 
	try 
		{ 
			//定位需要调整的frame框架
			var a = document.getElementsByName("formModule"); 
			for(var i=0; i<a.length; i++)
			{ 
				var h = 0;
				$(window.frames["formModule"].document).find("._height").each(function(i){
			 		if($(this).offset().top>h){
			 		 	h = $(this).offset().top;
			 		}
				});
				if(getExplorerType()=="IE"&&isLoadAuto==true){
					h = h - 54;
				}
				a[i].style.height =  h +"px"; 
				isLoadAuto = true;
			} 
		} 
	catch (ex){} 
	} 
	window.setInterval(iframeAuto, 200); 
	</script>
</head>
	<body>
	<iframe align="top" width="15%" height="95%" src="<%=request.getContextPath() %>/tree/leftModuleTree.action?type=1" id="leftFrame" name="leftFrame" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" ></iframe>
	<iframe align="top" width="83%" height="95%" src="<%=request.getContextPath() %>/module/getModuleByCondition.action?isAll=1" id="formModule" name="formModule" marginwidth="0" marginheight="0" frameborder="0" scrolling="no"></iframe>
	</body>
	<span class='_height'></span>
</html>