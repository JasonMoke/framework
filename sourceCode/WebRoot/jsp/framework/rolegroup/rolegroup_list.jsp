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
	function _detail(UUID){
	 //设置请求的url 
		var _url = "<%=basePath%>/rolegroup/detail.action?id="+UUID;
		location.href=_url;
	}
	function _add() {
	     //设置请求的url
	    var url = "<%=basePath%>/rolegroup/gotoAdd.action";
		location.href=url;
	}
	function _modify(UUID) {
			 //设置请求的url 
			var _url = "<%=basePath%>/rolegroup/gotoModify.action?id="+UUID;
			location.href=_url;
		}
	function delRole(UUID) {

			  	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
					document.srcForm.action="<%=path%>/rolegroup/delete.action?id="+UUID;
					document.srcForm.submit();
				}, function(){
				     art.dialog.tips('<s:text name="operation_cancel"></s:text>');
				});
}
		//刷新页面
function _resresh(){
	document.srcForm.action="<%=path%>/rolegroup/findList.action";
	document.srcForm.submit();
}
function delRoles(){
var RoleName = getCheckedValue();
	if(RoleName==""||RoleName==null){
		art.dialog.alert('<s:text name="operation_no_select"></s:text>');
	}else{
			  	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
					document.srcForm.action="<%=path%>/rolegroup/deleteBatch.action?RoleName="+RoleName;
					document.srcForm.submit();
				}, function(){
				    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
				});
		};
	}
function _roleSel(UUID) {
	//设置请求的url 
	var _url = "<%=basePath%>/tree/RoleTree.action?roleGroupId="+UUID+"&useType=roleGroup";
	//调用art.dialog组件弹出窗口 采用iframe方式
	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '选择角色',
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
				var form = iframe.document.getElementById("selUserRoleForm");
				form.submit();
			},
			cancel : true
	}); 
}
</script>
</head>

<body>
<form action="rolegroup/findList.action" id="srcForm" name="srcForm" method="post">
<s:hidden name="actionResult" id="actionResult"></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header">角色组管理</h2>
<!-- 工具条 -->
<div class="row">
 <div class="col-xs-6">
</div>
 <div class="col-xs-6" style="text-align: right">
	<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
	<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="delRoles();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
 </div>
</div>
<table class="table table-striped tablesorter table-hover table-condensed">
<thead>
<tr>
<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
</th>
<th width="5%">序号</th>
<th>角色组名称</th>
<th>备注</th>
<th>创建人</th>
<th>创建时间</th>
<th width="5%">状态</th>
<th width="10%">操作</th>
</tr>
</thead>
<tbody>
<s:if test="page.resultList.size()>0">
<s:iterator value="page.resultList" var="list" status="status">
<tr>
<td>
<s:if test="childCoutn!=0">
 <input type="checkbox" name="chk_list1" disabled="disabled" id="chk_list_<s:property value="#status.count" />_1" value="<s:property value="RoleName" />" title="此角色组含有子角色，请先删除子角色！"/>
 </s:if>
 <s:else>
<input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="UUID" />"/>
</s:else>
</td>
<td><s:property value="#status.count" /></td>
<td><span title="<s:property value="RoleGroupName" />"><s:property value="RoleGroupName" /></span></td>
<td><span title="<s:property value="RoleGroupRemark" />"><s:property value="RoleGroupRemark" /></span></td>
<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" /></span>
</td>
<td>
<CW:status url="rolegroupAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.UUID}" /></CW:status>
</td>
<td>
<div class="btn-group">
<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
<s:text name="operation" />
<span class="caret"></span>
</a>
<ul class="dropdown-menu pull-right">
<li><a href="#" onclick="_detail('<s:property value="UUID" />');return false;">
<s:text name="operation_detail" /></a> 
</li> 
<li><a href="#" onclick="_modify('<s:property value="UUID" />');return false;">
<s:text name="operation_modify" /></a> 
</li>
<s:if test="childCoutn==0">
<li><a href="#" onclick="delRole('<s:property value="UUID" />');return false;">
<s:text name="operation_delete" /></a> 
</li> 
</s:if>
<li><a href="#" onclick="_roleSel('<s:property value="UUID" />');return false;">
<s:text name="分配角色" /></a>
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
<CW:page property="page" url="rolegroup/findList.action" formName="srcForm"></CW:page>		
</form>
</body>
</html>
