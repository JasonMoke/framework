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

<title>角色组管理</title>

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
		$('#insertForm').validate({
			messages : {
				"rolegroup.RoleGroupName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>64</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"rolegroup.SeqNum" : {
				number:'<s:text name="number_onlydigital"></s:text>',
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>11</s:param></s:text>',
				},
			},
			rules : {
				"rolegroup.RoleGroupName" : {
				 required : true,
				 rangelength:[1,64]
				},
				"rolegroup.RoleNumber" : {
				number:true,
					required : true,
					rangelength : [ 1, 11 ]

				},
			},
		});
	});

	function _validate() {
		art.dialog.data('_validate', $("#insertForm").valid());

	}
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/rolegroup/findList.action";
		location.href=_url;
	}
function __submit(){
    if($("#insertForm").valid()){
	document.insertForm.action="<%=basePath%>rolegroup/insert.action";
	document.insertForm.submit();
	}
}
</script>
</head>

<body>
<form action="rolegroup/insert.action" name="insertForm" id="insertForm" method="post" target="mainFrame">
<h2 class="page-header">新增</h2>
<div class="form-actions text-right">  
	<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
	<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
</div>	
<div class="controls">  
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		 名称：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="rolegroup.RoleGroupName" id="RoleGroupName" type="text">
	    </div>
</div>	
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  排序号：
	    </label>
	    <div class="col-sm-8">
		  <input class="form-control" name="rolegroup.SeqNum" id="SeqNum" type="text">
	    </div>
</div>	
 <div class="form-group col-sm-6">
	    <label class="col-sm-4 control-label text-right">
		  状态：
	    </label>
	    <div class="col-sm-8">
	     <CW:switch name="rolegroup.Status" id="Status" type="checkbox" checked="true"/>
	    </div>
</div>	
 <div class="form-group col-sm-12">
	    <label class="col-sm-2 control-label text-right">
		 备注：
	    </label>
	    <div class="col-sm-10">
			<textarea class="form-control" cols="20" name="rolegroup.RoleGroupRemark" rows="4"></textarea>
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
