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

<title><s:text name="修改" />
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
		$(document).ready(function() {
		$('#updateForm').validate({
			messages : {
				"rolegroup.SeqNum" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>64</s:param></s:text>'
				},
			},
			rules : {
				"rolegroup.SeqNum" : {
					required : true,
					rangelength : [ 1, 11 ]

				},
			},
		});
	});
	function __back(){
	 //设置请求的url 
		var _url = "<%=path%>/rolegroup/findList.action";
		location.href=_url;
	}
	function _validate() {
		art.dialog.data('_validate', $("#updateForm").valid());
	}
	function __submit(){
	   if($("#updateForm").valid()){
			document.updateForm.action="<%=basePath%>rolegroup/update.action";
			document.updateForm.submit();
		}
	}
</script>
</head>

<body>
	<form action="rolegroup/update.action" name="updateForm" id="updateForm" method="post" target="mainFrame">
	<input type="hidden" name="rolegroup.UUID" id="rolegroup.UUID" value="<s:property value="rolegroup.UUID" />"/>
	<h2 class="page-header"><s:text name="修改" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>	
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="名称" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="rolegroup.RoleGroupName" id="RoleGroupName" type="text" value="<s:property value="rolegroup.RoleGroupName" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="排序号" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="rolegroup.SeqNum" id="SeqNum" type="text" value="<s:property value="rolegroup.SeqNum" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="状态" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="rolegroup.Status" id="Status" type="checkbox" />
			    </div>
		</div>	
		 <div class="form-group col-sm-12">
			    <label class="col-sm-2 control-label text-right">
				  <s:text name="备注" />：
			    </label>
			    <div class="col-sm-10">
					<textarea class="form-control" cols="20" name="rolegroup.RoleGroupRemark" rows="4"><s:property value="rolegroup.RoleGroupRemark" /></textarea>
				</div>
		</div>
	</div>		
</form>
</body>
</html>
