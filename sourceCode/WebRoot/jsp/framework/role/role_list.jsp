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

<title><s:text name="role_list_page_title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
<!--artDialog-->
<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
 $(document).ready(function() {
 //页面排序
	   $(".tablesorter").tablesorter({
		  headers:{ 
	          0:{sorter: false},	
	          1:{sorter: 'digit'}, 
	          2:{sorter: 'text'}, 
	          3:{sorter: 'text'}, 
	          4:{sorter: 'text'}, 
	          5:{sorter: 'text'}, 
	          6:{sorter: false},
	          7:{sorter: false}
	       }	
		});
	 //显示操作结果
	 var actionResult = $("#actionResult").val();
	 if(actionResult=="success"){
	 	art.dialog.tips('<s:text name="list_successfuloperation"></s:text>');
	 }else if(actionResult=="error"){
	 	art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
	 }
	});
	//查询一条数据
	function _detail(RoleName){
	 //设置请求的url 
		var _url = "<%=basePath%>/role/detailRole.action?RoleName="+encodeURI(RoleName);
		location.href=_url;
	}
	function _add() {
	     //设置请求的url
	    var url = "<%=basePath%>/role/gotoAdd.action";
		location.href=url;
	}
	function _modify(RoleName) {
			 //设置请求的url 
			var _url = "<%=basePath%>/role/toModifyRole.action?RoleName="+encodeURI(RoleName);
			location.href=_url;
		}
	function delRole(RoleName) {
		$.ajax({  
			type: "post",  
			url : "<%=path%>/roleAjax/getRoleManagerCountOne.action",
			data:"RoleName="+encodeURI(RoleName),
			success: function(data){ 
			    if(data=='true'){
				  	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
						document.roleListMain.action="<%=path%>/role/deleteRole.action?RoleName="+encodeURI(RoleName);
						document.roleListMain.submit();
					}, function(){
					     art.dialog.tips('<s:text name="operation_cancel"></s:text>');
					});
				  }
				  if(data=='1'){
					    art.dialog.alert('该角色有用户在使用，请先解除使用再来删除！');
				  }	
				  
				  if(data=='2'){
					  art.dialog.alert('该角色拥有权限，请先解除与权限关联再来删除！');
				  }
			  }
		});
	}
		//刷新页面
function _resresh(){
	document.roleListMain.action="<%=path%>/role/findAllRoles.action";
	document.roleListMain.submit();
}
function delRoles(){
var RoleName = getCheckedValue();
	if(RoleName==""||RoleName==null){
		art.dialog.tips('<s:text name="tip_select_least_one"></s:text>');
	}else{
		$.ajax({  
			type: "post",  
			url :  "<%=path%>/roleAjax/getRoleManagerCount.action",
			data:"RoleName="+encodeURI(RoleName),
			success: function(data){ 
			    if(data=='true'){
				  	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
						document.roleListMain.action="<%=path%>/role/deleteRole.action?RoleName="+encodeURI(RoleName);
						document.roleListMain.submit();
					}, function(){
					    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
					});
			    }
				if(data=='false'){
					art.dialog.alert('<s:text name="deleteOk_role"></s:text>');
				}
		  	}
		});
	}
}
function selectRight(name) {
	//设置请求的url 
	var _url = "<%=basePath%>/jsp/framework/role/role_resources_tree.jsp";
	art.dialog.data('name',name);
	//调用art.dialog组件弹出窗口 采用iframe方式
	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '<s:text name="role_select"></s:text>',
			width: '400px',
			height: '400px',
			drag: false,//禁止拖动
			resize: false,//禁止改变大小
			ok : function() {
				var iframe = this.iframe.contentWindow;
				if (!iframe.document.body) {
					/* 页面没有加载完毕！ */
					art.dialog.tips('<s:text name="operation_page_load"></s:text>');
					return false;
				}
				var form = iframe.document.getElementById("selResourcesForm");
				form.submit();
			},
			cancel : true
	}); 
}
</script>
</head>

<body>
<form action="role/getAllRoles.action" id="roleListMain"name="roleListMain" method="post">
<s:hidden name="actionResult" id="actionResult"></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header"><s:text name="role_list_th_title" /></h2>
<!-- 工具条 -->
<div class="row">
 <div class="col-xs-6">
</div>
 <div class="col-xs-6" style="text-align: right">
	<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
	<input id="delete" name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="delRoles();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
 </div>
</div>
<table class="table table-striped tablesorter table-hover table-condensed">
<thead>
<tr>
<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
</th>
<th width="5%"><s:text name="role_list_th_no" /></th>
<th><s:text name="role_list_th_rolename" /></th>
<th><s:text name="role_list_th_roleremark" /></th>
<th><s:text name="role_list_th_creater" /></th>
<th><s:text name="role_list_th_createtime" /></th>
<th width="5%"><s:text name="role_list_th_rolestatus" /></th>
<th width="10%"><s:text name="role_list_th_operation" /></th>
</tr>
</thead>
<tbody>
<s:if test="listRoleManager.size()>0">
<s:iterator value="listRoleManager" var="list" status="status">
<tr>
<td><input type="checkbox" name="chk_list"	id="chk_list_<s:property value="#status.count" />" value="<s:property value="RoleName" />" />
</td>
<td><s:property value="#status.count" />
</td>
<td><span title="<s:property value="RoleName" />"><s:property value="RoleName" /></span></td>
<td><span title="<s:property value="RoleRemark" />"><s:property value="RoleRemark" /></span></td>
<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" /></span>
</td>
<td>
<CW:status url="roleAjax/changeRoleStatus.action" field="Status" ><s:hidden value="%{#list.RoleName}" /></CW:status>
</td>
<td>
<div class="btn-group">
<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
<s:text name="operation" />
<span class="caret"></span>
</a>
<ul class="dropdown-menu pull-right">
<li><a href="#" onclick="_detail('<s:property value="RoleName" />');return false;">
<s:text name="operation_detail" /></a> 
</li> 
<li><a href="#" onclick="_modify('<s:property value="RoleName" />');return false;">
<s:text name="operation_modify" /></a> 
</li> 
<li><a href="#" onclick="delRole('<s:property value="RoleName" />');return false;">
<s:text name="operation_delete" /></a> 
</li> 
<li><a href="#" onclick="selectRight('<s:property value="RoleName" />');return false;">
分配资源</a>
</li>
</ul>
</div>
</td>
</tr>
</s:iterator>
</s:if>
</tbody>
</table>
<s:else>
<div class="container">
<div class="jumbotron">
<div class="row text-center">
<img alt="" src="images/errorc.png">
<h3><s:text name="list_nodata_to_load" /></h3>
</div>
</div>
</div>
</s:else>
<!--分页条-->
<CW:page property="page" url="role/findAllRoles.action" formName="roleListMain"></CW:page>		
</form>
</body>
</html>
