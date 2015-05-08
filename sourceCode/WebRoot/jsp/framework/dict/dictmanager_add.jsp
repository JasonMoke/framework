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
	<!--jqueryUI  -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<!--系统css  -->
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#insertDictManagerForm').validate({
			messages : {
				"dictmanager.DictName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>128</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"dictmanager.DictData1" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					maxlength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>256</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"dictmanager.DictNumber" : {
					number:'<s:text name="number_onlydigital"></s:text>',
					required :'<s:text name="tip_is_not_null"></s:text>',
					maxlength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>10</s:param></s:text>'
				},
			},
			rules : {
			 "dictmanager.DictName" : {
							required : true,
							rangelength:[1,128],
							remote: {
							url: "<%=basePath%>/dictAjax/getDictManagerByName.action", //后台处理程序
							type : "post", //数据发送方式
							dataType : "json", //接受数据格式   
							data : { //要传递的数据
							"DictListCode" : function() {
								return $("#DictListCode").val();
							},
							"DictName" : function() {
								return $("#DictName").val();
							}
							
						}
					}
				},
					 "dictmanager.DictData1" : {
							required : true,
							rangelength:[1,128],
							remote: {
							url: "<%=basePath%>/dictAjax/getDictManagerByDictData.action", //后台处理程序
							type : "post", //数据发送方式
							dataType : "json", //接受数据格式   
							data : { //要传递的数据
							"DictListCode" : function() {
								return $("#DictListCode").val();
							},
							"DictData1" : function() {
								return $("#DictData1").val();
							}
							
						}
					}
				},
			"dictmanager.DictNumber" : {
				number:true,
				required : true,
				rangelength : [ 1, 11 ]
			},
},
		});
	});
	//返回
	function __back(){
		var code = $("#DictListCode").val();
		window.location.href="<%=path%>/dict/getDictManager.action?code="+code;
	}
	//保存
	function __submit(){
	if($("#insertDictManagerForm").valid()){
	 $("#loadingdiv").show();
			document.insertDictManagerForm.action="<%=basePath%>dict/insertDictManager.action";
			document.insertDictManagerForm.submit();
		}
	}
	//校验
	function _validate() {
		art.dialog.data('_validate', $("#insertDictManagerForm").valid());
	}
</script>
</head>
<body>
	<form action="dict/insertDictManager.action" name="insertDictManagerForm" id="insertDictManagerForm" method="post"
		target="mainFrame">
	<h2 class="page-header"><s:text name="adddict_dict" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>
	<div class="controls">  
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictListCode" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictListCode"	type="text" id="DictListCode" disabled value="<s:property value="dict.dictListCode" />"> 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictName" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictName"	type="text" id="DictName" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictData1" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictData1"	type="text" id="DictData1" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictData2" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictData2"	type="text" id="dictmanager.DictData2" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictData3" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictData3"	type="text" id="dictmanager.DictData3" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictData4" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictData4"	type="text" id="dictmanager.DictData4" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictData5" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictData5"	type="text" id="dictmanager.DictData5" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_BigImage" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.BigImage"	type="text" id="dictmanager.BigImage" > 
		    </div>
		</div>	
		
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_SmallImage" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.SmallImage"	type="text" id="dictmanager.SmallImage" > 
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_list_th_DictListNumber" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dictmanager.DictNumber"	type="text" id="dictmanager.DictNumber" > 
		    </div>
		</div>	
		
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_manager_th_DictStatus" />：
		    </label>
		     <div class="col-sm-8">
		    	<CW:switch name="dictmanager.Status" id="Status" type="checkbox" checked="true"/>
		    </div>
		</div>	
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
		    </label>
		     <div class="col-sm-8">
		    </div>
		</div>	
		
		<div class="form-group col-sm-12">
		    <label class="col-sm-2 control-label text-right">
				<s:text name="dict_manager_th_DictRemark" />：
		    </label>
		    <div class="col-sm-10">
				<textarea class="form-control" cols="20"  name="dictmanager.DictRemark" rows="4" ></textarea>
			</div>
		</div>	
		
	</div>
	</form>
</body>
</html>
