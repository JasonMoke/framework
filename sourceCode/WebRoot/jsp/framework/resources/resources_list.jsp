<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>

	<script type="text/javascript">

	 $(document).ready(function() {
   $(".tablesorter").tablesorter({
	  headers:{ 
          0:{sorter: false},	
          1:{sorter: 'digit'}, 
          2:{sorter: 'text'}, 
          3:{sorter: 'text'}, 
          4:{sorter: 'text'}, 
          5:{sorter: 'text'}, 
          6:{sorter: 'text'},	
          7:{sorter: false},	
          8:{sorter: false}	
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
//带条件查询
function _search(){
		document.srcForm.action="<%=basePath%>/resources/findAll.action";
		document.srcForm.submit();
}
//删除资源
function _deleteOne(id){
	document.srcForm.action="<%=path%>/resources/delete.action?id="+id;
	document.srcForm.submit();	
}
//批量删除资源
function _delete(){
	var id = getCheckedValue(); 
	if(id==""||id==null){
		art.dialog.tips('请选择要删除的资源。');
	}else{
		document.srcForm.action="<%=path%>/resources/delete.action?id="+id;
		document.srcForm.submit();
	}	
}
//新增资源
	function _insertDict() {
		var _url = "<%=basePath%>resources/toAdd.action";
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
//修改资源
	function _modify(id) {
			var _url = "<%=basePath%>resources/toUpdate.action?id="+id;
			document.srcForm.action=_url;
			document.srcForm.submit();
		}
	//查询一条数据
	function _detail(id){
			var _url = "<%=basePath%>resources/detail.action?id="+id;
			document.srcForm.action=_url;
			document.srcForm.submit();
		}

	//刷新页面
	function _resresh(){
		$("#resourcesUrl").val("");
		$("#resourcesName").val("");
		document.srcForm.action="<%=path%>/resources/findAll.action";
		document.srcForm.submit();
	}
	function _reset(){
		$("#resourcesUrl").val("");
		$("#resourcesName").val("");
	}
</script>
<style type="text/css">
.error {
	border: 1px #red solid;
	background-color: #FEBFBC;
}
</style>
</head>
<body>
<form action="resources/findAll.action" name="srcForm" id="srcForm" method="post">
	<s:hidden name="actionResult" id="actionResult"></s:hidden>

	<!-- 模块名称 -->
	<h2 class="page-header"><s:text name="resources_list_th_Title" /></h2>
	<!-- 条件查询 -->
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
			    <label class="col-sm-4 control-label">
			    <s:text name="resources_list_th_resourcesName" />：
			    </label>
			    <div class="col-sm-8">
			    <input type="text" class="form-control" name="Resources.resourcesName" id="resourcesName" value="<s:property value="Resources.resourcesName" />">
			    </div>
			</div>
		</div>
	<div class="col-sm-4">
		<div class="form-group">
		      <label class="col-sm-4 control-label">
		      <s:text name="resources_list_th_resourcesUrl" />：
		      </label>
		      <div class="col-sm-8">
		     	 <input type="text" class="form-control" name="Resources.resourcesUrl" id="resourcesUrl" value="<s:property value="Resources.resourcesUrl" />">
		    	</div>
	   </div>
	</div>
		<div class="col-sm-12" style="text-align: center;">
			<input type="button" class="btn  btn-primary" value="<s:text name="operation_search" />" onclick="_search()">
			<input type="button" class="btn  btn-primary" value="<s:text name="operation_reset" />" onclick="_reset()">
		</div>
	</div>
	<!-- 工具条 -->
	<div class="row">
		 <div class="col-xs-6">
		</div>
		 <div class="col-xs-6" style="text-align: right">
			<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_insertDict();return false;">
			<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_delete();return false;">
			<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
		 </div>
	</div>

	<table class="table table-striped tablesorter table-hover table-condensed" >
		<thead>
			<tr>
				<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
				<th width="5%"><s:text name="resources_list_th_no" />
				</th>
				<th><s:text name="resources_list_th_resourcesName" />
				</th>
				<th>资源权限集
				</th>
				<th><s:text name="resources_list_th_resourcesUrl" />
				</th>
				<th><s:text name="resources_list_th_CreatePerson" />
				</th>
				<th><s:text name="resources_list_th_CreateTime" />
				</th>
				<th width="5%"><s:text name="resources_list_th_Status" />
				</th>
				<th width="10%"><s:text name="resources_list_th_Operation" />
				</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="list.size>0">
				<s:iterator value="list" var="list" status="status">
					<tr>
						<td><input type="checkbox" name="chk_list"	id="chk_list_<s:property value="#status.count" />" value="<s:property value="resourcesId" />" /></td>
						<td><s:property value="#status.count" /></td>
						<td><span title="<s:property value="resourcesName" />"><s:property value="resourcesName" /></span></td>
						<td><span title="<s:property value="permissionSet" />"><s:property value="permissionSet" /></span></td>
						<td><span title="<s:property value="resourcesUrl" />"><s:property value="resourcesUrl" /></span></td>
						<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
						<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
						<td>
						<CW:status url="resourcesAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.resourcesId}" /></CW:status>
						</td>
						<td>
						<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
							<s:text name="operation" />
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
							<li><a href="#" onclick="_detail('<s:property value="resourcesId" />');return false;">
							<s:text name="operation_detail" /></a> 
							</li> 
							<li><a href="#" onclick="_modify('<s:property value="resourcesId" />');return false;">
							<s:text name="operation_modify" /></a> 
							</li> 
							<li><a href="#" onclick="_deleteOne('<s:property value="resourcesId" />');return false;">
							<s:text name="operation_delete" /></a> 
							</li> 
						</ul>
						</div>
						</td>
					</tr>
				</s:iterator>
			</s:if>
		<tbody>
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
	<CW:page property="page" url="resources/findAll.action"></CW:page>	
</form>
</body>
</html>
