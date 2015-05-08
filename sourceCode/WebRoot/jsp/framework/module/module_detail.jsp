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
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript">
	function __back(){
		parent.leftFrame.location = "<%=basePath%>/jsp/framework/module/module_tree.jsp";
		parent.formModule.location = "<%=request.getContextPath() %>/module/getModuleByCondition.action";
	}
</script>
</head>

<body>
	<form action="">
<h2 class="page-header"><s:text name="detail_module" /></h2>
<div class="form-actions text-right">  
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_back" />" onclick="__back()">
</div>	

<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleName" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleName" type="text" disabled value="<s:property value="moduleManager.ModuleName" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleParent" />：
	    </label>
	    <div class="col-sm-8">
	   		<input class="form-control" name="moduleManager.ModuleParent" type="text" readonly="readonly" value="<s:property value="moduleManager.ModuleParent"  />">
   		</div>
</div> 
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleNumber" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleNumber" type="text" disabled value="<s:property value="moduleManager.ModuleNumber" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleParam" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleParam" type="text" disabled value="<s:property value="moduleManager.ModuleParam" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModulePrompt" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModulePrompt" type="text" disabled value="<s:property value="moduleManager.ModulePrompt" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleTarger" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleTarger" type="text" disabled value="<s:property value="moduleManager.ModuleTarger" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_HelpPath" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.HelpPath" type="text" disabled value="<s:property value="moduleManager.HelpPath" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_SmallLogo" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.SmallLogo" type="text" disabled value="<s:property value="moduleManager.SmallLogo" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_BigLogo" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.BigLogo" type="text" disabled value="<s:property value="moduleManager.BigLogo" />">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_IsMenu" />：
	    </label>
	    <div class="col-sm-8">
		  <CW:switch name="moduleManager.IsMenu" id="IsMenu" type="checkbox" checked="true" disabled="true"/>
	    </div>
</div>
<div class="row">
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_Status" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="moduleManager.Status" id="Status" type="checkbox" checked="true" disabled="true"/>
		</div>
</div>
 <div class="form-group col-sm-6 controls-row">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_IsEntrance" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="moduleManager.IsEntrance" id="IsEntrance" type="checkbox" checked="true" disabled="true"/>
		</div>
</div>
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="module_list_th_ModuleRemark" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="moduleManager.ModuleRemark" rows="4" disabled><s:property value="moduleManager.ModuleRemark" /></textarea>
		</div>
</div>
</div>
</div>
	</form>
</body>
</html>
