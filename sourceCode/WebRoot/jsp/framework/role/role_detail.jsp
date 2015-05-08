<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:text name="user_list_page_title" />
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript">
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/role/findAllRoles.action";
		location.href=_url;
	}
</script>
</head>

<body>
<h2 class="page-header"><s:text name="detail_role" /></h2>
<div class="form-actions text-right">  
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
</div>	
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="role_list_th_rolename" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="rolemanager.RoleName" id="RoleName" type="text" disabled value="<s:property value="rolemanager.RoleName" />">
	    </div>
</div>	
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="role_list_th_rolenumber" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="rolemanager.RoleNumber" id="RoleNumber" type="text" disabled value="<s:property value="rolemanager.RoleNumber" />">
	    </div>
</div>	
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="role_list_th_rolestatus" />：
	    </label>
	    <div class="col-sm-8">
	     <CW:switch name="rolemanager.Status" id="Status" type="checkbox" checked="true" disabled="true"/>
	    </div>
</div>	
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="role_list_th_roleremark" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="rolemanager.RoleRemark" rows="4" disabled><s:property value="rolemanager.RoleRemark" /></textarea>
		</div>
</div>
</div>		
</form>
</body>
</html>
