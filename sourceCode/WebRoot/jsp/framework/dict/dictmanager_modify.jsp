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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
<!--系统css  -->
<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
<!--通用js  -->
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<!--artDialog-->
<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#updateDictmanagerForm').validate({
			messages : {
				"dictmanager.dictName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>128</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"dictmanager.dictData1" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					maxlength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>256</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"dictmanager.dictNumber" : {
				number:'<s:text name="number_onlydigital"></s:text>',
					required : '<s:text name="tip_is_not_null"></s:text>',
					maxlength :'<s:text name="tip_rangelength"><s:param>1</s:param><s:param>10</s:param></s:text>'
				}
			},
			rules : {
			 "dictmanager.dictName" : {
							required : true,
							rangelength:[1,128],
							remote: {
							url: "<%=basePath%>/dictAjax/getDictManagerByNameID.action", //后台处理程序
							type : "post", //数据发送方式
							dataType : "json", //接受数据格式   
							data : { //要传递的数据
							"DictListCode" : function() {
								return $("#dictListCode").val();
							},
							"DictName" : function() {
								return $("#dictName").val();
							},
							"DictID" : function() {
								return $("#dictId").val();
							}
							
						}
					}
				},
			"dictmanager.dictData1" : {
			required : true,
							rangelength:[1,128],
							remote: {
							url: "<%=basePath%>/dictAjax/getDictManagerByDictDataID.action", //后台处理程序
							type : "post", //数据发送方式
							dataType : "json", //接受数据格式   
							data : { //要传递的数据
							"DictListCode" : function() {
								return $("#dictListCode").val();
							},
							"DictData1" : function() {
								return $("#dictData1").val();
							},
							"DictID" : function() {
								return $("#dictId").val();
							}
							
						}
					}
				},
			"dictmanager.dictListNumber" : {
			number:true,
				required : true,
				rangelength : [ 1, 11 ]
			},
},
		});
	});

	function _validate() {
		art.dialog.data('_validate', $("#updateDictmanagerForm").valid());
	}
	//返回
	function __back(){
  		var code = $("#dictListCode").val();
		window.location.href="<%=path%>/dict/getDictManager.action?code="+code;
		}
	//修改
	function __submit(){
		if($("#updateDictmanagerForm").valid()){
		 $("#loadingdiv").show();
				document.updateDictmanagerForm.action="<%=basePath%>dict/updateDictmanager.action";
				document.updateDictmanagerForm.submit();
		}
	}
</script>
</head>

<body>
	<form action="dict/updateDictmanager.action" name="updateDictmanagerForm" id="updateDictmanagerForm" method="post" target="mainFrame">
	<input name="dictmanager.dictId" id="dictId" type="hidden" value="<s:property value="Dictmanager.dictId" />">
	<h2 class="page-header"><s:text name="modify_dict" /></h2>
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
				  <input class="form-control" name="dictmanager.dictListCode" id="dictListCode" type="text" readonly="readonly" value="<s:property value="Dictmanager.dictListCode" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictName" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictName" id="dictName" type="text" value="<s:property value="Dictmanager.dictName" />">
			    </div>
		</div>

		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_ParentDictId" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.parentDictId" id="parentDictId" type="text" readonly="readonly" value="<s:property value="Dictmanager.parentDictId" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData1" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictData1" id="dictData1" type="text" value="<s:property value="Dictmanager.dictData1" />">
			    </div>
		</div>
		
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData2" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictData2" id="dictData2" type="text" value="<s:property value="Dictmanager.dictData2" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData3" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictData3" id="dictData3" type="text" value="<s:property value="Dictmanager.dictData3" />">
			    </div>
		</div>
		
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData4" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictData4" id="dictData4" type="text" value="<s:property value="Dictmanager.dictData4" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_manager_th_DictData5" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictData5" id="dictData5" type="text" value="<s:property value="Dictmanager.dictData5" />">
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
				  <s:text name="dict_manager_th_DictNumber" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictmanager.dictNumber" id="dictNumber" type="text" value="<s:property value="Dictmanager.dictNumber" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-12">
			    <label class="col-sm-2 control-label text-right">
				  <s:text name="dict_manager_th_DictRemark" />：
			    </label>
			    <div class="col-sm-10">
					<textarea class="form-control" cols="20" name="dictmanager.dictRemark" rows="4"><s:property value="Dictmanager.dictRemark" /></textarea>
				</div>
		</div>
	</div>	
</form>
</body>
</html>
