<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--引用CW标签  -->
<%@ taglib prefix="CW" uri="/WEB-INF/taglib/cw" %>
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
    
    <title><s:text name="systemparam_list_page_title" /></title>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

	<!--通用js  -->
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
 	          6:{sorter: 'text'},
 	          7:{sorter: false},
 	          8:{sorter: false}
 	       }	
 		});	  
	 /*显示操作结果*/
	var actionResult = $("#actionResult").val();
	if(actionResult=="success"){
		art.dialog.tips('<s:text name="list_successfuloperation" />');
	}else if(actionResult=="error"){
		art.dialog.tips('<s:text name="list_operationfailed" />');
	}
    });
    //删除
    function _deleteOne(id){
    	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
    	function(){
	  		$("#loadingdiv").show();
    		document.srcForm.action="<%=path%>/systemparam/delete.action?id="+id;
    		document.srcForm.submit();
    	}, function(){
    	    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
    	});
    }
    
    //批量删除
    function _deleteBatch(){
    	var ids = getCheckedValue(); 
    	if(ids==""||ids==null){
    		art.dialog.alert('<s:text name="operation_no_select"></s:text>');
    	}else{
    		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', 
    		function(){
		  		$("#loadingdiv").show();
    			document.srcForm.action="<%=basePath%>systemparam/deleteBatch.action?ids="+ids;
    			document.srcForm.submit();
    		}, function(){
    		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
    		});
    	}
    }
    
    //新增
    function _add() {
    	//设置请求的url 
    	var _url = "<%=basePath%>systemparam/gotoAdd.action";
    	location.href=_url;
    }
    
    //修改
    function _modify(id) {
    	 //设置请求的url 
    	var _url = "<%=basePath%>systemparam/gotoModify.action?id="+id;
    	location.href=_url;
    }
    
    //查询一条数据
    function _detail(id){
     	//设置请求的url 
    	var _url = "<%=basePath%>systemparam/detail.action?id="+id;
		location.href=_url;
    }
    
    //刷新页面
    function _resresh(){
    	_reset();
    	document.srcForm.action="<%=path%>/systemparam/findList.action";
    	document.srcForm.submit();
    }
    //条件查询
	function _search(){
		$("#loadingdiv").show();
		document.srcForm.action="<%=path%>/systemparam/findList.action";
	    document.srcForm.submit();
	}
	//重置查询条件
	function _reset(){
		$("#ParamName").val("");
		$("#ParamValue").val("");
		$("#SystemCode").val("");
	}
    </script>
</head>

<body id="body">
<form action="systemparam/findList.action" name="srcForm" method="post">
<!--操作结果  -->
<s:hidden name="actionResult" id="actionResult"></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header"><s:text name="systemparam_list_th_title" /></h2>
<!-- 条件查询 -->
<div class="row">
<div class="col-sm-4">
	<div class="form-group">
	    <label class="col-sm-4 control-label">
	    <s:text name="systemparam_list_th_paramname" />：
	    </label>
	    <div class="col-sm-8">
	     <input type="text" class="form-control" name="systemparam.ParamName" id="ParamName" value="<s:property value="systemparam.ParamName" />">
	    </div>
	</div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="systemparam_list_th_paramvalue" />：
      </label>
      <div class="col-sm-8">
       <input type="text" class="form-control" name="systemparam.ParamValue" id="ParamValue" value="<s:property value="systemparam.ParamValue" />">
    </div>
   </div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="systemparam_list_th_systemcode" />：
      </label>
      <div class="col-sm-8">
      <input type="text" class="form-control" name="systemparam.SystemCode" id="SystemCode" value="<s:property value="systemparam.SystemCode" />">
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
	<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_add();return false;">
	<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_deleteBatch();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
 </div>
</div>
<table class="tablesorter table table-striped table-hover table-condensed">
<thead>
<tr>
<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" /></th>
<th width="5%"><s:text name="systemparam_list_th_no" /></th>
<th><s:text name="systemparam_list_th_paramname" /></th>
<th><s:text name="systemparam_list_th_paramvalue" /></th>
<th><s:text name="systemparam_list_th_systemcode" /></th>
<th><s:text name="systemparam_list_th_paramremark" /></th>
<th><s:text name="systemparam_list_th_createtime" /></th>
<th width="5%"><s:text name="systemparam_list_th_status" /></th>
<th width="10%"><s:text name="systemparam_list_th_operation" /></th>
</tr>  
</thead>
<tbody>  
<s:if test="page.resultList.size()>0">
<s:iterator value="page.resultList" var="list" status="status"> 
<tr>
<td><input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="ParamId" />"/></td>
<td><s:property value="#status.count" /></td>
<td><span title="<s:property value="ParamName" />"><s:property value="ParamName" /></span></td>
<td><span title="<s:property value="ParamValue" />"><s:property value="ParamValue" /></span></td>
<td><span title="<s:property value="SystemCode" />"><s:property value="SystemCode" /></span></td>
<td><span title="<s:property value="ParamRemark" />"><s:property value="ParamRemark" /></span></td>
<td><span title="<s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="CreateTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
<td><CW:status url="/systemparamAjax/changeStatus.action" field="Status" ><s:hidden value="%{#list.ParamId}" /></CW:status>
</td>
<td>
<div class="btn-group">
<a class="btn btn-default dropdown-toggle btn-sm " data-toggle="dropdown" href="#" onclick="return false;">
<s:text name="operation" />
<span class="caret"></span>
</a>
<ul class="dropdown-menu pull-right">
<li><a href="#" onclick="_detail('<s:property value="ParamId" />');return false;">
<s:text name="operation_detail" /></a> 
</li> 
<li><a href="#" onclick="_modify('<s:property value="ParamId" />');return false;">
<s:text name="operation_modify" /></a> 
</li> 
<li><a href="#" onclick="_deleteOne('<s:property value="ParamId" />');return false;">
<s:text name="operation_delete" /></a> 
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
<CW:load/>
</body>
</html>
