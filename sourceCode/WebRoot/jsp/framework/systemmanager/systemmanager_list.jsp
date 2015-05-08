
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<!--引用struts标签  -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="systemmanager_list_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--通用js  -->
	<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
	<!--artDialog-->
	<script type="text/javascript" src="<%=basePath%>artDialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=basePath%>artDialog/plugins/iframeTools.source.js"></script>
	<script type="text/javascript">
	 $(document).ready(function() {
		 //排序
		 $(".tablesorter").tablesorter({
			  headers:{ 
		          0:{sorter:false},	//不排序
		          1:{sorter:'digit'}, //按数字排序
		          2:{sorter:'text'}, //按字符排序
		          3:{sorter:'text'}, 
		          4:{sorter:'text'}, 
		          5:{sorter:'text'}, 
		          6:{sorter:false},	
		          7:{sorter:false}	
		       }	
		 });
	});
	//删除
	function _deleteOne(id){
		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
		function(){
			document.srcForm.action="<%=path%>/systemmanager/deleteOne.action?id="+id;
			document.srcForm.submit();
		}, function(){
		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
		});
	}
	//批量删除
	function _delete(){
		var ids = getCheckedValue(); 
		if(ids==""||ids==null){
			art.dialog.alert('<s:text name="operation_no_select"></s:text>');
		}else{
			art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
			function(){
				document.srcForm.action="<%=basePath%>systemmanager/deleteBatch.action?ids="+ids;
				document.srcForm.submit();
			}, function(){
			    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
			});
		}
	}
	//新增
	function _add() {
		 //设置请求的url 
		var _url = "<%=basePath%>systemmanager/gotoAdd.action";
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
	//修改
	function _modify(id) {
		 //设置请求的url 
		var _url = "<%=basePath%>systemmanager/gotoModify.action?id="+id;
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
	//查询一条数据
	function _detail(id){
	 	//设置请求的url 
		var _url = "<%=basePath%>systemmanager/detail.action?id="+id;
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
	//刷新页面
	function _resresh(){
		$("#ShortName").val("");
		$("#FullName").val("");
		$("#Status").val("");
		document.srcForm.action="<%=path%>/systemmanager/findList.action";
		document.srcForm.submit();
	}

	//带条件查询
	function _search(){
			document.srcForm.action="<%=path%>/systemmanager/findList.action";
			document.srcForm.submit();
	}
	//重置查询条件
	function _reset(){
		$("#ShortName").val("");
		$("#FullName").val("");
		$("#systemmanagerStatus").val("");
	}

	//设置模块
	function _setModule(id){
		//设置请求的url 
		var _url = "<%=basePath%>/tree/moduleApplicationTree.action?checkedId="+id;
		//调用art.dialog组件弹出窗口 采用iframe方式
		art.dialog.open(_url,
			{ 
			 	lock: true,//背景锁定
		    	background: '#BFBFBF', // 背景色
		    	opacity: 0.5,	// 透明度
				title : '选择模块',
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
					var checkedIds = art.dialog.data('checkedIds');
					document.srcForm.action="<%=path%>/systemmanager/setModuleManager.action?SystemCode="+id+"&ids="+checkedIds;
					document.srcForm.submit();
				},
				cancel : true
		}); 
	}
	//查询下级模块
	function _detailModule(id){
		document.srcForm.action="<%=path%>/systemmanager/detailModule.action?SystemCode="+id;
		document.srcForm.submit();
	}
</script>
<style type="text/css">


</style>
  </head>
  
<body>
	<form action="systemmanager/setModule.action" id="srcForm" name="srcForm" method="post">
		<s:hidden name="actionResult" id="actionResult"  value=""></s:hidden>
		
		<!-- 模块名称 -->
		<h2 class="page-header"><s:text name="systemmanager_list_page_title" /></h2>
		<!-- 操作提示 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title tip-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse-content" >
					操作提示
					</a>
				</h4>
			</div>
			<div id="collapse-content" class="panel-collapse collapse">
				<div class="panel-body">
				包括应用注册、为应用添加模块、为模块添加资源功能；注册到Framework平台中的应用可以由平台统一进行用户界面集成、权限控制；资源是指开发的业务系统的一个功能项，比如查询产品列表，增加一条销售记录；模块是系统功能的逻辑划分，由一个或多个资源构成，如产品管理模块、销售管理模块；应用是指一个相对独立的系统，由一个或多个模块构成，如ERP应用系统，财务管理系统。
				</div>
			</div>
		</div>
		<!-- 条件查询 -->
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
				    <label class="col-sm-4 control-label">
				    <s:text name="systemmanager_list_th_shortname" />：
				    </label>
				    <div class="col-sm-8">
				    <input class="form-control" type="text" name="systemmanager.ShortName" id="ShortName" value="<s:property value="systemmanager.ShortName" />">
				    </div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
				      <label class="col-sm-4 control-label">
				      <s:text name="systemmanager_list_th_fullname" />：
				      </label>
				      <div class="col-sm-8">
				      <input class="form-control" type="text" name="systemmanager.FullName" id="FullName" value="<s:property value="systemmanager.FullName" />">
				    </div>
				   </div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
				      <label class="col-sm-4 control-label">
				      <s:text name="systemmanager_list_th_status" />：
				      </label>
				      <div class="col-sm-8">
				      <select class="form-control" name="systemmanager.Status" id="systemmanagerStatus">
						<option value=""></option>
						  <s:if test="systemmanager.Status==0">
						  <option value="0" selected="selected"><s:text name="systemmanager_list_td_status_disable" /></option>
						 </s:if>
						 <s:else>
						   <option value="0"><s:text name="systemmanager_list_td_status_disable" /></option>
						 </s:else>
						  <s:if test="systemmanager.Status==1">
						 <option value="1" selected="selected"><s:text name="systemmanager_list_td_status_enable" /></option>
						 </s:if>
						 <s:else>
						  <option value="1" ><s:text name="systemmanager_list_td_status_enable" /></option>
						 </s:else>
						</select>
						&nbsp;
						&nbsp;
				     </div>
				   </div>
			</div>
			<div class="col-sm-12 text-center">
				<input type="button" class="btn  btn-primary" value="<s:text name="operation_search" />" onclick="_search()">
				<input type="button" class="btn  btn-primary" value="<s:text name="operation_reset" />" onclick="_reset()">
			</div>
		</div>
		<!-- 工具条 -->
		<div class="row">
			 <div class="col-xs-6">
			</div>
			 <div class="col-xs-6" style="text-align: right">
				<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
				<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_delete();return false;">
				<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
			 </div>
		</div>
		<!-- 列表内容 -->
		<table class="table table-striped tablesorter table-hover table-condensed">
			<thead>
				<tr class="gridview_color">
				<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
				</th>
				<th width="5%"><s:text name="systemmanager_list_th_no" /></th>
				<th width="15%"><s:text name="systemmanager_list_th_shortname" /></th>
				<th width="25%"><s:text name="systemmanager_list_th_fullname" /></th>
				<th width="10%"><s:text name="systemmanager_list_th_createperson" /></th>
				<th width="20%"><s:text name="systemmanager_list_th_createtime" /></th>
				<th width="10%"><s:text name="systemmanager_list_th_status" /></th>
				<th width="80px"><s:text name="systemmanager_list_th_operation" /></th>
				</tr>
			</thead>
			<tbody>
				<s:if test="listSystemmanager.size()>0">
					<s:iterator value="listSystemmanager" var="list" status="status"  > 
						<tr>
						<td>
						<s:if test="childCoutn!=0">
						<input type="checkbox"  name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="SystemCode" />" />
						</s:if>
						<s:else>
						<input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="SystemCode" />"/>
						</s:else>
						</td>
						<td><s:property value="#status.count" /></td>
						<td><span title="<s:property value="ShortName" />"><s:property value="ShortName" /></span></td>
						<td><span title="<s:property value="FullName" />"><s:property value="FullName" /></span></td>
						<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
						<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
						<td><CW:status url="systemmanagerAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.SystemCode}" /></CW:status></td>
						<td>
						<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
						<s:text name="operation" />
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
						<li><a href="#" onclick="_detail('<s:property value="SystemCode" />');return false;">
						<s:text name="operation_detail" /></a> 
						</li> 
						<li><a href="#" onclick="_modify('<s:property value="SystemCode" />');return false;">
						<s:text name="operation_modify" /></a> 
						</li> 
						<s:if test="childCoutn==0">
						<li><a href="#" onclick="_deleteOne('<s:property value="SystemCode" />');return false;">
						<s:text name="operation_delete" /></a> 
						</li>
						</s:if> 
						<li><a href="#" onclick="_setModule('<s:property value="SystemCode" />');return false;">
						<s:text name="设置模块" /></a> 
						</li> 
						<li><a href="#" onclick="_detailModule('<s:property value="SystemCode" />');return false;">
						<s:text name="查看下级模块" /></a> 
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
		<CW:page property="page" url="systemparam/findList.action"></CW:page>	
	</form>
</body>
</html>
