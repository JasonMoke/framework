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
		document.srcForm.action="<%=basePath%>/dict/findAllDict.action";
		document.srcForm.submit();
}
//删除字典集
function _deleteOne(scode){
	$.ajax({  
			type: "post",  
			url : "<%=path%>/dictAjax/getListmanagerCount.action",
			data:"scode="+scode,
	  success: function(data){ 
		  if(data=='true'){
		  	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
		  	$("#loadingdiv").show();
				document.srcForm.action="<%=path%>/dict/deleteDictList.action?scode="+scode;
				document.srcForm.submit();
			}, function(){
			    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
			});
		  }
		  if(data=='false'){
			  art.dialog.tips('<s:text name="operation_dontdelete"></s:text>');
		  }
		  
		  }
							
	});
		
}
//批量删除字典集
function _delete(){
	var listCodes = getCheckedValue(); 
	if(listCodes==""||listCodes==null){
		art.dialog.tips('<s:text name="tip_select_least_one"></s:text>');
	}else{
		$.ajax({  
				type: "post",  
				url : "<%=path%>/dictAjax/getListmanagerCount.action",
				data:"scode="+listCodes,
		  success: function(data){ 
			  if(data=='true'){
			  		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
			  		$("#loadingdiv").show();
					document.srcForm.action="<%=path%>/dict/deleteDictList.action?scode="+listCodes;
					document.srcForm.submit();
				}, function(){
				    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
				});
			 	 }
			  	if(data=='false'){
			 		art.dialog.tips('<s:text name="operation_dontdelete"></s:text>');
			  	}
			  
			  	}
								
		});
}
		
}
//新增字典集
	function _insertDict() {
		var _url = "<%=basePath%>dict/toAddDictList.action";
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
//修改字典集
	function _modify(scode) {
			var _url = "<%=basePath%>dict/toUpdateDictList.action?scode="+scode;
			document.srcForm.action=_url;
			document.srcForm.submit();
		}
	//查询一条数据
	function _detail(scode){
			var _url = "<%=basePath%>dict/detailDictList.action?scode="+scode;
			document.srcForm.action=_url;
			document.srcForm.submit();
		}
	//查看下级
	function _get(code){
			window.location.href="<%=basePath%>dict/getDictManager.action?code="+code;
		}
	//刷新页面
	function _resresh(){
		$("#DictListCode").val("");
		$("#DictListName").val("");
		$("#Status").val("");
		document.srcForm.action="<%=path%>/dict/findAllDict.action";
		document.srcForm.submit();
	}
	function _reset(){
	$("#DictListCode").val("");
	$("#DictListName").val("");
	$("#Status").val("");
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
<form action="dict/findAllDict.action" name="srcForm" id="srcForm" method="post">
	<input type="hidden" id="hiddenListName" name="hiddenListName" value="<s:property value="ListName" />" />
	<input type="hidden" id="hiddenListStatus" name="hiddenListStatus" value="<s:property value="ListStatus" />" />
	<input type="hidden" id="hiddenListCode" name="hiddenListCode" value="<s:property value="ListCode" />" />
	<s:hidden name="actionResult" id="actionResult"></s:hidden>
	<input type="hidden" id="dictListCode" name="dictListCode" 	value='<s:property value="dictListCode" />' />
	<!-- 模块名称 -->
	<h2 class="page-header"><s:text name="dict_list_th_Title" /></h2>
	<!-- 条件查询 -->
	<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
			    <label class="col-sm-4 control-label">
			    <s:text name="dict_list_th_DictListCode" />：
			    </label>
			    <div class="col-sm-8">
			    <input type="text" class="form-control" name="dictList.DictListCode" id="DictListCode" value="<s:property value="dictList.DictListCode" />">
			    </div>
			</div>
		</div>
	<div class="col-sm-4">
		<div class="form-group">
		      <label class="col-sm-4 control-label">
		      <s:text name="dict_list_th_DictListName" />：
		      </label>
		      <div class="col-sm-8">
		     	 <input type="text" class="form-control" name="dictList.DictListName" id="DictListName" value="<s:property value="dictList.DictListName" />">
		    	</div>
	   </div>
	</div>
	<div class="col-sm-4">
		<div class="form-group">
		      <label class="col-sm-4 control-label">
		      <s:text name="dict_list_th_DictListStatus" />：
		      </label>
		      <div class="col-sm-8">
		      <select name="dictList.Status" id="Status" class="form-control">
				<option value=""></option>
				<s:if test="dictList.Status==0">
				<option value="0" selected="selected"><s:text name="dict_list_th_DictListStatus_disable" /></option>
				</s:if>
				<s:else>
				<option value="0"><s:text name="dict_list_th_DictListStatus_disable" /></option>
				</s:else>
				<s:if test="dictList.Status==1">
				<option value="1" selected="selected"><s:text name="dict_list_th_DictListStatus_enable" /></option>
				</s:if>
				<s:else>
				<option value="1" ><s:text name="dict_list_th_DictListStatus_enable" /></option>
				</s:else>
			</select>
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
				<th width="5%"><s:text name="dict_list_th_no" />
				</th>
				<th><s:text name="dict_list_th_DictListCode" />
				</th>
				<th><s:text name="dict_list_th_DictListName" />
				</th>
				<th><s:text name="dict_list_th_DictListRemark" />
				</th>
				<th><s:text name="dict_list_th_CreatePerson" />
				</th>
				<th><s:text name="dict_list_th_CreateTime" />
				</th>
				<th width="5%"><s:text name="dict_list_th_DictListStatus" />
				</th>
				<th width="10%"><s:text name="dict_list_th_Operation" />
				</th>
			</tr>
		</thead>
		<tbody>
			<s:if test="list.size>0">
				<s:iterator value="list" var="list" status="status">
					<tr>
						<td><input type="checkbox" name="chk_list"	id="chk_list_<s:property value="#status.count" />" value="<s:property value="DictListCode" />" /></td>
						<td><s:property value="#status.count" /></td>
						<td><span title="<s:property value="DictListCode" />"><s:property value="DictListCode" /></span></td>
						<td><span title="<s:property value="DictListName" />"><s:property value="DictListName" /></span></td>
						<td><span title="<s:property value="DictListRemark" /> "><s:property value="DictListRemark" /></span>
							<input type="hidden" value='<s:property value="DictListNumber" />' />
						</td>
						<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
						<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
						<td>
						<CW:status url="dictAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.DictListCode}" /></CW:status>
						</td>
						<td>
						<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
							<s:text name="operation" />
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
							<li><a href="#" onclick="_detail('<s:property value="DictListCode" />');return false;">
							<s:text name="operation_detail" /></a> 
							</li> 
							<li><a href="#" onclick="_modify('<s:property value="DictListCode" />');return false;">
							<s:text name="operation_modify" /></a> 
							</li> 
							<li><a href="#" onclick="_deleteOne('<s:property value="DictListCode" />');return false;">
							<s:text name="operation_delete" /></a> 
							</li> 
							<li><a href="#" onclick="_get('<s:property value="DictListCode" />');return false;">
							<s:text name="operation_detail_child" /></a>
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
	<CW:page property="page" url="dict/findAllDict.action"></CW:page>	
</form>
</body>
</html>
