<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="user_list_page_title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<!--系统css  -->
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>

<style type="text/css">
tr{
height:30px;
}
</style>
<script type="text/javascript">
	//返回
	function __back(){
		document.srcForm.action="<%=path%>/resources/findAll.action";
		document.srcForm.submit();
	}
</script>
  </head>
  <body>
    <form action="" name="srcForm" method="post">
    <h2 class="page-header"><s:text name="查看资源" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
	</div>	
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_resourcesName" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="resourcesName" id="resourcesName" type="text" disabled value="<s:property value="Resources.resourcesName" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_resourcesUrl" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.ResourcesName" id="ResourcesName" type="text" disabled value="<s:property value="Resources.resourcesUrl" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_Status" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="Resources.Status" id="status" type="checkbox" disabled="true"/>
			    </div>
		</div>	
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="是否菜单入口" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="Resources.isMenu" id="isMenu" type="checkbox" disabled="true"/>
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="排序号" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.resourcesNumber" id="resourcesNumber" type="text" disabled value="<s:property value="Resources.resourcesNumber" />">
			    </div>
		</div>	
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="资源权限集" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.permissionSet" id="permissionSet" type="text" disabled value="<s:property value="Resources.permissionSet" />">
			    </div>
		</div>	
	</div>	
    </form>
  </body>
</html>
