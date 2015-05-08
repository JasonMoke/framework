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

<title><s:text name="user_list_page_title" /></title>

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
	$(document).ready(function() {
		$('#updateModuleForm').validate({
			messages : {
				"moduleManager.ModuleName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength :'<s:text name="tip_rangelength"><s:param>1</s:param><s:param>64</s:param></s:text>'
				},
				"moduleManager.ModuleNumber" : {
					number:'<s:text name="number_onlydigital"></s:text>',
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>11</s:param></s:text>'
				},
			},
			rules : {
				"moduleManager.ModuleName" : {
					required : true,
					rangelength : [ 1, 64 ]

				},
				"moduleManager.ModuleNumber" : {
					number:true,
					required : true,
					rangelength : [ 1, 11 ]

				},
			},
		});
	});

	function _validate() {
		art.dialog.data('_validate', $("#updateModuleForm").valid());

	}
	function __back(){
		parent.leftFrame.location = "<%=request.getContextPath() %>/tree/leftModuleTree.action";
		parent.formModule.location = "<%=request.getContextPath() %>/module/getModuleByCondition.action";
	}
	function __submit(){
   		if($("#updateModuleForm").valid()){
	   		$("#loadingdiv").show();
			document.updateModuleForm.action="<%=basePath%>module/updateModule.action";
			document.updateModuleForm.submit();
		}
	}
</script>
</head>

<body>
	<form action="module/updateModule.action" name="updateModuleForm" id="updateModuleForm" method="post" target="mainFrame">
	<h2 class="page-header"><s:text name="modify_module" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_ModuleName" />：
			    </label>
			    <div class="col-sm-8">
		   			<input class="form-control" name="moduleManager.ModuleName" type="text" value="<s:property value="moduleManager.ModuleName" />">
					<input class="form-control" name="moduleManager.ModuleId" type="hidden" value="<s:property value="moduleManager.ModuleId" />">
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
			    	<input class="form-control" name="moduleManager.ModuleNumber" type="text" value="<s:property value="moduleManager.ModuleNumber" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_ModuleParam" />：
			    </label>
			    <div class="col-sm-8">
			    	<input class="form-control" name="moduleManager.ModuleParam" type="text" value="<s:property value="moduleManager.ModuleParam" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_ModulePrompt" />：
			    </label>
			    <div class="col-sm-8">
			    	<input class="form-control" name="moduleManager.ModulePrompt" type="text" value="<s:property value="moduleManager.ModulePrompt" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_ModuleTarger" />：
			    </label>
			    <div class="col-sm-8">
			    	<input class="form-control" name="moduleManager.ModuleTarger" type="text" value="<s:property value="moduleManager.ModuleTarger" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_HelpPath" />：
			    </label>
			    <div class="col-sm-8">
			    <input class="form-control" name="moduleManager.HelpPath" type="text" value="<s:property value="moduleManager.HelpPath" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="module_list_th_SmallLogo" />：
			    </label>
			    <div class="col-sm-8">
			    <input class="form-control" name="moduleManager.SmallLogo" type="text" value="<s:property value="moduleManager.SmallLogo" />">
		   		</div>
		</div>   
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right"><s:text name="module_list_th_BigLogo" />：</label>
			    <div class="col-sm-8">
			    <input class="form-control" name="moduleManager.BigLogo" type="text" value="<s:property value="moduleManager.BigLogo" />">
		   		</div>
		</div> 
			  <div class="form-group col-sm-6 ">
				    <label class="col-sm-4 control-label text-right"><s:text name="module_list_th_IsEntrance" />：</label>
				    <div class="col-sm-8">
				     <CW:switch name="moduleManager.IsEntrance" id="IsEntrance" type="checkbox"/>
				    </div>
			</div>
			<div class="row">
			  <div class="form-group col-sm-6">
				    <label class="col-sm-4 control-label text-right"><s:text name="module_list_th_IsMenu" />：</label>
				    <div class="col-sm-8">
				     <CW:switch name="moduleManager.IsMenu" id="IsMenu" type="checkbox" />
				    </div>
			</div>
		   <div class="form-group col-sm-6">
				    <label class="col-sm-4 control-label text-right"><s:text name="module_list_th_Status" />：</label>
				    <div class="col-sm-8">
				     <CW:switch name="moduleManager.Status" id="Status" type="checkbox" />
				    </div>
			</div>
			 <div class="form-group col-sm-12">
				    <label class="col-sm-2 control-label text-right">
				    <s:text name="module_list_th_ModuleRemark" />：
				    </label>
				    <div class="col-sm-10">
						<textarea class="form-control" cols="20" name="moduleManager.ModuleRemark" rows="4"><s:property value="moduleManager.ModuleRemark"/></textarea>
					</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
