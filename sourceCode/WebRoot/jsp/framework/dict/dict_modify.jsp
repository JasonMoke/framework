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
		 $(document).ready(
			function() {
				$('#updateDictForm').validate(
				{
				messages : {
						"dictList.dictListName" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
							rangelength :  '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>36</s:param></s:text>'
						},
				        "dictList.dictListNumber" : {
				        number:'<s:text name="number_onlydigital"></s:text>',
							required : '<s:text name="tip_is_not_null"></s:text>',
				        	maxlength: '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>11</s:param></s:text>'
				        } 
					} ,
					rules : {
				"dictList.dictListName" : {
					required : true,
					rangelength : [ 1, 36 ]
				},
				"dictList.dictListNumber" : {
				number:true,
					required : true,
					rangelength : [ 1, 11 ]
				},
			},
		});
	});

	function _validate() {
		art.dialog.data('_validate', $("#updateDictForm").valid());
	}
	//修改
	function __submit(){
		if($("#updateDictForm").valid()){
		 $("#loadingdiv").show();
			document.updateDictForm.action="<%=basePath%>dict/updateDictList.action";
			document.updateDictForm.submit();
		}
	}
	//返回
	function __back(){
		window.location.href="<%=path%>/dict/findAllDict.action";
	}
</script>
</head>

<body>
	<form action="dict/updateDictList.action" name="updateDictForm"	id="updateDictForm" method="post" target="mainFrame">
    <h2 class="page-header"><s:text name="modify_dictlist" /></h2>
	<div class="form-actions text-right"> 
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()"> 
	</div>	
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_list_th_DictListCode" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictList.dictListCode" id="dictListCode" type="text" readonly="readonly" value="<s:property value="DictList.dictListCode" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_list_th_DictListName" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictList.dictListName" id="DictListName" type="text"  value="<s:property value="DictList.DictListName" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_list_th_DictListStatus" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="dictList.Status" id="Status" type="checkbox" checked="true" />
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="dict_list_th_DictListNumber" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="dictList.dictListNumber" id="DictListNumber" type="text"  value="<s:property value="DictList.DictListNumber" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-12">
			    <label class="col-sm-2 control-label text-right">
				  <s:text name="dict_list_th_DictListRemark" />：
			    </label>
			    <div class="col-sm-10">
					<textarea class="form-control" cols="20" name="dictList.dictListRemark" rows="4" ><s:property value="DictList.DictListRemark" /></textarea>
				</div>
		</div>
	</div>	
	</form>
</body>
</html>
