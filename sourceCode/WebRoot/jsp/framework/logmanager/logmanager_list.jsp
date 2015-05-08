<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
/**
 * 删除操作。
 */
function delEntity(id) {
	var switchFlag=$("#switchFlagInput").val();
	art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
	$("#loadingdiv").show();
		document.formPublisher.action="<%=path%>/logmanager/delete.action?LogId="+id+"&switchFlag="+switchFlag;
		document.formPublisher.submit();
	}, function(){
	    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
	});
}
//批量删除
function delBatchEntity(isDel){
	var switchFlag=$("#switchFlagInput").val();
	var ids = getCheckedValue(); 
	if(ids==""||ids==null){
		art.dialog.tips('<s:text name="tip_select_least_one"></s:text>');
	}else{
		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
		$("#loadingdiv").show();
			document.formPublisher.action="<%=path%>/logmanager/deleteBatch.action?LogIds="+ids+"&switchFlag="+switchFlag;
			document.formPublisher.submit();
		}, function(){
		    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
		});
	}
}
  $(document).ready(function(){
  //页面排序
   $(".tablesorter").tablesorter({
	 
	  headers:{ 
          0:{sorter: false},	
          1:{sorter: 'text'}, 
          2:{sorter: 'text'}, 
          3:{sorter: 'text'}, 
          4:{sorter: 'text'}, 
          5:{sorter: 'text'}, 
          6:{sorter: 'text'},	
          7:{sorter: 'text'},	
          8:{sorter: 'text'},	
          9:{sorter: false}	
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
//刷新页面
function _resresh(){
	_reset();
	var switchFlag=$("#switchFlagInput").val();
	document.formPublisher.action="<%=path%>/logmanager/findList.action?switchFlag="+switchFlag;
	document.formPublisher.submit();
}
//条件查询
function _search(){
$("#loadingdiv").show();
	var switchFlag=$("#switchFlagInput").val();
	document.formPublisher.action="<%=path%>/logmanager/findList.action?switchFlag="+switchFlag;
	document.formPublisher.submit();
}
//重置查询条件
function _reset(){
	$("#LogLevel").val("");
	$("#OperatObject").val("");
	$("#LogType").val("");
	$("#OperatResult").val("");
	$("#OperatTimeStart").val("");
	$("#OperatTimeEnd").val("");
}
</script>
</head>
<body >
<form method="post" id="formPublisher" name="formPublisher" action="<%=path%>/logmanager/findList.action" >
<s:hidden name="actionResult" id="actionResult"  ></s:hidden>
<!-- 模块名称 -->
<h2 class="page-header"><s:text name="logmanager_list_th_title" /></h2>
<!-- 条件查询 -->
<div class="row">
<div class="col-sm-4">
<div class="form-group">
<label class="col-sm-4 control-label">
<s:text name="logmanager_list_th_operatobject" />：
</label>
<div class="col-sm-8">
<input type="text" class="form-control" name="logmanager.OperatObject" id="OperatObject" value="<s:property value="logmanager.OperatObject" />">
</div>
</div>
</div>
<div class="col-sm-4">
<div class="form-group">
<label class="col-sm-4 control-label">
<s:text name="logmanager_list_th_loglevel" />：
</label>
<div class="col-sm-8">
<select class="form-control" name="logmanager.LogLevel" id="LogLevel">
<option value="" selected="selected"></option>
<option value="1" <s:if test="logmanager.LogLevel==1">selected='selected'</s:if>>1</option>
<option value="2" <s:if test="logmanager.LogLevel==2">selected='selected'</s:if>>2</option>
<option value="3" <s:if test="logmanager.LogLevel==3">selected='selected'</s:if>>3</option>
<option value="4" <s:if test="logmanager.LogLevel==4">selected='selected'</s:if>>4</option>
<option value="5" <s:if test="logmanager.LogLevel==5">selected='selected'</s:if>>5</option>
</select>
</div>
</div>
</div>
<div class="col-sm-4">
<div class="form-group">
<label class="col-sm-4 control-label">
<s:text name="logmanager_list_th_operatresult" />：
</label>
<div class="col-sm-8">
<select class="form-control" name="logmanager.OperatResult" id="OperatResult">
<option value="" selected="selected"></option>
<option value="-1" <s:if test="logmanager.OperatResult==-1">selected='selected'</s:if>><s:text name="list_operationfailed" /></option>
<option value="1" <s:if test="logmanager.OperatResult==1">selected='selected'</s:if>><s:text name="list_successfuloperation" /></option>
</select>
</div>
</div>
</div>
<div class="col-sm-4">
<div class="form-group">
<label class="col-sm-4 control-label">
<s:text name="logmanager_list_th_operattime" />：
</label>
<div class="col-sm-8">
<input class="form-control" id="OperatTimeStart" name="logmanager.OperatTimeStart" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'OperatTimeEnd\')}'});" readonly="readonly" type="text" class="Wdate" value="<s:property value="logmanager.OperatTimeStart" />" style="width:45%"/> 
	-
<input class="form-control" id="OperatTimeEnd" name="logmanager.OperatTimeEnd" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'OperatTimeStart\')}'});" readonly="readonly" type="text" class="Wdate" value="<s:property value="logmanager.OperatTimeEnd" />" style="width:45%"/> 
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
<!-- 操作 -->
<div class="col-xs-6" style="text-align: right">
<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="delBatchEntity();return false;">
<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
</div>
</div>
<table class="tablesorter table table-striped table-hover table-condensed">
<thead>
<tr>
<th width="5%"><CW:checkAll childName="chk_list" name="checkAll" id="checkAll" />
</th>
<th><s:text name="logmanager_list_th_operatobject" /></th>
<th><s:text name="logmanager_list_th_logtype" /></th>
<th><s:text name="logmanager_list_th_loglevel" /></th>
<th><s:text name="logmanager_list_th_operatperson" /></th>
<th><s:text name="logmanager_list_th_operatip" /></th>
<th><s:text name="logmanager_list_th_operatresult" /></th>
<th><s:text name="logmanager_list_th_operattime" /></th>
<th><s:text name="logmanager_list_th_logmessage" /></th>
<th><s:text name="logmanager_list_th_operation" /></th>
</tr>
</thead>
<tbody>
<s:if test="page.resultList.size()>0">
<s:iterator value="page.resultList" var="publisher" status="status">
<tr>
<td><input type="checkbox" name="chk_list" id="chk_list_<s:property value="#status.count" />" value="<s:property value="LogId" />"/>
</td>
<td><span title="<s:property value="OperatObject" />"><s:property value="OperatObject" /></span></td>
<td><span title="<s:property value="LogType" />"><s:property value="LogType" /></span></td>
<td><span title="<s:property value="LogLevel" />"><s:property value="LogLevel" /></span></td>
<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
<td><span title="<s:property value="OperatIp" />"><s:property value="OperatIp" /></span></td>
<td><s:if test="%{OperatResult == 0}"><s:text name="list_operationfailed" /></s:if><s:if test="%{OperatResult == 1}"><s:text name="list_successfuloperation" /></s:if></td>
<td><span title="<s:date name="OperatTime" format="yyyy/MM/dd HH:mm:ss" />"><s:date name="OperatTime" format="yyyy/MM/dd HH:mm:ss" /></span></td>
<td><span title="<s:property value="LogMessage" />"><s:property value="LogMessage" /></span></td>
<td>
<a href="#" onclick="delEntity('<s:property value="LogId" />');return false;"><s:text name="operation_delete" /></a>
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
<CW:page property="page" url="logmanager/findList.action" formName="formPublisher"></CW:page>	
</form>
</body>
</html>
