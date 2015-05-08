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
<title>模块管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  //页面排序
	   $(".tablesorter").tablesorter({
		  headers:{ 
	          0:{sorter: false},	
	          1:{sorter: 'text'}, 
	          2:{sorter: 'text'}, 
	          3:{sorter: 'text'}, 
	          4:{sorter: 'text'}, 
	          5:{sorter: false}, 
	          6:{sorter: false}
	       }	
		});
  //显示操作结果
	 var actionResult = $("#actionResult").val();
	 if(actionResult=="success"){
	     parent.leftFrame.location = "<%=basePath%>/jsp/framework/module/module_tree.jsp";
	 	art.dialog.tips('<s:text name="list_successfuloperation"></s:text>');
	 }else if(actionResult=="error"){
	 	art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
	 }
  
}); 
function _add() {
	var ModuleParent=$("#mParent").val();
     //设置请求的url
    var url = "<%=basePath%>/module/toAddModule.action?ModuleParent="+ModuleParent;
    location.href=url;
}
//修改
function _modify(ModuleId) {
			 //设置请求的url 
			var _url = "<%=basePath%>/module/toUpdateModoule.action?ModuleId="+ModuleId;
			location.href=_url;
		}
	//查询一条数据
	function _detail(ModuleId){
	 //设置请求的url 
			var _url = "<%=basePath%>/module/detailModule.action?ModuleId="+ModuleId;
			location.href=_url;
		}
/**
 * 删除操作。
 */
function delEntity(ModuleId) {
	$.ajax({  
		type: "post",  
		url : "<%=path%>/moduleAjax/getModuleManagerCountOne.action",
		data:"ModuleId="+encodeURI(ModuleId),
		success: function(data){ 
		    if(data=='true'){
		      	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
				  	$("#loadingdiv").show();
						document.formModule.action="<%=path%>/module/deleteModule.action?ModuleId="+encodeURI(ModuleId);
						document.formModule.submit();
					}, function(){
					   art.dialog.tips('<s:text name="operation_cancel"></s:text>');
					});
			  }
			  if(data=='1'){
				    art.dialog.alert('该模块有资源关联，请先解除关联再来删除！');
			  }	
			  if(data=='2'){
				  art.dialog.alert('该模块有角色在使用，请先将解除关联再来删除！');
			  }
			  if(data=='3'){
				  art.dialog.alert('该模块有应用在使用，请先将解除关联再来删除！');
			  }
		  }
	});
}
function delBatchEntity(){
var ModuleId = getCheckedValue(); 
	if(ModuleId==""||ModuleId==null){
		art.dialog.tips('<s:text name="tip_select_least_one"></s:text>');
	}else{
		$.ajax({  
			type: "post",  
			url :  "<%=path%>/moduleAjax/getModuleManagerCount.action",
			data:"ModuleId="+encodeURI(ModuleId),
			success: function(data){ 
			    if(data=='true'){
			    	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
				  			function(){
				  		$("#loadingdiv").show();
						document.formModule.action="<%=path%>/module/deleteModule.action?ModuleId="+encodeURI(ModuleId);
						document.formModule.submit();
					}, function(){
					     art.dialog.tips('<s:text name="operation_cancel"></s:text>');
					});
			    }
				if(data=='false'){
					art.dialog.alert('此模块与资源、角色、应用有关联，请先解除关联再来删除！');
				}
		  	}
		});	
	};
}
//刷新页面
function _resresh(){
		parent.leftFrame.location = "<%=request.getContextPath() %>/tree/leftModuleTree.action";
		parent.formModule.location = "<%=request.getContextPath() %>/module/getModuleByCondition.action";
	}
	
//设置资源
function _setResources(id){
	//设置请求的url 
	var _url = "<%=basePath%>/tree/resourcesTree.action?checkedId="+id;
	//调用art.dialog组件弹出窗口 采用iframe方式
	art.dialog.open(_url,
		{ 
		 	lock: true,//背景锁定
	    	background: '#BFBFBF', // 背景色
	    	opacity: 0.5,	// 透明度
			title : '选择资源',
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
				/* var form = iframe.document.getElementById("selUserRoleForm");
				form.submit(); */
				
				
				
				var checkedIds = art.dialog.data('checkedIds');
				//var checkedNames = art.dialog.data('checkedNames');
				document.formModule.action="<%=path%>/module/setResources.action?ModuleId="+id+"&ids="+checkedIds;
				document.formModule.submit();
			},
			cancel : true
	}); 
}
</script>
</head>
<body>
	<form method="post" id="formModule" name="formModule" action="" target="mainFrame" >
	<input type="hidden" name="foo" id="foo" value="<s:property value="switchFlag" />"> 
	<s:hidden name="actionResult" id="actionResult"></s:hidden>
	<input type="hidden" id="mParent" name="mParent" value='<s:property value="chache.parent" />' />
	
	<h2 class="page-header"><s:text name="module_list_th_title" /></h2>
	<div class="row placeholders">
      <div class="col-xs-6 placeholder">
      </div>
      <div class="col-xs-6 placeholder" style="text-align: right">
        <input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
		<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="delBatchEntity();return false;">
		<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
      </div>
    </div>
    <div class="table-responsive">
	<table class="table table-striped tablesorter table-hover table-condensed" >
	<thead>
		<tr>
			<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
			<th><s:text name="module_list_th_ModuleName" />
			</th>
			<th><s:text name="module_list_th_CreatePerson" />
			</th>
			<th><s:text name="module_list_th_CreateTime" />
			</th>
			<th><s:text name="module_list_th_Status" />
			</th>
			<th width="70px"><s:text name="module_list_th_Operation" />
			</th>
		</tr>
		</thead>
		<tbody>  
		<s:if test="page.resultList.size()>0">
			<s:iterator value="page.resultList" var="list"	status="status">
					<tr>
					<td><input type="checkbox" name="chk_list"	id="chk_list_<s:property value="#status.count" />"	value="<s:property value="ModuleId" />" /></td>
					<td><span title="<s:property value="ModuleName" />"><s:property value="ModuleName" /></span>
					</td>
					<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue>
					</td>
					<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span>
					</td>
					<td><CW:status url="/moduleAjax/changeModuleStatus.action" field="Status" ><s:hidden value="%{#list.ModuleId}" /></CW:status>
					</td>
					<td>
					<div class="btn-group">
					  <a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
					    <s:text name="operation" />
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu pull-right">
					     <li><a href="#" onclick="_detail('<s:property value="ModuleId" />');return false;">
					     <s:text name="operation_detail" /></a> 
				         </li> 
				         <li><a href="#" onclick="_modify('<s:property value="ModuleId" />');return false;">
				         <s:text name="operation_modify" /></a> 
				         </li> 
				         <li><a href="#" onclick="delEntity('<s:property value="ModuleId" />');return false;">
				         <s:text name="operation_delete" /></a> 
				         </li> 
				         <li><a href="#" onclick="_setResources('<s:property value="ModuleId" />');return false;">
				         <s:text name="设置资源" /></a> 
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
	<CW:page property="page" url="module/getModuleByCondition.action" formName="formModule" target="formModule"></CW:page>
</div>
</form>
</body>
</html>
