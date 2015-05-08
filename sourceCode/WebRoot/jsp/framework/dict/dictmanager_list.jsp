<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/cw" prefix="CW" %>
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

<title><s:text name="user_list_page_title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script type="text/javascript" src="<%=basePath%>js/commons/common.js"></script>
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
	          7:{sorter: 'text'},	
	          8:{sorter: false},	
	          9:{sorter: false}	
	       }	
		});
	 var actionResult = $("#actionResult").val();
	 if(actionResult=="success"){
	 	art.dialog.tips('<s:text name="list_successfuloperation"></s:text>');
	 }else if(actionResult=="error"){
	 	art.dialog.tips('<s:text name="list_operationfailed"></s:text>');
	 }
	 //全选/取消全选
		$("#chk_all").click(function(){
		$("input[name='chk_list']").prop("checked", this.checked);
		});
		 $("input[name='chk_list']").click(function() {
		    var $subs = $("input[name='chk_list']");
		    $("#ckAll").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
  		});
	});
	//获取被选中的checkbox的值
	function getCheckedValue(){
		var chk_list = document.getElementsByName('chk_list');
		var chkString = "";
		for(var i=0;i<chk_list.length;i++){
			if(chk_list[i].checked){
				 chkString =chk_list[i].value+","+chkString;
			}
		}
		//var chkValue = chkString.substring(0,chkString.length-1).split(",");
		//return chkValue;
		return chkString.substring(0,chkString.length-1);
	}
	//删除字典
	function _deleteOne(dictId){
		var scode=$("#dictListCode").val();
		art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
		 $("#loadingdiv").show();
				document.srcForm.action="<%=path%>/dict/deleteDictmanager.action?dictId="+dictId+"&scode="+scode;
				document.srcForm.submit();
			}, function(){
			    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
			});
	}
//单条删除
	function _delete(){
		var scode=$("#dictListCode").val();
		var dictId = getCheckedValue(); 
		if(dictId==""||dictId==null){
			art.dialog.tips('<s:text name="tip_select_least_one"></s:text>');
		}else{
			art.dialog.confirm('<s:text name="operation_confirm"><s:param><s:text name="operation_delete"></s:text></s:param></s:text>', function(){
			 $("#loadingdiv").show();
				document.srcForm.action="<%=basePath%>/dict/deleteDictmanager.action?dictId="+dictId+"&scode="+scode;
				document.srcForm.submit();
			}, function(){
			    art.dialog.tips('<s:text name="operation_cancel"></s:text>');
			});
		}
	}
//新增字典
	function _insertDictManager() {
			document.srcForm.action="<%=basePath%>dict/toAddDictManager.action";
			document.srcForm.submit();
		}
