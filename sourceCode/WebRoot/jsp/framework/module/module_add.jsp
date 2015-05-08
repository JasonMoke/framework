<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
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

<title><s:text name="user_add_page_title" />
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#insertModuleForm').validate({
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
							rangelength:[1,64]
				},
			 "moduleManager.ModuleNumber" : {
							 number:true,
							required : true,
							rangelength:[1,11]
				},
},
		});
	});

	function _validate() {
		art.dialog.data('_validate', $("#insertModuleForm").valid());
		
	}
	function __back(){
		parent.leftFrame.location = "<%=request.getContextPath() %>/tree/leftModuleTree.action";
		parent.formModule.location = "<%=request.getContextPath() %>/module/getModuleByCondition.action";
	}
	function __submit(){
	   if($("#insertModuleForm").valid()){
	   $("#loadingdiv").show();
		document.insertModuleForm.action="<%=basePath%>module/insertModule.action";
		document.insertModuleForm.submit();
	}
}
	//模块树
	function _ModuleTree(){
		var mouduleId = $("#moduleParent").val();
		//设置请求的url 
    	var _url = "<%=basePath%>/tree/selModuleTree.action?checkedId="+mouduleId;
    	//调用art.dialog组件弹出窗口 采用iframe方式
    	art.dialog.open(_url,
    		{ 
    		 	lock: true,//背景锁定
    	    	background: '#BFBFBF', // 背景色
    	    	opacity: 0.5,	// 透明度
    			title : '选择组织',
    			width: '400px',
    			height: '400px',
    			drag : false,//禁止拖动
    			resize: false,//禁止改变大小
    			ok : function() {
    				var iframe = this.iframe.contentWindow;
    				if (!iframe.document.body) {
    					/* 页面没有加载完毕！ */
    					alert('页面没有加载完毕！');
    					return false;
    				}
    				var checkedIds = art.dialog.data('checkedIds');
    				var checkedNames = art.dialog.data('checkedNames');
    				$("#moduleParent").val(checkedIds);
    				$("#moduleName").val(checkedNames);
    			},
    			cancel : true
    	}); 
	}
</script>
</head>

<body>
<form action="module/insertModule.action" name="insertModuleForm" id="insertModuleForm" method="post" target="mainFrame">
<h2 class="page-header"><s:text name="add_module" /></h2>
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
		  <input class="form-control" name="moduleManager.ModuleName" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleParam" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleParam" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModulePrompt" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModulePrompt" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleTarger" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleTarger" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_HelpPath" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.HelpPath" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_SmallLogo" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.SmallLogo" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_BigLogo" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.BigLogo" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_IsMenu" />：
	    </label>
	    <div class="col-sm-8">
		  <CW:switch name="moduleManager.IsMenu" id="IsMenu" type="checkbox" checked="true"/>
	    </div>
</div>
<div class="row">
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_Status" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="moduleManager.Status" id="Status" type="checkbox" checked="true"/>
		</div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_IsEntrance" />：
	    </label>
	    <div class="col-sm-8">
	    <CW:switch name="moduleManager.IsEntrance" id="IsEntrance" type="checkbox" checked="true"/>
		</div>
</div>
<div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleNumber" />：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="moduleManager.ModuleNumber" type="text">
	    </div>
</div>
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  <s:text name="module_list_th_ModuleParent" />：
	    </label>
	    <div class="input-group col-sm-7 col-sm-offset-7" >
	   		<input class="form-control" name="moduleManager.ModuleParent" id="moduleParent" type="hidden" value="<s:property value="moduleManager.ModuleParent"  />">
   			<input class="form-control" id="moduleName"  type="text" onclick="_ModuleTree()">
   			 <span class="input-group-addon" onclick="_ModuleTree()">+</span>
   		</div>
</div> 
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		  <s:text name="module_list_th_ModuleRemark" />：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="moduleManager.ModuleRemark" rows="4"></textarea>
		</div>
</div>
</div>
</div>
</form>
<div id="loadingdiv" class="loadingdiv">
    <center>
        <div class="child">
		<img src="<%=path%>/images/loading.gif" style="width:160px" />
		</div>
    </center>
 </div>
</body>
</html>
