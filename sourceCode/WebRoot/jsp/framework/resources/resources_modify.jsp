<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css"> 
	<!--系统css  -->
	<link href="<%=basePath%>css/en/edit.css" rel="stylesheet" type="text/css">
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>

<style type="text/css">
tr{
height:30px;
}
</style>
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
		$('#srcForm').validate(
		{
		messages : {
				 "Resources.resourcesName" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>64</s:param></s:text>',
					remote:'<s:text name="operation_Alreadyexists"></s:text>'
				},
				"Resources.resourcesUrl" : {
					required : '<s:text name="tip_is_not_null"></s:text>',
					rangelength : '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>512</s:param></s:text>'
				},
		        "Resources.resourcesNumber" : {
		        	number:'<s:text name="number_onlydigital"></s:text>',
					required : '<s:text name="tip_is_not_null"></s:text>',
		        	maxlength: '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>9</s:param></s:text>'
		        } ,
		        "Resources.permissionSet" : {
		        	maxlength: '<s:text name="tip_rangelength"><s:param>1</s:param><s:param>512</s:param></s:text>'
		        } 
			} ,
			rules : {
		        "Resources.resourcesName" : {
					required : true,
					rangelength:[1,64]
			},
			"Resources.resourcesUrl" : {
				required : true,
				rangelength : [ 1, 512 ]
			},
			"Resources.resourcesNumber" : {
				number:true,
				required : true,
				rangelength : [ 1, 9 ]
			},
			"Resources.permissionSet" : {
				rangelength : [ 1, 512 ]
			}
	},
});
});
	//返回
	function __back(){
		window.location.href="<%=path%>/resources/findAll.action";
	}
	function __submit(){
		if($("#srcForm").valid()){
			document.srcForm.action="<%=basePath%>resources/update.action";
			document.srcForm.submit();
		}
	}
	function _validate() {
		art.dialog.data('_validate', $("#srcForm").valid());
	}
</script>
  </head>
  <body>
    <form action="" id="srcForm" name="srcForm" method="post">
    <h2 class="page-header"><s:text name="修改资源" /></h2>
	<div class="form-actions text-right">  
		<input class="btn btn-primary  " name="_submit" type="button" value="<s:text name="operation_submit" />" onclick="__submit()">
		<input class="btn btn-default  " name="_back" type="button" value="<s:text name="operation_tip_cancel" />" onclick="__back()">
	</div>	
	<input type="hidden" id="resourcesId" name="Resources.resourcesId" 	value='<s:property value="Resources.resourcesId" />' />
	<div class="controls">  
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_resourcesName" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.resourcesName" id="resourcesName" type="text"  value="<s:property value="Resources.resourcesName" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_resourcesUrl" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.resourcesUrl" id="resourcesUrl" type="text"  value="<s:property value="Resources.resourcesUrl" />">
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="resources_list_th_Status" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="Resources.Status" id="Status" type="checkbox" />
			    </div>
		</div>	
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="是否菜单入口" />：
			    </label>
			    <div class="col-sm-8">
			     <CW:switch name="Resources.isMenu" id="isMenu" type="checkbox" />
			    </div>
		</div>	
		 <div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="排序号" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.resourcesNumber" id="resourcesNumber" type="text" value="<s:property value="Resources.resourcesNumber" />">
			    </div>
		</div>
		<div class="form-group col-sm-6">
			    <label class="col-sm-4 control-label text-right">
				  <s:text name="资源权限集" />：
			    </label>
			    <div class="col-sm-8">
				  <input class="form-control" name="Resources.permissionSet" id="permissionSet" type="text" value="<s:property value="Resources.permissionSet" />">
			    </div>
		</div>	
	</div>	
    </form>
  </body>
</html>