//修改字典
	function _modify(dictId) {
		 //设置请求的url 
		var _url = "<%=basePath%>dict/toUpdateDictmanager.action?dictId="+dictId;
		document.srcForm.action=_url;
		document.srcForm.submit();
	}
	//查询一条数据
	function _detail(dictId){
			var _url = "<%=basePath%>dict/detailDictmanager.action?dictId="+dictId;
			document.srcForm.action=_url;
			document.srcForm.submit();
		}
	//刷新页面
	function _resresh(){
		$("#DictData1").val("");
		$("#DictName").val("");
		$("#Status").val("");
		var code = $("#dictListCode").val();
		document.srcForm.action="<%=path%>/dict/getDictManager.action?code="+code;
		document.srcForm.submit();
	}
	//返回
	function _back(){
		document.srcForm.action="<%=path%>/dict/findAllDict.action";
		document.srcForm.submit();
	}
	//带条件查询
	function _search(){
		var code = $("#dictListCode").val();
		document.srcForm.action="<%=path%>/dict/getDictManager.action?code="+code;
		document.srcForm.submit();
	}
	//重置查询条件
	function _reset(){
		$("#DictData1").val("");
		$("#DictName").val("");
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
	<form action="dict/getDictManager.action" name="srcForm" id="srcForm" method="post">
		<input type="hidden" id="dictListCode" name="dict.dictListCode" value='<s:property value="dictListCode" />' />
		<input type="hidden" id="hiddenListName" name="hiddenListName" value='<s:property value="ListName" />' />
		<input type="hidden" id="hiddenListStatus" name="hiddenListStatus" value='<s:property value="ListStatus" />' />
		<input type="hidden" id="hiddenData1" name="hiddenData1" value='<s:property value="Data1" />' />
		<s:hidden name="actionResult" id="actionResult"></s:hidden>
		<!-- 模块名称  -->
		<h2 class="page-header"><s:text name="dictmanager_list_th_Title" /></h2>
		<!-- 条件查询 -->
<div class="row">
<div class="col-sm-4">
	<div class="form-group">
	    <label class="col-sm-4 control-label">
	    <s:text name="dict_manager_th_DictData1" />：
	    </label>
	    <div class="col-sm-8">
	    <input type="text" class="form-control" name="dictmanager.DictData1" id="DictData1" value="<s:property value="dictmanager.DictData1" />">
	    </div>
	</div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="dict_list_th_DictListName" />：
      </label>
      <div class="col-sm-8">
      <input type="text" class="form-control" name="dictmanager.DictName" id="DictName" value="<s:property value="dictmanager.DictName" />">
    </div>
   </div>
</div>
<div class="col-sm-4">
<div class="form-group">
      <label class="col-sm-4 control-label">
      <s:text name="dict_list_th_DictListStatus" />：
      </label>
      <div class="col-sm-8">
      <select name="dictmanager.Status" id="Status" class="form-control">
		<option value=""></option>
		<s:if test="dictmanager.Status==0">
		<option value="0" selected="selected"><s:text name="dict_list_th_DictListStatus_disable" /></option>
		</s:if>
		<s:else>
		<option value="0"><s:text name="dict_list_th_DictListStatus_disable" /></option>
		</s:else>
		<s:if test="dictmanager.Status==1">
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
	<input name="add" type="button" value="<s:text name="operation_add" />" class="btn btn-default" onclick="_insertDictManager();return false;">
	<input name="delete" type="button" value="<s:text name="operation_delete" />" class="btn btn-default" onclick="_delete();return false;">
	<input name="refresh" type="button" value="<s:text name="operation_resresh" />" class="btn btn-default" onclick="_resresh();return false;">
 	<input name="back" type="button" value="<s:text name="operation_back" />" class="btn btn-default" onclick="_back();return false;">
 </div>
</div>
			<table class="table table-striped tablesorter table-hover table-condensed" >
				<thead>
				<tr>
					<th width="40px"><input type="checkbox" name="chk_all" id="chk_all" />
					</th>
					<th width="40px"><s:text name="dict_manager_th_no" /></th>
					<th><s:text name="dict_manager_th_DictListCode" />
					</th>

					<th><s:text name="dict_manager_th_DictName" /></th>
					
					<th><s:text name="dict_manager_th_DictData1" /></th>
					<th><s:text name="dict_manager_th_DictRemark" />
					</th>
					<th><s:text name="dict_manager_th_CreatePerson" />
					</th>
					<th><s:text name="dict_manager_th_CreateTime" />
					</th>
					<th width="40px"><s:text name="dict_manager_th_DictStatus" /></th>
					<th width="80px"><s:text name="dict_list_th_Operation" />
					</th>
				</tr>
				</thead>
				<tbody>
				<s:if test="listManager.size>0">
				<s:iterator value="listManager" var="dict" status="status">
				<tr>
					<td><input type="checkbox" name="chk_list"
						id="chk_list_<s:property value="#status.count" />"
						value="<s:property value="DictId" />" /></td>
					<td><s:property value="#status.count" />
					</td>
					<td><span title="<s:property value="DictListCode" />"><s:property value="DictListCode" /></span></td>
					<td><span title="<s:property value="DictName" />"><s:property value="DictName" /></span></td>
					<td><span title="<s:property value="DictData1" />"><s:property value="DictData1" /></span> 
						<input type="hidden" value='<s:property value="DictNumber" />' /></td>
					<td><span title="<s:property value="DictRemark" />"><s:property value="DictRemark" /></span></td>
					<td><CW:dataValue property="CreatePerson" field="CreatePerson" person="true"></CW:dataValue></td>
					<td><s:date name="CreateTime" format="yyyy/MM/dd hh:mm:ss" />
					</td>
						<td>
						<CW:status url="/dictAjax/managerchangeStatus.action" field="Status" ><s:hidden value="%{#dict.DictId}" /></CW:status>
					</td>
					<td>
					<div class="btn-group">
						<a class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown" href="#" onclick="return false;">
						<s:text name="operation" />
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu pull-right">
					<li><a href="#" onclick="_detail('<s:property value="DictId" />');return false;"><s:text
								name="operation_detail" /></a>
					</li>
					<li><a href="#" onclick="_modify('<s:property value="DictId" />');return false;"><s:text
								name="operation_modify" /></a>
					</li>
					<li><a href="#" onclick="_deleteOne('<s:property value="DictId" />');return false;"><s:text
								name="operation_delete" /></a>
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
	<CW:page property="pageDictmanager" url="/dict/getDictManager.action"></CW:page>
	</form>
</body>
</html>
