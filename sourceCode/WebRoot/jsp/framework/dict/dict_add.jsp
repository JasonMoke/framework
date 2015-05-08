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
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript">
	/*!
		 * 
		 * 绑定验证
		 * 
		 * 注意1：所有的验证规则统一在此处进行定义
		 * 注意2：当input中name使用pentity.name这样的命名方式时,其中"."与JQUERY冲突, 则rules: {"pentity.name": {}} 要加上双引号 
		 * 
		 */
		 $(document).ready(
			function() {
				$('#insertDictForm').validate(
				{
				messages : {
						 "dict.dictListCode" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
							rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>32</s:param></s:text>',
							remote:'<s:text name="operation_Alreadyexists"></s:text>'
						},
						"dict.dictListName" : {
							required : '<s:text name="tip_is_not_null"></s:text>',
							rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>36</s:param></s:text>'
						},
				        "dict.dictListNumber" : {
				        	number:'<s:text name="number_onlydigital"></s:text>',
							required : '<s:text name="tip_is_not_null"></s:text>',
				        	maxlength: '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>11</s:param></s:text>'
				        } 
					} ,
					rules : {
				        "dict.dictListCode" : {
							required : true,
							rangelength:[1,32],
							remote: {
							    url: "<%=basePath%>/dictAjax/getDictByCode.action", //后台处理程序
						type : "post", //数据发送方式
						dataType : "json", //接受数据格式   
						data : { //要传递的数据
							"DictListCode" : function() {
								return $("#dictList.dictListCode").val();
							}
						}
					}
				},
				"dict.dictListName" : {
					required : true,
					rangelength : [ 1, 36 ]
				},
				"dict.dictListNumber" : {
					number:true,
					required : true,
					rangelength : [ 1, 11 ]
				},
			},
		});
	});
	function __back(){
		window.location.href="<%=path%>/dict/findAllDict.action";
	}
	function __submit(){
		if($("#insertDictForm").valid()){
	 		$("#loadingdiv").show();
			document.insertDictForm.action="<%=basePath%>dict/insertDictList.action";
			document.insertDictForm.submit();
		}
	}
	function _validate() {
		art.dialog.data('_validate', $("#insertDictForm").valid());
	}
</script>
</head>

<body>
	<form action="dict/insertDictList.action" name="insertDictForm"	id="insertDictForm" method="post" target="mainFrame">
	<h2 class="page-header"><s:text name="addlist_dict" /></h2>
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
		    	<input class="form-control"name="dict.dictListCode"	type="text" id="dict.dictListCode">
		    </div>
		</div>
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_list_th_DictListName" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dict.dictListName"	type="text" id="dict.dictListName">
		    </div>
		</div>
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_list_th_DictListNumber" />：
		    </label>
		    <div class="col-sm-8">
		    	<input class="form-control"name="dict.dictListNumber"	type="text" id="dict.dictListNumber">
		    </div>
		</div>
		<div class="form-group col-sm-6">
		    <label class="col-sm-4 control-label text-right">
				<s:text name="dict_list_th_DictListStatus" />：
		    </label>
		    <div class="col-sm-8">
		    	<CW:switch name="dict.Status" id="Status" type="checkbox" checked="true"/>
		    </div>
		</div>
		 <div class="form-group col-sm-12">
		    <label class="col-sm-2 control-label text-right">
			  <s:text name="dict_list_th_DictListRemark" />：
		    </label>
		    <div class="col-sm-10">
				<textarea class="form-control" cols="20" name="dict.dictListRemark" rows="4"></textarea>
			</div>
		</div>
	</div>
</form>
</body>
</html>
